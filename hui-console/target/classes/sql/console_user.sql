/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:01:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_user
-- ----------------------------
DROP TABLE IF EXISTS `console_user`;
CREATE TABLE `console_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '盐',
  `role_ids` varchar(64) NOT NULL COMMENT '角色',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_console_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of console_user
-- ----------------------------
INSERT INTO `console_user` VALUES ('1', 'admin', '7a7b5ca0d7a8555a1a6b628b6e540464', '389cc9dd882fdb56b685325822f2423a', '1', '1', '0', '2017-12-22 18:13:34', '0', '2017-12-22 18:13:34', '1');
