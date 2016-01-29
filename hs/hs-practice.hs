-- Problem 1
-- If we list all the natural numbers below 10 that are multiples of 3
-- or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find
-- the sum of all the multiples of 3 or 5 below 1000.
problem1 :: (Integral a) => a -> a
problem1 x = sum [y | y <- [1..(x - 1)], (y `mod` 3 == 0) || (y `mod` 5 == 0)]


nextfib :: Num a => a -> a -> [a]
nextfib x y = x + y : nextfib y (x + y)

fib = nextfib 0 1

-- Problem 2
-- Find the sum of even valued fibbonacci numbers less than n
--problem2 :: (Integer a) => a -> a
problem2 n = sum (takeWhile (\x -> x < n) (filter even (fib)))
-- Test: problem2 4000000 == 4613732

-- Problem 9
-- A Pythagorean triplet is a set of three natural numbers, a < b < c,
-- for which, a^2 + b^2 = c^2. For example, 32 + 42 = 9 + 16 = 25 = 52.
-- There exists exactly one Pythagorean triplet for which a + b + c =
-- 1000. Find the product abc.
-- TODO
-- problem9 :: (Integral a) => a -> [(a,a,a)]
-- problem9 x = [(a,b,c) | a+b+c == x, a < b, b < c]
