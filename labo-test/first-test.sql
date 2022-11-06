CREATE TABLE Cours(
   Id_Cours INT AUTO_INCREMENT,
   Nom_Cours VARCHAR(255) NOT NULL,
   Nombre_Seance TINYINT NOT NULL,
   PRIMARY KEY(Id_Cours)
);

CREATE TABLE Annee_Scolaire(
   Id_AnneeScolaire INT AUTO_INCREMENT,
   Annee_Scolaire VARCHAR(255) NOT NULL,
   PRIMARY KEY(Id_AnneeScolaire)
);

CREATE TABLE Section(
   Id_Section INT AUTO_INCREMENT,
   Nom_Section VARCHAR(255) NOT NULL,
   Degres_Section VARCHAR(255) NOT NULL,
   Nom_Titulaire VARCHAR(470) NOT NULL,
   PRIMARY KEY(Id_Section)
);
