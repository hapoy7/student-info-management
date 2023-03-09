/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : control

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/03/2023 23:43:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_request
-- ----------------------------
DROP TABLE IF EXISTS `sys_request`;
CREATE TABLE `sys_request`  (
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接口路径',
  `app_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名',
  `sum_count` bigint NULL DEFAULT NULL COMMENT '请求次数',
  `sum_milli_second` bigint NULL DEFAULT NULL COMMENT '请求总时长',
  `avg_milli_second` decimal(12, 2) NULL DEFAULT NULL COMMENT '请求平均时长',
  `max_milli_second` int NULL DEFAULT NULL COMMENT '请求最大时长',
  `min_milli_second` int NULL DEFAULT NULL COMMENT '请求最小时长',
  `type` tinyint NULL DEFAULT NULL COMMENT '1后台2前端',
  PRIMARY KEY (`path`, `app_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '请求时长统计' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
