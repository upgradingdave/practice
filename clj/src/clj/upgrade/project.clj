(ns upgrade.project
  (:require [selmer.parser :as selm]
            [clojure.string :as str]))

(def opts {:tag-open \< :tag-close \>})

(defn to-camel-case [name]
  (let [[first & rest] (str/split name #"\s")
        result (str (.toLowerCase first) (apply str (map str/capitalize rest)))]
    result))

(defn gen-js-challenge!
  "Quickly generate files for a new javascript challenge"
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

        cmd (str "./node_modules/.bin/mocha --grep \"" challenge-name
                 "\"  --watch ./test ./")
       ]
    (spit (str file-path "/src/" (:file-name context) ".js") main)
    (spit (str file-path "/test/test_" (:file-name context) ".js") test)

    (println cmd)

 ))

(comment
  (gen-js-challenge! "../js/codewars" "My Challenge")
  (to-camel-case "My Function")
  )



