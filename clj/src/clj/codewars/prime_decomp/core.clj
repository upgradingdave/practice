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

(defn prime? [c primes]
  (loop [p (first primes) primes (rest primes)]
    (if p
      (if (= (mod c p) 0)
        false
        (recur (first primes) (rest primes)))
      true)))

(defn factor [n]
  (loop [c (dec n)]
    (if (< (* c c) n)
      [n 1]
      (if (= (mod n c) 0)
        [c (/ n c)]
        (recur (dec c))))))

(defn factorize [n]
  (let [[f1 f2] (factor n)]
    (if (= f2 1)
      f1
      [(factorize f1)
       (factorize f2)])))

(defn next-prime [primes]
  (if (empty? primes) 2
      (loop [c (inc (last primes))]
        (if c
          (if (prime? c primes)
            c
            (recur (inc c)))))))

(defn prime-factors [n]
  (loop [p 2 ps [] n n factors []]
    (if (> n 1)
      (if (= (mod n p) 0)
        (recur p ps (/ n p) (conj factors p))
        (recur (next-prime (conj ps p)) (conj ps p) n factors))
      (factors->str factors))))

(comment
  (clojure.test/run-tests 'codewars.prime-decomp.test)

  (defn rt []
    (let [tns 'codewars.prime-decomp.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

