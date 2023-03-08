/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : auth_center

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/03/2023 17:08:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func`  (
  `role_func_id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `func_id` bigint NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_func_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '角色功能关联' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
