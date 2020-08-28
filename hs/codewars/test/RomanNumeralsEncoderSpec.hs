module RomanNumeralsEncoderSpec where

import Experiment
import RomanNumeralsEncoder (solution)
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "RomanNumeralsEncoder" $ do
    it "encode roman numerals" $ do
      solution 1 `shouldBe` "I"
      solution 3 `shouldBe` "III"
      solution 4 `shouldBe` "IV"
