-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema rating_site_db
--

CREATE DATABASE IF NOT EXISTS rating_site_db;
USE rating_site_db;

--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `uniCategory` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`ID`,`name`) VALUES 
 (2,'АВТО/МОТО'),
 (3,'БИЗНЕС/ФИНАНСЫ'),
 (4,'ДОМ/САД'),
 (1,'ИНТЕРНЕТ/КОМПЬЮТЕРЫ'),
 (5,'ИСКУССТВО/КУЛЬТУРА'),
 (6,'МЕДИЦИНА/ЗДОРОВЬЕ'),
 (7,'МУЗЫКА/КИНО'),
 (8,'НАУКА/ОБРАЗОВАНИЕ'),
 (9,'ОБУСТРОЙСТВО И КОМФОРТ'),
 (10,'ОБЩЕСТВО'),
 (11,'ОТДЫХ И РАЗВЛЕЧЕНИЯ'),
 (12,'ПРИРОДА'),
 (13,'ПРОДУКТЫ ПИТАНИЯ'),
 (14,'ПРОМЫШЛЕННОСТЬ'),
 (15,'ПУТЕШЕСТВИЯ И ТУРИЗМ'),
 (16,'СВЯЗЬ'),
 (17,'СЕМЬЯ'),
 (18,'СМИ'),
 (19,'СПОРТ'),
 (20,'СТРОЙКА/РЕМОНТ'),
 (21,'ХОББИ');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `ID` int(11) NOT NULL auto_increment,
  `city` varchar(45) NOT NULL,
  `country` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`country`),
  UNIQUE KEY `cityUni` (`city`),
  KEY `fk_cities_countries1` (`country`),
  CONSTRAINT `fk_cities_countries1` FOREIGN KEY (`country`) REFERENCES `countries` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cities`
--

/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` (`ID`,`city`,`country`) VALUES 
 (2,'Донецк',1),
 (1,'Киев',1),
 (3,'Лондон',3),
 (7,'Лос-Анжелес',4),
 (5,'Мадрид',7),
 (6,'Милан',6),
 (4,'Париж',5);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;


--
-- Definition of table `counter`
--

DROP TABLE IF EXISTS `counter`;
CREATE TABLE `counter` (
  `ID` int(11) NOT NULL auto_increment,
  `site` int(11) NOT NULL,
  `counterType` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`site`,`counterType`),
  UNIQUE KEY `counterUni` (`site`,`counterType`),
  KEY `fk_counter_site1` (`site`),
  KEY `fk_counter_counterType1` (`counterType`),
  CONSTRAINT `fk_counter_counterType1` FOREIGN KEY (`counterType`) REFERENCES `countertype` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_counter_site1` FOREIGN KEY (`site`) REFERENCES `site` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `counter`
--

/*!40000 ALTER TABLE `counter` DISABLE KEYS */;
INSERT INTO `counter` (`ID`,`site`,`counterType`) VALUES 
 (2,1,2),
 (5,1,5),
 (41,1,10),
 (42,1,14),
 (43,1,7),
 (44,1,8),
 (1,2,1),
 (45,2,2),
 (46,2,3),
 (47,2,4),
 (48,2,5),
 (49,2,6),
 (50,2,7),
 (51,2,8),
 (52,2,9),
 (53,2,10),
 (2,3,1),
 (3,4,4),
 (6,4,1),
 (4,5,5),
 (7,6,13),
 (8,6,12),
 (9,6,14),
 (10,7,1),
 (11,7,2),
 (12,7,3),
 (13,8,4),
 (14,8,6),
 (15,8,7),
 (16,9,1),
 (17,9,3),
 (18,9,4),
 (19,9,5),
 (20,10,6),
 (21,10,8),
 (22,11,2),
 (23,11,3),
 (24,11,4),
 (25,11,5),
 (26,12,14),
 (27,12,12),
 (28,13,11),
 (29,13,10),
 (30,14,5),
 (31,14,6),
 (32,15,4),
 (33,15,6),
 (34,15,10),
 (35,16,10),
 (36,17,12),
 (37,17,13),
 (38,18,1),
 (39,18,2),
 (40,18,3),
 (58,24,10),
 (59,24,7),
 (60,24,12),
 (61,24,14),
 (62,25,3),
 (63,25,5);
/*!40000 ALTER TABLE `counter` ENABLE KEYS */;


--
-- Definition of table `counterdata`
--

DROP TABLE IF EXISTS `counterdata`;
CREATE TABLE `counterdata` (
  `ID` int(11) NOT NULL auto_increment,
  `date` date NOT NULL,
  `counts` int(11) NOT NULL default '0',
  `counter` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`counter`),
  UNIQUE KEY `counterDataUni` (`date`,`counter`),
  KEY `fk_counterData_counter1` (`counter`),
  CONSTRAINT `fk_counterData_counter1` FOREIGN KEY (`counter`) REFERENCES `counter` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `counterdata`
--

/*!40000 ALTER TABLE `counterdata` DISABLE KEYS */;
INSERT INTO `counterdata` (`ID`,`date`,`counts`,`counter`) VALUES 
 (1,'2012-12-12',1,1),
 (3,'2012-12-12',0,3),
 (4,'2012-12-12',0,4),
 (5,'2012-12-12',6,5),
 (6,'2012-12-12',12,2),
 (7,'2012-12-13',1,5),
 (8,'2011-12-20',2,5),
 (9,'2011-12-20',1,1),
 (10,'2011-12-20',3,2),
 (11,'2011-12-24',1,5),
 (12,'2011-12-24',1,2),
 (14,'2011-12-24',5,6),
 (15,'2011-12-24',7,7),
 (16,'2011-12-24',8,8),
 (17,'2011-12-24',9,9),
 (18,'2011-12-24',1,10),
 (19,'2011-12-24',22,11),
 (20,'2011-12-24',12,12),
 (21,'2011-12-24',13,13),
 (22,'2011-12-24',14,14),
 (23,'2011-12-24',15,15),
 (24,'2011-12-24',35,16),
 (25,'2011-12-24',32,17),
 (26,'2011-12-24',3,18),
 (27,'2011-12-24',4,19),
 (28,'2011-12-24',6,20),
 (29,'2011-12-24',5,21),
 (30,'2011-12-24',7,22),
 (31,'2011-12-24',8,23),
 (32,'2011-12-24',9,24),
 (33,'2011-12-24',23,25),
 (34,'2011-12-24',3,26),
 (35,'2011-12-24',35,27),
 (36,'2011-12-24',23,28),
 (37,'2011-12-24',23,29),
 (38,'2011-12-24',4,30),
 (39,'2011-12-24',33,31),
 (40,'2011-12-24',3,32),
 (41,'2011-12-24',25,33),
 (42,'2011-12-24',36,34),
 (43,'2011-12-24',34,35),
 (44,'2011-12-24',3,36),
 (45,'2011-12-24',5,37),
 (46,'2011-12-24',56,38),
 (47,'2011-12-24',7,39),
 (48,'2011-12-24',68,40),
 (49,'2011-12-29',8,26),
 (50,'2011-12-29',4,27),
 (51,'2011-12-30',22,26),
 (52,'2011-12-30',12,27),
 (53,'2011-12-30',1,31),
 (54,'2011-12-30',1,30),
 (55,'2011-12-30',7,28),
 (56,'2011-12-30',3,29),
 (57,'2011-12-30',4,22),
 (58,'2011-12-30',3,24),
 (59,'2011-12-30',3,25),
 (60,'2011-12-30',1,23),
 (61,'2011-12-30',6,4),
 (62,'2011-12-30',1,9),
 (63,'2011-12-30',1,7),
 (64,'2011-12-30',2,8),
 (65,'2011-12-30',11,3),
 (66,'2011-12-30',7,2),
 (67,'2011-12-30',3,5),
 (68,'2011-12-30',5,1),
 (69,'2011-12-30',4,42),
 (70,'2011-12-30',45,43),
 (71,'2011-12-30',34,44),
 (72,'2011-12-30',32,45),
 (73,'2011-12-30',76,46),
 (74,'2011-12-30',56,47),
 (75,'2011-12-30',15,48),
 (76,'2011-12-30',62,49),
 (77,'2011-12-30',27,50),
 (78,'2011-12-30',43,51),
 (79,'2011-12-30',3,52),
 (80,'2011-12-30',4,53),
 (81,'2011-12-30',2,41),
 (82,'2012-01-03',3,46),
 (83,'2012-01-03',3,45),
 (84,'2012-01-03',2,47),
 (87,'2012-01-06',0,58),
 (88,'2012-01-06',3,59),
 (89,'2012-01-06',1,60),
 (90,'2012-01-06',1,61),
 (91,'2012-01-15',0,62),
 (92,'2012-01-15',0,63);
/*!40000 ALTER TABLE `counterdata` ENABLE KEYS */;


--
-- Definition of table `countertype`
--

DROP TABLE IF EXISTS `countertype`;
CREATE TABLE `countertype` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `registration` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`registration`),
  UNIQUE KEY `counterTypeUni` (`name`),
  KEY `fk_counterType_registrationType1` (`registration`),
  CONSTRAINT `fk_counterType_registrationType1` FOREIGN KEY (`registration`) REFERENCES `registrationtype` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `countertype`
--

/*!40000 ALTER TABLE `countertype` DISABLE KEYS */;
INSERT INTO `countertype` (`ID`,`name`,`registration`) VALUES 
 (10,'Большой ассортимент',2),
 (7,'Дизайн магазина',2),
 (3,'Дизайн сайта',1),
 (1,'Качество контента',1),
 (9,'Качество товара',2),
 (11,'Легкость совершения покупки',2),
 (5,'Обновление контента',1),
 (2,'Полезность контента',1),
 (12,'Своевременная доставка',2),
 (14,'Тактичная служба поддержки',2),
 (8,'Удобная навигация',2),
 (13,'Удобный расчет',2),
 (6,'Удобство навигации',1),
 (4,'Эксклюзивность контента',1);
/*!40000 ALTER TABLE `countertype` ENABLE KEYS */;


--
-- Definition of table `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE `countries` (
  `ID` int(11) NOT NULL auto_increment,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `uniCountry` (`country`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `countries`
--

/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` (`ID`,`country`) VALUES 
 (3,'Англия'),
 (7,'Испания'),
 (6,'Италия'),
 (2,'Россия'),
 (4,'США'),
 (1,'Украина'),
 (5,'Франция');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;


--
-- Definition of table `faq`
--

DROP TABLE IF EXISTS `faq`;
CREATE TABLE `faq` (
  `ID` int(11) NOT NULL auto_increment,
  `question` text NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `faq`
--

/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;


--
-- Definition of table `keywords`
--

DROP TABLE IF EXISTS `keywords`;
CREATE TABLE `keywords` (
  `ID` int(11) NOT NULL auto_increment,
  `keyword` varchar(45) NOT NULL,
  `site` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`site`),
  UNIQUE KEY `uniKeywords` (`keyword`,`site`),
  KEY `fk_keywords_site1` (`site`),
  CONSTRAINT `fk_keywords_site1` FOREIGN KEY (`site`) REFERENCES `site` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `keywords`
--

/*!40000 ALTER TABLE `keywords` DISABLE KEYS */;
INSERT INTO `keywords` (`ID`,`keyword`,`site`) VALUES 
 (1,'site (сайт) 1',1),
 (8,'site (сайт) 13',13),
 (9,'site (сайт) 14',14),
 (10,'site (сайт) 16',16),
 (11,'site (сайт) 18',18),
 (2,'site (сайт) 2',2),
 (3,'site (сайт) 3',3),
 (4,'site (сайт) 4',4),
 (5,'site (сайт) 5',5),
 (6,'site (сайт) 6',6),
 (7,'site (сайт) 7',7),
 (14,'store (магазин) 10',10),
 (15,'store (магазин) 11',11),
 (16,'store (магазин) 12',12),
 (17,'store (магазин) 15',15),
 (18,'store (магазин) 17',17),
 (19,'store (магазин) 24',24),
 (12,'store (магазин) 8',8),
 (13,'store (магазин) 9',9);
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;


--
-- Definition of table `registrationtype`
--

DROP TABLE IF EXISTS `registrationtype`;
CREATE TABLE `registrationtype` (
  `ID` int(11) NOT NULL auto_increment,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `uniType` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `registrationtype`
--

/*!40000 ALTER TABLE `registrationtype` DISABLE KEYS */;
INSERT INTO `registrationtype` (`ID`,`type`) VALUES 
 (2,'Магазин'),
 (1,'Сайт');
/*!40000 ALTER TABLE `registrationtype` ENABLE KEYS */;


--
-- Definition of table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(128) NOT NULL,
  `descr` varchar(256) NOT NULL,
  `screen` varchar(45) NOT NULL,
  `link` varchar(45) NOT NULL,
  `added` date NOT NULL,
  `free` tinyint(1) NOT NULL,
  `usr` int(11) NOT NULL,
  `registration` int(11) NOT NULL,
  `subcategory` int(11) NOT NULL,
  `city` int(11) default NULL,
  `street` varchar(45) default NULL,
  `house` varchar(45) default NULL,
  `homeTel` varchar(45) default NULL,
  `workTel` varchar(45) default NULL,
  `mobiTel` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  PRIMARY KEY  (`ID`,`usr`,`registration`,`subcategory`),
  UNIQUE KEY `siteUni` (`link`),
  KEY `fk_site_registrationType1` (`registration`),
  KEY `fk_site_subcategory1` (`subcategory`),
  KEY `fk_site_cities1` (`city`),
  KEY `fk_site_users1` (`usr`),
  CONSTRAINT `fk_site_cities1` FOREIGN KEY (`city`) REFERENCES `cities` (`ID`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_site_registrationType1` FOREIGN KEY (`registration`) REFERENCES `registrationtype` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_subcategory1` FOREIGN KEY (`subcategory`) REFERENCES `subcategory` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_users1` FOREIGN KEY (`usr`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `site`
--

/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` (`ID`,`name`,`descr`,`screen`,`link`,`added`,`free`,`usr`,`registration`,`subcategory`,`city`,`street`,`house`,`homeTel`,`workTel`,`mobiTel`,`email`) VALUES 
 (1,'Google1','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?1','2012-01-22',0,1,1,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Google2','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?2','2012-01-22',0,1,1,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'Google3','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?3','2012-01-22',0,1,1,21,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'Google4','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?4','2012-01-22',0,1,1,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'Google5','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?5','2012-01-22',0,1,1,26,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,'Google6','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?6','2012-01-22',0,5,1,20,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (7,'Google7','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?7','2012-01-22',0,5,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (8,'Google8','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?8','2012-01-22',0,5,2,15,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (9,'Google9','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?9','2012-01-22',0,5,2,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (10,'Google10','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?10','2012-01-22',0,8,2,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (11,'Google11','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?11','2012-01-22',0,8,2,22,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (12,'Google12','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?12','2012-01-22',0,5,2,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (13,'Google13','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?13','2012-01-22',0,5,1,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (14,'Google14','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?14','2012-01-22',0,5,1,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (15,'Google15','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?15','2012-01-22',0,8,2,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (16,'Google16','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?16','2012-01-22',0,8,1,22,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (17,'Google17','Goggle search system (магазин)','images/1.jpg','http://www.google.ru/?17','2012-01-22',0,8,2,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (18,'Google18','Goggle search system (сайт)','images/1.jpg','http://www.google.ru/?18','2012-01-22',0,8,1,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (24,'Тестовый сайт','Тест добавления сайта.\r\nВроде все уже должно работать. \r\nКроме редактирования.','images/1.jpg','www.testsite.ru','2012-01-22',0,8,2,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (25,'Тестовый сайт (сайт)','тестовый сайт \r\nтип сайт','images/1.jpg','http://www.testsite.com','2012-01-22',0,8,1,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;


--
-- Definition of table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
CREATE TABLE `subcategory` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `category` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`category`),
  UNIQUE KEY `uniSubcategory` (`name`,`category`),
  KEY `fk_subcategory_categories1` (`category`),
  CONSTRAINT `fk_subcategory_categories1` FOREIGN KEY (`category`) REFERENCES `category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subcategory`
--

/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` (`ID`,`name`,`category`) VALUES 
 (6,'АВТОЗАПЧАСТИ',2),
 (5,'АВТОСЕРВИСЫ',2),
 (4,'АВТОСТРАХОВАНИЕ',2),
 (10,'БЕЗОПАСНОСТЬ',1),
 (27,'ВУЗЫ',8),
 (22,'ВЫРАЩИВАНИЕ ФРУКТОВ',4),
 (9,'ЖЕЛЕЗО',1),
 (14,'ЗНАКОМСТВА',1),
 (8,'ИГРЫ',1),
 (19,'ИНВЕСТИЦИИ',3),
 (3,'ИНОМАРКИ',2),
 (23,'КАЛЕНДАРЬ РАБОТ',4),
 (18,'МАРКЕТИНГ',3),
 (2,'МОТОЦИКЛЫ',2),
 (17,'НЕДВИЖИМОСТЬ',3),
 (26,'ПЕДАГОГИКА',8),
 (1,'РЕЙТИНГОВЫЕ СИСТЕМЫ',1),
 (16,'РЕКЛАМА',3),
 (25,'РЕФЕРАТЫ',8),
 (20,'САД',4),
 (7,'СЕТИ',1),
 (13,'СОФТ',1),
 (21,'УХОД ЗА ЦВЕТАМИ',4),
 (11,'ФОРУМЫ',1),
 (12,'ЧАТЫ',1),
 (24,'ШКОЛЫ',8),
 (15,'ЮРИСПРУДЕНЦИЯ',3);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;


--
-- Definition of table `texts`
--

DROP TABLE IF EXISTS `texts`;
CREATE TABLE `texts` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `uniTexts` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `texts`
--

/*!40000 ALTER TABLE `texts` DISABLE KEYS */;
/*!40000 ALTER TABLE `texts` ENABLE KEYS */;


--
-- Definition of table `usernews`
--

DROP TABLE IF EXISTS `usernews`;
CREATE TABLE `usernews` (
  `ID` int(11) NOT NULL auto_increment,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `news` varchar(250) NOT NULL,
  `usr` int(11) NOT NULL,
  `site` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`usr`,`site`),
  KEY `fk_usernews_users1` (`usr`),
  KEY `fk_usernews_site1` (`site`),
  CONSTRAINT `fk_usernews_users1` FOREIGN KEY (`usr`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usernews_site1` FOREIGN KEY (`site`) REFERENCES `site` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usernews`
--

/*!40000 ALTER TABLE `usernews` DISABLE KEYS */;
/*!40000 ALTER TABLE `usernews` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` varchar(45) NOT NULL default 'user',
  `email` varchar(45) NOT NULL,
  `ban` tinyint(1) NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `userUni` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`ID`,`name`,`password`,`role`,`email`,`ban`) VALUES 
 (1,'Denis','111111','user','somemail@mail.ru',0),
 (5,'rol','111111','user','ssdf@dfsds.com',0),
 (8,'conarh','111111','user','conarh@yandex.ru',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `visitornotes`
--

DROP TABLE IF EXISTS `visitornotes`;
CREATE TABLE `visitornotes` (
  `ID` int(11) NOT NULL auto_increment,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `name` varchar(45) NOT NULL,
  `note` text NOT NULL,
  `site` int(11) NOT NULL,
  PRIMARY KEY  (`ID`,`site`),
  UNIQUE KEY `uniVisitorNotes` (`date`,`time`),
  KEY `fk_visitornotes_userSites1` (`site`),
  CONSTRAINT `fk_visitornotes_userSites1` FOREIGN KEY (`site`) REFERENCES `site` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `visitornotes`
--

/*!40000 ALTER TABLE `visitornotes` DISABLE KEYS */;
INSERT INTO `visitornotes` (`ID`,`date`,`time`,`name`,`note`,`site`) VALUES 
 (1,'2011-12-23','01:59:00','wefwef','wefwefwefwef',1),
 (2,'2011-12-23','02:01:00','Tester','Test',1),
 (4,'2011-12-24','02:48:35','Конарх','тест добавления заметки',1),
 (5,'2011-12-24','02:49:58','Конарх','ТЕСТ ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ ТЕСТТЕСТ  ТЕСТ ТЕСТ ТЕСТ ТЕСТ  ТЕСТ ТЕСТ  ТЕСТ ТЕСТ ТЕСТ  ТЕСТ ТЕСТ ТЕСТ м  ТЕСТ ТЕСТ м м  ТЕСТТЕСТ  ТЕСТТЕСТ  м ТЕСТ  ТЕСТ ТЕСТ ТЕСТ ТЕСТ ТЕСТ м',1),
 (7,'2011-12-24','22:50:07','asdasdadqqwd','qwdqw qr fq3 r q3 rq rf 3  q2 rf3qfrq fq2r2q',1),
 (8,'2011-12-30','09:20:39','rol','1111111',12),
 (9,'2012-01-03','17:24:44','test','test тест',2),
 (10,'2012-01-05','01:48:59','тестер','еще тест заметок',2),
 (11,'2012-01-06','14:03:00','Conarh','test',24),
 (12,'2012-01-06','14:03:18','Vasya','test2',24);
/*!40000 ALTER TABLE `visitornotes` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
