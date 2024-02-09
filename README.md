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

# pour run l'application :
mvn spring-boot:run

http://localhost:8080/localfilesystem
http://localhost:8080/S3