/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 09:45:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_resource
-- ----------------------------
DROP TABLE IF EXISTS `console_resource`;
CREATE TABLE `console_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `type` varchar(64) NOT NULL COMMENT '权限类型',
  `url` varchar(128) DEFAULT NULL COMMENT 'url',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级权限',
  `parent_ids` varchar(64) DEFAULT NULL COMMENT '所有上级权限',
  `permission` varchar(64) NOT NULL COMMENT '权限',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '1：启用 2：停用',
  `create_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_console_resource_parent_id` (`parent_id`),
  KEY `idx_console_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of console_resource
-- ----------------------------
INSERT INTO `console_resource` VALUES ('1', '资源', 'menu', '0', '0', '0/', '', '1', '0', '2017-12-20 16:41:39', '0', '2017-12-20 16:41:39', '1');
INSERT INTO `console_resource` VALUES ('2', '用户管理', 'menu', '/user', '1', '0/1/', 'user:*', '1', '0', '2017-12-20 16:41:39', '0', '2017-12-20 16:41:39', '1');
INSERT INTO `console_resource` VALUES ('3', '用户新增', 'button', '', '2', '0/1/2/', 'user:create', '1', '0', '2017-12-20 16:41:39', '0', '2017-12-20 16:41:39', '1');
INSERT INTO `console_resource` VALUES ('4', '用户修改', 'button', '', '2', '0/1/2/', 'user:update', '1', '0', '2017-12-20 16:41:39', '0', '2017-12-20 16:41:39', '1');
INSERT INTO `console_resource` VALUES ('5', '用户删除', 'button', '', '2', '0/1/2/', 'user:delete', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('6', '用户查看', 'button', '', '2', '0/1/2/', 'user:view', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('7', '资源管理', 'menu', '/resource', '1', '0/1/', 'resource:*', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('8', '资源新增', 'button', '', '7', '0/1/7/', 'resource:create', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('9', '资源修改', 'button', '', '7', '0/1/7/', 'resource:update', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('10', '资源删除', 'button', '', '7', '0/1/7/', 'resource:delete', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('11', '资源查看', 'button', '', '7', '0/1/7/', 'resource:view', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('12', '角色管理', 'menu', '/role', '1', '0/1/', 'role:*', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('13', '角色新增', 'button', '', '12', '0/1/12/', 'role:create', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('14', '角色修改', 'button', '', '12', '0/1/12/', 'role:update', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('15', '角色删除', 'button', '', '12', '0/1/12/', 'role:delete', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('16', '角色查看', 'button', '', '12', '0/1/12/', 'role:view', '1', '0', '2017-12-20 16:41:40', '0', '2017-12-20 16:41:40', '1');
INSERT INTO `console_resource` VALUES ('17', '酒店管理', 'menu', '/hotel', '17', '0/1/', 'hotel:*', '1', '0', '2018-01-12 09:35:57', '0', '2018-01-12 09:36:00', '1');
INSERT INTO `console_resource` VALUES ('18', '酒店新增', 'button', '', '17', '0/1/17/', 'hotel:create', '1', '0', '2018-01-12 09:37:14', '0', '2018-01-12 09:37:17', '1');
INSERT INTO `console_resource` VALUES ('19', '酒店修改', 'button', '', '17', '0/1/17/', 'hotel:update', '1', '0', '2018-01-12 09:38:56', '0', '2018-01-12 09:39:02', '1');
INSERT INTO `console_resource` VALUES ('20', '酒店删除', 'button', '', '17', '0/1/17', 'hotel:delete', '1', '0', '2018-01-12 09:38:59', '0', '2018-01-12 09:39:06', '1');
INSERT INTO `console_resource` VALUES ('21', '酒店查看', 'button', '', '17', '0/1/17', 'hotel:view', '1', '0', '2018-01-12 09:38:59', '0', '2018-01-12 09:39:06', '1');
