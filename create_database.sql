DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;

DROP TABLE IF EXISTS ansatt;
DROP TABLE IF EXISTS avdeling;
DROP TABLE IF EXISTS prosjekt;
DROP TABLE IF EXISTS prosjekt_ansatte;

--Lager avdeling tabel
CREATE TABLE avdeling
(
    avdelingId SERIAL,
    navn VARCHAR (40) not null,
    sjef Integer not null,
    CONSTRAINT avdeling_pk Primary Key (avdelingId)
);

--Lager ansatt table
CREATE TABLE ansatt
(
    ansattId SERIAL, 
    brukernavn VARCHAR (10) not null unique,
    fornavn VARCHAR (30) not null,
    etternavn VARCHAR not null,
    dato DATE,
    stilling VARCHAR not null,
    maanedslonn integer,
    avdelingId Integer not null,
    CONSTRAINT ansatt_pk Primary Key (ansattId),
    FOREIGN KEY(avdelingId) references avdeling(avdelingId),
    CONSTRAINT ansattBr CHECK ((LENGTH(brukernavn) >= 3) AND (LENGTH(brukernavn) <= 4))
);

CREATE TABLE prosjekt
(
    prosjektId SERIAL,
    navn VARCHAR (40) not null,
    beskrivelse VARCHAR (40),
    CONSTRAINT prosjekt_pk Primary Key (prosjektId)
);

INSERT INTO 
prosjekt(Navn,Beskrivelse)
Values 
('Oblig1','DAT102 Øving1'),
('Oblig2','MAT108 Øving1'),
('Oblig3','DAT107 Øving2');



INSERT INTO
  avdeling(navn, sjef)
        VALUES
        ('DAT100', 1),
        ('DAT102', 2),
        ('DAT107', 3);

CREATE TABLE prosjekt_ansatte
(
 prosjektDeltagelseId SERIAL,	
 ansattId integer not null,
 prosjektId Integer not null,
 rolle varChar(40),
 arbeidstimer Integer,
 er_aktiv varChar(1),
  
 CONSTRAINT er_aktivC check(er_aktiv = 'T'or er_aktiv = 'F'),
 CONSTRAINT prosjekt_pk1 Primary Key(prosjektDeltagelseId),	 
 CONSTRAINT idUnique UNIQUE (ansattId, prosjektId),	
 FOREIGN KEY (ansattId) REFERENCES ansatt(ansattId),	
 FOREIGN key (prosjektID) references prosjekt(prosjektId)	
);


INSERT INTO
  ansatt(brukernavn, fornavn, etternavn, dato,
        stilling, maanedslonn,avdelingid)
        VALUES
    ('FRB', 'Frede', 'Berdal','11.11.1111','Geni',1000000,1),
    ( 'CHE', 'Christian', 'Evensen','11.12.1112','gutt',2000000,2),
    ('SIK', 'Simon', 'Kobbenes','12.12.1212','BOII',3000000,3),
    ('Mat', 'Matias', 'Vedeler','11.12.1212','chill',1000000,1),
    ( 'CHED', 'Patrick', 'Bruh','11.12.1112','ansatt',2000000,2),
    ('SIKe', 'Kubilay', 'Kygo','12.12.1212','grett',3000000,3),
    ('FRBt', 'Magnus', 'Test2','11.11.1111','noice',1000000,1),
    ('CHEq', 'Adolf', 'Hemit','11.12.1112','gutt',2000000,2),
    ('SIKs', 'Etkar', 'Lido','12.12.1212','utvikler',3000000,3),
    ('SIKv', 'Even', 'Felix','12.12.1212','Trentoget',3000000,3);

INSERT INTO 
prosjekt_ansatte(ansattId,prosjektId,rolle,arbeidstimer,er_aktiv)
VALUES
(1,1,'Leder',55,'T'),
(2,1,'Backend',89,'T'),
(3,1,'Økonomiansvarlig',101,'T'),
(4,2,'Frontend',36,'F'),
(5,2,'Backend',33,'T'),
(6,2,'Økonomiansvarlig',99,'T');


--Endrer på avdeling
ALTER TABLE avdeling
ADD FOREIGN KEY(sjef) references ansatt(ansattId);

--ALTER TABLE ansatt
--ADD FOREIGN KEY(prosjektId) REFERENCES prosjekt(prosjektId);