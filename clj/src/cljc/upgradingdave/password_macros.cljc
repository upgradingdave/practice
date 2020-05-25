(ns upgradingdave.password-macros
  (:require #?(:clj  [clojure.spec :as s]
               :cljs [cljs.spec    :as s])))

(defmacro and'
  [pred-forms]
  `(s/and-spec-impl '~pred-forms ~pred-forms nil))

