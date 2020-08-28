module MultiplesOf3And5Spec where

import MultiplesOf3And5 (solution)
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "MultiplesOf5And5" $ do
    it "basic tests" $ do
      solution 10 `shouldBe` 23
      solution 20 `shouldBe` 78
      solution 200 `shouldBe` 9168


