-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: timetablecollege
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collegetimetable`
--

DROP TABLE IF EXISTS `collegetimetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collegetimetable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) NOT NULL,
  `f_id` int NOT NULL,
  `subcode` varchar(100) NOT NULL,
  `days` varchar(45) NOT NULL,
  `time` varchar(100) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_id_idx` (`f_id`),
  KEY `subcode_idx` (`subcode`),
  KEY `Dname_idx` (`department`),
  CONSTRAINT `DN` FOREIGN KEY (`department`) REFERENCES `department` (`dept_name`) ON DELETE CASCADE,
  CONSTRAINT `f_id` FOREIGN KEY (`f_id`) REFERENCES `faculty` (`fid`) ON DELETE CASCADE,
  CONSTRAINT `subcode` FOREIGN KEY (`subcode`) REFERENCES `subject` (`code`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collegetimetable`
--

LOCK TABLES `collegetimetable` WRITE;
/*!40000 ALTER TABLE `collegetimetable` DISABLE KEYS */;
INSERT INTO `collegetimetable` VALUES (40,'5A',19,'18CS52','Monday','8:30AM-9:30AM','computer science'),(41,'5A',16,'18CS52','Monday','9:30AM-10:30AM','computer science'),(42,'5A',16,'18CS52','Monday','10:45AM-11:45AM','computer science'),(43,'5A',17,'18CS53','Monday','11:45AM-12:45PM','computer science'),(44,'5A',17,'18CSL57','Monday','1:30PM-2:30PM','computer science'),(45,'5A',17,'18CSL57','Monday','2:30PM-3:30PM','computer science'),(46,'5A',17,'18CSL57','Monday','3:30PM-4:30PM','computer science'),(47,'5B',20,'18CS51','Monday','8:30AM-9:30AM','computer science'),(48,'5B',21,'18CS52','Monday','9:30AM-10:30AM','computer science'),(49,'5B',20,'18CS51','Monday','10:45AM-11:45AM','computer science'),(53,'5B',20,'18CS51','Monday','11:45AM-12:45PM','computer science'),(54,'5B',21,'18CSL57','Monday','1:30PM-2:30PM','computer science'),(55,'5B',21,'18CSL57','Monday','2:30PM-3:30PM','computer science'),(56,'5B',21,'18CSL57','Monday','3:30PM-4:30PM','computer science'),(57,'3A',22,'18CS52','Monday','8:30AM-9:30AM','information science'),(63,'5B',21,'18CS51','Tuesday','8:30AM-9:30AM','computer science'),(64,'5B',19,'18CS51','Tuesday','9:30AM-10:30AM','computer science'),(68,'5B',19,'18CS51','Tuesday','10:45AM-11:45AM','computer science'),(72,'5B',19,'18CS51','Tuesday','11:45AM-12:45PM','computer science'),(76,'5B',19,'18CS51','Tuesday','1:30PM-2:30PM','computer science');
/*!40000 ALTER TABLE `collegetimetable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 19:20:11
