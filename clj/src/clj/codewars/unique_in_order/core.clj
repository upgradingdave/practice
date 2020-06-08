(ns codewars.unique-in-order.core)

(defn unique-in-order [input]
  (let [[result _]
        (reduce (fn [[acc p] n] [(if (= n p) acc (conj acc n)) n])
                [[] nil]
                input)]
    result))

(comment
  (clojure.test/run-tests 'codewars.reverse-polish.test)

  (defn rt []
    (let [tns 'codewars.unique-in-order.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

