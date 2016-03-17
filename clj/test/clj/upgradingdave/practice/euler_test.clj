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

(expect 1366 (problem16))

(expect 23 (count-letters (numbers 342)))

(expect 20 (count-letters (numbers 115)))

(expect 36 (reduce + (map count-letters (map numbers (range 1 10)))))

(expect 70 (reduce + (map count-letters (map numbers (range 10 20)))))

(expect 19 (reduce + (map count-letters (map numbers (range 1 6)))))

(expect 21124 (problem17))

(expect 1074 (problem18 tri2))


(expect 31 (days-in-month 0))
(expect 28 (days-in-month 1))
(expect 29 (days-in-month 1 2016))
(expect 28 (days-in-month 1 2015))
(expect 31 (days-in-month 2))
(expect 30 (days-in-month 3))
(expect 31 (days-in-month 4))
(expect 30 (days-in-month 5))
(expect 31 (days-in-month 6))
(expect 31 (days-in-month 7))
(expect 30 (days-in-month 8))
(expect 31 (days-in-month 9))
(expect 30 (days-in-month 10))
(expect 31 (days-in-month 11))

(expect true  (is-leap-year? 2016))
(expect false (is-leap-year? 2015))
(expect true  (is-leap-year? 2000))
(expect false (is-leap-year? 2100))

(expect 0 (next-month 0  0 1900))
(expect 1 (next-month 0  1 1900))
(expect 1 (next-month 30 0 1900))
(expect 2 (next-month 28 1 2016))

(expect 1900 (next-year 0  0  1900))
(expect 1901 (next-year 30 11 1900))

(expect 171 (problem19))

(expect 3628800 (factorial 10))

(expect 27 (problem20 10))

(expect 648 (problem20 100))

(expect 504 (problem21 284))

