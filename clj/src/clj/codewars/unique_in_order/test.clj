(ns codewars.unique-in-order.test
  (:require [clojure.test :refer :all]
            [codewars.unique-in-order.core :refer :all]))

(deftest Tests
  (is (= (unique-in-order "AAAABBBCCDAABBB") [\A, \B, \C, \D, \A, \B]))
  (is (= (unique-in-order "ABBCcAD") [\A, \B, \C, \c, \A, \D]))
  (is (= (unique-in-order [1,2,2,3,3]) [1,2,3]))
  
)



