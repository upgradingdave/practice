(ns upgradingdave.practice.euler-test
  (:require [expectations                 :refer :all]
            [upgradingdave.practice.euler :refer :all]))

(expect 23     (problem1 (range 1 10)))
(expect 233168 (problem1 (range 1 1000)))

(expect 10      (problem2 10))
(expect 4613732 (problem2 4000000))

(expect [5 7 13 29] (prime-factors 13195))

(expect 29   (problem3 13195))
(expect 6857 (problem3 600851475143))

(expect true (palindrome? 999))
(expect nil? (palindrome? 990))
(expect true (palindrome? 1001))
(expect true (palindrome? 1000000001))
(expect nil? (palindrome? 1000001001))
(expect nil? (palindrome? 100011001))
(expect true (palindrome? 1000110001))
(expect true (palindrome? 11))
(expect nil? (palindrome? 12))
(expect true (palindrome? 1))
(expect true (palindrome? 4))

(expect 9009   (problem4 2))
(expect 906609 (problem4 3))

(expect 385      (sum-of-squares 10))
(expect 3025     (square-of-sum  10))
(expect 2640     (problem6       10))
(expect 25164150 (problem6       100))

;; Takes a couple mins
;; (expect 104743 (problem7 10001))

(expect 5832        (problem8 4))
(expect 23514624000 (problem8 13))

(expect nil? (is-square 5))
(expect 2    (is-square 4))

(expect 60       (problem9 12))
(expect 31875000 (problem9 1000))

(expect 17 (problem10 10))
;;(expect 17 (problem10 2000000))

(expect 70600674 (problem11))

(expect "5537376230" (problem13))

(expect 137846528820 (problem15 20))
