(ns codewars.strip-comments.core
  (:require [clojure.string :as s])
  )

(defn strip-comment [symbol line]
  (if (or (nil? line) (s/starts-with? line symbol))
    nil
    (if-let [i (s/index-of line symbol)]
      (s/trimr (subs line 0 i))
      (s/trimr line))))

(defn strip-comments [text symbols]
  (let [lines (clojure.string/split text #"\R")
        f (apply comp (map #(partial strip-comment %1) symbols))]
    (apply str (interpose "\n" (map #(f %1) lines)))))

(comment
  (clojure.test/run-tests 'codewars.strip-comments.test)

  (defn rt []
    (let [tns 'codewars.strip-comments.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

