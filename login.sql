# Host: localhost  (Version 5.6.27-enterprise-commercial-advanced-log)
# Date: 2016-03-31 15:12:04
# Generator: MySQL-Front 5.3  (Build 5.21)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "login"
#

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user` varchar(20) NOT NULL DEFAULT '',
  `pwd` varchar(255) NOT NULL DEFAULT '000000',
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "login"
#

INSERT INTO `login` VALUES ('admin','admin');
