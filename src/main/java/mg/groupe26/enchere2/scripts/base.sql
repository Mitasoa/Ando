CREATE SEQUENCE seq_Admin NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_Categorie NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_Enchere NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_Historique NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_RechargeCompte NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_Token NO MINVALUE NO MAXVALUE START WITH 1;

CREATE SEQUENCE seq_Utilisateur NO MINVALUE NO MAXVALUE START WITH 1;

CREATE TABLE Admin (
	id varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	mdp varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Categorie (
	id varchar(255) NOT NULL,
	intitule varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE Enchere (
	id varchar(255) NOT NULL,
	proprietaireid varchar(255) NOT NULL,
	dateDebut timestamp DEFAULT now() NOT NULL,
	duree float,
	gagnantId varchar(255),
	prixVente float4,
	status int4 DEFAULT 0 NOT NULL,
	nomProduit varchar(255) NOT NULL,
	descri text NOT NULL,
	prixMin float4 NOT NULL,
	Categorieid varchar(255) NOT NULL,
        img text,
	PRIMARY KEY (id)
);

CREATE TABLE Historique (
	id varchar(255) NOT NULL,
	dateEnchere timestamp default now(),
	prix float4,
	Utilisateurid varchar(255) NOT NULL,
	Enchereid varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE RechargeCompte (
	id varchar(255) NOT NULL,
	montant float4,
	estValide int4 DEFAULT 0 NOT NULL,
	dateDemande timestamp DEFAULT now() NOT NULL,
	Utilisateurid varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Token (
	id varchar(255) NOT NULL,
	contenu varchar(255) NOT NULL UNIQUE,
	Utilisateurid varchar(255) NOT NULL,
	dateExpiration timestamp DEFAULT now() + interval '90 minutes' NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Utilisateur (
	id varchar(255) NOT NULL,
	nom varchar(255) NOT NULL,
	pseudo varchar(255) NOT NULL UNIQUE,
	email varchar(255) NOT NULL UNIQUE,
	mdp varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Config (tauxCommission float4 NOT NULL);

ALTER TABLE
	RechargeCompte
ADD
	FOREIGN KEY (Utilisateurid) REFERENCES Utilisateur (id);

ALTER TABLE
	Historique
ADD
	FOREIGN KEY (Utilisateurid) REFERENCES Utilisateur (id);

ALTER TABLE
	Historique
ADD
	FOREIGN KEY (Enchereid) REFERENCES Enchere (id);

ALTER TABLE
	Token
ADD
	FOREIGN KEY (Utilisateurid) REFERENCES Utilisateur (id);

ALTER TABLE
	Enchere
ADD
	FOREIGN KEY (proprietaireid) REFERENCES Utilisateur (id);

ALTER TABLE
	Enchere
ADD
	FOREIGN KEY (Categorieid) REFERENCES Categorie (id);

create
or replace view v_Enchere as (
	SELECT
		*,
		(datedebut + (interval '1 hour' * duree)) as datefin
	FROM
		enchere
);

create
or replace view v_RechargeCompte as (
	select
		rc.*,
		u.nom,
		u.pseudo,
		u.email
	from
		RechargeCompte rc
		join Utilisateur u on rc.Utilisateurid = u.id
);

create
or replace view v_EnchereCategorie as (
	select
		e.categorieId,
		count(e.*) as nb,
		c.intitule
	from
		enchere e
		join categorie c on e.categorieid = c.id
	group by
		e.categorieId,
		c.intitule
);

create
or replace view v_EnchereTotal as (
	select
		count(*) as total
	from
		enchere
);

create
or replace view v_Solde as (
	select
		utilisateurId,
		sum(montant) as solde
	from
		RechargeCompte
	where
		estValide = 1
	group by
		utilisateurId
);