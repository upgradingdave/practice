(ns purelyfun.380.core)

(comment
  (clojure.test/run-tests 'purelyfun.380.test)
  (defn rt []
    (let [tns 'purelyfun.380.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns)))
  )

(defn contraindications
  [meds pairs]
  ;; one loop thru the list of meds to make an map to facilitate quick lookups
  (let [medmap (into {} (map (fn [{:keys [name rxNorm]}] [rxNorm name]) meds))]
    ;; one loop thru the list of pairs. quickly check if each pair is
    ;; in list of meds
    (loop [pair (first pairs) pairs (rest pairs) result #{}]
      (if pair
        (let [[p1 p2] pair]
          (if (get medmap p1)
            (if (get medmap p2)
              (recur (first pairs) (rest pairs) (conj result pair))
              (recur (first pairs) (rest pairs) result))
            (recur (first pairs) (rest pairs) result)))
        (if (empty? result) nil result)))))
