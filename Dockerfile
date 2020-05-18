FROM openjdk:11-jre-slim
RUN groupadd spring && useradd spring -g spring
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
USER spring:spring
ENTRYPOINT ["java","-cp","app:app/lib/*","me.tecuani.kafkaproducer.KafkaHandlerApplication"]
