{-# LANGUAGE InstanceSigs #-}
module HW3.T4
  ( State (..)
  , Prim (..)
  , Expr (..)
  , mapState
  , wrapState
  , joinState
  , modifyState
  , eval
  ) where

import HW3.T1

newtype State s a = S { runS :: s -> Annotated s a }

mapState :: (a -> b) -> State s a -> State s b
mapState f oldState = S $ \s ->
  let (a :# newState) = runS oldState s
  in f a :# newState

wrapState :: a -> State s a
wrapState a = S (a :#)

joinState :: State s (State s a) -> State s a
joinState oldState = S $ \s ->
  let (S a :# newState) = runS oldState s
  in a newState

modifyState :: (s -> s) -> State s ()
modifyState f = S $ \s -> () :# f s

instance Functor (State s) where
  fmap :: (a -> b) -> State s a -> State s b
  fmap = mapState

instance Applicative (State s) where
  pure :: a -> State s a
  pure = wrapState
  (<*>) :: State s (a -> b) -> State s a -> State s b
  (<*>) s1 s2 = S $ \s ->
    let (f :# newState1) = runS s1 s
        (a :# newState2) = runS s2 newState1
    in f a :# newState2

instance Monad (State s) where
  (>>=) :: State s a -> (a -> State s b) -> State s b
  oldState >>= f = S $ \s ->
    let (a :# newState) = runS oldState s
    in runS (f a) newState

data Prim a =
    Add a a
  | Sub a a
  | Mul a a
  | Div a a
  | Abs a
  | Sgn a
  deriving Show

data Expr = Val Double | Op (Prim Expr)
  deriving Show

instance Num Expr where
  (+) :: Expr -> Expr -> Expr
  x + y = Op (Add x y)

  (-) :: Expr -> Expr -> Expr
  x - y = Op (Sub x y)

  (*) :: Expr -> Expr -> Expr
  x * y = Op (Mul x y)

  abs :: Expr -> Expr
  abs x = Op (Abs x)
  
  signum :: Expr -> Expr
  signum x = Op (Sgn x)
  
  fromInteger :: Integer -> Expr
  fromInteger x = Val (fromInteger x)

instance Fractional Expr where
  (/) :: Expr -> Expr -> Expr
  x / y = Op (Div x y)
  fromRational :: Rational -> Expr
  fromRational x = Val (fromRational x)

eval :: Expr -> State [Prim Double] Double
eval (Val x) = return x

eval (Op (Add x y)) = do
  first <- eval x
  second <- eval y
  modifyState (Add first second :)
  return (first + second)

eval (Op (Sub x y)) = do
  first <- eval x
  second <- eval y
  modifyState (Sub first second :)
  return (first - second)

eval (Op (Mul x y)) = do
  first <- eval x
  second <- eval y
  modifyState (Mul first second :)
  return (first * second)

eval (Op (Div x y)) = do
  first <- eval x
  second <- eval y
  modifyState (Div first second :)
  return (first / second) 

eval (Op (Abs x)) = do
  first <- eval x
  modifyState (Abs first :)
  return (abs first)

eval (Op (Sgn x)) = do
  first <- eval x
  modifyState (Sgn first :)
  return (signum first) 