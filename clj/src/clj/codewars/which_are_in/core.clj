(ns codewars.which-are-in.core)

(defn in-array [a1 a2]
  (into []
        (into (sorted-set)
              (for [a a2
                    b a1
                    :when (clojure.string/includes? a b)] b))))

(comment
  (def a1 ["arp", "live", "strong"])
  (def a2 ["lively", "alive", "harp", "sharp", "armstrong"])
  (clojure.test/run-tests 'codewars.which-are-in.test)
  )

