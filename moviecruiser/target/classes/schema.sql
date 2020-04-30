CREATE TABLE IF NOT EXISTS `moviecruiser`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `boxOffice` VARCHAR(15) NULL,
  `active` BOOLEAN NULL,
  `dateOfLaunch` DATE NULL,
  `genre` VARCHAR(45) NULL,
  `teaser` BOOLEAN NULL,
  PRIMARY KEY (`id`));