(ns upgradingdave.bmr
  (:require [reagent.core :as r]))

(defn kg->lb [kg]
  (* kg 2.20462))

(defn lb->kg [lb]
  (* lb 0.453592))

(defn to-cm [ft in]
  (* (+ (* ft 12) in) 2.54))

(defn calc-bmr [{:keys [gender weight height age]}]
  (let [weight-unit (:unit weight)
        weight-val  (:value weight)
        cm          (:cm height)
        kg          (if (= weight-unit "kgs")
                      weight-val
                      (lb->kg weight-val))
        bmr          (+  (* 10 kg) (* 6.25 cm) (- (* 5 age)))]
    (if (= gender "f")
      (- bmr 161)
      (+ bmr 5))))

(defn gender-select [data]
  [:div {:class "form-group"}
   [:label {:class "col-sm-2 control-label"} "Gender"]
   [:div {:class "col-sm-2"}
    [:select {:class "form-control"
              :value (get-in @data [:gender])
              :on-change #(let [new-val (-> % .-target .-value)]
                            (swap! data assoc-in [:gender] new-val))}
     [:option {:value "f" :key "f"} "Female"]
     [:option {:value "m" :key "m"} "Male"]]]])

(defn weight-select [data]
  [:select {:class "form-control"
            :value (get-in @data [:weight :unit])
            :on-change #(let [new-val (-> % .-target .-value)]
                          (swap! data assoc-in [:weight :unit] new-val))}
   [:option {:value "lbs" :key "lbs"} "Pounds"]
   [:option {:value "kgs" :key "kgs"} "Kilograms"]])

(defn weight [data]
  [:div {:class "form-group"}
   [:label {:class "col-sm-2 control-label"} "Weight"]
   [:div {:class "col-sm-2"}
    [:input {:class "form-control" 
             :type "text" :value (get-in @data [:weight :value])
             :on-change #(let [new-val (-> % .-target .-value)]
                           (swap! data assoc-in [:weight :value]
                                  (if (or (empty? new-val) (js/isNaN new-val))
                                    nil
                                    (js/parseInt new-val))))}]]

   [:div {:class "col-sm-2"}
    [weight-select data]]])

(defn age [data]
  [:div {:class "form-group"}
   [:label {:class "col-sm-2 control-label"} "Age"]

   [:div {:class "col-sm-2"}
    [:select {:class "form-control"
              :value (get-in @data [:age])
              :on-change #(let [new-val (-> % .-target .-value)]
                            (swap! data assoc-in [:age]
                                   (js/parseInt new-val)))}
     (map #(vector :option {:value (str %) :key (str %)} (str %)) 
          (range 1 100))]]])

(defn feet-select [data]
  [:select {:class "form-control"
            :value (get-in @data [:height :ft])
            :on-change #(let [new-val (-> % .-target .-value)]
                          (let [ft (js/parseInt new-val)
                                in (get-in @data [:height :in])
                                cm (get-in @data [:height :cm])]
                            (swap! data assoc-in [:height :cm]
                                   (to-cm ft in))
                            (swap! data assoc-in [:height :ft]
                                   ft)))}
   (map #(vector :option {:value (str %) :key (str %)} (str % " feet")) 
        (range 1 8))])

(defn inch-select [data]
  [:select {:class "form-control"
            :value (get-in @data [:height :in])
            :on-change #(let [new-val (-> % .-target .-value)]
                          (let [ft (get-in @data [:height :ft])
                                in (js/parseInt new-val)
                                cm (get-in @data [:height :cm])]
                            (swap! data assoc-in [:height :cm]
                                   (to-cm ft in))
                            (swap! data assoc-in [:height :in]
                                   in)))}
   (map #(vector :option {:value (str %) :key (str %)} (str % " inches")) 
        (range 1 13))])

(defn height [data]
  [:div {:class "form-group"}
   [:label {:class "col-sm-2 control-label"} "Height"]
   [:div {:class "col-sm-2"} (feet-select data)]
   [:div {:class "col-sm-2"} (inch-select data)]])

(defn bmr-calculator [data]
  [:form {:class "form-horizontal"}

   [age data]

   [gender-select data]

   [weight data]

   [height data]

   [:div {:class "form-group"}
    [:div {:class "col-sm-offset-1 col-sm-11 alert alert-success" 
           :id "bmr-result"} (str "Your BMR: " (calc-bmr @data))]]])

(def data (r/atom {:gender "m"
                   :weight {:value 220 :unit "lbs"} 
                   :height {:ft 6 :in 2 :cm (to-cm 6 2)}
                   :age 36}))

(defn main []
  (if-let [node (.getElementById js/document "bmr-calc")]
    (r/render-component [bmr-calculator data] node)))

(main)
