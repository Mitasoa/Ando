insert into
	Admin
values
	(
		CONCAT('Admin', nextval('seq_Admin')),
		'admin@gmail.com',
		'admin'
	);

insert into
	Utilisateur
values
	(
		CONCAT('Utilisateur', nextval('seq_Utilisateur')),
		'Ando',
		'ando',
		'ando@gmail.com',
		'ando'
	),
	(
		CONCAT('Utilisateur', nextval('seq_Utilisateur')),
		'Martin',
		'martin',
		'martin@gmail.com',
		'1111'
	),
	(
		CONCAT('Utilisateur', nextval('seq_Utilisateur')),
		'Koto',
		'koto',
		'koto@gmail.com',
		'222'
	),
	(
		CONCAT('Utilisateur', nextval('seq_Utilisateur')),
		'Bob',
		'bob',
		'bob@gmail.com',
		'333'
	),
	(
		CONCAT('Utilisateur', nextval('seq_Utilisateur')),
		'Raozy',
		'raozy',
		'raozy@gmail.com',
		'444'
	);

insert into
	Categorie
values
	(
		concat('Categorie', nextval('seq_Categorie')),
		'oeuvre'
	),
	(
		concat('Categorie', nextval('seq_Categorie')),
		'meuble'
	),
	(
		concat('Categorie', nextval('seq_Categorie')),
		'technologie'
	),
	(
		concat('Categorie', nextval('seq_Categorie')),
		'fashion'
	);

insert into
	Enchere
values
	(
		concat('Enchere', nextval('seq_Enchere')),
		'Utilisateur1',
		default,
		12,
		default,
		default,
		default,
		'Tableau',
		'objet de valeur',
		110000,
		'Categorie1',
                default
	),
	(
		concat('Enchere', nextval('seq_Enchere')),
		'Utilisateur1',
		default,
		12,
		default,
		default,
		default,
		'Table',
		'en bois',
		110000,
		'Categorie2',
                default
	),
	(
		concat('Enchere', nextval('seq_Enchere')),
		'Utilisateur1',
		default,
		12,
		default,
		default,
		default,
		'Smartphone',
		'Samsung S10',
		10000,
		'Categorie3',
                default
	),
	(
		concat('Enchere', nextval('seq_Enchere')),
		'Utilisateur1',
		default,
		12,
		default,
		default,
		default,
		'Chaussure',
		'de France',
		12000,
		'Categorie4',
                default
	),
	(
		concat('Enchere', nextval('seq_Enchere')),
		'Utilisateur2',
		default,
		12,
		default,
		default,
		default,
		'Batterie',
		'300W',
		20000,
		'Categorie3',
                default
	);

insert into
	RechargeCompte
values
	(
		concat('RechargeCompte', nextval('seq_RechargeCompte')),
		200000,
		default,
		default,
		'Utilisateur1'
	),
	(
		concat('RechargeCompte', nextval('seq_RechargeCompte')),
		400000,
		default,
		default,
		'Utilisateur1'
	),
	(
		concat('RechargeCompte', nextval('seq_RechargeCompte')),
		600000,
		default,
		default,
		'Utilisateur2'
	),
	(
		concat('RechargeCompte', nextval('seq_RechargeCompte')),
		250000,
		default,
		default,
		'Utilisateur2'
	);

insert into
	Config
values
	('10');