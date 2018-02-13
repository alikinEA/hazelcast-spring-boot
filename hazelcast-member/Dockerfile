FROM frolvlad/alpine-oraclejdk8:slim
ADD /target/hazelcast-member.jar hazelcast-member.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /hazelcast-member.jar