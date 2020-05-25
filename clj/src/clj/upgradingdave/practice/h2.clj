(ns upgradingdave.practice.h2
  (:import (org.h2.mvstore MVStore)))

(defn open [file-name]
  (MVStore/open file-name))

(defn open-map [store map-name]
  (.openMap store map-name))

(defn put-map [m k v]
  (.put m k v))

