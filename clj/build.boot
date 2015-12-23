(merge-env!
 :source-paths #{"src/clj" "test/clj"}
 :dependencies '[[org.clojure/clojure     "1.7.0"              ]
                 [upgradingdave/boot-dave "0.1.1" :scope "test"]]
)

(require '[upgradingdave.boot-cider  :refer [cider]]
         
         '[upgradingdave.boot-expect :refer [expect]])

(deftask dev
  "Continously run practice exercises."
  []
  (comp
   (cider)
   (repl)
   (watch)
   (expect)))

(deftask go 
  "Run practice exercises once."
  []
  (comp
   (expect)))
