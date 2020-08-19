(ns purelyfun.382.core)

(defn uniques
  [values]
  (map first (filter #(= (count %) 1) (vals (group-by identity values)))))
