/*
Navicat MySQL Data Transfer

Source Server         : hrmsystem
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : hrmsystem

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-12-10 23:03:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hui_meituan_hotel
-- ----------------------------
DROP TABLE IF EXISTS `hui_meituan_hotel`;
CREATE TABLE `hui_meituan_hotel` (
  `ID` bigint(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `city` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店所在城市',
  `name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店名称',
  `address` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店地址',
  `score` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店评分',
  `phone` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  `information` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店信息',
  `introduce` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店介绍',
  `policy` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '酒店政策',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=141594 DEFAULT CHARSET=latin1;
