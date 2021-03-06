CREATE TABLE healthmonitoringapi.parent (
	idparent int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	firstname varchar(255) NULL,
	lastname varchar(255) NULL,
	phone varchar(255) NULL,
	userid varchar(255) NULL,
	CONSTRAINT parent_pkey PRIMARY KEY (idparent)
);

CREATE TABLE healthmonitoringapi."user" (
	iduser int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	idparent int4 NULL,
	CONSTRAINT user_pkey PRIMARY KEY (iduser)
);

ALTER TABLE healthmonitoringapi."user" ADD CONSTRAINT fk_user_parent FOREIGN KEY (idparent) REFERENCES healthmonitoringapi.parent(idparent);


CREATE TABLE healthmonitoringapi.infant (
	idinfant int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	birthday date NULL,
	device varchar(255) NULL,
	firstname varchar(255) NULL,
	lastname varchar(255) NULL,
	weight numeric(19,2) NULL,
	idparent int4 NULL,
	CONSTRAINT infant_pkey PRIMARY KEY (idinfant)
);

ALTER TABLE healthmonitoringapi.infant ADD CONSTRAINT fk_infant_parent FOREIGN KEY (idparent) REFERENCES healthmonitoringapi.parent(idparent);


CREATE TABLE healthmonitoringapi.address (
	idaddress int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	city varchar(255) NULL,
	description varchar(255) NULL,
	district varchar(255) NULL,
	latitude numeric(19,2) NULL,
	longitude numeric(19,2) NULL,
	"number" varchar(255) NULL,
	state varchar(255) NULL,
	street varchar(255) NULL,
	zipcode varchar(255) NULL,
	idinfant int4 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (idaddress)
);

ALTER TABLE healthmonitoringapi.address ADD CONSTRAINT fk_address_infant FOREIGN KEY (idinfant) REFERENCES healthmonitoringapi.infant(idinfant);

