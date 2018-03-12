-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: qlkhenthuong
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=cp866;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin'),(2,'admin2','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danh_hieu`
--

DROP TABLE IF EXISTS `danh_hieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `danh_hieu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `danh_hieu` varchar(145) NOT NULL,
  `is_ca_nhan` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danh_hieu`
--

LOCK TABLES `danh_hieu` WRITE;
/*!40000 ALTER TABLE `danh_hieu` DISABLE KEYS */;
INSERT INTO `danh_hieu` VALUES (14,'Chiến sĩ thi đua toàn quốc',1),(15,'Lao động tiên tiến ',1),(16,'Chiến sĩ thi đua cấp cơ sở',1),(17,'Chiến sĩ thi đua cấp Bộ',1),(18,'Cờ thi đua Chính phủ',0),(19,'Cờ thi đua cấp bộ',0),(20,'Tập thể Lao động xuất sắc',0);
/*!40000 ALTER TABLE `danh_hieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh_thuc`
--

DROP TABLE IF EXISTS `hinh_thuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hinh_thuc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hinh_thuc` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_thuc`
--

LOCK TABLES `hinh_thuc` WRITE;
/*!40000 ALTER TABLE `hinh_thuc` DISABLE KEYS */;
INSERT INTO `hinh_thuc` VALUES (1,'Huân chương lao động hạng 1'),(2,'Huân chương lao động hạng 2'),(3,'Huân chương lao động hạng 3'),(4,'Nhà giáo nhân dân'),(5,'Nhà giáo ưu tú');
/*!40000 ALTER TABLE `hinh_thuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khen_thuong_ca_nhan`
--

DROP TABLE IF EXISTS `khen_thuong_ca_nhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khen_thuong_ca_nhan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ca_nhan` varchar(10) NOT NULL,
  `id_danh_hieu` int(11) DEFAULT NULL,
  `id_hinh_thuc` int(11) DEFAULT NULL,
  `ngay_kt` date NOT NULL,
  `id_quyet_dinh` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `khenthuong_danhhieu_idx` (`id_danh_hieu`),
  KEY `khenthuong_qd_idx` (`id_quyet_dinh`),
  KEY `khenthuong_hinhthuc_idx` (`id_hinh_thuc`),
  KEY `khenthuong_canhan_idx` (`id_ca_nhan`),
  CONSTRAINT `khenthuong_canhan` FOREIGN KEY (`id_ca_nhan`) REFERENCES `nhan_vien` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `khenthuong_danhhieu` FOREIGN KEY (`id_danh_hieu`) REFERENCES `danh_hieu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `khenthuong_hinhthuc` FOREIGN KEY (`id_hinh_thuc`) REFERENCES `hinh_thuc` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `khenthuong_qd` FOREIGN KEY (`id_quyet_dinh`) REFERENCES `qd_khen_thuong` (`ma_so_kt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khen_thuong_ca_nhan`
--

LOCK TABLES `khen_thuong_ca_nhan` WRITE;
/*!40000 ALTER TABLE `khen_thuong_ca_nhan` DISABLE KEYS */;
/*!40000 ALTER TABLE `khen_thuong_ca_nhan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khen_thuong_tap_the`
--

DROP TABLE IF EXISTS `khen_thuong_tap_the`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khen_thuong_tap_the` (
  `id` varchar(10) NOT NULL,
  `id_tap_the` int(11) NOT NULL,
  `id_danh_hieu` int(11) DEFAULT NULL,
  `id_hinh_thuc` int(11) DEFAULT NULL,
  `ngay_khen_thuong` datetime DEFAULT NULL,
  `id_quyet_dinh` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `khenthuong_dh_idx` (`id_danh_hieu`),
  KEY `khenthuong_tapthe_idx` (`id_tap_the`),
  KEY `khenthuong_ht_idx` (`id_hinh_thuc`),
  KEY `khenthuong_qd_tt_idx` (`id_quyet_dinh`),
  CONSTRAINT `khenthuong_dh` FOREIGN KEY (`id_danh_hieu`) REFERENCES `danh_hieu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `khenthuong_ht` FOREIGN KEY (`id_hinh_thuc`) REFERENCES `hinh_thuc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `khenthuong_qd_tt` FOREIGN KEY (`id_quyet_dinh`) REFERENCES `qd_khen_thuong` (`ma_so_kt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `khenthuong_tapthe` FOREIGN KEY (`id_tap_the`) REFERENCES `tap_the` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khen_thuong_tap_the`
--

LOCK TABLES `khen_thuong_tap_the` WRITE;
/*!40000 ALTER TABLE `khen_thuong_tap_the` DISABLE KEYS */;
INSERT INTO `khen_thuong_tap_the` VALUES ('1',101,NULL,3,'2017-12-07 00:00:00','QD001'),('2',101,NULL,3,'2017-12-07 00:00:00','QD001'),('abc',108,NULL,1,'2017-12-06 00:00:00','QD003'),('abc1',108,18,1,'2017-12-06 00:00:00','QD003'),('abc123',104,14,2,'2017-01-01 00:00:00','QD001'),('abc1234d',101,18,NULL,'2017-12-12 00:00:00','QD001'),('abc1234e',101,18,NULL,'2017-12-07 00:00:00','QD001'),('abc1234ef',101,18,1,'2015-12-08 00:00:00','QD001'),('abc1234efd',101,NULL,1,'2017-12-07 00:00:00','QD001'),('abc2',108,18,1,'2017-12-06 00:00:00','QD003'),('abc3',108,18,1,'2017-12-06 00:00:00','QD003'),('abc4',111,18,3,'2017-12-06 00:00:00','QD003'),('abc5',111,NULL,3,'2017-12-14 00:00:00','QD003'),('abc6',101,NULL,3,'2017-12-14 00:00:00','QD003'),('abc7',101,NULL,3,'2017-12-14 00:00:00','QD003'),('abc8',101,NULL,3,'2017-12-14 00:00:00','QD003'),('abcdefgh',150,NULL,4,'2017-01-01 00:00:00','QD001'),('abcdefghr',150,NULL,4,'2017-01-01 00:00:00','QD001');
/*!40000 ALTER TABLE `khen_thuong_tap_the` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modify_tb`
--

DROP TABLE IF EXISTS `modify_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modify_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ngay_chinh_sua` datetime DEFAULT NULL,
  `tac_vu` varchar(45) DEFAULT NULL,
  `noi_dung` varchar(545) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp866;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modify_tb`
--

LOCK TABLES `modify_tb` WRITE;
/*!40000 ALTER TABLE `modify_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `modify_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `myview`
--

DROP TABLE IF EXISTS `myview`;
/*!50001 DROP VIEW IF EXISTS `myview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `myview` AS SELECT 
 1 AS `id`,
 1 AS `ky_hieu`,
 1 AS `ten_tap_the`,
 1 AS `id_tap_the_cha`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhan_vien` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `chuc_vu_1` varchar(45) DEFAULT NULL,
  `chuc_vu_2` varchar(45) DEFAULT NULL,
  `dien_thoai` int(11) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `id_tap_the` int(11) NOT NULL,
  `gioi_tinh` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nhanvien_tapthe_idx` (`id_tap_the`),
  CONSTRAINT `nhanvien_tapthe` FOREIGN KEY (`id_tap_the`) REFERENCES `tap_the` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES ('DUE00007','Nguyễn Thị D','2017-11-17','Giảng Viên',NULL,984661082,'abc@gmail.com',151,0),('DUE00008','Nguyễn Thị D','2017-11-17','Giảng Viên',NULL,984661082,'abc@gmail.com',151,0),('DUE00009','Nguyễn Thị D','2017-11-17','Giảng Viên',NULL,984661082,'abc@gmail.com',151,0),('DUE00010','Nguyễn Thị D','2017-11-17','Giảng Viên',NULL,984661082,'abc@gmail.com',151,0);
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qd_khen_thuong`
--

DROP TABLE IF EXISTS `qd_khen_thuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qd_khen_thuong` (
  `ma_so_kt` varchar(20) NOT NULL,
  `ten_qd` varchar(145) NOT NULL,
  `reference_link` varchar(55) DEFAULT NULL,
  `ngay` date NOT NULL,
  PRIMARY KEY (`ma_so_kt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qd_khen_thuong`
--

LOCK TABLES `qd_khen_thuong` WRITE;
/*!40000 ALTER TABLE `qd_khen_thuong` DISABLE KEYS */;
INSERT INTO `qd_khen_thuong` VALUES ('QD001','QD khen thuong 1','/file/qd/qd001.pdf','2017-01-10'),('QD003','QD Khen Thuong 2',NULL,'2017-11-10'),('QD004','QD khen thuong 3','C:\\Users\\Ha Nguyen\\Documents\\Student.exe','2018-12-07');
/*!40000 ALTER TABLE `qd_khen_thuong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tap_the`
--

DROP TABLE IF EXISTS `tap_the`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tap_the` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ky_hieu` varchar(10) NOT NULL,
  `ten_tap_the` varchar(100) NOT NULL,
  `id_tap_the_cha` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tapthecon_tapthecha_idx` (`id_tap_the_cha`),
  CONSTRAINT `tapthecon_tapthecha` FOREIGN KEY (`id_tap_the_cha`) REFERENCES `tap_the` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tap_the`
--

LOCK TABLES `tap_the` WRITE;
/*!40000 ALTER TABLE `tap_the` DISABLE KEYS */;
INSERT INTO `tap_the` VALUES (100,'DU','Đại học Đà Nẵng',NULL),(101,'DUT','Dai Hoc Bach Khoa',100),(102,'DUE','Dai Học Kinh Tế',100),(103,'UDE','DH Sư Phạm',100),(104,'UFL','DH Ngoại Ngữ',100),(105,'ADU','Cơ quan đại học Đà Nẵng',100),(106,'DCT','Cao Đẳng Công Nghệ',100),(107,'CIT','Cao Đẳng Công Nghệ Thông Tin',100),(108,'DUBK','Phân Hiệu Kontum',100),(111,'DUT002','Phòng Tài Chính',101),(150,'DUE002','Phong tai chinh 2',102),(151,'DUE001','Phong tai chinh 1',102),(156,'DUE003','Phong tai chinh 4',102),(157,'DUE005','Phong tai chinh 5',102);
/*!40000 ALTER TABLE `tap_the` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'qlkhenthuong'
--
/*!50003 DROP PROCEDURE IF EXISTS `khen_thuong_ca_nhan_lien_tiep` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `khen_thuong_ca_nhan_lien_tiep`(in so_nam_lt int(1))
BEGIN
	declare x INT default 0;
	SET x = 1;
	Create view ktlt_view as( select * from khen_thuong_ca_nhan where year(ngay_kt) = year(CURTIME()));
	WHILE x < so_nam_lt DO
		create or replace view ktlt_view as(select * from khen_thuong_ca_nhan kt 
											inner join ktlt_view kt_view on kt_view.id_ca_nhan = kt.id_ca_nhan
                                            where year(kt_view.ngay_kt) - year(kt.ngay_kt) = 1 );
		SET x = x + 1;
	END WHILE;
    select * from ktlt_view;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `myview`
--

/*!50001 DROP VIEW IF EXISTS `myview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `myview` AS (select `tap_the`.`id` AS `id`,`tap_the`.`ky_hieu` AS `ky_hieu`,`tap_the`.`ten_tap_the` AS `ten_tap_the`,`tap_the`.`id_tap_the_cha` AS `id_tap_the_cha` from `tap_the`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-04  9:04:08
