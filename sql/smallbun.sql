/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : smallbun

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-02-25 10:44:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `type_name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `type_code` varchar(100) DEFAULT NULL COMMENT '类型编码',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_dict_type_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1068147088417656833', '性别', 'SEX', '1', '2018-11-29 08:18:06', '1', '2018-11-29 08:18:06', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1070172546462183426', '操作类型', 'SYS_OPER_TYPE', '1', '2018-12-04 22:26:33', '1', '2018-12-04 22:26:33', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1071771839543394306', '系统数据', 'SYSTEM_DATA', '1', '2018-12-09 08:21:34', '1', '2018-12-09 08:21:34', '0', '系统数据');
INSERT INTO `sys_dict_type` VALUES ('1071774715300823041', '角色类型', 'ROLE_TYPE', '1', '2018-12-09 08:32:59', '1', '2018-12-09 08:32:59', '0', '角色类型');
INSERT INTO `sys_dict_type` VALUES ('1071775472771153921', '数据范围', 'DATA_SCOPE', '1', '2018-12-09 08:36:00', '1', '2018-12-09 08:36:00', '0', '数据范围');
INSERT INTO `sys_dict_type` VALUES ('1073081042031661057', '角色状态', 'ROLE_STATUS', '1', '2018-12-12 23:03:52', '1', '2018-12-12 23:03:52', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1073615366678130690', '是否可用', 'WHETHER_USEABLE', '1', '2018-12-14 10:27:05', '1', '2018-12-14 10:27:05', '0', '是否可用');
INSERT INTO `sys_dict_type` VALUES ('1073931106488807425', '系统数据', 'SYS_DATA', '1', '2018-12-15 07:21:43', '1', '2018-12-15 07:21:43', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1077887529627197441', '机构级别', 'ORG_LEVEL', '1', '2018-12-26 05:23:08', '1', '2018-12-26 05:23:08', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1077912772638937089', '机构类型', 'ORG_TYPE', '1', '2018-12-26 07:03:26', '1', '2018-12-26 07:03:26', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1085153622473314306', '可见', 'VISIBLE', '1', '2019-01-15 06:35:59', '1', '2019-01-15 06:35:59', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1085181767851188225', '菜单类型', 'MENU_TYPE', '1', '2019-01-15 08:27:50', '1', '2019-01-15 08:27:50', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1086942198961823745', '菜单状态', 'MENU_STATUS', '1', '2019-01-20 05:03:09', '1', '2019-01-20 05:03:09', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1088414522714132482', '用户状态', 'USER_STATUS', '1', '2019-01-24 06:33:39', '1', '2019-01-24 06:33:39', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1088418616384159745', '用户类型', 'USER_TYPE', '1', '2019-01-24 06:49:55', '1', '2019-01-24 06:49:55', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1096032224433672194', '公告类型', 'NOTIFY_GENRE', '1', '2019-02-14 13:03:40', '1', '2019-02-16 10:27:34', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1096335577848008706', '通知状态', 'NOTIFY_STATUS', '1', '2019-02-15 09:09:05', '1', '2019-02-16 10:31:33', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1097109241359011842', '来源渠道', 'CHANNEL', '1', '2019-02-17 12:23:21', '1', '2019-02-17 12:23:21', '0', '');
INSERT INTO `sys_dict_type` VALUES ('1097116460209164290', '请求功能', 'REQUEST_ACTION', '1', '2019-02-17 12:52:02', '1', '2019-02-17 12:52:02', '0', '');

-- ----------------------------
-- Table structure for `sys_dict_value`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value` (
  `id` bigint(50) NOT NULL COMMENT '主键',
  `dict_type` bigint(50) DEFAULT NULL COMMENT '所属字典类型(关联sys_dict_type表)',
  `dict_label` varchar(100) DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT NULL COMMENT '字典值',
  `sort` int(11) NOT NULL COMMENT '排序',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统字典数据';

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------
INSERT INTO `sys_dict_value` VALUES ('1', '1068147088417656833', '男', '0', '0', '1', '2018-10-03 23:31:19', '1', '2018-10-04 14:02:24', '0', null);
INSERT INTO `sys_dict_value` VALUES ('1069446852119703554', '1068147088417656833', '女', '1', '1', '1', '2018-12-02 22:22:54', '1', '2018-12-02 22:22:54', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071385137993748482', '1070172546462183426', '新增', '0', '0', '1', '2018-12-08 06:44:57', '1', '2018-12-08 06:44:57', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071385241551114242', '1070172546462183426', '修改', '1', '1', '1', '2018-12-08 06:45:22', '1', '2018-12-08 06:45:22', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071385311839260673', '1070172546462183426', '删除', '2', '2', '1', '2018-12-08 06:45:38', '1', '2018-12-08 06:45:38', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071385424473100290', '1070172546462183426', '导入', '3', '3', '1', '2018-12-08 06:46:05', '1', '2018-12-08 06:46:05', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071774159857532930', '1071771839543394306', '是', '0', '0', '1', '2018-12-09 08:30:47', '1', '2018-12-09 08:30:47', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071774274613690369', '1071771839543394306', '否', '1', '1', '1', '2018-12-09 08:31:14', '1', '2018-12-09 08:31:14', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071775002283491329', '1071774715300823041', '管理角色', '0', '0', '1', '2018-12-09 08:34:08', '1', '2018-12-09 08:34:08', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071775139474980865', '1071774715300823041', '普通角色', '1', '1', '1', '2018-12-09 08:34:41', '1', '2018-12-09 08:34:41', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071775830046162946', '1071775472771153921', '所有数据', '1', '1', '1', '2018-12-09 08:37:25', '1', '2018-12-09 08:37:25', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071775935029592066', '1071775472771153921', '所在公司及以下数据', '2', '2', '1', '2018-12-09 08:37:50', '1', '2018-12-09 08:37:50', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071775991287791618', '1071775472771153921', '所在公司数据', '3', '3', '1', '2018-12-09 08:38:04', '1', '2018-12-09 08:38:04', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071776155150860290', '1071775472771153921', '所在部门及以下数据', '4', '4', '1', '2018-12-09 08:38:43', '1', '2018-12-09 08:38:43', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071776241708711937', '1071775472771153921', '所在部门数据', '5', '5', '1', '2018-12-09 08:39:03', '1', '2018-12-09 08:39:03', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071776330414047234', '1071775472771153921', '仅本人数据', '8', '8', '1', '2018-12-09 08:39:24', '1', '2018-12-09 08:39:24', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1071776402845483010', '1071775472771153921', '按明细设置', '9', '9', '1', '2018-12-09 08:39:42', '1', '2018-12-09 08:39:42', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073081197791334401', '1073081042031661057', '正常', '0', '0', '1', '2018-12-12 23:04:29', '1', '2018-12-12 23:04:29', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073081320889962498', '1073081042031661057', '冻结', '1', '1', '1', '2018-12-12 23:04:58', '1', '2018-12-12 23:04:58', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073617139224662018', '1073615366678130690', '可用', '0', '0', '1', '2018-12-14 10:34:07', '1', '2018-12-14 10:34:07', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073617230429802498', '1073615366678130690', '不可用', '1', '1', '1', '2018-12-14 10:34:29', '1', '2018-12-14 10:34:29', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073931280174936066', '1073931106488807425', '是', '0', '0', '1', '2018-12-15 07:22:25', '1', '2018-12-15 07:22:25', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1073931393509224450', '1073931106488807425', '否', '1', '1', '1', '2018-12-15 07:22:52', '1', '2018-12-15 07:22:52', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1077887694027137026', '1077887529627197441', '省级', '0', '0', '1', '2018-12-26 05:23:47', '1', '2018-12-26 05:23:47', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1077887762868248578', '1077887529627197441', '地级', '1', '1', '1', '2018-12-26 05:24:04', '1', '2018-12-26 05:24:04', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1077888669383172097', '1077887529627197441', '县级', '3', '3', '1', '2018-12-26 05:27:40', '1', '2018-12-26 05:27:40', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1077888767919955970', '1077887529627197441', '乡级', '4', '4', '1', '2018-12-26 05:28:03', '1', '2018-12-26 05:28:03', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1079228262342836225', '1077912772638937089', '公司', '0', '0', '1', '2018-12-29 22:10:43', '1', '2018-12-29 22:10:43', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1079228607559221250', '1077912772638937089', '部门', '1', '1', '1', '2018-12-29 22:12:06', '1', '2018-12-29 22:12:06', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1079228690199592961', '1077912772638937089', '小组', '2', '2', '1', '2018-12-29 22:12:26', '1', '2018-12-29 22:12:26', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1079228755488129025', '1077912772638937089', '其他', '3', '3', '1', '2018-12-29 22:12:41', '1', '2018-12-29 22:12:41', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1085153745504833537', '1085153622473314306', '是', '0', '0', '1', '2019-01-15 06:36:29', '1', '2019-01-15 06:36:29', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1085153828908568578', '1085153622473314306', '否', '1', '1', '1', '2019-01-15 06:36:49', '1', '2019-01-15 06:36:49', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1085181901242638337', '1085181767851188225', '菜单', '1', '1', '1', '2019-01-15 08:28:22', '1', '2019-01-15 08:28:22', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1085182083476758529', '1085181767851188225', '按钮', '2', '2', '1', '2019-01-15 08:29:05', '1', '2019-01-15 08:29:05', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1085914234906816513', '1085181767851188225', '目录', '0', '0', '1', '2019-01-17 08:58:24', '1', '2019-01-17 08:58:24', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1086954335134400514', '1086942198961823745', '正常', '0', '0', '1', '2019-01-20 05:51:23', '1', '2019-01-20 05:51:23', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1086954404722098177', '1086942198961823745', '锁定', '1', '1', '1', '2019-01-20 05:51:39', '1', '2019-01-20 05:51:39', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088415042409332737', '1088414522714132482', '正常', '0', '0', '1', '2019-01-24 06:35:43', '1', '2019-01-24 06:35:43', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088415153608720385', '1088414522714132482', '禁用', '1', '1', '1', '2019-01-24 06:36:09', '1', '2019-01-24 06:36:09', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088415243643650049', '1088414522714132482', '锁定', '2', '2', '1', '2019-01-24 06:36:31', '1', '2019-01-24 06:36:31', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088419001983303682', '1088418616384159745', '系统管理', '0', '0', '1', '2019-01-24 06:51:27', '1', '2019-01-24 06:51:27', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088419088570515458', '1088418616384159745', '普通用户', '1', '1', '1', '2019-01-24 06:51:47', '1', '2019-01-24 06:51:47', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1088419214408024066', '1088418616384159745', '部门经理', '2', '2', '1', '2019-01-24 06:52:17', '1', '2019-01-24 06:52:17', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1089130065108729857', '1088418616384159745', '开发人员', '3', '3', '1', '2019-01-26 05:56:57', '1', '2019-01-26 05:56:57', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1096032443070156802', '1096032224433672194', '会议通告', '0', '0', '1', '2019-02-14 13:04:32', '1', '2019-02-14 13:04:32', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1096032491912826881', '1096032224433672194', '奖惩通告', '1', '1', '1', '2019-02-14 13:04:44', '1', '2019-02-14 13:04:44', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1096032559655030786', '1096032224433672194', '活动通告', '2', '2', '1', '2019-02-14 13:05:00', '1', '2019-02-14 13:05:00', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1096335664246476802', '1096335577848008706', '发布', '0', '0', '1', '2019-02-15 09:09:26', '1', '2019-02-15 09:09:26', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1096335709205221378', '1096335577848008706', '草稿', '1', '1', '1', '2019-02-15 09:09:36', '1', '2019-02-15 09:14:56', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097109329741385729', '1097109241359011842', '平台', '0', '0', '1', '2019-02-17 12:23:42', '1', '2019-02-17 12:23:42', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097109391326351361', '1097109241359011842', 'APP', '1', '1', '1', '2019-02-17 12:23:56', '1', '2019-02-17 12:23:56', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097109466958041089', '1097109241359011842', '其他', '2', '2', '1', '2019-02-17 12:24:14', '1', '2019-02-17 12:24:14', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116622549700610', '1097116460209164290', '新增', '0', '0', '1', '2019-02-17 12:52:41', '1', '2019-02-17 12:52:41', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116672914903041', '1097116460209164290', '修改', '1', '1', '1', '2019-02-17 12:52:53', '1', '2019-02-17 12:52:53', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116778347122689', '1097116460209164290', '新增或修改', '2', '2', '1', '2019-02-17 12:53:18', '1', '2019-02-17 12:53:18', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116851638390785', '1097116460209164290', '删除', '3', '3', '1', '2019-02-17 12:53:35', '1', '2019-02-17 12:53:35', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116922811535361', '1097116460209164290', '打开FORM', '4', '4', '1', '2019-02-17 12:53:52', '1', '2019-02-17 12:53:52', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097116988003602434', '1097116460209164290', '查看FORM', '5', '5', '1', '2019-02-17 12:54:08', '1', '2019-02-17 12:54:08', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097117092458549249', '1097116460209164290', '添加或查看FORM', '6', '6', '1', '2019-02-17 12:54:33', '1', '2019-02-17 12:54:33', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097117181063221250', '1097116460209164290', '查询LIST', '7', '7', '1', '2019-02-17 12:54:54', '1', '2019-02-17 12:54:54', '0', '');
INSERT INTO `sys_dict_value` VALUES ('1097117249677840385', '1097116460209164290', '查询PAGE', '8', '8', '1', '2019-02-17 12:55:10', '1', '2019-02-17 12:55:10', '0', '');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单 0为顶级目录',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '所有父级标签',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `font_color` varchar(20) DEFAULT NULL COMMENT '字体颜色',
  `url` varchar(1000) DEFAULT NULL COMMENT '地址',
  `menu_type` char(1) NOT NULL COMMENT '类型 0:目录1:菜单 2: 按钮',
  `permission` varchar(50) DEFAULT '' COMMENT '权限',
  `sort` int(11) NOT NULL COMMENT '排序',
  `target` varchar(20) DEFAULT NULL COMMENT '菜单目标',
  `menu_status` int(11) DEFAULT NULL COMMENT '菜单状态 0可用1不可用',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '添加时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(120) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_menu_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '53', null, '用户管理', 'fa  fa-users', '', 'user', '1', '', '1', null, '0', '0', '2018-07-01 16:02:37', '0', '2019-02-09 19:32:17', '0', null);
INSERT INTO `sys_menu` VALUES ('7', '0', '0', '系统管理', 'fa  fa-gears', '#dddddd', '', '0', '', '2', null, '0', '0', '2018-07-01 16:02:37', '1', '2019-02-14 12:01:37', '0', '');
INSERT INTO `sys_menu` VALUES ('8', '7', '0,7', '菜单管理', 'fa  fa-cog', '#dddddd', 'menu', '1', 'manage:menu:index', '6', null, '0', '0', '2018-07-01 16:02:37', '1', '2019-02-25 01:58:32', '0', '');
INSERT INTO `sys_menu` VALUES ('9', '8', '0', '查询', '', '#dddddd', '', '2', 'manage:menu:query', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2018-11-01 22:56:39', '0', '');
INSERT INTO `sys_menu` VALUES ('10', '8', null, '新增', '', '', '', '2', 'manage:menu:add', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:24:43', '0', null);
INSERT INTO `sys_menu` VALUES ('11', '8', null, '删除', '', '', '', '2', 'manage:menu:del', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:24:44', '0', null);
INSERT INTO `sys_menu` VALUES ('12', '53', null, '角色管理', 'fa fa-user-secret', '', 'role', '1', '', '7', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:18', '0', null);
INSERT INTO `sys_menu` VALUES ('13', '12', null, '查询', '', '', '', '2', 'manage:role:query', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:24:45', '0', null);
INSERT INTO `sys_menu` VALUES ('14', '12', null, '新增', '', '', '', '2', 'manage:role:add', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:24:44', '0', null);
INSERT INTO `sys_menu` VALUES ('15', '12', null, '删除', '', '', '', '2', 'manage:role:del', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:24:44', '0', null);
INSERT INTO `sys_menu` VALUES ('16', '0', '0', '文件管理', 'fa fa-folder-open', '#dddddd', 'file', '1', '', '8', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:20', '0', '');
INSERT INTO `sys_menu` VALUES ('17', '16', null, '查询', '', '', '', '2', 'manage:file:query', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:25:16', '0', null);
INSERT INTO `sys_menu` VALUES ('18', '16', null, '删除', '', '', '', '2', 'manage:file:del', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:25:15', '0', null);
INSERT INTO `sys_menu` VALUES ('19', '0', null, '系统监控', 'fa  fa-eye', '', '', '0', '', '9', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:18', '0', null);
INSERT INTO `sys_menu` VALUES ('20', '44', null, '接口swagger', 'fa fa-file-pdf-o', '', 'swagger-ui.html', '1', '', '10', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:17', '0', null);
INSERT INTO `sys_menu` VALUES ('22', '0', '0', '多级菜单', 'fa fa-bars', '#dddddd', '', '0', '', '12', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:20', '0', '');
INSERT INTO `sys_menu` VALUES ('26', '0', '0', '日志管理', 'fa  fa-file-text-o', '#dddddd', '', '0', 'manage:log:query', '13', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-25 01:59:16', '0', '');
INSERT INTO `sys_menu` VALUES ('27', '0', '0', '邮件管理', 'fa  fa-envelope', '#dddddd', 'pages/mail/mailList.html', '0', '', '14', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:20', '0', '');
INSERT INTO `sys_menu` VALUES ('28', '7', '0', '发送邮件', '', '#dddddd', '', '2', 'manage:mail:send', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2018-08-04 19:06:30', '0', '');
INSERT INTO `sys_menu` VALUES ('29', '27', '0', '查询', '', '', '', '2', 'manage:mail:all:query', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:13', '0', '');
INSERT INTO `sys_menu` VALUES ('30', '0', '0', '定时任务管理', 'fa fa-tasks', '#dddddd', 'pages/job/jobList.html', '0', '', '15', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:20', '0', '');
INSERT INTO `sys_menu` VALUES ('31', '30', '0', '查询', '', '', '', '2', 'manage:job:query', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:09', '0', '');
INSERT INTO `sys_menu` VALUES ('32', '30', '0', '新增', '', '', '', '2', 'manage:job:add', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:13', '0', '');
INSERT INTO `sys_menu` VALUES ('33', '30', '0', '删除', '', '', '', '2', 'manage:job:del', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:09', '0', '');
INSERT INTO `sys_menu` VALUES ('37', '7', '0,7', '字典管理', 'fa fa-reddit', '#dddddd', 'dict/type', '0', 'manage:dict:index', '17', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-25 01:58:47', '0', '');
INSERT INTO `sys_menu` VALUES ('38', '37', '0', '查询', '', '', '', '2', 'manage:dict:query', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:09', '0', '');
INSERT INTO `sys_menu` VALUES ('39', '37', '0', '新增', '', '', '', '2', 'manage:dict:add', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:09', '0', '');
INSERT INTO `sys_menu` VALUES ('40', '37', '0', '删除', '', '', '', '2', 'manage:dict:del', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-07 22:25:10', '0', '');
INSERT INTO `sys_menu` VALUES ('41', '22', null, '一级菜单', 'fa  fa-circle-o', '', '', '1', '', '12', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:20', '0', null);
INSERT INTO `sys_menu` VALUES ('42', '41', null, '二级菜单', 'fa  fa-circle-o', '', '', '1', '', '12', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:19', '0', null);
INSERT INTO `sys_menu` VALUES ('43', '42', '0', '三级菜单（一）', 'fa  fa-circle-o', '#dddddd', '', '1', '', '12', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:18', '0', '');
INSERT INTO `sys_menu` VALUES ('44', '0', '0', '开发工具', 'fa  fa-circle-o text-aqua', '#dddddd', '', '0', '', '10', null, '1', '0', '2018-07-01 16:02:54', '1', '2019-02-09 19:32:18', '0', '');
INSERT INTO `sys_menu` VALUES ('46', '8', '0', '修改', '', '#dddddd', '', '2', 'manage:menu:edit', '10', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-08 08:21:42', '0', '');
INSERT INTO `sys_menu` VALUES ('47', '1', '0', '查询', '', '#dddddd', '', '2', 'manage:user:query', '100', null, '0', '0', '2018-07-01 16:02:54', '1', '2019-02-08 03:58:10', '0', '');
INSERT INTO `sys_menu` VALUES ('48', '1', null, '新增', '', '', '', '2', 'manage:user:add', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:25:14', '0', null);
INSERT INTO `sys_menu` VALUES ('49', '1', null, '删除', '', '', '', '2', 'manage:user:del', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-07 22:25:14', '0', null);
INSERT INTO `sys_menu` VALUES ('50', '1', '0', '修改', '', '#dddddd', '', '2', 'manage:user:edit', '10', null, '0', '0', '2018-07-01 16:02:54', '1', '2018-11-01 22:55:59', '0', '');
INSERT INTO `sys_menu` VALUES ('51', '44', null, '表单配置', 'fa  fa-file-pdf-o', '', 'modules/gen/list.html', '1', '', '100', null, '0', '0', '2018-07-01 16:02:54', '0', '2019-02-09 19:32:20', '0', null);
INSERT INTO `sys_menu` VALUES ('52', '53', null, '部门管理', 'fa  fa-file-code-o', '', 'org', '0', '', '5', null, '0', '0', '2018-07-01 16:02:37', '0', '2019-02-09 19:32:19', '0', null);
INSERT INTO `sys_menu` VALUES ('53', '0', '0', '组织架构', 'fa  fa-sitemap', '#dddddd', '', '0', '', '2', null, '0', '0', '2018-07-01 16:02:37', '1', '2019-02-09 19:32:19', '0', '');
INSERT INTO `sys_menu` VALUES ('54', '19', null, '在线用户', 'fa   fa-users', '', 'monitor/online/user/view', '1', '', '101', null, '0', '0', '2019-01-11 12:22:32', '0', '2019-02-09 19:32:18', '0', null);
INSERT INTO `sys_menu` VALUES ('1086992901067558913', '52', '0', '新增', null, '#dddddd', null, '2', 'manage:org:add', '0', null, '0', '1', '2019-01-20 08:24:38', '1', '2019-01-20 08:24:38', '0', '');
INSERT INTO `sys_menu` VALUES ('1086993134316998658', '52', '0', '修改', null, '#dddddd', null, '2', 'manage:org:edit', '9999', null, '0', '1', '2019-01-20 08:25:33', '1', '2019-02-08 08:22:06', '0', '');
INSERT INTO `sys_menu` VALUES ('1087347698790576129', '16', '0', '添加', null, '#dddddd', null, '2', 'manage:file:add', '7', null, '0', '1', '2019-01-21 07:54:28', '1', '2019-01-21 07:54:28', '0', '');
INSERT INTO `sys_menu` VALUES ('1087724118092132353', '52', '0', '删除', null, '#dddddd', null, '2', 'manage:org:del', '0', null, '0', '1', '2019-01-22 08:50:13', '1', '2019-01-22 08:50:13', '0', '');
INSERT INTO `sys_menu` VALUES ('1089503509683933186', '26', '0,26', '登录日志', null, '#ffffff', 'log/login', '0', '', '0', null, '1', '1', '2019-01-27 06:40:53', '1', '2019-02-17 12:15:51', '0', '');
INSERT INTO `sys_menu` VALUES ('1089503626952478722', '26', '0,26', '操作日志', null, '#ffffff', 'log/open', '0', 'manage:log:operate:index', '0', null, '0', '1', '2019-01-27 06:41:21', '1', '2019-02-25 01:59:50', '0', '');
INSERT INTO `sys_menu` VALUES ('1092619492460130305', '42', '0', '三级菜单（二）', 'fa   fa-circle-o', '#dddddd', null, '1', '', '200', null, '0', '1', '2019-02-04 21:02:42', '1', '2019-02-09 19:32:21', '0', '');
INSERT INTO `sys_menu` VALUES ('1093810117331148802', '37', '0', '修改', null, '#dddddd', null, '2', 'manage:dict:edit', '0', null, '0', '1', '2019-02-08 03:53:49', '1', '2019-02-08 08:21:22', '0', '');
INSERT INTO `sys_menu` VALUES ('1093811378357035010', '52', '0', '查询', null, '#dddddd', null, '2', 'manage:org:query', '0', null, '0', '1', '2019-02-08 03:58:49', '1', '2019-02-08 03:58:49', '0', '');
INSERT INTO `sys_menu` VALUES ('1093811581130661889', '12', '0', '修改', null, '#dddddd', null, '2', 'manage:role:edit', '0', null, '0', '1', '2019-02-08 03:59:38', '1', '2019-02-08 04:12:14', '0', '');
INSERT INTO `sys_menu` VALUES ('1096016383835123713', '0', '0', '通知公告', 'fa fa-commenting-o', '#dddddd', 'notify', '1', 'manage:notify:index', '0', null, '1', '1', '2019-02-14 12:00:43', '1', '2019-02-25 02:41:44', '0', '');
INSERT INTO `sys_menu` VALUES ('1096017410365861889', '1096016383835123713', '0,1096016383835123713', '新增', null, '#dddddd', null, '2', 'manage:notify:add', '99999', null, '0', '1', '2019-02-14 12:04:48', '1', '2019-02-14 12:04:48', '0', '');
INSERT INTO `sys_menu` VALUES ('1096017504527986689', '1096016383835123713', '0,1096016383835123713', '修改', null, '#dddddd', null, '2', 'manage:notify:edit', '99999', null, '0', '1', '2019-02-14 12:05:10', '1', '2019-02-14 12:05:10', '0', '');
INSERT INTO `sys_menu` VALUES ('1096017585767460866', '1096016383835123713', '0,1096016383835123713', '删除', null, '#dddddd', null, '2', 'manage:notify:del', '99999', null, '0', '1', '2019-02-14 12:05:30', '1', '2019-02-14 12:05:30', '0', '');
INSERT INTO `sys_menu` VALUES ('1096017694378962946', '1096016383835123713', '0,1096016383835123713', '查询', null, '#dddddd', null, '2', 'manage:notify:query', '99999', null, '0', '1', '2019-02-14 12:05:56', '1', '2019-02-14 12:05:56', '0', '');
INSERT INTO `sys_menu` VALUES ('1096724847318376449', '19', 'null,19', '操作日志', 'fa fa-file-text-o', '#dddddd', 'log/operate', '1', '', '99999', null, '0', '1', '2019-02-16 10:55:54', '1', '2019-02-16 10:55:54', '0', '');
INSERT INTO `sys_menu` VALUES ('1097107526685589506', '1089503626952478722', '0,1089503626952478722', '删除', null, '#dddddd', null, '2', 'manage:log:operate:del', '9999', null, '0', '1', '2019-02-17 12:16:32', '1', '2019-02-17 12:16:32', '0', '');
INSERT INTO `sys_menu` VALUES ('1097107978806394881', '1089503626952478722', '0,1089503626952478722', '查询', null, '#dddddd', null, '2', 'manage:log:operate:query', '9999', null, '0', '1', '2019-02-17 12:18:20', '1', '2019-02-17 12:18:20', '0', '');

-- ----------------------------
-- Table structure for `sys_notify`
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify`;
CREATE TABLE `sys_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `notify_genre` char(1) DEFAULT NULL COMMENT '类型',
  `notify_status` char(1) DEFAULT NULL COMMENT '状态',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='通知通告';

-- ----------------------------
-- Records of sys_notify
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_notify_record`
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify_record`;
CREATE TABLE `sys_notify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `notify_id` bigint(20) DEFAULT NULL COMMENT '通知通告',
  `user_id` bigint(20) DEFAULT NULL COMMENT '接受人',
  `read_flag` char(1) DEFAULT '0' COMMENT '阅读标记 1未阅读，0阅读',
  `read_date` datetime DEFAULT NULL COMMENT '阅读时间',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` longtext DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='通知通告发送记录';

-- ----------------------------
-- Records of sys_notify_record
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` bigint(11) NOT NULL COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `action` varchar(100) DEFAULT '' COMMENT '功能请求',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operate_param` longtext DEFAULT NULL COMMENT '请求参数',
  `channel` char(1) DEFAULT '' COMMENT '来源渠道（0 后台用户 1 手机端用户 2其它）',
  `operate_user` varchar(50) DEFAULT '' COMMENT '操作人员',
  `operate_org` varchar(50) DEFAULT '' COMMENT '部门名称',
  `operate_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `operate_ip` varchar(30) DEFAULT '' COMMENT '主机地址',
  `operate_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `operate_status` varchar(20) NOT NULL DEFAULT '0' COMMENT '操作状态',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '所有父级编号',
  `org_name` varchar(100) NOT NULL COMMENT '机构名称',
  `org_code` varchar(20) NOT NULL COMMENT '机构编码',
  `area_id` bigint(20) DEFAULT NULL COMMENT '归属区域',
  `org_type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `longitude` decimal(65,0) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(65,0) DEFAULT NULL COMMENT '纬度',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `principal` varchar(100) DEFAULT NULL COMMENT '负责人',
  `telephone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(30) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_del_flag` (`is_deleted`) USING BTREE,
  KEY `sys_office_type` (`org_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', '0', '山东-济南', 'A000', '1', '0', '0', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-02-15 10:50:32', '0', '');
INSERT INTO `sys_org` VALUES ('231112', '1', '0,1', '商务部', 'A001', '1', '1', '1', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-02-07 08:52:53', '0', '');
INSERT INTO `sys_org` VALUES ('232399', '1', '0,1', '行政部', 'A002', '1', '1', '1', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-01-31 09:59:48', '0', '');
INSERT INTO `sys_org` VALUES ('233334', '1', '0,1', '产品体验部', 'A003', '1', '1', '1', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-01-31 09:59:47', '0', '');
INSERT INTO `sys_org` VALUES ('300003', '1', '0,1', '技术部', 'A004', '1', '1', '1', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-01-31 09:59:47', '0', '');
INSERT INTO `sys_org` VALUES ('465723', '1', '0,1', '招商部', 'A005', '1', '1', '1', null, '1', '', '', '', '', '', '', '0', '1', '2018-08-04 14:47:22', '1', '2019-01-31 09:59:47', '0', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `en_name` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `data_scope` int(11) DEFAULT NULL COMMENT '数据范围',
  `role_type` varchar(100) DEFAULT NULL COMMENT '角色类型',
  `useable` varchar(100) DEFAULT NULL COMMENT '是否可用',
  `sys_data` bigint(255) DEFAULT NULL COMMENT '系统数据',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL COMMENT '数据状态:1:删除 0:未删除',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1084827767704403971 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'SYSTEM', '2', '0', '0', '1', '0', '2018-07-01 15:17:40', '1', '2019-02-08 04:20:32', '0', '');
INSERT INTO `sys_role` VALUES ('2', '系统用户', 'USER', '8', '1', '0', '1', '0', '2018-07-01 15:17:40', '1', '2019-02-09 04:56:07', '0', '');
INSERT INTO `sys_role` VALUES ('1073957301490032642', '超级管理员', 'ROOT', '1', '0', '0', '0', '1', '2018-12-15 09:05:48', '1', '2019-02-17 12:18:38', '0', '');
INSERT INTO `sys_role` VALUES ('1077874980521287682', '开发人员', 'KF', '9', '1', '1', '1', '1', '2018-12-26 04:33:16', '1', '2018-12-26 04:33:16', '0', '');
INSERT INTO `sys_role` VALUES ('1084827767704403970', '开发公司', 'KF', '3', '1', '1', '1', '1', '2019-01-14 09:01:10', '1', '2019-01-14 09:01:10', '1', '');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '关联菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '16');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '18');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '26');
INSERT INTO `sys_role_menu` VALUES ('1', '27');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '29');
INSERT INTO `sys_role_menu` VALUES ('1', '30');
INSERT INTO `sys_role_menu` VALUES ('1', '31');
INSERT INTO `sys_role_menu` VALUES ('1', '32');
INSERT INTO `sys_role_menu` VALUES ('1', '33');
INSERT INTO `sys_role_menu` VALUES ('1', '34');
INSERT INTO `sys_role_menu` VALUES ('1', '35');
INSERT INTO `sys_role_menu` VALUES ('1', '36');
INSERT INTO `sys_role_menu` VALUES ('1', '37');
INSERT INTO `sys_role_menu` VALUES ('1', '38');
INSERT INTO `sys_role_menu` VALUES ('1', '39');
INSERT INTO `sys_role_menu` VALUES ('1', '40');
INSERT INTO `sys_role_menu` VALUES ('1', '41');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '43');
INSERT INTO `sys_role_menu` VALUES ('1', '44');
INSERT INTO `sys_role_menu` VALUES ('1', '45');
INSERT INTO `sys_role_menu` VALUES ('1', '46');
INSERT INTO `sys_role_menu` VALUES ('1', '47');
INSERT INTO `sys_role_menu` VALUES ('1', '48');
INSERT INTO `sys_role_menu` VALUES ('1', '49');
INSERT INTO `sys_role_menu` VALUES ('1', '50');
INSERT INTO `sys_role_menu` VALUES ('1', '51');
INSERT INTO `sys_role_menu` VALUES ('1', '52');
INSERT INTO `sys_role_menu` VALUES ('1', '53');
INSERT INTO `sys_role_menu` VALUES ('1', '54');
INSERT INTO `sys_role_menu` VALUES ('1', '1086992901067558913');
INSERT INTO `sys_role_menu` VALUES ('1', '1086993134316998658');
INSERT INTO `sys_role_menu` VALUES ('1', '1087347698790576129');
INSERT INTO `sys_role_menu` VALUES ('1', '1087724118092132353');
INSERT INTO `sys_role_menu` VALUES ('1', '1089503509683933186');
INSERT INTO `sys_role_menu` VALUES ('1', '1089503626952478722');
INSERT INTO `sys_role_menu` VALUES ('1', '1092619492460130305');
INSERT INTO `sys_role_menu` VALUES ('1', '1093810117331148802');
INSERT INTO `sys_role_menu` VALUES ('1', '1093811378357035010');
INSERT INTO `sys_role_menu` VALUES ('1', '1093811581130661889');
INSERT INTO `sys_role_menu` VALUES ('2', '45');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '7');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '8');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '9');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '10');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '11');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '12');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '13');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '14');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '15');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '17');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '18');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '19');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '20');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '26');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '28');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '29');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '31');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '32');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '33');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '37');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '38');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '39');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '40');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '41');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '42');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '43');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '46');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '47');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '48');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '49');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '50');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '51');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '52');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '53');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '54');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1086992901067558913');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1086993134316998658');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1087347698790576129');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1087724118092132353');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1089503626952478722');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1092619492460130305');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1093810117331148802');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1093811378357035010');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1093811581130661889');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096016383835123713');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096017410365861889');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096017504527986689');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096017585767460866');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096017694378962946');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1096724847318376449');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1097107526685589506');
INSERT INTO `sys_role_menu` VALUES ('1073957301490032642', '1097107978806394881');
INSERT INTO `sys_role_menu` VALUES ('1076743671619022850', '45');
INSERT INTO `sys_role_menu` VALUES ('1076834908820762626', '20');
INSERT INTO `sys_role_menu` VALUES ('1076834908820762626', '44');
INSERT INTO `sys_role_menu` VALUES ('1076834908820762626', '51');
INSERT INTO `sys_role_menu` VALUES ('1077874980521287682', '20');
INSERT INTO `sys_role_menu` VALUES ('1077874980521287682', '44');
INSERT INTO `sys_role_menu` VALUES ('1077874980521287682', '51');
INSERT INTO `sys_role_menu` VALUES ('1084827767704403970', '20');
INSERT INTO `sys_role_menu` VALUES ('1084827767704403970', '44');
INSERT INTO `sys_role_menu` VALUES ('1084827767704403970', '51');

-- ----------------------------
-- Table structure for `sys_role_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org`;
CREATE TABLE `sys_role_org` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织',
  UNIQUE KEY `sys_role_org_pk` (`role_id`,`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色-组织';

-- ----------------------------
-- Records of sys_role_org
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL COMMENT '姓名',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `job_number` varchar(20) DEFAULT NULL COMMENT '工号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `head_portrait_url` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '上次登录ip',
  `last_login_address` varchar(50) DEFAULT NULL COMMENT '上次登录地址',
  `last_login_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '最后登录日期',
  `user_status` varchar(10) NOT NULL COMMENT '用户状态  0：正常   1：禁用 2：锁定',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型',
  `org_id` mediumtext DEFAULT NULL COMMENT '用户归属组织',
  `creator` bigint(20) NOT NULL COMMENT '创建者ID',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `editor` bigint(20) NOT NULL COMMENT '修改者ID',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `is_deleted` char(1) NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`,`gmt_modified`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1088787447501930498 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'admin', '$2a$10$K26/8HAGoW0jFe7Tofr8e.hT7/P42ZQLuHMlenOsGkl.m9Zt6Vpby', '管理员', 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_family.jpg', '15621346836', '', '2689170096@qq.com', '2018-05-24', '', null, null, '0:0:0:0:0:0:0:1', '本地', '2019-02-25 02:41:57', '0', '0', '232399', '1', '2018-06-18 10:50:02', '1', '2019-02-23 09:38:53', '0', '');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', 'user', '$2a$10$W7c1gJyPxf9Rtp3/G5WLre6vJomdzaSqEDt2/jJa70A6IHTetRkoO', '管理员', 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_colleagues.jpg', '', '', '', '1998-06-15', null, null, null, '0:0:0:0:0:0:0:1', '本地', '2019-02-25 02:37:31', '0', '1', '231112', '1', '2018-06-18 10:50:02', '1', '2019-02-09 18:59:04', '0', '');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1073957301490032642');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('1088787447501930497', '1077874980521287682');
