FROM openjdk:8

ADD ./target/kafkaclient-0.0.1-SNAPSHOT.jar app/kafkaclient-0.0.1-SNAPSHOT.jar

WORKDIR app

ENTRYPOINT ["java","-jar", "kafkaclient-0.0.1-SNAPSHOT.jar"]
