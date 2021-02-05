# lein uberjar
export=JVM_OPTS="-Dvertx.threadChecks=false -Dvertx.disableContextTimings=true"
java $JVM_OPTS -jar ./target/demo.jar 