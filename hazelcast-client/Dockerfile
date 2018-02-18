FROM frolvlad/alpine-oraclejdk8:slim
ADD /target/hazelcast-client.jar hazelcast-client.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /hazelcast-client.jar