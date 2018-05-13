/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:16:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_hotel
-- ----------------------------
DROP TABLE IF EXISTS `console_hotel`;
CREATE TABLE `console_hotel` (
  `ID` bigint(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `city` varchar(128) DEFAULT NULL COMMENT '酒店所在城市',
  `name` varchar(128) DEFAULT NULL COMMENT '酒店名称',
  `address` varchar(128) DEFAULT NULL COMMENT '酒店地址',
  `score` varchar(128) DEFAULT NULL COMMENT '酒店评分',
  `phone` varchar(128) DEFAULT NULL COMMENT '联系方式',
  `information` varchar(256) DEFAULT NULL COMMENT '酒店信息',
  `introduce` varchar(1024) DEFAULT NULL COMMENT '酒店介绍',
  `policy` varchar(256) DEFAULT NULL COMMENT '酒店政策',
  `create_user` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新者',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标识',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=212830 DEFAULT CHARSET=utf8;
