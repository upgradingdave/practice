(ns upgradingdave.practice.form
  (:require [clojure.spec     :as s]
            [clojure.spec.gen :as gen]))

(s/def ::digitbox 
  (s/keys :req [::value]
          :opt [::valid ::error]))

(defn parse-digit [s]
  (let [v (get s ::value)]
    (cond 
      (and (string? v) (re-matches #"\d+" v)) 
      (Integer/parseInt s)

      (int? v)
      v)))

(s/fdef parse-digit
        :args (s/cat :digitbox ::digitbox))

(defn digitbox-in-range? [start end v]
  (if-let [i (parse-int v)] (s/int-in-range? start end i)))

(defn digitbox-gt? 
  "True if digitbox 'a' is greater than 'b'"
  [a b]
  (let [a (parse-int a) 
        b (parse-int b)]
    (if (and a b)
      (> a b))))

;; (s/def ::digit     (parse-int (get % :value)))
;; (s/def ::min-range (digit-range 0 10))
;; (s/def ::max-range (digit-range 10 30))
;; (s/def ::min       (s/and ::digit ::min-range))
;; (s/def ::max       (s/and ::digit ::max-range))

;; (s/def ::min-max min-gt-max)

;; (s/def ::my-form (s/and 
;;                   ::min-max
;;                   (s/keys :req [::min ::max])))
