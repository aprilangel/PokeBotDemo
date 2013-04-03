CREATE TABLE pokebot (
  `NOM` varchar(15) default NOT NULL,
  `ESPECE` varchar(15) default NOT NULL,
  `OWNER` varchar(15) default NULL,
  `PV` number(3) default NOT NULL,
  `PVMAX` number(3) default NOT NULL,
  `ISFIGHTING` number(1) default 0,
  `EXP` number(9) default 0,
  `LEVEL` number(3) default 1,
  PRIMARY KEY (`NOM`)
)


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


INSERT INTO `pokebot`
	VALUES ('MagicarpeShiny','Magicarpe',null,'1337','1337',0,1250000,100);

INSERT INTO `pokebot`
	VALUES ('NoctaliShiny','Noctali',null,'1337','1337',0,1000000,100);
