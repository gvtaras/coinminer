FROM openjdk:11-jre-slim

WORKDIR /app
ARG JAR_FILE
ENV PORT=8081
COPY target/${JAR_FILE} /app/microservice.jar

# run application with this command line
CMD java -jar \
    /app/microservice.jar \
    --spring.config.location="/app/conf/${PROPERTY_FILE}"

EXPOSE ${PORT}
