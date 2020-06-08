(ns codewars.pangram.core)

(defn pangram? [s]
  (let [uppers (into {} (map (fn [c] [(char c) 0]) (range 65 91)))]
    (loop [c (first s) xs (rest s) uppers uppers]
      (if c
        (let [upper (Character/toUpperCase c)]
          (if (get uppers upper)
            (recur (first xs) (rest xs) (update uppers upper inc))
            (recur (first xs) (rest xs) uppers)))
        (empty? (filter (fn [[_ cnt]] (= cnt 0)) uppers))
        ))))

(comment
  (clojure.test/run-tests 'codewars.reverse-polish.test)

  (defn rt []
    (let [tns 'codewars.pangram.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns)))
  )

