/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : alice_code_creator

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 06/06/2022 16:44:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary`;
CREATE TABLE `base_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组编码',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组名称',
  `dict_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `is_enable` tinyint(1) NULL DEFAULT NULL COMMENT '启停标志 0：正常、1：禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_dictionary
-- ----------------------------
INSERT INTO `base_dictionary` VALUES (1, 'STATUS', '状态', '0', '停用', 1, '2018-06-12 14:49:53', '系统用户', '2018-06-12 16:34:03', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (2, 'STATUS', '状态', '1', '启用', 1, '2018-06-12 15:31:08', '系统用户', '2018-06-12 15:31:08', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (5, 'MENU_TYPE', '菜单类别', '0', '路径', 1, '2018-06-26 17:22:48', '系统用户', '2018-06-26 17:22:48', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (6, 'MENU_TYPE', '菜单类别', '1', '菜单', 1, '2018-06-26 17:23:08', '系统用户', '2018-06-26 17:23:08', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (7, 'MENU_LEVEL', '菜单级别', '0', '一级菜单', 1, '2018-06-26 17:39:17', '系统用户', '2018-06-26 17:39:17', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (8, 'MENU_LEVEL', '菜单级别', '1', '二级菜单', 1, '2018-06-26 17:39:28', '系统用户', '2018-06-26 17:39:56', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (9, 'MENU_LEVEL', '菜单级别', '2', '三级菜单', 1, '2018-06-26 17:39:45', '系统用户', '2018-06-26 17:39:45', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (10, 'USER_SEX', '性别', '0', '未填写', 0, '2018-06-27 15:59:29', '系统用户', '2021-12-27 13:45:41', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (11, 'USER_SEX', '性别', '1', '男', 1, '2018-06-27 15:59:42', '系统用户', '2018-06-27 15:59:42', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (12, 'USER_SEX', '性别', '2', '女', 1, '2018-06-27 15:59:52', '系统用户', '2018-06-27 15:59:52', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (13, 'DATASOURCE_TYPE', '数据源类型', 'MySQL', 'MySQL', 1, '2021-12-27 13:41:38', '刘小平', '2021-12-27 13:41:38', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (14, 'DATASOURCE_TYPE', '数据源类型', 'Oracle', 'Oracle', 1, '2021-12-27 13:41:50', '刘小平', '2021-12-27 13:41:50', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (15, 'DATASOURCE_TYPE', '数据源类型', 'SQLServer', 'SQLServer', 1, '2021-12-27 13:42:01', '刘小平', '2021-12-27 13:42:01', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (16, 'DATASOURCE_TYPE', '数据源类型', 'ClickHouse', 'ClickHouse', 0, '2021-12-27 13:43:02', '刘小平', '2021-12-27 13:43:02', '刘小平', 0);

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单英文名称',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单中文名称',
  `menu_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单描述',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_type` tinyint(1) NOT NULL COMMENT '菜单类型',
  `menu_level` tinyint(1) NOT NULL COMMENT '菜单级别',
  `menu_order` int(4) NULL DEFAULT NULL COMMENT '菜单顺序',
  `menu_parent_id` bigint(21) NULL DEFAULT NULL COMMENT '父级主键',
  `menu_parent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES (1, 'DEVELOP', '开发工具', NULL, '', 'icon mdi fa fa-wrench fa-fw', 0, 0, NULL, 0, NULL, '2018-06-26 17:15:08', '系统用户', '2018-06-28 10:25:22', '系统用户', 0);
INSERT INTO `base_menu` VALUES (2, 'DEVELOP_GENERATOR', '代码生成', NULL, '#biz/generator/generator', 'icon mdi fa fa-code fa-fw', 1, 0, NULL, 0, NULL, '2018-06-28 10:02:30', '系统用户', '2021-12-27 13:38:17', '刘小平', 0);
INSERT INTO `base_menu` VALUES (3, 'DEVELOP_MAPPING', '数据类型映射', NULL, '#biz/generator/mapping/generator_config_mapping_list', 'icon mdi fa fa-clone fa-fw', 1, 1, NULL, 50, NULL, '2018-06-28 10:05:55', '系统用户', '2021-12-27 13:38:33', '刘小平', 0);
INSERT INTO `base_menu` VALUES (5, 'BASIC', '系统数据', NULL, '', 'icon mdi fa fa-building fa-fw', 0, 0, NULL, 0, NULL, '2018-06-28 10:26:38', '系统用户', '2021-01-04 11:28:32', '刘小平', 0);
INSERT INTO `base_menu` VALUES (6, 'BASIC_DICTIONARY_NORMAL', '数据字典', NULL, '#biz/basic/base/dictionary/dictionary_list', 'icon mdi fa fa-book fa-fw', 1, 1, NULL, 5, NULL, '2018-06-28 10:29:08', '系统用户', '2020-01-30 12:09:35', '刘小平', 0);
INSERT INTO `base_menu` VALUES (7, 'BASIC_USER', '用户管理', NULL, '#biz/basic/base/user/user_list', 'icon mdi fa fa-user fa-fw', 1, 1, NULL, 5, NULL, '2018-06-28 10:31:14', '系统用户', '2018-06-28 10:31:14', '系统用户', 0);
INSERT INTO `base_menu` VALUES (8, 'BASIC_ROLE', '角色管理', NULL, '#biz/basic/base/role/role_list', 'icon mdi fa fa-user-secret fa-fw', 1, 1, NULL, 5, NULL, '2018-06-28 10:32:29', '系统用户', '2018-06-28 10:32:29', '系统用户', 0);
INSERT INTO `base_menu` VALUES (9, 'BASIC_MENU', '菜单管理', NULL, '#biz/basic/base/menu/menu_list', 'icon mdi fa fa-bars fa-fw', 1, 1, NULL, 5, NULL, '2018-06-28 10:33:00', '系统用户', '2018-06-28 10:33:00', '系统用户', 0);
INSERT INTO `base_menu` VALUES (48, 'DEVELOP_GROUP', '模板管理', NULL, '#biz/generator/config/group/generator_config_group_list', 'icon mdi fa fa-file-code-o fa-fw', 1, 1, NULL, 50, NULL, '2020-12-30 16:56:08', '刘小平', '2021-01-04 15:24:00', '刘小平', 0);
INSERT INTO `base_menu` VALUES (49, 'DEVELOP_DATASOURCE', '数据源配置', NULL, '#biz/generator/config/datasource/generator_config_datasource_list', 'icon mdi fa fa-database fa-fw', 1, 1, NULL, 50, NULL, '2020-12-31 11:52:31', '刘小平', '2021-01-04 15:32:07', '刘小平', 0);
INSERT INTO `base_menu` VALUES (50, 'CONFIG', '规则配置', NULL, '', 'icon mdi fa fa-cog fa-fw', 0, 0, NULL, 0, NULL, '2020-12-31 11:54:20', '刘小平', '2021-12-27 13:37:44', '刘小平', 0);

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0:正常 1:禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES (1, '超级管理员', '拥有全部菜单', 1, '2018-06-27 15:01:06', '系统用户', '2021-12-07 13:22:03', '刘小平', 0);
INSERT INTO `base_role` VALUES (3, '注册用户', '代码生成功能', 1, '2018-06-28 09:43:33', '系统用户', '2020-12-12 11:23:21', '刘小平', 0);

-- ----------------------------
-- Table structure for base_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `base_role_menu_rel`;
CREATE TABLE `base_role_menu_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单表主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_role_menu_rel
-- ----------------------------
INSERT INTO `base_role_menu_rel` VALUES (1, 1, 1, '2018-06-28 15:37:11', '系统用户', '2021-12-27 13:39:08', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (2, 1, 2, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (3, 1, 3, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (5, 1, 5, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (6, 1, 6, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (7, 1, 7, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (8, 1, 8, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (9, 1, 9, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (148, 3, 1, '2020-12-12 11:23:37', '刘小平', '2021-12-27 13:39:03', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (149, 3, 2, '2020-12-12 11:23:37', '刘小平', '2020-12-12 11:23:37', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (150, 3, 3, '2020-12-12 11:23:37', '刘小平', '2020-12-12 11:23:37', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (152, 3, 47, '2020-12-30 14:20:34', '刘小平', '2021-01-04 15:28:20', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (153, 1, 47, '2020-12-30 14:20:40', '刘小平', '2021-01-04 15:28:16', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (154, 1, 48, '2020-12-30 16:56:16', '刘小平', '2020-12-30 16:56:16', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (155, 3, 48, '2020-12-30 16:56:21', '刘小平', '2020-12-30 16:56:21', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (156, 1, 50, '2020-12-31 12:43:15', '刘小平', '2020-12-31 12:43:15', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (157, 1, 49, '2020-12-31 12:43:15', '刘小平', '2020-12-31 12:43:15', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (158, 3, 50, '2020-12-31 12:43:20', '刘小平', '2020-12-31 12:43:20', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (159, 3, 49, '2020-12-31 12:43:20', '刘小平', '2020-12-31 12:43:20', '刘小平', 0);

-- ----------------------------
-- Table structure for base_role_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `base_role_user_rel`;
CREATE TABLE `base_role_user_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户表主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与用户关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_role_user_rel
-- ----------------------------
INSERT INTO `base_role_user_rel` VALUES (3, 3, 3, '2018-06-28 09:52:16', '系统用户', '2018-06-28 09:52:16', '系统用户', 0);
INSERT INTO `base_role_user_rel` VALUES (4, 1, 3, '2018-06-28 09:52:57', '系统用户', '2018-06-28 09:52:57', '系统用户', 0);
INSERT INTO `base_role_user_rel` VALUES (29, 3, 5, '2020-12-12 11:38:17', 'liuxp2', '2020-12-12 11:38:17', 'liuxp2', 0);
INSERT INTO `base_role_user_rel` VALUES (30, 3, 6, '2021-01-04 15:29:46', 'test1', '2021-01-04 15:29:46', 'test1', 0);
INSERT INTO `base_role_user_rel` VALUES (31, 3, 7, '2021-11-17 20:35:25', 'admin', '2021-11-17 20:35:25', 'admin', 0);
INSERT INTO `base_role_user_rel` VALUES (32, 1, 7, '2021-11-17 20:35:30', '刘小平', '2021-11-17 20:35:30', '刘小平', 0);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别 0:未填写 1:男 2:女',
  `user_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `user_wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `user_signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `is_enable` tinyint(1) NULL DEFAULT 0 COMMENT '用户状态 0:禁用 1:正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES (3, 'liuxp', '刘小平', '$2a$10$wU9uXTBGaa7dqydOKxAjzeN.kKyk8YUvQ.d.KbT4ubGJSkZy.lw1K', 1, '', '', NULL, '', '1986-09-15', NULL, 1, '2018-02-03 20:11:18', 'liuxp', '2020-11-13 14:51:37', '刘小平', 0);
INSERT INTO `base_user` VALUES (5, 'liuxp2', 'liuxp2', '$2a$10$vfTRsB0a4hFrCvJLSJd/de9.NVQ6tuV.8FAuH7pwOMUnToC0w4tdu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-12-12 11:38:17', 'liuxp2', '2020-12-12 11:38:17', 'liuxp2', 0);
INSERT INTO `base_user` VALUES (6, 'test1', 'test1', '$2a$10$dwsW7RHykqp1hGkVvWUUeO845BF.VGIBIRZAedvRqBpUXHWYGQbOm', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2021-01-04 15:29:46', 'test1', '2021-01-04 15:29:46', 'test1', 0);
INSERT INTO `base_user` VALUES (7, 'admin', '刘小平', '$2a$10$ikNiSnqeAc4KqkvbQ3ePduLQXz7qiyzzRu.VRSl/T2Xkw.o2tPVdO', 1, NULL, '', NULL, '', NULL, NULL, 1, '2021-11-17 20:35:25', 'admin', '2021-11-17 20:35:25', 'admin', 0);

-- ----------------------------
-- Table structure for generator_config_datasource
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_datasource`;
CREATE TABLE `generator_config_datasource`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `datasource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据源名称',
  `datasource_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据源类型（MySQL,Oracle,SQLServer）',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'JDBC驱动',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据源地址',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `owner_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  `owner_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所有者用户名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of generator_config_datasource
-- ----------------------------
INSERT INTO `generator_config_datasource` VALUES (1, '云社区测试环境', 'MySQL', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.10.231.67:3306/gov_cloud_service_finance?characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true', 'root', 'Viewhigh*132', 3, '刘小平', '2021-01-04 11:39:38', '刘小平', '2021-01-04 11:40:00', '刘小平', 0);
INSERT INTO `generator_config_datasource` VALUES (2, '火查查公网开发数据库', 'MySQL', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://lxp135.asuscomm.com:3306/disaster_platform_basic?characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai', 'disaster', '123456', 3, '刘小平', '2021-01-08 15:38:53', '刘小平', '2021-01-08 15:38:53', '刘小平', 0);
INSERT INTO `generator_config_datasource` VALUES (3, '10.0.22.151Oracle', 'Oracle', 'oracle.jdbc.driver.OracleDriver', 'jdbc:oracle:thin:@10.0.22.151:1521:orcl', 'Daping_demo', 'Daping_demo*123', 3, '刘小平', '2021-04-01 17:03:36', '刘小平', '2021-04-01 17:03:36', '刘小平', 0);
INSERT INTO `generator_config_datasource` VALUES (4, 'SQLServer测试', 'SQLServer', 'com.microsoft.sqlserver.jdbc.SQLServerDriver', 'jdbc:sqlserver://10.0.23.60:1433;DatabaseName=master', 'sa', 'SYviewhigh@123456', 3, '刘小平', '2021-04-06 17:29:04', '刘小平', '2021-04-06 17:29:04', '刘小平', 0);
INSERT INTO `generator_config_datasource` VALUES (5, '顺义HRP SQLServer', 'SQLServer', 'com.microsoft.sqlserver.jdbc.SQLServerDriver', 'jdbc:sqlserver://10.0.58.31:1433;DatabaseName=hrp332fy1018', 'sa', 'vh_2020', 3, '刘小平', '2021-11-02 17:39:44', '刘小平', '2021-11-02 17:39:44', '刘小平', 0);
INSERT INTO `generator_config_datasource` VALUES (6, 'localhost', 'MySQL', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://localhost:3306/alice_code_creator?characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true	', 'root', 'root', 3, '刘小平', '2021-12-07 17:52:23', '刘小平', '2021-12-07 17:52:23', '刘小平', 0);

-- ----------------------------
-- Table structure for generator_config_group
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_group`;
CREATE TABLE `generator_config_group`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `group_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组内容描述',
  `template_amount` int(4) NULL DEFAULT NULL COMMENT '模板总数',
  `default_sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认签名',
  `default_package` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认包路径',
  `default_table_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认表前缀',
  `default_field_unique` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识字段',
  `default_field_ext` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展标识字段（可以存多个，按照逗号分割）',
  `default_field_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '逻辑删除标识字段名称',
  `owner_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  `owner_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所有者用户名称',
  `is_public` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否公开 0：私有 1：公开',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板分组' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of generator_config_group
-- ----------------------------
INSERT INTO `generator_config_group` VALUES (2, '财务云社区 - 会计核算', '望海政府事业部微服务架构下的财务云社区会计核算服务。', 7, 'contact@liuxp.me', 'com.vh.gov.cloud.finance', 't', 'id', 'created_by,created_time,last_modified_by,last_modified_time,tenant_id', 'is_deleted', 3, '刘小平', 1, '2021-01-04 14:42:25', '刘小平', '2021-01-15 11:44:26', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (3, '财务云社区 - 基础平台', '望海政府事业部微服务架构下的财务云社区基础平台服务。', 7, 'contact@liuxp.me', 'com.vh.gov.cloud.platform', 't', 'id', 'created_by,created_time,last_modified_by,last_modified_time', 'is_deleted', 3, '刘小平', 0, '2021-01-04 15:23:10', '刘小平', '2021-01-28 11:34:45', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (4, 'ALICE CODECREATOR', '代码生成器自身模板组', 13, 'contact@liuxp.me', 'alice.code.creator', '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '刘小平', 1, '2021-01-04 16:33:06', '刘小平', '2021-01-04 16:33:06', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (6, '私人云平台 - 基础平台', '私人云平台，基础数据服务', 15, 'contact@liuxp.me', 'org.liuxp.cloud.platform.basic', 'basic', 'id', 'remark,create_user,create_time,update_user,update_time,ts', 'is_delete', 3, '刘小平', 0, '2021-01-14 17:55:00', '刘小平', '2021-01-15 10:11:01', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (7, '龙岗报表数据生成', '宽表数据', 1, 'contact@liuxp.me', 'com.vh.gov.cloud.report', '', '', '', '', 3, '刘小平', 0, '2021-03-11 11:53:48', '刘小平', '2021-03-11 11:54:15', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (8, '指标库', '指标库', 13, 'contact@liuxp.me', 'com.vh.gov.cloud.indicator', 't', 'id', 'created_by,created_time,last_modified_by,last_modified_time', 'is_deleted', 3, '刘小平', 0, '2021-05-25 12:50:31', '刘小平', '2021-05-25 14:05:16', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (9, '顺义数据同步', 'SQLServer数据库，不规范', 6, 'contact@liuxp.me', 'com.vh.gov.cloud.link', '', '', 'comp_code,copy_code', '', 3, '刘小平', 0, '2021-11-02 17:49:23', '刘小平', '2021-11-02 18:39:17', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (10, '郑州绩效', '郑州绩效', 1, 'liuxiaoping@viewhigh.com', 'nhc.platform', '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '刘小平', 0, '2021-11-17 20:28:41', '刘小平', '2021-12-16 14:39:18', '刘小平', 1);
INSERT INTO `generator_config_group` VALUES (11, '郑州绩效的拷贝', '郑州绩效', 1, 'liuxiaoping@viewhigh.com', 'nhc.platform', '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '刘小平', 0, '2021-12-16 14:33:27', '刘小平', '2021-12-16 14:33:57', '刘小平', 1);
INSERT INTO `generator_config_group` VALUES (12, '郑州nhc_platform', '郑州新的系统平台，单体应用，使用lombok', 13, 'liuxiaoping@viewhigh.com', 'nhc.platform', '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '刘小平', 0, '2021-12-16 14:37:03', '刘小平', '2021-12-16 14:38:14', '刘小平', 0);
INSERT INTO `generator_config_group` VALUES (13, '龙岗报表数据生成的拷贝', '宽表数据', 1, 'contact@liuxp.me', 'com.vh.gov.cloud.report', '', '', '', '', 3, '刘小平', 0, '2021-12-16 15:56:01', '刘小平', '2021-12-16 15:56:17', '刘小平', 1);
INSERT INTO `generator_config_group` VALUES (14, '郑州nhc_platform的拷贝', '郑州新的系统平台，单体应用，使用lombok', 13, 'liuxiaoping@viewhigh.com', 'nhc.platform', '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '刘小平', 0, '2022-01-10 14:25:15', '刘小平', '2022-01-10 14:25:29', '刘小平', 1);

-- ----------------------------
-- Table structure for generator_config_individual
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_individual`;
CREATE TABLE `generator_config_individual`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `default_sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认签名',
  `default_package` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认包路径',
  `default_table_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认表前缀',
  `default_group_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '默认分组编号',
  `default_group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认分组名称',
  `default_datasource_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '默认数据源编号',
  `default_datasource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认数据源名称',
  `default_field_unique` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识字段',
  `default_field_ext` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展标识字段（可以存多个，按照逗号分割）',
  `default_field_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '逻辑删除标识字段名称',
  `owner_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户个性化配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of generator_config_individual
-- ----------------------------
INSERT INTO `generator_config_individual` VALUES (1, 'contact@liuxp.me', 'com.vh.gov.cloud.finance', 't_', NULL, '', NULL, '', 'id', 'create_user,create_time,update_user,update_time', 'is_delete', 3, '2020-12-30 16:04:23', '刘小平', '2020-12-30 16:10:42', '刘小平', 0);

-- ----------------------------
-- Table structure for generator_config_mapping
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_mapping`;
CREATE TABLE `generator_config_mapping`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `datasource_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据源类型（MySQL,Oracle,SQLServer）',
  `db_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库类型',
  `jdbc_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'jdbc类型',
  `java_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'java类型',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据类型映射' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of generator_config_mapping
-- ----------------------------
INSERT INTO `generator_config_mapping` VALUES (1, 'MySQL', 'bit', 'bit', 'Boolean', '2018-12-26 17:37:39', 'SYSTEM', '2021-01-12 11:26:25', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (2, 'MySQL', 'blob', 'blob', 'byte[]', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 18:32:03', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (3, 'MySQL', 'bigint', 'bigint', 'Long', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (4, 'MySQL', 'char', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (5, 'MySQL', 'date', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (6, 'MySQL', 'datetime', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (7, 'MySQL', 'decimal', 'decimal', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (8, 'MySQL', 'double', 'double', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (9, 'MySQL', 'float', 'float', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (10, 'MySQL', 'int', 'integer', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (11, 'MySQL', 'smallint', 'integer', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (12, 'MySQL', 'numeric', 'numeric', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2021-11-02 18:30:45', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (13, 'MySQL', 'timestamp', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (14, 'MySQL', 'tinyint', 'tinyint', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (15, 'MySQL', 'varchar', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2021-12-27 15:03:44', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (16, 'MySQL', 'text', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (17, 'MySQL', 'longtext', 'varchar', 'String', '2021-03-23 13:54:19', '刘小平', '2021-03-23 13:54:19', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (18, 'SQLServer', 'nvarchar', 'VARCHAR', 'String', '2021-11-02 18:29:23', '刘小平', '2021-12-27 17:25:58', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (19, 'SQLServer', 'bigint', 'BIGINT', 'Long', '2021-12-27 16:48:08', '刘小平', '2021-12-27 16:48:08', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (20, 'SQLServer', 'timestamp', 'BINARY', 'byte[]', '2021-12-27 16:51:55', '刘小平', '2021-12-27 16:51:55', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (21, 'SQLServer', 'binary', 'BINARY', 'byte[]', '2021-12-27 16:52:18', '刘小平', '2021-12-27 16:52:18', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (22, 'SQLServer', 'bit', 'BIT', 'Boolean', '2021-12-27 16:52:32', '刘小平', '2021-12-27 16:52:32', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (23, 'SQLServer', 'char', 'CHAR', 'String', '2021-12-27 16:52:47', '刘小平', '2021-12-27 16:52:47', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (24, 'SQLServer', 'decimal', 'DECIMAL', 'BigDecimal', '2021-12-27 16:53:06', '刘小平', '2021-12-27 16:53:06', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (25, 'SQLServer', 'money', 'DECIMAL', 'BigDecimal', '2021-12-27 17:21:56', '刘小平', '2021-12-27 17:21:56', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (26, 'SQLServer', 'smallmoney', 'DECIMAL', 'BigDecimal', '2021-12-27 17:22:09', '刘小平', '2021-12-27 17:22:09', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (27, 'SQLServer', 'float', 'DOUBLE', 'Double', '2021-12-27 17:22:25', '刘小平', '2021-12-27 17:22:25', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (28, 'SQLServer', 'int', 'INTEGER', 'Integer', '2021-12-27 17:22:41', '刘小平', '2021-12-27 17:22:41', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (29, 'SQLServer', 'image', 'LONGVARBINARY', 'byte[]', '2021-12-27 17:23:00', '刘小平', '2021-12-27 17:23:00', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (30, 'SQLServer', 'varbinary', 'VARBINARY', 'byte[]', '2021-12-27 17:23:20', '刘小平', '2021-12-27 17:24:09', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (31, 'SQLServer', 'varbinary(max)', 'LONGVARBINARY', 'byte[]', '2021-12-27 17:24:43', '刘小平', '2021-12-27 17:24:43', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (32, 'SQLServer', 'varchar(max)', 'LONGVARCHAR', 'String', '2021-12-27 17:25:06', '刘小平', '2021-12-27 17:25:06', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (33, 'SQLServer', 'text', 'LONGVARCHAR', 'String', '2021-12-27 17:25:18', '刘小平', '2021-12-27 17:25:18', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (34, 'SQLServer', 'nchar', 'CHAR', 'String', '2021-12-27 17:25:44', '刘小平', '2021-12-27 17:25:44', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (35, 'SQLServer', 'nvarchar(max)', 'LONGVARCHAR', 'String', '2021-12-27 17:26:15', '刘小平', '2021-12-27 17:26:15', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (36, 'SQLServer', 'ntext', 'LONGVARCHAR', 'String', '2021-12-27 17:26:27', '刘小平', '2021-12-27 17:26:27', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (37, 'SQLServer', 'numeric', 'NUMERIC', 'BigDecimal', '2021-12-27 17:26:40', '刘小平', '2021-12-27 17:26:40', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (38, 'SQLServer', 'real', 'REAL', 'float', '2021-12-27 17:26:52', '刘小平', '2021-12-27 17:26:52', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (39, 'SQLServer', 'smallint', 'SMALLINT', 'short', '2021-12-27 17:27:07', '刘小平', '2021-12-27 17:27:07', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (40, 'SQLServer', 'datetime', 'TIMESTAMP', 'Date', '2021-12-27 17:27:20', '刘小平', '2021-12-27 17:30:53', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (41, 'SQLServer', 'smalldatetime', 'TIMESTAMP', 'Date', '2021-12-27 17:31:10', '刘小平', '2021-12-27 17:31:10', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (42, 'SQLServer', 'udt', 'VARBINARY', 'byte[]', '2021-12-27 17:31:31', '刘小平', '2021-12-27 17:31:31', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (43, 'SQLServer', 'varchar', 'VARCHAR', 'String', '2021-12-27 17:31:44', '刘小平', '2021-12-27 17:31:44', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (44, 'SQLServer', 'tinyint', 'TINYINT', 'Integer', '2021-12-27 17:32:08', '刘小平', '2021-12-27 17:32:08', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (45, 'SQLServer', 'uniqueidentifier', 'CHAR', 'String', '2021-12-27 17:33:59', '刘小平', '2021-12-27 17:33:59', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (46, 'SQLServer', 'xml', 'LONGVARCHAR', 'String', '2021-12-27 17:34:12', '刘小平', '2021-12-27 17:34:12', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (47, 'SQLServer', 'date', 'DATE', 'Date', '2021-12-27 17:34:30', '刘小平', '2021-12-27 17:34:30', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (48, 'SQLServer', 'datetime2', 'TIMESTAMP', 'Date', '2021-12-27 17:36:15', '刘小平', '2021-12-27 17:36:15', '刘小平', 0);

-- ----------------------------
-- Table structure for generator_config_template
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_template`;
CREATE TABLE `generator_config_template`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `template_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板英文名',
  `template_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板中文名',
  `template_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板内容',
  `file_path` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成地址相对路径',
  `file_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件后缀与扩展名',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '分组编号',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成器模板' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;