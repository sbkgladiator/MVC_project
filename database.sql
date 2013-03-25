-- Database: siw

-- DROP DATABASE siw;

CREATE DATABASE siw
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       LC_COLLATE = 'it_IT.UTF-8'
       LC_CTYPE = 'it_IT.UTF-8'
       CONNECTION LIMIT = -1;

-- Schema: "public"

-- DROP SCHEMA public;

CREATE SCHEMA public
  AUTHORIZATION postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public IS 'standard public schema';


-- Sequence: id_postgres

-- DROP SEQUENCE id_postgres;

CREATE SEQUENCE id_postgres
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999
  START 1
  CACHE 1
  NO CYCLE;
ALTER TABLE id_postgres OWNER TO postgres;


-- Table: clienti

-- DROP TABLE clienti;

CREATE TABLE clienti
(
  id integer NOT NULL,
  nome text,
  partitaiva text NOT NULL,
  indirizzo text,
  CONSTRAINT clienti_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE clienti OWNER TO postgres;


-- Table: fornitori

-- DROP TABLE fornitori;

CREATE TABLE fornitori
(
  id integer NOT NULL,
  nome text NOT NULL,
  indirizzo text,
  telefono text,
  CONSTRAINT fornitori_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fornitori OWNER TO postgres;


-- Table: forniture

-- DROP TABLE forniture;

CREATE TABLE forniture
(
  fornitore integer NOT NULL,
  prodotto integer NOT NULL,
  CONSTRAINT forniture_pkey PRIMARY KEY (fornitore, prodotto),
  CONSTRAINT forniture_fornitore_fkey FOREIGN KEY (fornitore)
      REFERENCES fornitori (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT forniture_prodotto_fkey FOREIGN KEY (prodotto)
      REFERENCES prodotti (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forniture OWNER TO postgres;


-- Table: ordini

-- DROP TABLE ordini;

CREATE TABLE ordini
(
  data date NOT NULL,
  stato text NOT NULL,
  cliente integer,
  id integer NOT NULL,
  codice text,
  CONSTRAINT id PRIMARY KEY (id),
  CONSTRAINT ordini_cliente_fkey FOREIGN KEY (cliente)
      REFERENCES clienti (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordini OWNER TO postgres;


-- Table: prodotti

-- DROP TABLE prodotti;

CREATE TABLE prodotti
(
  codice text NOT NULL,
  nome text,
  descrizione text,
  costo integer,
  id integer NOT NULL,
  disponibilita integer,
  CONSTRAINT prodotti_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE prodotti OWNER TO postgres;


-- Table: righeordini

-- DROP TABLE righeordini;

CREATE TABLE righeordini
(
  id integer NOT NULL,
  ordine integer NOT NULL,
  prodotto integer NOT NULL,
  numriga integer NOT NULL,
  quantita integer NOT NULL,
  CONSTRAINT righeordini_pkey PRIMARY KEY (id),
  CONSTRAINT righeordini_ordine_fkey FOREIGN KEY (ordine)
      REFERENCES ordini (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT righeordini_prodotto_fkey FOREIGN KEY (prodotto)
      REFERENCES prodotti (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE righeordini OWNER TO postgres;


-- Table: utenze

-- DROP TABLE utenze;

CREATE TABLE utenze
(
  id integer,
  utente text NOT NULL,
  isadmin boolean DEFAULT false,
  pwd text NOT NULL,
  CONSTRAINT utenze_pkey PRIMARY KEY (utente),
  CONSTRAINT utenze_id_fkey FOREIGN KEY (id)
      REFERENCES clienti (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE utenze OWNER TO postgres;


