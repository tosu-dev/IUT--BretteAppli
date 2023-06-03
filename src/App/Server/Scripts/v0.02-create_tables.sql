CREATE TABLE bretteapplidb.Subscriber (
    id INTEGER AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    isBanned INT(1) DEFAULT 0,
    bannedDate DATETIME,
    CONSTRAINT PK_Subscriber PRIMARY KEY (id)
);

CREATE TABLE bretteapplidb.TypeDocumentState (
    id INT AUTO_INCREMENT,
    label VARCHAR(20) NOT NULL,
    CONSTRAINT PK_TypeDocumentState PRIMARY KEY (id)
);

CREATE TABLE bretteapplidb.Document (
    id INTEGER AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    state INT NOT NULL,
    CONSTRAINT PK_Document PRIMARY KEY (id),
    CONSTRAINT FK_Document_state FOREIGN KEY (state) REFERENCES TypeDocumentState(id)
);

CREATE TABLE bretteapplidb.Command (
    id INTEGER AUTO_INCREMENT,
    idSubscriber INTEGER NOT NULL,
    idDocument INTEGER NOT NULL,
    date DATETIME NOT NULL,
    CONSTRAINT PK_Command PRIMARY KEY (id),
    CONSTRAINT FK_Command_idSubscriber FOREIGN KEY (idSubscriber) REFERENCES Subscriber(id),
    CONSTRAINT FK_Command_idDocument FOREIGN KEY (idDocument) REFERENCES Document(id),
    CONSTRAINT U_Command_idSubscriber_idDocument UNIQUE (idSubscriber, idDocument)
);

CREATE TABLE bretteapplidb.Dvd (
    id INTEGER,
    adult boolean,
    CONSTRAINT PK_Dvd PRIMARY KEY (id),
    CONSTRAINT FK_Dvd_id FOREIGN KEY (id) REFERENCES Document(id)
);