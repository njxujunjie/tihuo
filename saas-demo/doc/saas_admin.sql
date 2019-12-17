/*
Navicat MySQL Data Transfer

Source Server         : saas_demo
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : saas_admin

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-12-17 14:58:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_base` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES ('00000000025', '8', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_8', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000026', '7', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_7', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000027', '6', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_6', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000028', '4', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_4', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000029', '2', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_2', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000030', '2', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_2', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000031', '5', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_5', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000032', '6', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_6', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000033', '8', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_8', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000034', '9', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_9', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000035', '11', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_11', 'root', '123456');
INSERT INTO `tenant` VALUES ('00000000036', '15', null, 'jdbc:mysql://127.0.0.1:3306/', 'saas_tenant_15', 'root', '123456');
