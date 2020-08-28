module RomanNumeralsEncoder where

solution :: Integer -> String
solution x 
  | x < 4 = replicate (fromIntegral x) 'I'
  | x == 4 = ['I','V']
  | x < 9 =  'V' : solution (x - 5)
  | x == 9 = ['I', 'X']
  | x < 40 = 'X' : solution (x - 10)
  | x < 50 = 'X' : 'L' : solution (x - 40)
  | x < 90 = 'L' : solution (x - 50)
  | x < 100 = 'X' : 'C' : solution (x - 90)
  | x < 400 = 'C' : solution (x - 100)
  | x < 500 = 'C' : 'D' : solution (x - 400)
  | x < 900 = 'D' : solution (x - 500)
  | x < 1000 = 'C' : 'M' : solution (x - 900)
  | otherwise = 'M' : solution (x - 1000)   
