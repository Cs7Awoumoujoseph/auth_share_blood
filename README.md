# Auth Share Blood

## Présentation

**Auth Share Blood** est un backend d'authentification développé avec Spring Boot pour un projet de partage de sang. Le code comprend des composants dédiés aux donneurs, aux hôpitaux et aux comptes institutionnels, ainsi qu'une configuration de sécurité fondée sur Spring Security et les jetons JWT.

Le dépôt fournit le backend ; il ne suffit pas, à lui seul, à décrire l'interface utilisateur ou l'ensemble de l'écosystème de partage de sang.

## Fonctionnalités identifiées dans le code

- API HTTP construite avec Spring Web.
- Persistance via Spring Data JPA et PostgreSQL.
- Configuration de sécurité Spring Security.
- Gestion de jetons JWT.
- Classes d'inscription pour différents types de comptes.
- Contrôleurs REST liés aux donneurs, aux hôpitaux et aux comptes institutionnels.
- Dépendance d'envoi de courriels via Spring Mail.

Les routes exactes, les règles d'autorisation et les parcours d'inscription doivent être vérifiés dans les contrôleurs et la configuration de sécurité avant intégration à un client.

## Technologies

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT avec `java-jwt`
- Spring Mail
- Maven Wrapper
- Docker

## Structure du dépôt

```text
auth_share_blood/
├── src/main/java/com/jojo/authentification_blood_share/
│   ├── Repository/       # Accès aux données
│   ├── config_env/       # Configuration applicative et JWT
│   ├── entities/         # Entités métier
│   ├── exceptrions/      # Gestion des exceptions
│   ├── register/         # Modèles/requêtes d'inscription
│   └── restControlleur/  # Contrôleurs REST
├── src/main/resources/
│   └── application.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
└── Dockerfile
```

## Prérequis

- JDK 17.
- PostgreSQL.
- Accès à un serveur SMTP si les fonctionnalités de courriel sont utilisées.
- Docker, facultatif.

## Configuration

L'application attend les variables d'environnement suivantes. Utilisez des valeurs propres à votre environnement et ne les committez jamais dans Git.

| Variable | Utilisation |
|---|---|
| `DB_URL` | URL JDBC PostgreSQL |
| `DB_USERNAME` | Utilisateur PostgreSQL |
| `DB_PASSWORD` | Mot de passe PostgreSQL |
| `JWT_SECRET` | Secret de signature des JWT |
| `JWT_EXPIRATION` | Durée de validité configurée pour les JWT |
| `APP_SECRET_GOVPASS` | Secret applicatif utilisé pour les opérations institutionnelles |
| `MAIL_USERNAME` | Compte SMTP |
| `MAIL_PASS` | Mot de passe ou secret SMTP |
| `PORT` | Port HTTP, facultatif (8080 par défaut) |

L'application utilise le chemin de contexte `/auth). Préparez la base de données et configurez tous les paramètres requis avant de démarrer le serveur.

## Installation et lancement

Clonez le dépôt :

```bash
git clone https://github.com/Cs7Awoumoujoseph/auth_share_blood.git
cd auth_share_blood
```

Configurez les variables d'environnement, puis démarrez avec le Maven Wrapper.

Linux/macOS :

```bash
./mvnw spring-boot:run
```

Windows :

```bat
mvnw.cmd spring-boot:run
```

Pour générer l'artefact :

```bash
./mvnw clean package
```

Le serveur écoute sur le port configuré, par défaut `8080). Le chemin de contexte est `/auth`; les chemins complets des endpoints dépendent des mappings des contrôleurs.

## Courriels

La configuration présente utilise SMTP avec TLS sur le port 587. Renseignez les variables SMTP dans l'environnement et utilisez un compte ou un secret prévu pour l'application. N'intégrez pas de mot de passe de messagerie dans le code source.

## Sécurité et production

- Utilisez un secret JWT long, aléatoire et confidentiel ; ne réutilisez pas un secret de développement.
- Vérifiez la durée de validité, la validation de signature et les autorisations associées à chaque endpoint.
- Ne désactivez pas la protection CSRF ou d'autres protections sans analyser le modèle d'authentification utilisé.
- Vérifiez que les mots de passe utilisateurs sont hachés avec un algorithme adapté et jamais enregistrés en clair.
- Utilisez HTTPS en production.
- Réexaminez la configuration Hibernate `ddl-auto=update` avant déploiement et privilégiez des migrations versionnées.
- Ajoutez des tests d'intégration couvrant les inscriptions, l'authentification, les rôles et les accès non autorisés.

## Tests

Le projet inclut Spring Boot Test. Lancez les tests avec :

```bash
./mvnw test
```

Sous Windows, utilisez `mvnw.cmd test`.

## Licence

Aucune licence explicite n'est identifiée dans le dépôt. Ajoutez une licence pour clarifier les conditions de réutilisation.
