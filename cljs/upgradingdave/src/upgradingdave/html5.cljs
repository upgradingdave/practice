(ns upgradingdave.html5)

(defn file-api-supported? []
  (and js/File
       js/FileReader 
       js/FileList 
       js/Blob))
