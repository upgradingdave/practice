(ns upgradingdave.password-test
  (:require [expectations              :refer :all]
            [upgradingdave.common-test :refer :all]
            [upgradingdave.password    :refer :all]
            [clojure.spec :as s]))

;; create a password spec (used for validating passwords)
(def pwd-spec1 (create-password-spec pwd-conf-example))

;; create a function that will create passwors based on spec
(def create-pwd1 (create-password-fn pwd-conf-example))

(s/fdef create-pwd1
        :args empty?
        :ret pwd-spec1)

(expect empty? (test-check 'upgradingdave.password/create-pwd1))


