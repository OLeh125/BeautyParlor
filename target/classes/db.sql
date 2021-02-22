DROP TABLE IF EXISTS b_master_additional_inf;
DROP TABLE IF EXISTS b_master_treatment;
DROP TABLE IF EXISTS b_review;
DROP TABLE IF EXISTS b_order;
DROP TABLE IF EXISTS b_treatment;
DROP TABLE IF EXISTS b_user;
DROP TYPE IF EXISTS urer_role;
DROP TYPE IF EXISTS order_status;


CREATE TYPE urer_role AS ENUM ('admin', 'client', 'master');
CREATE TYPE order_status AS ENUM('submitted','confirmed','paid','done','canceled');


CREATE TABLE b_user(
                       id SERIAL PRIMARY KEY,
                       en_name VARCHAR(100) NOT NULL ,
                       en_surname VARCHAR(100) NOT NULL,
                       ua_name VARCHAR(100) NOT NULL ,
                       ua_surname VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       u_role urer_role
);

CREATE TABLE b_treatment(
                            id SERIAL PRIMARY KEY,
                            en_name VARCHAR(100) NOT NULL UNIQUE,
                            ua_name VARCHAR(100) NOT NULL UNIQUE,
                            price INT NOT NULL
);

CREATE TABLE b_master_treatment(
                                        master_id INT NOT NULL REFERENCES b_user(id),
                                        treatment_id INT REFERENCES b_treatment(id)

);

CREATE TABLE b_master_additional_inf
(
    master_id INT NOT NULL REFERENCES b_user(id),
    rating NUMERIC DEFAULT 0.0
);

CREATE TABLE b_review(
                         id SERIAL PRIMARY KEY,
                         review_content VARCHAR(500) NOT NULL,
                         date_time  TIMESTAMP NOT NULL,
                         client_id INT REFERENCES b_user(id) NOT NULL
);



CREATE TABLE b_order(
                        id SERIAL PRIMARY KEY,
                        treatment_execution_date TIMESTAMP NOT NULL,
                        treatment_execution_time TIME NOT NULL,
                        status order_status,
                        request_feedback BOOLEAN DEFAULT false,
                        treatment_id INT REFERENCES b_treatment(id) NOT NULL,
                        client_id INT REFERENCES b_user(id) NOT NULL,
                        master_id INT REFERENCES b_user(id) NOT NULL

);


INSERT INTO b_user(en_name, en_surname,ua_name,ua_surname, email, password, u_role) VALUES
('Zina', 'Miller','Зіна', 'Міллер', 'zinaM@gmail', 'm1', 'master'),
('Brianna', 'Solis','Бріана', 'Соліс', 'briannaSO@gmail', 'm2', 'master'),
('Nina', 'Garza','Ніна', 'Гарза', 'ninaGAR@gmail', 'm3', 'master'),
('Charlotte', 'Ganzales','Шарлота', 'Ганзалес', 'charlotteGANZ@gmail', 'm4', 'master'),
('Layla', 'Baker','Лейла', 'Бейкер', 'laylaBAK@gmail', 'm5', 'master'),

('Aniela','Duke','Аніелла', 'Дюк','anielaDUk@gmail','c1','client'),
('Kelan','Kaur','Келен', 'Ко','kelanKAuR@gmail','c2','client'),
('Shola','Norman','Шола', 'Норман','sholaNorm@gmail','c3','client'),
('Harlan','Gilliam','Хален', 'Гілієм','harlanGil@gmail','c4','client'),
('Arif','Rooney','Аріф', 'Рунні','arifRonnf@gmail','c5','client'),

('admin','admin','admin','admin','artymyk.oleh@gmail.com','adminadmin','admin');



INSERT INTO b_treatment(en_name,ua_name, price) VALUES
('Manicure','Манікюр',200),
('Pedicure','Педікюр',400),
('Facials','Догляд за обличчям',250),
('Eyelash Tinting','Тонування вій',150),
('Eyebrow Tinting','Тонування брів',200),
('Hair Dyeing','Фарбування волосся',250),
('Haircut','Стрижка',200);


INSERT INTO  b_master_treatment (master_id,treatment_id) VALUES
(1,2),(1,3),(1,4),(1,5),(2,1),(2,3),(2,6),(2,7),(3,2),(3,3),(3,5),(3,7),
(4,1),(4,2),(4,7),(4,4),(5,1),(5,2);

INSERT INTO b_master_additional_inf(master_id, rating) VALUES (1,4.5),(2,4.3),(3,4.9),(4,4.8),(5,4.5);


INSERT INTO b_review(review_content,date_time,client_id) VALUES
('Love my new haircut. Thank you','2021-02-21 13:00',6),
('Had a great time everyone nice and friendly and love my new hair do','2021-02-21 15:00',7),
('First class treatments today with Zina Miller! From top to toe literally
- pedicure with gel polish, delighted with the results! Felt very safe and applaud the professionalism of all staff Beauty.','2021-02-21 19:00',8),
('Love my hair. Charlotte made a great job as usual.','2021-02-21 20:00',9),
('Absolutely in love with my new colour done by Layla. Brilliant service, as always!','2021-02-21 22:00',10);

