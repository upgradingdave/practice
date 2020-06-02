(ns purelyfun.380.test
  (:require [clojure.test :refer :all]
            [purelyfun.380.core :refer :all]))

(deftest test-contraindictions
  (let [pairs [["nr913ng" "blyth"]
               ["masbut"  "87f2h139049f"]
               ["nr913ng" "j1j81f0"]
               ["blyth" "welb"]
               ["masbut"  "welb"]]]

    (let [meds [{:name "Blythenal"
                 :rxNorm "blyth"}
                {:name "Masbutol"
                 :rxNorm "masbut"}
                {:name "Welbutril"
                 :rxNorm "welb"}]]
      (is (= #{["blyth" "welb"] ["masbut" "welb"]}
             (contraindications meds pairs))))
    
    (let [meds [{:name "Blythenal"
                 :rxNorm "blyth"}]]
      (is (= nil (contraindications meds pairs))))))
