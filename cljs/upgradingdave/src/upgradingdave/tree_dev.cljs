(ns upgradingdave.tree-dev
  (:require
   [devcards.core        :as dc]
   [reagent.core         :as r]
   [upgradingdave.tree    :as tree]
   [upgradingdave.tree2   :as tree2])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest defcard-doc]]
   [cljs.test            :refer [is testing]]))

(def data (r/atom {:filesystem {:label "/"
                                :subdirs [{:label "Users"
                                           :subdirs [{:label "dave"}]}
                                          {:label "etc"
                                           :subdirs [{:label "apache"}]}]}
                   :local {:tree {:root {:ref [:filesystem] 
                                         :label "/"
                                         :leaf? false
                                         :expanded false}}}}))

(def css-transition-group
  (r/adapt-react-class js/React.addons.CSSTransitionGroup))

(defcard 
  "### Bootstrap Clojurescript Tree"
  (dc/reagent 
   (fn [data _] [tree/tree-widget data]))
  data
  {:inspect-data true})

(def fade-in-time 1)
(def fade-out-time 1)

(def style
  (str
   ".tree2 li {
  width: 150px;
  list-style: none;
  height: 2em;
  line-height: 2em;
  padding: 0 0.5em;
  margin: 0;
  }

.tree2 li::before, .tree li::after {
    content:'';
    left:-20px;
    position:absolute;
    right:auto
}

  .tree2 li::before {
  before-left:1px solid #999;
  bottom: 50px;
  height: 100%;
  top: 0;
  width:1px;
  }

  .foo-enter {
  opacity: 0.01;
  max-height: 0px;
  }

  .foo-enter-active {
  opacity: 1;
  max-height: 999px;
  -moz-transition: all " fade-in-time "s ease-in-out;
  -webkit-transition: all " fade-in-time "s ease-in-out;
  -ms-transition: all " fade-in-time "s ease-in-out;
  -o-transition: all " fade-in-time "s ease-in-out;
  transition: all " fade-in-time "s ease-in-out;
  }

  .foo-leave {
  opacity: 1;
  }

  .foo-leave-active {
  opacity: 0.01;
  transition: opacity 0.25s ease-in-out;
  -moz-transition: opacity 0.25s ease-in-out;
  -webkit-transition: opacity 0.25s ease-in-out;
  }
"))

(def app-state
  (r/atom {:items [1 2 3]}))

(defn add-item []
  (let [items (:items @app-state)]
    (swap! app-state assoc :items 
           (conj items (inc (count (:items @app-state)))))))

(defn delete-item []
  (let [items (:items @app-state)]
    (swap! app-state assoc :items (vec (butlast items)))))

(defcard 
  "### CSS3 fade in/out"
  (dc/reagent 
   (fn [data _] 
     [:div
      [:div (str "Total list items to date:  " (count (:items @app-state)))]
      [:button {:on-click #(add-item)} "add"]
      [:button {:on-click #(delete-item)} "delete"]
      [:style style]
      [:ul {:class "tree2"}
       [css-transition-group {:transition-name "foo"
                              :transition-enter-timeout 
                              (* fade-in-time 1000)
                              :transition-leave-timeout 100}
        (map-indexed 
         (fn [i x]
           ^{:key i} [:li [:span {:class "glyphicon glyphicon-folder-close"}] 
                      (str " List Item " x)])
         (:items @app-state))]]
      ]))
  app-state
  {:inspect-data true})

(deftest file-api-supported
  (testing "sanity"
    (is (= true true))))

(dc/start-devcard-ui!)
