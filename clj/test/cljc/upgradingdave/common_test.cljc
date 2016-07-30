(ns upgradingdave.common-test
  (:require [clojure.spec.test :as stest]))

(defn test-check [sym-or-syms]
  (filter :failure (map stest/abbrev-result (stest/check sym-or-syms))))
