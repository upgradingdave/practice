(ns codewars.prime-decomp.core)

(defn factor->str [cnt p]
  (if (> cnt 1) (str "(" p "**" cnt ")") (str "(" p ")") ))

(defn factors->str [factors]
  (loop [x (first factors)
         xs (rest factors)
         prev (first factors)
         cnt 0
         result []]
    (if x
      (if (= prev x)
        (recur (first xs) (rest xs) x (inc cnt) result)
        (recur (first xs) (rest xs) x 1 (conj result (factor->str cnt prev))))
      (apply str (conj result (factor->str cnt prev))))))

(defn factor [n]
  (cond
    (= (mod n 2) 0)
    [2 (/ n 2)]

    (= (mod n 3) 0)
    [3 (/ n 3)]

    :else
    (loop [try 5 i 2]
      (if (> (* try try) n)
        [n 1]
        (if (= (mod n try) 0)
          [try (/ n try)]
          (recur (+ try i) (- 6 i)))))))

(defn factorize [n]
  (let [[f1 f2] (factor n)]
    (if (= f2 1)
      f1
      [(factorize f1)
       (factorize f2)])))

(defn prime-factors [n]
  (let [factors (factorize n)
        factors-tree (tree-seq vector? identity factors)]
    (factors->str (sort (filter (complement (partial vector?)) factors-tree)))))

(comment
  (clojure.test/run-tests 'codewars.prime-decomp.test)

  (defn rt []
    (let [tns 'codewars.prime-decomp.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

