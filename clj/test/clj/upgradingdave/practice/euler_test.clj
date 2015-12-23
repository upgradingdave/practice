(ns upgradingdave.practice.euler-test
  (:require [expectations                 :refer :all]
            [upgradingdave.practice.euler :refer :all]))

(expect 23 (problem1 (range 1 10)))
(expect 233168 (problem1 (range 1 1000)))

