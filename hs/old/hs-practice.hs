-- Problem 1
-- If we list all the natural numbers below 10 that are multiples of 3
-- or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find
-- the sum of all the multiples of 3 or 5 below 1000.
problem1 :: (Integral a) => a -> a
problem1 x = sum [y | y <- [1..(x - 1)], (y `mod` 3 == 0) || (y `mod` 5 == 0)]

fib :: [Integer]
fib = nextfib 0 1
    where nextfib x y = x + y : nextfib y (x + y)

-- Problem 2
-- Find the sum of even valued fibbonacci numbers less than n
problem2 :: Integer -> Integer
problem2 n = sum (takeWhile (\x -> x < n) (filter even (fib)))
-- Test: problem2 4000000 == 4613732

prime :: Integer -> Bool
prime n = prime' n (n-1)
    where prime' x 1 = True
          prime' x y = if x `mod` y == 0 then False else prime' x (y-1)

-- Problem 3
-- Find the largest prime factor of the number n
-- 1) start with i=2
-- 2a) is i>=n? if so, it's the largest prime factor
-- 2b) is does i divide n evenly?
-- 3)  if yes, then set n = n/i and go to '2'
-- 4)  if no, increment i and goto '2'
problem3 :: Integral a => a -> a
problem3 n = problem3' n 2
    where problem3' n i
              | i >= n = i
              | n `mod` i == 0 = problem3' (n `div` i) i
              | otherwise = problem3' n (i+1)
-- Test: problem3 600851475143 == 6857

test :: Int -> Int
test 0 = -1
test n = test (floor ((fromIntegral n) / 2))

largestDigit :: Num a => Int -> a
largestDigit 1 = 9
largestDigit y = (largestDigit (y-1)) + x
    where x = (9 * (foldl (*) 1 (take (y-1) (repeat 10))))

-- How to reverse digits of a number
-- r= r * 10 + n mod 10
-- n= n/10

-- n=123
-- r=0

-- r= 0 * 10 + 123 mod 10 = 3
-- n = 123 / 10 = 12

-- n = 12
-- r = 3

-- r = 3 * 10 + 12 mod 10 = 32
-- n = 12 / 10 = 1

-- n = 1
-- r = 32

-- r = 32 * 10 + 1 mod 10 = 321
-- n = 1 / 10 = 0

reverseDigits :: Integral a => a -> a
reverseDigits n = reverseDigits' n 0
    where reverseDigits' n r
              | n <= 0 = r
              | otherwise = reverseDigits' n' r'
              where n' = (n `div` 10) 
                    r' = ((r * 10) + (n `mod` 10))

palindrome :: Integral a => a -> Bool
palindrome n = n == (reverseDigits n)

problem4 :: Integral b => Int -> b
problem4 n = foldl max 0 
             [x*y | x <- [1..i], y <- [1..i], palindrome (x*y)]
    where i = largestDigit n
-- Test: (problem4 3) == 906609

-- erastosthenes
-- 1) start with 2
-- 2) mark all factors of 2 false
-- 3) 

-- Problem 9
-- A Pythagorean triplet is a set of three natural numbers, a < b < c,
-- for which, a^2 + b^2 = c^2. For example, 32 + 42 = 9 + 16 = 25 = 52.
-- There exists exactly one Pythagorean triplet for which a + b + c =
-- 1000. Find the product abc.
-- TODO
-- problem9 :: (Integral a) => a -> [(a,a,a)]
-- problem9 x = [(a,b,c) | a+b+c == x, a < b, b < c]

factors :: Integral t => t -> [t]
factors n = n : if n `mod` 2 == 0 
                then [x | x <- [1..(n `div` 2)], n `mod` x == 0] 
                else [x | x <- [1,3..(n `div` 2)], n `mod` x == 0] 

numberOfDivisors n = 2 * (length [x | x <- [1..floor (sqrt (fromIntegral n))], n `mod` x == 0])

-- sum of n from i=1 to n is equal to (n * n+1) / 2
triangleNumber n = (n * (n+1)) `div` 2

-- triangleNumber n = sum [1..n]

-- triangleNumber' 1 = 1
-- triangleNumber' n = n + triangleNumber (n - 1)

-- triangleNumbers n last = n + last : [x | x <- triangleNumbers (n+1) (n + last)]

problem12 max = head [x | x <- map triangleNumber [1..], numberOfDivisors x > max]






