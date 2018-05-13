/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:17:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_consumer_info
-- ----------------------------
DROP TABLE IF EXISTS `console_consumer_info`;
CREATE TABLE `console_consumer_info` (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `province` varchar(64) NOT NULL COMMENT '消费省份',
  `city` varchar(64) NOT NULL COMMENT '消费城市',
  `code` int(11) NOT NULL COMMENT '消费类别',
  `parent_code` int(11) NOT NULL COMMENT '父消费类别',
  `amount` int(11) NOT NULL COMMENT '消费金额（扩大100倍）',
  `digest` int(4) NOT NULL DEFAULT '2' COMMENT '摘要（1：收入 2：支出）',
  `consumer` varchar(64) NOT NULL COMMENT '消费者',
  `consumer_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消费时间',
  `create_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of console_consumer_info
-- ----------------------------
