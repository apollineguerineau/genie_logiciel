# genie_logiciel

cd genie_logiciel
mvn compile
mvn package
java -cp target/demo-1.0.jar fr.ensai.demo.Main

# changer les credentials pour aws
aws configure
aws configure set aws_session_token "<token>"

# upload un dossier dans un bucket S3 (pour tester)
aws s3 sync dossier_a_upload s3://bucketprojectapolline/dossier1

# base de données : 
sudo docker stop db_container_project
sudo docker rm db_container_project
sudo docker run --name db_container_project -e POSTGRES_DB=db_project -e POSTGRES_USER=db_user -e POSTGRES_PASSWORD=db_password -p 5433:5432 -d postgres

# pour run l'application :
mvn spring-boot:run

http://localhost:8080/local_file_system?path=/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest&filenameFilter=file&extensionFilter=txt
http://localhost:8080/S3_file_system?bucketName=bucketprojectapolline&filenameFilter=test&extensionFilter=txt