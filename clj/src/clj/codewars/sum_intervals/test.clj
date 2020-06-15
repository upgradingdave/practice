(ns codewars.sum-intervals.test
  (:require [clojure.test :refer :all]
            [codewars.sum-intervals.core :refer :all]))

(deftest sample-tests
  (are [expected calculated] (= expected calculated)
    4   (sum-intervals [[1 5]])
    8   (sum-intervals [[1 5] [6 10]])))



