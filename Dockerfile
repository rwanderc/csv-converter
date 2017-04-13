FROM java:8u102-jre

RUN mkdir /app
ADD ./target/csv-converter-1.0-exec.jar /app/csv-converter.jar

WORKDIR /app
ENTRYPOINT ["java", "-jar", "csv-converter.jar"]
