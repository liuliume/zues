/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.21 : Database - moonlight
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `passport_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(15) NOT NULL,
  `reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reg_ip` varchar(15) NOT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `uniq_name` varchar(127) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `address` varchar(511) DEFAULT NULL,
  `flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `idx_name` (`uniq_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `account` */

insert  into `account`(`account_id`,`passport_id`,`email`,`mobile`,`reg_time`,`reg_ip`,`avatar`,`uniq_name`,`gender`,`province_id`,`city_id`,`area_id`,`address`,`flag`) values (1,NULL,'lalss@sogou.com','13811633448','2015-09-13 20:11:05','0:0:0:0:0:0:0:1',NULL,'test',0,4,5,6,'12321',0),(2,NULL,'sb@litb.com','18888888888','2015-09-13 19:54:39','0:0:0:0:0:0:0:1',NULL,'test sb',-1,4,5,6,'望京丽泽中二路',0);

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`id`,`name`,`parent_id`,`level`) values (1,'beijing213',NULL,'1'),(2,'haidian1234',1,'1'),(3,'beijing1',NULL,'1'),(4,'北京市',NULL,'1'),(5,'北京市',4,'2'),(6,'朝阳区',5,'3'),(7,'海淀区',5,'3');

/*Table structure for table `animals` */

DROP TABLE IF EXISTS `animals`;

CREATE TABLE `animals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animals_name` varchar(128) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `animals` */

insert  into `animals`(`id`,`animals_name`,`type_id`) values (1,'哈士奇',1),(2,'喵喵咪',2),(3,'小白鼠',3);

/*Table structure for table `animals_type` */

DROP TABLE IF EXISTS `animals_type`;

CREATE TABLE `animals_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(32) DEFAULT NULL,
  `expense_coefficient` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `animals_type` */

insert  into `animals_type`(`id`,`type_name`,`expense_coefficient`) values (1,'大型动物',1.2),(2,'中型动物',1.1),(3,'小型动物',1);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(128) DEFAULT NULL,
  `course_describe` varchar(256) DEFAULT NULL,
  `expense` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `course` */

insert  into `course`(`id`,`course_name`,`course_describe`,`expense`) values (1,'course1','test course',100),(2,'course22222','course test',1000);

/*Table structure for table `hairdressing` */

DROP TABLE IF EXISTS `hairdressing`;

CREATE TABLE `hairdressing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hairdressing_name` varchar(128) DEFAULT NULL,
  `hairdressing_describe` varchar(256) DEFAULT NULL,
  `expense` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hairdressing` */

insert  into `hairdressing`(`id`,`hairdressing_name`,`hairdressing_describe`,`expense`) values (1,'test','test1',120);

/*Table structure for table `hairdressing_time` */

DROP TABLE IF EXISTS `hairdressing_time`;

CREATE TABLE `hairdressing_time` (
  `id` int(11) NOT NULL,
  `start_time` varchar(32) DEFAULT NULL,
  `end_time` varchar(32) DEFAULT NULL,
  `service_persion_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hairdressing_time` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
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
  `start_date` varchar(31) NOT NULL COMMENT '开始时间 yyyy-MM-dd hh:mm:ss',
  `end_date` varchar(31) NOT NULL COMMENT '结束时间 yyyy-MM-dd hh:mm:ss',
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `orders` */

insert  into `orders`(`order_id`,`account_id`,`order_type`,`animals_id`,`province_id`,`city_id`,`area_id`,`address`,`service_type`,`service_begin`,`service_end`,`start_date`,`end_date`,`room_id`,`course_id`,`cost`,`hairdress_id`,`payment_status`,`status`,`create_time`,`last_modified`,`payment_type`) values (1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09/01/2015','09/17/2015',4,NULL,NULL,NULL,0,-3,'2015-09-04 23:32:20','2015-09-04 23:32:20',NULL),(2,1,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09/07/2015','09/18/2015',NULL,1,NULL,NULL,1,0,'2015-09-04 23:55:44','2015-09-04 23:55:45',NULL),(3,1,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09/10/2015','09/18/2015',4,NULL,NULL,NULL,0,0,'2015-09-04 23:57:15','2015-09-04 23:57:15',NULL),(4,1,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'09/10/2015','09/18/2015',4,NULL,NULL,NULL,0,0,'2015-09-04 23:57:16','2015-09-04 23:57:16',NULL);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

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

/*Data for the table `room` */

insert  into `room`(`id`,`room_name`,`cost`,`weixin_discount`,`discount_30`,`discount_90`,`discount_180`,`room_num`) values (4,'低调奢华房',1,1,1,1,11,1),(5,'高端大气房',2,22,22,2,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
