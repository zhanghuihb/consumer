/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:03:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_role
-- ----------------------------
DROP TABLE IF EXISTS `console_role`;
CREATE TABLE `console_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(64) NOT NULL COMMENT '角色名',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `resource_ids` varchar(64) NOT NULL COMMENT '权限',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '1：启用 2：停用',
  `create_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_console_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of console_role
-- ----------------------------
INSERT INTO `console_role` VALUES ('1', 'admin', '超级管理员', '2,7,12', '1', '0', '2017-12-20 16:26:57', '0', '2017-12-20 16:26:57', '1');
INSERT INTO `console_role` VALUES ('2', 'ordinary', '普通用户', '6,11,16', '1', '0', '2017-12-20 16:26:57', '0', '2017-12-20 16:26:57', '1');
