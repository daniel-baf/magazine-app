-- SQL USER
-- DROP USER IF EXISTS 'ipc2'@'localhost';
-- CREATE USER 'ipc2'@'localhost' IDENTIFIED BY 'ipc2+contraPjct0s';
-- GRANT ALL PRIVILEGES ON `Magazine_Web`.* TO 'ipc2'@'localhost';
-- FLUSH PRIVILEGES;

-- INSERT READER
INSERT INTO `Magazine_Web`.`Reader` (`email`, `name`, `password`) VALUES ('reader@reader.com', 'Sarai Jrz', '123');
-- INSERT ADMIN
INSERT INTO `Magazine_Web`.`Admin` (`email`, `password`, `name`) VALUES ('admin@admin.com', '123', 'Myke Wasousky');
-- INSERT EDITOR  
INSERT INTO `Magazine_Web`.`Editor` (`email`, `name`, `password`, `description`) VALUES ('editor@editor.com', 'Roberto ruperto ', '123', 'Soy el editor mas perron del mundo');
-- CATEGORIES
INSERT INTO `Magazine_Web`.`Category` (`name`) VALUES ('deportes'), ('musica'), ('comida'),('viajes'),('lenguaje'),('literatura'); 
--  MAGAZINE COST
INSERT INTO `Magazine_Web`.`Company_Fee` (`percentaje`) VALUES ('55');

