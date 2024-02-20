# genie_logiciel
cd genie_logiciel
mvn package

# changer les credentials pour aws
aws configure
aws configure set aws_session_token "<token>"

# upload un dossier dans un bucket S3 (pour tester)
aws s3 sync dossier_a_upload s3://bucketprojectapolline/dossier1

# base de données local (pour l'instant): 
sudo docker stop db_container_project
sudo docker rm db_container_project
sudo docker run --name db_container_project -e POSTGRES_DB=db_project -e POSTGRES_USER=db_user -e POSTGRES_PASSWORD=db_password -p 5433:5432 -d postgres

# pour run les tests : 
mvn test
# puis pour voir la  converture des tests : 
ouvrir le fichier target/site/jacoco/index.html dans un navigateur web

# pour run l'application :
mvn spring-boot:run

http://0.0.0.0:8080/get_all_scans
http://0.0.0.0:8080/create_scan/local_file_system?path=/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest&filenameFilter=file&extensionFilter=txt
http://0.0.0.0:8080/create_scan/S3_file_system?bucketName=bucketprojectapolline

http://localhost:8082/swagger
http://localhost:8082/create_scan/local_file_system?path=/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest&filenameFilter=file&extensionFilter=txt
http://localhost:8082/create_scan/S3_file_system?bucketName=bucketprojectapolline&filenameFilter=test&extensionFilter=txt
http://localhost:8082/get_scan?id=1
http://localhost:8082/get_all_scans
http://localhost:8082/delete_scan?id=2
http://localhost:8082/replay_scan?id=1
http://localhost:8082/compare_scans?id1=5&id2=7

# image Docker : 
sudo docker-compose build
sudo docker-compose up