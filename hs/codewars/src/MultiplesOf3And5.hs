module MultiplesOf3And5 where

divisibleBy3or5 :: Integer -> Bool
divisibleBy3or5 x = mod x 3 == 0 || mod x 5 == 0 

solution :: Integer -> Integer
solution number = sum (filter divisibleBy3or5 [1..number-1])

