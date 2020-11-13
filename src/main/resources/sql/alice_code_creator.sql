/*
 Navicat Premium Data Transfer

 Source Server         : MySQL本机
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : alice_code_creator

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 13/11/2020 14:53:00
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
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

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
INSERT INTO `base_dictionary` VALUES (10, 'USER_SEX', '性别', '0', '未填写', 1, '2018-06-27 15:59:29', '系统用户', '2018-06-27 15:59:29', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (11, 'USER_SEX', '性别', '1', '男', 1, '2018-06-27 15:59:42', '系统用户', '2018-06-27 15:59:42', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (12, 'USER_SEX', '性别', '2', '女', 1, '2018-06-27 15:59:52', '系统用户', '2018-06-27 15:59:52', '系统用户', 0);
INSERT INTO `base_dictionary` VALUES (13, 'NATION_TYPE', '民族类型', '1', '汉族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (14, 'NATION_TYPE', '民族类型', '2', '蒙古族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (15, 'NATION_TYPE', '民族类型', '3', '回族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (16, 'NATION_TYPE', '民族类型', '4', '藏族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (17, 'NATION_TYPE', '民族类型', '5', '维吾尔族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (18, 'NATION_TYPE', '民族类型', '6', '苗族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (19, 'NATION_TYPE', '民族类型', '7', '彝族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (20, 'NATION_TYPE', '民族类型', '8', '壮族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (21, 'NATION_TYPE', '民族类型', '9', '布依族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (22, 'NATION_TYPE', '民族类型', '10', '朝鲜族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (23, 'NATION_TYPE', '民族类型', '11', '满族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (24, 'NATION_TYPE', '民族类型', '12', '侗族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (25, 'NATION_TYPE', '民族类型', '13', '瑶族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (26, 'NATION_TYPE', '民族类型', '14', '白族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (27, 'NATION_TYPE', '民族类型', '15', '土家族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (28, 'NATION_TYPE', '民族类型', '16', '哈尼族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (29, 'NATION_TYPE', '民族类型', '17', '哈萨克族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (30, 'NATION_TYPE', '民族类型', '18', '傣族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (31, 'NATION_TYPE', '民族类型', '19', '黎族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (32, 'NATION_TYPE', '民族类型', '20', '傈僳族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (33, 'NATION_TYPE', '民族类型', '21', '佤族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (34, 'NATION_TYPE', '民族类型', '22', '畲族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (35, 'NATION_TYPE', '民族类型', '23', '高山族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (36, 'NATION_TYPE', '民族类型', '24', '拉祜族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (37, 'NATION_TYPE', '民族类型', '25', '水族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (38, 'NATION_TYPE', '民族类型', '26', '东乡族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (39, 'NATION_TYPE', '民族类型', '27', '纳西族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (40, 'NATION_TYPE', '民族类型', '28', '景颇族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (41, 'NATION_TYPE', '民族类型', '29', '柯尔克孜族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (42, 'NATION_TYPE', '民族类型', '30', '土族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (43, 'NATION_TYPE', '民族类型', '31', '达斡尔族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (44, 'NATION_TYPE', '民族类型', '32', '仫佬族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (45, 'NATION_TYPE', '民族类型', '33', '羌族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (46, 'NATION_TYPE', '民族类型', '34', ' 布朗族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (47, 'NATION_TYPE', '民族类型', '35', ' 撒拉族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (48, 'NATION_TYPE', '民族类型', '36', ' 毛难族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (49, 'NATION_TYPE', '民族类型', '37', ' 仡佬族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (50, 'NATION_TYPE', '民族类型', '38', ' 锡伯族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (51, 'NATION_TYPE', '民族类型', '39', ' 阿昌族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (52, 'NATION_TYPE', '民族类型', '40', ' 普米族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (53, 'NATION_TYPE', '民族类型', '41', ' 塔吉克族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (54, 'NATION_TYPE', '民族类型', '42', ' 怒族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (55, 'NATION_TYPE', '民族类型', '43', ' 乌孜别克族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (56, 'NATION_TYPE', '民族类型', '44', ' 俄罗斯族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (57, 'NATION_TYPE', '民族类型', '45', ' 鄂温克族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (58, 'NATION_TYPE', '民族类型', '46', ' 崩龙族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (59, 'NATION_TYPE', '民族类型', '47', ' 保安族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (60, 'NATION_TYPE', '民族类型', '48', ' 裕固族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (61, 'NATION_TYPE', '民族类型', '49', ' 京族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (62, 'NATION_TYPE', '民族类型', '50', ' 塔塔尔族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (63, 'NATION_TYPE', '民族类型', '51', ' 独龙族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (64, 'NATION_TYPE', '民族类型', '52', ' 鄂伦春族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (65, 'NATION_TYPE', '民族类型', '53', ' 赫哲族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (66, 'NATION_TYPE', '民族类型', '54', ' 门巴族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (67, 'NATION_TYPE', '民族类型', '55', ' 珞巴族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (68, 'NATION_TYPE', '民族类型', '56', ' 基诺族', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (69, 'NATION_TYPE', '民族类型', '57', ' 其他', 1, '2017-11-14 16:05:41', '管理员', '2017-11-14 16:05:41', '管理员', 0);
INSERT INTO `base_dictionary` VALUES (70, 'IS_FINISH', '是否完结', '0', '连载中', 1, '2020-05-11 16:16:43', '刘小平', '2020-05-11 16:16:43', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (71, 'IS_FINISH', '是否完结', '1', '完结', 1, '2020-05-11 16:16:58', '刘小平', '2020-05-11 16:16:58', '刘小平', 0);
INSERT INTO `base_dictionary` VALUES (72, 'IS_FINISH', '是否完结', '2', '太监', 1, '2020-05-11 16:17:12', '刘小平', '2020-05-11 16:17:12', '刘小平', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES (1, 'DEVELOP', '开发工具', NULL, '', 'icon mdi fa fa-wrench', 0, 0, NULL, 0, NULL, '2018-06-26 17:15:08', '系统用户', '2018-06-28 10:25:22', '系统用户', 0);
INSERT INTO `base_menu` VALUES (2, 'DEVELOP_GENERATOR', '代码生成', NULL, '#biz/generator/generator', 'icon mdi fa fa-code', 1, 1, NULL, 1, NULL, '2018-06-28 10:02:30', '系统用户', '2018-06-28 10:25:35', '系统用户', 0);
INSERT INTO `base_menu` VALUES (3, 'DEVELOP_MAPPING', '数据类型映射', NULL, '#biz/generator/mapping/generator_config_mapping_list', 'icon mdi fa fa-clone', 1, 1, NULL, 1, NULL, '2018-06-28 10:05:55', '系统用户', '2018-12-27 10:41:01', '刘小平', 0);
INSERT INTO `base_menu` VALUES (4, 'DEVELOP_TEMPLATE', '模板管理', NULL, '#biz/generator/template/generator_config_template_list', 'icon mdi fa fa-file-code-o', 1, 1, NULL, 1, NULL, '2018-06-28 10:06:37', '系统用户', '2019-03-12 17:34:42', '刘小平', 0);
INSERT INTO `base_menu` VALUES (5, 'BASIC', '基础配置', NULL, '', 'icon mdi fa fa-cog', 0, 0, NULL, 0, NULL, '2018-06-28 10:26:38', '系统用户', '2018-06-28 10:27:09', '系统用户', 0);
INSERT INTO `base_menu` VALUES (6, 'BASIC_DICTIONARY_NORMAL', '数据字典', NULL, '#biz/basic/base/dictionary/dictionary_list', 'icon mdi fa fa-book', 1, 1, NULL, 5, NULL, '2018-06-28 10:29:08', '系统用户', '2020-01-30 12:09:35', '刘小平', 0);
INSERT INTO `base_menu` VALUES (7, 'BASIC_USER', '用户管理', NULL, '#biz/basic/base/user/user_list', 'icon mdi fa fa-user', 1, 1, NULL, 5, NULL, '2018-06-28 10:31:14', '系统用户', '2018-06-28 10:31:14', '系统用户', 0);
INSERT INTO `base_menu` VALUES (8, 'BASIC_ROLE', '角色管理', NULL, '#biz/basic/base/role/role_list', 'icon mdi fa fa-user-secret', 1, 1, NULL, 5, NULL, '2018-06-28 10:32:29', '系统用户', '2018-06-28 10:32:29', '系统用户', 0);
INSERT INTO `base_menu` VALUES (9, 'BASIC_MENU', '菜单管理', NULL, '#biz/basic/base/menu/menu_list', 'icon mdi fa fa-bars', 1, 1, NULL, 5, NULL, '2018-06-28 10:33:00', '系统用户', '2018-06-28 10:33:00', '系统用户', 0);
INSERT INTO `base_menu` VALUES (45, 'BASIC_RESOURCE', '资源管理', NULL, '', 'icon mdi fa fa-server', 0, 1, NULL, 5, NULL, '2018-12-12 15:07:00', '刘小平', '2019-03-12 17:34:00', '刘小平', 0);
INSERT INTO `base_menu` VALUES (46, 'BASIC_RESOURCE_FILE', '文件管理', NULL, '#biz/basic/resource/file/resource_file_list', 'icon mdi fa fa-file', 1, 2, NULL, 45, NULL, '2018-12-12 15:08:41', '刘小平', '2019-01-11 16:19:06', '刘小平', 0);
INSERT INTO `base_menu` VALUES (47, 'BASIC_RESOURCE_SMS', '短信管理', NULL, '#biz/basic/resource/sms/log/resource_sms_log_list', 'icon mdi fa fa-commenting-o', 1, 2, NULL, 45, NULL, '2018-12-12 15:09:22', '刘小平', '2019-01-11 16:19:38', '刘小平', 0);
INSERT INTO `base_menu` VALUES (62, 'MONITOR', '平台监控', NULL, '', 'icon mdi fa fa-thermometer-three-quarters', 0, 0, NULL, 0, NULL, '2019-03-07 16:55:44', '刘小平', '2019-03-12 17:33:37', '刘小平', 0);
INSERT INTO `base_menu` VALUES (63, 'MONITOR_SYSTEM', '系统信息', NULL, '#biz/monitor/monitor_system', '', 1, 1, NULL, 62, NULL, '2019-03-07 16:56:15', '刘小平', '2019-03-08 14:55:18', '刘小平', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES (1, '超级管理员', '拥有全部菜单', 1, '2018-06-27 15:01:06', '系统用户', '2018-11-14 14:23:03', '刘小平', 0);
INSERT INTO `base_role` VALUES (2, '运营人员', '除系统级别配置菜单外', 1, '2018-06-28 09:43:02', '系统用户', '2018-06-28 09:43:02', '系统用户', 0);
INSERT INTO `base_role` VALUES (3, '注册用户', '自助注册用户拥有的有限菜单', 1, '2018-06-28 09:43:33', '系统用户', '2018-06-28 09:43:33', '系统用户', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_role_menu_rel
-- ----------------------------
INSERT INTO `base_role_menu_rel` VALUES (1, 1, 1, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (2, 1, 2, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (3, 1, 3, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (4, 1, 4, '2018-06-28 15:37:11', '系统用户', '2018-06-28 15:37:11', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (5, 1, 5, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (6, 1, 6, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (7, 1, 7, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (8, 1, 8, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (9, 1, 9, '2018-06-28 15:37:57', '系统用户', '2018-06-28 15:37:57', '系统用户', 0);
INSERT INTO `base_role_menu_rel` VALUES (74, 1, 45, '2018-12-12 15:09:37', '刘小平', '2018-12-12 15:09:37', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (75, 1, 46, '2018-12-12 15:09:37', '刘小平', '2018-12-12 15:09:37', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (76, 1, 47, '2018-12-12 15:09:37', '刘小平', '2018-12-12 15:09:37', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (107, 1, 62, '2019-03-07 17:06:05', '刘小平', '2019-03-07 17:06:05', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (108, 1, 63, '2019-03-07 17:06:05', '刘小平', '2019-03-07 17:06:05', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (128, 2, 5, '2020-01-30 12:08:09', '刘小平', '2020-01-30 12:08:09', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (129, 2, 6, '2020-01-30 12:08:09', '刘小平', '2020-01-30 12:08:09', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (130, 2, 7, '2020-01-30 12:08:09', '刘小平', '2020-01-30 12:08:09', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (131, 2, 8, '2020-01-30 12:08:09', '刘小平', '2020-02-04 11:38:42', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (132, 2, 9, '2020-01-30 12:08:09', '刘小平', '2020-01-31 13:46:30', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (133, 2, 45, '2020-01-30 12:08:09', '刘小平', '2020-01-31 13:46:30', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (134, 2, 46, '2020-01-30 12:08:09', '刘小平', '2020-01-31 13:46:30', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (135, 2, 47, '2020-01-30 12:08:09', '刘小平', '2020-01-31 13:46:30', '刘小平', 1);
INSERT INTO `base_role_menu_rel` VALUES (140, 2, 62, '2020-01-31 15:18:56', '刘小平', '2020-01-31 15:18:56', '刘小平', 0);
INSERT INTO `base_role_menu_rel` VALUES (141, 2, 63, '2020-01-31 15:18:56', '刘小平', '2020-01-31 15:18:56', '刘小平', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_role_user_rel
-- ----------------------------
INSERT INTO `base_role_user_rel` VALUES (3, 3, 3, '2018-06-28 09:52:16', '系统用户', '2018-06-28 09:52:16', '系统用户', 0);
INSERT INTO `base_role_user_rel` VALUES (4, 1, 3, '2018-06-28 09:52:57', '系统用户', '2018-06-28 09:52:57', '系统用户', 0);
INSERT INTO `base_role_user_rel` VALUES (7, 2, 3, '2018-06-28 09:57:10', '系统用户', '2018-06-28 09:57:10', '系统用户', 0);
INSERT INTO `base_role_user_rel` VALUES (26, 6, 3, '2019-04-18 17:34:56', '刘小平', '2019-04-18 17:34:56', '刘小平', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES (3, 'liuxp', '刘小平', '$2a$10$wU9uXTBGaa7dqydOKxAjzeN.kKyk8YUvQ.d.KbT4ubGJSkZy.lw1K', 1, '', '', NULL, '', '1986-09-15', NULL, 1, '2018-02-03 20:11:18', 'liuxp', '2020-11-13 14:51:37', '刘小平', 0);

-- ----------------------------
-- Table structure for generator_config_mapping
-- ----------------------------
DROP TABLE IF EXISTS `generator_config_mapping`;
CREATE TABLE `generator_config_mapping`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `db_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库类型',
  `jdbc_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'jdbc类型',
  `java_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'java类型',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据类型映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of generator_config_mapping
-- ----------------------------
INSERT INTO `generator_config_mapping` VALUES (1, 'bit', 'bit', 'boolean', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (2, 'blob', 'blob', 'byte[]', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 18:32:03', '刘小平', 0);
INSERT INTO `generator_config_mapping` VALUES (3, 'bigint', 'bigint', 'Long', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (4, 'char', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (5, 'date', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (6, 'datetime', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (7, 'decimal', 'decimal', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (8, 'double', 'double', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (9, 'float', 'float', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (10, 'int', 'integer', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (11, 'smallint', 'integer', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (12, 'numeric', 'bigint', 'BigDecimal', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (13, 'timestamp', 'timestamp', 'Date', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (14, 'tinyint', 'tinyint', 'Integer', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (15, 'varchar', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);
INSERT INTO `generator_config_mapping` VALUES (16, 'text', 'varchar', 'String', '2018-12-26 17:37:39', 'SYSTEM', '2018-12-26 17:37:42', 'SYSTEM', 0);

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
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成器模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of generator_config_template
-- ----------------------------
INSERT INTO `generator_config_template` VALUES (1, 'sqlMapperTemplate', 'SqlMapper模板', '<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n	\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<!-- $!{tableNameCH}SQL author by $!{author} -->\n<mapper namespace=\"$!{packagePath}.dao.$!{tablePrefixLowerCase}.$!{tableClassNameEN}Dao\" >\n    <!-- 映射关系 -->\n    <resultMap id=\"resultMap\" type=\"$!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN}\" >\n        <id column=\"$!{pkColumn.columnNameEN}\" property=\"$!{pkColumn.columnPropertyName}\" jdbcType=\"$!{pkColumn.jdbcType}\" />       <!-- $!{pkColumn.columnNameCH} -->\n#foreach(${column} in ${columns})\n        <result column=\"$!{column.columnNameEN}\" property=\"$!{column.columnPropertyName}\" jdbcType=\"$!{column.jdbcType}\" />       <!-- $!{column.columnNameCH} -->\n#end\n#foreach(${column} in ${extFlagColumns})\n        <result column=\"$!{column.columnNameEN}\" property=\"$!{column.columnPropertyName}\" jdbcType=\"$!{column.jdbcType}\" />       <!-- $!{column.columnNameCH} -->\n#end\n        <result column=\"$!{effectiveFlagColumn.columnNameEN}\" property=\"$!{effectiveFlagColumn.columnPropertyName}\" jdbcType=\"$!{effectiveFlagColumn.jdbcType}\" />       <!-- $!{effectiveFlagColumn.columnNameCH} -->\n    </resultMap>\n\n    <!-- 查询列 -->\n    <sql id=\"queryColumns\">\n        $!{pkColumn.columnNameEN},       /* $!{pkColumn.columnNameCH} */\n#foreach(${column} in ${columns})\n        $!{column.columnNameEN},       /* $!{column.columnNameCH} */\n#end\n#foreach(${column} in ${extFlagColumns})\n        $!{column.columnNameEN},       /* $!{column.columnNameCH} */\n#end\n        $!{effectiveFlagColumn.columnNameEN}       /* $!{effectiveFlagColumn.columnNameCH} */\n    </sql>\n\n    <!-- 查询条件 -->\n    <sql id=\"queryClause\">\n        <where>\n            $!{effectiveFlagColumn.columnNameEN} = 0    /* $!{effectiveFlagColumn.columnNameCH} */\n            <if test=\"$!{pkColumn.columnPropertyName} != null\">\n                AND $!{pkColumn.columnNameEN} = #{$!{pkColumn.columnPropertyName}, jdbcType=BIGINT}	/* $!{pkColumn.columnNameCH} */\n            </if>\n            <if test=\"$!{pkColumn.columnPropertyName}Min != null\">\n                <![CDATA[ AND $!{pkColumn.columnNameEN} > #{$!{pkColumn.columnPropertyName}Min, jdbcType=BIGINT} ]]>	/* $!{pkColumn.columnNameCH}大于某值*/\n            </if>\n            <if test=\"$!{pkColumn.columnPropertyName}Max != null\">\n                <![CDATA[ AND $!{pkColumn.columnNameEN} < #{$!{pkColumn.columnPropertyName}Max, jdbcType=BIGINT} ]]>	/* $!{pkColumn.columnNameCH}小于某值 */\n            </if>\n#foreach(${column} in ${columns})\n#if($!{column.javaType}==\"String\")\n#if($!{column.likeFlag} == true)\n            <if test=\"$!{column.columnPropertyName} != null and $!{column.columnPropertyName} != \'\'\" >\n                AND $!{column.columnNameEN} = #{$!{column.columnPropertyName},jdbcType=$!{column.jdbcType}}       /* $!{column.columnNameCH} */\n            </if>\n            <if test=\"$!{column.columnPropertyName}Like != null and $!{column.columnPropertyName}Like != \'\'\" >\n                AND $!{column.columnNameEN} LIKE concat(\'%\', #{$!{column.columnPropertyName}Like,jdbcType=$!{column.jdbcType}},\'%\')       /* $!{column.columnNameCH} */\n            </if>\n#else\n            <if test=\"$!{column.columnPropertyName} != null and $!{column.columnPropertyName} != \'\'\" >\n                AND $!{column.columnNameEN} = #{$!{column.columnPropertyName},jdbcType=$!{column.jdbcType}}       /* $!{column.columnNameCH} */\n            </if>\n#end\n#elseif($!{column.javaType}==\"Date\")\n            <if test=\"$!{column.columnPropertyName}Begin != null\" >\n                <![CDATA[ AND $!{column.columnNameEN} >= #{$!{column.columnPropertyName}Begin,jdbcType=$!{column.jdbcType}} ]]>       /* $!{column.columnNameCH}/起 */\n            </if>\n            <if test=\"$!{column.columnPropertyName}End != null\" >\n                <![CDATA[ AND $!{column.columnNameEN} <= #{$!{column.columnPropertyName}End,jdbcType=$!{column.jdbcType}} ]]>       /* $!{column.columnNameCH}/止 */\n            </if>\n#else\n            <if test=\"$!{column.columnPropertyName} != null\" >\n                AND $!{column.columnNameEN} = #{$!{column.columnPropertyName},jdbcType=$!{column.jdbcType}}       /* $!{column.columnNameCH} */\n            </if>\n#end\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.javaType}==\"String\")\n            <if test=\"$!{column.columnPropertyName} != null and $!{column.columnPropertyName} != \'\'\" >\n                AND $!{column.columnNameEN} = #{$!{column.columnPropertyName},jdbcType=$!{column.jdbcType}}       /* $!{column.columnNameCH} */\n            </if>\n#elseif($!{column.javaType}==\"Date\")\n            <if test=\"$!{column.columnPropertyName}Begin != null\" >\n                <![CDATA[ AND $!{column.columnNameEN} >= #{$!{column.columnPropertyName}Begin,jdbcType=$!{column.jdbcType}} ]]>       /* $!{column.columnNameCH}/起 */\n            </if>\n            <if test=\"$!{column.columnPropertyName}End != null\" >\n                <![CDATA[ AND $!{column.columnNameEN} <= #{$!{column.columnPropertyName}End,jdbcType=$!{column.jdbcType}} ]]>       /* $!{column.columnNameCH}/止 */\n            </if>\n#else\n            <if test=\"$!{column.columnPropertyName} != null\" >\n                AND $!{column.columnNameEN} = #{$!{column.columnPropertyName},jdbcType=$!{column.jdbcType}}       /* $!{column.columnNameCH} */\n            </if>\n#end\n#end\n        </where>\n    </sql>\n\n    <!-- 排序 -->\n    <sql id=\"sortClause\">\n        <if test=\"_sort != null\">\n            ORDER BY\n            <foreach collection=\"_sort\" item=\"item\" index=\"index\" separator=\",\">\n                <choose>\n                    <when test=\"item.propertyName != null and item.propertyName == \'$!{pkColumn.columnPropertyName}\'\">\n                        $!{pkColumn.columnNameEN}    /* $!{pkColumn.columnNameCH} */\n                        <choose>\n                            <when test=\"item.ascending\">ASC</when>\n                            <otherwise>DESC</otherwise>\n                        </choose>\n                    </when>\n#foreach(${column} in ${columns})\n                    <when test=\"item.propertyName != null and item.propertyName == \'$!{column.columnPropertyName}\'\">\n                        $!{column.columnNameEN}    /* $!{column.columnNameCH} */\n                        <choose>\n                            <when test=\"item.ascending\">ASC</when>\n                            <otherwise>DESC</otherwise>\n                        </choose>\n                    </when>\n#end\n#foreach(${column} in ${extFlagColumns})\n                    <when test=\"item.propertyName != null and item.propertyName == \'$!{column.columnPropertyName}\'\">\n                        $!{column.columnNameEN}    /* $!{column.columnNameCH} */\n                        <choose>\n                            <when test=\"item.ascending\">ASC</when>\n                            <otherwise>DESC</otherwise>\n                        </choose>\n                    </when>\n#end\n                    <otherwise>update_time DESC</otherwise>\n                </choose>\n            </foreach>\n        </if>\n    </sql>\n\n    <!-- 分页 -->\n    <sql id=\"pageClause\">\n        <if test=\"_page != null\">\n            limit #{_page.offset, jdbcType=INTEGER}, #{_page.limit, jdbcType=INTEGER}\n        </if>\n    </sql>\n\n    <!-- 插入数据 -->\n    <insert id=\"insert\" parameterType=\"$!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN}\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n        INSERT INTO $!{tablePrefix}$!{tableNameEN} (\n#foreach(${column} in ${columns})\n            $!{column.columnNameEN},                      /* $!{column.columnNameCH} */\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.columnNameEN}!=\"ts\")\n            $!{column.columnNameEN},                      /* $!{column.columnNameCH} */\n#end\n#end\n            $!{effectiveFlagColumn.columnNameEN}       /* $!{effectiveFlagColumn.columnNameCH} */\n        )VALUES(\n#foreach(${column} in ${columns})\n            #{$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},                 /* $!{column.columnNameCH} */\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.columnNameEN}!=\"ts\")\n            #{$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},                 /* $!{column.columnNameCH} */\n#end\n#end\n            #{$!{effectiveFlagColumn.columnPropertyName}, jdbcType=$!{effectiveFlagColumn.jdbcType}}                 /* $!{effectiveFlagColumn.columnNameCH} */\n        )\n    </insert>\n\n    <!-- 批量插入数据 -->\n    <insert id=\"batchInsert\" parameterType=\"java.util.List\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n        INSERT INTO $!{tablePrefix}$!{tableNameEN} (\n#foreach(${column} in ${columns})\n            $!{column.columnNameEN},                      /* $!{column.columnNameCH} */\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.columnNameEN}!=\"ts\")\n            $!{column.columnNameEN},                      /* $!{column.columnNameCH} */\n#end\n#end\n            $!{effectiveFlagColumn.columnNameEN}       /* $!{effectiveFlagColumn.columnNameCH} */\n        )VALUES\n        <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n            (\n#foreach(${column} in ${columns})\n                #{item.$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},                 /* $!{column.columnNameCH} */\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.columnNameEN}!=\"ts\")\n                #{item.$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},                 /* $!{column.columnNameCH} */\n#end\n#end\n                #{item.$!{effectiveFlagColumn.columnPropertyName}, jdbcType=$!{effectiveFlagColumn.jdbcType}}                 /* $!{effectiveFlagColumn.columnNameCH} */\n            )\n        </foreach>\n    </insert>\n\n    <!-- 按主键更新 -->\n    <update id=\"updateByPrimaryKey\" parameterType=\"java.util.Map\">\n        <if test=\"_parameter != null\">\n            UPDATE $!{tablePrefix}$!{tableNameEN}\n            <set>\n#foreach(${column} in ${columns})\n                <if test=\"$!{column.columnPropertyName} != null\">\n                    $!{column.columnNameEN} = #{$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},    /* $!{column.columnNameCH} */\n                </if>\n#end\n#foreach(${column} in ${extFlagColumns})\n#if($!{column.columnNameEN}!=\"ts\")\n                <if test=\"$!{column.columnPropertyName} != null\">\n                    $!{column.columnNameEN} = #{$!{column.columnPropertyName}, jdbcType=$!{column.jdbcType}},    /* $!{column.columnNameCH} */\n                </if>\n#end\n#end\n                    $!{effectiveFlagColumn.columnNameEN} = 0       /* $!{effectiveFlagColumn.columnNameCH} */\n            </set>\n            <where>\n                $!{pkColumn.columnNameEN} = #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}	/* $!{pkColumn.columnNameCH} */\n                AND $!{effectiveFlagColumn.columnNameEN} = 0		/* $!{effectiveFlagColumn.columnNameCH} */\n            </where>\n        </if>\n    </update>\n\n    <!-- 按主键删除 -->\n    <update id=\"deleteByPrimaryKey\" parameterType=\"$!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN}\">\n        <if test=\"_parameter != null\">\n            UPDATE $!{tablePrefix}$!{tableNameEN}\n            <set>\n                $!{effectiveFlagColumn.columnNameEN} = 1,    /* $!{effectiveFlagColumn.columnNameCH} */\n                update_user = #{updateUser, jdbcType=VARCHAR},	/* 修改人 */\n                update_time = #{updateTime, jdbcType=TIMESTAMP}	/* 修改时间 */\n            </set>\n            <where>\n                $!{pkColumn.columnNameEN} = #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}	/* $!{pkColumn.columnNameCH} */\n                AND $!{effectiveFlagColumn.columnNameEN} = 0		/* $!{effectiveFlagColumn.columnNameCH} */\n            </where>\n        </if>\n    </update>\n\n    <!-- 按主键批量删除 -->\n    <update id=\"batchDeleteByPrimaryKey\" parameterType=\"java.util.Map\">\n        <if test=\"_parameter != null\">\n            UPDATE $!{tablePrefix}$!{tableNameEN}\n            <set>\n                $!{effectiveFlagColumn.columnNameEN} = 1,    /* $!{effectiveFlagColumn.columnNameCH} */\n                update_user = #{updateUser, jdbcType=VARCHAR},	/* 修改人 */\n                update_time = #{updateTime, jdbcType=TIMESTAMP}	/* 修改时间 */\n            </set>\n            <where>\n                $!{pkColumn.columnNameEN} in    /* $!{pkColumn.columnNameCH}集合 */\n                <foreach collection=\"ids\" item=\"$!{pkColumn.columnPropertyName}\" open=\"(\" separator=\",\" close=\")\">\n                    #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}\n                </foreach>\n                AND $!{effectiveFlagColumn.columnNameEN} = 0    /* $!{effectiveFlagColumn.columnNameCH} */\n            </where>\n        </if>\n    </update>\n\n    <!-- 按主键批量物理删除，慎用 -->\n    <delete id=\"batchDeleteByPrimaryKeyPhysically\" parameterType=\"java.util.List\">\n        <if test=\"_parameter != null\">\n            DELETE FROM $!{tablePrefix}$!{tableNameEN}\n            <where>\n                $!{pkColumn.columnNameEN} in	/* $!{pkColumn.columnNameCH}集合 */\n                <foreach collection=\"list\" item=\"$!{pkColumn.columnPropertyName}\" open=\"(\" separator=\",\" close=\")\">\n                    #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}\n                </foreach>\n            </where>\n        </if>\n    </delete>\n\n    <!-- 查询所有记录 -->\n    <select id=\"selectAll\" resultMap=\"resultMap\">\n        SELECT\n        <include refid=\"queryColumns\"/>\n        FROM\n        $!{tablePrefix}$!{tableNameEN}\n        <where>\n            $!{effectiveFlagColumn.columnNameEN} = 0    /* $!{effectiveFlagColumn.columnNameCH} */\n        </where>\n    </select>\n\n    <!-- 按主键查询 -->\n    <select id=\"selectByPrimaryKey\" resultMap=\"resultMap\" parameterType=\"java.lang.Long\">\n        SELECT\n        <include refid=\"queryColumns\"/>\n        FROM\n        $!{tablePrefix}$!{tableNameEN}\n        <where>\n            $!{pkColumn.columnNameEN} = #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}	/* $!{pkColumn.columnNameCH} */\n            AND $!{effectiveFlagColumn.columnNameEN} = 0			/* $!{effectiveFlagColumn.columnNameCH} */\n        </where>\n    </select>\n\n    <!-- 按主键集合查询 -->\n    <select id=\"selectByPrimaryKeys\" resultMap=\"resultMap\" parameterType=\"java.util.List\">\n        SELECT\n        <include refid=\"queryColumns\"/>\n        FROM\n        $!{tablePrefix}$!{tableNameEN}\n        <where>\n            $!{pkColumn.columnNameEN} in	/* $!{pkColumn.columnNameCH}集合 */\n            <foreach collection=\"list\" item=\"$!{pkColumn.columnPropertyName}\" open=\"(\" separator=\",\" close=\")\">\n                #{$!{pkColumn.columnPropertyName}, jdbcType=$!{pkColumn.jdbcType}}\n            </foreach>\n            AND $!{effectiveFlagColumn.columnNameEN} = 0	/* $!{effectiveFlagColumn.columnNameCH} */\n        </where>\n    </select>\n\n    <!-- 按条件查询 -->\n    <select id=\"selectByCondition\" resultMap=\"resultMap\" parameterType=\"java.util.Map\">\n        SELECT\n        <include refid=\"queryColumns\"/>\n        FROM\n        $!{tablePrefix}$!{tableNameEN}\n        <include refid=\"queryClause\"/>\n        <include refid=\"sortClause\"/>\n        <include refid=\"pageClause\"/>\n    </select>\n\n    <!-- 按条件统计记录数 -->\n    <select id=\"countByCondition\" resultType=\"java.lang.Long\" parameterType=\"java.util.Map\">\n        SELECT\n        COUNT(1)\n        FROM\n        $!{tablePrefix}$!{tableNameEN}\n        <include refid=\"queryClause\"/>\n    </select>\n\n</mapper>', 'mappers.mysql', 'Mapper.xml', '2018-12-27 10:56:12', '刘小平', '2020-05-08 17:16:08', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (2, 'daoTemplate', 'Dao层服务接口类模板', 'package $!{packagePath}.dao.$!{tablePrefixLowerCase};\n\nimport $!{packagePath}.common.dao.IDao;\n\n/**\n * $!{tableNameCH}Dao层接口类\n * User: $!{author}\n * DateTime: $!{dateTime}\n **/\npublic interface $!{tableClassNameEN}Dao extends IDao{\n\n}', 'dao', 'Dao.java', '2018-12-27 14:07:05', '刘小平', '2020-05-08 17:16:50', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (3, 'daoImplTemplate', 'Dao层服务实现类模板', 'package $!{packagePath}.dao.$!{tablePrefixLowerCase}.impl;\n\nimport $!{packagePath}.dao.$!{tablePrefixLowerCase}.$!{tableClassNameEN}Dao;\nimport $!{packagePath}.common.dao.AbstractDao;\nimport org.springframework.stereotype.Repository;\n\n/**\n * $!{tableNameCH}Dao层实现类\n * User: $!{author}\n * DateTime: $!{dateTime}\n **/\n@Repository\npublic class $!{tableClassNameEN}DaoImpl extends AbstractDao implements $!{tableClassNameEN}Dao {\n\n}', 'dao.impl', 'DaoImpl.java', '2018-12-27 14:07:50', '刘小平', '2020-05-08 17:19:03', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (4, 'serviceTemplate', 'Service层服务接口类模板', 'package $!{packagePath}.service.$!{tablePrefixLowerCase};\n\nimport $!{packagePath}.common.service.IService;\n\n/**\n * $!{tableNameCH}Service层接口类\n * User: $!{author}\n * DateTime: $!{dateTime}\n **/\npublic interface $!{tableClassNameEN}Service extends IService{\n\n}', 'service', 'Service.java', '2018-12-27 14:08:22', '刘小平', '2020-05-08 17:19:22', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (5, 'serviceImplTemplate', 'Service层服务实现类模板', 'package $!{packagePath}.service.$!{tablePrefixLowerCase}.impl;\n\nimport $!{packagePath}.common.service.AbstractService;\nimport $!{packagePath}.dao.$!{tablePrefixLowerCase}.$!{tableClassNameEN}Dao;\nimport $!{packagePath}.service.$!{tablePrefixLowerCase}.$!{tableClassNameEN}Service;\n\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.stereotype.Service;\n\n/**\n * $!{tableNameCH}Service层实现类\n * User: $!{author}\n * DateTime: $!{dateTime}\n **/\n@Service\npublic class $!{tableClassNameEN}ServiceImpl extends AbstractService implements $!{tableClassNameEN}Service {\n\n    @Autowired\n    public void setDao($!{tableClassNameEN}Dao dao) {this.iDao = dao;}\n\n}', 'service.impl', 'ServiceImpl.java', '2018-12-27 14:08:43', '刘小平', '2020-05-08 17:19:56', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (6, 'domainTemplate', '实体类模板', 'package $!{packagePath}.domain.model.$!{tablePrefixLowerCase};\n\nimport com.fasterxml.jackson.annotation.JsonFormat;\nimport $!{packagePath}.domain.Transient;\n\nimport $!{packagePath}.domain.model.AbstractEntity;\n\nimport java.math.BigDecimal;\nimport java.util.Date;\n\n/**\n * $!{tableNameCH}Domain\n * User: $!{author}\n * Date: $!{dateTime}\n **/\npublic class $!{tableClassNameEN} extends AbstractEntity{\n\n#foreach(${column} in ${columns})\n#if($!{column.javaType}  == \"Date\")\n    /**\n     * $!{column.columnNameCH}\n     */\n    @JsonFormat(timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\n    private $!{column.javaType} $!{column.columnPropertyName};\n    /**\n     * $!{column.columnNameCH}/起(虚拟字段：用于时间段查询)\n     */\n    @Transient\n    private String $!{column.columnPropertyName}Begin;\n    /**\n     * $!{column.columnNameCH}/止(虚拟字段：用于时间段查询)\n     */\n    @Transient\n    private String $!{column.columnPropertyName}End;\n#elseif($!{column.javaType}==\"String\" and $!{column.likeFlag} == true)\n    /**\n     * $!{column.columnNameCH}\n     */\n    private $!{column.javaType} $!{column.columnPropertyName};\n    /**\n     * $!{column.columnNameCH}(虚拟字段：用于模糊查询)\n     */\n    @Transient\n    private $!{column.javaType} $!{column.columnPropertyName}Like;\n#else\n    /**\n     * $!{column.columnNameCH}\n     */\n    private $!{column.javaType} $!{column.columnPropertyName};\n#end\n#end\n\n#foreach(${column} in ${columns})\n#if($!{column.javaType}  == \"Date\")\n    /**\n     * 获取$!{column.columnNameCH}\n     * @return $!{column.columnNameCH}\n     */\n    public $!{column.javaType} get$!{column.columnMethodName}() {\n        return $!{column.columnPropertyName};\n    }\n\n    /**\n     * 设置$!{column.columnNameCH}\n     * @param $!{column.columnPropertyName} $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}($!{column.javaType} $!{column.columnPropertyName}) {\n        this.$!{column.columnPropertyName} = $!{column.columnPropertyName};\n    }\n\n    /**\n     * 获取$!{column.columnNameCH}/起(虚拟字段：用于时间段查询)\n     * @return $!{column.columnNameCH}\n     */\n    public String get$!{column.columnMethodName}Begin() {\n        return $!{column.columnPropertyName}Begin;\n    }\n\n    /**\n     * 设置$!{column.columnNameCH}\n     * @param $!{column.columnPropertyName}Begin $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}Begin(String $!{column.columnPropertyName}Begin) {\n        this.$!{column.columnPropertyName}Begin = $!{column.columnPropertyName}Begin;\n    }\n\n    /**\n     * 获取$!{column.columnNameCH}/止(虚拟字段：用于时间段查询)\n     * @return $!{column.columnNameCH}\n     */\n    public String get$!{column.columnMethodName}End() {\n        return $!{column.columnPropertyName}End;\n    }\n\n    /**\n     * 设置$!{column.columnNameCH}\n     * @param $!{column.columnPropertyName}End $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}End(String $!{column.columnPropertyName}End) {\n        this.$!{column.columnPropertyName}End = $!{column.columnPropertyName}End;\n    }\n\n#elseif($!{column.javaType}==\"String\" and $!{column.likeFlag} == true)\n    /**\n     * 获取$!{column.columnNameCH}\n     * @return $!{column.columnNameCH}\n     */\n    public $!{column.javaType} get$!{column.columnMethodName}() {\n        return $!{column.columnPropertyName};\n    }\n    /**\n     * 设置$!{column.columnNameCH}\n     * @param $!{column.columnPropertyName} $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}($!{column.javaType} $!{column.columnPropertyName}) {\n        this.$!{column.columnPropertyName} = $!{column.columnPropertyName};\n    }\n    /**\n     * 获取$!{column.columnNameCH}(虚拟字段：用于模糊查询)\n     * @return $!{column.columnNameCH}\n     */\n    public $!{column.javaType} get$!{column.columnMethodName}Like() {\n        return $!{column.columnPropertyName}Like;\n    }\n    /**\n     * 设置$!{column.columnNameCH}(虚拟字段：用于模糊查询)\n     * @param $!{column.columnPropertyName}Like $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}Like($!{column.javaType} $!{column.columnPropertyName}Like) {\n        this.$!{column.columnPropertyName}Like = $!{column.columnPropertyName}Like;\n    }\n\n#else\n    /**\n     * 获取$!{column.columnNameCH}\n     * @return $!{column.columnNameCH}\n     */\n    public $!{column.javaType} get$!{column.columnMethodName}() {\n        return $!{column.columnPropertyName};\n    }\n\n    /**\n     * 设置$!{column.columnNameCH}\n     * @param $!{column.columnPropertyName} $!{column.columnNameCH}\n     */\n    public void set$!{column.columnMethodName}($!{column.javaType} $!{column.columnPropertyName}) {\n        this.$!{column.columnPropertyName} = $!{column.columnPropertyName};\n    }\n#end\n#end\n}', 'domain', '.java', '2018-12-27 14:09:11', '刘小平', '2020-05-08 17:30:33', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (7, 'controllerTemplate', 'controller模板', 'package $!{packagePath}.controller.$!{tablePrefixLowerCase};\n\nimport $!{packagePath}.controller.BaseController;\nimport $!{packagePath}.domain.model.Page;\nimport $!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN};\nimport org.springframework.stereotype.Controller;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RequestMethod;\nimport org.springframework.web.bind.annotation.RequestParam;\nimport org.springframework.web.bind.annotation.ResponseBody;\nimport $!{packagePath}.service.$!{tablePrefixLowerCase}.$!{tableClassNameEN}Service;\n\nimport javax.annotation.Resource;\nimport java.util.List;\n\n/**\n * $!{tableNameCH}Controller\n * User: $!{author}\n * DateTime: $!{dateTime}\n */\n@Controller\n@RequestMapping(\"/web$!{htmlPath}\")\npublic class $!{tableClassNameEN}Controller extends BaseController{\n\n    /**\n     * $!{tableNameCH}Service接口\n     */\n    @Resource\n    private $!{tableClassNameEN}Service $!{tablePropertyNameEN}Service;\n\n    /**\n     * 根据条件分页查询\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\n     * @param pageNum 当前页码\n     * @param pageSize 每页显示条数\n     * @param sortName 排序字段\n     * @param sortOrder 排序方式\n     * @return Page<$!{tableClassNameEN}> 分页列表\n     */\n    @RequestMapping(value = \"/selectPage\", method = RequestMethod.POST)\n    @ResponseBody\n    public Page<$!{tableClassNameEN}> selectPage($!{tableClassNameEN} $!{tablePropertyNameEN},\n            @RequestParam(value = \"page\", required = false, defaultValue = \"1\") int pageNum,\n            @RequestParam(value = \"rows\", required = false, defaultValue = \"20\") int pageSize,\n            @RequestParam(value = \"sidx\", required = false, defaultValue = \"ts\") String sortName,\n            @RequestParam(value = \"sord\", required = false, defaultValue = \"desc\") String sortOrder) {\n\n        Page<$!{tableClassNameEN}> page = buildPage($!{tablePropertyNameEN}, pageNum,pageSize,sortName,sortOrder);\n        // 执行查询\n        return $!{tablePropertyNameEN}Service.selectPage(page);\n    }\n\n    /**\n     * 根据条件查询\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\n     * @return $!{tableNameCH}列表\n    */\n    @RequestMapping(value = \"/selectList\", method = RequestMethod.POST)\n    @ResponseBody\n    public List<$!{tableClassNameEN}> selectList($!{tableClassNameEN} $!{tablePropertyNameEN}) {\n\n        return $!{tablePropertyNameEN}Service.selectList($!{tablePropertyNameEN});\n    }\n\n    /**\n     * 根据主键查询\n     * @param $!{pkColumn.columnPropertyName} $!{pkColumn.columnNameCH}\n     * @return $!{tableNameCH}\n    */\n    @RequestMapping(value = \"/selectOne\", method = RequestMethod.POST)\n    @ResponseBody\n    public $!{tableClassNameEN} selectOne($!{pkColumn.javaType} $!{pkColumn.columnPropertyName}) {\n\n        return $!{tablePropertyNameEN}Service.selectOne($!{pkColumn.columnPropertyName});\n    }\n\n    /**\n     * 保存数据\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\n     * @return 执行结果\n     */\n    @RequestMapping(value = \"/insert\", method = RequestMethod.POST)\n    @ResponseBody\n    public $!{tableClassNameEN} insert($!{tableClassNameEN} $!{tablePropertyNameEN}) {\n\n        // 执行插入，返回已设置主键的数据实体\n        return $!{tablePropertyNameEN}Service.insert($!{tablePropertyNameEN},getAccount().getUserName());\n    }\n\n    /**\n     * 修改数据\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\n     * @return 执行结果\n     */\n    @RequestMapping(value = \"/update\", method = RequestMethod.POST)\n    @ResponseBody\n    public Boolean update($!{tableClassNameEN} $!{tablePropertyNameEN}) {\n        // 执行更新\n        int rows = $!{tablePropertyNameEN}Service.update($!{tablePropertyNameEN},getAccount().getUserName());\n				return rows > 0;\n    }\n\n    /**\n     * 逻辑删除数据\n     * @param $!{pkColumn.columnPropertyName} $!{pkColumn.columnNameCH}\n     * @return 执行结果\n     */\n    @RequestMapping(value = \"/delete\", method = RequestMethod.POST)\n    @ResponseBody\n    public Boolean delete($!{pkColumn.javaType} $!{pkColumn.columnPropertyName}) {\n        // 执行逻辑删除\n        int rows = $!{tablePropertyNameEN}Service.delete($!{pkColumn.columnPropertyName},getAccount().getUserName());\n				return rows > 0;\n    }\n\n}', 'controller', 'Controller.java', '2018-12-27 14:09:33', '刘小平', '2020-05-08 17:38:26', '刘小平', 0);
INSERT INTO `generator_config_template` VALUES (8, 'restControllerTemplate', 'restController模板', 'package $!{packagePath}.$!{tablePrefixLowerCase}.controller;\r\n\r\nimport $!{packagePath}.api.$!{tablePrefixLowerCase}.$!{tableClassNameEN}RemoteService;\r\nimport $!{packagePath}.$!{tablePrefixLowerCase}.service.$!{tableClassNameEN}Service;\r\nimport $!{packagePath}.common.controller.BaseRestController;\r\nimport $!{packagePath}.domain.model.Page;\r\nimport $!{packagePath}.domain.model.Result;\r\nimport $!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN};\r\nimport org.springframework.web.bind.annotation.*;\r\n\r\nimport javax.annotation.Resource;\r\nimport java.util.List;\r\n\r\n/**\r\n * $!{tableNameCH}RestController\r\n * @author $!{author}\r\n * DateTime: $!{dateTime}\r\n */\r\n@RestController\r\npublic class $!{tableClassNameEN}RestController extends BaseRestController implements $!{tableClassNameEN}RemoteService {\r\n\r\n    // $!{tableNameCH}Service\r\n    @Resource\r\n    private $!{tableClassNameEN}Service $!{tablePropertyNameEN}Service;\r\n\r\n    /**\r\n     * 分页查询\r\n     * @param page 分页与查询条件对象\r\n     * @return Result<Page<$!{tableClassNameEN}>> 结果集\r\n     */\r\n    public Result<Page<$!{tableClassNameEN}>> selectPage(@RequestBody Page<$!{tableClassNameEN}> page) {\r\n        // 执行查询\r\n        page = $!{tablePropertyNameEN}Service.selectPage(page);\r\n        // 返回查询结果\r\n        return new Result<>(page);\r\n    }\r\n\r\n    /**\r\n     * 列表查询\r\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n     * @return Result<List<$!{tableClassNameEN}>> 结果集\r\n     */\r\n    public Result<List<$!{tableClassNameEN}>> selectList(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN}) {\r\n        // 执行查询\r\n        List<$!{tableClassNameEN}> content = $!{tablePropertyNameEN}Service.selectList($!{tablePropertyNameEN});\r\n        // 返回结果\r\n        return new Result<>(content);\r\n    }\r\n\r\n    /**\r\n     * 单条查询\r\n     * @param id 主键\r\n     * @return Result<$!{tableClassNameEN}> 数据实体\r\n     */\r\n    public Result<$!{tableClassNameEN}> selectOne(Long id) {\r\n        // 执行查询\r\n        $!{tableClassNameEN} content = $!{tablePropertyNameEN}Service.selectOne(id);\r\n        // 返回结果\r\n        return new Result<>(content);\r\n    }\r\n\r\n    /**\r\n    * 新增\r\n    * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n    * @return Result<$!{tableClassNameEN}> 已设置主键的数据实体\r\n    */\r\n    public Result<$!{tableClassNameEN}> insert(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN}) {\r\n        // 执行插入\r\n        $!{tablePropertyNameEN} = $!{tablePropertyNameEN}Service.insert($!{tablePropertyNameEN},getAccount().getUserName());\r\n        // 返回已设置主键的数据实体\r\n        return new Result<>($!{tablePropertyNameEN});\r\n    }\r\n\r\n    /**\r\n     * 批量新增\r\n     * @param $!{tablePropertyNameEN}List $!{tableNameCH}数据实体\r\n     * @return Result<List<$!{tableClassNameEN}>> 已设置主键的数据实体结果集\r\n     */\r\n    @Override\r\n    public Result<List<$!{tableClassNameEN}>> batchInsert(@RequestBody List<$!{tableClassNameEN}> $!{tablePropertyNameEN}List) {\r\n        return new Result<>((List<$!{tableClassNameEN}>)$!{tablePropertyNameEN}Service.insert($!{tablePropertyNameEN}List,getAccount().getUserName()));\r\n    }\r\n\r\n    /**\r\n     * 修改\r\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n     * @return Result<Boolean> 是否成功\r\n     */\r\n    public Result<Boolean> update(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN}) {\r\n        // 执行更新\r\n        int rows = $!{tablePropertyNameEN}Service.update($!{tablePropertyNameEN}, getAccount().getUserName());\r\n        if (rows == 1) {\r\n            // 返回\r\n            return new Result<>(true);\r\n        }\r\n        // 返回\r\n        return new Result<>(false);\r\n    }\r\n\r\n    /**\r\n     * 逻辑删除\r\n     * @param id 数据主键\r\n     * @return Result<Boolean> 是否成功\r\n     */\r\n    public Result<Boolean> delete(Long id) {\r\n        // 执行逻辑删除\r\n        int rows = $!{tablePropertyNameEN}Service.delete(id, getAccount().getUserName());\r\n        if (rows == 1) {\r\n            // 返回\r\n            return new Result<>(true);\r\n        }\r\n        // 返回\r\n        return new Result<>(false);\r\n    }\r\n\r\n}', 'controller', 'RestController.java', '2018-12-27 14:10:01', '刘小平', '2020-01-30 14:27:15', '刘小平', 1);
INSERT INTO `generator_config_template` VALUES (9, 'remoteServiceTemplate', 'remoteService层服务接口', 'package $!{packagePath}.api.$!{tablePrefixLowerCase};\r\n\r\nimport org.liuxp.cloud.platform.api.ApiConfig;\r\nimport $!{packagePath}.domain.model.$!{tablePrefixLowerCase}.$!{tableClassNameEN};\r\nimport org.liuxp.cloud.platform.domain.model.Page;\r\nimport org.liuxp.cloud.platform.domain.model.Result;\r\nimport org.springframework.cloud.openfeign.FeignClient;\r\nimport org.springframework.web.bind.annotation.*;\r\n\r\nimport java.util.List;\r\n\r\n/**\r\n * $!{tableNameCH}RemoteService层接口类\r\n * User: $!{author}\r\n * DateTime: $!{dateTime}\r\n **/\r\n@FeignClient(name= ApiConfig.$!{tablePrefixUpperCase}_SERVICE_NAME)\r\n@RequestMapping(\"/api/$!{tablePrefixLowerCase}/$!{tablePropertyNameEN}\")\r\npublic interface $!{tableClassNameEN}RemoteService{\r\n\r\n    /**\r\n     * 分页查询\r\n     * @param page 分页与查询条件对象\r\n     * @return Result<Page<$!{tableClassNameEN}>> 结果集\r\n     */\r\n    @RequestMapping(value = \"/selectPage\", method = RequestMethod.POST)\r\n    Result<Page<$!{tableClassNameEN}>> selectPage(@RequestBody Page<$!{tableClassNameEN}> page);\r\n\r\n    /**\r\n     * 列表查询\r\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n     * @return Result<List<$!{tableClassNameEN}>> 结果集\r\n     */\r\n    @RequestMapping(value = \"/selectList\", method = RequestMethod.POST)\r\n    Result<List<$!{tableClassNameEN}>> selectList(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN});\r\n\r\n    /**\r\n     * 单条查询\r\n     * @param id 主键\r\n     * @return Result<$!{tableClassNameEN}> 数据实体\r\n     */\r\n    @RequestMapping(value = \"/selectOne\", method = RequestMethod.POST)\r\n    Result<$!{tableClassNameEN}> selectOne(@RequestParam(\"id\") Long id);\r\n\r\n    /**\r\n     * 新增数据\r\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n     * @return Result<$!{tableClassNameEN}> 已设置主键的数据实体\r\n     */\r\n    @RequestMapping(value = \"/insert\", method = RequestMethod.POST)\r\n    Result<$!{tableClassNameEN}> insert(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN});\r\n\r\n    /**\r\n     * 批量新增\r\n     * @param $!{tablePropertyNameEN}List $!{tableNameCH}数据实体\r\n     * @return Result<List<$!{tableClassNameEN}>> 已设置主键的数据实体结果集\r\n     */\r\n    @RequestMapping(value = \"/batchInsert\", method = RequestMethod.POST)\r\n    Result<List<$!{tableClassNameEN}>> batchInsert(@RequestBody List<$!{tableClassNameEN}> $!{tablePropertyNameEN}List);\r\n\r\n    /**\r\n     * 修改数据\r\n     * @param $!{tablePropertyNameEN} $!{tableNameCH}数据实体\r\n     * @return Result<Boolean> 是否成功\r\n     */\r\n    @RequestMapping(value = \"/update\", method = RequestMethod.POST)\r\n    Result<Boolean> update(@RequestBody $!{tableClassNameEN} $!{tablePropertyNameEN});\r\n\r\n    /**\r\n     * 逻辑删除数据\r\n     * @param id 数据主键\r\n     * @return Result<Boolean> 是否成功\r\n     */\r\n    @RequestMapping(value = \"/delete\", method = RequestMethod.POST)\r\n    Result<Boolean> delete(@RequestParam(\"id\") Long id);\r\n\r\n}', 'api', 'RemoteService.java', '2018-12-27 14:10:33', '刘小平', '2020-01-30 14:13:28', '刘小平', 1);

-- ----------------------------
-- Table structure for monitor_login_log
-- ----------------------------
DROP TABLE IF EXISTS `monitor_login_log`;
CREATE TABLE `monitor_login_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户唯一标识',
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `login_app` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录应用',
  `login_time` datetime(0) NOT NULL COMMENT '登录时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人名称',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_login_log
-- ----------------------------
INSERT INTO `monitor_login_log` VALUES (1, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-08 13:14:50', '刘小平', '2020-05-08 13:14:50', '刘小平', '2020-05-08 13:14:50', 0);
INSERT INTO `monitor_login_log` VALUES (2, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-08 14:36:18', '刘小平', '2020-05-08 14:36:18', '刘小平', '2020-05-08 14:36:18', 0);
INSERT INTO `monitor_login_log` VALUES (3, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-08 14:41:26', '刘小平', '2020-05-08 14:41:26', '刘小平', '2020-05-08 14:41:26', 0);
INSERT INTO `monitor_login_log` VALUES (4, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-08 16:50:56', '刘小平', '2020-05-08 16:50:56', '刘小平', '2020-05-08 16:50:56', 0);
INSERT INTO `monitor_login_log` VALUES (5, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-08 17:12:22', '刘小平', '2020-05-08 17:12:22', '刘小平', '2020-05-08 17:12:22', 0);
INSERT INTO `monitor_login_log` VALUES (6, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-09 17:38:58', '刘小平', '2020-05-09 17:38:58', '刘小平', '2020-05-09 17:38:58', 0);
INSERT INTO `monitor_login_log` VALUES (7, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-11 11:02:17', '刘小平', '2020-05-11 11:02:17', '刘小平', '2020-05-11 11:02:17', 0);
INSERT INTO `monitor_login_log` VALUES (8, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-11 12:13:28', '刘小平', '2020-05-11 12:13:28', '刘小平', '2020-05-11 12:13:28', 0);
INSERT INTO `monitor_login_log` VALUES (9, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-11 12:20:06', '刘小平', '2020-05-11 12:20:06', '刘小平', '2020-05-11 12:20:06', 0);
INSERT INTO `monitor_login_log` VALUES (10, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-11 16:14:19', '刘小平', '2020-05-11 16:14:19', '刘小平', '2020-05-11 16:14:19', 0);
INSERT INTO `monitor_login_log` VALUES (11, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-11 17:58:04', '刘小平', '2020-05-11 17:58:04', '刘小平', '2020-05-11 17:58:04', 0);
INSERT INTO `monitor_login_log` VALUES (12, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-12 10:43:14', '刘小平', '2020-05-12 10:43:14', '刘小平', '2020-05-12 10:43:14', 0);
INSERT INTO `monitor_login_log` VALUES (13, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-12 17:52:03', '刘小平', '2020-05-12 17:52:03', '刘小平', '2020-05-12 17:52:03', 0);
INSERT INTO `monitor_login_log` VALUES (14, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-13 10:29:51', '刘小平', '2020-05-13 10:29:51', '刘小平', '2020-05-13 10:29:51', 0);
INSERT INTO `monitor_login_log` VALUES (15, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36', '2020-05-22 17:00:20', '刘小平', '2020-05-22 17:00:20', '刘小平', '2020-05-22 17:00:20', 0);
INSERT INTO `monitor_login_log` VALUES (16, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36', '2020-05-27 15:14:10', '刘小平', '2020-05-27 15:14:10', '刘小平', '2020-05-27 15:14:10', 0);
INSERT INTO `monitor_login_log` VALUES (17, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36', '2020-05-27 15:14:29', '刘小平', '2020-05-27 15:14:29', '刘小平', '2020-05-27 15:14:29', 0);
INSERT INTO `monitor_login_log` VALUES (18, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36', '2020-05-27 15:30:15', '刘小平', '2020-05-27 15:30:15', '刘小平', '2020-05-27 15:30:15', 0);
INSERT INTO `monitor_login_log` VALUES (19, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36', '2020-05-28 11:20:08', '刘小平', '2020-05-28 11:20:08', '刘小平', '2020-05-28 11:20:08', 0);
INSERT INTO `monitor_login_log` VALUES (20, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36', '2020-05-28 15:02:53', '刘小平', '2020-05-28 15:02:53', '刘小平', '2020-05-28 15:02:53', 0);
INSERT INTO `monitor_login_log` VALUES (21, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-13 14:44:51', '刘小平', '2020-11-13 14:44:51', '刘小平', '2020-11-13 14:44:51', 0);
INSERT INTO `monitor_login_log` VALUES (22, 3, 'liuxp', '刘小平', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-13 14:51:49', '刘小平', '2020-11-13 14:51:49', '刘小平', '2020-11-13 14:51:49', 0);

-- ----------------------------
-- Table structure for monitor_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `monitor_sms_log`;
CREATE TABLE `monitor_sms_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `sms_send_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `sms_template_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信模板编码',
  `sms_template_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信模板名称',
  `sms_gateway_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信网关类型：netease、aliyun',
  `sms_send_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信内容',
  `sms_send_status` tinyint(1) NOT NULL COMMENT '发送状态 0失败 1成功',
  `sms_send_time` datetime(0) NOT NULL COMMENT '短信发送时间',
  `sms_send_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口返回值',
  `sms_send_exception_text` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送异常信息',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信发送日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_sms_log
-- ----------------------------

-- ----------------------------
-- Table structure for resource_file
-- ----------------------------
DROP TABLE IF EXISTS `resource_file`;
CREATE TABLE `resource_file`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件KEY',
  `file_source_type_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源类型编码',
  `file_source_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源类型名称',
  `file_origin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原文件名',
  `file_current_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '现文件名',
  `file_mime_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MIME类型',
  `file_suffix` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `file_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件位置（bucket）',
  `file_md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_finished` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0:未完成 1:已完成',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新用户',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件存储信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_file
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
