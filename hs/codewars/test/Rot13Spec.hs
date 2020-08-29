module Rot13Spec where

import Rot13 (rot13)
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "rot13" $ do
    it "test" $
      rot13 "test" `shouldBe` "grfg"
    it "Test" $
      rot13 "Test" `shouldBe` "Grfg"
