-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: rm-bp1g59hwo14m0s45kao.mysql.rds.aliyuncs.com    Database: ruoyitest
-- ------------------------------------------------------
-- Server version	5.7.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '25e54276-5b8d-11ed-a700-00163e105cf6:1-391853,
6cb82020-91c9-11ed-b1cd-00163e1e3082:1-2185999,
91e15eaf-0afc-11ed-bb37-00163e0e28c5:1-508465';

--
-- Table structure for table `card_info`
--

DROP TABLE IF EXISTS `card_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scantime` datetime NOT NULL COMMENT '扫描时间',
  `isforeigner` tinyint(1) NOT NULL COMMENT '是否外国人',
  `bz` varchar(50) DEFAULT NULL COMMENT '备注',
  `xtly` varchar(50) DEFAULT NULL COMMENT '系统来源',
  `companyname` varchar(100) NOT NULL COMMENT '公司名称',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `card` varchar(50) NOT NULL COMMENT '身份证号码',
  `sjh` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `scanmethod` varchar(50) NOT NULL COMMENT '扫描方式',
  `scanjd` varchar(200) NOT NULL COMMENT '扫描经度',
  `scanwd` varchar(200) NOT NULL COMMENT '扫描纬度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='扫描记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_info`
--

LOCK TABLES `card_info` WRITE;
/*!40000 ALTER TABLE `card_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--



--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` text COMMENT '分类描述',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户账号',
  `qrcode_category_flag` char(1) DEFAULT 'N' COMMENT '是否二维码绑定时产生的分类',
  `qrcode_id` bigint(20) DEFAULT NULL COMMENT '二维码表ID',
  `created_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='物品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category`
--

LOCK TABLES `item_category` WRITE;
/*!40000 ALTER TABLE `item_category` DISABLE KEYS */;
INSERT INTO `item_category` VALUES (2,'春秋衣服','春季衣服',1,'admin','N',NULL,'admin','2023-10-05 00:00:00','admin','2023-10-05 00:00:00','0');
INSERT INTO `item_category` VALUES (39,'夏季衣服','夏季衣服',1,'admin','N',NULL,'admin','2023-10-09 23:34:05','admin','2023-10-09 23:34:05','0');
INSERT INTO `item_category` VALUES (40,'短袖','短袖',1,'admin','N',NULL,'admin','2023-10-10 19:46:47','admin','2023-10-10 19:46:47','0');
INSERT INTO `item_category` VALUES (41,'夏季短裤','夏季短裤',1,'admin','N',NULL,'admin','2023-10-11 22:22:08','admin','2023-10-11 22:22:08','0');
INSERT INTO `item_category` VALUES (42,'套装','套装',1,'admin','N',NULL,'admin','2023-10-11 22:22:24','admin','2023-10-11 22:22:24','0');
INSERT INTO `item_category` VALUES (43,'当前衣柜衣服','当前衣柜衣服挂着的没有压缩的衣服',1,'admin','N',NULL,'admin','2023-10-11 22:23:09','admin','2023-10-11 22:23:09','0');
INSERT INTO `item_category` VALUES (44,'冬季衣服','冬季衣服',1,'admin','N',NULL,'admin','2023-10-11 22:28:00','admin','2023-10-11 22:28:00','0');
INSERT INTO `item_category` VALUES (45,'药品','药品',1,'admin','N',NULL,'admin','2023-10-17 20:35:32','admin','2023-10-17 20:35:32','0');
INSERT INTO `item_category` VALUES (46,'桌子4号抽屉','桌子4号抽屉',1,'admin','N',NULL,'admin','2023-10-17 20:56:39','admin','2023-10-17 20:56:39','0');
INSERT INTO `item_category` VALUES (47,'桌子2号抽屉','桌子2号抽屉',1,'admin','N',NULL,'admin','2023-10-17 20:51:40','admin','2023-10-17 20:50:06','0');
INSERT INTO `item_category` VALUES (48,'厨房用品','厨房用品',1,'admin','N',NULL,'admin','2023-10-17 21:52:13','admin','2023-10-17 21:52:13','0');
INSERT INTO `item_category` VALUES (49,'数码产品','数码产品',1,'admin','N',NULL,'admin','2023-10-17 21:55:11','admin','2023-10-17 21:55:11','0');
INSERT INTO `item_category` VALUES (50,'桌子3号抽屉','桌子3号抽屉',1,'admin','N',NULL,'admin','2023-10-17 20:52:58','admin','2023-10-17 20:51:06','0');
INSERT INTO `item_category` VALUES (51,'桌子1号抽屉','桌子1号抽屉',1,'admin','N',NULL,'admin','2023-10-17 20:50:39','admin','2023-10-17 20:36:06','0');
INSERT INTO `item_category` VALUES (52,'厨房电器','厨房电器',1,'admin','N',NULL,'admin','2023-10-21 20:55:18','admin','2023-10-21 20:55:18','0');
INSERT INTO `item_category` VALUES (53,'厨房调料','厨房调料',1,'admin','N',NULL,'admin','2023-10-21 20:55:28','admin','2023-10-21 20:55:28','0');
INSERT INTO `item_category` VALUES (54,'洗护用品','洗护用品',1,'admin','N',NULL,'admin','2023-10-21 20:55:36','admin','2023-10-21 20:55:36','0');
INSERT INTO `item_category` VALUES (55,'杂七杂八','杂七杂八',1,'admin','N',NULL,'admin','2023-10-21 20:55:44','admin','2023-10-21 20:55:44','0');
INSERT INTO `item_category` VALUES (56,'零食饮料水果','临时饮料水果',1,'admin','N',NULL,'admin','2023-10-21 20:56:14','admin','2023-10-30 22:25:05','0');
INSERT INTO `item_category` VALUES (57,'冰箱冷冻层','冰箱冷冻层',1,'admin','N',NULL,'admin','2023-10-21 20:56:24','admin','2023-10-21 20:56:24','0');
INSERT INTO `item_category` VALUES (58,'1号收纳箱','1号收纳箱',1,'admin','N',NULL,'admin','2023-10-21 21:44:55','admin','2023-10-21 21:44:55','0');
INSERT INTO `item_category` VALUES (59,'包','包',1,'admin','N',NULL,'admin','2023-10-21 21:48:20','admin','2023-10-21 23:48:20','0');
INSERT INTO `item_category` VALUES (60,'2号收纳箱','2号收纳箱',1,'admin','N',NULL,'admin','2023-10-21 23:02:40','admin','2023-10-21 23:02:40','0');
INSERT INTO `item_category` VALUES (61,'塑料小收纳盒3号','塑料小收纳盒3号',1,'admin','N',NULL,'admin','2023-10-22 17:50:01','admin','2023-10-22 17:50:48','0');
INSERT INTO `item_category` VALUES (62,'塑料小收纳盒2号','塑料小收纳盒2号',1,'admin','N',NULL,'admin','2023-10-22 17:50:35','admin','2023-10-22 17:50:35','0');
INSERT INTO `item_category` VALUES (63,'塑料小收纳盒1号','塑料小收纳盒1号',1,'admin','N',NULL,'admin','2023-10-22 17:50:48','admin','2023-10-22 17:50:01','0');
INSERT INTO `item_category` VALUES (64,'衣服1号收纳袋','衣服1号收纳袋',1,'admin','N',NULL,'admin','2023-10-22 19:15:57','admin','2023-10-22 19:15:57','0');
INSERT INTO `item_category` VALUES (65,'衣服2号收纳袋','衣服2号收纳袋',1,'admin','N',NULL,'admin','2023-10-22 19:16:05','admin','2023-10-22 19:16:05','0');
INSERT INTO `item_category` VALUES (66,'衣服3号收纳袋','衣服3号收纳袋',1,'admin','N',NULL,'admin','2023-10-23 21:39:53','admin','2023-10-23 21:39:53','0');
INSERT INTO `item_category` VALUES (67,'衣服4号收纳袋','衣服4号收纳袋',1,'admin','N',NULL,'admin','2023-10-23 21:40:16','admin','2023-10-23 21:40:16','0');
INSERT INTO `item_category` VALUES (68,'黑色柜子','黑色柜子',1,'admin','N',NULL,'admin','2023-10-24 21:34:20','admin','2023-10-26 21:34:20','0');
INSERT INTO `item_category` VALUES (69,'洗发露牙膏等消耗品1号收纳箱','洗发露牙膏等消耗品',1,'admin','N',NULL,'admin','2023-10-24 21:59:31','admin','2023-10-26 21:59:31','0');
INSERT INTO `item_category` VALUES (70,'衣服5号收纳袋','衣服5号收纳袋',1,'admin','N',NULL,'admin','2023-10-25 21:58:02','admin','2023-10-25 21:58:02','0');
INSERT INTO `item_category` VALUES (71,'衣服6号收纳袋','衣服6号收纳袋',1,'admin','N',NULL,'admin','2023-10-25 22:07:48','admin','2023-10-25 22:07:48','0');
INSERT INTO `item_category` VALUES (72,'衣服7号收纳袋','衣服7号收纳袋',1,'admin','N',NULL,'admin','2023-10-25 22:19:16','admin','2023-10-25 22:19:16','0');
INSERT INTO `item_category` VALUES (73,'超大极低使用频率收纳箱','里面的东西使用极少，几乎不使用',1,'admin','N',NULL,'admin','2023-10-28 20:16:38','admin','2023-10-28 20:16:38','0');
/*!40000 ALTER TABLE `item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_category_relation`
--

DROP TABLE IF EXISTS `item_category_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_category_relation` (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` bigint(20) NOT NULL COMMENT '物品ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户账号',
  `created_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=694 DEFAULT CHARSET=utf8 COMMENT='物品与分类关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category_relation`
--

LOCK TABLES `item_category_relation` WRITE;
/*!40000 ALTER TABLE `item_category_relation` DISABLE KEYS */;
INSERT INTO `item_category_relation` VALUES (691,115,71,1,'admin','admin','2023-12-18 21:34:00','admin','2023-12-18 21:34:00','0');
INSERT INTO `item_category_relation` VALUES (692,89,44,1,'admin','admin','2023-12-18 21:39:56','admin','2023-12-18 21:39:56','0');
INSERT INTO `item_category_relation` VALUES (693,89,43,1,'admin','admin','2023-12-18 21:39:56','admin','2023-12-18 21:39:56','0');
/*!40000 ALTER TABLE `item_category_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_image`
--

DROP TABLE IF EXISTS `item_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` bigint(20) NOT NULL COMMENT '物品ID',
  `image_name` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `image_size` int(11) NOT NULL COMMENT '图片大小（KB）',
  `image_url` varchar(500) NOT NULL COMMENT '图片URL',
  `object_key` varchar(100) NOT NULL COMMENT '对象键',
  `upload_platform` varchar(10) DEFAULT NULL COMMENT '上传平台',
  `created_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8 COMMENT='物品图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_image`
--

LOCK TABLES `item_image` WRITE;
/*!40000 ALTER TABLE `item_image` DISABLE KEYS */;
INSERT INTO `item_image` VALUES (357,350,'IMG_20231118_171434.jpg',2955331,'https://1-1252076141.cos.ap-shanghai.myqcloud.com/a102d2ea-3809-4c28-IMG_20231118_171434.jpg','a102d2ea-3809-4c28-IMG_20231118_171434.jpg','COS','admin','2023-11-18 17:14:47','admin','2023-11-18 17:14:47','0');
INSERT INTO `item_image` VALUES (358,351,'IMG_20231118_171637.jpg',2399364,'https://1-1252076141.cos.ap-shanghai.myqcloud.com/099bc328-9a42-464e-IMG_20231118_171637.jpg','099bc328-9a42-464e-IMG_20231118_171637.jpg','COS','admin','2023-11-18 17:16:45','admin','2023-11-18 17:16:45','0');
INSERT INTO `item_image` VALUES (359,352,'IMG_20231218_205913.jpg',1787508,'https://1-1252076141.cos.ap-shanghai.myqcloud.com/dd3b0650-3371-4945-IMG_20231218_205913.jpg','dd3b0650-3371-4945-IMG_20231218_205913.jpg','COS','admin','2023-12-18 21:00:53','admin','2023-12-18 21:00:53','0');
/*!40000 ALTER TABLE `item_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_information`
--

DROP TABLE IF EXISTS `item_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_information` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '物品的描述信息',
  `has_image` char(1) DEFAULT 'N' COMMENT '是否上传物品图片',
  `purchase_price` decimal(10,2) DEFAULT NULL COMMENT '购买价格',
  `purchase_date` date DEFAULT NULL COMMENT '购买日期',
  `purchase_quantity` int(11) DEFAULT NULL COMMENT '购买的数量',
  `purchase_platform` varchar(100) DEFAULT NULL COMMENT '购买平台，1京东，2淘宝，3拼多多，4淘特，5一号店，6唯品会，7抖音，8快手，9饿了么，10叮咚买菜，11，其它',
  `order_id` bigint(30) DEFAULT NULL COMMENT '订单号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(11) DEFAULT NULL COMMENT '保质期（天数）',
  `expiration_date` date DEFAULT NULL COMMENT '有效期至',
  `storage_location` varchar(500) DEFAULT NULL COMMENT '物品存放的位置',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注信息',
  `created_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '逻辑删除',
  `qr_code_generated` char(1) DEFAULT 'N' COMMENT '是否生成二维码',
  `item_status` int(11) DEFAULT NULL COMMENT '物品状态，1全新未使用，2使用中，3使用完，4送人，5出售',
  `usage_rate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '使用百分率',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8 COMMENT='物品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_information`
--

LOCK TABLES `item_information` WRITE;
/*!40000 ALTER TABLE `item_information` DISABLE KEYS */;
INSERT INTO `item_information` VALUES (350,'慢严舒柠润喉糖10粒一盒',NULL,'Y',1.00,NULL,2,'1',NULL,'2023-10-28',24,'2025-10-28','桌子4号抽屉',NULL,'admin','2023-11-18 17:14:28',NULL,1,'admin','2023-11-18 17:14:45','0','N',1,0);
INSERT INTO `item_information` VALUES (351,'网易严选一次性手套100',NULL,'Y',NULL,'2023-11-10',2,'11',NULL,NULL,NULL,NULL,'2号收纳箱',NULL,'admin','2023-11-18 17:16:28',NULL,1,'admin','2023-11-18 17:16:43','0','N',1,0);
INSERT INTO `item_information` VALUES (352,'乔丹春秋运动外套黑白色',NULL,'Y',107.00,'2023-11-22',1,'7',NULL,NULL,NULL,NULL,'收纳袋3',NULL,'admin','2023-12-18 21:00:41',NULL,1,'admin','2023-12-18 21:00:49','0','N',2,0);
/*!40000 ALTER TABLE `item_information` ENABLE KEYS */;
UNLOCK TABLES;



DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) NOT NULL COMMENT '状态',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) NOT NULL COMMENT '任务组名',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2023-02-25 23:53:54','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2023-02-25 23:53:54','',NULL,'初始化密码 123456');
INSERT INTO `sys_config` VALUES (3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2023-02-25 23:53:54','',NULL,'深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4,'账号自助-验证码开关','sys.account.captchaEnabled','true','Y','admin','2023-02-25 23:53:54','',NULL,'是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2023-02-25 23:53:54','',NULL,'是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6,'用户登录-黑名单列表','sys.login.blackIPList','','Y','admin','2023-02-25 23:53:54','',NULL,'设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
INSERT INTO `sys_dept` VALUES (109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-02-25 23:53:54','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2023-02-25 23:53:54','',NULL,'性别男');
INSERT INTO `sys_dict_data` VALUES (2,2,'女','1','sys_user_sex','','','N','0','admin','2023-02-25 23:53:54','',NULL,'性别女');
INSERT INTO `sys_dict_data` VALUES (3,3,'未知','2','sys_user_sex','','','N','0','admin','2023-02-25 23:53:54','',NULL,'性别未知');
INSERT INTO `sys_dict_data` VALUES (4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2023-02-25 23:53:54','',NULL,'显示菜单');
INSERT INTO `sys_dict_data` VALUES (5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2023-02-25 23:53:54','',NULL,'正常状态');
INSERT INTO `sys_dict_data` VALUES (7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'停用状态');
INSERT INTO `sys_dict_data` VALUES (8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2023-02-25 23:53:54','',NULL,'正常状态');
INSERT INTO `sys_dict_data` VALUES (9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'停用状态');
INSERT INTO `sys_dict_data` VALUES (10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2023-02-25 23:53:54','',NULL,'默认分组');
INSERT INTO `sys_dict_data` VALUES (11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2023-02-25 23:53:54','',NULL,'系统分组');
INSERT INTO `sys_dict_data` VALUES (12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2023-02-25 23:53:54','',NULL,'系统默认是');
INSERT INTO `sys_dict_data` VALUES (13,2,'否','N','sys_yes_no','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'系统默认否');
INSERT INTO `sys_dict_data` VALUES (14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2023-02-25 23:53:54','',NULL,'通知');
INSERT INTO `sys_dict_data` VALUES (15,2,'公告','2','sys_notice_type','','success','N','0','admin','2023-02-25 23:53:54','',NULL,'公告');
INSERT INTO `sys_dict_data` VALUES (16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2023-02-25 23:53:54','',NULL,'正常状态');
INSERT INTO `sys_dict_data` VALUES (17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'关闭状态');
INSERT INTO `sys_dict_data` VALUES (18,99,'其他','0','sys_oper_type','','info','N','0','admin','2023-02-25 23:53:54','',NULL,'其他操作');
INSERT INTO `sys_dict_data` VALUES (19,1,'新增','1','sys_oper_type','','info','N','0','admin','2023-02-25 23:53:54','',NULL,'新增操作');
INSERT INTO `sys_dict_data` VALUES (20,2,'修改','2','sys_oper_type','','info','N','0','admin','2023-02-25 23:53:54','',NULL,'修改操作');
INSERT INTO `sys_dict_data` VALUES (21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'删除操作');
INSERT INTO `sys_dict_data` VALUES (22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2023-02-25 23:53:54','',NULL,'授权操作');
INSERT INTO `sys_dict_data` VALUES (23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2023-02-25 23:53:54','',NULL,'导出操作');
INSERT INTO `sys_dict_data` VALUES (24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2023-02-25 23:53:54','',NULL,'导入操作');
INSERT INTO `sys_dict_data` VALUES (25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'强退操作');
INSERT INTO `sys_dict_data` VALUES (26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2023-02-25 23:53:54','',NULL,'生成操作');
INSERT INTO `sys_dict_data` VALUES (27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'清空操作');
INSERT INTO `sys_dict_data` VALUES (28,1,'成功','0','sys_common_status','','primary','N','0','admin','2023-02-25 23:53:54','',NULL,'正常状态');
INSERT INTO `sys_dict_data` VALUES (29,2,'失败','1','sys_common_status','','danger','N','0','admin','2023-02-25 23:53:54','',NULL,'停用状态');
INSERT INTO `sys_dict_data` VALUES (101,0,'京东','1','item_infomation',NULL,'default','N','0','admin','2023-03-25 22:59:05','admin','2023-03-25 23:00:45','京东');
INSERT INTO `sys_dict_data` VALUES (102,1,'淘宝','2','item_infomation',NULL,'default','N','0','admin','2023-03-25 22:59:45','admin','2023-03-25 23:00:55',NULL);
INSERT INTO `sys_dict_data` VALUES (103,2,'拼多多','3','item_infomation',NULL,'default','N','0','admin','2023-03-25 23:00:02','admin','2023-03-25 23:01:04',NULL);
INSERT INTO `sys_dict_data` VALUES (104,3,'淘特','4','item_infomation',NULL,'default','N','0','admin','2023-03-25 23:00:21','admin','2023-03-25 23:01:12',NULL);
INSERT INTO `sys_dict_data` VALUES (105,0,'全新未使用','1','item_information',NULL,'default','N','0','admin','2023-03-25 23:03:39','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (106,1,'使用中','2','item_information',NULL,'default','N','0','admin','2023-03-25 23:03:53','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (107,0,'有效','0','del_flag',NULL,'default','N','0','admin','2023-06-06 22:52:28','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (108,1,'删除','1','del_flag',NULL,'default','N','0','admin','2023-06-06 22:52:48','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (109,0,'个人物品管理系统','0','belongs_projects',NULL,'default','N','0','admin','2023-06-25 21:04:01','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (110,1,'金鳞计划','1','belongs_projects',NULL,'default','N','0','admin','2023-06-25 21:04:12','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (111,2,'学习前端','2','belongs_projects',NULL,'default','N','0','admin','2023-08-29 22:07:56','admin','2023-08-29 22:13:12',NULL);
INSERT INTO `sys_dict_data` VALUES (112,7,'其它','8','item_infomation',NULL,'default','N','0','admin','2023-10-09 22:36:34','admin','2023-10-10 20:39:48',NULL);
INSERT INTO `sys_dict_data` VALUES (113,4,'抖音','5','item_infomation',NULL,'default','N','0','admin','2023-10-10 20:31:00','admin','2023-10-10 20:32:26',NULL);
INSERT INTO `sys_dict_data` VALUES (114,5,'实体店','6','item_infomation',NULL,'default','N','0','admin','2023-10-10 20:32:00','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (115,6,'唯品会','7','item_infomation',NULL,'default','N','0','admin','2023-10-10 20:39:40','',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (116,8,'一号店','9','item_infomation',NULL,'default','N','0','admin','2023-10-29 20:42:16','admin','2023-10-29 20:43:37',NULL);
INSERT INTO `sys_dict_data` VALUES (117,9,'叮咚买菜','10','item_infomation',NULL,'default','N','0','admin','2023-10-29 20:42:37','admin','2023-10-29 20:43:48',NULL);
INSERT INTO `sys_dict_data` VALUES (118,10,'网易严选','11','item_infomation',NULL,'default','N','0','admin','2023-10-29 20:43:00','admin','2023-10-29 20:44:00',NULL);
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2023-02-25 23:53:54','',NULL,'用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2,'菜单状态','sys_show_hide','0','admin','2023-02-25 23:53:54','',NULL,'菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3,'系统开关','sys_normal_disable','0','admin','2023-02-25 23:53:54','',NULL,'系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4,'任务状态','sys_job_status','0','admin','2023-02-25 23:53:54','',NULL,'任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5,'任务分组','sys_job_group','0','admin','2023-02-25 23:53:54','',NULL,'任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6,'系统是否','sys_yes_no','0','admin','2023-02-25 23:53:54','',NULL,'系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7,'通知类型','sys_notice_type','0','admin','2023-02-25 23:53:54','',NULL,'通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8,'通知状态','sys_notice_status','0','admin','2023-02-25 23:53:54','',NULL,'通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9,'操作类型','sys_oper_type','0','admin','2023-02-25 23:53:54','',NULL,'操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10,'系统状态','sys_common_status','0','admin','2023-02-25 23:53:54','',NULL,'登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100,'购买平台','item_infomation','0','admin','2023-03-25 22:54:23','',NULL,'购买平台列表');
INSERT INTO `sys_dict_type` VALUES (101,'物品状态','item_information','0','admin','2023-03-25 23:03:17','',NULL,NULL);
INSERT INTO `sys_dict_type` VALUES (102,'逻辑删除','del_flag','0','admin','2023-06-06 22:51:38','',NULL,'逻辑删除字段');
INSERT INTO `sys_dict_type` VALUES (103,'所属项目','belongs_projects','0','admin','2023-06-25 21:02:21','admin','2023-06-25 21:03:03','所属项目的字典\n');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_job` VALUES (2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_job` VALUES (3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2023-02-25 23:53:54','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (255,'admin','127.0.0.1','内网IP','Chrome 12','Windows 10','0','登录成功','2024-02-05 15:19:06');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2127 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system','admin','2023-02-25 23:53:54','',NULL,'系统管理目录');
INSERT INTO `sys_menu` VALUES (2,'系统监控',0,2,'monitor',NULL,'',1,0,'M','0','0','','monitor','admin','2023-02-25 23:53:54','',NULL,'系统监控目录');
INSERT INTO `sys_menu` VALUES (3,'系统工具',0,3,'tool',NULL,'',1,0,'M','0','0','','tool','admin','2023-02-25 23:53:54','',NULL,'系统工具目录');
INSERT INTO `sys_menu` VALUES (4,'若依官网',0,4,'http://ruoyi.vip',NULL,'',0,0,'M','0','1','','guide','admin','2023-02-25 23:53:54','',NULL,'若依官网地址');
INSERT INTO `sys_menu` VALUES (100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user','admin','2023-02-25 23:53:54','',NULL,'用户管理菜单');
INSERT INTO `sys_menu` VALUES (101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples','admin','2023-02-25 23:53:54','',NULL,'角色管理菜单');
INSERT INTO `sys_menu` VALUES (102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table','admin','2023-02-25 23:53:54','',NULL,'菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree','admin','2023-02-25 23:53:54','',NULL,'部门管理菜单');
INSERT INTO `sys_menu` VALUES (104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post','admin','2023-02-25 23:53:54','',NULL,'岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict','admin','2023-02-25 23:53:54','',NULL,'字典管理菜单');
INSERT INTO `sys_menu` VALUES (106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit','admin','2023-02-25 23:53:54','',NULL,'参数设置菜单');
INSERT INTO `sys_menu` VALUES (107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message','admin','2023-02-25 23:53:54','',NULL,'通知公告菜单');
INSERT INTO `sys_menu` VALUES (108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log','admin','2023-02-25 23:53:54','',NULL,'日志管理菜单');
INSERT INTO `sys_menu` VALUES (109,'在线用户',2,1,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online','admin','2023-02-25 23:53:54','',NULL,'在线用户菜单');
INSERT INTO `sys_menu` VALUES (110,'定时任务',2,2,'job','monitor/job/index','',1,0,'C','0','0','monitor:job:list','job','admin','2023-02-25 23:53:54','',NULL,'定时任务菜单');
INSERT INTO `sys_menu` VALUES (111,'数据监控',2,3,'druid','monitor/druid/index','',1,0,'C','0','0','monitor:druid:list','druid','admin','2023-02-25 23:53:54','',NULL,'数据监控菜单');
INSERT INTO `sys_menu` VALUES (112,'服务监控',2,4,'server','monitor/server/index','',1,0,'C','0','0','monitor:server:list','server','admin','2023-02-25 23:53:54','',NULL,'服务监控菜单');
INSERT INTO `sys_menu` VALUES (113,'缓存监控',2,5,'cache','monitor/cache/index','',1,0,'C','0','0','monitor:cache:list','redis','admin','2023-02-25 23:53:54','',NULL,'缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114,'缓存列表',2,6,'cacheList','monitor/cache/list','',1,0,'C','0','0','monitor:cache:list','redis-list','admin','2023-02-25 23:53:54','',NULL,'缓存列表菜单');
INSERT INTO `sys_menu` VALUES (115,'表单构建',3,1,'build','tool/build/index','',1,0,'C','0','0','tool:build:list','build','admin','2023-02-25 23:53:54','',NULL,'表单构建菜单');
INSERT INTO `sys_menu` VALUES (116,'代码生成',3,2,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code','admin','2023-02-25 23:53:54','',NULL,'代码生成菜单');
INSERT INTO `sys_menu` VALUES (117,'系统接口',3,3,'swagger','tool/swagger/index','',1,0,'C','0','0','tool:swagger:list','swagger','admin','2023-02-25 23:53:54','',NULL,'系统接口菜单');
INSERT INTO `sys_menu` VALUES (500,'操作日志',108,1,'operlog','monitor/operlog/index','',1,0,'C','0','0','monitor:operlog:list','form','admin','2023-02-25 23:53:54','',NULL,'操作日志菜单');
INSERT INTO `sys_menu` VALUES (501,'登录日志',108,2,'logininfor','monitor/logininfor/index','',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2023-02-25 23:53:54','',NULL,'登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1001,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1002,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1003,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1004,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1005,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1006,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1007,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1008,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1009,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1010,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1011,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1012,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1013,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1014,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1015,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1016,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1017,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1018,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1019,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1020,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1021,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1022,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1023,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1024,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1025,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1026,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1027,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1028,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1029,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1030,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1031,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1032,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1033,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1034,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1035,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1036,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1037,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1038,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1039,'操作查询',500,1,'#','','',1,0,'F','0','0','monitor:operlog:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1040,'操作删除',500,2,'#','','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1041,'日志导出',500,3,'#','','',1,0,'F','0','0','monitor:operlog:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1042,'登录查询',501,1,'#','','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1043,'登录删除',501,2,'#','','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1044,'日志导出',501,3,'#','','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1045,'账户解锁',501,4,'#','','',1,0,'F','0','0','monitor:logininfor:unlock','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1046,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1047,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1048,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1049,'任务查询',110,1,'#','','',1,0,'F','0','0','monitor:job:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1050,'任务新增',110,2,'#','','',1,0,'F','0','0','monitor:job:add','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1051,'任务修改',110,3,'#','','',1,0,'F','0','0','monitor:job:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1052,'任务删除',110,4,'#','','',1,0,'F','0','0','monitor:job:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1053,'状态修改',110,5,'#','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1054,'任务导出',110,6,'#','','',1,0,'F','0','0','monitor:job:export','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1055,'生成查询',116,1,'#','','',1,0,'F','0','0','tool:gen:query','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1056,'生成修改',116,2,'#','','',1,0,'F','0','0','tool:gen:edit','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1057,'生成删除',116,3,'#','','',1,0,'F','0','0','tool:gen:remove','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1058,'导入代码',116,4,'#','','',1,0,'F','0','0','tool:gen:import','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1059,'预览代码',116,5,'#','','',1,0,'F','0','0','tool:gen:preview','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (1060,'生成代码',116,6,'#','','',1,0,'F','0','0','tool:gen:code','#','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_menu` VALUES (2039,'个人菜单',0,0,'11',NULL,NULL,1,0,'M','0','0',NULL,'bug','admin','2023-04-05 22:19:58','',NULL,'');
INSERT INTO `sys_menu` VALUES (2040,'物品信息',2039,1,'information','system/information/index',NULL,1,0,'C','0','0','system:information:list','#','admin','2023-04-05 22:21:29','',NULL,'物品信息菜单');
INSERT INTO `sys_menu` VALUES (2041,'物品信息查询',2040,1,'#','',NULL,1,0,'F','0','0','system:information:query','#','admin','2023-04-05 22:21:29','',NULL,'');
INSERT INTO `sys_menu` VALUES (2042,'物品信息新增',2040,2,'#','',NULL,1,0,'F','0','0','system:information:add','#','admin','2023-04-05 22:21:29','',NULL,'');
INSERT INTO `sys_menu` VALUES (2043,'物品信息修改',2040,3,'#','',NULL,1,0,'F','0','0','system:information:edit','#','admin','2023-04-05 22:21:29','',NULL,'');
INSERT INTO `sys_menu` VALUES (2044,'物品信息删除',2040,4,'#','',NULL,1,0,'F','0','0','system:information:remove','#','admin','2023-04-05 22:21:29','',NULL,'');
INSERT INTO `sys_menu` VALUES (2045,'物品信息导出',2040,5,'#','',NULL,1,0,'F','0','0','system:information:export','#','admin','2023-04-05 22:21:29','',NULL,'');
INSERT INTO `sys_menu` VALUES (2064,'物品分类',2039,1,'category','system/category/index',NULL,1,0,'C','0','0','system:category:list','#','admin','2023-05-29 20:36:21','',NULL,'物品分类菜单');
INSERT INTO `sys_menu` VALUES (2065,'物品分类查询',2064,1,'#','',NULL,1,0,'F','0','0','system:category:query','#','admin','2023-05-29 20:36:21','',NULL,'');
INSERT INTO `sys_menu` VALUES (2066,'物品分类新增',2064,2,'#','',NULL,1,0,'F','0','0','system:category:add','#','admin','2023-05-29 20:36:21','',NULL,'');
INSERT INTO `sys_menu` VALUES (2067,'物品分类修改',2064,3,'#','',NULL,1,0,'F','0','0','system:category:edit','#','admin','2023-05-29 20:36:21','',NULL,'');
INSERT INTO `sys_menu` VALUES (2068,'物品分类删除',2064,4,'#','',NULL,1,0,'F','0','0','system:category:remove','#','admin','2023-05-29 20:36:21','',NULL,'');
INSERT INTO `sys_menu` VALUES (2069,'物品分类导出',2064,5,'#','',NULL,1,0,'F','0','0','system:category:export','#','admin','2023-05-29 20:36:21','',NULL,'');
INSERT INTO `sys_menu` VALUES (2070,'物品与分类关联',2039,1,'relation','system/relation/index',NULL,1,0,'C','0','0','system:relation:list','#','admin','2023-05-29 22:31:01','',NULL,'物品与分类关联菜单');
INSERT INTO `sys_menu` VALUES (2071,'物品与分类关联查询',2070,1,'#','',NULL,1,0,'F','0','0','system:relation:query','#','admin','2023-05-29 22:31:01','',NULL,'');
INSERT INTO `sys_menu` VALUES (2072,'物品与分类关联新增',2070,2,'#','',NULL,1,0,'F','0','0','system:relation:add','#','admin','2023-05-29 22:31:01','',NULL,'');
INSERT INTO `sys_menu` VALUES (2073,'物品与分类关联修改',2070,3,'#','',NULL,1,0,'F','0','0','system:relation:edit','#','admin','2023-05-29 22:31:01','',NULL,'');
INSERT INTO `sys_menu` VALUES (2074,'物品与分类关联删除',2070,4,'#','',NULL,1,0,'F','0','0','system:relation:remove','#','admin','2023-05-29 22:31:01','',NULL,'');
INSERT INTO `sys_menu` VALUES (2075,'物品与分类关联导出',2070,5,'#','',NULL,1,0,'F','0','0','system:relation:export','#','admin','2023-05-29 22:31:01','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2018-07-01 若依新版本发布啦','2',_binary '新版本内容','0','admin','2023-02-25 23:53:54','',NULL,'管理员');
INSERT INTO `sys_notice` VALUES (2,'维护通知：2018-07-01 若依系统凌晨维护','1',_binary '维护内容','0','admin','2023-02-25 23:53:54','',NULL,'管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1161 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (1159,'代码生成',6,'com.ruoyi.generator.controller.GenController.importTableSave()','POST',1,'admin',NULL,'/tool/gen/importTable','127.0.0.1','内网IP','\"wx_last_sendtime\"','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-01-15 13:06:44',712);
INSERT INTO `sys_oper_log` VALUES (1160,'代码生成',8,'com.ruoyi.generator.controller.GenController.batchGenCode()','GET',1,'admin',NULL,'/tool/gen/batchGenCode','127.0.0.1','内网IP','{\"tables\":\"wx_last_sendtime\"}',NULL,0,NULL,'2024-01-15 13:06:51',851);
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_post` VALUES (2,'se','项目经理',2,'0','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_post` VALUES (3,'hr','人力资源',3,'0','admin','2023-02-25 23:53:54','',NULL,'');
INSERT INTO `sys_post` VALUES (4,'user','普通员工',4,'0','admin','2023-02-25 23:53:54','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2023-02-25 23:53:54','',NULL,'超级管理员');
INSERT INTO `sys_role` VALUES (2,'普通角色','common',2,'2',1,1,'0','0','admin','2023-02-25 23:53:54','',NULL,'普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100);
INSERT INTO `sys_role_dept` VALUES (2,101);
INSERT INTO `sys_role_dept` VALUES (2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1);
INSERT INTO `sys_role_menu` VALUES (2,2);
INSERT INTO `sys_role_menu` VALUES (2,3);
INSERT INTO `sys_role_menu` VALUES (2,4);
INSERT INTO `sys_role_menu` VALUES (2,100);
INSERT INTO `sys_role_menu` VALUES (2,101);
INSERT INTO `sys_role_menu` VALUES (2,102);
INSERT INTO `sys_role_menu` VALUES (2,103);
INSERT INTO `sys_role_menu` VALUES (2,104);
INSERT INTO `sys_role_menu` VALUES (2,105);
INSERT INTO `sys_role_menu` VALUES (2,106);
INSERT INTO `sys_role_menu` VALUES (2,107);
INSERT INTO `sys_role_menu` VALUES (2,108);
INSERT INTO `sys_role_menu` VALUES (2,109);
INSERT INTO `sys_role_menu` VALUES (2,110);
INSERT INTO `sys_role_menu` VALUES (2,111);
INSERT INTO `sys_role_menu` VALUES (2,112);
INSERT INTO `sys_role_menu` VALUES (2,113);
INSERT INTO `sys_role_menu` VALUES (2,114);
INSERT INTO `sys_role_menu` VALUES (2,115);
INSERT INTO `sys_role_menu` VALUES (2,116);
INSERT INTO `sys_role_menu` VALUES (2,117);
INSERT INTO `sys_role_menu` VALUES (2,500);
INSERT INTO `sys_role_menu` VALUES (2,501);
INSERT INTO `sys_role_menu` VALUES (2,1000);
INSERT INTO `sys_role_menu` VALUES (2,1001);
INSERT INTO `sys_role_menu` VALUES (2,1002);
INSERT INTO `sys_role_menu` VALUES (2,1003);
INSERT INTO `sys_role_menu` VALUES (2,1004);
INSERT INTO `sys_role_menu` VALUES (2,1005);
INSERT INTO `sys_role_menu` VALUES (2,1006);
INSERT INTO `sys_role_menu` VALUES (2,1007);
INSERT INTO `sys_role_menu` VALUES (2,1008);
INSERT INTO `sys_role_menu` VALUES (2,1009);
INSERT INTO `sys_role_menu` VALUES (2,1010);
INSERT INTO `sys_role_menu` VALUES (2,1011);
INSERT INTO `sys_role_menu` VALUES (2,1012);
INSERT INTO `sys_role_menu` VALUES (2,1013);
INSERT INTO `sys_role_menu` VALUES (2,1014);
INSERT INTO `sys_role_menu` VALUES (2,1015);
INSERT INTO `sys_role_menu` VALUES (2,1016);
INSERT INTO `sys_role_menu` VALUES (2,1017);
INSERT INTO `sys_role_menu` VALUES (2,1018);
INSERT INTO `sys_role_menu` VALUES (2,1019);
INSERT INTO `sys_role_menu` VALUES (2,1020);
INSERT INTO `sys_role_menu` VALUES (2,1021);
INSERT INTO `sys_role_menu` VALUES (2,1022);
INSERT INTO `sys_role_menu` VALUES (2,1023);
INSERT INTO `sys_role_menu` VALUES (2,1024);
INSERT INTO `sys_role_menu` VALUES (2,1025);
INSERT INTO `sys_role_menu` VALUES (2,1026);
INSERT INTO `sys_role_menu` VALUES (2,1027);
INSERT INTO `sys_role_menu` VALUES (2,1028);
INSERT INTO `sys_role_menu` VALUES (2,1029);
INSERT INTO `sys_role_menu` VALUES (2,1030);
INSERT INTO `sys_role_menu` VALUES (2,1031);
INSERT INTO `sys_role_menu` VALUES (2,1032);
INSERT INTO `sys_role_menu` VALUES (2,1033);
INSERT INTO `sys_role_menu` VALUES (2,1034);
INSERT INTO `sys_role_menu` VALUES (2,1035);
INSERT INTO `sys_role_menu` VALUES (2,1036);
INSERT INTO `sys_role_menu` VALUES (2,1037);
INSERT INTO `sys_role_menu` VALUES (2,1038);
INSERT INTO `sys_role_menu` VALUES (2,1039);
INSERT INTO `sys_role_menu` VALUES (2,1040);
INSERT INTO `sys_role_menu` VALUES (2,1041);
INSERT INTO `sys_role_menu` VALUES (2,1042);
INSERT INTO `sys_role_menu` VALUES (2,1043);
INSERT INTO `sys_role_menu` VALUES (2,1044);
INSERT INTO `sys_role_menu` VALUES (2,1045);
INSERT INTO `sys_role_menu` VALUES (2,1046);
INSERT INTO `sys_role_menu` VALUES (2,1047);
INSERT INTO `sys_role_menu` VALUES (2,1048);
INSERT INTO `sys_role_menu` VALUES (2,1049);
INSERT INTO `sys_role_menu` VALUES (2,1050);
INSERT INTO `sys_role_menu` VALUES (2,1051);
INSERT INTO `sys_role_menu` VALUES (2,1052);
INSERT INTO `sys_role_menu` VALUES (2,1053);
INSERT INTO `sys_role_menu` VALUES (2,1054);
INSERT INTO `sys_role_menu` VALUES (2,1055);
INSERT INTO `sys_role_menu` VALUES (2,1056);
INSERT INTO `sys_role_menu` VALUES (2,1057);
INSERT INTO `sys_role_menu` VALUES (2,1058);
INSERT INTO `sys_role_menu` VALUES (2,1059);
INSERT INTO `sys_role_menu` VALUES (2,1060);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','若依','00','ry@163.com','15888888888','1','/profile/avatar/2023/10/30/blob_20231030223555A001.png','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2024-02-05 15:19:08','admin','2023-02-25 23:53:54','','2024-02-05 15:19:06','管理员');
INSERT INTO `sys_user` VALUES (2,105,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2023-02-25 23:53:54','admin','2023-02-25 23:53:54','',NULL,'测试员');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1);
INSERT INTO `sys_user_post` VALUES (2,2);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1);
INSERT INTO `sys_user_role` VALUES (2,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_time_record`
--


--
-- Dumping routines for database 'ruoyitest'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-05 15:32:45
