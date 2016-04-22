(ns upgradingdave.resize
  (:require [reagent.core :as r]
            [goog.date :as dt]
            [upgradingdave.csv   :as csv]
            [upgradingdave.html5 :as html5]))

(defn get-file-name [file]
  (if file
    (js/escape (.-name file))))

(defn image? 
  "Determine whether a js File object represents an image"
  [f]
  (let [ftype (.-type f)] 
    (re-matches #"image/jpeg" ftype)))

(defn process-file
  ""
  [img data])

(defn read-file
  "Given a js File Object, parse the contents as ics. The resulting
  map is put into the data atom"
  [file data]
  (let [reader (doto (js/FileReader.)
                 (aset "onload" #(process-file (-> % .-target .-result)
                                              data)))]
    (.readAsText reader file)))

(defn handle-file-select [e data]
  (let [files (array-seq (-> e .-target .-files))
        curr (first (filter image? files))]
    (swap! data (fn [old]
                  (-> old
                      (assoc-in [:files]    files)
                      (assoc-in [:results]  nil)
                      (assoc-in [:loading]  true)
                      (assoc-in [:selected] (get-file-name curr)))))
    ;;(read-file curr data)
    ))


(defn file-select [data]
  [:span {:class "btn btn-default btn-file"
          :style {:position "relative"
                  :overflow "hidden"}}
   "Choose a Photo"
   [:input {:type "file" :id "files" :multiple true 
            :style {:position "absolute"
                    :top "0"
                    :right "0"
                    :min-width "100%"
                    :min-height "100%"
                    :font-size "100px"
                    :text-align "right"
                    :filter "alpha(opacity=0)"
                    :opacity "0"
                    :outline "none"
                    :background "white"
                    :cursor "inherit"
                    :display "block"}
            :on-change #(handle-file-select % data)}]])

(defn limit-width [{:keys [width height] :as photo} & [max-width]]
  (let [max-width (or max-width 445)]
    (let [bigger     (> width max-width)
          new-width  (if bigger max-width width)
          new-height (if bigger (* new-width (/ height width)))]
      (-> photo
          (assoc :display-height new-height)
          (assoc :orig-display-height new-height)
          (assoc :display-width  new-width)
          (assoc :orig-display-width  new-width)))))

(defn create-image [data]
  (doto (js/Image.)
    (aset "onload" 
          #(this-as t 
                    (swap! data assoc-in [:photo] 
                           (-> {:orig-width  (aget t "width")
                                :width       (aget t "width")
                                :orig-height (aget t "height")
                                :height      (aget t "height")
                                :src         (aget t "src")}
                               (limit-width 700)))))
    (aset "src" "/i/20151117_124052.jpg")))

(defn resize-photo! [data height' width' display-height' display-width']
  (swap! data (fn [old]
                (-> old
                    (assoc-in [:photo :height] height' )
                    (assoc-in [:photo :width]  width')
                    (assoc-in [:photo :display-height] display-height')
                    (assoc-in [:photo :display-width]  display-width')))))

(defn resize-by-percent! [data percent]
  (let [photo (:photo @data)
        {:keys [orig-height orig-width 
                orig-display-height 
                orig-display-width]} photo
        r       (/ percent 100)
        height' (* r orig-height)
        width'  (* r orig-width)
        display-height' (* r orig-display-height)
        display-width'  (* r orig-display-width)]
    (resize-photo! data height' width' display-height' display-width')))

(defn resize-by-width! [data width]
  (let [photo (:photo @data)
        {:keys [orig-height orig-width 
                orig-display-height 
                orig-display-width]} photo
        width (if (> width orig-width) orig-width width)
        width (if (< width 0) 0 width)
        r     (/ width orig-width)
        percent (* 100 r)
        ratio (/ orig-height orig-width)
        width' width
        height' (* width ratio)
        display-height' (* r orig-display-height)
        display-width'  (* r orig-display-width)]
    (swap! data assoc-in [:zoom-slider :value] percent)
    (resize-photo! data height' width' display-height' display-width')
    ))

(defn resize-by-height! [data height]
  (let [photo (:photo @data)
        {:keys [orig-height orig-width 
                orig-display-height 
                orig-display-width]} photo
        height (if (> height orig-height) orig-height height)
        height (if (< height 0) 0 height)
        r     (/ height orig-height)
        percent (* 100 r)
        ratio (/ orig-width orig-height)
        height' height
        width' (* height ratio)
        display-height' (* r orig-display-height)
        display-width'  (* r orig-display-width)]
    (swap! data assoc-in [:zoom-slider :value] percent)
    (resize-photo! data height' width' display-height' display-width')
    ))

(defn slider-control [data]
  (let [v (or (get-in @data [:zoom-slider :value]) 100)]
    [:div {:class "form-group"}
     [:label (str "Resize - " (js/Math.round  
                               (or
                                (get-in @data [:zoom-slider :value]) 100)) "%")]
     [:input {:type "range" :name "zoom" :id "zoom"
              :min "0" :max "100"
              :value v
              :on-change #(let [new-val (-> % .-target .-value)]
                            (swap! data assoc-in [:zoom-slider :value] new-val)
                            (resize-by-percent! data new-val)
                            )}]]))

(defn photo-editor [data]
  (if (:loading @data)
    [:div {:class "alert alert-success"} "Working ..."]
    
    (if-let [photo (:photo @data)]
      [:div {:id "image-editor"}
       [:form
        [:div {:class "form-group"}
         [:label "Photo Width"]
         [:input {:type "text" :class "form-control"
                  :value (:width photo)
                  :on-change 
                  #(let [new-val (-> % .-target .-value)]
                     (if (empty? new-val) 
                       (swap! data assoc-in [:photo :width] nil)
                       (when (not (js/isNaN new-val))
                         (let [width (js/parseInt new-val)]
                           (resize-by-width! data width)))))}]]
        [:div {:class "form-group"}
         [:label "Photo Height"]
         [:input {:type "text" :class "form-control"
                  :value (:height photo)
                  :on-change
                  #(let [new-val (-> % .-target .-value)]
                     (if (empty? new-val) 
                       (swap! data assoc-in [:photo :height] nil)
                       (when (not (js/isNaN new-val))
                         (let [height (js/parseInt new-val)]
                           (resize-by-height! data height)))))}]]
        [slider-control data]
        [:img {:class  "thumbnail" 
               :src    (:src photo) 
               :width  (:display-width photo) 
               :height (:display-height photo)}]]]
      )))

(defn not-supported []
  [:div {:class "alert alert-danger"}
   (str "Oops, sorry but this Image Resize Tool won't work with
   your browser. This will only work with browsers that support the
   HTML5 File API. Maybe try using ")
   [:a {:href "https://www.google.com/chrome/browser"} 
    "Google Chrome instead."]])

(defn file-chooser [images data]
  [:ul {:class "nav nav-pills nav-stacked"}
   (let [files  images
         active (:selected @data)]
     (for [file files]
       (let [fname (get-file-name file)]
         [:li {:key fname
               :class (if (= active fname) "active")}
          [:a {:href "#" 
               :id fname
               :on-click 
               #(let [chosen (-> % .-target .-id)
                      curr   (first (filter 
                                     (fn [i] (= (get-file-name i) chosen)) 
                                     images))]
                  (swap! data (fn [old]
                                (-> old
                                    (assoc-in [:results] nil)
                                    (assoc-in [:loading] true)
                                    (assoc-in [:selected] chosen))))

                  (read-file curr data)
                  (.preventDefault %))}
           fname]])))])

(defn resize-tool [data & [{supported? :html5-file-api-supported?}]]
  [:div {:id "resize-tool" :class "container"}

   (let [supported (if (nil? supported?) 
                     (html5/file-api-supported?) supported?)
         files (:files @data)
         valid (filter image? files)
         others (filter #(not (image? %)) files)]
     
     ;; file api isn't supported, so show an error message
     (if (not supported)
       [:div {:class "row"}
        [:div {:class "col-xs-12"}
         [not-supported]]]

       ;; file app supported, try showing the widget
       [:div {:class "row"}

        [:div {:class "col-xs-12"}
         [:h3 "Resize Tool"]

         ;; File Chooser button and warning area
         [:div {:class "row"}
          [:div {:class "col-xs-2"}
           [file-select data]]
          [:div {:class "col-xs-10"}
           ;; if any files are not valid, then show warnings
           (if (not (empty? others))
             [:div {:class "alert alert-danger"} 
              "Oops, this only works for ics files, ignoring: "
              (apply str (interpose ", " (map #(get-file-name %) others)))])]]
         
         [:div {:class "row"}

          ;; horizontal separator, and then the chooser and table
          [:div {:class "col-xs-12"}
           [:hr]
           [:div {:class "row"}
            [:div {:class "col-xs-12 col-sm-3"} 
             [file-chooser valid data]]
            [:div {:class "col-xs-12 col-sm-9"} 
             [photo-editor data]]]
           ]]]]))])

(def data (r/atom {}))

(defn main []
  (if-let [node (.getElementById js/document "resize-div")]
    (r/render-component [resize-tool data] node)))

(main)
