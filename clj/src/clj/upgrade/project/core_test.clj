(ns upgrade.project.core-test
  (:require [clojure.test :refer :all]
            [upgrade.project.core :refer :all]
            [upgrade.tools :as tools])
  (:import (java.io File)))

(comment
  (gen-js-challenge! "../out/" "My Challenge")
  (to-camel-case "My Function"))

(deftest test-gen-js
  (let [file-path "./target/js/codewars"]
    (tools/delete-dir! file-path)
    (let [code-dir (File. file-path)
          dir-created (.mkdir code-dir)
          src-dir (File. (str file-path "/src"))
          src-dir-created (.mkdir src-dir)
          test-dir (File. (str file-path "/test"))
          test-dir-created (.mkdir test-dir)
          ]
      (is dir-created)
      (is src-dir-created)
      (is test-dir-created)
      (let [challenge "My Challenge"
            file-name (to-camel-case challenge)
            main-file (File. (js-main-file-path file-path file-name))
            test-file (File. (js-main-file-path file-path file-name))
            ]
        (gen-js-challenge! file-path challenge)
        (is (.exists main-file))
        (is (.exists test-file)))
      )))
