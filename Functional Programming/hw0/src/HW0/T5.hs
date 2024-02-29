module HW0.T5
  ( Nat
  , nFromNatural
  , nmult
  , nplus
  , ns
  , nToNum
  , nz
  ) where

import Numeric.Natural

type Nat a = (a -> a) -> a -> a

nz :: Nat a
nz _ a = a

ns :: Nat a -> Nat a
ns n f x = f (n f x)

nplus :: Nat a -> Nat a -> Nat a
nplus a b f x = a f (b f x)

nmult :: Nat a -> Nat a -> Nat a
nmult a b x = a (b x)

nFromNatural :: Natural -> Nat a
nFromNatural 0 = nz
nFromNatural n = ns (nFromNatural (n - 1))

nToNum :: Num a => Nat a -> a
nToNum n = n (+ 1) 0
