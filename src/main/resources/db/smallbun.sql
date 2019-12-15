/*
 Navicat Premium Data Transfer

 Source Server         : 个人腾讯云
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-2ipogt4w.cd.tencentcdb.com:10008
 Source Schema         : smallbun

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 15/12/2019 14:48:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_authority_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority_type`;
CREATE TABLE `sys_authority_type`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称',
  `code_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编码',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_authority_type_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_authority_type_name__index`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authority_type
-- ----------------------------
INSERT INTO `sys_authority_type` VALUES ('1192399973845667843', '系统用户', 'user', '5', '2019-08-11 03:14:42', '5', '2019-11-12 05:43:32', NULL, '0', '系统用户相关');
INSERT INTO `sys_authority_type` VALUES ('1192400131941568514', '系统角色', 'role', '5', '2019-08-11 03:15:19', '5', '2019-11-12 05:43:22', NULL, '0', '系统角色相关');
INSERT INTO `sys_authority_type` VALUES ('1192400302410665986', '组织机构', 'group', '5', '2019-08-11 03:16:00', '5', '2019-11-12 05:43:44', NULL, '0', '组织机构相关');
INSERT INTO `sys_authority_type` VALUES ('1192400391623512066', '数据字典', 'dict', '5', '2019-08-11 03:16:21', '5', '2019-11-12 05:43:53', NULL, '0', '数据字典相关');
INSERT INTO `sys_authority_type` VALUES ('1192400462851182594', '系统日志', 'logger', '5', '2019-08-11 03:16:38', '5', '2019-11-12 05:44:02', NULL, '0', '系统日志相关');
INSERT INTO `sys_authority_type` VALUES ('1193914893898649601', '系统权限', 'authority', '5', '2019-11-12 07:34:27', '5', '2019-12-12 06:28:32', NULL, '0', '系统权限相关');

-- ----------------------------
-- Table structure for sys_authorize_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorize_item`;
CREATE TABLE `sys_authorize_item`  (
  `id_` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authorize_` bigint(20) NULL DEFAULT NULL COMMENT '归属权限',
  `name_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `permission_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权标识',
  `status_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态  0：正常 1：禁用',
  `type_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型  1：路由 2：操作 3：接口',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_authorize_item_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_authorize_item_name__index`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authorize_item
-- ----------------------------
INSERT INTO `sys_authorize_item` VALUES ('1193107063383826434', 1192400462851182594, '系统日志', 'manage:route:logger', '0', '0', '5', '2019-11-10 02:04:25', '5', '2019-11-22 07:27:46', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193107366220963842', 1192400462851182594, '清空日志', 'manage:logger:remove', '0', '1', '5', '2019-11-10 02:05:37', '5', '2019-12-13 07:32:03', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193115438112907265', 1192400391623512066, '数据字典', 'manage:route:dict', '0', '0', '5', '2019-11-10 02:37:42', '5', '2019-11-10 02:42:45', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193116297622265857', 1192400391623512066, '新增数据字典', 'manage:operate:dict:add', '0', '1', '5', '2019-11-10 02:41:07', '5', '2019-11-12 05:36:49', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193116382942797827', 1192400391623512066, '修改数据字典', 'manage:operate:dict:update', '0', '1', '5', '2019-11-10 02:41:27', '5', '2019-11-12 05:37:04', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193116458809368577', 1192400391623512066, '删除数据字典', 'manage:operate:dict:remove', '0', '1', '5', '2019-11-10 02:41:45', '5', '2019-11-12 05:37:17', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193117531762663425', 1192400302410665986, '组织机构', 'manage:route:group', '0', '0', '5', '2019-11-10 02:46:01', '5', '2019-11-12 05:50:34', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193880704205778945', 1192400131941568514, '系统角色', 'manage:route:role', '0', '0', '5', '2019-11-12 05:18:35', '5', '2019-11-12 05:20:58', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193880832077524995', 1192400131941568514, '新增角色', 'manage:operate:role:add', '0', '1', '5', '2019-11-12 05:19:06', '5', '2019-11-12 05:21:07', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193880891930243075', 1192400131941568514, '修改角色', 'manage:operate:role:update', '0', '1', '5', '2019-11-12 05:19:20', '5', '2019-11-12 05:21:16', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193880944195465218', 1192400131941568514, '删除角色', 'manage:operate:role:remove', '0', '1', '5', '2019-11-12 05:19:32', '5', '2019-11-12 05:21:27', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193888487173058563', 1192400302410665986, '增加机构', 'manage:operate:group:add', '0', '1', '5', '2019-11-12 05:49:31', '5', '2019-11-12 05:49:31', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193888525504802817', 1192400302410665986, '修改机构', 'manage:operate:group:update', '0', '1', '5', '2019-11-12 05:49:40', '5', '2019-11-12 05:49:40', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193888597751689219', 1192400302410665986, '删除机构', 'manage:operate:group:remove', '0', '1', '5', '2019-11-12 05:49:57', '5', '2019-11-12 05:49:57', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193889489490079745', 1192399973845667843, '系统用户', 'manage:route:user', '0', '0', '5', '2019-11-12 05:53:30', '5', '2019-11-22 07:15:04', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193889716552921089', 1192399973845667843, '添加用户', 'manage:operate:user:add', '0', '1', '5', '2019-11-12 05:54:24', '5', '2019-11-12 05:54:24', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193889767253667841', 1192399973845667843, '修改用户', 'manage:operate:user:update', '0', '1', '5', '2019-11-12 05:54:36', '5', '2019-11-12 05:54:36', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193889806730457089', 1192399973845667843, '删除用户', 'manage:operate:user:remove', '0', '1', '5', '2019-11-12 05:54:45', '5', '2019-11-12 05:54:45', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193915780788424705', 1193914893898649601, '新增权限', 'manage:operate:authority:add', '0', '1', '5', '2019-11-12 07:37:58', '5', '2019-12-10 07:05:22', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193915835016581121', 1193914893898649601, '修改权限', 'manage:operate:authority:update', '0', '1', '5', '2019-11-12 07:38:11', '5', '2019-12-10 07:22:43', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193915886388416513', 1193914893898649601, '删除权限', 'manage:operate:authority:remove', '0', '1', '5', '2019-11-12 07:38:23', '5', '2019-12-10 07:05:24', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1193916078747586561', 1193914893898649601, '系统权限', 'manage:route:authority', '0', '0', '5', '2019-11-12 07:39:09', '5', '2019-12-12 06:39:22', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1194234153483161601', 1193914893898649601, '新增权限', 'manage:interface:authority:add', '0', '2', '5', '2019-11-13 04:43:04', '5', '2019-11-22 07:27:34', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1194234858356920322', 1193914893898649601, '查询权限', 'manage:interface:authority:fetch', '0', '2', '5', '2019-11-13 04:45:52', '5', '2019-11-22 07:27:35', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1194235023868350465', 1193914893898649601, '修改权限', 'manage:interface:authority:update', '0', '2', '5', '2019-11-13 04:46:32', '5', '2019-11-22 07:27:37', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1194235152872558594', 1193914893898649601, '删除权限', 'manage:interface:authority:remove', '0', '2', '5', '2019-11-13 04:47:02', '5', '2019-11-23 06:12:38', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1194235306329559042', 1193914893898649601, '唯一验证', 'manage:interface:authority:unique', '0', '2', '5', '2019-11-13 04:47:39', '5', '2019-11-22 07:27:39', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195375746164584449', 1192400131941568514, '权限配置', 'manage:operate:role:config', '0', '1', '5', '2019-11-16 08:19:21', '5', '2019-11-16 08:19:21', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195616924717817858', 1192400462851182594, '新增操作日志', 'manage:interface:logger:operate:add', '0', '2', '5', '2019-11-17 00:17:42', '5', '2019-12-12 06:39:48', NULL, '1', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195617002710900737', 1192400462851182594, '清空操作日志', 'manage:interface:logger:operate:remove', '0', '2', '5', '2019-11-17 00:18:01', '5', '2019-11-22 07:27:51', NULL, '1', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195617104733151234', 1192400462851182594, '查询操作信息', 'manage:interface:logger:operate:fetch', '0', '2', '5', '2019-11-17 00:18:25', '5', '2019-11-22 07:27:52', NULL, '1', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195617340981518338', 1192400462851182594, '新增登录日志', 'manage:interface:logger:login:add', '0', '2', '5', '2019-11-17 00:19:22', '5', '2019-11-22 07:27:53', NULL, '1', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195617416697094146', 1192400462851182594, '清空日志', 'manage:interface:logger:remove', '0', '2', '5', '2019-11-17 00:19:40', '5', '2019-12-13 07:32:51', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195617482002407426', 1192400462851182594, '查询日志', 'manage:interface:logger:fetch', '0', '2', '5', '2019-11-17 00:19:55', '5', '2019-12-14 21:09:32', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195632558713348097', 1192400391623512066, '新增字典', 'manage:interface:dict:add', '0', '2', '5', '2019-11-17 01:19:50', '5', '2019-11-17 22:41:55', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195633144728920066', 1192400391623512066, '修改字典', 'manage:interface:dict:update', '0', '2', '5', '2019-11-17 01:22:10', '5', '2019-11-17 22:42:14', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195633229206396930', 1192400391623512066, '删除字典', 'manage:interface:dict:remove', '0', '2', '5', '2019-11-17 01:22:30', '5', '2019-11-17 22:42:18', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195633358361600002', 1192400391623512066, '查询字典', 'manage:interface:dict:fetch', '0', '2', '5', '2019-11-17 01:23:01', '5', '2019-11-17 22:42:25', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195633485197352961', 1192400391623512066, '唯一校验', 'manage:interface:dict:unique', '0', '2', '5', '2019-11-17 01:23:31', '5', '2019-11-17 22:42:55', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195634281351749634', 1192400302410665986, '新增机构', 'manage:interface:group:add', '0', '2', '5', '2019-11-17 01:26:41', '5', '2019-11-17 02:13:20', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195635597708894210', 1192400302410665986, '修改机构', 'manage:interface:group:update', '0', '2', '5', '2019-11-17 01:31:54', '5', '2019-11-17 02:13:24', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195635765351030786', 1192400302410665986, '删除机构', 'manage:interface:group:remove', '0', '2', '5', '2019-11-17 01:32:34', '5', '2019-11-17 02:13:28', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195635821823139842', 1192400302410665986, '查询机构', 'manage:interface:group:fetch', '0', '2', '5', '2019-11-17 01:32:48', '5', '2019-11-17 02:13:33', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195635999749709825', 1192400302410665986, '唯一验证', 'manage:interface:group:unique', '0', '2', '5', '2019-11-17 01:33:30', '5', '2019-11-17 22:43:23', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636256315285506', 1192399973845667843, '新增用户', 'manage:interface:user:add', '0', '2', '5', '2019-11-17 01:34:32', '5', '2019-11-17 02:13:50', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636315450777602', 1192399973845667843, '修改用户', 'manage:interface:user:update', '0', '2', '5', '2019-11-17 01:34:46', '5', '2019-11-17 02:13:55', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636369423081474', 1192399973845667843, '删除用户', 'manage:interface:user:remove', '0', '2', '5', '2019-11-17 01:34:58', '5', '2019-11-17 02:14:01', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636434325741569', 1192399973845667843, '查询用户', 'manage:interface:user:fetch', '0', '2', '5', '2019-11-17 01:35:14', '5', '2019-11-17 02:14:06', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636517482012674', 1192399973845667843, '用户唯一验证', 'manage:interface:user:unique', '0', '2', '5', '2019-11-17 01:35:34', '5', '2019-11-17 02:14:10', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636653595566082', 1192400131941568514, '新增角色', 'manage:interface:role:add', '0', '2', '5', '2019-11-17 01:36:06', '5', '2019-11-17 02:14:37', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636710638100481', 1192400131941568514, '查询角色', 'manage:interface:role:fetch', '0', '2', '5', '2019-11-17 01:36:20', '5', '2019-12-01 05:35:08', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636765709312001', 1192400131941568514, '更新角色', 'manage:interface:role:update', '0', '2', '5', '2019-11-17 01:36:33', '5', '2019-12-01 05:35:25', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636827910840322', 1192400131941568514, '删除角色', 'manage:interface:role:remove', '0', '2', '5', '2019-11-17 01:36:48', '5', '2019-12-01 05:35:32', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195636915223666689', 1192400131941568514, '唯一验证', 'manage:interface:role:unique', '0', '2', '5', '2019-11-17 01:37:09', '5', '2019-11-17 22:43:08', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195637038930468865', 1192400131941568514, '权限配置', 'manage:interface:role:authorize', '0', '2', '5', '2019-11-17 01:37:38', '5', '2019-11-17 22:43:13', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195736038765535233', 1193914893898649601, '配置权限项', 'manage:operate:authority:config', '0', '1', '5', '2019-11-17 08:11:02', '5', '2019-12-10 07:05:25', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195736297503760386', 1192400391623512066, '字典项', 'manage:operate:dict:item', '0', '1', '5', '2019-11-17 08:12:03', '5', '2019-11-17 08:12:15', NULL, '0', NULL);
INSERT INTO `sys_authorize_item` VALUES ('1195736559408685057', 1192399973845667843, '用户详情', 'manage:operate:user:detail', '0', '1', '5', '2019-11-17 08:13:06', '5', '2019-11-17 08:13:06', NULL, '0', NULL);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `key_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数键',
  `value_` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数值',
  `status_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态  0：正常   1：禁用 2：锁定',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type_` bigint(20) NULL DEFAULT NULL COMMENT '所属字典类型(关联sys_dict_type表)',
  `label_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `value_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `sort_` int(11) NOT NULL COMMENT '排序',
  `color_` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '颜色值',
  `is_default` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否为默认选中  0：是   1：不是',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_dict_item_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_dict_item_label__index`(`label_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1184281607373144066', 1184275929216593921, '管理角色', '0', 9997, 'magenta', '0', '5', '2020-09-07 13:51:55', '5', '2019-11-22 08:03:34', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1184284245317750786', 1184276065695051777, '启用', '0', 9999, 'green', '0', '5', '2020-09-07 13:51:55', '5', '2020-07-10 14:42:08', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1184443365207359489', 1184443299096739842, '启用', '0', 9999, 'green', '0', '5', '2020-09-07 13:51:55', '5', '2020-09-07 13:51:55', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1184446171200929794', 1184446082709504001, '是', '0', 9999, 'green', '0', '5', '2020-09-07 13:51:55', '5', '2020-09-07 13:51:55', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185573862247620610', 1184278818265763841, '普通用户', '1', 9999, 'green', '0', '5', '2020-06-10 15:10:10', '5', '2020-06-10 15:10:54', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185574249151193089', 1184279015343525890, '启用', '0', 9999, 'green', '0', '5', '2020-06-10 15:11:43', '5', '2020-11-10 09:05:53', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185784609753862145', 1184280146572558337, '正常', '0', 9999, 'green', '0', '5', '2020-07-10 05:07:36', '5', '2020-07-10 05:07:36', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185796054335881218', 1184276065695051777, '禁用', '1', 9999, 'red', '1', '5', '2020-07-10 05:53:05', '5', '2020-07-10 05:53:20', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185796779556208641', 1184446082709504001, '否', '1', 9999, 'red', '1', '5', '2020-07-10 05:55:58', '5', '2020-07-10 05:55:58', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1185809206599520257', 1184275929216593921, '普通角色', '1', 9999, 'green', '1', '5', '2020-07-10 06:45:21', '5', '2019-11-22 08:03:32', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1186233312587288578', 1186287827395076097, '是', '0', 9999, 'green', '0', '5', '2020-08-10 10:50:35', '5', '2020-08-10 10:50:35', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1186233453260050434', 1186287827395076097, '否', '1', 9999, 'red', '1', '5', '2020-08-10 10:51:09', '5', '2020-08-10 10:51:09', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1186287892205461505', 1186233229531680770, '一级', '0', 9999, 'green', '0', '5', '2020-08-10 14:27:28', '5', '2020-08-10 14:27:28', NULL, '1', '');
INSERT INTO `sys_dict_item` VALUES ('1186287929610264578', 1186233229531680770, '二级', '1', 9999, 'green', '1', '5', '2020-08-10 14:27:37', '5', '2020-08-10 14:27:37', NULL, '1', '');
INSERT INTO `sys_dict_item` VALUES ('1186287962732683266', 1186233229531680770, '三级', '2', 9999, 'green', '1', '5', '2020-08-10 14:27:45', '5', '2020-08-10 14:27:45', NULL, '1', '');
INSERT INTO `sys_dict_item` VALUES ('1186288029191430146', 1184280146572558337, '禁用', '1', 9999, 'red', '1', '5', '2020-08-10 14:28:00', '5', '2020-08-10 14:28:00', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1186288215187841025', 1184279359863656449, '公司', '0', 9999, 'green', '1', '5', '2020-08-10 14:28:45', '5', '2019-11-22 06:31:15', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1186288248826159105', 1184279359863656449, '部门', '1', 9999, 'green', '0', '5', '2020-08-10 14:28:53', '5', '2019-11-22 06:31:16', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1186288298297974786', 1184279359863656449, '小组', '2', 9999, 'green', '1', '5', '2020-08-10 14:29:05', '5', '2020-08-10 14:29:05', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1186288342870843394', 1184279359863656449, '其他', '3', 9999, 'green', '1', '5', '2020-08-10 14:29:15', '5', '2020-08-10 14:29:15', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1187173373440978945', 1184279015343525890, '禁用', '1', 9999, 'red', '1', '5', '2020-11-10 09:06:03', '5', '2020-11-10 09:06:03', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1187664322021556225', 1184278818265763841, '管理用户', '0', 9999, 'green', '1', '5', '2021-01-10 01:36:54', '5', '2021-01-10 01:37:04', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118475373654019', 1189117822823837698, '菜单', '0', 9999, 'green', '0', '5', '2021-05-10 09:55:11', '5', '2021-05-10 09:55:11', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118563063967745', 1189117822823837698, '按钮', '1', 9999, 'green', '1', '5', '2021-05-10 09:55:32', '5', '2021-05-10 09:55:32', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118639341580289', 1189118141091819522, '启用', '0', 9999, 'green', '0', '5', '2021-05-10 09:55:51', '5', '2021-05-10 09:55:51', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118669498626050', 1189118141091819522, '禁用', '1', 9999, 'green', '1', '5', '2021-05-10 09:55:58', '5', '2021-05-10 09:55:58', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118719285014530', 1189118298978004993, '内部', '0', 9999, 'green', '0', '5', '2021-05-10 09:56:10', '5', '2021-05-10 09:56:10', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1189118741242195969', 1189118298978004993, '外部', 'target_', 9999, 'green', '1', '5', '2021-05-10 09:56:15', '5', '2021-05-10 15:36:30', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1190900464875495426', 1190894568304828418, '管理端', 'MANAGE', 9999, 'green', '0', '5', '2019-03-11 23:56:11', '5', '2019-11-22 06:09:36', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1190900520030593027', 1190894568304828418, '手机端', 'MOBILE', 9999, 'green', '1', '5', '2019-03-11 23:56:24', '5', '2019-03-11 23:56:24', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1190900568864874498', 1190894568304828418, '未知', 'UNKNOWN', 9999, 'green', '1', '5', '2019-03-11 23:56:36', '5', '2019-11-22 06:09:34', NULL, '0', NULL);
INSERT INTO `sys_dict_item` VALUES ('1192399509146144769', 1192387905000153089, '启用', '0', 9999, 'green', '0', '5', '2019-08-11 03:12:51', '5', '2019-11-08 07:30:38', NULL, '0', '');
INSERT INTO `sys_dict_item` VALUES ('1192399541215793154', 1192387905000153089, '禁用', '1', 9999, 'red', '1', '5', '2019-08-11 03:12:58', '5', '2019-11-08 07:30:51', NULL, '0', '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `status_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态  0：正常 1：禁用',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_dict_type_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_dict_type_name__index`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1184275929216593921', '角色类型', 'ROLE_TYPE', '0', '5', '2019-07-21 21:51:55', '5', '2019-11-22 06:14:01', NULL, '0', NULL);
INSERT INTO `sys_dict_type` VALUES ('1184276065695051777', '角色状态', 'ROLE_STATUS', '0', '5', '2019-07-21 21:51:55', '5', '2019-07-21 21:51:55', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1184278818265763841', '用户类型', 'USER_TYPE', '0', '5', '2019-07-21 21:51:55', '5', '2019-07-21 21:51:55', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1184279015343525890', '用户状态', 'USER_STATUS', '0', '5', '2019-07-21 21:51:55', '5', '2019-07-21 21:51:55', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1184279359863656449', '机构类型', 'GROUP_TYPE', '0', '5', '2019-07-21 21:51:55', '5', '2019-11-22 06:33:47', NULL, '0', NULL);
INSERT INTO `sys_dict_type` VALUES ('1184280146572558337', '机构状态', 'GROUP_STATUS', '0', '5', '2019-07-21 21:51:55', '5', '2019-10-20 22:26:54', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1184446082709504001', '系统数据', 'IS_SYSTEM', '0', '5', '2019-07-21 21:51:55', '5', '2019-07-21 21:51:55', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1186287827395076097', '演示环境', 'PREVIEW', '0', '5', '2019-10-20 18:50:15', '5', '2019-10-20 18:50:15', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1189118141091819522', '菜单状态', 'MENU_STATUS', '0', '5', '2019-10-29 09:53:52', '5', '2019-10-29 09:53:52', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1189118298978004993', '菜单目标', 'MENU_TARGET', '0', '5', '2019-10-29 09:54:29', '5', '2019-11-22 05:01:47', NULL, '0', NULL);
INSERT INTO `sys_dict_type` VALUES ('1190894568304828418', '平台类型', 'PLATFORM', '0', '5', '2019-11-03 23:32:45', '5', '2019-11-03 23:32:45', NULL, '0', '');
INSERT INTO `sys_dict_type` VALUES ('1192387905000153089', '业务状态', 'MODULE_STATUS', '0', '5', '2019-11-08 02:26:44', '5', '2019-11-20 19:38:01', NULL, '0', NULL);
INSERT INTO `sys_dict_type` VALUES ('1198111547474321410', '机构级别', 'GROUP_LEVEL', '0', '5', '2019-11-23 21:30:27', '5', '2019-12-09 05:55:29', NULL, '0', NULL);

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单 0为顶级目录',
  `parents_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父级标签',
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `code_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编码',
  `type_` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构类型',
  `status_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态  0：正常   1：禁用 2：锁定',
  `sort_` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_group_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_group_name__index`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('1186291246453284866', 0, '', '山东济南某公司', 'A0001', '0', '0', 9999, '5', '2019-10-21 22:40:48', '5', '2019-12-15 04:28:18', NULL, '0', '');
INSERT INTO `sys_group` VALUES ('1186291406193352705', 1186291246453284866, '', '商务部', 'A0002', '1', '0', 9999, '5', '2019-10-21 22:41:26', '5', '2019-12-15 04:29:08', NULL, '0', '');
INSERT INTO `sys_group` VALUES ('11862914061933527051', 1186291246453284866, '', '财务部', 'A0002', '1', '0', 9999, '5', '2019-10-21 22:41:26', '5', '2019-12-15 04:29:22', NULL, '0', '');
INSERT INTO `sys_group` VALUES ('1186291505426391042', 1186291246453284866, '', '行政部', 'A0003', '1', '0', 9999, '5', '2019-10-21 22:41:49', '5', '2019-12-15 04:29:16', NULL, '0', '');
INSERT INTO `sys_group` VALUES ('1205031715140124673', 1186291246453284866, NULL, '体验技术部', 'A0004', '1', '0', 0, '5', '2019-12-12 23:48:43', '5', '2019-12-12 23:55:27', NULL, '0', NULL);
INSERT INTO `sys_group` VALUES ('1205032099137044481', 1186291246453284866, NULL, '平台研发部', 'A0005', '1', '0', 1, '5', '2019-12-12 23:50:15', '5', '2019-12-15 04:28:27', NULL, '0', NULL);

-- ----------------------------
-- Table structure for sys_logger_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_logger_login`;
CREATE TABLE `sys_logger_login`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `login_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '登录时间',
  `ip_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `result_` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logger_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_logger_operate`;
CREATE TABLE `sys_logger_operate`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `module_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `feature_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能名称',
  `action_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `platform_` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台类型',
  `method_` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `user_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `time_` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `ip_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `uri_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URI',
  `location_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地点',
  `browser_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `status_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `result_` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '结果',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify`;
CREATE TABLE `sys_notify`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content_` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `type_` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通知通告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notify
-- ----------------------------
INSERT INTO `sys_notify` VALUES ('1', '放假通知', '放假', 'A', '5', '2019-03-23 01:47:51', '5', '2019-07-23 05:51:55', NULL, '0', '备注1');
INSERT INTO `sys_notify` VALUES ('2', '活动通知', '篮球比赛', 'E', '5', '2019-04-24 02:48:52', '5', '2019-06-23 04:50:54', NULL, '0', '备注2');
INSERT INTO `sys_notify` VALUES ('3', '规范通知', '规范', 'D', '5', '2019-05-25 03:49:53', '5', '2019-05-23 03:49:53', NULL, '0', '备注3');
INSERT INTO `sys_notify` VALUES ('4', '任务通知', '任务要求', 'C', '5', '2019-06-26 04:50:54', '5', '2019-04-23 02:48:52', NULL, '0', '备注4');
INSERT INTO `sys_notify` VALUES ('5', '公告通知', '团建活动', 'B', '5', '2019-07-27 05:51:55', '5', '2019-03-23 01:47:51', NULL, '0', '备注5');

-- ----------------------------
-- Table structure for sys_port
-- ----------------------------
DROP TABLE IF EXISTS `sys_port`;
CREATE TABLE `sys_port`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `type_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位分类（高管、中层、基层）',
  `sort_` decimal(10, 0) NOT NULL COMMENT '岗位排序（升序）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统员工岗位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_port
-- ----------------------------
INSERT INTO `sys_port` VALUES ('1', '00001', '技术员工', '基层', 5, '5', '2019-03-23 01:47:51', '5', '2019-07-23 05:51:55', NULL, '0', '备注1');
INSERT INTO `sys_port` VALUES ('2', '00002', '销售员工', '基层', 5, '5', '2019-04-24 02:48:52', '5', '2019-06-23 04:50:54', NULL, '0', '备注2');
INSERT INTO `sys_port` VALUES ('3', '00003', '项目经理', '中层', 2, '5', '2019-05-25 03:49:53', '5', '2019-05-23 03:49:53', NULL, '0', '备注3');
INSERT INTO `sys_port` VALUES ('4', '00004', '销售经理', '中层', 2, '5', '2019-06-26 04:50:54', '5', '2019-04-23 02:48:52', NULL, '0', '备注4');
INSERT INTO `sys_port` VALUES ('5', '00005', '人事经理', '中层', 3, '5', '2019-07-27 05:51:55', '5', '2019-03-23 01:47:51', NULL, '0', '备注5');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `type_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  `status_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态 0：正常  1：禁用 ',
  `data_scope` int(11) NULL DEFAULT NULL COMMENT '数据范围',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_role_id__uindex`(`id_`) USING BTREE,
  INDEX `sys_role_name__index`(`name_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1186303736474042369', '管理员', 'ROLE_ADMIN', '0', '0', NULL, '5', '2019-10-21 23:30:25', '5', '2019-12-12 07:25:22', NULL, '0', '拥有管理系统的能力');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority`  (
  `role_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  `authority_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限ID',
  `type_` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型 1 路由 2 操作 3 接口',
  UNIQUE INDEX `sys_role_authority_pk`(`role_`, `authority_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193107184884424706', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193107281026260993', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1193107366220963842', '1');
INSERT INTO `sys_role_authority` VALUES ('1186304019673448449', '1193115438112907265', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633552650149889', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633613329145857', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633756069699585', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633840454901761', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195634006352207875', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195734107506647041', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195734197373804546', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195734485237276673', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195734588148719618', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195734881976492033', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1193880704205778945', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1195636256315285506', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1195636315450777602', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1195636369423081474', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1195636434325741569', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303924735377409', '1195636517482012674', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1204362541212504066', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193889489490079745', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193889716552921089', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193889767253667841', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193889806730457089', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195736559408685057', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636256315285506', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636315450777602', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636369423081474', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636434325741569', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636517482012674', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193880704205778945', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193880832077524995', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193880891930243075', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193880944195465218', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195375746164584449', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636653595566082', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636710638100481', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636765709312001', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636827910840322', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195636915223666689', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195637038930468865', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193117531762663425', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193888487173058563', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193888525504802817', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193888597751689219', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195634281351749634', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195635597708894210', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195635765351030786', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195635821823139842', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195635999749709825', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193115438112907265', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193116297622265857', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193116382942797827', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193116458809368577', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195736297503760386', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195632558713348097', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633144728920066', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633229206396930', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633358361600002', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195633485197352961', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193107063383826434', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193107366220963842', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195616924717817858', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195617002710900737', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195617104733151234', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195617340981518338', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195617416697094146', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195617482002407426', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193916078747586561', '0');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193915780788424705', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193915835016581121', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1193915886388416513', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1195736038765535233', '1');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1194234153483161601', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1194234858356920322', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1194235023868350465', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1194235152872558594', '2');
INSERT INTO `sys_role_authority` VALUES ('1186303736474042369', '1194235306329559042', '2');
INSERT INTO `sys_role_authority` VALUES ('1204774545521664002', '1193115438112907265', '0');
INSERT INTO `sys_role_authority` VALUES ('1204774545521664002', '1195633358361600002', '2');
INSERT INTO `sys_role_authority` VALUES ('1204774545521664002', '1195633485197352961', '2');
INSERT INTO `sys_role_authority` VALUES ('1204774545521664002', '1195736297503760386', '1');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193889489490079745', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195636434325741569', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195636517482012674', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193880704205778945', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193117531762663425', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193115438112907265', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193107063383826434', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1193916078747586561', '0');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195736038765535233', '1');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1194235306329559042', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1194234858356920322', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195617104733151234', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195617482002407426', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195635999749709825', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195635821823139842', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195636915223666689', '2');
INSERT INTO `sys_role_authority` VALUES ('1205018637342081025', '1195636710638100481', '2');

-- ----------------------------
-- Table structure for sys_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group`;
CREATE TABLE `sys_role_group`  (
  `role_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `group_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织',
  UNIQUE INDEX `sys_role_group_pk`(`role_`, `group_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色组织关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_group
-- ----------------------------
INSERT INTO `sys_role_group` VALUES ('1', '4');
INSERT INTO `sys_role_group` VALUES ('2', '1');
INSERT INTO `sys_role_group` VALUES ('3', '3');
INSERT INTO `sys_role_group` VALUES ('4', '2');
INSERT INTO `sys_role_group` VALUES ('5', '5');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password_hash` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `group_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属组织',
  `name_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `head_portrait_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像URL',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `status_` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态  0：启用   1：禁用 2：锁定',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_modified_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改者',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `version_` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `remarks_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据状态: 0：正常 1：删除',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `sys_user_username__uindex`(`username_`) USING BTREE,
  INDEX `sys_user_username__index`(`username_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'admin', '$2a$10$SkPLa0RwRFrjyv1YterZtucAtjrPgYXi6zGXbjmEpolt10AcKZBqW', '1186291246453284866', 'admin', '系统管理员', '', '', NULL, '', '0', '5', '2019-07-26 13:51:55', '5', '2019-12-12 23:30:45', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户',
  `post_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位',
  UNIQUE INDEX `sys_user_post_pk`(`user_`, `post_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');
INSERT INTO `sys_user_post` VALUES ('3', '3');
INSERT INTO `sys_user_post` VALUES ('4', '4');
INSERT INTO `sys_user_post` VALUES ('5', '5');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `sys_user_role_pk`(`user_`, `role_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1186303924735377409');
INSERT INTO `sys_user_role` VALUES ('1199317379369189377', '1186303924735377409');
INSERT INTO `sys_user_role` VALUES ('1199317379369189377', '1186304019673448449');
INSERT INTO `sys_user_role` VALUES ('1199318363969470466', '1186303736474042369');
INSERT INTO `sys_user_role` VALUES ('1199318363969470466', '1186303924735377409');
INSERT INTO `sys_user_role` VALUES ('1200288711661711361', '1186304019673448449');
INSERT INTO `sys_user_role` VALUES ('1200675536108625922', '1186303736474042369');
INSERT INTO `sys_user_role` VALUES ('1200705989729419266', '1186303736474042369');
INSERT INTO `sys_user_role` VALUES ('1200706387986972674', '1186303736474042369');
INSERT INTO `sys_user_role` VALUES ('1200723232689352706', '1186303736474042369');
INSERT INTO `sys_user_role` VALUES ('1204774942839693314', '1204774545521664002');
INSERT INTO `sys_user_role` VALUES ('1205018476742180865', '1205018637342081025');
INSERT INTO `sys_user_role` VALUES ('2', '1186303924735377409');
INSERT INTO `sys_user_role` VALUES ('3', '1186303924735377409');
INSERT INTO `sys_user_role` VALUES ('4', '1186304019673448449');
INSERT INTO `sys_user_role` VALUES ('5', '1186303736474042369');

SET FOREIGN_KEY_CHECKS = 1;
