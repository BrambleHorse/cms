-- MySQL dump 10.13  Distrib 5.6.13, for Win64 (x86_64)
--
-- Host: localhost    Database: cms
-- ------------------------------------------------------
-- Server version	5.6.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_position` int(11) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `parentCategory_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK4D47461C78D107D2` (`parentCategory_category_id`),
  CONSTRAINT `FK4D47461C78D107D2` FOREIGN KEY (`parentCategory_category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (6,0,'Главная',NULL),(12,1,'Категория1',NULL),(13,2,'Категория2',NULL),(15,0,'Подкатегория1.1',12),(17,1,'Подкатегория1.2',12);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_position` int(11) DEFAULT NULL,
  `content_type` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  KEY `FK38B7347948B169E9` (`category_id`),
  CONSTRAINT `FK38B7347948B169E9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (8,0,0,6),(9,2,2,6);
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_content`
--

DROP TABLE IF EXISTS `image_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_content` (
  `filepath` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `thumbfilepath` varchar(255) DEFAULT NULL,
  `thumbpath` varchar(255) DEFAULT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`content_id`),
  KEY `FK12DBF995584A984B` (`content_id`),
  CONSTRAINT `FK12DBF995584A984B` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_content`
--

LOCK TABLES `image_content` WRITE;
/*!40000 ALTER TABLE `image_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sheet_content`
--

DROP TABLE IF EXISTS `sheet_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sheet_content` (
  `sheet` varchar(255) DEFAULT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`content_id`),
  KEY `FK58846999584A984B` (`content_id`),
  CONSTRAINT `FK58846999584A984B` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sheet_content`
--

LOCK TABLES `sheet_content` WRITE;
/*!40000 ALTER TABLE `sheet_content` DISABLE KEYS */;
INSERT INTO `sheet_content` VALUES ('<tr>\r\n<td>Название</td>\r\n<td>Стоимость</td>\r\n</tr>\r\n<tr>\r\n<td>Рыба</td>\r\n<td>100</td>\r\n</tr>\r\n<tr>\r\n<td>Мясо</td>\r\n<td>200</td>\r\n</tr>\r\n<tr>\r\n<td>Птица</td>\r\n<td>300</td>\r\n</tr>\r\n<tr>\r\n<td>Овощи</td>\r\n<td>400</td>\r\n</tr>',9);
/*!40000 ALTER TABLE `sheet_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_content`
--

DROP TABLE IF EXISTS `text_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text_content` (
  `text` longtext,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`content_id`),
  KEY `FKB16FA367584A984B` (`content_id`),
  CONSTRAINT `FKB16FA367584A984B` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_content`
--

LOCK TABLES `text_content` WRITE;
/*!40000 ALTER TABLE `text_content` DISABLE KEYS */;
INSERT INTO `text_content` VALUES ('Таким образом постоянный количественный рост и сфера нашей активности влечет за собой процесс внедрения и модернизации направлений прогрессивного развития. Товарищи! начало повседневной работы по формированию позиции требуют от нас анализа форм развития. Идейные соображения высшего порядка, а также реализация намеченных плановых заданий представляет собой интересный эксперимент проверки системы обучения кадров, соответствует насущным потребностям. Идейные соображения высшего порядка, а также реализация намеченных плановых заданий требуют от нас анализа системы обучения кадров, соответствует насущным потребностям. Равным образом реализация намеченных плановых заданий позволяет оценить значение системы обучения кадров, соответствует насущным потребностям.\r\nЗначимость этих проблем настолько очевидна, что дальнейшее развитие различных форм деятельности требуют от нас анализа дальнейших направлений развития. С другой стороны реализация намеченных плановых заданий влечет за собой процесс внедрения и модернизации модели развития. Значимость этих проблем настолько очевидна, что реализация намеченных плановых заданий играет важную роль в формировании дальнейших направлений развития.',8);
/*!40000 ALTER TABLE `text_content` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-01 20:14:47
