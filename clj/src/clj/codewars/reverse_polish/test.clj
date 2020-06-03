(ns codewars.reverse-polish.test
  (:require [clojure.test :refer :all]
            [codewars.reverse-polish.core :refer :all]))

(deftest reverse-notation
  (is (= (calc "") 0))
  (is (= (calc "3") 3))
  (is (= (calc "3.5") 3.5))
  (is (= (calc "1 3 +") 4))
  (is (= (calc "1 3 *") 3))
  (is (= (calc "1 3 -") -2))
  (is (= (calc "4 2 /") 2)))



