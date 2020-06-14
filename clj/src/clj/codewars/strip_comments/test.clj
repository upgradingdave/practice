(ns codewars.strip-comments.test
  (:require [clojure.test :refer :all]
            [codewars.strip-comments.core :refer :all]))

(deftest strip-comments-tests
  (are [text symbols expected] (= (strip-comments text symbols) expected)
    "apples, pears # and bananas\ngrapes\nbananas !apples"
    '("#" "!")
    "apples, pears\ngrapes\nbananas"
    
    "a #b\nc\nd $e f g"
    '("#" "$")
    "a\nc\nd"

    "a \n b \nc "
    '("#" "$")
    "a\n b\nc"

))



