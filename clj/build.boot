(merge-env!
 :dependencies '[[org.clojure/clojure     "1.9.0-alpha10"]
                 [org.clojure/test.check  "0.9.0" :scope "test"]
                 [upgradingdave/boot-dave "0.1.1" :scope "test"]
                 ;;[adzerk/bootlaces        "0.1.13" :scope "test"]
                 ])

(require '[upgradingdave.boot-cider  :refer [cider]]
         '[upgradingdave.boot-expect :refer [expect]])

(defn practice-env! []  
  (merge-env!
   :source-paths #{"src/clj" "src/cljc" "test/clj" "test/cljc"}   
   :dependencies '[[clj-time "0.12.0"]]))

(deftask dev
  "Start repl"
  []
  (practice-env!)
  (comp
   (cider)
   (repl)
   ;;(watch)
   ;;(expect)
   ))

(deftask go 
  "Run practice exercises once."
  []
  (practice-env!)
  (comp
   (expect)))

(deftask build
  "Build jar and install to maven repo"
  []
  ;;(merge-env! :dependencies '[[adzerk/bootlaces "X.Y.Z" :scope "test"]])
  ;;(require '[adzerk.bootlaces :refer :all])
  ;;(bootlaces! +version+)
  (merge-env!
   :resource-paths #{"src/cljc"})
  (comp
   (pom :project 'upgradingdave/password
        :version "0.1.0")
   (jar)
   (install)))


