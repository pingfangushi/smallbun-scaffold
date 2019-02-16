/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100307
 Source Host           : localhost:3306
 Source Schema         : smallbun

 Target Server Type    : MySQL
 Target Server Version : 100307
 File Encoding         : 65001

 Date: 16/02/2019 18:58:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(50) NOT NULL COMMENT '主键ID',
  `type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `type_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_dict_type_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1068147088417656833, '性别', 'SEX', 1, '2018-11-29 08:18:06', 1, '2018-11-29 08:18:06', 0, '');
INSERT INTO `sys_dict_type` VALUES (1070172546462183426, '操作类型', 'SYS_OPER_TYPE', 1, '2018-12-04 22:26:33', 1, '2018-12-04 22:26:33', 0, '');
INSERT INTO `sys_dict_type` VALUES (1071771839543394306, '系统数据', 'SYSTEM_DATA', 1, '2018-12-09 08:21:34', 1, '2018-12-09 08:21:34', 0, '系统数据');
INSERT INTO `sys_dict_type` VALUES (1071774715300823041, '角色类型', 'ROLE_TYPE', 1, '2018-12-09 08:32:59', 1, '2018-12-09 08:32:59', 0, '角色类型');
INSERT INTO `sys_dict_type` VALUES (1071775472771153921, '数据范围', 'DATA_SCOPE', 1, '2018-12-09 08:36:00', 1, '2018-12-09 08:36:00', 0, '数据范围');
INSERT INTO `sys_dict_type` VALUES (1073081042031661057, '角色状态', 'ROLE_STATUS', 1, '2018-12-12 23:03:52', 1, '2018-12-12 23:03:52', 0, '');
INSERT INTO `sys_dict_type` VALUES (1073615366678130690, '是否可用', 'WHETHER_USEABLE', 1, '2018-12-14 10:27:05', 1, '2018-12-14 10:27:05', 0, '是否可用');
INSERT INTO `sys_dict_type` VALUES (1073931106488807425, '系统数据', 'SYS_DATA', 1, '2018-12-15 07:21:43', 1, '2018-12-15 07:21:43', 0, '');
INSERT INTO `sys_dict_type` VALUES (1077887529627197441, '机构级别', 'ORG_LEVEL', 1, '2018-12-26 05:23:08', 1, '2018-12-26 05:23:08', 0, '');
INSERT INTO `sys_dict_type` VALUES (1077912772638937089, '机构类型', 'ORG_TYPE', 1, '2018-12-26 07:03:26', 1, '2018-12-26 07:03:26', 0, '');
INSERT INTO `sys_dict_type` VALUES (1085153622473314306, '可见', 'VISIBLE', 1, '2019-01-15 06:35:59', 1, '2019-01-15 06:35:59', 0, '');
INSERT INTO `sys_dict_type` VALUES (1085181767851188225, '菜单类型', 'MENU_TYPE', 1, '2019-01-15 08:27:50', 1, '2019-01-15 08:27:50', 0, '');
INSERT INTO `sys_dict_type` VALUES (1086942198961823745, '菜单状态', 'MENU_STATUS', 1, '2019-01-20 05:03:09', 1, '2019-01-20 05:03:09', 0, '');
INSERT INTO `sys_dict_type` VALUES (1088414522714132482, '用户状态', 'USER_STATUS', 1, '2019-01-24 06:33:39', 1, '2019-01-24 06:33:39', 0, '');
INSERT INTO `sys_dict_type` VALUES (1088418616384159745, '用户类型', 'USER_TYPE', 1, '2019-01-24 06:49:55', 1, '2019-01-24 06:49:55', 0, '');
INSERT INTO `sys_dict_type` VALUES (1096032224433672194, '公告类型', 'NOTIFY_GENRE', 1, '2019-02-14 13:03:40', 1, '2019-02-16 10:27:34', 0, '');
INSERT INTO `sys_dict_type` VALUES (1096335577848008706, '通知状态', 'NOTIFY_STATUS', 1, '2019-02-15 09:09:05', 1, '2019-02-16 10:31:33', 0, '');

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value`  (
  `id` bigint(50) NOT NULL COMMENT '主键',
  `dict_type` bigint(50) NULL DEFAULT NULL COMMENT '所属字典类型(关联sys_dict_type表)',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `sort` int(11) NOT NULL COMMENT '排序',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------
INSERT INTO `sys_dict_value` VALUES (1, 1068147088417656833, '男', '0', 0, 1, '2018-10-03 23:31:19', 1, '2018-10-04 14:02:24', 0, NULL);
INSERT INTO `sys_dict_value` VALUES (1069446852119703554, 1068147088417656833, '女', '1', 1, 1, '2018-12-02 22:22:54', 1, '2018-12-02 22:22:54', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071385137993748482, 1070172546462183426, '新增', '0', 0, 1, '2018-12-08 06:44:57', 1, '2018-12-08 06:44:57', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071385241551114242, 1070172546462183426, '修改', '1', 1, 1, '2018-12-08 06:45:22', 1, '2018-12-08 06:45:22', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071385311839260673, 1070172546462183426, '删除', '2', 2, 1, '2018-12-08 06:45:38', 1, '2018-12-08 06:45:38', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071385424473100290, 1070172546462183426, '导入', '3', 3, 1, '2018-12-08 06:46:05', 1, '2018-12-08 06:46:05', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071774159857532930, 1071771839543394306, '是', '0', 0, 1, '2018-12-09 08:30:47', 1, '2018-12-09 08:30:47', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071774274613690369, 1071771839543394306, '否', '1', 1, 1, '2018-12-09 08:31:14', 1, '2018-12-09 08:31:14', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071775002283491329, 1071774715300823041, '管理角色', '0', 0, 1, '2018-12-09 08:34:08', 1, '2018-12-09 08:34:08', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071775139474980865, 1071774715300823041, '普通角色', '1', 1, 1, '2018-12-09 08:34:41', 1, '2018-12-09 08:34:41', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071775830046162946, 1071775472771153921, '所有数据', '1', 1, 1, '2018-12-09 08:37:25', 1, '2018-12-09 08:37:25', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071775935029592066, 1071775472771153921, '所在公司及以下数据', '2', 2, 1, '2018-12-09 08:37:50', 1, '2018-12-09 08:37:50', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071775991287791618, 1071775472771153921, '所在公司数据', '3', 3, 1, '2018-12-09 08:38:04', 1, '2018-12-09 08:38:04', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071776155150860290, 1071775472771153921, '所在部门及以下数据', '4', 4, 1, '2018-12-09 08:38:43', 1, '2018-12-09 08:38:43', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071776241708711937, 1071775472771153921, '所在部门数据', '5', 5, 1, '2018-12-09 08:39:03', 1, '2018-12-09 08:39:03', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071776330414047234, 1071775472771153921, '仅本人数据', '8', 8, 1, '2018-12-09 08:39:24', 1, '2018-12-09 08:39:24', 0, '');
INSERT INTO `sys_dict_value` VALUES (1071776402845483010, 1071775472771153921, '按明细设置', '9', 9, 1, '2018-12-09 08:39:42', 1, '2018-12-09 08:39:42', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073081197791334401, 1073081042031661057, '正常', '0', 0, 1, '2018-12-12 23:04:29', 1, '2018-12-12 23:04:29', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073081320889962498, 1073081042031661057, '冻结', '1', 1, 1, '2018-12-12 23:04:58', 1, '2018-12-12 23:04:58', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073617139224662018, 1073615366678130690, '可用', '0', 0, 1, '2018-12-14 10:34:07', 1, '2018-12-14 10:34:07', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073617230429802498, 1073615366678130690, '不可用', '1', 1, 1, '2018-12-14 10:34:29', 1, '2018-12-14 10:34:29', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073931280174936066, 1073931106488807425, '是', '0', 0, 1, '2018-12-15 07:22:25', 1, '2018-12-15 07:22:25', 0, '');
INSERT INTO `sys_dict_value` VALUES (1073931393509224450, 1073931106488807425, '否', '1', 1, 1, '2018-12-15 07:22:52', 1, '2018-12-15 07:22:52', 0, '');
INSERT INTO `sys_dict_value` VALUES (1077887694027137026, 1077887529627197441, '省级', '0', 0, 1, '2018-12-26 05:23:47', 1, '2018-12-26 05:23:47', 0, '');
INSERT INTO `sys_dict_value` VALUES (1077887762868248578, 1077887529627197441, '地级', '1', 1, 1, '2018-12-26 05:24:04', 1, '2018-12-26 05:24:04', 0, '');
INSERT INTO `sys_dict_value` VALUES (1077888669383172097, 1077887529627197441, '县级', '3', 3, 1, '2018-12-26 05:27:40', 1, '2018-12-26 05:27:40', 0, '');
INSERT INTO `sys_dict_value` VALUES (1077888767919955970, 1077887529627197441, '乡级', '4', 4, 1, '2018-12-26 05:28:03', 1, '2018-12-26 05:28:03', 0, '');
INSERT INTO `sys_dict_value` VALUES (1079228262342836225, 1077912772638937089, '公司', '0', 0, 1, '2018-12-29 22:10:43', 1, '2018-12-29 22:10:43', 0, '');
INSERT INTO `sys_dict_value` VALUES (1079228607559221250, 1077912772638937089, '部门', '1', 1, 1, '2018-12-29 22:12:06', 1, '2018-12-29 22:12:06', 0, '');
INSERT INTO `sys_dict_value` VALUES (1079228690199592961, 1077912772638937089, '小组', '2', 2, 1, '2018-12-29 22:12:26', 1, '2018-12-29 22:12:26', 0, '');
INSERT INTO `sys_dict_value` VALUES (1079228755488129025, 1077912772638937089, '其他', '3', 3, 1, '2018-12-29 22:12:41', 1, '2018-12-29 22:12:41', 0, '');
INSERT INTO `sys_dict_value` VALUES (1085153745504833537, 1085153622473314306, '是', '0', 0, 1, '2019-01-15 06:36:29', 1, '2019-01-15 06:36:29', 0, '');
INSERT INTO `sys_dict_value` VALUES (1085153828908568578, 1085153622473314306, '否', '1', 1, 1, '2019-01-15 06:36:49', 1, '2019-01-15 06:36:49', 0, '');
INSERT INTO `sys_dict_value` VALUES (1085181901242638337, 1085181767851188225, '菜单', '1', 1, 1, '2019-01-15 08:28:22', 1, '2019-01-15 08:28:22', 0, '');
INSERT INTO `sys_dict_value` VALUES (1085182083476758529, 1085181767851188225, '按钮', '2', 2, 1, '2019-01-15 08:29:05', 1, '2019-01-15 08:29:05', 0, '');
INSERT INTO `sys_dict_value` VALUES (1085914234906816513, 1085181767851188225, '目录', '0', 0, 1, '2019-01-17 08:58:24', 1, '2019-01-17 08:58:24', 0, '');
INSERT INTO `sys_dict_value` VALUES (1086954335134400514, 1086942198961823745, '正常', '0', 0, 1, '2019-01-20 05:51:23', 1, '2019-01-20 05:51:23', 0, '');
INSERT INTO `sys_dict_value` VALUES (1086954404722098177, 1086942198961823745, '锁定', '1', 1, 1, '2019-01-20 05:51:39', 1, '2019-01-20 05:51:39', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088415042409332737, 1088414522714132482, '正常', '0', 0, 1, '2019-01-24 06:35:43', 1, '2019-01-24 06:35:43', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088415153608720385, 1088414522714132482, '禁用', '1', 1, 1, '2019-01-24 06:36:09', 1, '2019-01-24 06:36:09', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088415243643650049, 1088414522714132482, '锁定', '2', 2, 1, '2019-01-24 06:36:31', 1, '2019-01-24 06:36:31', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088419001983303682, 1088418616384159745, '系统管理', '0', 0, 1, '2019-01-24 06:51:27', 1, '2019-01-24 06:51:27', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088419088570515458, 1088418616384159745, '普通用户', '1', 1, 1, '2019-01-24 06:51:47', 1, '2019-01-24 06:51:47', 0, '');
INSERT INTO `sys_dict_value` VALUES (1088419214408024066, 1088418616384159745, '部门经理', '2', 2, 1, '2019-01-24 06:52:17', 1, '2019-01-24 06:52:17', 0, '');
INSERT INTO `sys_dict_value` VALUES (1089130065108729857, 1088418616384159745, '开发人员', '3', 3, 1, '2019-01-26 05:56:57', 1, '2019-01-26 05:56:57', 0, '');
INSERT INTO `sys_dict_value` VALUES (1096032443070156802, 1096032224433672194, '会议通告', '0', 0, 1, '2019-02-14 13:04:32', 1, '2019-02-14 13:04:32', 0, '');
INSERT INTO `sys_dict_value` VALUES (1096032491912826881, 1096032224433672194, '奖惩通告', '1', 1, 1, '2019-02-14 13:04:44', 1, '2019-02-14 13:04:44', 0, '');
INSERT INTO `sys_dict_value` VALUES (1096032559655030786, 1096032224433672194, '活动通告', '2', 2, 1, '2019-02-14 13:05:00', 1, '2019-02-14 13:05:00', 0, '');
INSERT INTO `sys_dict_value` VALUES (1096335664246476802, 1096335577848008706, '发布', '0', 0, 1, '2019-02-15 09:09:26', 1, '2019-02-15 09:09:26', 0, '');
INSERT INTO `sys_dict_value` VALUES (1096335709205221378, 1096335577848008706, '草稿', '1', 1, 1, '2019-02-15 09:09:36', 1, '2019-02-15 09:14:56', 0, '');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单 0为顶级目录',
  `parent_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父级标签',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `font_color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字体颜色',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型 0:目录1:菜单 2: 按钮',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限',
  `sort` int(11) NOT NULL COMMENT '排序',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单目标',
  `menu_status` int(11) NULL DEFAULT NULL COMMENT '菜单状态 0可用1不可用',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_menu_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 53, NULL, '用户管理', 'fa  fa-users', '', 'user', '1', '', 1, NULL, 0, 0, '2018-07-01 16:02:37', 0, '2019-02-09 19:32:17', 0, NULL);
INSERT INTO `sys_menu` VALUES (7, 0, '0', '系统管理', 'fa  fa-gears', '#dddddd', '', '0', '', 2, NULL, 0, 0, '2018-07-01 16:02:37', 1, '2019-02-14 12:01:37', 0, '');
INSERT INTO `sys_menu` VALUES (8, 7, NULL, '菜单管理', 'fa  fa-cog', '', 'menu', '1', '', 6, NULL, 0, 0, '2018-07-01 16:02:37', 0, '2019-02-09 19:32:17', 0, NULL);
INSERT INTO `sys_menu` VALUES (9, 8, '0', '查询', '', '#dddddd', '', '2', 'manage:menu:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2018-11-01 22:56:39', 0, '');
INSERT INTO `sys_menu` VALUES (10, 8, NULL, '新增', '', '', '', '2', 'manage:menu:add', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:24:43', 0, NULL);
INSERT INTO `sys_menu` VALUES (11, 8, NULL, '删除', '', '', '', '2', 'manage:menu:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:24:44', 0, NULL);
INSERT INTO `sys_menu` VALUES (12, 53, NULL, '角色管理', 'fa fa-user-secret', '', 'role', '1', '', 7, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:18', 0, NULL);
INSERT INTO `sys_menu` VALUES (13, 12, NULL, '查询', '', '', '', '2', 'manage:role:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:24:45', 0, NULL);
INSERT INTO `sys_menu` VALUES (14, 12, NULL, '新增', '', '', '', '2', 'manage:role:add', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:24:44', 0, NULL);
INSERT INTO `sys_menu` VALUES (15, 12, NULL, '删除', '', '', '', '2', 'manage:role:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:24:44', 0, NULL);
INSERT INTO `sys_menu` VALUES (16, 0, '0', '文件管理', 'fa fa-folder-open', '#dddddd', 'file', '1', '', 8, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:20', 0, '');
INSERT INTO `sys_menu` VALUES (17, 16, NULL, '查询', '', '', '', '2', 'manage:file:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:25:16', 0, NULL);
INSERT INTO `sys_menu` VALUES (18, 16, NULL, '删除', '', '', '', '2', 'manage:file:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:25:15', 0, NULL);
INSERT INTO `sys_menu` VALUES (19, 0, NULL, '系统监控', 'fa  fa-eye', '', '', '0', '', 9, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:18', 0, NULL);
INSERT INTO `sys_menu` VALUES (20, 44, NULL, '接口swagger', 'fa fa-file-pdf-o', '', 'swagger-ui.html', '1', '', 10, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:17', 0, NULL);
INSERT INTO `sys_menu` VALUES (22, 0, '0', '多级菜单', 'fa fa-bars', '#dddddd', '', '0', '', 12, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:20', 0, '');
INSERT INTO `sys_menu` VALUES (26, 0, '0', '日志管理', 'fa  fa-file-text-o', '#dddddd', '', '0', 'manage:log:query', 13, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:19', 0, '');
INSERT INTO `sys_menu` VALUES (27, 0, '0', '邮件管理', 'fa  fa-envelope', '#dddddd', 'pages/mail/mailList.html', '0', '', 14, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:20', 0, '');
INSERT INTO `sys_menu` VALUES (28, 7, '0', '发送邮件', '', '#dddddd', '', '2', 'manage:mail:send', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2018-08-04 19:06:30', 0, '');
INSERT INTO `sys_menu` VALUES (29, 27, '0', '查询', '', '', '', '2', 'manage:mail:all:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:13', 0, '');
INSERT INTO `sys_menu` VALUES (30, 0, '0', '定时任务管理', 'fa fa-tasks', '#dddddd', 'pages/job/jobList.html', '0', '', 15, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:20', 0, '');
INSERT INTO `sys_menu` VALUES (31, 30, '0', '查询', '', '', '', '2', 'manage:job:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:09', 0, '');
INSERT INTO `sys_menu` VALUES (32, 30, '0', '新增', '', '', '', '2', 'manage:job:add', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:13', 0, '');
INSERT INTO `sys_menu` VALUES (33, 30, '0', '删除', '', '', '', '2', 'manage:job:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:09', 0, '');
INSERT INTO `sys_menu` VALUES (37, 7, NULL, '字典管理', 'fa fa-reddit', '', 'dict/type', '0', '', 17, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:17', 0, NULL);
INSERT INTO `sys_menu` VALUES (38, 37, '0', '查询', '', '', '', '2', 'manage:dict:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:09', 0, '');
INSERT INTO `sys_menu` VALUES (39, 37, '0', '新增', '', '', '', '2', 'manage:dict:add', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:09', 0, '');
INSERT INTO `sys_menu` VALUES (40, 37, '0', '删除', '', '', '', '2', 'manage:dict:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-07 22:25:10', 0, '');
INSERT INTO `sys_menu` VALUES (41, 22, NULL, '一级菜单', 'fa  fa-circle-o', '', '', '1', '', 12, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:20', 0, NULL);
INSERT INTO `sys_menu` VALUES (42, 41, NULL, '二级菜单', 'fa  fa-circle-o', '', '', '1', '', 12, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:19', 0, NULL);
INSERT INTO `sys_menu` VALUES (43, 42, '0', '三级菜单（一）', 'fa  fa-circle-o', '#dddddd', '', '1', '', 12, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:18', 0, '');
INSERT INTO `sys_menu` VALUES (44, 0, '0', '开发工具', 'fa  fa-circle-o text-aqua', '#dddddd', '', '0', '', 10, NULL, 1, 0, '2018-07-01 16:02:54', 1, '2019-02-09 19:32:18', 0, '');
INSERT INTO `sys_menu` VALUES (46, 8, '0', '修改', '', '#dddddd', '', '2', 'manage:menu:edit', 10, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-08 08:21:42', 0, '');
INSERT INTO `sys_menu` VALUES (47, 1, '0', '查询', '', '#dddddd', '', '2', 'manage:user:query', 100, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2019-02-08 03:58:10', 0, '');
INSERT INTO `sys_menu` VALUES (48, 1, NULL, '新增', '', '', '', '2', 'manage:user:add', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:25:14', 0, NULL);
INSERT INTO `sys_menu` VALUES (49, 1, NULL, '删除', '', '', '', '2', 'manage:user:del', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-07 22:25:14', 0, NULL);
INSERT INTO `sys_menu` VALUES (50, 1, '0', '修改', '', '#dddddd', '', '2', 'manage:user:edit', 10, NULL, 0, 0, '2018-07-01 16:02:54', 1, '2018-11-01 22:55:59', 0, '');
INSERT INTO `sys_menu` VALUES (51, 44, NULL, '表单配置', 'fa  fa-file-pdf-o', '', 'modules/gen/list.html', '1', '', 100, NULL, 0, 0, '2018-07-01 16:02:54', 0, '2019-02-09 19:32:20', 0, NULL);
INSERT INTO `sys_menu` VALUES (52, 53, NULL, '部门管理', 'fa  fa-file-code-o', '', 'org', '0', '', 5, NULL, 0, 0, '2018-07-01 16:02:37', 0, '2019-02-09 19:32:19', 0, NULL);
INSERT INTO `sys_menu` VALUES (53, 0, '0', '组织架构', 'fa  fa-sitemap', '#dddddd', '', '0', '', 2, NULL, 0, 0, '2018-07-01 16:02:37', 1, '2019-02-09 19:32:19', 0, '');
INSERT INTO `sys_menu` VALUES (54, 19, NULL, '在线用户', 'fa   fa-users', '', 'monitor/online/user/view', '1', '', 101, NULL, 0, 0, '2019-01-11 12:22:32', 0, '2019-02-09 19:32:18', 0, NULL);
INSERT INTO `sys_menu` VALUES (1086992901067558913, 52, '0', '新增', NULL, '#dddddd', NULL, '2', 'manage:org:add', 0, NULL, 0, 1, '2019-01-20 08:24:38', 1, '2019-01-20 08:24:38', 0, '');
INSERT INTO `sys_menu` VALUES (1086993134316998658, 52, '0', '修改', NULL, '#dddddd', NULL, '2', 'manage:org:edit', 9999, NULL, 0, 1, '2019-01-20 08:25:33', 1, '2019-02-08 08:22:06', 0, '');
INSERT INTO `sys_menu` VALUES (1087347698790576129, 16, '0', '添加', NULL, '#dddddd', NULL, '2', 'manage:file:add', 7, NULL, 0, 1, '2019-01-21 07:54:28', 1, '2019-01-21 07:54:28', 0, '');
INSERT INTO `sys_menu` VALUES (1087724118092132353, 52, '0', '删除', NULL, '#dddddd', NULL, '2', 'manage:org:del', 0, NULL, 0, 1, '2019-01-22 08:50:13', 1, '2019-01-22 08:50:13', 0, '');
INSERT INTO `sys_menu` VALUES (1089503509683933186, 26, '0', '登录日志', NULL, '#ffffff', 'log/login', '0', '', 0, NULL, 0, 1, '2019-01-27 06:40:53', 1, '2019-02-07 22:25:42', 0, '');
INSERT INTO `sys_menu` VALUES (1089503626952478722, 26, '0', '操作日志', NULL, '#ffffff', 'log/open', '0', '', 0, NULL, 0, 1, '2019-01-27 06:41:21', 1, '2019-02-07 22:25:42', 0, '');
INSERT INTO `sys_menu` VALUES (1092619492460130305, 42, '0', '三级菜单（二）', 'fa   fa-circle-o', '#dddddd', NULL, '1', '', 200, NULL, 0, 1, '2019-02-04 21:02:42', 1, '2019-02-09 19:32:21', 0, '');
INSERT INTO `sys_menu` VALUES (1093810117331148802, 37, '0', '修改', NULL, '#dddddd', NULL, '2', 'manage:dict:edit', 0, NULL, 0, 1, '2019-02-08 03:53:49', 1, '2019-02-08 08:21:22', 0, '');
INSERT INTO `sys_menu` VALUES (1093811378357035010, 52, '0', '查询', NULL, '#dddddd', NULL, '2', 'manage:org:query', 0, NULL, 0, 1, '2019-02-08 03:58:49', 1, '2019-02-08 03:58:49', 0, '');
INSERT INTO `sys_menu` VALUES (1093811581130661889, 12, '0', '修改', NULL, '#dddddd', NULL, '2', 'manage:role:edit', 0, NULL, 0, 1, '2019-02-08 03:59:38', 1, '2019-02-08 04:12:14', 0, '');
INSERT INTO `sys_menu` VALUES (1096016383835123713, 0, '0', '通知公告', 'fa fa-commenting-o', '#dddddd', 'notify', '1', NULL, 0, NULL, 0, 1, '2019-02-14 12:00:43', 1, '2019-02-14 12:02:25', 0, '');
INSERT INTO `sys_menu` VALUES (1096017410365861889, 1096016383835123713, '0,1096016383835123713', '新增', NULL, '#dddddd', NULL, '2', 'manage:notify:add', 99999, NULL, 0, 1, '2019-02-14 12:04:48', 1, '2019-02-14 12:04:48', 0, '');
INSERT INTO `sys_menu` VALUES (1096017504527986689, 1096016383835123713, '0,1096016383835123713', '修改', NULL, '#dddddd', NULL, '2', 'manage:notify:edit', 99999, NULL, 0, 1, '2019-02-14 12:05:10', 1, '2019-02-14 12:05:10', 0, '');
INSERT INTO `sys_menu` VALUES (1096017585767460866, 1096016383835123713, '0,1096016383835123713', '删除', NULL, '#dddddd', NULL, '2', 'manage:notify:del', 99999, NULL, 0, 1, '2019-02-14 12:05:30', 1, '2019-02-14 12:05:30', 0, '');
INSERT INTO `sys_menu` VALUES (1096017694378962946, 1096016383835123713, '0,1096016383835123713', '查询', NULL, '#dddddd', NULL, '2', 'manage:notify:query', 99999, NULL, 0, 1, '2019-02-14 12:05:56', 1, '2019-02-14 12:05:56', 0, '');
INSERT INTO `sys_menu` VALUES (1096724847318376449, 19, 'null,19', '操作日志', 'fa fa-file-text-o', '#dddddd', 'log/operate', '1', '', 99999, NULL, 0, 1, '2019-02-16 10:55:54', 1, '2019-02-16 10:55:54', 0, '');

-- ----------------------------
-- Table structure for sys_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify`;
CREATE TABLE `sys_notify`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `notify_genre` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `notify_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知通告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notify_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify_record`;
CREATE TABLE `sys_notify_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `notify_id` bigint(20) NULL DEFAULT NULL COMMENT '通知通告',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '接受人',
  `read_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '阅读标记 1未阅读，0阅读',
  `read_date` datetime(0) NULL DEFAULT NULL COMMENT '阅读时间',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知通告发送记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `id` bigint(11) NOT NULL COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '功能请求',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operate_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `channel` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '来源渠道（0 后台用户 1 手机端用户 2其它）',
  `open_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `open_org` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `operate_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `operate_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `operate_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `operate_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '操作状态',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES (1096723936797892610, '查询所有菜单', '8', 'org.smallbun.fast.manage.menu.controller.SysMenuController.list()', '{}', '0', '1', '232399', '/menu/list', '本地', '本地', '200', '', '2019-02-16 10:52:17', 1, '2019-02-16 10:52:17', 1, '2019-02-16 10:52:17', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096723995056775169, '查询所有菜单', '8', 'org.smallbun.fast.manage.menu.controller.SysMenuController.list()', '{}', '0', '1', '232399', '/menu/list', '本地', '本地', '200', '', '2019-02-16 10:52:31', 1, '2019-02-16 10:52:31', 1, '2019-02-16 10:52:31', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724847687475201, '', '1', 'org.smallbun.fast.manage.menu.controller.SysMenuController.saveOrUpdate()', '{\"id\":[\"\"],\"parentId\":[\"19\"],\"menuName\":[\"操作日志\"],\"menuType\":[\"1\"],\"menuStatus\":[\"0\"],\"url\":[\"log/operate\"],\"target\":[\"\"],\"icon\":[\"fa fa-file-text-o\"],\"fontColor\":[\"#dddddd\"],\"permission\":[\"\"],\"sort\":[\"99999\"],\"remarks\":[\"\"]}', '0', '1', '232399', '/menu/saveOrUpdate', '本地', '本地', '200', '', '2019-02-16 10:55:54', 1, '2019-02-16 10:55:54', 1, '2019-02-16 10:55:54', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724856210300930, '查询所有菜单', '8', 'org.smallbun.fast.manage.menu.controller.SysMenuController.list()', '{}', '0', '1', '232399', '/menu/list', '本地', '本地', '200', '', '2019-02-16 10:55:56', 1, '2019-02-16 10:55:56', 1, '2019-02-16 10:55:56', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724890351935490, '', '8', 'org.smallbun.fast.manage.role.controller.SysRoleController.page()', '{\"size\":[\"20\"],\"current\":[\"1\"],\"orderByColumn\":[\"gmtCreate\"],\"isAsc\":[\"desc\"]}', '0', '1', '232399', '/role/page', '本地', '本地', '200', '', '2019-02-16 10:56:04', 1, '2019-02-16 10:56:04', 1, '2019-02-16 10:56:04', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724910937579522, '', '5', 'org.smallbun.fast.manage.role.controller.SysRoleController.form()', '{\"id\":[\"1073957301490032642\"]}', '0', '1', '232399', '/role/form/', '本地', '本地', '200', '', '2019-02-16 10:56:09', 1, '2019-02-16 10:56:09', 1, '2019-02-16 10:56:09', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724929564483586, '查询所有菜单', '8', 'org.smallbun.fast.manage.menu.controller.SysMenuController.list()', '{}', '0', '1', '232399', '/menu/list', '本地', '本地', '200', '', '2019-02-16 10:56:14', 1, '2019-02-16 10:56:14', 1, '2019-02-16 10:56:14', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724961478942722, '', '1', 'org.smallbun.fast.manage.role.controller.SysRoleController.saveOrUpdate()', '{\"id\":[\"1073957301490032642\"],\"menusJson\":[\"[{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-08 11:59:38\\\",\\\"gmtModified\\\":\\\"2019-02-08 12:12:14\\\",\\\"id\\\":\\\"1093811581130661889\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"12\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:role:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0},{\\\"fontColor\\\":\\\"#ffffff\\\",\\\"gmtCreate\\\":\\\"2019-01-27 14:40:53\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:42\\\",\\\"id\\\":\\\"1089503509683933186\\\",\\\"menuName\\\":\\\"登录日志\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"26\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0,\\\"url\\\":\\\"log/login\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-08 11:58:49\\\",\\\"gmtModified\\\":\\\"2019-02-08 11:58:49\\\",\\\"id\\\":\\\"1093811378357035010\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"52\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:org:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-14 20:00:43\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:02:25\\\",\\\"icon\\\":\\\"fa fa-commenting-o\\\",\\\"id\\\":\\\"1096016383835123713\\\",\\\"menuName\\\":\\\"通知公告\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"0\\\",\\\"parentIds\\\":\\\"0\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0,\\\"url\\\":\\\"notify\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-01-22 16:50:13\\\",\\\"gmtModified\\\":\\\"2019-01-22 16:50:13\\\",\\\"id\\\":\\\"1087724118092132353\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"52\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:org:del\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0},{\\\"fontColor\\\":\\\"#ffffff\\\",\\\"gmtCreate\\\":\\\"2019-01-27 14:41:21\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:42\\\",\\\"id\\\":\\\"1089503626952478722\\\",\\\"menuName\\\":\\\"操作日志\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"26\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0,\\\"url\\\":\\\"log/open\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-08 11:53:49\\\",\\\"gmtModified\\\":\\\"2019-02-08 16:21:22\\\",\\\"id\\\":\\\"1093810117331148802\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"37\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:dict:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-01-20 16:24:38\\\",\\\"gmtModified\\\":\\\"2019-01-20 16:24:38\\\",\\\"id\\\":\\\"1086992901067558913\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"52\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:org:add\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":0},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:37\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:17\\\",\\\"icon\\\":\\\"fa  fa-users\\\",\\\"id\\\":\\\"1\\\",\\\"menuName\\\":\\\"用户管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"53\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":1,\\\"url\\\":\\\"user\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:37\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:01:37\\\",\\\"icon\\\":\\\"fa  fa-gears\\\",\\\"id\\\":\\\"7\\\",\\\"menuName\\\":\\\"系统管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"0\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":2,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:37\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:19\\\",\\\"icon\\\":\\\"fa  fa-sitemap\\\",\\\"id\\\":\\\"53\\\",\\\"menuName\\\":\\\"组织架构\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"0\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":2,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:37\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:19\\\",\\\"icon\\\":\\\"fa  fa-file-code-o\\\",\\\"id\\\":\\\"52\\\",\\\"menuName\\\":\\\"部门管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"53\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":5,\\\"url\\\":\\\"org\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:37\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:17\\\",\\\"icon\\\":\\\"fa  fa-cog\\\",\\\"id\\\":\\\"8\\\",\\\"menuName\\\":\\\"菜单管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"7\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":6,\\\"url\\\":\\\"menu\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-01-21 15:54:28\\\",\\\"gmtModified\\\":\\\"2019-01-21 15:54:28\\\",\\\"id\\\":\\\"1087347698790576129\\\",\\\"menuName\\\":\\\"添加\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"16\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:file:add\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":7},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:18\\\",\\\"icon\\\":\\\"fa fa-user-secret\\\",\\\"id\\\":\\\"12\\\",\\\"menuName\\\":\\\"角色管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"53\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":7,\\\"url\\\":\\\"role\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:18\\\",\\\"icon\\\":\\\"fa  fa-eye\\\",\\\"id\\\":\\\"19\\\",\\\"menuName\\\":\\\"系统监控\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":9,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2018-11-02 06:55:59\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"50\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:user:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":10,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:17\\\",\\\"icon\\\":\\\"fa fa-file-pdf-o\\\",\\\"id\\\":\\\"20\\\",\\\"menuName\\\":\\\"接口swagger\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"44\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":10,\\\"url\\\":\\\"swagger-ui.html\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 16:21:42\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"46\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"8\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:menu:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":10,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:20\\\",\\\"icon\\\":\\\"fa  fa-circle-o\\\",\\\"id\\\":\\\"41\\\",\\\"menuName\\\":\\\"一级菜单\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"22\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":12,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:18\\\",\\\"icon\\\":\\\"fa  fa-circle-o\\\",\\\"id\\\":\\\"43\\\",\\\"menuName\\\":\\\"三级菜单（一）\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"42\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":12,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:19\\\",\\\"icon\\\":\\\"fa  fa-circle-o\\\",\\\"id\\\":\\\"42\\\",\\\"menuName\\\":\\\"二级菜单\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"41\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":12,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:17\\\",\\\"icon\\\":\\\"fa fa-reddit\\\",\\\"id\\\":\\\"37\\\",\\\"menuName\\\":\\\"字典管理\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":0,\\\"parentId\\\":\\\"7\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":17,\\\"url\\\":\\\"dict/type\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:09\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"39\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"37\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:dict:add\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:24:44\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"11\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"8\\\",\\\"permission\\\":\\\"manage:menu:del\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:14\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"48\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1\\\",\\\"permission\\\":\\\"manage:user:add\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2018-08-05 03:06:30\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"28\\\",\\\"menuName\\\":\\\"发送邮件\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"7\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:mail:send\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:24:45\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"13\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"12\\\",\\\"permission\\\":\\\"manage:role:query\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:09\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"31\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"30\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:job:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:24:44\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"15\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"12\\\",\\\"permission\\\":\\\"manage:role:del\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:09\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"33\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"30\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:job:del\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:15\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"18\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"16\\\",\\\"permission\\\":\\\"manage:file:del\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:09\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"38\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"37\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:dict:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:24:43\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"10\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"8\\\",\\\"permission\\\":\\\"manage:menu:add\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 11:58:10\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"47\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:user:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:14\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"49\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1\\\",\\\"permission\\\":\\\"manage:user:del\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:10\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"40\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"37\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:dict:del\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:13\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"29\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"27\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:mail:all:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:24:44\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"14\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"12\\\",\\\"permission\\\":\\\"manage:role:add\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:20\\\",\\\"icon\\\":\\\"fa  fa-file-pdf-o\\\",\\\"id\\\":\\\"51\\\",\\\"menuName\\\":\\\"表单配置\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"44\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"modules/gen/list.html\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:13\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"32\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"30\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:job:add\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2019-02-08 06:25:16\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"17\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"16\\\",\\\"permission\\\":\\\"manage:file:query\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2018-07-02 00:02:54\\\",\\\"gmtModified\\\":\\\"2018-11-02 06:56:39\\\",\\\"icon\\\":\\\"\\\",\\\"id\\\":\\\"9\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"8\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:menu:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":100,\\\"url\\\":\\\"\\\"},{\\\"fontColor\\\":\\\"\\\",\\\"gmtCreate\\\":\\\"2019-01-11 20:22:32\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:18\\\",\\\"icon\\\":\\\"fa   fa-users\\\",\\\"id\\\":\\\"54\\\",\\\"menuName\\\":\\\"在线用户\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"19\\\",\\\"permission\\\":\\\"\\\",\\\"sort\\\":101,\\\"url\\\":\\\"monitor/online/user/view\\\"},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-05 05:02:42\\\",\\\"gmtModified\\\":\\\"2019-02-10 03:32:21\\\",\\\"icon\\\":\\\"fa   fa-circle-o\\\",\\\"id\\\":\\\"1092619492460130305\\\",\\\"menuName\\\":\\\"三级菜单（二）\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":1,\\\"parentId\\\":\\\"42\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":200},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-01-20 16:25:33\\\",\\\"gmtModified\\\":\\\"2019-02-08 16:22:06\\\",\\\"id\\\":\\\"1086993134316998658\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"52\\\",\\\"parentIds\\\":\\\"0\\\",\\\"permission\\\":\\\"manage:org:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":9999},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-14 20:04:48\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:04:48\\\",\\\"id\\\":\\\"1096017410365861889\\\",\\\"menuName\\\":\\\"新增\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1096016383835123713\\\",\\\"parentIds\\\":\\\"0,1096016383835123713\\\",\\\"permission\\\":\\\"manage:notify:add\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":99999},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-14 20:05:30\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:05:30\\\",\\\"id\\\":\\\"1096017585767460866\\\",\\\"menuName\\\":\\\"删除\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1096016383835123713\\\",\\\"parentIds\\\":\\\"0,1096016383835123713\\\",\\\"permission\\\":\\\"manage:notify:del\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":99999},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-14 20:05:10\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:05:10\\\",\\\"id\\\":\\\"1096017504527986689\\\",\\\"menuName\\\":\\\"修改\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1096016383835123713\\\",\\\"parentIds\\\":\\\"0,1096016383835123713\\\",\\\"permission\\\":\\\"manage:notify:edit\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":99999},{\\\"fontColor\\\":\\\"#dddddd\\\",\\\"gmtCreate\\\":\\\"2019-02-14 20:05:56\\\",\\\"gmtModified\\\":\\\"2019-02-14 20:05:56\\\",\\\"id\\\":\\\"1096017694378962946\\\",\\\"menuName\\\":\\\"查询\\\",\\\"menuStatus\\\":\\\"0\\\",\\\"menuType\\\":2,\\\"parentId\\\":\\\"1096016383835123713\\\",\\\"parentIds\\\":\\\"0,1096016383835123713\\\",\\\"permission\\\":\\\"manage:notify:query\\\",\\\"remarks\\\":\\\"\\\",\\\"sort\\\":99999}]\"],\"orgsJson\":[\"\"],\"roleName\":[\"超级管理员\"],\"enName\":[\"ROOT\"],\"roleType\":[\"0\"],\"useable\":[\"0\"],\"sysData\":[\"0\"],\"dataScope\":[\"1\"],\"remarks\":[\"\"],\"menuList[0].id\":[\"1096016383835123713\"],\"menuList[1].id\":[\"1096017504527986689\"],\"menuList[2].id\":[\"1096017410365861889\"],\"menuList[3].id\":[\"1096017694378962946\"],\"menuList[4].id\":[\"1096017585767460866\"],\"menuList[5].id\":[\"7\"],\"menuList[6].id\":[\"8\"],\"menuList[7].id\":[\"46\"],\"menuList[8].id\":[\"9\"],\"menuList[9].id\":[\"10\"],\"menuList[10].id\":[\"11\"],\"menuList[11].id\":[\"37\"],\"menuList[12].id\":[\"1093810117331148802\"],\"menuList[13].id\":[\"38\"],\"menuList[14].id\":[\"39\"],\"menuList[15].id\":[\"40\"],\"menuList[16].id\":[\"28\"],\"menuList[17].id\":[\"53\"],\"menuList[18].id\":[\"1\"],\"menuList[19].id\":[\"50\"],\"menuList[20].id\":[\"47\"],\"menuList[21].id\":[\"48\"],\"menuList[22].id\":[\"49\"],\"menuList[23].id\":[\"52\"],\"menuList[24].id\":[\"1087724118092132353\"],\"menuList[25].id\":[\"1086992901067558913\"],\"menuList[26].id\":[\"1093811378357035010\"],\"menuList[27].id\":[\"1086993134316998658\"],\"menuList[28].id\":[\"12\"],\"menuList[29].id\":[\"1093811581130661889\"],\"menuList[30].id\":[\"13\"],\"menuList[31].id\":[\"14\"],\"menuList[32].id\":[\"15\"],\"menuList[33].id\":[\"1087347698790576129\"],\"menuList[34].id\":[\"17\"],\"menuList[35].id\":[\"18\"],\"menuList[36].id\":[\"19\"],\"menuList[37].id\":[\"54\"],\"menuList[38].id\":[\"1096724847318376449\"],\"menuList[39].id\":[\"20\"],\"menuList[40].id\":[\"51\"],\"menuList[41].id\":[\"41\"],\"menuList[42].id\":[\"42\"],\"menuList[43].id\":[\"43\"],\"menuList[44].id\":[\"1092619492460130305\"],\"menuList[45].id\":[\"1089503626952478722\"],\"menuList[46].id\":[\"1089503509683933186\"],\"menuList[47].id\":[\"29\"],\"menuList[48].id\":[\"31\"],\"menuList[49].id\":[\"32\"],\"menuList[50].id\":[\"33\"]}', '0', '1', '232399', '/role/saveOrUpdate', '本地', '本地', '200', '', '2019-02-16 10:56:21', 1, '2019-02-16 10:56:21', 1, '2019-02-16 10:56:21', '0', NULL);
INSERT INTO `sys_operate_log` VALUES (1096724973092970497, '', '8', 'org.smallbun.fast.manage.role.controller.SysRoleController.page()', '{\"size\":[\"20\"],\"current\":[\"1\"],\"orderByColumn\":[\"gmtCreate\"],\"isAsc\":[\"desc\"]}', '0', '1', '232399', '/role/page', '本地', '本地', '200', '', '2019-02-16 10:56:24', 1, '2019-02-16 10:56:24', 1, '2019-02-16 10:56:24', '0', NULL);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父级编号',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构名称',
  `org_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编码',
  `area_id` bigint(20) NULL DEFAULT NULL COMMENT '归属区域',
  `org_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构类型',
  `grade` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构等级',
  `longitude` decimal(65, 0) NULL DEFAULT NULL COMMENT '经度',
  `latitude` decimal(65, 0) NULL DEFAULT NULL COMMENT '纬度',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `zip_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `principal` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `telephone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `fax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `useable` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可用',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_office_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_office_del_flag`(`is_deleted`) USING BTREE,
  INDEX `sys_office_type`(`org_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, 0, '0', '山东-济南', 'A000', 1, '0', '0', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-02-15 10:50:32', '0', '');
INSERT INTO `sys_org` VALUES (231112, 1, '0,1', '商务部', 'A001', 1, '1', '1', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-02-07 08:52:53', '0', '');
INSERT INTO `sys_org` VALUES (232399, 1, '0,1', '行政部', 'A002', 1, '1', '1', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-01-31 09:59:48', '0', '');
INSERT INTO `sys_org` VALUES (233334, 1, '0,1', '产品体验部', 'A003', 1, '1', '1', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-01-31 09:59:47', '0', '');
INSERT INTO `sys_org` VALUES (300003, 1, '0,1', '技术部', 'A004', 1, '1', '1', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-01-31 09:59:47', '0', '');
INSERT INTO `sys_org` VALUES (465723, 1, '0,1', '招商部', 'A005', 1, '1', '1', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-01-31 09:59:47', '0', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `en_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `data_scope` int(11) NULL DEFAULT NULL COMMENT '数据范围',
  `role_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  `useable` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可用',
  `sys_data` bigint(255) NULL DEFAULT NULL COMMENT '系统数据',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1084827767704403971 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'SYSTEM', 2, '0', '0', 1, 0, '2018-07-01 15:17:40', 1, '2019-02-08 04:20:32', '0', '');
INSERT INTO `sys_role` VALUES (2, '系统用户', 'USER', 8, '1', '0', 1, 0, '2018-07-01 15:17:40', 1, '2019-02-09 04:56:07', '0', '');
INSERT INTO `sys_role` VALUES (1073957301490032642, '超级管理员', 'ROOT', 1, '0', '0', 0, 1, '2018-12-15 09:05:48', 1, '2019-02-16 10:56:21', '0', '');
INSERT INTO `sys_role` VALUES (1077874980521287682, '开发人员', 'KF', 9, '1', '1', 1, 1, '2018-12-26 04:33:16', 1, '2018-12-26 04:33:16', '0', '');
INSERT INTO `sys_role` VALUES (1084827767704403970, '开发公司', 'KF', 3, '1', '1', 1, 1, '2019-01-14 09:01:10', 1, '2019-01-14 09:01:10', '1', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '关联菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (1, 29);
INSERT INTO `sys_role_menu` VALUES (1, 30);
INSERT INTO `sys_role_menu` VALUES (1, 31);
INSERT INTO `sys_role_menu` VALUES (1, 32);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (1, 35);
INSERT INTO `sys_role_menu` VALUES (1, 36);
INSERT INTO `sys_role_menu` VALUES (1, 37);
INSERT INTO `sys_role_menu` VALUES (1, 38);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1, 40);
INSERT INTO `sys_role_menu` VALUES (1, 41);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1, 45);
INSERT INTO `sys_role_menu` VALUES (1, 46);
INSERT INTO `sys_role_menu` VALUES (1, 47);
INSERT INTO `sys_role_menu` VALUES (1, 48);
INSERT INTO `sys_role_menu` VALUES (1, 49);
INSERT INTO `sys_role_menu` VALUES (1, 50);
INSERT INTO `sys_role_menu` VALUES (1, 51);
INSERT INTO `sys_role_menu` VALUES (1, 52);
INSERT INTO `sys_role_menu` VALUES (1, 53);
INSERT INTO `sys_role_menu` VALUES (1, 54);
INSERT INTO `sys_role_menu` VALUES (1, 1086992901067558913);
INSERT INTO `sys_role_menu` VALUES (1, 1086993134316998658);
INSERT INTO `sys_role_menu` VALUES (1, 1087347698790576129);
INSERT INTO `sys_role_menu` VALUES (1, 1087724118092132353);
INSERT INTO `sys_role_menu` VALUES (1, 1089503509683933186);
INSERT INTO `sys_role_menu` VALUES (1, 1089503626952478722);
INSERT INTO `sys_role_menu` VALUES (1, 1092619492460130305);
INSERT INTO `sys_role_menu` VALUES (1, 1093810117331148802);
INSERT INTO `sys_role_menu` VALUES (1, 1093811378357035010);
INSERT INTO `sys_role_menu` VALUES (1, 1093811581130661889);
INSERT INTO `sys_role_menu` VALUES (2, 45);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 7);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 8);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 9);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 10);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 11);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 12);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 13);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 14);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 15);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 17);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 18);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 19);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 20);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 28);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 29);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 31);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 32);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 33);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 37);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 38);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 39);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 40);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 41);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 42);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 43);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 46);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 47);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 48);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 49);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 50);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 51);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 52);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 53);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 54);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1086992901067558913);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1086993134316998658);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1087347698790576129);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1087724118092132353);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1089503509683933186);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1089503626952478722);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1092619492460130305);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1093810117331148802);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1093811378357035010);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1093811581130661889);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096016383835123713);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096017410365861889);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096017504527986689);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096017585767460866);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096017694378962946);
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1096724847318376449);
INSERT INTO `sys_role_menu` VALUES (1076743671619022850, 45);
INSERT INTO `sys_role_menu` VALUES (1076834908820762626, 20);
INSERT INTO `sys_role_menu` VALUES (1076834908820762626, 44);
INSERT INTO `sys_role_menu` VALUES (1076834908820762626, 51);
INSERT INTO `sys_role_menu` VALUES (1077874980521287682, 20);
INSERT INTO `sys_role_menu` VALUES (1077874980521287682, 44);
INSERT INTO `sys_role_menu` VALUES (1077874980521287682, 51);
INSERT INTO `sys_role_menu` VALUES (1084827767704403970, 20);
INSERT INTO `sys_role_menu` VALUES (1084827767704403970, 44);
INSERT INTO `sys_role_menu` VALUES (1084827767704403970, 51);

-- ----------------------------
-- Table structure for sys_role_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org`;
CREATE TABLE `sys_role_org`  (
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '组织',
  UNIQUE INDEX `sys_role_org_pk`(`role_id`, `org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-组织' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_org
-- ----------------------------
INSERT INTO `sys_role_org` VALUES (1076834908820762626, 1);
INSERT INTO `sys_role_org` VALUES (1076834908820762626, 300003);
INSERT INTO `sys_role_org` VALUES (1077874980521287682, 1);
INSERT INTO `sys_role_org` VALUES (1077874980521287682, 300003);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `job_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `head_portrait_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像URL',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `qr_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `last_login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录ip',
  `last_login_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录地址',
  `last_login_time` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登录日期',
  `user_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态  0：正常   1：禁用 2：锁定',
  `user_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `org_id` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户归属组织',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`, `gmt_modified`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1088787447501930498 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', 'admin', '$2a$10$qLpUo87P.NtgkGJE2D9XeO6j//3wWI3URizII87SHAmSp5CLM4S5a', '管理员', 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_family.jpg', '', '', '', '2018-05-25', NULL, NULL, NULL, '0:0:0:0:0:0:0:1', '本地', '2019-02-16 10:42:13', '0', '0', '232399', 1, '2018-06-18 10:50:02', 1, '2019-02-16 18:42:13', '0', '');
INSERT INTO `sys_user` VALUES (2, 'user', 'user', 'user', '$2a$10$W7c1gJyPxf9Rtp3/G5WLre6vJomdzaSqEDt2/jJa70A6IHTetRkoO', '管理员', 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_colleagues.jpg', '', '', '', '1998-06-15', NULL, NULL, NULL, '0:0:0:0:0:0:0:1', '本地', '2019-02-09 18:59:04', '0', '1', '231112', 1, '2018-06-18 10:50:02', 1, '2019-02-09 18:59:04', '0', '');
INSERT INTO `sys_user` VALUES (1088787447501930497, '研发', 'SanLi', 'A0001', '000000', NULL, NULL, '', '', '', '1998-06-19', '370983199806225319', NULL, NULL, NULL, NULL, '2019-01-26 19:42:34', '2', '3', '300003', 1, '2019-01-25 07:15:31', 1, '2019-02-09 01:58:51', '0', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1073957301490032642);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (1088787447501930497, 1077874980521287682);

SET FOREIGN_KEY_CHECKS = 1;
