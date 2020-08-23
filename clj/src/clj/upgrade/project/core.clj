(ns upgrade.project.core
  (:require [selmer.parser :as selm]
            [clojure.string :as str]))

(def opts {:tag-open \< :tag-close \>})

(defn to-camel-case [name]
  (let [[first & rest] (str/split name #"\s")
        result (str (.toLowerCase first) (apply str (map str/capitalize rest)))]
    result))

(defn js-main-file-path [file-path file-name]
  (str file-path "/src/" file-name ".js"))

(defn js-test-file-path [file-path file-name]
  (str file-path "/test/test_" file-name".js"))

(defn gen-js-challenge!
  "Quickly generate files for a new javascript challenge and return command to run tests"
  [file-path challenge-name]
  (let [
        fn-name (to-camel-case challenge-name)
        file-name fn-name
        context {:challenge-name challenge-name
                 :fn-name        fn-name
                 :file-name      file-name
                 }
        main (selm/render-file "JSChallenge/main.js" context opts)
        test (selm/render-file "JSChallenge/test.js" context opts)

        cmd (str file-path "/node_modules/.bin/mocha --grep \""
                 challenge-name
                 "\"  --watch ./test ./")
        ]
    (spit (js-main-file-path file-path file-name) main)
    (spit (js-test-file-path file-path file-name) test)

    cmd
    ))
