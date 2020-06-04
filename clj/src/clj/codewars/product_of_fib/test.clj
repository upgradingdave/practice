(ns codewars.product-of-fib.test
  (:require [clojure.test :refer :all]
            [codewars.product-of-fib.core :refer :all]))

(deftest a-test1
  (testing "Test 1"
    (is (= (product-fib 4895) [55N 89N true]))))
(deftest a-test2
  (testing "Test 2"
    (is (= (product-fib 5895) [89, 144, false]))))
    





