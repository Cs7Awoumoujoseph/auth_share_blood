# Étape 1 : image de base contenant Maven + Java 17
# On utilise Maven pour compiler le projet Spring Boot
FROM maven:3.9.6-eclipse-temurin-17 AS build

# On définit le dossier de travail dans le conteneur
WORKDIR /app

# On copie tout le projet (code source + pom.xml) dans le conteneur
COPY . .

# On compile le projet et on génère le fichier .jar
# -DskipTests = ignore les tests pour accélérer le build
RUN mvn clean package -DskipTests


# Étape 2 : image finale (plus légère, sans Maven)
# On utilise seulement Java pour exécuter l'application
FROM eclipse-temurin:17-jdk

# Dossier de travail de l'application finale
WORKDIR /app

# On copie le fichier .jar généré depuis l'étape "build"
COPY --from=build /app/target/*.jar app.jar

# On expose le port 8080 (port utilisé par Spring Boot)
EXPOSE 8080

# Commande de démarrage de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]