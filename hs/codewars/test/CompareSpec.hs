module CompareSpec where

import Compare (comp)
import Test.Hspec
import Test.QuickCheck

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "comp" $ do
    it "should return `True`  for empty lists" $ comp [] [] `shouldBe` True    
    it "should return `False` if the lists differ in length" $ property $ 
      forAll (listOf1 arbitrary) $ \ys@(_:xs) ->
        (comp ys xs `shouldBe` False) .&. (comp xs ys `shouldBe` False)
    
    it "should return `True` for a valid example" $ do
      let a1 = [121, 144, 19, 161, 19, 144, 19, 11]
      let a2 = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]
      comp a1 a2 `shouldBe` True
      
    it "should return `False`" $ do
      let a1 = [1, 2, 3]
      let a2 = [1, 3, 10]
      comp a1 a2 `shouldBe` False
      let a3 = [12, 12] 
      let a4 = [144, 7]
      comp a3 a4 `shouldBe` False  