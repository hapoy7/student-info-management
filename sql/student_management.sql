/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : student_management

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/03/2023 23:36:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_clazz
-- ----------------------------
DROP TABLE IF EXISTS `base_clazz`;
CREATE TABLE `base_clazz`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  `major_id` bigint NOT NULL COMMENT '专业id',
  `institute_id` bigint NOT NULL COMMENT '学院id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `institute_id`(`institute_id` ASC) USING BTREE,
  INDEX `major_id`(`major_id` ASC) USING BTREE,
  CONSTRAINT `base_clazz_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `base_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `base_clazz_ibfk_2` FOREIGN KEY (`institute_id`) REFERENCES `base_institute` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_clazz
-- ----------------------------

-- ----------------------------
-- Table structure for base_course
-- ----------------------------
DROP TABLE IF EXISTS `base_course`;
CREATE TABLE `base_course`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `course_type` int NOT NULL COMMENT '课程类型',
  `credit` double(10, 1) NOT NULL COMMENT '学分',
  `sign_stime` date NULL DEFAULT NULL COMMENT '签到开始时间',
  `sign_etime` date NULL DEFAULT NULL COMMENT '签到结束时间',
  `exam_stime` date NULL DEFAULT NULL COMMENT '考试开始时间',
  `exam_etime` date NULL DEFAULT NULL COMMENT '考试结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_course
-- ----------------------------

-- ----------------------------
-- Table structure for base_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `base_course_teacher`;
CREATE TABLE `base_course_teacher`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `course_id` bigint NOT NULL COMMENT '课程id',
  `teacher_id` bigint NOT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `base_course_teacher_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `base_teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `base_course_teacher_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `base_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '老师课程关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_course_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for base_institute
-- ----------------------------
DROP TABLE IF EXISTS `base_institute`;
CREATE TABLE `base_institute`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院名称',
  `institute_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学院' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_institute
-- ----------------------------

-- ----------------------------
-- Table structure for base_major
-- ----------------------------
DROP TABLE IF EXISTS `base_major`;
CREATE TABLE `base_major`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业名称',
  `major_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业编号',
  `institute_id` bigint NOT NULL COMMENT '学院id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `institute_id`(`institute_id` ASC) USING BTREE,
  CONSTRAINT `base_major_ibfk_1` FOREIGN KEY (`institute_id`) REFERENCES `base_institute` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_major
-- ----------------------------

-- ----------------------------
-- Table structure for base_student
-- ----------------------------
DROP TABLE IF EXISTS `base_student`;
CREATE TABLE `base_student`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `student_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `clazz_id` bigint NOT NULL COMMENT '班级id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `sex` int NOT NULL COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `photo_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `clazz_id`(`clazz_id` ASC) USING BTREE,
  INDEX `student_number`(`student_number` ASC) USING BTREE,
  CONSTRAINT `base_student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `base_student_ibfk_2` FOREIGN KEY (`clazz_id`) REFERENCES `base_clazz` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_student
-- ----------------------------

-- ----------------------------
-- Table structure for base_student_course
-- ----------------------------
DROP TABLE IF EXISTS `base_student_course`;
CREATE TABLE `base_student_course`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `student_id` bigint NOT NULL COMMENT '学生id',
  `course_id` bigint NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  CONSTRAINT `base_student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `base_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `base_student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `base_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生课程关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_student_course
-- ----------------------------

-- ----------------------------
-- Table structure for base_teacher
-- ----------------------------
DROP TABLE IF EXISTS `base_teacher`;
CREATE TABLE `base_teacher`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '老师姓名',
  `sex` int NOT NULL COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头衔',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `photo_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sex`(`sex` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `base_teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '老师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for question_exam
-- ----------------------------
DROP TABLE IF EXISTS `question_exam`;
CREATE TABLE `question_exam`  (
  `id` bigint NOT NULL COMMENT 'id',
  `exam_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试介绍',
  `course_id` bigint NULL DEFAULT NULL COMMENT '课程id',
  `exam_course` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `exam_date` date NULL DEFAULT NULL COMMENT '考试日期',
  `total_time` int NULL DEFAULT NULL COMMENT '考试时长',
  `major` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `institute` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  `total_score` int NULL DEFAULT NULL COMMENT '总分',
  `exam_type` int NULL DEFAULT NULL COMMENT '考试类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  CONSTRAINT `question_exam_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `base_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考试安排' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_exam
-- ----------------------------

-- ----------------------------
-- Table structure for question_exam_record
-- ----------------------------
DROP TABLE IF EXISTS `question_exam_record`;
CREATE TABLE `question_exam_record`  (
  `id` bigint NOT NULL COMMENT '考试记录表的主键',
  `exam_id` bigint NULL DEFAULT NULL COMMENT '考试安排ID',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生id',
  `exem_date` date NULL DEFAULT NULL COMMENT '参加考试的时间',
  `exem_time_cost` int NULL DEFAULT 0 COMMENT '完成考试所用的时间,单位分钟',
  `exem_score` int NULL DEFAULT 0 COMMENT '参与考试的实际得分',
  `exem_result_level` int NULL DEFAULT 0 COMMENT '考试结果的等级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_id`(`exam_id` ASC) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  CONSTRAINT `question_exam_record_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `question_exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `question_exam_record_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `base_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考试记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_exam_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级主键',
  `leaf` tinyint NULL DEFAULT NULL COMMENT '是否叶子',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint NULL DEFAULT NULL COMMENT '类型',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `version` int NULL DEFAULT NULL COMMENT '版本',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接口' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES (10000, 0, 0, '认证中心', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10001, 10000, 0, '认证中心', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10002, 10001, 0, '系统管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10003, 10001, 0, '钥匙柜', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10004, 10002, 0, '接口管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10005, 10002, 0, '菜单维护', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10006, 10002, 0, '字典管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10007, 10002, 0, '部门管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10008, 10002, 0, '用户管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10009, 10002, 0, '角色管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10010, 10002, 0, '接口性能', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10011, 10002, 0, '系统日志', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10012, 10002, 0, '系统参数', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10013, 10002, 1, '数据检查', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10014, 10003, 0, '钥匙柜', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10015, 10003, 0, '钥匙', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10016, 10002, 0, '字典值管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10017, 10002, 0, '系统接口', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10019, 10002, 0, '角色接口', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10020, 10002, 0, '角色部门接口', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10021, 10002, 0, '角色用户接口', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (10022, 10002, 0, '角色菜单接口', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100021, 10004, 1, '删除', 1, '/api/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100022, 10004, 1, '列表', 1, '/api/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100023, 10004, 1, '保存', 1, '/api/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100024, 10004, 1, '树形', 1, '/api/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100025, 10004, 1, '更新', 1, '/api/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100026, 10014, 1, '总数', 1, '/box/count', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100027, 10014, 1, '删除', 1, '/box/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100028, 10014, 1, '根据id查询', 1, '/box/findById', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100029, 10014, 1, '列表', 1, '/box/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100030, 10014, 1, '分页', 1, '/box/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100031, 10014, 1, '保存', 1, '/box/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100032, 10014, 1, '更新', 1, '/box/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100033, 10017, 1, '跨域伪造', 1, '/csrf', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100034, 10007, 1, '删除', 1, '/dept/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100035, 10007, 1, '列表', 1, '/dept/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100036, 10007, 1, '保存', 1, '/dept/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100037, 10007, 1, '树型', 1, '/dept/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100038, 10007, 1, '更新', 1, '/dept/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100039, 10006, 1, '删除', 1, '/dict/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100040, 10006, 1, '分页', 1, '/dict/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100041, 10006, 1, '保存', 1, '/dict/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100042, 10006, 1, '更新', 1, '/dict/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100043, 10016, 1, '所有', 1, '/dictItem/all', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100044, 10016, 1, '删除', 1, '/dictItem/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100045, 10016, 1, '列表', 1, '/dictItem/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100046, 10016, 1, '保存', 1, '/dictItem/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100047, 10016, 1, '更新', 1, '/dictItem/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100048, 10017, 1, '错误', 1, '/error', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100049, 10017, 1, '错误', 1, '/error', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100050, 10005, 1, '删除', 1, '/func/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100051, 10005, 1, '列表', 1, '/func/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100052, 10005, 1, '保存', 1, '/func/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100053, 10005, 1, '树型', 1, '/func/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100054, 10005, 1, '更新', 1, '/func/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100055, 10015, 1, '总数', 1, '/key/count', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100056, 10015, 1, '删除', 1, '/key/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100057, 10015, 1, '根据id查询', 1, '/key/findById', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100058, 10015, 1, '列表', 1, '/key/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100059, 10015, 1, '分页', 1, '/key/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100060, 10015, 1, '保存', 1, '/key/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100061, 10015, 1, '更新', 1, '/key/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100062, 10011, 1, '删除', 1, '/log/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100063, 10011, 1, '分页', 1, '/log/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100064, 10012, 1, '删除', 1, '/parameter/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100065, 10012, 1, '分页', 1, '/parameter/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100066, 10012, 1, '保存', 1, '/parameter/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100067, 10012, 1, '更新', 1, '/parameter/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100068, 10010, 1, '删除', 1, '/request/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100069, 10010, 1, '列表', 1, '/request/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100070, 10009, 1, '删除', 1, '/role/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100071, 10009, 1, '导出', 1, '/role/export', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100072, 10009, 1, '导入', 1, '/role/import', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100073, 10009, 1, '分页', 1, '/role/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100074, 10009, 1, '保存', 1, '/role/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100075, 10009, 1, '更新', 1, '/role/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100076, 10019, 1, '保存', 1, '/roleApi/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100077, 10019, 1, '树型', 1, '/roleApi/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100078, 10020, 1, '保存', 1, '/roleDept/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100079, 10020, 1, '树型', 1, '/roleDept/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100080, 10022, 1, '保存', 1, '/roleFunc/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100081, 10022, 1, '树型', 1, '/roleFunc/tree', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100082, 10021, 1, '列表', 1, '/roleUser/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100083, 10021, 1, '保存', 1, '/roleUser/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100084, 10017, 1, '删除文件', 1, '/sys/deleteFile', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100085, 10017, 1, '登录', 1, '/sys/login', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100086, 10017, 1, '保存文件', 1, '/sys/saveFile', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100087, 10017, 1, '获取token', 1, '/sys/token', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100088, 10013, 1, '删除', 1, '/tableRef/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100089, 10013, 1, '分页', 1, '/tableRef/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100090, 10013, 1, '保存', 1, '/tableRef/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100091, 10013, 1, '更新', 1, '/tableRef/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100092, 10008, 1, '批量保存', 1, '/user/addBatch', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100093, 10008, 1, '删除', 1, '/user/delete', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100094, 10008, 1, '批量删除', 1, '/user/deleteBatch', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100095, 10008, 1, '列表', 1, '/user/list', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100096, 10008, 1, '分页', 1, '/user/page', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100097, 10008, 1, '保存', 1, '/user/save', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100098, 10008, 1, '更新', 1, '/user/update', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (100099, 10008, 1, '批量更新', 1, '/user/updateBatch', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (1234234, 10001, 1, '设备管理', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (745099322047070208, 10013, 1, 'fieldconf/list', 1, 'fieldconf/list', NULL, NULL, '2023-02-13 09:58:42', 10000, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (746215702788046848, 1234234, 1, '设备告警', 1, '', NULL, '设备告警', '2023-02-16 11:54:48', 10000, NULL, NULL, 0);
INSERT INTO `sys_api` VALUES (746216167663730688, 746215702788046848, 1, '列表', 1, '/equalert/page', 1, '设备告警列表', '2023-02-16 11:56:38', 10000, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典编码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (504670504825786512, '性别', 'gender', '性别', '2022-01-01 00:00:00', 10000, '2023-02-01 20:14:26', NULL, 0);
INSERT INTO `sys_dict` VALUES (504670504825786513, '状态', 'status', '状态', '2022-01-01 00:00:00', 10000, '2022-10-11 18:19:05', -1, 0);
INSERT INTO `sys_dict` VALUES (504670504825786514, '机构类型', 'deptType', '机构类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:30:33', -1, 0);
INSERT INTO `sys_dict` VALUES (504670504825786515, '请求类型', 'requestType', '请求类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:28:23', -1, 0);
INSERT INTO `sys_dict` VALUES (692271353708937216, '人员类型', 'personType', '人员类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:28:43', -1, 0);
INSERT INTO `sys_dict` VALUES (692276399993520128, '钥匙柜状态', 'boxStatus', '钥匙柜状态', '2022-01-01 00:00:00', 10000, '2022-10-08 17:25:56', -1, 0);
INSERT INTO `sys_dict` VALUES (692303874848980992, '日志类型', 'logType', '日志类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:25:54', -1, 0);
INSERT INTO `sys_dict` VALUES (692312088046993408, '告警类型', 'alertType', '告警类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:31:41', -1, 0);
INSERT INTO `sys_dict` VALUES (692978996559740928, '厂家类型', 'firmType', '厂家类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:25:49', -1, 0);
INSERT INTO `sys_dict` VALUES (692979143347798016, '协议类型', 'protocolType', '协议类型', '2022-01-01 00:00:00', 10000, '2022-10-08 17:21:51', -1, 0);
INSERT INTO `sys_dict` VALUES (692979308267831296, '组信息', 'boxGroup', '组信息', '2022-01-01 00:00:00', 10000, '2023-01-30 17:19:59', NULL, 0);
INSERT INTO `sys_dict` VALUES (694787338525474816, '有无状态', 'isHave', '有无状态', '2022-01-01 00:00:00', 10000, '2022-10-08 17:23:33', -1, 0);
INSERT INTO `sys_dict` VALUES (708902511733374976, '计划类型', 'planStatus', '计划类型', '2022-11-05 12:49:08', 10000, '2022-11-10 09:55:23', -1, 0);
INSERT INTO `sys_dict` VALUES (708903426938896384, '计划状态', 'planType', '计划状态', '2022-11-05 12:45:30', 10000, '2022-11-10 09:55:27', -1, 0);
INSERT INTO `sys_dict` VALUES (710671776396345344, '功能类型', 'funcType', '功能类型', '2022-11-10 09:55:55', 10000, '2022-12-14 16:07:12', 10000, 0);
INSERT INTO `sys_dict` VALUES (711028625851285504, '操作类型', 'operationType', '操作日志类型', '2022-11-11 09:33:55', 10000, '2022-12-14 16:07:15', 10000, 0);
INSERT INTO `sys_dict` VALUES (719506723500457984, '钥匙在位状态', 'keyStatus', '钥匙在位状态', '2022-12-04 19:02:51', 10000, '2023-01-12 18:04:05', 10000, 0);
INSERT INTO `sys_dict` VALUES (721311929594806272, '物品柜通讯板状态', 'goodsBoardStatus', '物品柜通讯板状态', '2022-12-09 18:36:05', 10000, '2022-12-09 19:06:04', 10000, 0);
INSERT INTO `sys_dict` VALUES (721718945509277696, '物品柜状态', 'cabinetStatus', '物品柜状态', '2022-12-10 21:33:26', 10000, '2023-01-12 18:04:37', 10000, 0);
INSERT INTO `sys_dict` VALUES (721721728207683584, '物品柜格子状态', 'gridStatus', '物品柜格子状态', '2022-12-10 21:44:29', 10000, '2023-01-12 18:04:41', 10000, 0);
INSERT INTO `sys_dict` VALUES (721724634822606848, '物品类型', 'itemType', '物品类型', '2022-12-10 21:56:02', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES (722297521346445312, '钥匙柜通讯板是否控制开门', 'isDoor', '钥匙柜通讯板是否控制开门', '2022-12-12 11:52:29', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES (723448223020089344, '角色类型', 'roleType', '角色类型', '2022-12-15 16:04:57', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES (723456021653094400, '钥匙归还状态', 'keyReturnStatus', '钥匙归还状态', '2022-12-15 16:35:57', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES (746278670288551936, '测试', '1', '测试数据', '2023-02-16 16:05:00', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint NOT NULL COMMENT '主键',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '字典主键',
  `text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典项文本',
  `value` int NULL DEFAULT NULL COMMENT '字典项值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `order_num` float NULL DEFAULT NULL COMMENT '排序编号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dict_id`(`dict_id` ASC) USING BTREE,
  CONSTRAINT `sys_dict_item_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `sys_dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '字典项' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (504670504796426241, 504670504825786512, '女', 0, '女', 0, '2021-04-20 23:14:03', 10000, '2022-07-04 01:53:58', -1, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426242, 504670504825786512, '男', 1, '男', 1, '2021-04-20 23:14:03', 10000, '2022-07-04 01:53:54', -1, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426243, 504670504825786513, '启用', 1, '启用', 1, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426244, 504670504825786513, '停用', 0, '停用', 0, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426245, 504670504825786514, '单位', 1, '单位', 1, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426246, 504670504825786514, '部门', 2, '部门', 2, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426247, 504670504825786515, '前端文件', 1, '前端文件', NULL, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (504670504796426248, 504670504825786515, '后端接口', 2, '后端接口', NULL, '2021-04-20 23:14:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692271528988901376, 692271353708937216, '常规人员', 1, '常规人员', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692271602108203008, 692271353708937216, '临时人员', 2, '临时人员', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692276510534402048, 692276399993520128, '初始', 1, '初始', 1, NULL, NULL, '2022-09-20 15:40:11', -1, 0);
INSERT INTO `sys_dict_item` VALUES (692276598065332224, 692276399993520128, '正常', 2, '正常', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692276725253406720, 692276399993520128, '故障', 3, '故障', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692276786544771072, 692276399993520128, '离线', 4, '离线', 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692304133708840960, 692303874848980992, '登录', 1, '登录', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692304184422170624, 692303874848980992, '领用', 2, '领用', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692304242702024704, 692303874848980992, '归还', 3, '归还', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692304308179304448, 692303874848980992, '紧急取钥匙', 4, '紧急取钥匙', 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692312207031009280, 692312088046993408, '超时操作', 1, '超时操作', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692312259711467520, 692312088046993408, '误还钥匙', 2, '误还钥匙', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692312316577841152, 692312088046993408, '柜门未关紧', 3, '柜门未关紧', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692979848074756096, 692979308267831296, '运用组', 1, '运用组', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692979947005804544, 692979308267831296, '联合检修组', 2, '联合检修组', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980033945337856, 692979308267831296, '工程车组', 3, '工程车组', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980132968660992, 692978996559740928, '厂商01', 1, '厂商01', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980187536556032, 692978996559740928, '厂商02', 2, '厂商02', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980240741302272, 692978996559740928, '厂商03', 3, '厂商03', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980401488003072, 692979143347798016, '协议01', 1, '协议01', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980548976508928, 692979143347798016, '协议02', 2, '协议02', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (692980589778698240, 692979143347798016, '协议03', 3, '协议03', 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (694787431718715392, 694787338525474816, '有', 1, '有', 1, '2022-09-27 13:57:12', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (694787518234624000, 694787338525474816, '无', 0, '无', 0, '2022-09-27 13:57:33', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708902813559685120, 708902511733374976, '初始化', 0, '初始化', 0, '2022-11-05 12:46:42', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708902934758293504, 708902511733374976, '正进行', 1, '正进行', 1, '2022-11-05 12:47:10', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708903000009080832, 708902511733374976, '未完成', 2, '未完成', 2, '2022-11-05 12:47:26', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708903756342755328, 708903426938896384, '现场调研', 0, '现场调研', 0, '2022-11-05 12:50:26', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708903843135488000, 708903426938896384, '招聘带人', 1, '招聘带人', 1, '2022-11-05 12:50:47', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708903905412513792, 708903426938896384, '组内会议', 2, '组内会议', 2, '2022-11-05 12:51:02', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708904074296164352, 708903426938896384, '模块开发', 3, '模块开发', 3, '2022-11-05 12:51:42', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (708904152008228864, 708903426938896384, '功能测试', 4, '功能测试', 4, '2022-11-05 12:52:01', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (710671858151718912, 710671776396345344, '菜单', 1, '菜单', 1, '2022-11-10 09:56:15', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (710675880745107456, 710671776396345344, '按钮', 2, '按钮', 2, '2022-11-10 10:12:14', 10000, '2022-11-10 10:12:21', 10000, 0);
INSERT INTO `sys_dict_item` VALUES (710677717330165760, 710671776396345344, '目录', 3, '目录', 3, '2022-11-10 10:19:32', 10000, '2022-11-10 10:19:36', 10000, 0);
INSERT INTO `sys_dict_item` VALUES (711028990067867648, 711028625851285504, '取钥匙', 0, '取钥匙', 0, '2022-11-11 09:35:22', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (711029039061532672, 711028625851285504, '还钥匙', 1, '还钥匙', 1, '2022-11-11 09:35:33', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (711029084997550080, 711028625851285504, '开门', 2, '开门', 2, '2022-11-11 09:35:44', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (711029131147476992, 711028625851285504, '关门', 3, '关门', 3, '2022-11-11 09:35:55', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (719506896142204928, 719506723500457984, '未注册', 0, '未注册', 0, '2022-12-04 19:03:32', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (719506974105927680, 719506723500457984, '在位', 1, '在位', 1, '2022-12-04 19:03:50', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (719507050614226944, 719506723500457984, '不在位', 2, '不在位', 2, '2022-12-04 19:04:09', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (719507101080092672, 719506723500457984, '错位', 3, '错位', 3, '2022-12-04 19:04:21', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (719507149528498176, 719506723500457984, '超时状态-超时未归还', 4, '超时状态-超时未归还', 4, '2022-12-04 19:04:32', -1, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721312419648897024, 721311929594806272, '初始', 1, '初始', 1, '2022-12-09 18:38:02', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721312503161683968, 721311929594806272, '正常', 2, '正常', 2, '2022-12-09 18:38:22', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721312581750358016, 721311929594806272, '故障', 3, '故障', 3, '2022-12-09 18:38:41', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721312635236122624, 721311929594806272, '离线', 4, '离线', 4, '2022-12-09 18:38:54', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721719103600984064, 721718945509277696, '初始', 1, '初始', 1, '2022-12-10 21:34:03', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721719176636399616, 721718945509277696, '正常', 2, '正常', 2, '2022-12-10 21:34:21', 10000, '2022-12-10 21:34:47', 10000, 0);
INSERT INTO `sys_dict_item` VALUES (721719367615643648, 721718945509277696, '故障', 3, '故障', 3, '2022-12-10 21:35:06', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721719439262744576, 721718945509277696, '离线', 4, '离线', 4, '2022-12-10 21:35:23', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721722242693595136, 721721728207683584, '正常', 1, '正常', 1, '2022-12-10 21:46:32', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721722329096257536, 721721728207683584, '异常', 0, '异常', 2, '2022-12-10 21:46:52', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (721724745522872320, 721724634822606848, '接地杆', 1, '接地杆', 1, '2022-12-10 21:56:28', 10000, '2022-12-14 17:39:09', 10000, 0);
INSERT INTO `sys_dict_item` VALUES (722297640921858048, 722297521346445312, '是', 1, '是', 1, '2022-12-12 11:52:57', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (722297843930365952, 722297521346445312, '否', 0, '否', 0, '2022-12-12 11:53:46', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (723448383993282560, 723448223020089344, '实干者', 1, '实干者', 1, '2022-12-15 16:05:36', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (723448529942478848, 723448223020089344, '创新者', 2, '创新者', 2, '2022-12-15 16:06:11', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (723448600306122752, 723448223020089344, '监督者', 3, '监督者', 3, '2022-12-15 16:06:27', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (723456100887691264, 723456021653094400, '已归还', 1, '已归还', 1, '2022-12-15 16:36:16', 10000, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES (723456185314836480, 723456021653094400, '未归还', 0, '未归还', 2, '2022-12-15 16:36:36', 10000, '2022-12-15 16:39:20', 10000, 0);
INSERT INTO `sys_dict_item` VALUES (741125981783195648, 504670504825786512, '12', 12, '12', 12, '2023-02-02 10:50:04', 10000, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_func`;
CREATE TABLE `sys_func`  (
  `func_id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint NOT NULL COMMENT '上级主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint NULL DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限值',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '前端URL',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态',
  `orders` float NULL DEFAULT NULL COMMENT '排序',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `leaf` tinyint NULL DEFAULT NULL COMMENT '叶子',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`func_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '功能' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_func
-- ----------------------------
INSERT INTO `sys_func` VALUES (504681454035407041, 0, '系统首页', 1, NULL, 'http://localhost:9001', '/base/key/KeyList', 'el-icon-s-home', 1, 1, 'base/key/KeyList', 0, '2021-04-20 23:35:28', 10000, NULL, NULL, 1);
INSERT INTO `sys_func` VALUES (504681454035407042, 0, '系统管理', 1, NULL, 'http://localhost:9001', '', 'el-icon-s-tools', 1, 2, '', 0, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407043, 504681454035407042, '菜单维护', 1, NULL, 'http://localhost:9001', '/sys/func/FuncTree', 'el-icon-s-unfold', 1, 600, 'sys/func/FuncTree', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407044, 504681454035407042, '字典管理', 1, NULL, 'http://localhost:9001', '/sys/dict/DictList', 'el-icon-reading', 1, 500, 'sys/dict/DictList', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407046, 0, '生成案例', 1, NULL, 'http://localhost:9001', '', 'el-icon-film', 1, 3, '', 0, '2021-04-20 23:35:28', 10000, '2022-12-09 13:00:48', 10000, 0);
INSERT INTO `sys_func` VALUES (504681454035407049, 504681454035407042, '部门管理', 1, '', 'http://localhost:9001', '/sys/dept/DeptTree', 'el-icon-office-building', 1, 100, 'sys/dept/DeptTree', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407050, 504681454035407042, '用户管理', 1, NULL, 'http://localhost:9001', '/sys/user/UserList', 'el-icon-user-solid', 1, 200, 'sys/user/UserList', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407051, 504681454035407042, '角色管理', 1, NULL, 'http://localhost:9001', '/sys/role/RolePage', 'el-icon-s-custom\r\n', 1, 300, 'sys/role/RolePage', 1, '2021-04-20 23:35:28', 10000, '2022-07-04 01:38:58', -1, 0);
INSERT INTO `sys_func` VALUES (504681454035407052, 504681454035407042, '接口性能', 1, NULL, 'http://localhost:9001', '/sys/request/RequestList', 'el-icon-info', 1, 700, 'sys/request/RequestList', 1, '2021-04-20 23:35:28', 10000, '2022-07-04 01:39:05', -1, 0);
INSERT INTO `sys_func` VALUES (504681454035407053, 504681454035407042, '系统日志', 1, NULL, 'http://localhost:9001', '/sys/log/LogPage', 'el-icon-date', 1, 800, 'sys/log/LogPage', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (504681454035407054, 504681454035407042, '系统参数', 1, NULL, 'http://localhost:9001', '/sys/parameter/ParameterPage', 'el-icon-odometer', 1, NULL, 'sys/parameter/ParameterPage', 1, '2021-04-20 23:35:28', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (700185431353851904, 504681454035407042, '数据检查', 1, NULL, 'http://localhost:9001', '/sys/tableref/TableRefPage', 'el-icon-view', 1, NULL, 'sys/tableref/TableRefPage', 1, '2022-10-12 11:26:56', -1, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (710453565063168000, 504681454035407046, '钥匙柜', 1, NULL, 'http://localhost:9001', '/base/box/BoxPage', 'el-icon-film', 30, 1, 'base/box/BoxPage', 1, '2022-11-09 19:28:50', -1, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (710454853419139072, 504681454035407046, '钥匙', 1, NULL, 'http://localhost:9001', '/base/key/KeyPage', 'el-icon-key', 1, 60, 'base/key/KeyPage', 1, '2022-11-09 19:33:57', -1, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (728451207349141504, 504681454035407042, '接口管理', 1, NULL, 'http://localhost:9001', '/sys/api/ApiTree', 'el-icon-info', 1, NULL, 'sys/api/ApiTree', 1, '2022-12-29 11:25:02', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (731017883538161664, 0, '权限按钮', 2, NULL, 'http://localhost:9001', '/1', '1', 1, 1, '1', 0, '2023-01-05 13:24:05', 10001, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (731028376411701248, 731017883538161664, '人员新增', 2, NULL, 'http://localhost:9001', '/person_save', 'person_save', 1, NULL, 'person_save', 1, '2023-01-05 14:05:47', 10001, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (731067133504520192, 504681454035407042, '公共组件', 1, NULL, 'http://localhost:9001', '/component/demo', 'el-icon-s-finance', 1, 1, 'component/demo', 1, '2023-01-05 16:39:47', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (740906844066676736, 504681454035407046, '字典管理', 1, NULL, 'http://localhost:9001', '/demo/index', 'icon', 1, NULL, 'demo/index', 1, '2023-02-01 20:19:17', NULL, '2023-02-01 20:20:01', 10000, 0);
INSERT INTO `sys_func` VALUES (741206779144175616, 504681454035407042, '字段配置', 1, NULL, 'http://localhost:9001', '/sys/fieldconf/FieldConfPage', 'el-icon-s-flag', 1, NULL, 'sys/fieldconf/FieldConfPage', 1, '2023-02-02 16:11:07', 10000, '2023-02-02 16:11:47', 10000, 0);
INSERT INTO `sys_func` VALUES (746194759571210240, 0, '设备管理', 1, NULL, 'http://localhost:9001', '', 'el-icon-s-tools', 1, 1, NULL, 0, '2023-02-16 10:31:34', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746195721513861120, 746194759571210240, '设备告警', 1, NULL, 'http://localhost:9001', '/equ/equalert/EquAlertPage', 'el-icon-s-tools', 1, NULL, 'equ/equalert/EquAlertPage', 1, '2023-02-16 10:35:24', NULL, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746239798888366080, 746194759571210240, '设备台账', 1, NULL, NULL, '/equ/equdevice/EquDevicePage', 'el-icon-s-tools', 1, 2, 'equ/equdevice/EquDevicePage', 1, '2023-02-16 13:30:33', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746240095035588608, 746194759571210240, '维保计划', 1, NULL, NULL, '/equ/equplan/EquPlanPage', 'el-icon-s-tools', 1, 2, 'equ/equplan/EquPlanPage', 1, '2023-02-16 13:31:43', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746240331590139904, 746194759571210240, '维修记录', 1, NULL, NULL, '/equ/equrepair/EquRepairPage', 'el-icon-s-tools', 1, 2, 'equ/equrepair/EquRepairPage', 1, '2023-02-16 13:32:40', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746240574708776960, 746194759571210240, '设备规程', 1, NULL, NULL, '/equ/equrules/EquRulesPage', 'el-icon-s-tools', 1, 2, 'equ/equrules/EquRulesPage', 1, '2023-02-16 13:33:37', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746240875813666816, 746194759571210240, '规程条目', 1, NULL, NULL, '/equ/equrulesitem/EquRulesItemPage', 'el-icon-s-tools', 1, 2, 'equ/equrulesitem/EquRulesItemPage', 1, '2023-02-16 13:34:49', 10000, NULL, NULL, 1);
INSERT INTO `sys_func` VALUES (746241104994631680, 746194759571210240, '设备分类', 1, NULL, NULL, '/equ/equtype/EquTypeTree', 'el-icon-s-tools', 1, NULL, 'equ/equtype/EquTypeTree', 1, '2023-02-16 13:35:44', 10000, '2023-02-16 13:42:23', 10000, 0);
INSERT INTO `sys_func` VALUES (746241415599620096, 746194759571210240, '使用记录', 1, NULL, NULL, '/equ/equuse/EquUsePage', 'el-icon-s-tools', 1, 2, 'equ/equuse/EquUsePage', 1, '2023-02-16 13:36:58', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746241727546785792, 746194759571210240, '工单记录', 1, NULL, NULL, '/order/orderrecord/OrderRecordPage', 'el-icon-s-tools', 1, 2, 'order/orderrecord/OrderRecordPage', 1, '2023-02-16 13:38:12', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746241961878355968, 746194759571210240, '工单明细', 1, NULL, NULL, '/order/orderrecorddetail/OrderRecordDetailPage', 'el-icon-s-tools', 1, 2, 'order/orderrecorddetail/OrderRecordDetailPage', 1, '2023-02-16 13:39:08', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746242187498356736, 746194759571210240, '规程定义', 1, NULL, NULL, '/rule/ruledefine/RuleDefinePage', 'el-icon-s-tools', 1, 2, 'rule/ruledefine/RuleDefinePage', 1, '2023-02-16 13:40:02', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746243661326450688, 746194759571210240, '规程明细定义', 1, NULL, NULL, '/rule/ruledetaildefine/RuleDetailDefinePage', 'el-icon-s-tools', 1, 2, 'rule/ruledetaildefine/RuleDetailDefinePage', 1, '2023-02-16 13:45:53', 10000, NULL, NULL, 1);
INSERT INTO `sys_func` VALUES (746244061970563072, 746194759571210240, '提醒策略', 1, NULL, NULL, '/rule/rulenotifystrategy/RuleNotifyStrategyPage', 'el-icon-s-tools', 1, 2, 'rule/rulenotifystrategy/RuleNotifyStrategyPage', 1, '2023-02-16 13:47:29', 10000, NULL, NULL, 0);
INSERT INTO `sys_func` VALUES (746244192061095936, 746194759571210240, '规程类型', 1, NULL, NULL, '/rule/ruletype/RuleTypePage', 'el-icon-s-tools', 1, 2, 'rule/ruletype/RuleTypePage', 1, '2023-02-16 13:48:00', 10000, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志标题',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户主键',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `method` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'java类.方法',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求类型',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `operate_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '返回结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `type` tinyint NULL DEFAULT NULL COMMENT '角色类型',
  `order_num` int NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NULL DEFAULT NULL COMMENT '删除标记',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (504678162433708172, '管理员', 1, 2, '2023-03-08 22:57:07', 1000, '2023-03-08 22:57:38', 1000, 0, '测试数据');
INSERT INTO `sys_role` VALUES (504678162433708173, '老师', 2, 2, '2023-03-08 22:58:45', 1000, '2023-03-08 22:58:27', 1000, 0, '测试数据');
INSERT INTO `sys_role` VALUES (504678162433708174, '学生', 2, 2, '2023-03-08 22:58:45', 1000, '2023-03-08 22:58:27', 1000, 0, '测试数据');

-- ----------------------------
-- Table structure for sys_role_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_api`;
CREATE TABLE `sys_role_api`  (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `api_id` bigint NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `api_id`(`api_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `sys_role_api_ibfk_1` FOREIGN KEY (`api_id`) REFERENCES `sys_api` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_api_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色接口关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_api
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func`  (
  `role_func_id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `func_id` bigint NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_func_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `func_id`(`func_id` ASC) USING BTREE,
  CONSTRAINT `sys_role_func_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_func_ibfk_2` FOREIGN KEY (`func_id`) REFERENCES `sys_func` (`func_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '角色功能关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_func
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `user_role_id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '角色用户关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL COMMENT '主键',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '机构ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帐号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `login_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录名',
  `avatar` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态',
  `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '编号',
  `card_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '卡号',
  `group_name` tinyint NULL DEFAULT NULL COMMENT '分组',
  `fingerprint_count` tinyint NULL DEFAULT NULL COMMENT '指纹数量',
  `face_info` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '人脸信息',
  `type` tinyint NULL DEFAULT NULL COMMENT '人员类型1永久2临时',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `dr` tinyint NULL DEFAULT NULL COMMENT '删除标记',
  `code` int NULL DEFAULT NULL COMMENT '指纹模块专用编号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10000, 504670504792231946, '管理员', '827ccb0eea8a706c4c34a16891f84e7b', 'admin', 'sys/001.jpg', '1111', 0, 1, '1', '1234', 1, 3, '1', 2, '2022-09-22 17:05:59', '2022-09-16 00:00:00', '1111', NULL, NULL, '2022-12-27 14:56:36', NULL, 0, 1);
INSERT INTO `sys_user` VALUES (10001, 504670504792231947, '销售员', '827ccb0eea8a706c4c34a16891f84e7b', 'sale', 'sys/cc9273e9ae7f4a37b97ccd0ad09b8ab9.jpg', '1', 0, 1, '1234', '1234', 1, 0, '1', 2, '2022-09-22 17:05:59', '2022-09-16 00:00:00', '1111', NULL, NULL, '2022-12-27 14:56:36', NULL, 0, 2);

SET FOREIGN_KEY_CHECKS = 1;
