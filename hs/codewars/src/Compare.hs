module Compare where

contains :: (Eq a, Num a) => a -> [a] -> [a] -> (Bool, [a])
contains _ [] rs = (False, rs)
contains a (b:bs) rs = 
  if (a * a) == b 
  then (True, bs ++ rs)
  else contains a bs (b:rs)

checkContains :: (Eq t, Num t) => [t] -> [t] -> [Bool]
checkContains [] [] = []
checkContains (a:as) bs = let (result, cs) = contains a bs [] in result : checkContains as cs 
  
comp :: [Integer] -> [Integer] -> Bool
comp [] [] = True
comp as bs = (length as == length bs) && all (==True) (checkContains as bs)

-- | How cool is this?
-- comp :: [Integer] -> [Integer] -> Bool
-- comp as bs = sort (map (^2) as) == sort bs