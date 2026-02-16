FROM amazoncorretto:17-alpine3.15
LABEL MANTAINER="Victor Cardoso"

WORKDIR /app

EXPOSE 8080

RUN apk add --no-cache curl

RUN wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

COPY spring/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]