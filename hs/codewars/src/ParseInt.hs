module ParseInt where

containsDash :: String -> Bool
containsDash [] = False
containsDash (x : xs) = (x == '-') || containsDash xs

splitOnDash :: String -> [String]
splitOnDash [] = [""]
splitOnDash ('-' : xs) = "" : splitOnDash xs
splitOnDash (x : xs) = let (y : ys) = splitOnDash xs in (x : y) : ys

convertWord :: String -> Int
convertWord x =
  if containsDash x
    then let (y:z:zs) = splitOnDash x in convertWord y + convertWord z
    else case x of
      "one" -> 1
      "two" -> 2
      "three" -> 3
      "four" -> 4
      "five" -> 5
      "six" -> 6
      "seven" -> 7
      "eight" -> 8
      "nine" -> 9
      "ten" -> 10
      "eleven" -> 11
      "twelve" -> 12
      "thirteen" -> 13
      "fourteen" -> 14
      "fifteen" -> 15
      "sixteen" -> 16
      "seventeen" -> 17
      "eighteen" -> 18
      "nineteen" -> 19
      "twenty" -> 20
      "thirty" -> 30
      "fourty" -> 40
      "forty" -> 40
      "fifty" -> 50
      "sixty" -> 60
      "seventy" -> 70
      "eighty" -> 80
      "ninety" -> 90
      x -> 0

convertWords :: [String] -> Int
convertWords [] = 0
convertWords ["million", "one"] = 1000000
convertWords ("thousand":xs) = 1000 * convertWords xs
convertWords ("hundred":x:xs) = (100 * convertWord x) + convertWords xs 
convertWords (x : xs) = convertWord x + convertWords xs

parseInt :: String -> Int
parseInt x = convertWords (reverse (words x))
