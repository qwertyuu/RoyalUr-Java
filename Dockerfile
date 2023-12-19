FROM maven:3.8-openjdk-17

WORKDIR /usr/src/app
COPY . .
RUN mvn install
RUN mvn clean compile assembly:single
EXPOSE 8080
ENTRYPOINT ["sh", "/usr/src/app/startup.sh"]