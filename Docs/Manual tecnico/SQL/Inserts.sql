USE `Magazine_Web`;

-- CATEGORIES
INSERT INTO `Category` (`name`) VALUES 
	('deportes'), ('musica'), ('comida'),('viajes'),('lenguaje'),('literatura'),
    ('videojuegos'), ('juegos de mesa'), ('naturaleza'),('tecnologia'),('ciencia'),('biologia'),
    ('filosofia'), ('programacion'),('computacion'),('machine learning'),('Inteligencia artificial'); 
    
-- MAGAZINE TAGS
INSERT INTO `Tag` (`name`) VALUES ('futbol'),
	('basquetbol'),('acustico'),('cantantes'),('letras'),('reposteria'),('comida rapda'),('comida local'),('autos'),
    ('vuelos'),('aerolineas'),('lectura'),('uso de palabras'),('ofertas'),('juegos'),('chess'),('tableros'),
    ('roles'),('plantas'),('animales'),('avances tecnologicos'),('computadores'),('taxonomia'),('sistemas y ecosistemas'),('pensamientro critico'),
    ('reflexión'),('lenguajes de programacion'),('automatas'),('parsers'),('redes neuronales'),('nodos'),('auto aprendizaje computarizado'),
    ('automatizacion de procesos'),('programas evolutivos');
    
--  MAGAZINE COST
INSERT INTO `Company_Fee` (`percentaje`) VALUES ('55');

-- INSERT READER
INSERT INTO `Reader` (`email`, `name`, `password`,`imgPath`) VALUES 
	('reader@reader.com', 'Jefe mayoneso', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader@reader.com'),
	('reader1@reader.com', 'Pepe Antonio Matiaz', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader1@reader.com'),
    ('reader2@reader.com', 'Pepe el pollo', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader@reader2.com'),
    ('reader3@reader.com', 'Hasbullita', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader@reader3.com'),
    ('reader4@reader.com', 'Mander 4 a pocket', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader4@reader.com'),
    ('reader5@reader.com', 'Splinter Raton blanco', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader5@reader.com'),
    ('reader6@reader.com', 'Fredboat musica bot', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader6@reader.com'),
    ('reader7@reader.com', 'JockieMusic bot discord', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader7@reader.com'),
    ('reader8@reader.com', 'JockieMusic bot discord 1', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader8@reader.com'),
    ('reader9@reader.com', 'JockieMusic bot discord 2', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader9@reader.com'),
    ('reader10@reader.com', 'Actualizacione bot', 'lu5O4QoVolc16kN0ffGenw==','FilesMagPage/img/profile/reader/reader10@reader.com'),
    ('reader11@reader.com', 'Apollo', 'lu5O4QoVolc16kN0ffGenw==', 'FilesMagPage/img/profile/reader/reader10@reader.com');

-- INSERT READER INTREST CATEGORIES
INSERT INTO `User_Intrest_Categories` (`reader`, `category`) VALUES 
	    ('reader@reader.com', 'deportes'),('reader@reader.com', 'biologia'),('reader@reader.com', 'videojuegos'),('reader1@reader.com', 'comida'),('reader1@reader.com', 'programacion'),
        ('reader1@reader.com', 'ciencia'),('reader1@reader.com', 'videojuegos'),('reader1@reader.com', 'juegos de mesa'),('reader2@reader.com', 'viajes'),('reader2@reader.com', 'musica'),
        ('reader2@reader.com', 'naturaleza'),('reader3@reader.com', 'lenguaje'),('reader3@reader.com', 'literatura'),('reader3@reader.com', 'tecnologia'),('reader4@reader.com', 'literatura'),
        ('reader4@reader.com', 'computacion'),('reader4@reader.com', 'naturaleza'),('reader5@reader.com', 'videojuegos'),('reader5@reader.com', 'ciencia'),('reader6@reader.com', 'juegos de mesa'),
        ('reader6@reader.com', 'machine learning'),('reader7@reader.com', 'naturaleza'),('reader7@reader.com', 'Inteligencia artificial'),('reader8@reader.com', 'tecnologia'),('reader8@reader.com', 'filosofia'),
        ('reader8@reader.com', 'ciencia'),('reader9@reader.com', 'biologia'),('reader9@reader.com', 'programacion'),('reader9@reader.com', 'filosofia'),('reader10@reader.com', 'filosofia'),
        ('reader10@reader.com', 'viajes'),('reader11@reader.com', 'ciencia'),('reader11@reader.com', 'biologia'),('reader11@reader.com', 'naturaleza');

-- INSERT ADMIN
INSERT INTO `Admin` (`email`, `password`, `name`) VALUES 
	('admin@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Myke Wasousky'),
	('admin1@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Papirico'),
    ('admin2@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Say-tama'),
    ('admin3@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Sideral'),
    ('admin4@admin.com', 'lu5O4QoVolc16kN0ffGenw==', '</scorp>'),
    ('admin5@admin.com', 'lu5O4QoVolc16kN0ffGenw==', '_joelenriquez'),
    ('admin6@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Achess'),
    ('admin7@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'Ague'),
    ('admin8@admin.com', 'lu5O4QoVolc16kN0ffGenw==', 'alejosama16');    

-- INSERT EDITOR  
INSERT INTO `Editor` (`email`, `name`, `password`, `description`,`imgPath`) VALUES 
	('editor@editor.com', 'Roberto ruperto ', 'lu5O4QoVolc16kN0ffGenw==', 'Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor@editor.com'),
    ('editor1@editor.com', 'PartyBeast', 'lu5O4QoVolc16kN0ffGenw==','Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor1@editor.com'),
    ('editor2@editor.com', 'Aguare chems', 'lu5O4QoVolc16kN0ffGenw==','Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor2@editor.com'),
    ('editor3@editor.com', 'AWPingu red hot chilli', 'lu5O4QoVolc16kN0ffGenw==','Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor3@editor.com'),
    ('editor4@editor.com', 'Cotorro legendario podcast', 'lu5O4QoVolc16kN0ffGenw==','Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor4@editor.com'),
    ('editor5@editor.com', 'DanyKiller', 'lu5O4QoVolc16kN0ffGenw==','Soy el editor mas perron del mundo','FilesMagPage/img/profile/editor/editor5@editor.com');

-- INSERT MAGAZINES
INSERT INTO `Magazine` (`name`, `subscription_fee`, `company_fee`, `cost_per_day`, `creation_date`,`description`, `allow_comment`, `allow_likes`, `category`, `editor`, `approved`) VALUES 
	('Tlacuaches Ingeniebrios', 145, 55, 3.67, '2015-10-20', 
        'Esta es la revista mas chida que vas a ver, hablaremos sobre muchas cosas respecto a juegos y como dejar de ser manco', 
        1, 1,'videojuegos', 'editor@editor.com',  1),
    ('QuieroComer', 131.12, 55, 0, '2017-09-12', 
        'Hablemos un poco sobre la comida y por que es tan divertido comer y juntos cocinemos cosas increibles', 
        1, 1,'comida', 'editor1@editor.com',  0),
	('Qver', 110, 55, 0, '2019-01-02', 
        'Exploremos las distintas cosas que la tierra tiene para mostrarnos', 
        0, 1,'ciencia', 'editor2@editor.com',  0),
	('Green paradise', 131.12, 55, 0, '2017-09-12', 
        'La naturaleza tiene muchas cosas que mostrarnos, observemos todo lo que tiene para mostrarnos', 
        1, 0,'naturaleza', 'editor3@editor.com',  0),
	('Los tomatines', 100, 55, 0, '2020-03-01', 
        'Te has preguntado lo que realmente venimos a hacer al mundo, estas dudas y mas son las que veremos aqui.', 
        0, 0,'filosofia', 'editor4@editor.com',  1),
	('EL valor de la coma', 90, 55, 0, '2020-12-07', 
        'Es importante que las personas aprendan sobre el lenguaje, en "El valor de la coma" queremos que aprendas mas sobre el lenguaje', 
        1, 0,'lenguaje', 'editor5@editor.com',  1),
	('Un sol sin fa', 175, 55, 0, '2021-01-01', 
        'Conoce mas sobre como funciona el mundo de la musica', 
        1, 1,'musica', 'editor2@editor.com',  1),
	('El mi molido', 97.12, 55, 0, '2018-09-12', 
        'Enterate de lo que los artistas piensan respecto a distintas cosas', 
        1, 1,'musica', 'editor@editor.com',  0),
	('Explorando el mar', 131.12, 55, 0, '2017-09-12', 
        'El mar es uno de los lugares menos explorados, lee sobre lo que sabemos del mar', 
        1, 1,'biologia', 'editor2@editor.com',  0);

-- MAGAZINE TAGS
INSERT INTO `Magazine_Tag` (`magazine`, `tag`) VALUES 
    ('Tlacuaches Ingeniebrios','chess'),('Tlacuaches Ingeniebrios','tableros'),('Tlacuaches Ingeniebrios','ofertas'),('Tlacuaches Ingeniebrios','juegos'),('QuieroComer','reposteria'),
    ('QuieroComer','comida rapda'),('QuieroComer','comida local'),('Qver','autos'),('Qver','vuelos'),('Qver','roles'),('Qver','aerolineas'),('Green paradise','plantas'),
    ('Green paradise','animales'),('Los tomatines','lectura'),('Los tomatines','uso de palabras'),('EL valor de la coma','uso de palabras'),('EL valor de la coma','lectura'),
    ('Un sol sin fa','acustico'),('Un sol sin fa','cantantes'),('Un sol sin fa','letras'),('El mi molido','acustico'),('El mi molido','cantantes'),
    ('El mi molido','letras'),('Explorando el mar','taxonomia'),('Explorando el mar','sistemas y ecosistemas'),('Explorando el mar','pensamientro critico');

-- INSERT MAGAZINE POST

INSERT INTO `Post` (`name`, `date`, `pdf`, `magazine`) VALUES 
    ('Tlacuaches tomo 1', '2015-10-23', 'FilesMagPage/Posts/1', 'Tlacuaches Ingeniebrios'),
    ('Tlacuaches tomo 2', '2017-11-12', 'FilesMagPage/Posts/2', 'Tlacuaches Ingeniebrios'),
    ('Tlacucahes tomo 3', '2018-06-03', 'FilesMagPage/Posts/3', 'Tlacuaches Ingeniebrios'),
    ('Tlacucaches tomo 4', '2021-04-27', 'FilesMagPage/Posts/4', 'Tlacuaches Ingeniebrios'),
    ('Los tomatines tomo 1', '2020-05-12', 'FilesMagPage/Posts/5', 'Los tomatines'),
    ('El valor de la coma tomo 1', '2020-12-18', 'FilesMagPage/Posts/6', 'EL valor de la coma'),
    ('El valor de la coma tomo 2', '2021-03-01', 'FilesMagPage/Posts/7', 'EL valor de la coma'),
    ('Un sol sin fa 0', '2021-02-12', 'FilesMagPage/Posts/8', 'Un sol sin fa'),
    ('Un sol sin fa 1', '2021-07-09', 'FilesMagPage/Posts/9', 'Un sol sin fa');

-- USER MAGAZINE TAGS INTREST
INSERT INTO `Reader_Magazine_Tag` (`reader`, `tag`) VALUES 
	('reader@reader.com', 'juegos'),
    ('reader@reader.com', 'roles'),
    ('reader1@reader.com', 'vuelos'),
    ('reader1@reader.com', 'reposteria'),
    ('reader2@reader.com', 'computadores'),
    ('reader4@reader.com', 'taxonomia'),
    ('reader5@reader.com', 'plantas'),
    ('reader7@reader.com', 'animales');


--  INSERT ADVERTISER
INSERT INTO `Advertiser` (`name`) VALUES 
    ('Pepito'),('AguareChems'),('Ague'),('TS21'),('Mogo'),('Canche');

-- INSERT AD
INSERT INTO `Ad` (`advertiser_paid`, `expiration_date`, `start_date`, `shown_counter`, `type`, `advertiser`, `shown_url`, `video_url`, `img_local_path`, `text`) VALUES 
    -- TEST ADS
    (745.12, '2022-12-01', '2021-01-01', 12, 1, 'Pepito', '', null, null, 'Hola soy pepito y he pagado para que veas este anuncio de texto :D'),
    (1011.82, '2019-01-21', '2018-08-01', 120, 1, 'AguareChems', '', null, null, 'Este es un anucnio de texto que no deberia mostrarse, esta expirado'),
    (4645.73, '2021-07-15', '2018-06-12', 981, 1, 'TS21', '', null, null, 'UN tercer anuncio de texto que esta bien muamadisimo'),
    -- INSERT IMG AD
    (5145.35, '2021-02-05', '2020-05-21', 231, 2, 'Mogo', '',null,'FilesMagPage/img/ad/4','Observa al poderosisimo hasbulla'),
    (10865.12, '2022-07-05', '2020-12-28', 1231, 2, 'Ague','',null, 'FilesMagPage/img/ad/5','Esto no se que es, tal vez ponga un meme'),
    -- INSERT VIDEO AD
    (2013.98, '2021-12-19', '2020-12-28', 1231, 3, 'Canche', '','https://www.youtube.com/watch?v=YOXUNjLnpHo', null,'Esto no se que es, tal vez ponga un meme'),
    (8123.41, '2020-07-05', '2014-12-28', 1231, 3, 'AguareChems','', 'https://www.youtube.com/watch?v=R7hTUxzbH48', null, 'Esto no se que es, tal vez ponga un meme');

-- INSERT AD TAG
INSERT INTO `Ad_Tag` (`tag`, `ad`) VALUES 
    ('basquetbol', 1),('acustico', 1),('cantantes', 1),('letras', 1),('juegos', 1),
    ('reflexión', 2),('automatas', 2),('programas evolutivos', 2),('computadores', 2),
    ('vuelos', 3),('ofertas', 3),
    ('comida rapda', 4),('tableros', 4),
    ('avances tecnologicos', 5),
    ('computadores', 6),('lectura', 6),
    ('cantantes', 7),('animales', 7),('computadores', 7),('lectura', 7);

-- INSERT likes

INSERT INTO `Like` (`date`, `magazine`, `user`) VALUES 
    ('2016-02-08', 'Tlacuaches Ingeniebrios', 'reader@reader.com'),
    ('2017-02-21', 'Tlacuaches Ingeniebrios', 'reader8@reader.com'),
    ('2017-04-28', 'Tlacuaches Ingeniebrios', 'reader2@reader.com'),
    ('2019-04-17', 'Tlacuaches Ingeniebrios', 'reader9@reader.com'),
    ('2019-04-25', 'Tlacuaches Ingeniebrios', 'reader7@reader.com'),
    ('2019-06-12', 'Tlacuaches Ingeniebrios', 'reader1@reader.com'),
    ('2020-04-21', 'Tlacuaches Ingeniebrios', 'reader6@reader.com'),
    ('2020-10-16', 'Tlacuaches Ingeniebrios', 'reader3@reader.com'),
    ('2020-12-19', 'EL valor de la coma', 'reader1@reader.com'),
    ('2020-12-30', 'EL valor de la coma', 'reader4@reader.com'),
    ('2021-01-21', 'Un sol sin fa', 'reader10@reader.com'),
    ('2021-02-15', 'Un sol sin fa', 'reader7@reader.com'),
    ('2021-05-16', 'EL valor de la coma', 'reader6@reader.com'),
    ('2021-10-08', 'EL valor de la coma', 'reader2@reader.com'),
    ('2021-10-10', 'Tlacuaches Ingeniebrios', 'reader5@reader.com'),
    ('2021-10-11', 'Tlacuaches Ingeniebrios', 'reader4@reader.com');

-- INSERT COMMENt
INSERT INTO `Comment` (`date`, `text`, `user`, `magazine`) VALUES 
    ('2019-06-12','Me gusta mucho el contenido', 'reader1@reader.com', 'Tlacuaches Ingeniebrios'),
    ('2017-02-21','Pfff, al prinicipio una chorrada pero terminò gustandome el contendio', 'reader1@reader.com', 'Tlacuaches Ingeniebrios'),
    ('2017-04-28','Deberia mejorar en redacción', 'reader2@reader.com', 'Tlacuaches Ingeniebrios'),
    ('2020-10-16','Sin palabaras, magnifico', 'reader3@reader.com', 'Tlacuaches Ingeniebrios'),
    ('2021-02-15','Mmmmm, podria ser mejor, esta bien pero no es la mejor revista', 'reader7@reader.com', 'Un sol sin fa'),
    ('2021-01-21','Me gusta la manera en que escribe', 'reader10@reader.com', 'Un sol sin fa');

-- INSERT SUBSCRIPTION
INSERT INTO `Subscription` (`months`, `expiration_date`, `acquisition_date`, `magazine`, `reader`) VALUES 
    ('12', '2017-02-05', '2016-02-05', 'Tlacuaches Ingeniebrios', 'reader@reader.com'),
    ('1', '2017-03-20', '2017-02-20', 'Tlacuaches Ingeniebrios', 'reader8@reader.com'),
    ('1', '2017-05-28', '2017-04-28', 'Tlacuaches Ingeniebrios', 'reader2@reader.com'),
    ('1', '2019-05-17', '2019-04-17', 'Tlacuaches Ingeniebrios', 'reader9@reader.com'),
    ('1', '2019-05-25', '2019-04-25', 'Tlacuaches Ingeniebrios', 'reader7@reader.com'),
    ('12', '2020-06-10', '2019-06-10', 'Tlacuaches Ingeniebrios', 'reader1@reader.com'),
    ('12', '2021-04-21', '2020-04-21', 'Tlacuaches Ingeniebrios', 'reader6@reader.com'),
    ('12', '2021-09-26', '2020-09-26', 'Tlacuaches Ingeniebrios', 'reader@reader.com'),
    ('12', '2021-10-16', '2020-10-16', 'Tlacuaches Ingeniebrios', 'reader3@reader.com'),
    ('12', '2021-12-19', '2020-12-19', 'EL valor de la coma', 'reader1@reader.com'),
    ('1', '2021-01-30', '2020-12-30', 'EL valor de la coma', 'reader4@reader.com'),
    ('1', '2021-02-21', '2021-01-21', 'Un sol sin fa', 'reader10@reader.com'),
    ('12', '2022-02-15', '2021-02-15', 'Un sol sin fa', 'reader7@reader.com'),
    ('1', '2021-06-16', '2021-05-16', 'EL valor de la coma', 'reader6@reader.com'),
    ('1', '2021-11-08', '2021-10-08', 'EL valor de la coma', 'reader2@reader.com'),
    ('12', '2022-10-10', '2021-10-10', 'Tlacuaches Ingeniebrios', 'reader5@reader.com'),
    ('12', '2022-10-11', '2021-10-11', 'Tlacuaches Ingeniebrios', 'reader4@reader.com');

-- INSERT PAYMENT
INSERT INTO `Payment` (`subscription`, `editor_fee`, `company_fee`) VALUES 
    (1, 65.25, 79.75),(2, 65.25, 79.75),(3, 65.25, 79.75),(4, 65.25, 79.75),
    (5, 65.25, 79.75),(6, 65.25, 79.75),(7, 65.25, 79.75),(8, 65.25, 79.75),
    (9, 65.25, 79.75),(10, 40.5, 49.5),(11, 40.5, 49.5),(12, 78.75, 96.25),
    (13, 78.75, 96.25),(14, 40.5, 49.5),(15, 40.5, 49.5),(16, 65.25, 79.75),
    (17, 65.25, 79.75);

    -- TO INSERT
-- mag cost changelogs * NOT IMPLEMENTED
-- reader magazine tag