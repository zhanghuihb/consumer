/*
Navicat MySQL Data Transfer

Source Server         : hrmsystem
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : hrmsystem

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-12-10 23:03:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hui_meituan_food
-- ----------------------------
DROP TABLE IF EXISTS `hui_meituan_food`;
CREATE TABLE `hui_meituan_food` (
  `ID` bigint(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `city` varchar(128) DEFAULT NULL COMMENT '门店所在城市',
  `name` varchar(128) DEFAULT NULL COMMENT '门店名称',
  `address` varchar(128) DEFAULT NULL COMMENT '门店地址',
  `score` varchar(128) DEFAULT NULL COMMENT '门店评分',
  `phone` varchar(128) DEFAULT NULL COMMENT '联系方式',
  `business_hours` varchar(128) DEFAULT NULL COMMENT '门店营业时间',
  `consumption_per_person` varchar(128) DEFAULT NULL COMMENT '门店人均消费',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
