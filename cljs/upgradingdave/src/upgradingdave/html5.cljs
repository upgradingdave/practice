(ns upgradingdave.html5)

(defn file-api-supported? []
  (and js/File
       js/FileReader 
       js/FileList 
       js/Blob))


;; toBlob polyfill from 
;; https://developer.mozilla.org/en-US/docs/Web/API/HTMLCanvasElement/toBlob
(defn to-blob [canvas callback type quality]
  (if (not (.. js/HTMLCanvasElement prototype toBlob))

    ;; If browser doesn't support canvas.toBlob, use this polyfill from
    ;; https://developer.mozilla.org/en-US/docs/Web/API/HTMLCanvasElement/toBlob
    (let [bin-str (aget (.split (js/atob (.toDataURL canvas type quality))) 1)
          len     (.-length bin-str)
          arr     (js/Uint8Array. len)
          type    (or type "image/png")]
      (loop [i 0]
        (when (< i len)
          (aset arr i (.charCodeAt bin-str i))
          (recur (inc i))))

      (callback (js/Blob. #js [arr] #js {"type" type}))))

  ;; If browser supports canvas.toBlob, then just call that
  (.toBlob canvas callback type quality))

