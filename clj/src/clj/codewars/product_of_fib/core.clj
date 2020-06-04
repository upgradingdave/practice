(ns codewars.product-of-fib.core)

(defn product-fib [prod]
  (loop [fibn 0N fibn1 1N]
    (if (>= (* fibn fibn1) prod)
      [fibn1 fibn (= (* fibn fibn1) prod)]
      (recur (+ fibn fibn1) fibn))
    ))

(comment
  (defn rt []
    (let [tns 'codewars.product-of-fib.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns)))
  )

