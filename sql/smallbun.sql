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

 Date: 11/02/2019 20:02:55
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
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
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
INSERT INTO `sys_menu` VALUES (7, 0, '0', '系统管理', 'fa  fa-gears', '#dddddd', '', '0', '', 0, NULL, 0, 0, '2018-07-01 16:02:37', 1, '2019-02-09 19:32:20', 0, '');
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
INSERT INTO `sys_org` VALUES (1, 0, '0', '山东-济南', 'A000', 1, '0', '0', NULL, 1, '', '', '', '', '', '', '0', 1, '2018-08-04 14:47:22', 1, '2019-02-09 02:19:00', '0', '');
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
INSERT INTO `sys_role` VALUES (1073957301490032642, '超级管理员', 'ROOT', 1, '0', '0', 0, 1, '2018-12-15 09:05:48', 1, '2019-02-09 05:18:23', '0', '');
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
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 45);
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
INSERT INTO `sys_role_menu` VALUES (1073957301490032642, 1094193491006828546);
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
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', 'admin', '$2a$10$qLpUo87P.NtgkGJE2D9XeO6j//3wWI3URizII87SHAmSp5CLM4S5a', '管理员', 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_family.jpg', '', '', '', '2018-05-25', NULL, NULL, NULL, '0:0:0:0:0:0:0:1', '本地', '2019-02-11 11:50:43', '0', '0', '232399', 1, '2018-06-18 10:50:02', 1, '2019-02-11 19:50:43', '0', '');
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
