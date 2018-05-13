/*
Navicat MySQL Data Transfer

Source Server         : hui_project
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hui_project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:17:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for console_consumer_category
-- ----------------------------
DROP TABLE IF EXISTS `console_consumer_category`;
CREATE TABLE `console_consumer_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL DEFAULT '默认分类' COMMENT '分类名称',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '状态（1：启用 2：停用）',
  `parent_code` varchar(64) NOT NULL COMMENT '父编码',
  `create_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of console_consumer_category
-- ----------------------------
INSERT INTO `console_consumer_category` VALUES ('1', '01', '食品烟酒', '1', '0', '0', '2018-01-11 09:47:02', '0', '2018-01-11 09:47:02', '1');
INSERT INTO `console_consumer_category` VALUES ('2', '02', '衣着', '1', '0', '0', '2018-01-11 09:47:48', '0', '2018-01-11 09:47:48', '1');
INSERT INTO `console_consumer_category` VALUES ('3', '03', '居住', '1', '0', '0', '2018-01-11 09:48:09', '0', '2018-01-11 09:48:09', '1');
INSERT INTO `console_consumer_category` VALUES ('4', '04', '生活用品及服务', '1', '0', '0', '2018-01-11 09:48:47', '0', '2018-01-11 09:48:47', '1');
INSERT INTO `console_consumer_category` VALUES ('5', '05', '交通和通信', '1', '0', '0', '2018-01-11 09:49:25', '0', '2018-01-11 09:49:25', '1');
INSERT INTO `console_consumer_category` VALUES ('6', '06', '教育、文化和娱乐', '1', '0', '0', '2018-01-11 09:50:22', '0', '2018-01-11 09:50:22', '1');
INSERT INTO `console_consumer_category` VALUES ('7', '07', '医疗保健', '1', '0', '0', '2018-01-11 09:50:48', '0', '2018-01-11 09:50:48', '1');
INSERT INTO `console_consumer_category` VALUES ('8', '08', '其他服务', '1', '0', '0', '2018-01-11 09:51:17', '0', '2018-01-11 09:51:17', '1');
INSERT INTO `console_consumer_category` VALUES ('9', '0101', '食品', '1', '01', '0', '2018-01-11 09:52:26', '0', '2018-01-11 09:52:26', '1');
INSERT INTO `console_consumer_category` VALUES ('10', '0102', '饮料（不含酒精）', '1', '01', '0', '2018-01-11 09:53:17', '0', '2018-01-11 09:53:17', '1');
INSERT INTO `console_consumer_category` VALUES ('11', '0103', '烟酒', '1', '01', '0', '2018-01-11 09:53:59', '0', '2018-01-11 09:53:59', '1');
INSERT INTO `console_consumer_category` VALUES ('12', '0104', '饮食服务', '1', '01', '0', '2018-01-11 09:54:22', '0', '2018-01-11 09:54:22', '1');
INSERT INTO `console_consumer_category` VALUES ('13', '0105', '其他', '1', '01', '0', '2018-01-11 09:55:45', '0', '2018-01-11 09:55:45', '1');
INSERT INTO `console_consumer_category` VALUES ('14', '0201', '衣类', '1', '02', '0', '2018-01-11 09:56:32', '0', '2018-01-11 09:56:32', '1');
INSERT INTO `console_consumer_category` VALUES ('15', '0202', '鞋类', '1', '02', '0', '2018-01-11 09:57:36', '0', '2018-01-11 09:57:36', '1');
INSERT INTO `console_consumer_category` VALUES ('16', '0203', '其他', '1', '02', '0', '2018-01-11 09:58:20', '0', '2018-01-11 09:58:20', '1');
INSERT INTO `console_consumer_category` VALUES ('17', '0301', '租赁房房租', '1', '03', '0', '2018-01-11 09:59:04', '0', '2018-01-11 09:59:04', '1');
INSERT INTO `console_consumer_category` VALUES ('18', '0302', '住房保养、维修及管理 ', '1', '03', '0', '2018-01-11 09:59:37', '0', '2018-01-11 09:59:37', '1');
INSERT INTO `console_consumer_category` VALUES ('19', '0303', '水、电、燃料', '1', '03', '0', '2018-01-11 10:00:40', '0', '2018-01-11 10:00:40', '1');
INSERT INTO `console_consumer_category` VALUES ('20', '0304', '其他', '1', '03', '0', '2018-01-11 10:02:17', '0', '2018-01-11 10:02:17', '1');
INSERT INTO `console_consumer_category` VALUES ('21', '0401', '家具及室内装饰品', '1', '04', '0', '2018-01-11 10:07:56', '0', '2018-01-11 10:07:56', '1');
INSERT INTO `console_consumer_category` VALUES ('22', '0402', '家用器具', '1', '04', '0', '2018-01-11 10:08:29', '0', '2018-01-11 10:08:29', '1');
INSERT INTO `console_consumer_category` VALUES ('23', '0403', '家用纺织品', '1', '04', '0', '2018-01-11 10:08:51', '0', '2018-01-11 10:08:51', '1');
INSERT INTO `console_consumer_category` VALUES ('24', '0404', '家庭日用杂品', '1', '04', '0', '2018-01-11 10:08:52', '0', '2018-01-11 10:08:52', '1');
INSERT INTO `console_consumer_category` VALUES ('25', '0405', '个人护理用品', '1', '04', '0', '2018-01-11 10:10:22', '0', '2018-01-11 10:10:22', '1');
INSERT INTO `console_consumer_category` VALUES ('26', '0406', '家庭服务', '1', '04', '0', '2018-01-11 10:11:22', '0', '2018-01-11 10:11:22', '1');
INSERT INTO `console_consumer_category` VALUES ('27', '0407', '其他', '1', '04', '0', '2018-01-11 10:11:35', '0', '2018-01-11 10:11:35', '1');
INSERT INTO `console_consumer_category` VALUES ('28', '0501', '交通', '1', '05', '0', '2018-01-11 10:12:25', '0', '2018-01-11 10:12:25', '1');
INSERT INTO `console_consumer_category` VALUES ('29', '0502', '通信', '1', '05', '0', '2018-01-11 10:12:46', '0', '2018-01-11 10:12:46', '1');
INSERT INTO `console_consumer_category` VALUES ('30', '0503', '其他', '1', '05', '0', '2018-01-11 10:12:56', '0', '2018-01-11 10:12:56', '1');
INSERT INTO `console_consumer_category` VALUES ('31', '0601', '教育', '1', '06', '0', '2018-01-11 10:13:20', '0', '2018-01-11 10:13:20', '1');
INSERT INTO `console_consumer_category` VALUES ('32', '0602', '文化和娱乐', '1', '06', '0', '2018-01-11 10:13:41', '0', '2018-01-11 10:13:41', '1');
INSERT INTO `console_consumer_category` VALUES ('33', '0603', '其他', '1', '06', '0', '2018-01-11 10:14:05', '0', '2018-01-11 10:14:05', '1');
INSERT INTO `console_consumer_category` VALUES ('34', '0701', '医疗器具及药品', '1', '07', '0', '2018-01-11 10:14:39', '0', '2018-01-11 10:14:39', '1');
INSERT INTO `console_consumer_category` VALUES ('35', '0702', '医疗服务', '1', '07', '0', '2018-01-11 10:15:00', '0', '2018-01-11 10:15:00', '1');
INSERT INTO `console_consumer_category` VALUES ('36', '0703', '其他', '1', '07', '0', '2018-01-11 10:15:40', '0', '2018-01-11 10:15:40', '1');
INSERT INTO `console_consumer_category` VALUES ('37', '0801', '其他用品', '1', '08', '0', '2018-01-11 10:16:12', '0', '2018-01-11 10:16:12', '1');
INSERT INTO `console_consumer_category` VALUES ('38', '0802', '其他服务', '1', '08', '0', '2018-01-11 10:17:44', '0', '2018-01-11 10:17:44', '1');
