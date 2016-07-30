(ns upgradingdave.password-test
  (:require [expectations              :refer :all]
            [upgradingdave.common-test :refer :all]
            [upgradingdave.password    :refer :all]))

(expect empty? (test-check 'upgradingdave.password/create-password))



