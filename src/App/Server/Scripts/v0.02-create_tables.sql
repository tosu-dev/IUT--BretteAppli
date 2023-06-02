CREATE TABLE bretteapplidb.Subscriber (
    id INTEGER AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    CONSTRAINT PK_Suscriber PRIMARY KEY (id)
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

CREATE TABLE bretteapplidb.TypeCommand (
    id INT AUTO_INCREMENT,
    label VARCHAR(20) NOT NULL,
    CONSTRAINT PK_TypeCommand PRIMARY KEY (id)
);

CREATE TABLE bretteapplidb.Command (
    id INTEGER AUTO_INCREMENT,
    idSuscriber INTEGER NOT NULL,
    idDocument INTEGER NOT NULL,
    date Date NOT NULL,
    type INT NOT NULL,
    CONSTRAINT PK_Command PRIMARY KEY (id),
    CONSTRAINT FK_Command_idSuscriber FOREIGN KEY (idSuscriber) REFERENCES Subscriber(id),
    CONSTRAINT FK_Command_idDocument FOREIGN KEY (idDocument) REFERENCES Document(id),
    CONSTRAINT FK_Command_type FOREIGN KEY (type) REFERENCES TypeCommand(id),
    CONSTRAINT U_Command_idSuscriber_idDocument UNIQUE (idSuscriber, idDocument)
);

CREATE TABLE bretteapplidb.Dvd (
    id INTEGER,
    adult boolean,
    CONSTRAINT PK_Dvd PRIMARY KEY (id),
    CONSTRAINT FK_Dvd_id FOREIGN KEY (id) REFERENCES Document(id)
);