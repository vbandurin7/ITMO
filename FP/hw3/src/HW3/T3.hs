module HW3.T3
  ( joinOption
  , joinExcept
  , joinAnnotated
  , joinList
  , joinFun
  ) where

import HW3.T1

joinOption :: Option (Option a) -> Option a
joinOption None            = None
joinOption (Some None)     = None
joinOption (Some (Some x)) = Some x

joinExcept :: Except e (Except e a) -> Except e a
joinExcept (Error e) = Error e
joinExcept (Success (Error e)) = Error e
joinExcept (Success (Success a)) = Success a

joinAnnotated :: Semigroup e => Annotated e (Annotated e a) -> Annotated e a
joinAnnotated ((a :# e2) :# e1) = a :# (e1 <> e2)

joinList :: List (List a) -> List a
joinList Nil = Nil
joinList (x :. xs) = collect x (joinList xs)
  where
    collect :: List a -> List a -> List a
    collect Nil s        = s
    collect (ah :. at) s = ah :. collect at s


joinFun :: Fun i (Fun i a) -> Fun i a
joinFun (F f) = F (\i -> (\(F g) -> g i) (f i))