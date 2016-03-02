(def compiled-js-dir "resources/public/js/compiled")

(defn cljs-conf [ns]
  {:id (str "prod-" ns)
   :source-paths ["src"]
   :compiler {:main       (str "upgradingdave." ns)
              :asset-path "js/compiled/out"
              :output-to  (str compiled-js-dir "/" ns ".js")
              :optimizations :advanced}})

(defn cljs-dev-conf [ns]
  {:id (str "prod-" ns "-devcards")
   :source-paths ["src"]
   :compiler {:main       (str "upgradingdave." ns "-dev")
              :devcards   true
              :asset-path "js/compiled/out"
              :output-to  (str compiled-js-dir "/" ns "-dev.js")
              :optimizations :advanced}})

(defproject upgradingdave "0.1.0-SNAPSHOT"
  :description "Clojurescript Practice"
  :url "http://upgradingdave.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.219"]

                 [devcards "0.2.1"]
                 [org.clojure/test.check "0.9.0"]
                 [org.clojure/tools.reader "0.10.0-SNAPSHOT"]
                 [com.cognitect/transit-clj "0.8.285"]
                 [reagent "0.5.1"]

                 [cljsjs/exif "2.1.1-1"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]
  
  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src"]
                        :figwheel {:devcards true } ;; <- note this
                        :compiler {:main       "upgradingdave.core"
                                   :asset-path "js/compiled/devcards_out"
                                   :output-to ~(str 
                                                compiled-js-dir 
                                                "/upgradingdave_devcards.js")
                                   :output-dir ~(str 
                                                 compiled-js-dir 
                                                 "/devcards_out")
                                   :source-map-timestamp true }}

                       ~(cljs-conf "pwd")
                       ~(cljs-dev-conf "pwd")

                       ~(cljs-conf "bmr")
                       ~(cljs-dev-conf "bmr")

                       ~(cljs-conf "pcf")
                       ~(cljs-dev-conf "pcf")

                       ~(cljs-conf "exif")
                       ~(cljs-dev-conf "exif")

                       ~(cljs-conf "lattice")
                       ~(cljs-dev-conf "lattice")

                       ]}

  :figwheel { :css-dirs ["resources/public/css"] })
