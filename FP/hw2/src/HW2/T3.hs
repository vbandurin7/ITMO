{-# LANGUAGE LambdaCase #-}
module HW2.T3
  ( epart
  , mcat
  ) where

mcat :: Monoid a => [Maybe a] -> a
mcat = foldl f mempty
  where
    f :: Monoid a => a -> Maybe a -> a
    f acc Nothing  = acc
    f acc (Just x) = acc <> x

epart :: (Monoid a, Monoid b) => [Either a b] -> (a, b)
epart = foldMap $ \case
    Left x  -> (x, mempty)
    Right x -> (mempty, x)
