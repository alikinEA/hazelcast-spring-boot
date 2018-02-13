FROM frolvlad/alpine-oraclejdk8:slim
ADD /target/admin.jar admin.jar
ENTRYPOINT ["java", "-jar" ,"/admin.jar" ]