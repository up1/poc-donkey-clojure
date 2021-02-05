## Demo
Create new project
```
$lein new app clojure-donkey
```

Add depednencies in file `project.clj`
```
(defproject clojure-donkey "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
    [org.clojure/clojure "1.10.0"]
    [com.appsflyer/donkey "0.4.2"]]
  :main ^:skip-aot clojure-donkey.core
  :uberjar-name "demo.jar"
  :profiles {:uberjar {:aot :all}})

```

Create first HTTP server
```
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
```

Build JAR file
```
$lein uberjar
$export=JVM_OPTS="-Dvertx.threadChecks=false -Dvertx.disableContextTimings=true"
$java $JVM_OPTS -jar ./target/demo.jar 

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Server started listening on port 8080
```

Performance testing with wrk
```
$wrk -c100 -t5 -d10s http://localhost:8080

Running 10s test @ http://localhost:8080
  5 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.02ms  501.07us  20.85ms   98.23%
    Req/Sec    19.93k     2.42k   23.91k    63.96%
  1001346 requests in 10.10s, 49.66MB read
Requests/sec:  99120.87
Transfer/sec:      4.92MB
```