CREATE TABLE Cours(
   Id_Cours INT AUTO_INCREMENT,
   Nom_Cours VARCHAR(255) NOT NULL,
   Nombre_Seance TINYINT NOT NULL,
   PRIMARY KEY(Id_Cours)
);

CREATE TABLE Annee_Scolaire(
   Id_AnneeScolaire INT AUTO_INCREMENT,
   Annee_Scolaire VARCHAR(999) NOT NULL,
   PRIMARY KEY(Id_AnneeScolaire)
);

CREATE TABLE Section(
   Id_Section INT AUTO_INCREMENT,
   Nom_Section VARCHAR(123) NOT NULL,
   Degres_Section VARCHAR(690) NOT NULL,
   Nom_Titulaire VARCHAR(470) NOT NULL,
   PRIMARY KEY(Id_Section)
);

CREATE TABLE Localite(
   Id_Localite INT AUTO_INCREMENT,
   Code_Postale SMALLINT NOT NULL,
   Localite VARCHAR(255) NOT NULL,
   PRIMARY KEY(Id_Localite)
);

CREATE TABLE Cours_Organises(
   Id_Cours_Organises INT AUTO_INCREMENT,
   Id_Cours INT NOT NULL,
   Id_AnneeScolaire INT NOT NULL,
   PRIMARY KEY(Id_Cours_Organises),
   FOREIGN KEY(Id_Cours) REFERENCES Cours(Id_Cours),
   FOREIGN KEY(Id_AnneeScolaire) REFERENCES Annee_Scolaire(Id_AnneeScolaire)
);

CREATE TABLE Professeur(
   Id_Professeur INT AUTO_INCREMENT,
   Nom_Professeur VARCHAR(50) NOT NULL,
   Prenom_Professeur VARCHAR(50),
   Adresse_Professeur VARCHAR(50),
   Telephone_Professeur VARCHAR(20) NOT NULL,
   Email_Professeur VARCHAR(30) NOT NULL,
   Id_Localite INT NOT NULL,
   PRIMARY KEY(Id_Professeur),
   FOREIGN KEY(Id_Localite) REFERENCES Localite(Id_Localite)
);

CREATE TABLE Etudiant(
   Id_Etudiant INT AUTO_INCREMENT,
   Nom_Etudiant VARCHAR(50) NOT NULL,
   Prenom_Etudiant VARCHAR(50),
   Adresse_Etudiant VARCHAR(50),
   Telephone_Etudiant VARCHAR(20) NOT NULL,
   Email_Etudiant VARCHAR(30) NOT NULL,
   Id_Localite INT NOT NULL,
   PRIMARY KEY(Id_Etudiant),
   FOREIGN KEY(Id_Localite) REFERENCES Localite(Id_Localite)
);

CREATE TABLE Seances(
   Id_Seance INT AUTO_INCREMENT,
   Numero_Seance VARCHAR(200000) NOT NULL,
   Id_Cours_Organises INT NOT NULL,
   PRIMARY KEY(Id_Seance),
   FOREIGN KEY(Id_Cours_Organises) REFERENCES Cours_Organises(Id_Cours_Organises)
);

# ----------------------- PIVOTS --------------------- 
CREATE TABLE R_Section_Cours(
   Id_Cours INT,
   Id_Section INT,
   PRIMARY KEY(Id_Cours, Id_Section),
   FOREIGN KEY(Id_Cours) REFERENCES Cours(Id_Cours),
   FOREIGN KEY(Id_Section) REFERENCES Section(Id_Section)
);

CREATE TABLE R_Etudiant_Seances(
   Id_Etudiant INT,
   Id_Seance INT,
   Presence BOOLEAN NOT NULL,
   PRIMARY KEY(Id_Etudiant, Id_Seance),
   FOREIGN KEY(Id_Etudiant) REFERENCES Etudiant(Id_Etudiant),
   FOREIGN KEY(Id_Seance) REFERENCES Seances(Id_Seance)
);

CREATE TABLE R_Professeur_CoursOrganises(
   Id_Professeur INT,
   Id_Cours_Organises INT,
   PRIMARY KEY(Id_Professeur, Id_Cours_Organises),
   FOREIGN KEY(Id_Professeur) REFERENCES Professeur(Id_Professeur),
   FOREIGN KEY(Id_Cours_Organises) REFERENCES Cours_Organises(Id_Cours_Organises)
);

CREATE TABLE R_Etudiant_CoursOrganises(
   Id_Etudiant INT,
   Id_Cours_Organises INT,
   Inscrit BOOLEAN NOT NULL,
   PRIMARY KEY(Id_Etudiant,Id_Cours_Organises),
   FOREIGN KEY(Id_Etudiant) REFERENCES Etudiant(Id_Etudiant),
   FOREIGN KEY(Id_Cours_Organises) REFERENCES Cours_Organises(Id_Cours_Organises)
);
