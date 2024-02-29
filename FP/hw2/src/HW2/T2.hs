module HW2.T2
  ( joinWith
  , splitOn
  ) where

import Data.List.NonEmpty (NonEmpty (..))

pushFront :: a -> NonEmpty [a] -> NonEmpty [a]
pushFront x (w :| t) = (x : w) :| t

shift :: NonEmpty [a] -> NonEmpty [a]
shift (w :| t) = [] :| (w:t)

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn _ [] = [] :| []
splitOn sep (x:xs)
  | sep == x  = shift (splitOn sep xs)
  | otherwise = pushFront x (splitOn sep xs)

joinWith :: a -> NonEmpty [a] -> [a]
joinWith _ (x:|[]) = x
joinWith sep (x:|(xsh:xst)) = x ++ [sep] ++ joinWith sep (xsh :| xst)
