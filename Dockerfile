FROM openjdk:17
LABEL maintainer="bilisim.com.tr"
ADD target/erp-0.0.1-SNAPSHOT.jar erpMini.jar
ENTRYPOINT ["java","-jar","erpMini.jar"]