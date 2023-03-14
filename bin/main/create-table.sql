-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gig-job
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account`
(
    `id`           varchar(255) NOT NULL,
    `created_date` datetime     DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `image_url`    varchar(255) DEFAULT NULL,
    `is_disable`   bit(1)       NOT NULL,
    `is_locked`    bit(1)       NOT NULL,
    `password`     varchar(255) DEFAULT NULL,
    `phone`        varchar(255) DEFAULT NULL,
    `role`         int          DEFAULT NULL,
    `updated_date` datetime     DEFAULT NULL,
    `username`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `city`       varchar(255) DEFAULT NULL,
    `country`    varchar(255) DEFAULT NULL,
    `district`   varchar(255) DEFAULT NULL,
    `province`   varchar(255) DEFAULT NULL,
    `street`     varchar(255) DEFAULT NULL,
    `account_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKascogpq8x3gfx04oy2fr6l3i5` (`account_id`),
    CONSTRAINT `FKascogpq8x3gfx04oy2fr6l3i5` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fcm_token`
--

DROP TABLE IF EXISTS `fcm_token`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fcm_token`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `value`      varchar(255) DEFAULT NULL,
    `account_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK3pms1n9dy8kxorbgcfcn4tyab` (`account_id`),
    CONSTRAINT `FK3pms1n9dy8kxorbgcfcn4tyab` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history`
(
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `duration`  double       DEFAULT NULL,
    `position`  varchar(255) DEFAULT NULL,
    `worker_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKs21mnvll0n3y8jgp03rk3xllf` (`worker_id`),
    CONSTRAINT `FKs21mnvll0n3y8jgp03rk3xllf` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `benefit`      varchar(255) DEFAULT NULL,
    `created_date` datetime     DEFAULT NULL,
    `description`  varchar(255) DEFAULT NULL,
    `expired_date` datetime     DEFAULT NULL,
    `skill`        varchar(255) DEFAULT NULL,
    `title`        varchar(255) DEFAULT NULL,
    `updated_date` datetime     DEFAULT NULL,
    `job_type_id`  bigint       DEFAULT NULL,
    `shop_id`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKby5kmudqp9ee4baa93rlmoieg` (`job_type_id`),
    KEY `FK2tjgkeyr2fharuijgqdyecpu5` (`shop_id`),
    CONSTRAINT `FK2tjgkeyr2fharuijgqdyecpu5` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
    CONSTRAINT `FKby5kmudqp9ee4baa93rlmoieg` FOREIGN KEY (`job_type_id`) REFERENCES `job_type` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_type`
--

DROP TABLE IF EXISTS `job_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_type`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment`
(
    `id`     varchar(255) NOT NULL,
    `amount` double DEFAULT NULL,
    `tax`    double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `duration` int          DEFAULT NULL,
    `shift`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop`
(
    `id`          varchar(255) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `account_id`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKl3oxnwhq2g9wxjvduq1r31n19` (`account_id`),
    CONSTRAINT `FKl3oxnwhq2g9wxjvduq1r31n19` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction`
(
    `id`            varchar(255) NOT NULL,
    `amount`        double       DEFAULT NULL,
    `created_time`  datetime     DEFAULT NULL,
    `hash_value`    varchar(255) DEFAULT NULL,
    `previous_hash` varchar(255) DEFAULT NULL,
    `status`        varchar(255) DEFAULT NULL,
    `payment_id`    varchar(255) DEFAULT NULL,
    `wallet_id`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKq9m7rb5uydysanp8smxcovxlh` (`payment_id`),
    KEY `FKtfwlfspv2h4wcgc9rjd1658a6` (`wallet_id`),
    CONSTRAINT `FKq9m7rb5uydysanp8smxcovxlh` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
    CONSTRAINT `FKtfwlfspv2h4wcgc9rjd1658a6` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wage`
--

DROP TABLE IF EXISTS `wage`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wage`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `salary` double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet`
(
    `id`         varchar(255) NOT NULL,
    `balance`    double       DEFAULT NULL,
    `account_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_q29lp8vy75l04mpr87j5j62mf` (`account_id`),
    CONSTRAINT `FK4q1fs3jihuicq5afy27cy2gk9` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker`
(
    `id`          varchar(255) NOT NULL,
    `birthday`    datetime     DEFAULT NULL,
    `education`   varchar(255) DEFAULT NULL,
    `first_name`  varchar(255) DEFAULT NULL,
    `last_name`   varchar(255) DEFAULT NULL,
    `middle_name` varchar(255) DEFAULT NULL,
    `account_id`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKndu9s6xhg7mmr6g6362oeysbc` (`account_id`),
    CONSTRAINT `FKndu9s6xhg7mmr6g6362oeysbc` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `working_session`
--

DROP TABLE IF EXISTS `working_session`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `working_session`
(
    `wage_id`    bigint       NOT NULL,
    `worker_id`  varchar(255) NOT NULL,
    `job_id`     bigint       NOT NULL,
    `session_id` bigint       NOT NULL,
    PRIMARY KEY (`job_id`, `session_id`, `wage_id`, `worker_id`),
    KEY `FKdck9pgepk9vr1jdsgdrh2f9j3` (`wage_id`),
    KEY `FKnfqmsqbc4yv2isu8lmhfw3td7` (`worker_id`),
    KEY `FK9n0kqux3wlc8w5aqnrdejbemj` (`session_id`),
    CONSTRAINT `FK26ptpfdpkx1rnj5mlw50oa64j` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
    CONSTRAINT `FK9n0kqux3wlc8w5aqnrdejbemj` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`),
    CONSTRAINT `FKdck9pgepk9vr1jdsgdrh2f9j3` FOREIGN KEY (`wage_id`) REFERENCES `wage` (`id`),
    CONSTRAINT `FKnfqmsqbc4yv2isu8lmhfw3td7` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-02-28 22:20:56
