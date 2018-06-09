/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : db_shiro

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 06/09/2018 11:45:27 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permission_message` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES ('1', '用户管理', 'user:list', '/web/in/user/list'), ('2', '用户添加', 'user:add', '/web/in/user/add'), ('3', '用户删除', 'user:del', '/web/in/user/del');
COMMIT;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `intro` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', '管理员', 'admin'), ('2', 'VIP会员', 'vip'), ('3', 'test', 'test');
COMMIT;

-- ----------------------------
--  Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `permiss_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permiss_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permiss_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES ('1', '1'), ('2', '1'), ('3', '2');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'admin');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`user_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKgkmyslkrfeyn9ukmolvek8b8f` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
