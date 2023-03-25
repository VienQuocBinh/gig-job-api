-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 13.228.218.62    Database: gig-job
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('0liRAgNrsvhlGAJEmcQIwEVmom72','2022-03-13 00:00:00','shop7@gmai.com',NULL,_binary '\0',_binary '\0','\0','0268974562',0,'2022-03-13 00:00:00','shop7@gmai.com'),('3Djc7gbCKkUoftyckXKkOkpFp5G2','2022-03-13 00:00:00','shop4@gmail.com',NULL,_binary '\0',_binary '\0','123','0325698547',0,'2022-03-13 00:00:00','shop4@gmail.com'),('3vTwMrencefts7dy5oc4tZ1gGiG3','2023-03-23 08:22:10','quochdase161320@fpt.edu.vn',NULL,_binary '\0',_binary '\0',NULL,NULL,NULL,'2023-03-23 08:22:10','quochdase161320@fpt.edu.vn'),('AFv78KupgVVAHl7BLMyNmaomkxG2','2022-02-10 00:00:00','quyendqse150975@fpt.edu.vn','test',_binary '\0',_binary '\0','123456','0956987456',1,'2023-03-16 01:11:25','letriquyen'),('dCYnSBeA0iMcLu0rdeyIlIxrkfT2','2023-03-22 16:22:39','bmlquangquyen1102@gmail.com',NULL,_binary '\0',_binary '\0',NULL,'0909281717',0,'2023-03-22 16:22:39','quyendao'),('j9u5Tkj2cRP4ea6rKRnFsuEGjRn2','2023-03-22 16:27:03','quanan@gmail.com','https://firebasestorage.googleapis.com/v0/b/gigjob-mobile.appspot.com/o/image%2F6b43f26f-4002-40de-a90b-3c3d1998ade7.png?alt=media',_binary '\0',_binary '\0','123456789','0123456789',0,'2023-03-22 17:57:35','quanan'),('moIkAcoRmxQfBdYj0DUltFhxyyn1','2022-02-10 00:00:00','thuyenvase161502@fpt.edu.vn','',_binary '\0',_binary '\0','5',NULL,1,'2022-12-16 00:00:00','thuyen'),('string','2023-03-22 17:35:58','string',NULL,_binary '\0',_binary '\0','string','string',0,'2023-03-22 17:35:58','string'),('t7maa0qYnhUjhK0n6eUYNvwsgEr1','2022-02-10 00:00:00','phamhuy09042002@gmail.com','',_binary '\0',_binary '\0','3',NULL,1,'2022-12-16 00:00:00','huy'),('Uy3z8txHALNF43J9V1hXxlX33Os2','2022-02-10 00:00:00','taictse161569@fpt.edu.vn','https://firebasestorage.googleapis.com/v0/b/gigjob-mobile.appspot.com/o/image%2F995ac837-644f-452a-bfab-d47abf0a187c.jpg?alt=media',_binary '\0',_binary '\0','2','0909090988',1,'2023-03-24 15:22:51','tai');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'HCM','VietNam','go vap','khanh','pham van dong','AFv78KupgVVAHl7BLMyNmaomkxG2'),(2,'HN','VietNam','cau giay','khanh','pham van dong','t7maa0qYnhUjhK0n6eUYNvwsgEr1'),(3,'ho chi minh','Vietnam','binh thanh','ho chi minh','215/36K NGUYEN XI','dCYnSBeA0iMcLu0rdeyIlIxrkfT2'),(4,'Thủ Đức','Việt Nam','Quận 9','','Lê Văn Việt ','j9u5Tkj2cRP4ea6rKRnFsuEGjRn2'),(5,'string','string','string','string','string','string');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (0,'04ef509f-fddf-47c7-bd94-ee89f6038523',1),(0,'c5777d08-19fe-4bd6-b466-9349dcfdc060',19),(1,'59c5a81e-af69-4d59-968e-af4e9abab102',22),(1,'59c5a81e-af69-4d59-968e-af4e9abab102',24);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fcm_token`
--

LOCK TABLES `fcm_token` WRITE;
/*!40000 ALTER TABLE `fcm_token` DISABLE KEYS */;
INSERT INTO `fcm_token` VALUES (1,'ac03db8a-be5f-11ed-afa1-0242ac120002','AFv78KupgVVAHl7BLMyNmaomkxG2'),(2,'ac03e17a-be5f-11ed-afa1-0242ac120002','eST4k1Y5o1g5CDHek9wGSjbyfFA3'),(3,'ac03e42c-be5f-11ed-afa1-0242ac120002','moIkAcoRmxQfBdYj0DUltFhxyyn1');
/*!40000 ALTER TABLE `fcm_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `history`
--

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'12 ngày phép năm','2023-05-09 10:50:05','Quản lý, đào tạo, tuyển dụng và phân phối công việc cho đội thực tập sinh (TTS), cộng tác viên (CTV) (ít nhất 4 TTS, 8 CTV).','2023-05-04 00:00:00',20000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Logistic','2023-05-09 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(2,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 03:03:50','Quản lí bảo dưỡng danh sách các xe được phân công.','2023-05-04 00:00:00',19000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Thợ sửa xe máy','2023-05-07 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(3,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-05 02:25:50','Tư vấn bán hàng SP cho Khách Hàng','2023-05-04 00:00:00',26000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên','2023-05-09 00:00:00',2,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(4,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-06 09:25:30','Tư vấn, giới thiệu sản phẩm và bán hàng máy lọc nước, cây nóng lạnh Karofi','2023-05-04 00:00:00',26500,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên','2023-05-09 00:00:00',2,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(5,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Tư vấn, giới thiệu sản phẩm và bán hàng máy lọc nước, cây nóng lạnh Karofi','2023-05-04 00:00:00',23000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên','2023-05-02 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(6,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00',' Bán hàng','2023-05-04 00:00:00',24000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Nhân viên bán hàng','2024-05-04 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(7,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Cần biết pha nhiều loại cà phê','2023-05-04 00:00:00',20000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Nhân viên pha cà phê','2024-05-03 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(8,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Trở thành nhân viên đa nhiệm, đa năng, đảm đương mọi công việc tại siêu thị một cách xuất sắc nhất (gia tăng năng suất, một nhân viên làm tất cả các việc, sử dụng tối đa tác nghiệp qua smartphone). ','2023-05-04 00:00:00',25000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên online','2024-05-06 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(9,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00',' Hỗ trợ cài đặt phần mềm, hình ảnh, nhạc cho sản phẩm theo yêu cầu của khách hàng','2023-05-04 00:00:00',26000,'communication skills, agility','Kỹ sư phần mềm','2024-05-08 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(10,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Quản lý, sắp xếp, trưng bày hàng hóa tại siêu thị gọn gàng, tươm tất','2023-05-04 00:00:00',20000,'Có kinh nghiệm về Xuất nhập khẩu hàng hóa.','Quản lý','2024-05-09 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(11,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Tư vấn bán hàng, hỗ trợ và hướng dẫn Khách Hàng khi đến tham quan và mua sắm tại siêu thị','2023-05-04 00:00:00',20000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên online','2024-05-08 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(12,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Trang trí sản phẩm ĐTDĐ cho Khách hàng [dán màn hình,..)','2023-05-04 00:00:00',20000,'Ưu tiên có kinh nghiệm làm việc ở vị trí logistic hoặc mua hàng hoặc tương đương.','Thiết kế mẫu sản phẩm','2024-05-10 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(13,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Bạn là Nữ, tốt nghiệp THPT trở lên và có ngoại hình khá (cao từ 1m56 trở lên, tuổi từ 18-27)','2023-05-04 00:00:00',20000,' Ability to withstand heavy workload pressure, fast pacing, multitasking.','Người mẫu sản phầm','2024-05-05 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(14,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Liên hệ, tương tác với nhà cung cấp cho việc đặt và mua hàng hóa theo yêu cầu.','2023-05-04 00:00:00',20000,' Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên online','2024-07-04 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(15,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Thực hiện văn bản (hợp đồng, công văn, thông báo , …) cho các phòng ban/ bộ phận','2023-05-04 00:00:00',20000,'Sử dụng ERP MWG phần hành nhập/xuất/tồn và thanh toán là lợi thế.','Thư ký','2024-05-04 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(16,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Phụ trách tất cả vấn đề liên quan đến Mua hàng OEM của công ty dịch vụ lắp đặt sửa chữa bảo hành Tận Tâm (Điện Máy, Điện Lạnh, Điện gia dụng...)','2023-05-04 00:00:00',20000,'Bắt buộc sử dụng vi tính văn phòng (Word, Excel, PP, …)','Tư vấn viên','2024-05-04 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(17,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Trông Xe - Bảo Quản Tài Sản ngoài bãi xe','2023-05-04 00:00:00',20000,'Sử dụng ERP MWG phần hành nhập/xuất/tồn và thanh toán là lợi thế.','Bảo vệ','2024-05-04 00:00:00',1,'77f29f0d-8f06-4f3e-9a2b-07d973ed352e'),(18,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Dắt - trông nom xe và đồ - sắp xếp xe cho khách, Vệ sinh Khu vực bãi xe , Trời nắng, mưa đều phải được bọc phủ yên xe','2023-05-04 00:00:00',20000,'Sử dụng ERP MWG phần hành nhập/xuất/tồn và thanh toán là lợi thế.','Bảo vệ','2024-05-04 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(19,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-04 00:00:00','Liên hệ, tương tác với nhà cung cấp cho việc đặt và mua hàng hóa theo yêu cầu.','2023-10-04 12:20:40',20000,'Sử dụng ERP MWG phần hành nhập/xuất/tồn và thanh toán là lợi thế.','Tư vấn viên online','2024-05-04 00:00:00',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(20,'Môi trường làm việc thân thiện chuyên nghiệp','2023-05-10 00:00:00','Thực hiện các báo cáo định kỳ hoặc theo yêu cầu','2023-05-04 00:00:00',20000,'Sử dụng ERP MWG phần hành nhập/xuất/tồn và thanh toán là lợi thế.','Kế toán','2023-02-27 09:57:38',1,'1b142c57-5abd-40ea-99a6-6c1bd5b8d86b'),(21,'Nhân viên văn phòng','2023-03-22 16:27:51','Nhân viên văn phòng','2023-04-05 16:27:51',25000,'Nhân viên văn phòng','Nhân viên văn phòng','2023-03-22 16:27:51',1,'03690fbe-1c2c-4441-a23a-3f93410a23ea'),(22,'Nhân viên pha chế','2023-03-22 16:31:58','Nhân viên pha chế','2023-04-05 16:31:58',30000,'Nhân viên pha chế','Nhân viên pha chế','2023-03-22 16:31:58',2,'03690fbe-1c2c-4441-a23a-3f93410a23ea'),(23,'Nhân viên tạp vụ','2023-03-22 18:18:39','Nhân viên tạp vụ','2023-04-05 18:18:39',25000,'Nhân viên tạp vụ','Nhân viên tạp vụ','2023-03-22 18:18:39',2,'03690fbe-1c2c-4441-a23a-3f93410a23ea'),(24,'Kiểm toán','2023-03-23 08:11:02','Kiểm toán','2023-04-06 08:11:02',35000,'Kiểm toán','Kiểm toán','2023-03-23 08:11:02',1,'03690fbe-1c2c-4441-a23a-3f93410a23ea');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `job_type`
--

LOCK TABLES `job_type` WRITE;
/*!40000 ALTER TABLE `job_type` DISABLE KEYS */;
INSERT INTO `job_type` VALUES (1,'Việc khối văn phòng'),(2,'Việc ở siêu thị, kho');
/*!40000 ALTER TABLE `job_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('9n0kqux3wlc8w5aqnrdejbabc',456,20),('9n0kqux3wlc8w5aqnrdejbemj',878,10);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,'2023-05-04 00:00:00',3,'DAY'),(2,'2023-03-23 06:48:24',-1,'DAY'),(3,'2023-03-23 08:16:18',-1,'NIGHT'),(4,'2023-03-24 12:07:51',-1,'DAY');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES ('03690fbe-1c2c-4441-a23a-3f93410a23ea','Quán ăn',37.4226711,-122.0849872,'Quán Ăn','j9u5Tkj2cRP4ea6rKRnFsuEGjRn2'),('1b142c57-5abd-40ea-99a6-6c1bd5b8d86b','Eleven là một thương hiệu đã quá nổi tiếng trên thế giới. Năm 2017 7.Eleven xuất hiện tại Việt Nam và được người tiêu dùng vô cùng đón nhận, hiện nay thì đây cũng là một cái tên được rất nhiều người biết',10.841401497664323,106.80994751595063,'Seven Eleven','0liRAgNrsvhlGAJEmcQIwEVmom72'),('3ca813fd-249f-4e59-8acc-a1f1eb791f0a','job 123',10.83657513955323,106.80835645189516,'Lau Cua Dong','dCYnSBeA0iMcLu0rdeyIlIxrkfT2'),('77f29f0d-8f06-4f3e-9a2b-07d973ed352e','Tại các thành phố lớn như Hà Nội, TP.Hồ Chí Minh bạn dễ dàng có thể tìm được cái tên này, nhất là đối với các bạn trẻ. Đây là một thương hiệu bán lẻ nổi tiếng trên toàn thế giới bởi chất lượng sản phẩm',10.865077348758073,106.7466154346982,'GS25','3Djc7gbCKkUoftyckXKkOkpFp5G2');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('q9m7rb5uydysanp8smxcovdfg',20,'2023-05-06 00:00:00','Văn phòng Chính phủ vừa có văn bản gửi tới bộ trưởng, chủ tịch UBND các tỉnh, thành phố và tổng giám đốc các cơ quan Bảo hiểm xã hội Việt Nam, Ngân hàng Phát triển Việt Nam, Ngân hàng Chính sách xã hội',' tổng giám đốc Tập đoàn Điện lực Việt Nam về việc công bố, công khai, hướng dẫn thực hiện thủ tục hành chính có yêu cầu nộp, xuất trình sổ hộ khẩu giấy, sổ tạm trú giấy hoặc xác nhận tại nơi cư trú.','stop','9n0kqux3wlc8w5aqnrdejbemj','9f5d9dd0-bfe4-4710-a409-99718ce6e9ab'),('q9m7rb5uydysanp8smxcovxlh',10,'2023-05-04 00:00:00','Văn phòng Chính phủ vừa có văn bản gửi tới bộ trưởng, chủ tịch UBND các tỉnh, thành phố và tổng giám đốc các cơ quan Bảo hiểm xã hội Việt Nam, Ngân hàng Phát triển Việt Nam, Ngân hàng Chính sách xã hội',' tổng giám đốc Tập đoàn Điện lực Việt Nam về việc công bố, công khai, hướng dẫn thực hiện thủ tục hành chính có yêu cầu nộp, xuất trình sổ hộ khẩu giấy, sổ tạm trú giấy hoặc xác nhận tại nơi cư trú.','loading','9n0kqux3wlc8w5aqnrdejbabc','4e4df084-03c7-489c-b054-69799392ba1c');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES ('3fe4fe3a-438d-4d50-a017-acffa321ad87',0,'3vTwMrencefts7dy5oc4tZ1gGiG3'),('461f940c-092e-4d4c-bd10-7935ab20d749',0,'j9u5Tkj2cRP4ea6rKRnFsuEGjRn2'),('4e4df084-03c7-489c-b054-69799392ba1c',1248.73,'eST4k1Y5o1g5CDHek9wGSjbyfFA3'),('6d320db4-8ec5-4f84-ab32-7324982cdc9e',0,'string'),('9f5d9dd0-bfe4-4710-a409-99718ce6e9ab',7972.92,'Uy3z8txHALNF43J9V1hXxlX33Os2'),('d6a82b1a-0aa9-4841-ade1-801712e46ecd',0,'dCYnSBeA0iMcLu0rdeyIlIxrkfT2');
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES ('04ef509f-fddf-47c7-bd94-ee89f6038523','2001-01-15 00:00:00','bằng đại học tốt nghiệp đại học FPT,Toeic 800,bằng excel,word','thanh','vo','tien','moIkAcoRmxQfBdYj0DUltFhxyyn1'),('59c5a81e-af69-4d59-968e-af4e9abab102','2002-11-16 00:00:00','Bachelor of FPT university and, Degrre in spanish of Zaragoza university','Tai','Chau','Tan','Uy3z8txHALNF43J9V1hXxlX33Os2'),('bac239cf-671a-48a4-8267-298af46ea90e','2001-03-16 00:48:57','University','Quyen','Le','Tri','AFv78KupgVVAHl7BLMyNmaomkxG2'),('c5777d08-19fe-4bd6-b466-9349dcfdc060','2022-12-12 00:00:00','Đại học FPT','Huy','Pham','Ho','t7maa0qYnhUjhK0n6eUYNvwsgEr1');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `working_session`
--

LOCK TABLES `working_session` WRITE;
/*!40000 ALTER TABLE `working_session` DISABLE KEYS */;
INSERT INTO `working_session` VALUES ('04ef509f-fddf-47c7-bd94-ee89f6038523',1,1),('59c5a81e-af69-4d59-968e-af4e9abab102',22,2),('59c5a81e-af69-4d59-968e-af4e9abab102',24,3),('59c5a81e-af69-4d59-968e-af4e9abab102',22,4);
/*!40000 ALTER TABLE `working_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-24 23:58:44
