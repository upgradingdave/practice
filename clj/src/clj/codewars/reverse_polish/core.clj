(ns codewars.reverse-polish.core)

(defn calc [expr]
  (let [tokens (clojure.string/split expr #" ")]
    (loop [x (first tokens) xs (rest tokens) ns []]
      (if x
        (cond

          (= "+" x)
          (let [ns (conj (into [] (drop-last 2 ns))
                         (+ (last (butlast ns)) (last ns)))]
            (recur (first xs) (rest xs) ns))

          (= "*" x)
          (let [ns (conj (into [] (drop-last 2 ns))
                         (* (last (butlast ns)) (last ns)))]
            (recur (first xs) (rest xs) ns))

          (= "-" x)
          (let [ns (conj (into [] (drop-last 2 ns))
                         (- (last (butlast ns)) (last ns)))]
            (recur (first xs) (rest xs) ns))

          (= "/" x)
          (let [ns (conj (into [] (drop-last 2 ns))
                         (/ (last (butlast ns)) (last ns)))]
            (recur (first xs) (rest xs) ns))

          :else
          (recur (first xs) (rest xs) (conj ns (clojure.edn/read-string x)))
          
          )
        (or (first ns) 0)
        ))))


(comment
  (clojure.test/run-tests 'codewars.reverse-polish.test)

  (defn rt []
    (let [tns 'codewars.reverse-polish.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns)))
  )

