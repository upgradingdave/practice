module Rot13 where

cipher :: (Num a, Ord a) => a -> a -> a -> a -> a
cipher min max offset value = 
  if value >= min && value <= max  
  then if value + offset <= max then value + offset else (min + (offset - (max - value))) - 1  
  else value   

upperCase13Cipher :: (Num a, Ord a) => a -> a 
upperCase13Cipher = cipher 65 90 13

lowerCase13Cipher :: (Num a, Ord a) => a -> a
lowerCase13Cipher = cipher 97 122 13

cipher13 :: (Num a, Ord a) => a -> a
cipher13 v = upperCase13Cipher (lowerCase13Cipher v)

numToChar :: Int -> Char
numToChar n = toEnum n :: Char

rot13 :: String -> String
rot13 msg = map numToChar result
  where result = map (cipher13 . fromEnum) msg  
