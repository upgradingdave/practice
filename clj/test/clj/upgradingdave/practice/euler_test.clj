(ns upgradingdave.practice.euler-test
  (:require [expectations                 :refer :all]
            [upgradingdave.practice.euler :refer :all]))

(expect 23     (problem1 (range 1 10)))
(expect 233168 (problem1 (range 1 1000)))

(expect 10      (problem2 10))
(expect 4613732 (problem2 4000000))

(expect [5 7 13 29] (prime-factors 13195))

(expect 29  (problem3 13195))
;;(expect nil (problem3 600851475143))






