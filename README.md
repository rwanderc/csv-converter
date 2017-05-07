# CSV Converter

[![Build Status](https://travis-ci.org/rwanderc/csv-converter.svg?branch=master)](https://travis-ci.org/rwanderc/csv-converter)

CSV Converter is an application to convert JSON and XML files into CSV.
It was designed as a microservice to provide high quality and adaptable solution for possible applications consuming it.
As a web service, it offers language-agnostic solution and a high performance experience, making possible for different
applications to consume it simultaneously over HTTP protocol.

# Getting Started
Clone the project into your computer.
If order to build this application locally, you will need `Java JRE 8 (build 1.8.0_73-b02)` and `Maven 3.3.9` or
greater. In addition, you can also build the Docker image for the application. In this case, you will need `Docker
1.13.0` or greater.

To compile the project, run the command `mvn clean install` from the project folder.

In order to run the application from the source code, run the command `mvn spring-boot:run` from the project folder.

In order to run the application from the JAR file, run the command `java -jar target/csv-converter-1.0-exec.jar` from
the project folder.

In order to build the image for Docker, run the command `docker build -t csv-converter .` from the project folder.

In order to run the container, run the command `docker run -p 8080:8080 csv-converter`.


# License
The license of this project is described in the file LICENSE in the project folder.
