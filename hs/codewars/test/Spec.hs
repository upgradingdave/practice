import Experiment
import ParseInt (parseInt)
import MultiplesOf3And5 (solution)
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "getAverage" $ do
    it "works for some examples" $ do
      getAverage [2, 2, 2, 2] `shouldBe` 2
      getAverage [1, 5, 87, 45, 8, 8] `shouldBe` 25
      getAverage [2, 5, 13, 20, 16, 16, 10] `shouldBe` 11
      getAverage [1, 2, 15, 15, 17, 11, 12, 17, 17, 14, 13, 15, 6, 11, 8, 7] `shouldBe` 11
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
  describe "MultiplesOf5And5" $ do
    it "basic tests" $ do
      solution 10 `shouldBe` 23
      solution 20 `shouldBe` 78
      solution 200 `shouldBe` 9168
