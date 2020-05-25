(ns bootstrap.textbox
  (:require #?(:clj  [clojure.spec       :as s]
               :cljs [cljs.spec          :as s])
            [ud.form :as f]))

(s/def ::digitbox :ud.form/field)

(defn parse-digit [s]
  (let [v (get s ::value)]
    (cond 

      (and (string? v) (re-matches #"\d+" v)) 
      #?(:clj  (Integer/parseInt v)
         :cljs (js/parseInt v))

      (int? v)
      v)))

(s/fdef parse-digit
        :args (s/cat :digitbox ::digitbox))

(defn digitbox-in-range? [start end v]
  (if-let [i (parse-digit v)] (s/int-in-range? start end i)))

(defn digitbox-gt? 
  "True if digitbox 'a' is greater than 'b'"
  [a b]
  (let [a (parse-digit a) 
        b (parse-digit b)]
    (if (and a b)
      (> a b))))

(s/def ::digit? parse-digit)

