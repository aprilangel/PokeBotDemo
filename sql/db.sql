--	DROP TABLE pokebot;

CREATE TABLE Pokebot (
  NOM 		VARCHAR(15),
  ESPECE 	VARCHAR(15),
  OWNER 	VARCHAR(15),
  JUDGE		VARCHAR(15),
  PV 		INT(3),
  PVMAX 	INT(3),
  ISFIGHTING 	INT(1),
  EXP 		INT(9),
  LEVEL 	INT(3),
  PRIMARY KEY (NOM)
);


--	** Si on ne veux plus utiliser le pokedex **
--
--	CREATE TABLE pokemon (
--	  `ESPECE` varchar(15) default NOT NULL,
--	  `TYPE1` varchar(15) default NOT NULL,
--	  `TYPE2` varchar(15) default NULL,
--	  `EXPVAL` number(3) default NOT NULL,
--	  `EXPMAX` number(9) default NOT NULL,
--
--	  PRIMARY KEY (`ESPECE`)
--	)
--
--	CREATE TABLE attaques (
--	  `NOM` varchar(15) default NOT NULL,
--	  `PUISSANCE` number(3) default NULL,
--	  `PRECISION` number(3) default NOT NULL,
--	  `PP` number(2) default NOT NULL,
--
--	  PRIMARY KEY (`NOM`)
--	)


INSERT INTO `Pokebot`
	VALUES ('MagicarpeShiny','Magicarpe',null,null,'1337','1337',0,1250000,100);

--
--	INSERT INTO `pokebot`
--	VALUES ('NoctaliShiny','Noctali',null,'1337','1337',0,1000000,100);
--
