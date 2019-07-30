INSERT INTO "postgres".PARENT (IDPARENT, FIRSTNAME, LASTNAME, BIRTHDAY) 
VALUES (nextval('seq_parent'), 'Emerson', 'Braga', now());

INSERT INTO "postgres".INFANT (IDINFANT, FIRSTNAME, LASTNAME, BIRTHDAY, WEIGHT, IDPARENT, DEVICE) 
VALUES (NEXTVAL('seq_infant'), 'Emerson', 'Braga', NOW(), 70.0, CURRVAL('seq_parent'), 'ERFW-618R-51E6-CE1E-81CE');

INSERT INTO "postgres".USER (IDUSER, USERNAME, PASSWORD, IDPARENT) 
VALUES (nextval('seq_user'), '', '', currval('seq_parent'));

