# import psycopg2

# # Connexion à la base de données
# conn = psycopg2.connect(
#     dbname="db_project",
#     user="db_user",
#     password="db_password",
#     host="localhost",
#     port="5433"
# )

# # Création d'un curseur pour exécuter des requêtes SQL
# cur = conn.cursor()

# # Exécution d'une requête SQL pour récupérer le contenu de la table Scan
# cur.execute("SELECT * FROM FileScanAssoc")

# # Récupération des résultats de la requête
# rows = cur.fetchall()

# # Affichage des résultats
# for row in rows:
#     print(row)

# # Fermeture du curseur et de la connexion à la base de données
# cur.close()
# conn.close()

import psycopg2

# Connexion à la base de données PostgreSQL (assurez-vous que le serveur PostgreSQL est en cours d'exécution)
conn = psycopg2.connect(
    host="localhost",
    user="db_user",
    password="db_password"
)

# Création d'un curseur
cur = conn.cursor()

# Lecture du contenu du fichier schema.sql
with open('/home/ensai/Documents/3A/GENIE_LOGICIEL/genie_logiciel/src/main/resources/schema.sql', 'r') as file:
    schema_sql = file.read()

# Exécution du script schema.sql pour créer les tables
cur.execute(schema_sql)

# Validation des changements et fermeture de la connexion
conn.commit()
conn.close()

