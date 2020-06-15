(ns codewars.sum-intervals.core
  (:require [clojure.string :as s])
  )

(defn overlap? [[l1 h1] [l2 h2]]
  (if (or (and (<= h2 h1) (>= h2 l1))
          (and (>= l2 l1) (<= l2 h1))
          (and (<= l2 l1) (>= h2 h1)))
    (let [l (if (< l2 l1) l2 l1)
          h (if (> h2 h1) h2 h1)]
      [l h])
    nil))

(defn combine-interval
  "check if any interval in intervals can be combined with interval i1"
  [i1 intervals]
  (loop [i2 (first intervals) remain (rest intervals) no-overlap []]
    (if i2
      (if-let [interval (overlap? i1 i2)]
        [interval (concat no-overlap remain)]
        (recur (first remain) (rest remain) (conj no-overlap i2)))
      [nil no-overlap])))

(defn combine-intervals
  "continue to call combine-interval until there's nothing else to combine"
  [interval intervals]
  (loop [i interval remain intervals]
    (let [[overlap remain] (combine-interval i remain)]
      (if overlap
        (recur overlap remain)
        (concat [i] remain)))))

(defn sum-intervals [intervals]
  (let [combined-intervals (reduce (fn [result i] (combine-intervals i result))
                                   (rest intervals)
                                   intervals)]
    (reduce + (map (fn [[l h]] (- h l)) combined-intervals))))

(comment
  (clojure.test/run-tests 'codewars.sum-intervals.test)

  (defn rt []
    (let [tns 'codewars.sum-intervals.test]
      (use tns :reload-all)
      (clojure.test/test-ns tns))
    )
  )

