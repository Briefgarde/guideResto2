INSERT INTO TYPES_GASTRONOMIQUES(libelle, description) VALUES ('Cuisine suisse', 'Cuisine classique et plats typiquement suisses');
INSERT INTO TYPES_GASTRONOMIQUES(libelle, description) VALUES ('Restaurant gastronomique', 'Restaurant gastronomique de haut standing');
INSERT INTO TYPES_GASTRONOMIQUES(libelle, description) VALUES ('Pizzeria', 'Pizzas et autres spécialités italiennes');
COMMIT;

INSERT INTO CRITERES_EVALUATION(nom, description) VALUES ('Service', 'Qualité du service');
INSERT INTO CRITERES_EVALUATION(nom, description) VALUES ('Cuisine', 'Qualité de la nourriture');
INSERT INTO CRITERES_EVALUATION(nom, description) VALUES ('Cadre', 'L''ambiance et la décoration sont-elles bonnes ?');
COMMIT;

INSERT INTO VILLES(code_postal, nom_ville) VALUES ('2000', 'Neuchâtel');
INSERT INTO VILLES(code_postal, nom_ville) VALUES ('5000', 'NeoBienne');
COMMIT;

INSERT INTO RESTAURANTS(nom, adresse, description, site_web, fk_type, fk_vill) VALUES ('Fleur-de-Lys', 'Rue du Bassin 10', 'Pizzeria au centre de Neuchâtel', 'http://www.pizzeria-neuchatel.ch', 3, 1);
INSERT INTO RESTAURANTS(nom, adresse, description, site_web, fk_type, fk_vill) VALUES ('La Maison du Prussien', 'Rue des Tunnels 11', 'Restaurant gastronomique renommé de Neuchâtel', 'www.hotel-prussien.ch', 2, 1);
COMMIT;

--TO DO : EVAL
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
INSERT INTO EVALUATIONS (date_eval, fk_rest) VALUES (sysdate, 1);
COMMIT;
--TO DO : BASIC EVAL

INSERT INTO BASICEVAL(fk_eval, isLiked, address_ip) VALUES (1, 'T', '1.2.3.4');
INSERT INTO BASICEVAL(fk_eval, isLiked, address_ip) VALUES (2, 'T', '1.2.3.5');
INSERT INTO BASICEVAL(fk_eval, isLiked, address_ip) VALUES (3, 'T', '1.2.3.6');
COMMIT;


--TO DO : CCOMPLETE EVAL

INSERT INTO COMPLETE_EVAL(fk_eval, commentaire, date_eval, username) VALUES (4, 'FUCK THIS PROGRAM', sysdate, 'TI');
INSERT INTO COMPLETE_EVAL(fk_eval, commentaire, date_eval, username) VALUES (5, 'FUCK2 THIS PROGRAM', sysdate, 'TITI');
INSERT INTO COMPLETE_EVAL(fk_eval, commentaire, date_eval, username) VALUES (6, 'FUCK3 THIS PROGRAM', sysdate, 'TITITI');
COMMIT;

INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (4, 10, 1);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (5, 10, 2);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (4, 10, 3);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (4, 11, 1);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (4, 11, 2);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (4, 11, 3);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (5, 12, 1);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (5, 12, 2);
INSERT INTO NOTES(note, fk_eval, fk_crit) VALUES (5, 12, 3);
COMMIT;






