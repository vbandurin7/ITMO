module HW0.T4
  ( fac
  , fib
  , map'
  , repeat'
  ) where

import Data.Function (fix)
import Numeric.Natural (Natural)

repeat' :: a -> [a]
repeat' x = fix (x:)

map' :: (a -> b) -> [a] -> [b]
map' = fix (\rec f l -> case l of
                        x:xs -> f x : rec f xs
                        _    -> [])

fib :: Natural -> Natural
fib = fix (\rec first second n -> if n == 0
                                  then first
                                  else rec second (first + second) (n - 1)
                                  ) 0 1

fac :: Natural -> Natural
fac = fix (\rec n -> if n <= 1 then 1 else n * rec (n-1))
