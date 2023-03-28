from openjdk:17
LABEL maintainer="https://aesthetic-sherbet-35a03e.netlify.app/"
ADD target/crud-0.0.1-SNAPSHOT.jar crudkaiburr8.jar
ENTRYPOINT ["java","-jar","crudkaiburr8.jar"]