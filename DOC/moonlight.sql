-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: moonlight
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `passport_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(15) NOT NULL,
  `reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reg_ip` varchar(15) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `uniq_name` varchar(127) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `address` varchar(511) DEFAULT NULL,
  `flag` tinyint(4) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  `animals_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `idx_name` (`uniq_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,NULL,'lalss@sogou.com','13811633448','2015-09-21 14:29:54','0:0:0:0:0:0:0:1',NULL,'test12',0,4,6,10,'12321321',0,'test_real1','aniamals1'),(2,NULL,'sb@litb.com','18888888888','2015-09-21 14:30:32','0:0:0:0:0:0:0:1',NULL,'test sb',0,4,5,6,'望京丽泽中二路',0,'test1','test2'),(3,NULL,'lao@sogou.com','13811633447','2015-09-23 13:55:24','0:0:0:0:0:0:0:1',NULL,'test',0,4,9,-1,'test',0,'','');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` char(1) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (4,'北京市',NULL,'1',4),(6,'朝阳区',4,'2',6),(7,'海淀区',4,'2',7),(9,'测试',4,'2',1),(10,'三环',6,'3',1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animals`
--

DROP TABLE IF EXISTS `animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animals_name` varchar(128) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `order_type` char(1) DEFAULT NULL,
  `order_type_orderby` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animals`
--

LOCK TABLES `animals` WRITE;
/*!40000 ALTER TABLE `animals` DISABLE KEYS */;
INSERT INTO `animals` VALUES (1,'哈士奇',1,'H',1),(2,'喵喵咪',2,'M',0),(3,'小白鼠',3,'X',0),(4,'哈士奇2',1,'H',2),(5,'哈士奇3',1,'H',3),(6,'测试宠物',1,'C',0);
/*!40000 ALTER TABLE `animals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animals_type`
--

DROP TABLE IF EXISTS `animals_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animals_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(32) DEFAULT NULL,
  `expense_coefficient` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animals_type`
--

LOCK TABLES `animals_type` WRITE;
/*!40000 ALTER TABLE `animals_type` DISABLE KEYS */;
INSERT INTO `animals_type` VALUES (1,'大型动物',1.2),(2,'中型动物',1.1),(3,'小型动物',1);
/*!40000 ALTER TABLE `animals_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(128) DEFAULT NULL,
  `course_describe` varchar(256) DEFAULT NULL,
  `expense` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'course1','test course',100),(2,'course22222','course test',1000);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hairdressing`
--

DROP TABLE IF EXISTS `hairdressing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hairdressing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hairdressing_name` varchar(128) DEFAULT NULL,
  `hairdressing_describe` varchar(256) DEFAULT NULL,
  `expense` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hairdressing`
--

LOCK TABLES `hairdressing` WRITE;
/*!40000 ALTER TABLE `hairdressing` DISABLE KEYS */;
INSERT INTO `hairdressing` VALUES (1,'test','test1',120);
/*!40000 ALTER TABLE `hairdressing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hairdressing_time`
--

DROP TABLE IF EXISTS `hairdressing_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hairdressing_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` varchar(32) DEFAULT NULL,
  `end_time` varchar(32) DEFAULT NULL,
  `service_persion_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hairdressing_time`
--

LOCK TABLES `hairdressing_time` WRITE;
/*!40000 ALTER TABLE `hairdressing_time` DISABLE KEYS */;
INSERT INTO `hairdressing_time` VALUES (1,'09:00','11:00',2);
/*!40000 ALTER TABLE `hairdressing_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` varchar(31) NOT NULL COMMENT '唯一订单号',
  `account_id` int(11) NOT NULL COMMENT '下单人账号',
  `order_type` tinyint(2) NOT NULL COMMENT '订单类型,1-寄样,2-训练,3-美容',
  `animals_id` int(11) NOT NULL COMMENT '宠物id',
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `service_type` tinyint(2) DEFAULT NULL COMMENT '服务方式:0-上门服务,1-到店服务',
  `service_begin` tinyint(2) DEFAULT NULL COMMENT '服务开始时间',
  `service_end` tinyint(4) DEFAULT NULL COMMENT '服务结束时间',
  `start_date` varchar(32) DEFAULT NULL,
  `end_date` varchar(32) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL COMMENT '房间类型',
  `course_id` int(11) DEFAULT NULL COMMENT '课程类型',
  `cost` decimal(11,4) DEFAULT NULL COMMENT '订单价格',
  `hairdress_id` int(11) DEFAULT NULL COMMENT '美容服务项目',
  `payment_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '付款状态:0-未付款,1-已付款',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `payment_type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('3bMuUpi11444745750102lHzt',1,3,1,NULL,NULL,NULL,NULL,0,NULL,NULL,'2015-10-02 10:00',NULL,NULL,NULL,144.0000,1,0,0,'2015-10-13 22:15:50','2015-10-13 14:15:50',2),('3HZNKwD11444835680085ITvP',1,3,5,4,6,10,'12321321',0,NULL,NULL,'2015-10-01 9:00',NULL,NULL,NULL,144.0000,1,0,0,'2015-10-14 23:14:40','2015-10-14 15:14:39',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `weixin_discount` double DEFAULT NULL,
  `discount_30` double DEFAULT NULL,
  `discount_90` double DEFAULT NULL,
  `discount_180` double DEFAULT NULL,
  `room_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'低调奢华房',1,1,1,1,11,1),(2,'高端大气房',2,22,22,2,2,2);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-26 22:54:36
