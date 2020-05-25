(ns up.zmq.core
  (:require [clojure.core.async :as async :refer [thread]])
  (:import (org.zeromq ZMQ)))

(defn server 
  "pass an atom, when atom is true, the server exits"
  [exit]
  (let [context (ZMQ/context 1)
        responder (.socket context ZMQ/REP)]
    (.bind responder "tcp://*:5555")
    (println "starting zmq server ...")
    (thread 
      (do
        (while (not @exit)
          (let [request (.recv responder 0)
                reply "World"]
            (println (str "Recieved: " request))
            (.send responder (.getBytes reply) 0)))
        (println "exiting zmq server ...")
        (.close responder)
        (.term context)
        true))))


(defn client []
  (let [context (ZMQ/context 1)]
    (println "Connecting to hello world server...")
    {:context context
     :requester 
     (doto (.socket context ZMQ/REQ)
       (.connect "tcp://localhost:5555"))}))

(defn send [{:keys [requester]} msg]
  (println (str "sending " msg "..."))
  (.send requester (.getBytes msg) 0)
  (let [reply (.recv requester 0)]
    (println (str reply))))

(defn client-close [{:keys [requester context]}]
  (println "closing client ...")
  (.close requester)
  (.term context))




