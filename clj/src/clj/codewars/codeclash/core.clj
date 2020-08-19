(ns codewars.codeclash.core)

(defn d [msg] (binding [*out* *err*] (println msg) (flush)))
(defn o [m] (println m) (flush))

(defn contiguous [l]
  (loop [x (first l) xs (rest l) c [] r []]
    (if x
      (if (= x (first xs))
        (recur (first xs) (rest xs) (conj c x) r)
        (recur (first xs) (rest xs) [] (conj r (conj c x))))
      r
      )))

(defn -main [& args]
  (let [N (read) K (read)]
    (let [slices
          (loop [i N slices []]
            (if (> i 0)
              (let [A (read)]
                                        ; A: Area of each slice.
                (recur (dec i) (conj slices A)))
              slices))]
      (let [m (apply min slices)
            c (reverse (sort-by count (contiguous slices)))
            l (first (first c))]
        (o (str m " " l))
        ))
    ))

(comment
  (clojure.test/run-tests 'codewars.codeclash.test)

  (defn rt []
    (let [tns 'codewars.codeclash.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

