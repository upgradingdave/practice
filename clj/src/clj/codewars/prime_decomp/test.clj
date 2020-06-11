(ns codewars.prime-decomp.test
  (:require [clojure.test :refer :all]
            [codewars.prime-decomp.core :refer :all]))

;;(time (prime-factors 5648934))
(deftest a-test1
  (testing "Test 1"
    (is (= (prime-factors 86240) "(2**5)(5)(7**2)(11)"))
    (is (= (prime-factors 7919) "(7919)"))
    (is (= (prime-factors 1348934) "(2)(31)(21757)"))
    ))



