(ns codewars.split-str.core)

(defn solution
  [s]
  (map #(apply str %) (partition 2 2 "_" s)))

(comment
  (clojure.test/run-tests 'codewars.split-str.test)
)


