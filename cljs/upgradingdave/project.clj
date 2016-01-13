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
                 [reagent "0.5.1"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]
  
  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src"]
                        :figwheel { :devcards true } ;; <- note this
                        :compiler { :main       "upgradingdave.core"
                                    :asset-path "js/compiled/devcards_out"
                                    :output-to  "resources/public/js/compiled/upgradingdave_devcards.js"
                                    :output-dir "resources/public/js/compiled/devcards_out"
                                    :source-map-timestamp true }}

                       {:id "prod-generators"
                        :source-paths ["src"]
                        :compiler {:main       "upgradingdave.generators"
                                   :devcards true
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/generators.js"
                                   :optimizations :advanced}}

                       {:id "prod-bmr"
                        :source-paths ["src"]
                        :compiler {:main       "upgradingdave.bmr"
                                   :devcards   true
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/bmr.js"
                                   :optimizations :advanced}}
                       
                       {:id "prod-bmr-devcards"
                        :source-paths ["src"]
                        :compiler {:main       "upgradingdave.bmr-dev"
                                   :devcards   true
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/bmr-dev.js"
                                   :optimizations :advanced}}
]}

  :figwheel { :css-dirs ["resources/public/css"] })
