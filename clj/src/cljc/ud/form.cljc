(ns ud.form
  (:require #?(:clj  [clojure.spec       :as s]
               :cljs [cljs.spec          :as s])))

(s/def ::field
  (s/keys :req [:field/value]
          :opt [:field/label 
                :field/valid? 
                :field/error]))
