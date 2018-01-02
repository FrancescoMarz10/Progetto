CREATE SCHEMA IF NOT EXISTS tirocinio;

use tirocinio;


CREATE TABLE IF NOT EXISTS Gestore(
CF CHAR(16) NOT NULL,
Nome VARCHAR(50) NOT NULL,
Cognome VARCHAR(50) NOT NULL,
Username VARCHAR(30) NOT NULL,
Psw VARCHAR(30) NOT NULL,
Mail VARCHAR(50) NOT NULL,

PRIMARY KEY (CF)
);

CREATE TABLE IF NOT EXISTS utente(
Username CHAR(30) NOT NULL,
Psw CHAR(30) NOT NULL,
Mail VARCHAR(50) NOT NULL,
Ruolo VARCHAR(50) NOT NULL,
Gestore CHAR(16),

PRIMARY KEY (Username,Psw),
FOREIGN KEY (Gestore) references Gestore(CF) on delete cascade
);


CREATE TABLE IF NOT EXISTS azienda(
Nome VARCHAR(50) NOT NULL,
Username VARCHAR(20),
Psw VARCHAR(20),
NomeResponsabile VARCHAR(50) NOT NULL,
Sede VARCHAR(50) NOT NULL,

PRIMARY KEY (Nome),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade
);


CREATE TABLE IF NOT EXISTS tutorEsterno(
CF CHAR(16) NOT NULL,
Nome VARCHAR(30) NOT NULL,
Cognome VARCHAR(30) NOT NULL,
Username VARCHAR(30),
Psw VARCHAR(30),

PRIMARY KEY (CF),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade
);

CREATE TABLE IF NOT EXISTS tutorInterno(
CF CHAR(16) NOT NULL,
Nome VARCHAR(30) NOT NULL,
Cognome VARCHAR(30) NOT NULL,
Username VARCHAR(30),
Psw VARCHAR(30),

PRIMARY KEY (CF),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade
);

CREATE TABLE IF NOT EXISTS Presidente(
CF CHAR(16) NOT NULL,
Nome VARCHAR(30) NOT NULL,
Cognome VARCHAR(30) NOT NULL,
Username VARCHAR(30),
Psw VARCHAR(30),

PRIMARY KEY (CF),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade
);

CREATE TABLE IF NOT EXISTS ufficio(
Sigla VARCHAR(20) NOT NULL,
Username VARCHAR(30),
Psw VARCHAR(30),

PRIMARY KEY (Sigla),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade
);



CREATE TABLE IF NOT EXISTS offertaFormativa(
ID INT NOT NULL,
Nome VARCHAR(50) NOT NULL,
Sede VARCHAR(50) NOT NULL,
Tema VARCHAR(50) NOT NULL,
Obiettivi VARCHAR(50) NOT NULL,
ModalitaSvolgimento VARCHAR(100) NOT NULL,

TutorEsterno CHAR(16),
Azienda VARCHAR(50),

PRIMARY KEY (ID),
FOREIGN KEY (Azienda) references azienda(Nome) on delete cascade,
FOREIGN KEY (TutorEsterno) references tutorEsterno(CF) on delete cascade
);


CREATE TABLE IF NOT EXISTS Studente(
Matricola CHAR(10) NOT NULL,
Nome VARCHAR(50) NOT NULL,
Cognome VARCHAR(50) NOT NULL,
Username VARCHAR(20) NOT NULL,
Psw VARCHAR(20) NOT NULL,
TutorInterno CHAR(16),
OffertaFormativa INT,

PRIMARY KEY (Matricola),
FOREIGN KEY (Username, Psw) references utente(Username, Psw) on delete cascade,
FOREIGN KEY (TutorInterno) references tutorInterno(CF) on delete cascade,
FOREIGN KEY (OffertaFormativa) references offertaFormativa(ID) on delete cascade
);


CREATE TABLE IF NOT EXISTS Documento(
Nome VARCHAR(50) NOT NULL,

PRIMARY KEY (Nome)
);

CREATE TABLE IF NOT EXISTS Notifica(
ID INT NOT NULL,
Testo VARCHAR (100)NOT NULL,
Tipo VARCHAR(50) NOT NULL,

ufficio VARCHAR(20),
TutorInterno CHAR(16),
TutorEsterno CHAR(16),
Azienda VARCHAR(50),
Studente CHAR(10),

PRIMARY KEY (ID),
FOREIGN KEY (ufficio) references ufficio(Sigla) on delete cascade,
FOREIGN KEY (TutorInterno) references tutorInterno(CF) on delete cascade,
FOREIGN KEY (TutorEsterno) references tutorEsterno(CF) on delete cascade,
FOREIGN KEY (Azienda) references azienda(Nome) on delete cascade,
FOREIGN KEY (Studente) references Studente(Matricola) on delete cascade
);



CREATE TABLE IF NOT EXISTS tirocinio(
Codice INT NOT NULL,
Materia VARCHAR(50) NOT NULL,
Periodo VARCHAR(50) NOT NULL,
Luogo VARCHAR(50) NOT NULL,

TutorInterno CHAR(16),
TutorEsterno CHAR(16),
Studente CHAR(10),
Azienda VARCHAR(50),
Presidente CHAR(16),
Documento VARCHAR(50),

PRIMARY KEY (Codice),
FOREIGN KEY (TutorInterno) references tutorInterno(CF) on delete cascade,
FOREIGN KEY (TutorEsterno) references tutorEsterno(CF) on delete cascade,
FOREIGN KEY (Studente) references Studente(Matricola) on delete cascade,
FOREIGN KEY (Azienda) references azienda(Nome) on delete cascade,
FOREIGN KEY (Presidente) references Presidente(CF) on delete cascade,
FOREIGN KEY (Documento) references Documento(Nome) on delete cascade
);

LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileGestore.txt' INTO TABLE Gestore FIELDS terminated by ',' LINES terminated by '\n' (CF,Nome,Cognome,Username,Psw,Mail);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileUtente.txt' INTO TABLE utente FIELDS terminated by ',' LINES terminated by '\n' (Username,Psw,Mail,Ruolo,Gestore);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileAzienda.txt' INTO TABLE azienda FIELDS terminated by ',' LINES terminated by '\n' (Nome,Username,Psw,NomeResponsabile,Sede);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileTutorEsterno.txt' INTO TABLE tutoresterno FIELDS terminated by ',' LINES terminated by '\n' (Nome,Cognome,Username,Psw,CF);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileTutorInterno.txt' INTO TABLE tutorinterno FIELDS terminated by ',' LINES terminated by '\n' (Nome,Cognome,Username,Psw,CF);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileStudente.txt' INTO TABLE Studente FIELDS terminated by ',' LINES terminated by '\n' (Matricola,Nome,Cognome,Username,Psw);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileOffertaFormativa.txt' INTO TABLE  offertaformativa FIELDS terminated by ',' LINES terminated by '\n' (ID,Nome,Sede,Tema,Obiettivi,ModalitaSvolgimento,Azienda,TutorEsterno);

UPDATE studente SET TutorInterno='VNANLL95L11H931K' WHERE studente.Matricola='0512103549';
UPDATE studente SET OffertaFormativa='002' WHERE studente.Matricola='0512103549';
INSERT INTO offertaformativa(ID,Nome,Sede,Tema,Obiettivi,ModalitaSvolgimento) VALUES (-1,'rifiuto','rifiuto','rifiuto','rifiuto','rifiuto');

LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/fileUfficio.txt' INTO TABLE  ufficio FIELDS terminated by ',' LINES terminated by '\n' (Sigla,Username,Psw);
LOAD DATA LOCAL INFILE 'C:/Users/utente/workspace/Sostage/db/filePopolazione/filePresidente.txt' INTO TABLE  presidente FIELDS terminated by ',' LINES terminated by '\n' (Nome,Cognome,Username,Psw,CF);





