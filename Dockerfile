FROM openjdk:20-jdk-slim-bullseye

RUN apt-get -y update
RUN apt-get -y install tesseract-ocr

COPY ./target/ocr-server.jar /opt/
COPY ./lib /opt/

CMD ["java", "-jar", "/opt/ocr-server.jar"]