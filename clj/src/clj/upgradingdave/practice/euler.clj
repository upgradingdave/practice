(ns upgradingdave.practice.euler)

(defn divisible?
  "Return true if x is divisible by y"
  [x y]
  (= (mod x y) 0))

(defn divisible-fn
  "Return a function that returns true if x is divisible by y"
  [y]
  (fn [x] (divisible? x y)))

(defn not-divisible-fn
  "Return a function that returns false if x is divisible by y"
  [y]
  (fn [x] (not (divisible? x y))))

(defn problem1
  "If we list all the natural numbers below 10 that are multiples of 3
  or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find
  the sum of all the multiples of 3 or 5 below 1000."
  [ns]
  (letfn [(by3or5? [n]  (or (divisible? n 3) (divisible? n 5)))]
    (transduce (filter by3or5?) + ns)))

(defn fibonacci 
  ([] (fibonacci 0 1))
  ([x y]
   (lazy-seq (cons (+ x y) (fibonacci y (+ x y))))))

(defn problem2 [maximum]
  "Each new term in the Fibonacci sequence is generated by adding the
  previous two terms. By starting with 1 and 2, the first 10 terms
  will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ... By considering the
  terms in the Fibonacci sequence whose values do not exceed four
  million, find the sum of the even-valued terms."
  (reduce + (filter even? (take-while #(< % maximum) (fibonacci)))))

(defn divisibles 
  "Infinite list of all numbers divisible by n"
  [n]
  (filter #(divisible? % n) (range)))

(defn not-divisibles 
  "Infinite list of all numbers not divisible by n starting at x"
  [x n]
  (filter #(not (divisible? % n)) (map #(+ x %) (range))))

(defn range-from
  "Just like `range` except can pass the start rather than end"
  [start]
  (map #(+ start %) (range)))

(defn eratosthenes
  "sieve of erotosthenes"
  ([] (eratosthenes [2] [(not-divisible-fn 2)]))
  ([primes filters] 
   (let [next       (last primes)
         filters    (conj filters (not-divisible-fn next))
         f          (apply juxt filters)
         next-prime (first 
                     (filter #(every? identity (f %)) 
                             (range-from (inc next))))]
     (lazy-seq (cons next
                     (eratosthenes (conj primes next-prime) filters))))))

(defn primes 
  "Infinite sequence of prime numbers"
  [] 
  (eratosthenes))

(defn factors 
  "find all factors of n"
  [n]
  (loop [acc [] x 1]
    (if (>= x n)
      acc
      (if (divisible? n x)
        (recur (conj acc x) (inc x))
        (recur acc (inc x))))))

(defn prime-factors [n]
  "Find prime factors of n. (This probably won't complete for large
  values)"
  (let [fs (factors n)
        ps (take-while #(< % (inc (last fs))) (primes))]
    (filter #(some #{%} ps) fs)))

(defn prime? [n]
  (loop [x n]
    (if (<= x 1)
      true
      (if (and (not (= x n))
               (divisible? n x))
        false
        (recur (dec x))))))

(defn problem3 
  "Find the largest prime factor of n"
  [n]
  (loop [x n p 2]
    (if (= x p)
      x
      (if (divisible? x p)
        (recur (/ x p) 2)
        (recur x (inc p))))))

