module ParseIntSpec where 

import ParseInt (parseInt)
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "parseInt" $ do
    it "one" $ do
      parseInt "one" `shouldBe` 1
    it "twenty" $ do
      parseInt "twenty" `shouldBe` 20
    it "two hundred forty-six" $ do
      parseInt "two hundred forty-six" `shouldBe` 246
    it "six hundred sixty-six thousand six hundred and sixty-six" $ do
      parseInt "six hundred sixty-six thousand six hundred and sixty-six" `shouldBe` 666666
    it "fifty-nine thousand and eighteen" $ do
      parseInt "fifty-nine thousand and eighteen" `shouldBe` 59018
    it "one million" $ do
      parseInt "one million" `shouldBe` 1000000
    it "one hundred eighteen thousand six hundred and eighty-eight" $ do
      parseInt "one hundred eighteen thousand six hundred eighty-eight" `shouldBe` 118688