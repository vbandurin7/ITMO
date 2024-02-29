{-# LANGUAGE DerivingStrategies         #-}
{-# LANGUAGE GeneralisedNewtypeDeriving #-}
{-# LANGUAGE InstanceSigs               #-}

module HW4.T2
  ( ParseError (..)
  , runP
  , pChar
  , parseError
  , parseExpr
  ) where

import Control.Applicative
import Control.Monad
import Data.Char
import Numeric.Natural (Natural)

import HW4.T1 (ExceptState (..))
import HW4.Types

newtype ParseError = ErrorAtPos Natural
  deriving Show

newtype Parser a = P (ExceptState ParseError (Natural, String) a) -- Error ParseError | Success (a :# (Natural, String))
  deriving newtype (Functor, Applicative, Monad)

runP :: Parser a -> String -> Except ParseError a
runP (P (ES f)) s = case f (0, s) of
  Error e          -> Error e
  Success (a :# _) -> Success a

parseError :: Parser a
parseError = P $ ES $ \(pos, _) -> Error (ErrorAtPos pos)


instance Alternative Parser where
  empty :: Parser a
  empty = parseError
  (<|>) :: Parser a -> Parser a -> Parser a
  P a <|> P b = P $ ES $ \s -> case runES a s of
    Error _            -> runES b s
    Success (a1 :# s1) -> Success (a1 :# s1)

pEof :: Parser ()
pEof = P $ ES $ \(pos, s) ->
  case s of
    []    -> Success (() :# (pos, []))
    (_:_) -> Error (ErrorAtPos pos)

-- Just an example of parser that may be useful
-- in the implementation of 'parseExpr'
pChar :: Parser Char
pChar = P $ ES $ \(pos, s) ->
  case s of
    []     -> Error (ErrorAtPos pos)
    (c:cs) -> Success (c :# (pos + 1, cs))

peekChar :: Parser Char
peekChar = P $ ES $ \(pos, s) ->
  case s of
    []    -> Error (ErrorAtPos pos)
    (c:_) -> Success (c :# (pos, s))

-- No metohds
instance MonadPlus Parser

skipWhitespaces :: Parser ()
skipWhitespaces = void (many (mfilter isSpace pChar))

expect :: Char -> Parser Char
expect c = mfilter (== c) pChar

pNum :: Parser [Char]
pNum = some (mfilter isDigit pChar)

parseExpr :: String -> Except ParseError Expr
parseExpr = runP parse

parse :: Parser Expr
parse = do
  expr <- parseE
  pEof
  pure expr

parseE :: Parser Expr
parseE = do
  skipWhitespaces
  expr <- parseT
  parseE' expr

parseE' :: Expr -> Parser Expr
parseE' e = do
  skipWhitespaces
  c <- peekChar
  if c == '-' then do
    _ <- pChar
    t <- parseT
    parseE' (Op (Sub e t))
  else if c == '+' then do
    _ <- pChar
    t <- parseT
    parseE' (Op (Add e t))
  else do pure e

parseT :: Parser Expr
parseT = do
  skipWhitespaces
  expr <- parseF
  parseT' expr

parseT' :: Expr -> Parser Expr
parseT' e = do
  skipWhitespaces
  c <- peekChar
  if c == '*' then do
    _ <- pChar
    f <- parseF
    parseE' (Op (Mul e f))
  else if c == '/' then do
    _ <- pChar
    f <- parseF
    parseE' (Op (Div e f))
  else do pure e

parseF :: Parser Expr
parseF = do
  skipWhitespaces
  c <- peekChar
  if c == '(' then do
    _ <- pChar
    expr <- parseE
    skipWhitespaces
    _ <- expect ')'
    return expr
  else if isDigit c then do parseNumber
  else do parseError

parseNumber :: Parser Expr
parseNumber = parseDouble <|> parseWhole

parseDouble :: Parser Expr
parseDouble = do
  skipWhitespaces
  wp <- pNum
  _ <- expect '.'
  Val . readDouble wp <$> pNum

parseWhole :: Parser Expr
parseWhole = do 
  skipWhitespaces
  wp <- pNum
  pure $ Val $ readDouble wp ""

charToDigit :: Char -> Int
charToDigit c = fromEnum c - fromEnum '0'

parseInt :: String -> Int
parseInt = go 0 
  where
    go acc [] = acc
    go acc (x:xs) = go (acc * 10 + charToDigit x) xs

readDouble :: String -> String -> Double
readDouble wp fp = fromIntegral (parseInt wp) + readFraction fp

readFraction :: String -> Double
readFraction = foldr (\char acc -> acc / 10 + fromIntegral (digitToInt char) / 10) 0.0