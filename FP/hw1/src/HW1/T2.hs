module HW1.T2
  ( N (..)
  , nplus
  , nmult
  , nsub
  , nFromNatural
  , nToNum
  , ncmp
  , nEven
  , nOdd
  , ndiv
  , nmod
  ) where


import           Data.Maybe      (fromJust)
import           Numeric.Natural

-- infixr 0 $
-- ($) :: (a -> b) -> a -> b  -- function application
-- f $ x = f x

data N = Z | S N

-- let ONE = S(Z)

nplus :: N -> N -> N
nplus Z Z         = Z
nplus (S x) Z     = S x
nplus Z (S x)     = S x
nplus (S a) (S b) = nplus a (S (S b))

nmult :: N -> N -> N
nmult Z _         = Z
nmult _ Z         = Z
nmult (S a) (S b) = nplus (S b) (nmult a (S b))

nsub :: N -> N -> Maybe N
nsub Z Z         = Just Z
nsub (S x) Z     = Just (S x)
nsub Z (S _)     = Nothing
nsub (S a) (S b) = nsub a b

ncmp :: N -> N -> Ordering
ncmp Z Z         = EQ
ncmp Z _         = LT
ncmp _ Z         = GT
ncmp (S a) (S b) = ncmp a b

getFromN :: Natural -> N -> N
getFromN 0 a = a
getFromN n x = getFromN (n - 1) (S x)

nFromNatural :: Natural -> N
nFromNatural n = getFromN n Z

nToNum :: Num a => N -> a
nToNum Z     = 0
nToNum (S x) = nToNum x + 1

nEven :: N -> Bool
nEven Z         = True
nEven (S Z)     = False
nEven (S (S x)) = nEven x

nOdd :: N -> Bool
nOdd x = not (nEven x)

divideInt :: N -> N -> N -> N
divideInt _ Z _ = undefined
divideInt a b c = if ncmp a b == LT
                  then c
                  else divideInt (fromJust (nsub a b)) b (S c)

ndiv :: N -> N -> N
ndiv a b = divideInt a b Z

nmod :: N -> N -> N
nmod _ Z = undefined
nmod Z _ = Z
nmod a b = fromJust (nsub a (nmult (ndiv a b) b))