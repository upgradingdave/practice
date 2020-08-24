module Experiment where

doubleMe x = x + x

doubleUs x y = doubleMe x + doubleMe y

fizBuz :: Integer -> String
fizBuz x
  | fizz && buzz = "fizzBuzz"
  | fizz = "fizz"
  | buzz = "buzz"
  | otherwise = show x
  where
    fizz = mod x 3 == 0
    buzz = mod x 5 == 0

fizzBuzz = [fizBuz x | x <- [1 .. 100]]

bmiTell :: (RealFloat a) => a -> a -> String
bmiTell weight height
  | bmi weight height <= 18.5 = "You're underweight, you emo, you!"
  | bmi weight height <= 25.0 = "You're supposedly normal. Pffft, I bet you're ugly!"
  | bmi weight height <= 30.0 = "You're fat! Lose some weight, fatty!"
  | otherwise = "You're a whale, congratulations!"
  where
    inchToMeter x = x / 39.37
    poundToKg x = x / 2.205
    bmi weight height = poundToKg weight / inchToMeter height ^ 2

getAverage :: [Int] -> Int
getAverage marks = div (sum marks) (length marks)

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]
