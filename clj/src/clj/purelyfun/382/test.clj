(ns purelyfun.382.test
  (:require [clojure.test :refer :all]
            [purelyfun.382.core :refer :all]))

(deftest test-unique
  (is (= (uniques [1 2 3 4 5 6 1 2 3 5 6]) '(4)))
  (is (= (uniques [:a :b :c :c]) '(:a :b)))
  (is (= (uniques [1 2 3 1 2 3]) '()))
  )

(comment
  (clojure.test/run-tests 'purelyfun.382.test)
  (defn rt []
    (let [tns 'purelyfun.382.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

