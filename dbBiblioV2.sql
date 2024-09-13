-- script db bibliotheque

CREATE TABLE document (
    id integer NOT NULL,
    titre character varying(255) NOT NULL,
    auteur character varying(255),
    datepublication date,
    nombredepages integer
);


CREATE TABLE journal_scientifique (
    domainerecherche character varying(255)
)
INHERITS (document);


CREATE TABLE livre (
    isbn character varying(50)
)
INHERITS (document);


CREATE TABLE magazine (
    numero integer
)
INHERITS (document);

CREATE TABLE these_universitaire (
    university character varying(255)
)
INHERITS (document);

CREATE TABLE utilisateur (
    id integer NOT NULL,
    name character varying(255),
    email character varying(255),
    age integer
);


CREATE TABLE etudiant (
    cne character varying(50)
)
INHERITS (utilisateur);

CREATE TABLE professeur (
    cin character varying(50)
)
INHERITS (utilisateur);


CREATE TABLE emprunt (
    id integer NOT NULL,
    document_id integer NOT NULL,
    utilisateur_id integer NOT NULL,
    date_emprunt date NOT NULL,
    statut VARCHAR(50) NOT NULL CHECK (statut IN ('disponible', 'emprunte'))
);


CREATE TABLE reservation (
    id integer NOT NULL,
    document_id integer NOT NULL,
    utilisateur_id integer NOT NULL,
    date_reservation date NOT NULL,
    statut VARCHAR(50) NOT NULL CHECK (statut IN ('reserve', 'annule'))
);

CREATE VIEW all_documents AS
SELECT id, titre, auteur, datePublication, nombreDePages, 'document' as doc_type FROM document
UNION ALL
SELECT id, titre, auteur, datePublication, nombreDePages, 'magazine' as doc_type FROM magazine
UNION ALL
SELECT id, titre, auteur, datePublication, nombreDePages, 'livre' as doc_type FROM livre
UNION ALL
SELECT id, titre, auteur, datePublication, nombreDePages, 'journal' as doc_type FROM journal_scientifique
UNION ALL
SELECT id, titre, auteur, datePublication, nombreDePages, 'these' as doc_type FROM these_universitaire;

CREATE OR REPLACE FUNCTION check_document_exists() RETURNS TRIGGER AS $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM all_documents WHERE id = NEW.document_id) THEN
    RAISE EXCEPTION 'Document with id % does not exist', NEW.document_id;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_document_exists() RETURNS TRIGGER AS $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM all_documents WHERE id = NEW.document_id) THEN
    RAISE EXCEPTION 'Document with id % does not exist', NEW.document_id;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_document_before_insert
BEFORE INSERT ON emprunt
FOR EACH ROW EXECUTE FUNCTION check_document_exists();

CREATE VIEW all_users AS
SELECT id, name, age, email, 'etudiant' AS user_type FROM etudiant
UNION ALL
SELECT id, name, age, email, 'professeur' AS user_type FROM professeur;

CREATE OR REPLACE FUNCTION check_user_exists() RETURNS TRIGGER AS $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM all_users WHERE id = NEW.user_id) THEN
    RAISE EXCEPTION 'User with id % does not exist', NEW.user_id;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_user_before_insert
BEFORE INSERT ON emprunt
FOR EACH ROW EXECUTE FUNCTION check_user_exists();

CREATE TRIGGER check_document_before_insert_reservation
BEFORE INSERT ON reservation
FOR EACH ROW EXECUTE FUNCTION check_document_exists();

CREATE TRIGGER check_user_before_insert_reservation
BEFORE INSERT ON reservation
FOR EACH ROW EXECUTE FUNCTION check_user_exists();

