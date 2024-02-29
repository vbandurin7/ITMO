{-# OPTIONS_GHC -Wno-incomplete-patterns #-}
module HW1.T3
  ( Tree (..)
  , tsize
  , tdepth
  , tmember
  , tinsert
  , tFromList
  ) where

type Meta = Int

data Tree a = Leaf | Branch Meta (Tree a) a (Tree a)
  deriving (Show)

tsize :: Tree a -> Int
tsize Leaf             = 0
tsize (Branch s _ _ _) = s

tdepth :: Tree a -> Int
tdepth Leaf             = 0
tdepth (Branch _ l _ r) = max (tdepth l) (tdepth r) + 1

tmember :: Ord a => a -> Tree a -> Bool
tmember _ Leaf = False
tmember a (Branch _ l v r)
    | a == v    = True
    | a < v     = tmember a l
    | otherwise = tmember a r

tinsert :: Ord a => a -> Tree a -> Tree a
tinsert a Leaf                = Branch 1 Leaf a Leaf
tinsert a (Branch s l v r)
    | a < v     = balance (Branch (tsize newLeft + tsize r + 1) newLeft v r)
    | a > v     = balance (Branch (tsize l + tsize newRight + 1) l v newRight)
    | otherwise = Branch s l v r
    where
      newLeft = tinsert a l
      newRight = tinsert a r

tFromList :: Ord a => [a] -> Tree a
tFromList []     = Leaf
tFromList (x:xs) = foldr tinsert Leaf (x:xs)

bfactor :: Tree a -> Int
bfactor Leaf             = 0
bfactor (Branch _ l _ r) = tdepth r - tdepth l

balance :: Tree a -> Tree a
balance Leaf = Leaf
balance (Branch s l v r)
    | (tdepth r - tdepth l == 2)  && (bfactor r < 0) = rotateLeft (Branch newSizeLeftRotation l v newRight)
    | tdepth r - tdepth l == 2 = rotateLeft (Branch s l v r)
    | (tdepth r - tdepth l == -2) && (bfactor l > 0) = rotateRight (Branch newSizeRightRotation newLeft v r)
    | tdepth r - tdepth l == -2 = rotateRight (Branch s l v r)
    | otherwise = Branch s l v r
      where
        newRight = rotateRight r
        newLeft = rotateLeft l
        newSizeLeftRotation = tsize l + tsize newRight + 1
        newSizeRightRotation = tsize newLeft + tsize r + 1


rotateLeft :: Tree a -> Tree a
rotateLeft Leaf                               = Leaf
rotateLeft (Branch _ ql q (Branch _ pl p pr)) = Branch newPSize newQ p pr
    where
      newQSize = tsize ql + tsize pl + 1
      newQ = Branch newQSize ql q pl
      newPSize = tsize newQ + tsize pr + 1

rotateRight :: Tree a -> Tree a
rotateRight Leaf                               = Leaf
rotateRight (Branch _ (Branch _ ql q qr) p pr) = Branch newQSize ql q newP
    where
      newPSize = tsize qr + tsize pr + 1
      newP = Branch newPSize qr p pr
      newQSize = tsize ql + tsize newP + 1