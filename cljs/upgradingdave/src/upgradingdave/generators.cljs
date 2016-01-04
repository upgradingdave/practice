(ns upgradingdave.generators
  (:require
   [devcards.core :as dc]
   [sablono.core :as sab :include-macros true]
   [clojure.test.check.generators :as gen])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest defcard-doc]]))

(defn char-upper []
  (gen/fmap char
            (gen/one-of [(gen/choose 65 90)])))

(defn char-lower []
  (gen/fmap char
            (gen/one-of [(gen/choose 97 122)])))

(defn char-special []
  (gen/one-of [(gen/elements [\! \$ \% \^ \&])]))

(defn gen-pwd [& [{:keys [len] :as opts}]]
  (let [len (or len 15)]
    (apply str
           (gen/sample 
            (gen/frequency [[25 (char-upper)]
                            [25 (char-lower)]
                            [25 (char-special)]
                            [25 gen/s-pos-int]])
            len))))

(def pwd-len (atom 15))

(defn password-generator []
  (sab/html [:form {:class "form-horizontal"}
             [:div {:class "form-group"}
              [:label {:class "col-sm-3 control-label"} "Password Length"]
              [:div {:class "col-sm-9"}
               [:input {:class "form-control" 
                        :type "text" :value @pwd-len
                        :on-change #(let [new-val (-> % .-target .-value)]
                                      (reset! pwd-len 
                                              (js/parseInt new-val)))}]]]
             [:div {:class "form-group"}
              [:div {:class "col-sm-offset-3 col-sm-9"}
               [:button {:class "btn btn-default"
                         :on-click 
                         #(let [el (.getElementById js/document "rnd-pwd")]
                            (set! (. el -innerHTML) (gen-pwd {:len @pwd-len})))}
                "Generate Random Password"]]]
             [:div {:class "form-group"}
              [:h1 {:class "col-sm-offset-3 col-sm-9" 
                    :id "rnd-pwd"} (gen-pwd {:len @pwd-len})]]]))

(defcard 
  "### Random Password Generator
   A [Random Password Generator](/blog/posts/2015-09-15-generate-data-clojure.html) using `test.check.generators` written in [clojurescript using devcards](/blog/posts/2016-01-04-random-generators-in-cljs.html)"
  (password-generator))

(defcard-doc 
  "### How the Form is Built

  The following function uses `hiccup` syntax to define a html form with
  a `Password Length` input text field and a `Generate Password` Button.

  The `Password Length` input text field uses the `on-change` event to
  update a clojurescript atom named `pwd-len`.

  The `Generate Password` button reads from the `pwd-len` atom to know
  what length to pass to the `gen-pwd` function. It writes the results
  into the `h1` element defined with the id `rnd-pwd`.

  By default, devcards makes
  the [sablano](https://github.com/r0man/sablono) library available.
  Sablano makes it easy to render hiccup as a react component.
"
  (dc/mkdn-pprint-source password-generator))

(defcard-doc 
  "### How the Password Generator Works

  Here are the functions used to generate a random password
"
  (dc/mkdn-pprint-source char-upper)
  (dc/mkdn-pprint-source char-lower)
  (dc/mkdn-pprint-source char-special)
  (dc/mkdn-pprint-source gen-pwd))

;; (defn main []
;;   ;; conditionally start the app based on wether the #main-app-area
;;   ;; node is on the page
;;   (if-let [node (.getElementById js/document "main-app-area")]
;;     (js/React.render (password-generator) node)))

;; (main)

(devcards.core/start-devcard-ui!)

