(ns upgradingdave.practice.euler)

(defn problem1
  "If we list all the natural numbers below 10 that are multiples of 3
  or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find
  the sum of all the multiples of 3 or 5 below 1000."
  [ns]
  (letfn [(mult    [x]  (fn [y] (= (mod y x) 0)))
          (by3or5? [n]  (or ((mult 3) n) ((mult 5) n)))]
    (transduce (filter by3or5?) + ns)))
