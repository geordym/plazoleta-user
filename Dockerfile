FROM eclipse-temurin:17.0.12_7-jre

WORKDIR /root

# Copia el JAR ya construido desde tu máquina local
COPY ./build/libs/user-0.0.1-SNAPSHOT.jar ./user-0.0.1-SNAPSHOT.jar

EXPOSE 7080

ENTRYPOINT ["java", "-jar", "/root/user-0.0.1-SNAPSHOT.jar"]
