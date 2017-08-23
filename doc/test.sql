-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 test 的数据库结构
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;


-- 导出  表 test.goods 结构
DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `pic` varchar(128) NOT NULL COMMENT '图片',
  `price` double NOT NULL COMMENT '价格',
  `persionId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lom7er4dugnf1yv0xhim2m7p` (`persionId`),
  CONSTRAINT `FK_6lom7er4dugnf1yv0xhim2m7p` FOREIGN KEY (`persionId`) REFERENCES `persion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.goods 的数据：~2 rows (大约)
DELETE FROM `goods`;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`id`, `name`, `content`, `pic`, `price`, `persionId`) VALUES
	('4028810d5dde6f9f015dde6fa16f0000', '西游记', '是什么', 'http://www.baidu.com', 15, NULL),
	('4028810d5ddfed13015ddfed15190000', '红楼梦', 'asg', 'http://hongxue.com', 19.99, '4028810d5ddfed13015ddfed15230001');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;


-- 导出  表 test.permission 结构
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `id` varchar(32) NOT NULL,
  `token` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `description` varchar(110) DEFAULT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLE_ID_FK` (`roleId`),
  CONSTRAINT `FK_ROLE_ID_FK` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.permission 的数据：~0 rows (大约)
DELETE FROM `permission`;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `token`, `url`, `description`, `roleId`) VALUES
	('1', 'sadg', 'sadg', 'sdg', '1');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- 导出  表 test.persion 结构
DROP TABLE IF EXISTS `persion`;
CREATE TABLE IF NOT EXISTS `persion` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `realName` varchar(100) NOT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.persion 的数据：~2 rows (大约)
DELETE FROM `persion`;
/*!40000 ALTER TABLE `persion` DISABLE KEYS */;
INSERT INTO `persion` (`id`, `name`, `realName`) VALUES
	('4028810d5ddfecc3015ddfecc49d0000', '张三', 'tom'),
	('4028810d5ddfed13015ddfed15230001', '曹', '曹雪芹'),
	('4028810d5ddfed13015ddfed15240002', '贾', '贾宝玉');
/*!40000 ALTER TABLE `persion` ENABLE KEYS */;


-- 导出  表 test.role 结构
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.role 的数据：~0 rows (大约)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `description`) VALUES
	('1', 'admin', '管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 导出  表 test.t_classes 结构
DROP TABLE IF EXISTS `t_classes`;
CREATE TABLE IF NOT EXISTS `t_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  test.t_classes 的数据：~0 rows (大约)
DELETE FROM `t_classes`;
/*!40000 ALTER TABLE `t_classes` DISABLE KEYS */;
INSERT INTO `t_classes` (`id`, `cname`) VALUES
	(1, '研发');
/*!40000 ALTER TABLE `t_classes` ENABLE KEYS */;


-- 导出  表 test.t_dept 结构
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE IF NOT EXISTS `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  test.t_dept 的数据：~0 rows (大约)
DELETE FROM `t_dept`;
/*!40000 ALTER TABLE `t_dept` DISABLE KEYS */;
INSERT INTO `t_dept` (`id`, `dname`) VALUES
	(4, '研发部');
/*!40000 ALTER TABLE `t_dept` ENABLE KEYS */;


-- 导出  表 test.t_emp 结构
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE IF NOT EXISTS `t_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hmj7rm9j17ilaq47r83kfe7bm` (`deptId`),
  CONSTRAINT `FK_hmj7rm9j17ilaq47r83kfe7bm` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  test.t_emp 的数据：~2 rows (大约)
DELETE FROM `t_emp`;
/*!40000 ALTER TABLE `t_emp` DISABLE KEYS */;
INSERT INTO `t_emp` (`id`, `ename`, `age`, `deptId`) VALUES
	(7, '张三', 30, 4),
	(8, '李四', 25, 4);
/*!40000 ALTER TABLE `t_emp` ENABLE KEYS */;


-- 导出  表 test.t_student 结构
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE IF NOT EXISTS `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `cId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_imdduqps5mkiisx0foa3xitkd` (`cId`),
  CONSTRAINT `FK_imdduqps5mkiisx0foa3xitkd` FOREIGN KEY (`cId`) REFERENCES `t_classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  test.t_student 的数据：~2 rows (大约)
DELETE FROM `t_student`;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` (`id`, `sname`, `age`, `cId`) VALUES
	(1, 'zs', 13, 1),
	(2, 'ls', 13, 1);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;


-- 导出  表 test.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `idDelete` int(2) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `isDelete` int(11) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.user 的数据：~1 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `idDelete`, `createDate`, `isDelete`) VALUES
	('1', 'zhang', '123456', 0, '2017-08-15 17:46:51', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 test.userrole 结构
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE IF NOT EXISTS `userrole` (
  `id` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ID` (`userId`),
  KEY `FK_ROLE_ID` (`roleId`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  test.userrole 的数据：~0 rows (大约)
DELETE FROM `userrole`;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` (`id`, `userId`, `roleId`) VALUES
	('1', '1', '1');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
