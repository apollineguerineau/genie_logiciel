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



