FROM maven:3.8.4-openjdk-17.0.4

WORKDIR /usr/src/app
COPY . .
RUN mvn install
RUN mvn clean compile assembly:single
EXPOSE 8080
CMD ["/usr/src/app/startup.sh"]