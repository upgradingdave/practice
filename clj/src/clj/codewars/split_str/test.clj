(ns codewars.split-str.test
  (:require [clojure.test :refer :all]
            [codewars.split-str.core :refer :all]))
            
(def ^:private sample-tests
  [{:tested-str "cdabefg"
    :result ["cd" "ab" "ef" "g_"]}
   {:tested-str "cdabefgh"
    :result ["cd" "ab" "ef" "gh"]}
   {:tested-str "abcd"
    :result ["ab" "cd"]}])
    
(doseq [{:keys [tested-str result]} sample-tests]
  (eval
    `(deftest ~(symbol (str "sample-test-" tested-str))
      (is (~'= ~result (~'solution ~tested-str))))))
