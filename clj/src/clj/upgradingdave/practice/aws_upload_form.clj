(ns upgradingdave.practice.aws-upload-form
  (:import (javax.crypto Mac)
           (javax.crypto.spec SecretKeySpec)
           (sun.misc BASE64Encoder))
  (:require [clojure.data.json :as json]))

;; This policy is good if you want to redirect
(def example1
  {"expiration" "2017-05-01T00:00:00Z"
   "conditions" [{"bucket" "upgradingdave"}
                 ["starts-with" "$key" "uploads/"]
                 {"acl" "private"}
                 {"success_action_redirect" 
                  "http://upgradingdave.com/blog/html/upload_success.html"}
                 ;; ["starts-with" "$Content-Type" ""]
                 ["content-length-range" 0 20971520]]})

;; This policy is good for ajax
(def example2
  {"expiration" "2017-05-01T00:00:00Z"
   "conditions" [{"bucket" "upgradingdave"}
                 ["starts-with" "$key" "uploads/"]
                 {"acl" "private"}
                 {"success_action_status" "201"}
                 ["content-length-range" 1 20971520]]})

(defn encode-policy [policy-map]
  (let [json-policy (json/write-str policy-map)]
    (-> (.encode (sun.misc.BASE64Encoder.) (.getBytes json-policy "UTF-8"))
        (.replaceAll "\n" "")
        (.replaceAll "\r" ""))))

(defn signature [policy-encoded aws-secret-key]
  (let [hmac (doto (javax.crypto.Mac/getInstance "HmacSHA1")
               (.init (javax.crypto.spec.SecretKeySpec. 
                       (.getBytes aws-secret-key "UTF-8") "HmacSHA1")))]
    (-> (.encode (sun.misc.BASE64Encoder.) 
                 (.doFinal hmac (.getBytes policy-encoded "UTF-8")))
        (.replaceAll"\n" ""))))

(defn policy-and-signature 
  "Generate policy and signature field values. Copy the results into html form"
  [policy-map aws-secret-key]
  (let [policy-encoded (encode-policy policy-map)]
    {:policy policy-encoded
     :signature (signature policy-encoded aws-secret-key)}))

