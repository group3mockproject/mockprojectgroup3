-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: apartment-management
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `apartments`
--

DROP TABLE IF EXISTS `apartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartments` (
  `apartment_id` bigint NOT NULL AUTO_INCREMENT,
  `area` float DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `num_of_bathroom` int DEFAULT NULL,
  `num_of_bedroom` int DEFAULT NULL,
  `rentfee` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `block_id` bigint DEFAULT NULL,
  PRIMARY KEY (`apartment_id`),
  KEY `FKkw4tcff1kc57cclkqwi5uhqah` (`block_id`),
  CONSTRAINT `FKkw4tcff1kc57cclkqwi5uhqah` FOREIGN KEY (`block_id`) REFERENCES `block` (`block_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments`
--

LOCK TABLES `apartments` WRITE;
/*!40000 ALTER TABLE `apartments` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_roles`
--

DROP TABLE IF EXISTS `app_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_roles` (
  `role_id` bigint NOT NULL,
  `role_name` varchar(30) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `APP_ROLE_UK` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_roles`
--

LOCK TABLES `app_roles` WRITE;
/*!40000 ALTER TABLE `app_roles` DISABLE KEYS */;
INSERT INTO `app_roles` VALUES (1,'ROLE_ADMIN'),(4,'ROLE_CUSTOMER'),(3,'ROLE_EMPLOYEE'),(2,'ROLE_MANAGER');
/*!40000 ALTER TABLE `app_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_roles_seq`
--

DROP TABLE IF EXISTS `app_roles_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_roles_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_roles_seq`
--

LOCK TABLES `app_roles_seq` WRITE;
/*!40000 ALTER TABLE `app_roles_seq` DISABLE KEYS */;
INSERT INTO `app_roles_seq` VALUES (101);
/*!40000 ALTER TABLE `app_roles_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_users`
--

DROP TABLE IF EXISTS `app_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_users` (
  `user_id` bigint NOT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `date_create` datetime(6) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `APP_USER_UK` (`email`),
  UNIQUE KEY `APP_USER_CODE_UK` (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users`
--

LOCK TABLES `app_users` WRITE;
/*!40000 ALTER TABLE `app_users` DISABLE KEYS */;
INSERT INTO `app_users` VALUES (1,_binary '',_binary '','Computer',_binary '',NULL,'2024-10-23','admin@gmail.com',_binary '','Admin','$2a$10$Ja3tSeOTowMxfGWNrTudmuwV.EuEOV84l2F.4BQxnKpVk0gj991.O','0123456789',NULL,NULL,'abcd',3),(2,_binary '',_binary '',NULL,_binary '','2024-08-28 10:29:06.364927',NULL,'dvkhang49@gmail.com',_binary '','Văn Khang','$2a$10$QbqJx6J3rs7hpy3knFR0oeONzuNWW28m7Urrz3pYFNmK9HaLAu8la','0123456789',NULL,'EM0001','https://firebasestorage.googleapis.com/v0/b/bicycle-shop-de666.appspot.com/o/multipleImages%2Fbabydoll2.jfif?alt=media&token=fe3d07bd-3710-46f8-9621-ef86e6a32556',0),(3,_binary '',_binary '','Universe',_binary '','2024-10-23 18:52:31.133841','2000-10-10','iamcustomer@gmail.com',_binary '','I am Customer','$2a$10$022acK738jPBTgwWN.ykc.km6X4LHhQKZ8/VK5Y4O83/x.JLJQWUu','0123456789',NULL,'CU0001',NULL,1),(4,_binary '\0',_binary '\0','Universe',_binary '','2024-10-23 18:53:10.039074','2000-10-10','iamcustomer2@gmail.com',_binary '\0','I am Customer second','$2a$10$xEjAQSff1sEhqOYHlJBqIuWYlpDPxD5w.AuYK1aKZ4l0kyShERO..','0123456789',NULL,'CU0002',NULL,0);
/*!40000 ALTER TABLE `app_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_users_seq`
--

DROP TABLE IF EXISTS `app_users_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users_seq`
--

LOCK TABLES `app_users_seq` WRITE;
/*!40000 ALTER TABLE `app_users_seq` DISABLE KEYS */;
INSERT INTO `app_users_seq` VALUES (101);
/*!40000 ALTER TABLE `app_users_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `block_id` bigint NOT NULL AUTO_INCREMENT,
  `delflag` bit(1) DEFAULT NULL,
  `num_of_apartment` int DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`block_id`),
  KEY `FKs067j2cpeac26h3qj68upqr68` (`building_id`),
  KEY `FKmtbxjr18trys4ummkjinno2m5` (`employee_id`),
  CONSTRAINT `FKmtbxjr18trys4ummkjinno2m5` FOREIGN KEY (`employee_id`) REFERENCES `app_users` (`user_id`),
  CONSTRAINT `FKs067j2cpeac26h3qj68upqr68` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building_equipment`
--

DROP TABLE IF EXISTS `building_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building_equipment` (
  `equipment_id` bigint NOT NULL AUTO_INCREMENT,
  `install_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `FKb41uf4gtwx6c758r2gwa7x1xm` (`building_id`),
  CONSTRAINT `FKb41uf4gtwx6c758r2gwa7x1xm` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building_equipment`
--

LOCK TABLES `building_equipment` WRITE;
/*!40000 ALTER TABLE `building_equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `building_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building_managers`
--

DROP TABLE IF EXISTS `building_managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building_managers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `building_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7t84s1b7ekjns3ssui2l1ke6t` (`building_id`),
  KEY `FKaaduhhk3tk5vrug7p6lpgfie6` (`user_id`),
  CONSTRAINT `FK7t84s1b7ekjns3ssui2l1ke6t` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`),
  CONSTRAINT `FKaaduhhk3tk5vrug7p6lpgfie6` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building_managers`
--

LOCK TABLES `building_managers` WRITE;
/*!40000 ALTER TABLE `building_managers` DISABLE KEYS */;
/*!40000 ALTER TABLE `building_managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildings` (
  `building_id` bigint NOT NULL AUTO_INCREMENT,
  `construction_year` int DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num_of_floor` int DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildings`
--

LOCK TABLES `buildings` WRITE;
/*!40000 ALTER TABLE `buildings` DISABLE KEYS */;
/*!40000 ALTER TABLE `buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_area`
--

DROP TABLE IF EXISTS `common_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `common_area` (
  `area_id` bigint NOT NULL AUTO_INCREMENT,
  `area_name` varchar(255) DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  KEY `FKj7xv6u594consatp9vytbs5nf` (`building_id`),
  CONSTRAINT `FKj7xv6u594consatp9vytbs5nf` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_area`
--

LOCK TABLES `common_area` WRITE;
/*!40000 ALTER TABLE `common_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `common_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_equipment`
--

DROP TABLE IF EXISTS `company_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_equipment` (
  `company_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `contract_inf` varchar(255) DEFAULT NULL,
  `contract_per` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_equipment`
--

LOCK TABLES `company_equipment` WRITE;
/*!40000 ALTER TABLE `company_equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint_types`
--

DROP TABLE IF EXISTS `complaint_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint_types` (
  `type_id` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_types`
--

LOCK TABLES `complaint_types` WRITE;
/*!40000 ALTER TABLE `complaint_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `complaint_id` bigint NOT NULL AUTO_INCREMENT,
  `complaint_description` text,
  `create_at` datetime(6) DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `resolve_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`complaint_id`),
  KEY `FKpjj1yn1xk3uxngk53b4xu5d4g` (`type_id`),
  KEY `FK47hlra3gejrcmjj2go3ueww5k` (`user_id`),
  CONSTRAINT `FK47hlra3gejrcmjj2go3ueww5k` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`),
  CONSTRAINT `FKpjj1yn1xk3uxngk53b4xu5d4g` FOREIGN KEY (`type_id`) REFERENCES `complaint_types` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debts`
--

DROP TABLE IF EXISTS `debts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debts` (
  `deb_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_due` int DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`deb_id`),
  KEY `FKc5ubc8htihfjug18nlrtjudtq` (`apartment_id`),
  KEY `FKs1g4oi9efw2g52olauthfjp77` (`payment_id`),
  CONSTRAINT `FKc5ubc8htihfjug18nlrtjudtq` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`),
  CONSTRAINT `FKs1g4oi9efw2g52olauthfjp77` FOREIGN KEY (`payment_id`) REFERENCES `payment_resident` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debts`
--

LOCK TABLES `debts` WRITE;
/*!40000 ALTER TABLE `debts` DISABLE KEYS */;
/*!40000 ALTER TABLE `debts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_dayoff`
--

DROP TABLE IF EXISTS `employee_dayoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_dayoff` (
  `dayoff_id` bigint NOT NULL AUTO_INCREMENT,
  `dayoff_date` date DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`dayoff_id`),
  KEY `FKd49tid76d9bxpvkq7acqtp5k7` (`user_id`),
  CONSTRAINT `FKd49tid76d9bxpvkq7acqtp5k7` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_dayoff`
--

LOCK TABLES `employee_dayoff` WRITE;
/*!40000 ALTER TABLE `employee_dayoff` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_dayoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_performance`
--

DROP TABLE IF EXISTS `employee_performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_performance` (
  `performance_id` bigint NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `evaluation_date` date DEFAULT NULL,
  `score` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`performance_id`),
  KEY `FKmgjn8g7g416jfjsangve0bx42` (`user_id`),
  CONSTRAINT `FKmgjn8g7g416jfjsangve0bx42` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_performance`
--

LOCK TABLES `employee_performance` WRITE;
/*!40000 ALTER TABLE `employee_performance` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_contract`
--

DROP TABLE IF EXISTS `equipment_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_contract` (
  `contract_id` bigint NOT NULL AUTO_INCREMENT,
  `contract_term` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `installation_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `warranty` varchar(255) DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `equipment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `FKme32abh04qkmlb3tbx9d08u34` (`apartment_id`),
  KEY `FKfu3oxmdo04fkgfo5hsb9yahdi` (`company_id`),
  KEY `FKmnau1q0acvvka1xv1iv1csmer` (`equipment_id`),
  CONSTRAINT `FKfu3oxmdo04fkgfo5hsb9yahdi` FOREIGN KEY (`company_id`) REFERENCES `company_equipment` (`company_id`),
  CONSTRAINT `FKme32abh04qkmlb3tbx9d08u34` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`),
  CONSTRAINT `FKmnau1q0acvvka1xv1iv1csmer` FOREIGN KEY (`equipment_id`) REFERENCES `equipments` (`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_contract`
--

LOCK TABLES `equipment_contract` WRITE;
/*!40000 ALTER TABLE `equipment_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipments` (
  `equipment_id` bigint NOT NULL AUTO_INCREMENT,
  `equip_description` varchar(255) DEFAULT NULL,
  `equipment_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `FK54jd8xubwjapofp3tqp9lmxx0` (`category_id`),
  CONSTRAINT `FK54jd8xubwjapofp3tqp9lmxx0` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

LOCK TABLES `equipments` WRITE;
/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_types`
--

DROP TABLE IF EXISTS `income_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income_types` (
  `type_id` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_types`
--

LOCK TABLES `income_types` WRITE;
/*!40000 ALTER TABLE `income_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `income_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomes`
--

DROP TABLE IF EXISTS `incomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomes` (
  `income_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `received_date` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`income_id`),
  KEY `FK7yof2fpcbhkryn55bkqv1kfdd` (`user_id`),
  KEY `FKlhhw2pk3do9d171ryng7aihne` (`type_id`),
  CONSTRAINT `FK7yof2fpcbhkryn55bkqv1kfdd` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`),
  CONSTRAINT `FKlhhw2pk3do9d171ryng7aihne` FOREIGN KEY (`type_id`) REFERENCES `income_types` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomes`
--

LOCK TABLES `incomes` WRITE;
/*!40000 ALTER TABLE `incomes` DISABLE KEYS */;
/*!40000 ALTER TABLE `incomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lease_contract`
--

DROP TABLE IF EXISTS `lease_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lease_contract` (
  `lease_id` bigint NOT NULL AUTO_INCREMENT,
  `delflag` bit(1) DEFAULT NULL,
  `deposit_amount` double DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `rent_amount` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`lease_id`),
  KEY `FKbbe0obdejqie82en5bo1if2qy` (`apartment_id`),
  KEY `FKgy58wqchsa79crqcswkpac2j` (`user_id`),
  CONSTRAINT `FKbbe0obdejqie82en5bo1if2qy` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`),
  CONSTRAINT `FKgy58wqchsa79crqcswkpac2j` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lease_contract`
--

LOCK TABLES `lease_contract` WRITE;
/*!40000 ALTER TABLE `lease_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `lease_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_history`
--

DROP TABLE IF EXISTS `maintenance_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `contractor` varchar(255) DEFAULT NULL,
  `fixe_date` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`history_id`),
  KEY `FKt5shpdiirtx0bwd8va350ctvt` (`building_id`),
  CONSTRAINT `FKt5shpdiirtx0bwd8va350ctvt` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_history`
--

LOCK TABLES `maintenance_history` WRITE;
/*!40000 ALTER TABLE `maintenance_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outcome_types`
--

DROP TABLE IF EXISTS `outcome_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outcome_types` (
  `type_id` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outcome_types`
--

LOCK TABLES `outcome_types` WRITE;
/*!40000 ALTER TABLE `outcome_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `outcome_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outcomes`
--

DROP TABLE IF EXISTS `outcomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outcomes` (
  `income_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `spent_date` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`income_id`),
  KEY `FKsgukkqp195rx2vmhfnx92goj1` (`user_id`),
  KEY `FKnaj6o9iytxmgakvlr3konu7qu` (`type_id`),
  CONSTRAINT `FKnaj6o9iytxmgakvlr3konu7qu` FOREIGN KEY (`type_id`) REFERENCES `outcome_types` (`type_id`),
  CONSTRAINT `FKsgukkqp195rx2vmhfnx92goj1` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outcomes`
--

LOCK TABLES `outcomes` WRITE;
/*!40000 ALTER TABLE `outcomes` DISABLE KEYS */;
/*!40000 ALTER TABLE `outcomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_paid` varchar(255) DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_method` int DEFAULT NULL,
  `penalties` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`history_id`),
  KEY `FK4bk17k1tuep9y7p297058jtgh` (`payment_id`),
  CONSTRAINT `FK4bk17k1tuep9y7p297058jtgh` FOREIGN KEY (`payment_id`) REFERENCES `payment_resident` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_history`
--

LOCK TABLES `payment_history` WRITE;
/*!40000 ALTER TABLE `payment_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_resident`
--

DROP TABLE IF EXISTS `payment_resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_resident` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `delflag` bit(1) DEFAULT NULL,
  `outstanding_amount` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `usage_id` bigint DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK1noxjv7h4nwxigb0p0i50o8v3` (`apartment_id`),
  KEY `FKapv53l6xm4cxbhocvotuhv49l` (`user_id`),
  KEY `FKm6g9eak0lccp38esh70c52493` (`usage_id`),
  CONSTRAINT `FK1noxjv7h4nwxigb0p0i50o8v3` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`apartment_id`),
  CONSTRAINT `FKapv53l6xm4cxbhocvotuhv49l` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`),
  CONSTRAINT `FKm6g9eak0lccp38esh70c52493` FOREIGN KEY (`usage_id`) REFERENCES `resident_utility_usages` (`usage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_resident`
--

LOCK TABLES `payment_resident` WRITE;
/*!40000 ALTER TABLE `payment_resident` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_tokens`
--

DROP TABLE IF EXISTS `refresh_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_tokens` (
  `token_id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FKt72gijoak5yufxws745gwflis` (`user_id`),
  CONSTRAINT `FKt72gijoak5yufxws745gwflis` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_tokens`
--

LOCK TABLES `refresh_tokens` WRITE;
/*!40000 ALTER TABLE `refresh_tokens` DISABLE KEYS */;
INSERT INTO `refresh_tokens` VALUES (3,'2024-10-24 11:33:27.785576','882febb0-0ca6-4e8a-beb1-868b7846a0b5',1);
/*!40000 ALTER TABLE `refresh_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resident_utility_usages`
--

DROP TABLE IF EXISTS `resident_utility_usages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resident_utility_usages` (
  `usage_id` bigint NOT NULL AUTO_INCREMENT,
  `usage_date` date DEFAULT NULL,
  `usage_fee` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `utility_id` bigint DEFAULT NULL,
  PRIMARY KEY (`usage_id`),
  KEY `FKggjg26yh4vrn9u801l4k063rg` (`user_id`),
  KEY `FK2aipmmikupkb6sm94k9q53c9s` (`utility_id`),
  CONSTRAINT `FK2aipmmikupkb6sm94k9q53c9s` FOREIGN KEY (`utility_id`) REFERENCES `utilities` (`utility_id`),
  CONSTRAINT `FKggjg26yh4vrn9u801l4k063rg` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resident_utility_usages`
--

LOCK TABLES `resident_utility_usages` WRITE;
/*!40000 ALTER TABLE `resident_utility_usages` DISABLE KEYS */;
/*!40000 ALTER TABLE `resident_utility_usages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timekeeping`
--

DROP TABLE IF EXISTS `timekeeping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timekeeping` (
  `timekeeping_id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `delflag` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`timekeeping_id`),
  KEY `FKhb10fj6dfythkpihjpsy1693d` (`user_id`),
  CONSTRAINT `FKhb10fj6dfythkpihjpsy1693d` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timekeeping`
--

LOCK TABLES `timekeeping` WRITE;
/*!40000 ALTER TABLE `timekeeping` DISABLE KEYS */;
/*!40000 ALTER TABLE `timekeeping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `benefits` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `term` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ROLE_UK` (`user_id`,`role_id`),
  KEY `FKasaenoxni6aw644w3g31ybnyj` (`role_id`),
  CONSTRAINT `FKasaenoxni6aw644w3g31ybnyj` FOREIGN KEY (`role_id`) REFERENCES `app_roles` (`role_id`),
  CONSTRAINT `FKnnjwin2r8oajs3wmc8sbn0672` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,NULL,NULL,NULL,NULL,NULL,1,1),(2,NULL,NULL,NULL,NULL,NULL,2,2),(3,NULL,NULL,NULL,NULL,NULL,4,3),(4,NULL,NULL,NULL,NULL,NULL,4,NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilities`
--

DROP TABLE IF EXISTS `utilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilities` (
  `utility_id` bigint NOT NULL AUTO_INCREMENT,
  `delflag` bit(1) DEFAULT NULL,
  `fee_rate` double DEFAULT NULL,
  `usages_rules` varchar(255) DEFAULT NULL,
  `utility_desc` text,
  `utility_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`utility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilities`
--

LOCK TABLES `utilities` WRITE;
/*!40000 ALTER TABLE `utilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utility_contract`
--

DROP TABLE IF EXISTS `utility_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utility_contract` (
  `contract_id` bigint NOT NULL AUTO_INCREMENT,
  `contract_term` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `utility_id` bigint DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `FKbif3wjn197sv0fkorxg5chkce` (`company_id`),
  KEY `FKhvw70gh93jqy8jacr1uaq4yrq` (`utility_id`),
  CONSTRAINT `FKbif3wjn197sv0fkorxg5chkce` FOREIGN KEY (`company_id`) REFERENCES `company_equipment` (`company_id`),
  CONSTRAINT `FKhvw70gh93jqy8jacr1uaq4yrq` FOREIGN KEY (`utility_id`) REFERENCES `utilities` (`utility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utility_contract`
--

LOCK TABLES `utility_contract` WRITE;
/*!40000 ALTER TABLE `utility_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `utility_contract` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-23 19:20:03
