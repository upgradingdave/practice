(ns codewars.roman-numerals.core)

(def numerals {:M 1000
               :CM 900
               :D 500
               :CD 400
               :C 100
               :XC 90
               :L 50
               :XL 40
               :X 10
               :IX 9
               :V 5
               :IV 4
               :I 1})

(defn biggest-one
  ([] nil)
  ([x] x)
  ([[[n v] s] [[n2 v2] s2]]
   (if (> v v2) [[n v] s] [[n2 v2] s2])))

(defn parse-numeral [s numerals]
  (reduce biggest-one 
          (for [[k v] numerals
                :when (and s (clojure.string/starts-with? s (name k)))]
            [[k v] (.substring s (.length (name k)))])))

(defn translate-roman-numerals [s]
  (loop [[[k v] s] (parse-numeral s numerals)
         result 0]
    (if (empty? s)
      (+ result v)
      (recur (parse-numeral s numerals)
             (+ result v)))))

(comment
  (clojure.test/run-tests 'codewars.roman-numerals.test)
  )

;; This is the top submitted solution ... 
(defn translate-roman-numerals2 [roman]
  (->> (partition-by identity roman)
       (map (partial map {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}))
       (map (partial reduce +))
       (reverse)
       (reduce #((if (< %1 %2) + -) %1 %2))
  ))
