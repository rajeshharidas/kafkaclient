FROM openjdk:8

ADD ./target/kafkaclient-0.0.1-SNAPSHOT.war app/kafkaclient-0.0.1-SNAPSHOT.war

WORKDIR app

ENTRYPOINT ["java","-jar", "kafkaclient-0.0.1-SNAPSHOT.war"]