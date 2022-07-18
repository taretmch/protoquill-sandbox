CREATE DATABASE `sandbox`;

USE `sandbox`;

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `age` smallint(6) NOT NULL,
  `gender` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `person` VALUES (1,'太郎','山田',25,1);
INSERT INTO `person` VALUES (2,'花子','山田',28,2);
INSERT INTO `person` VALUES (3,'太郎','佐藤',28,1);