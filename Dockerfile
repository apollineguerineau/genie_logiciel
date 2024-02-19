# Utiliser une image de base avec Java 17
FROM openjdk:17-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Installer Maven
RUN apk add --no-cache maven

# Copier le dossier .m2 dans le conteneur
COPY .m2 .
# Copier le dossier .mvn dans le conteneur
COPY .mvn .
# Copier le fichier mvnw dans le conteneur
COPY mvnw .
# Copier le fichier mvnw.cmd dans le conteneur
COPY mvnw.cmd .
# Copier le fichier pom.xml dans le conteneur
COPY pom.xml .

# Copier tous les fichiers du projet dans le conteneur
COPY src ./src

# Compiler l'application
RUN mvn clean package -e

# Commande à exécuter lorsque le conteneur démarre
CMD ["java", "-jar", "target/demo-1.0.jar"]