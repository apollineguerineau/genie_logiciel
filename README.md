# Application web backend qui scanne les répertoires et les fichiers

## Contenu du dépôt : 

* Un fichier dockerfile qui permet de créer l'image /app.
* Un fichier docker-compose qui permet de gérer la base de données postgres et l'application.
* Un fichier ci-cd.ylm qui permet de déclencher des actions CICD : 
    * consruit l'image à partir du docker-compose.yml
    * run les tests et push l'image sur docker hub si les tests passent
    * se connecte à aws
    * se connecte en ssh à une instance ec2 pour installer Docker, pull l'image et le fichier docker-compose.yml

## Comment utiliser l'application : 
### 1. Créer une paire de clés puis une instance ec2 sur aws qui utilise cette paire de clés
### 2. Remplir un fichier .env avec 
DOCKER_USERNAME
### 3. 6 variables secrètes à remplir sur github :
Variables secrètes de configuration pour aws
* AWS_ACCESS_KEY_ID
* AWS_SECRET_ACCESS_KEY
* AWS_SECRET_TOKEN
Variable qui stocke la clé privée reliée à la clé publique de l'instance ec2
SSH_PRIVATE_AWS_KEY
Variables secrètes pour se connecter à docker hub
* DOCKER_PASSWORD
* DOCKER_USERNAME
### 4. Se connecter en ssh à l'instance ec2 puis
* export DOCKER_USERNAME=<your-docker-username>
* docker-compose pull
### 5. Requête http pour utiliser l'application

## Endpoint exposé par l'application : 
/swagger
/create_scan/local_file_system?path=...
/create_scan/S3_file_system?bucketName=...
/get_scan?id=...
/get_all_scans
/delete_scan?id=...
/replay_scan?id=...
/compare_scans?id1=...&id2=...