-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: zmf-shop-pay
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `trajectory_month`
--

DROP TABLE IF EXISTS `trajectory_month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trajectory_month` (
  `vehicle_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型',
  `card_vin` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车牌号',
  `travel_count` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '历史总出行次数',
  `entry_station` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入口收费广场',
  `entry_date` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入口时间',
  `exit_station` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出口广场',
  `exit_date` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出口时间',
  `toll` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通行费用',
  `travel_time` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通行时长',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trajectory_month`
--

LOCK TABLES `trajectory_month` WRITE;
/*!40000 ALTER TABLE `trajectory_month` DISABLE KEYS */;
INSERT INTO `trajectory_month` VALUES ('1','2','3','4','5','6','7','8',NULL,3);
/*!40000 ALTER TABLE `trajectory_month` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 22:20:44
