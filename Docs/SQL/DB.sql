DROP SCHEMA IF EXISTS `Magazine_Web`;
CREATE SCHEMA `Magazine_Web` ;

USE `Magazine_Web`;

-- USERS

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`));

DROP TABLE IF EXISTS `Reader`;
CREATE TABLE `Reader` (
  `email` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`email`));

DROP TABLE IF EXISTS `Editor`;

CREATE TABLE `Editor` (
  `email` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `description` LONGTEXT NULL,
  `imgPath` VARCHAR(700) NULL DEFAULT NULL,
  PRIMARY KEY (`email`));
  
DROP TABLE IF EXISTS `Advertiser`;
CREATE TABLE `Advertiser` (
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`));
 
-- ATRIBUTES

DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category` (
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`));

DROP TABLE IF EXISTS `Tag`;
CREATE TABLE `Tag` (
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`));
  
DROP TABLE IF EXISTS `Company_Fee`;
CREATE TABLE `Company_Fee` (
	`percentaje` DECIMAL NOT NULL,
    PRIMARY KEY(`percentaje`)
);
  
-- MAGAZINE
DROP TABLE IF EXISTS `Magazine`;
CREATE TABLE `Magazine` (
  `name` VARCHAR(30) NOT NULL,
  `subscription_fee` DECIMAL(6,2) NOT NULL,
  `company_fee` DECIMAL NOT NULL,
  `cost_per_day` DECIMAL(6,2) NULL DEFAULT 0,
  `creation_date` DATE NOT NULL,
  `description` LONGTEXT NOT NULL,
  `allow_comment` SMALLINT NULL DEFAULT 0,
  `allow_likes` SMALLINT NULL DEFAULT 0,
  `category` VARCHAR(50) NOT NULL,
  `editor` VARCHAR(50) NOT NULL,
  `approved` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`name`),
  CONSTRAINT `fk_editor_magazine`
    FOREIGN KEY (`editor`)
    REFERENCES `Magazine_Web`.`Editor` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_category_magazine`
    FOREIGN KEY (`category`)
    REFERENCES `Magazine_Web`.`Category` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
   CONSTRAINT `fk_company_fee_magazine`
    FOREIGN KEY (`Company_Fee`)
    REFERENCES `Magazine_Web`.`Company_Fee` (`percentaje`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
    );

DROP TABLE IF EXISTS `Comment`;
CREATE TABLE `Comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  `magazine` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_magazine_comment`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_comment`
    FOREIGN KEY (`user`)
    REFERENCES `Magazine_Web`.`Reader` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Like`;
CREATE TABLE `Like` (
  `date` DATE NOT NULL,
  `magazine` VARCHAR(30) NOT NULL,
  `user` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`magazine`, `user`),
  CONSTRAINT `fk_like_user`
    FOREIGN KEY (`user`)
    REFERENCES `Magazine_Web`.`Reader` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_magazine`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Magazine_Tag`;
CREATE TABLE `Magazine_Tag` (
  `magazine` VARCHAR(30) NOT NULL,
  `tag` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`magazine`, `tag`),
  CONSTRAINT `fk_magazine_mag_tag`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tag_mag_tag`
    FOREIGN KEY (`tag`)
    REFERENCES `Magazine_Web`.`Tag` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
    
DROP TABLE IF EXISTS `Post`;


DROP TABLE IF EXISTS `Mag_Cost_Changelogs`;
CREATE TABLE `Mag_Cost_Changelogs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cost_per_day` DECIMAL(6,2) NOT NULL,
  `date` DATE NOT NULL,
  `admin` VARCHAR(45) NOT NULL,
  `magazine` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_admin_changelog`
    FOREIGN KEY (`admin`)
    REFERENCES `Magazine_Web`.`Admin` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_magazine_changelog`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

-- USER CONFIGS
DROP TABLE IF EXISTS `User_Intrest_Categories`;
CREATE TABLE `User_Intrest_Categories` (
  `reader` VARCHAR(50) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reader`, `category`),
  INDEX `fk_category_intrest_categ_idx` (`category` ASC) VISIBLE,
  CONSTRAINT `fk_user_intrest_categ`
    FOREIGN KEY (`reader`)
    REFERENCES `Magazine_Web`.`Reader` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_category_intrest_categ`
    FOREIGN KEY (`category`)
    REFERENCES `Magazine_Web`.`Category` (`name`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Reader_Magazine_Tag`;
CREATE TABLE `Reader_Magazine_Tag` (
  `reader` VARCHAR(50) NOT NULL,
  `tag` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`reader`, `tag`),
  INDEX `fk_tag_read_mag_tag_idx` (`tag` ASC) VISIBLE,
  CONSTRAINT `fk_reader_read_mag_tag`
    FOREIGN KEY (`reader`)
    REFERENCES `Magazine_Web`.`Reader` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tag_read_mag_tag`
    FOREIGN KEY (`tag`)
    REFERENCES `Magazine_Web`.`Tag` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Post`;
CREATE TABLE `Post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `pdf` VARCHAR(1000) NOT NULL,
  `magazine` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_magazine_post_idx` (`magazine` ASC) VISIBLE,
  CONSTRAINT `fk_magazine_post`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Subscription`;
CREATE TABLE `Subscription` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `months` TINYINT NOT NULL,
  `expiration_date` DATE NOT NULL,
  `acquisition_date` DATE NOT NULL,
  `magazine` VARCHAR(30) NOT NULL,
  `reader` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_magazine_subscription`
    FOREIGN KEY (`magazine`)
    REFERENCES `Magazine_Web`.`Magazine` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_subscription`
    FOREIGN KEY (`reader`)
    REFERENCES `Magazine_Web`.`Reader` (`email`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

-- ADDS
DROP TABLE IF EXISTS `Ad`;
CREATE TABLE `Ad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `advertiser_paid` DECIMAL(8,2) NOT NULL,
  `expiration_date` DATE NOT NULL,
  `start_date` DATE NOT NULL,
  `shown_counter` INT NOT NULL,
  `type` TINYINT NOT NULL,
  `advertiser` VARCHAR(50) NOT NULL,
  `shown_url` VARCHAR(800) NULL,
  `video_url` VARCHAR(800) NULL DEFAULT 'not video',
  `img_local_path` VARCHAR(600) NULL,
  `text` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_advertiser_ad`
    FOREIGN KEY (`advertiser`)
    REFERENCES `Magazine_Web`.`Advertiser` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `Ad_Tag`;
CREATE TABLE `Ad_Tag` (
  `tag` VARCHAR(50) NOT NULL,
  `ad` INT NOT NULL,
  PRIMARY KEY (`tag`, `ad`),
  CONSTRAINT `fk_tag_ad_tag`
    FOREIGN KEY (`tag`)
    REFERENCES `Magazine_Web`.`Tag` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ad_ad_tag`
    FOREIGN KEY (`ad`)
    REFERENCES `Magazine_Web`.`Ad` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- payment
DROP TABLE IF EXISTS `Payment`;
CREATE TABLE `Payment` (
  `subscription` INT,
  `editor_fee` DECIMAL,
  `company_fee` DECIMAL,
  PRIMARY KEY (`subscription`),
  CONSTRAINT `fk_subscription_payment`
	FOREIGN KEY(`subscription`)
    REFERENCES `Magazine_Web`.`Subscription` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);