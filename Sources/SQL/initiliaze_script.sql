CREATE DATABASE IF NOT EXISTS `data` CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `data`;

DROP TABLE IF EXISTS `config`;
DROP TABLE IF EXISTS `user_log`;
DROP TABLE IF EXISTS `task`;
DROP TABLE IF EXISTS `achievement`;
DROP TABLE IF EXISTS `permission`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `badge_language`;
DROP TABLE IF EXISTS `badge`;
DROP TABLE IF EXISTS `category_language`;
DROP TABLE IF EXISTS `language`;
DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`code` VARCHAR(15) UNIQUE NOT NULL,
	`position` INT(11) NOT NULL,
	`status` TINYINT NOT NULL,
	`tag` VARCHAR(15) NOT NULL,
	`file_name` VARCHAR(100) NOT NULL
);

CREATE TABLE `language`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`language` VARCHAR(30) NOT NULL,
	`country` VARCHAR(30) NOT NULL,
	`i18n` VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE `category_language` (
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`category_id` INT,
	`language_id` INT(11),
	`name` NVARCHAR(50) UNIQUE NOT NULL,
	`description` NVARCHAR(100),
	CONSTRAINT fk_CLLang FOREIGN KEY (language_id) REFERENCES `language`(id),
	CONSTRAINT fk_CLCate FOREIGN KEY (category_id) REFERENCES `category`(id)
);

CREATE TABLE `badge` (
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`status` INT NOT NULL,
	`file_name` VARCHAR(100) NOT NULL,
	`eligibility` VARCHAR(100) NOT NULL
);

CREATE TABLE `badge_language`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`badge_id` INT(11),
	`language_id` INT(11),
	`name` NVARCHAR(100) NOT NULL,
	`description` NVARCHAR(300) NOT NULL,
	CONSTRAINT fk_BLLang FOREIGN KEY (language_id) REFERENCES `language`(id),
	CONSTRAINT fk_BLBadge FOREIGN KEY (badge_id) REFERENCES `badge`(id)
);

CREATE TABLE `role` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(30)
);

CREATE TABLE `user` (
	`id` MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`role_id` INT(11),
	`username` VARCHAR(30) UNIQUE NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`email` VARCHAR(50) NOT NULL UNIQUE,
	`first_name` VARCHAR(45) NOT NULL,
	`last_name` VARCHAR(45),
	`create_date` DATETIME NOT NULL,
	`last_login` DATETIME,
	`gender` TINYINT(4) DEFAULT 0,
	`point` INT NOT NULL DEFAULT 0,
	`status` VARCHAR(15) NOT NULL,
	`token` VARCHAR(45) NULL
);

CREATE TABLE `permission` (
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`role_id` INT,
	`regional` VARCHAR(30) NOT NULL,
	CONSTRAINT fk_PerRole FOREIGN KEY(role_id) REFERENCES `role`(id)
);

CREATE TABLE `achievement` (
	`id` MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`user_id` MEDIUMINT,
	`badge_id` INT(11),
	`achieved_time` DATETIME DEFAULT NULL,
	CONSTRAINT fk_AchieUser FOREIGN KEY(user_id) REFERENCES `user`(id),
	CONSTRAINT fk_AchieBadge FOREIGN KEY(badge_id) REFERENCES `badge`(id)
);

CREATE TABLE `task` (
	`id` MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`user_id` MEDIUMINT,
	`category_id` INT,
	`name` NVARCHAR(70) NOT NULL,
	`description` NVARCHAR(100) NULL,
	`start_time` DATETIME NULL,
	`complete_time` DATETIME NOT NULL,
	`difficulty_level` INT(11) NOT NULL,
	`status` INT(11) NOT NULL,
	`create_time` DATETIME NOT NULL,
	`point` INT NOT NULL,
	CONSTRAINT fk_TaskUser FOREIGN KEY(user_id) REFERENCES `user`(id),
	CONSTRAINT fk_TaskCate FOREIGN KEY(category_id) REFERENCES `category`(id)
);

CREATE TABLE `user_log` (
	`id` MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`user_id` MEDIUMINT,
	`action` VARCHAR(50) NOT NULL,
	`time` DATETIME NOT NULL,
	CONSTRAINT fk_UserLog FOREIGN KEY(user_id) REFERENCES `user`(id)
);

CREATE TABLE `config` (
	`key` VARCHAR(50) PRIMARY KEY NOT NULL,
	`value` VARCHAR(100) NOT NULL
);

