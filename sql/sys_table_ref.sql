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

 Date: 09/03/2023 23:59:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_table_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_ref`;
CREATE TABLE `sys_table_ref`  (
  `id` bigint NOT NULL COMMENT '主键',
  `main_table` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '主表表名',
  `main_info` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '主表描述',
  `sub_table` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '子表表名',
  `sub_info` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '子表描述',
  `ref_id` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '外键名称',
  `del_flag` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除标记',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人主键',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人主键',
  `dr` tinyint NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `table_sub_field`(`main_table` ASC, `sub_table` ASC, `ref_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '表引用关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_table_ref
-- ----------------------------
INSERT INTO `sys_table_ref` VALUES (1, 'sys_role', '角色表', 'sys_role_user', '角色用户关联表', 'role_id', NULL, '2022-10-01 00:00:00', -1, '2022-10-12 15:42:56', -1, 0);
INSERT INTO `sys_table_ref` VALUES (2, 'sys_role', '角色表', 'sys_role_dept', '角色部门关联表', 'role_id', NULL, '2022-10-01 00:00:00', -1, '2022-10-12 15:43:04', -1, 0);
INSERT INTO `sys_table_ref` VALUES (3, 'sys_role', '角色表', 'sys_role_func', '角色菜单关联表', 'role_id', NULL, '2022-10-01 00:00:00', -1, '2022-10-12 15:45:41', -1, 0);

SET FOREIGN_KEY_CHECKS = 1;
