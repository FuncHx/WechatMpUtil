/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : wechat_mp

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 30/04/2024 18:14:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_role_name_uindex`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, 'admin', '2024-03-13 09:14:38', '2024-04-29 07:10:02', '管理员');
INSERT INTO `role` VALUES (3, 'boss', '2024-03-08 17:22:49', '2024-03-13 02:09:04', '老板');

-- ----------------------------
-- Table structure for role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_relation`;
CREATE TABLE `role_menu_relation`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL,
  `menu_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_permission_relation_permission_id_fk`(`menu_id`) USING BTREE,
  INDEX `role_permission_relation_role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `role_permission_relation_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 772 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
INSERT INTO `role_menu_relation` VALUES (701, 3, 1001);
INSERT INTO `role_menu_relation` VALUES (702, 3, 2061);
INSERT INTO `role_menu_relation` VALUES (758, 2, 1);
INSERT INTO `role_menu_relation` VALUES (759, 2, 102);
INSERT INTO `role_menu_relation` VALUES (760, 2, 1001);
INSERT INTO `role_menu_relation` VALUES (761, 2, 2061);
INSERT INTO `role_menu_relation` VALUES (762, 2, 2062);
INSERT INTO `role_menu_relation` VALUES (763, 2, 2063);
INSERT INTO `role_menu_relation` VALUES (764, 2, 2064);
INSERT INTO `role_menu_relation` VALUES (765, 2, 2065);
INSERT INTO `role_menu_relation` VALUES (766, 2, 2066);
INSERT INTO `role_menu_relation` VALUES (767, 2, 2068);
INSERT INTO `role_menu_relation` VALUES (768, 2, 2069);
INSERT INTO `role_menu_relation` VALUES (769, 2, 2070);
INSERT INTO `role_menu_relation` VALUES (770, 2, 2071);
INSERT INTO `role_menu_relation` VALUES (771, 2, 2072);
INSERT INTO `role_menu_relation` VALUES (772, 2, 2073);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2075 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 10, 'M', '0', '0', '', 'shezhi', '2021-01-14 15:39:13', '2021-01-21 17:43:31', '系统管理目录', 'system', 'LayoutIndex');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'C', '0', '0', 'system:menu:list', 'fenlei', '2021-01-14 15:39:13', '2024-03-12 09:52:00', '菜单管理菜单', 'menu', 'system/menu/index');
INSERT INTO `sys_menu` VALUES (1001, '新增菜单', 102, 1, 'F', '0', '0', 'system:menu:add', '#', '2024-03-12 17:36:57', '2024-03-12 17:37:00', '新增菜单', NULL, NULL);
INSERT INTO `sys_menu` VALUES (2061, '删除菜单', 102, 0, 'F', '0', '0', 'system:menu:delete', '', '2024-03-12 09:52:35', '2024-03-12 09:52:35', '', '', '');
INSERT INTO `sys_menu` VALUES (2062, '修改菜单', 102, 1, 'F', '0', '0', 'system:menu:update', '', '2024-03-12 10:21:17', '2024-03-12 10:21:17', '', '', '');
INSERT INTO `sys_menu` VALUES (2063, '角色管理', 1, 1, 'C', '0', '0', 'system:role:list', 'zuzhi', '2024-03-13 01:17:53', '2024-03-13 01:24:02', '', 'role', 'system/role/index');
INSERT INTO `sys_menu` VALUES (2064, '新增角色', 2063, 0, 'F', '0', '0', 'system:role:add', '', '2024-03-13 01:47:25', '2024-03-13 01:47:25', '', '', '');
INSERT INTO `sys_menu` VALUES (2065, '修改角色', 2063, 0, 'F', '0', '0', 'system:role:update', '', '2024-03-13 01:47:50', '2024-03-13 01:47:50', '', '', '');
INSERT INTO `sys_menu` VALUES (2066, '删除角色', 2063, 0, 'F', '0', '0', 'system:role:delete', '', '2024-03-13 01:48:17', '2024-03-13 01:48:17', '', '', '');
INSERT INTO `sys_menu` VALUES (2068, '公众号管理', 0, 1, 'M', '0', '0', '', 'dengji', '2024-03-13 02:26:18', '2024-03-13 02:26:18', '', 'wx', 'LayoutIndex');
INSERT INTO `sys_menu` VALUES (2069, '消息管理', 2068, 0, 'C', '0', '0', 'wx:message:list', 'xiaoxi2', '2024-03-13 02:31:27', '2024-03-13 02:32:19', '', 'message', 'wx/message/index');
INSERT INTO `sys_menu` VALUES (2070, '菜单管理', 2068, 1, 'C', '0', '0', 'wx:menu:list', 'fenlei', '2024-03-13 06:54:49', '2024-03-13 07:12:11', '', '/wx/menu', 'wx/menu/index');
INSERT INTO `sys_menu` VALUES (2071, '创建菜单', 2070, 0, 'F', '0', '0', 'wx:menu:create', '', '2024-03-13 08:30:03', '2024-03-13 08:30:03', '', '', '');
INSERT INTO `sys_menu` VALUES (2072, '素材管理', 2068, 2, 'C', '0', '0', 'wx:material:list', 'tupian', '2024-03-14 02:57:17', '2024-03-14 02:57:17', '', 'material', 'wx/material/index');
INSERT INTO `sys_menu` VALUES (2073, '用户管理', 2068, 0, 'C', '0', '0', 'wx:user:list', 'zuzhi', '2024-04-28 07:34:30', '2024-04-28 07:34:30', '', '/wx/user', 'wx/user/index');
INSERT INTO `sys_menu` VALUES (2074, '标签管理', 2068, 0, 'C', '0', '0', 'wx:tag:list', 'fengxian', '2024-04-29 08:08:02', '2024-04-29 08:08:02', '', '/wx/tag', 'wx/tag/index');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_user_name_uindex`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'W', '2207153529@qq.com', '15116464005', '0', 'http://localhost:80/static/4598f0e9716642bbb4d3fab088db126d.jpg', '$2a$10$OiLZmjXmxSnU.zSqzTqfD.r21LPL/FONKQWdetgQcHae.TNitDtkq', '1', '0', '', NULL, '2024-03-02 14:59:01', '2024-03-11 01:29:14');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_role_relation_role_id_fk`(`role_id`) USING BTREE,
  INDEX `user_role_relation_sys_user_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `user_role_relation_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_relation_sys_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (5, 1, 2);

-- ----------------------------
-- Table structure for wx_auto_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_auto_reply`;
CREATE TABLE `wx_auto_reply`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型（1、关注时回复；2、消息回复；3、关键词回复）',
  `req_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键词',
  `req_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求消息类型（text：文本；image：图片；voice：语音；video：视频；shortvideo：小视频；location：地理位置）',
  `rep_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复消息类型（text：文本；image：图片；voice：语音；video：视频；music：音乐；news：图文）',
  `rep_mate` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复类型文本匹配类型（1、全匹配，2、半匹配）',
  `rep_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '回复类型文本保存文字',
  `rep_media_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复类型imge、voice、news、video的mediaID或音乐缩略图的媒体id',
  `rep_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的素材名、视频和音乐的标题',
  `rep_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频和音乐的描述',
  `rep_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  `rep_hq_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '高质量链接',
  `rep_thumb_media_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图的媒体id',
  `rep_thumb_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图url',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图文消息的内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信自动回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_auto_reply
-- ----------------------------

-- ----------------------------
-- Table structure for wx_msg
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg`;
CREATE TABLE `wx_msg`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `app_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公众号名称',
  `app_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公众号logo',
  `wx_user_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '微信用户ID',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信用户昵称',
  `headimg_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信用户头像',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息分类（1、用户发给公众号；2、公众号发给用户；）',
  `rep_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息类型（text：文本；image：图片；voice：语音；video：视频；shortvideo：小视频；location：地理位置；music：音乐；news：图文；event：推送事件）',
  `rep_event` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '事件类型（subscribe：关注；unsubscribe：取关；CLICK、VIEW：菜单事件）',
  `rep_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '回复类型文本保存文字、地理位置信息',
  `rep_media_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复类型imge、voice、news、video的mediaID或音乐缩略图的媒体id',
  `rep_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的素材名、视频和音乐的标题',
  `rep_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频和音乐的描述',
  `rep_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  `rep_hq_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '高质量链接',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图文消息的内容',
  `rep_thumb_media_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图的媒体id',
  `rep_thumb_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图url',
  `rep_location_x` double NULL DEFAULT NULL COMMENT '地理位置维度',
  `rep_location_y` double NULL DEFAULT NULL COMMENT '地理位置经度',
  `rep_scale` double NULL DEFAULT NULL COMMENT '地图缩放大小',
  `read_flag` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '1' COMMENT '已读标记（1：是；0：否）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_msg
-- ----------------------------

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户备注',
  `del_flag` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `app_type` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '应用类型(1:小程序，2:公众号)',
  `subscribe` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否订阅（1：是；0：否；2：网页授权用户）',
  `subscribe_scene` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他',
  `subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '关注时间',
  `subscribe_num` int(0) NULL DEFAULT NULL COMMENT '关注次数',
  `cancel_subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '取消关注时间',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户标识',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '性别（1：男，2：女，0：未知）',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在城市',
  `country` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在国家',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在省份',
  `phone` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `language` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户语言',
  `headimg_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `union_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'union_id',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户组',
  `tagid_list` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签列表',
  `qr_scene_str` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '二维码扫码场景',
  `latitude` double NULL DEFAULT NULL COMMENT '地理位置纬度',
  `longitude` double NULL DEFAULT NULL COMMENT '地理位置经度',
  `precision` double NULL DEFAULT NULL COMMENT '地理位置精度',
  `session_key` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会话密钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_openid`(`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES ('1784871949806469122', '2024-04-29 09:06:49', '2024-04-30 15:44:07', '管理员', '0', '2', '1', NULL, NULL, 10, '2024-04-30 17:39:42', 'o_vvO6fcAXtcDdbYVJMLkO6Z-UeQ', '', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[2]', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wx_user` VALUES ('1785148332646588418', '2024-04-30 03:25:04', '2024-04-30 15:44:07', NULL, '0', NULL, '1', NULL, NULL, NULL, NULL, 'test', 'W', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
