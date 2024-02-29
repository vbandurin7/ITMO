{-# LANGUAGE InstanceSigs #-}
module HW4.T1
  ( EvaluationError (..)
  , ExceptState (..)
  , mapExceptState
  , wrapExceptState
  , joinExceptState
  , modifyExceptState
  , throwExceptState
  , eval
  ) where

import Control.Monad (ap)
import HW4.Types (Annotated (..), Except (..), Expr (..), Prim (..))

newtype ExceptState e s a = ES { runES :: s -> Except e (Annotated s a) }

mapExceptState :: (a -> b) -> ExceptState e s a -> ExceptState e s b
mapExceptState f (ES runFunc) = ES $ \s -> case runFunc s of
  Error e          -> Error e
  Success (x :# t) -> Success (f x :# t)

wrapExceptState :: a -> ExceptState e s a
wrapExceptState a = ES $ \s -> Success (a :# s)

joinExceptState :: ExceptState e s (ExceptState e s a) -> ExceptState e s a
joinExceptState oldState = ES $ \st -> case runES oldState st of
  Error e                    -> Error e
  Success (ES newState :# s) -> newState s

modifyExceptState :: (s -> s) -> ExceptState e s ()
modifyExceptState f = ES $ \s -> Success (() :# f s)

throwExceptState :: e -> ExceptState e s a
throwExceptState e = ES $ \_ -> Error e

instance Functor (ExceptState e s) where
  fmap :: (a -> b) -> ExceptState e s a -> ExceptState e s b
  fmap = mapExceptState

instance Applicative (ExceptState e s) where
  pure :: a -> ExceptState e s a
  pure = wrapExceptState
  (<*>) :: ExceptState e s (a -> b) -> ExceptState e s a -> ExceptState e s b
  (<*>) = Control.Monad.ap

instance Monad (ExceptState e s) where
  (>>=) :: ExceptState e s a -> (a -> ExceptState e s b) -> ExceptState e s b
  oldState >>= f = joinExceptState (fmap f oldState)

data EvaluationError = DivideByZero
  deriving Show

evalBinary :: (Double -> Double -> Double) -> (Double -> Double -> Prim Double) -> Expr -> Expr -> ExceptState EvaluationError [Prim Double] Double
evalBinary op primOp x y = do
  first <- eval x
  second <- eval y
  modifyExceptState (primOp first second :)
  return (first `op` second)

evalUnary :: (Double -> Double) -> (Double -> Prim Double) -> Expr -> ExceptState EvaluationError [Prim Double] Double
evalUnary op primOp x = do
  arg <- eval x
  modifyExceptState (primOp arg :)
  return (op arg)

eval :: Expr -> ExceptState EvaluationError [Prim Double] Double
eval (Val x) = return x

eval (Op (Add x y)) = evalBinary (+) Add x y
eval (Op (Sub x y)) = evalBinary (-) Sub x y
eval (Op (Mul x y)) = evalBinary (*) Mul x y
eval (Op (Div x y)) = do
  first <- eval x
  second <- eval y
  if second == 0 then throwExceptState DivideByZero else modifyExceptState(Div first second :)
  if second == 0 then throwExceptState DivideByZero else return (first / second)

eval (Op (Abs x)) = evalUnary abs Abs x
eval (Op (Sgn x)) = evalUnary signum Sgn x
