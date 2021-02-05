(ns clojure-donkey.core
  (:require [com.appsflyer.donkey.core :refer [create-donkey create-server]]
            [com.appsflyer.donkey.server :refer [start]]
            [com.appsflyer.donkey.result :refer [on-success]])
  (:gen-class)
)

(defn -main []
  (->
    (create-donkey)
    (create-server {:port   8080
                    :routes [{:handler (fn [_request respond _raise]
                                        (respond {:body "Hello, world!"}))}]})
    start
    (on-success (fn [_] (println "Server started listening on port 8080"))))

)