CREATE DATABASE IF NOT EXISTS `student_tracker`;

USE `student_tracker`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)

)