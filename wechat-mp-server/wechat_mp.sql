/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : wechat_mp

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 05/03/2024 23:28:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_role_name_uindex`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, 'admin', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
INSERT INTO `role_menu_relation` VALUES (559, 2, 1);
INSERT INTO `role_menu_relation` VALUES (560, 2, 2);
INSERT INTO `role_menu_relation` VALUES (561, 2, 3);
INSERT INTO `role_menu_relation` VALUES (562, 2, 4);
INSERT INTO `role_menu_relation` VALUES (563, 2, 100);
INSERT INTO `role_menu_relation` VALUES (564, 2, 101);
INSERT INTO `role_menu_relation` VALUES (565, 2, 102);
INSERT INTO `role_menu_relation` VALUES (566, 2, 103);
INSERT INTO `role_menu_relation` VALUES (567, 2, 104);
INSERT INTO `role_menu_relation` VALUES (568, 2, 105);
INSERT INTO `role_menu_relation` VALUES (569, 2, 106);
INSERT INTO `role_menu_relation` VALUES (570, 2, 107);
INSERT INTO `role_menu_relation` VALUES (571, 2, 108);
INSERT INTO `role_menu_relation` VALUES (572, 2, 109);
INSERT INTO `role_menu_relation` VALUES (573, 2, 110);
INSERT INTO `role_menu_relation` VALUES (574, 2, 111);
INSERT INTO `role_menu_relation` VALUES (575, 2, 112);
INSERT INTO `role_menu_relation` VALUES (576, 2, 113);
INSERT INTO `role_menu_relation` VALUES (577, 2, 114);
INSERT INTO `role_menu_relation` VALUES (578, 2, 115);
INSERT INTO `role_menu_relation` VALUES (579, 2, 116);
INSERT INTO `role_menu_relation` VALUES (580, 2, 500);
INSERT INTO `role_menu_relation` VALUES (581, 2, 501);
INSERT INTO `role_menu_relation` VALUES (582, 2, 1001);
INSERT INTO `role_menu_relation` VALUES (583, 2, 1002);
INSERT INTO `role_menu_relation` VALUES (584, 2, 1003);
INSERT INTO `role_menu_relation` VALUES (585, 2, 1004);
INSERT INTO `role_menu_relation` VALUES (586, 2, 1005);
INSERT INTO `role_menu_relation` VALUES (587, 2, 1006);
INSERT INTO `role_menu_relation` VALUES (588, 2, 1007);
INSERT INTO `role_menu_relation` VALUES (589, 2, 1008);
INSERT INTO `role_menu_relation` VALUES (590, 2, 1009);
INSERT INTO `role_menu_relation` VALUES (591, 2, 1010);
INSERT INTO `role_menu_relation` VALUES (592, 2, 1011);
INSERT INTO `role_menu_relation` VALUES (593, 2, 1012);
INSERT INTO `role_menu_relation` VALUES (594, 2, 1013);
INSERT INTO `role_menu_relation` VALUES (595, 2, 1014);
INSERT INTO `role_menu_relation` VALUES (596, 2, 1015);
INSERT INTO `role_menu_relation` VALUES (597, 2, 1016);
INSERT INTO `role_menu_relation` VALUES (598, 2, 1017);
INSERT INTO `role_menu_relation` VALUES (599, 2, 1018);
INSERT INTO `role_menu_relation` VALUES (600, 2, 1019);
INSERT INTO `role_menu_relation` VALUES (601, 2, 1020);
INSERT INTO `role_menu_relation` VALUES (602, 2, 1021);
INSERT INTO `role_menu_relation` VALUES (603, 2, 1022);
INSERT INTO `role_menu_relation` VALUES (604, 2, 1023);
INSERT INTO `role_menu_relation` VALUES (605, 2, 1024);
INSERT INTO `role_menu_relation` VALUES (606, 2, 1025);
INSERT INTO `role_menu_relation` VALUES (607, 2, 1026);
INSERT INTO `role_menu_relation` VALUES (608, 2, 1027);
INSERT INTO `role_menu_relation` VALUES (609, 2, 1028);
INSERT INTO `role_menu_relation` VALUES (610, 2, 1029);
INSERT INTO `role_menu_relation` VALUES (611, 2, 1030);
INSERT INTO `role_menu_relation` VALUES (612, 2, 1031);
INSERT INTO `role_menu_relation` VALUES (613, 2, 1032);
INSERT INTO `role_menu_relation` VALUES (614, 2, 1033);
INSERT INTO `role_menu_relation` VALUES (615, 2, 1034);
INSERT INTO `role_menu_relation` VALUES (616, 2, 1035);
INSERT INTO `role_menu_relation` VALUES (617, 2, 1036);
INSERT INTO `role_menu_relation` VALUES (618, 2, 1037);
INSERT INTO `role_menu_relation` VALUES (619, 2, 1038);
INSERT INTO `role_menu_relation` VALUES (620, 2, 1039);
INSERT INTO `role_menu_relation` VALUES (621, 2, 1040);
INSERT INTO `role_menu_relation` VALUES (622, 2, 1041);
INSERT INTO `role_menu_relation` VALUES (623, 2, 1042);
INSERT INTO `role_menu_relation` VALUES (624, 2, 1043);
INSERT INTO `role_menu_relation` VALUES (625, 2, 1044);
INSERT INTO `role_menu_relation` VALUES (626, 2, 1045);
INSERT INTO `role_menu_relation` VALUES (627, 2, 1046);
INSERT INTO `role_menu_relation` VALUES (628, 2, 1047);
INSERT INTO `role_menu_relation` VALUES (629, 2, 1048);
INSERT INTO `role_menu_relation` VALUES (630, 2, 1049);
INSERT INTO `role_menu_relation` VALUES (631, 2, 1050);
INSERT INTO `role_menu_relation` VALUES (632, 2, 1051);
INSERT INTO `role_menu_relation` VALUES (633, 2, 1052);
INSERT INTO `role_menu_relation` VALUES (634, 2, 1053);
INSERT INTO `role_menu_relation` VALUES (635, 2, 1054);
INSERT INTO `role_menu_relation` VALUES (636, 2, 1055);
INSERT INTO `role_menu_relation` VALUES (637, 2, 1056);
INSERT INTO `role_menu_relation` VALUES (638, 2, 1057);
INSERT INTO `role_menu_relation` VALUES (639, 2, 1058);
INSERT INTO `role_menu_relation` VALUES (640, 2, 1059);
INSERT INTO `role_menu_relation` VALUES (641, 2, 1060);
INSERT INTO `role_menu_relation` VALUES (642, 2, 2000);
INSERT INTO `role_menu_relation` VALUES (643, 2, 2001);
INSERT INTO `role_menu_relation` VALUES (644, 2, 2002);
INSERT INTO `role_menu_relation` VALUES (645, 2, 2003);
INSERT INTO `role_menu_relation` VALUES (646, 2, 2004);
INSERT INTO `role_menu_relation` VALUES (647, 2, 2005);
INSERT INTO `role_menu_relation` VALUES (648, 2, 2006);
INSERT INTO `role_menu_relation` VALUES (649, 2, 2007);
INSERT INTO `role_menu_relation` VALUES (650, 2, 2008);
INSERT INTO `role_menu_relation` VALUES (651, 2, 2009);
INSERT INTO `role_menu_relation` VALUES (652, 2, 2010);
INSERT INTO `role_menu_relation` VALUES (653, 2, 2011);
INSERT INTO `role_menu_relation` VALUES (654, 2, 2012);
INSERT INTO `role_menu_relation` VALUES (655, 2, 2013);
INSERT INTO `role_menu_relation` VALUES (656, 2, 2014);
INSERT INTO `role_menu_relation` VALUES (657, 2, 2015);
INSERT INTO `role_menu_relation` VALUES (658, 2, 2016);
INSERT INTO `role_menu_relation` VALUES (659, 2, 2017);
INSERT INTO `role_menu_relation` VALUES (660, 2, 2018);
INSERT INTO `role_menu_relation` VALUES (661, 2, 2019);
INSERT INTO `role_menu_relation` VALUES (662, 2, 2020);
INSERT INTO `role_menu_relation` VALUES (663, 2, 2021);
INSERT INTO `role_menu_relation` VALUES (664, 2, 2022);
INSERT INTO `role_menu_relation` VALUES (665, 2, 2023);
INSERT INTO `role_menu_relation` VALUES (666, 2, 2024);
INSERT INTO `role_menu_relation` VALUES (667, 2, 2025);
INSERT INTO `role_menu_relation` VALUES (668, 2, 2026);
INSERT INTO `role_menu_relation` VALUES (669, 2, 2027);
INSERT INTO `role_menu_relation` VALUES (670, 2, 2028);
INSERT INTO `role_menu_relation` VALUES (671, 2, 2029);
INSERT INTO `role_menu_relation` VALUES (672, 2, 2033);
INSERT INTO `role_menu_relation` VALUES (673, 2, 2034);
INSERT INTO `role_menu_relation` VALUES (674, 2, 2035);
INSERT INTO `role_menu_relation` VALUES (675, 2, 2036);
INSERT INTO `role_menu_relation` VALUES (676, 2, 2037);
INSERT INTO `role_menu_relation` VALUES (677, 2, 2038);
INSERT INTO `role_menu_relation` VALUES (678, 2, 2039);
INSERT INTO `role_menu_relation` VALUES (679, 2, 2040);
INSERT INTO `role_menu_relation` VALUES (680, 2, 2041);
INSERT INTO `role_menu_relation` VALUES (681, 2, 2042);
INSERT INTO `role_menu_relation` VALUES (682, 2, 2043);
INSERT INTO `role_menu_relation` VALUES (683, 2, 2044);
INSERT INTO `role_menu_relation` VALUES (684, 2, 2045);
INSERT INTO `role_menu_relation` VALUES (685, 2, 2046);
INSERT INTO `role_menu_relation` VALUES (686, 2, 2047);
INSERT INTO `role_menu_relation` VALUES (687, 2, 2048);
INSERT INTO `role_menu_relation` VALUES (688, 2, 2049);
INSERT INTO `role_menu_relation` VALUES (689, 2, 2050);
INSERT INTO `role_menu_relation` VALUES (690, 2, 2051);
INSERT INTO `role_menu_relation` VALUES (691, 2, 2052);
INSERT INTO `role_menu_relation` VALUES (692, 2, 2053);
INSERT INTO `role_menu_relation` VALUES (693, 2, 2054);
INSERT INTO `role_menu_relation` VALUES (694, 2, 2055);
INSERT INTO `role_menu_relation` VALUES (695, 2, 2057);
INSERT INTO `role_menu_relation` VALUES (696, 2, 2058);
INSERT INTO `role_menu_relation` VALUES (697, 2, 2059);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2061 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 10, 'M', '0', '0', '', 'system', '2021-01-14 15:39:13', '2021-01-21 17:43:31', '系统管理目录', 'system', NULL);
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 20, 'M', '0', '0', '', 'monitor', '2021-01-14 15:39:13', '2021-01-21 17:43:38', '系统监控目录', 'monitor', NULL);
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 30, 'M', '0', '0', '', 'tool', '2021-01-14 15:39:13', '2021-01-21 17:43:43', '系统工具目录', 'tool', NULL);
INSERT INTO `sys_menu` VALUES (4, '公众号管理', 0, 0, 'M', '0', '0', '', 'wechat', '2018-03-16 11:33:00', '2020-03-05 14:59:21', '若依官网地址', 'wxmp', NULL);
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'C', '0', '0', 'system:user:list', 'user', '2021-01-14 15:39:13', NULL, '用户管理菜单', 'user', 'system/user/index');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'C', '0', '0', 'system:role:list', 'peoples', '2021-01-14 15:39:13', NULL, '角色管理菜单', 'role', 'system/role/index');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'C', '0', '0', 'system:menu:list', 'tree-table', '2021-01-14 15:39:13', NULL, '菜单管理菜单', 'menu', 'system/menu/index');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'C', '0', '0', 'system:dept:list', 'tree', '2021-01-14 15:39:13', NULL, '部门管理菜单', 'dept', 'system/dept/index');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'C', '0', '0', 'system:post:list', 'post', '2021-01-14 15:39:13', NULL, '岗位管理菜单', 'post', 'system/post/index');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'C', '0', '0', 'system:dict:list', 'dict', '2021-01-14 15:39:13', NULL, '字典管理菜单', 'dict', 'system/dict/index');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'C', '0', '0', 'system:config:list', 'edit', '2021-01-14 15:39:13', NULL, '参数设置菜单', 'config', 'system/config/index');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'C', '0', '0', 'system:notice:list', 'message', '2021-01-14 15:39:13', NULL, '通知公告菜单', 'notice', 'system/notice/index');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'M', '0', '0', '', 'log', '2021-01-14 15:39:13', NULL, '日志管理菜单', 'log', '');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'C', '0', '0', 'monitor:online:list', 'online', '2021-01-14 15:39:13', NULL, '在线用户菜单', 'online', 'monitor/online/index');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'C', '0', '0', 'monitor:job:list', 'job', '2021-01-14 15:39:13', NULL, '定时任务菜单', 'job', 'monitor/job/index');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'C', '0', '0', 'monitor:druid:list', 'druid', '2021-01-14 15:39:13', NULL, '数据监控菜单', 'druid', 'monitor/druid/index');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'C', '0', '0', 'monitor:server:list', 'server', '2021-01-14 15:39:13', NULL, '服务监控菜单', 'server', 'monitor/server/index');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'C', '0', '0', 'monitor:cache:list', 'redis', '2021-01-14 15:39:13', NULL, '缓存监控菜单', 'cache', 'monitor/cache/index');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'C', '0', '0', 'tool:build:list', 'build', '2021-01-14 15:39:13', NULL, '表单构建菜单', 'build', 'tool/build/index');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'C', '0', '0', 'tool:gen:list', 'code', '2021-01-14 15:39:13', NULL, '代码生成菜单', 'gen', 'tool/gen/index');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'C', '0', '0', 'tool:swagger:list', 'swagger', '2021-01-14 15:39:13', NULL, '系统接口菜单', 'swagger', 'tool/swagger/index');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'C', '0', '0', 'monitor:operlog:list', 'form', '2021-01-14 15:39:13', NULL, '操作日志菜单', 'operlog', 'monitor/operlog/index');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', '2021-01-14 15:39:13', NULL, '登录日志菜单', 'logininfor', 'monitor/logininfor/index');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, 'F', '0', '0', 'system:user:query', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, 'F', '0', '0', 'system:user:add', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, 'F', '0', '0', 'system:user:edit', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, 'F', '0', '0', 'system:user:remove', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, 'F', '0', '0', 'system:user:export', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, 'F', '0', '0', 'system:user:import', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, 'F', '0', '0', 'system:user:resetPwd', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, 'F', '0', '0', 'system:role:query', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, 'F', '0', '0', 'system:role:add', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, 'F', '0', '0', 'system:role:edit', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, 'F', '0', '0', 'system:role:remove', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, 'F', '0', '0', 'system:role:export', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, 'F', '0', '0', 'system:menu:query', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, 'F', '0', '0', 'system:menu:add', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, 'F', '0', '0', 'system:menu:edit', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, 'F', '0', '0', 'system:menu:remove', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, 'F', '0', '0', 'system:dept:query', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, 'F', '0', '0', 'system:dept:add', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, 'F', '0', '0', 'system:dept:edit', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, 'F', '0', '0', 'system:dept:remove', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, 'F', '0', '0', 'system:post:query', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, 'F', '0', '0', 'system:post:add', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, 'F', '0', '0', 'system:post:edit', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, 'F', '0', '0', 'system:post:remove', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, 'F', '0', '0', 'system:post:export', '#', '2021-01-14 15:39:13', NULL, '', '', '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, 'F', '0', '0', 'system:dict:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, 'F', '0', '0', 'system:dict:add', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, 'F', '0', '0', 'system:dict:edit', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, 'F', '0', '0', 'system:dict:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, 'F', '0', '0', 'system:dict:export', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, 'F', '0', '0', 'system:config:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, 'F', '0', '0', 'system:config:add', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, 'F', '0', '0', 'system:config:edit', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, 'F', '0', '0', 'system:config:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, 'F', '0', '0', 'system:config:export', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, 'F', '0', '0', 'system:notice:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, 'F', '0', '0', 'system:notice:add', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, 'F', '0', '0', 'system:notice:edit', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, 'F', '0', '0', 'system:notice:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, 'F', '0', '0', 'monitor:operlog:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, 'F', '0', '0', 'monitor:operlog:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, 'F', '0', '0', 'monitor:operlog:export', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, 'F', '0', '0', 'monitor:logininfor:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, 'F', '0', '0', 'monitor:logininfor:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, 'F', '0', '0', 'monitor:logininfor:export', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, 'F', '0', '0', 'monitor:online:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, 'F', '0', '0', 'monitor:online:batchLogout', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, 'F', '0', '0', 'monitor:online:forceLogout', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, 'F', '0', '0', 'monitor:job:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, 'F', '0', '0', 'monitor:job:add', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, 'F', '0', '0', 'monitor:job:edit', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, 'F', '0', '0', 'monitor:job:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, 'F', '0', '0', 'monitor:job:changeStatus', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, 'F', '0', '0', 'monitor:job:export', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, 'F', '0', '0', 'tool:gen:query', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, 'F', '0', '0', 'tool:gen:edit', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, 'F', '0', '0', 'tool:gen:remove', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, 'F', '0', '0', 'tool:gen:import', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, 'F', '0', '0', 'tool:gen:preview', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, 'F', '0', '0', 'tool:gen:code', '#', '2021-01-14 15:39:13', NULL, '', '#', '');
INSERT INTO `sys_menu` VALUES (2000, '用户标签', 4, 10, 'C', '0', '0', 'wxmp:wxusertags:list', 'tab', '2020-03-03 10:47:36', '2020-03-03 20:17:50', '', 'wxusertags', 'wxmp/wxusertags/index');
INSERT INTO `sys_menu` VALUES (2001, '修改标签', 2000, 10, 'F', '1', '0', 'wxmp:wxusertags:edit', '#', '2020-03-03 11:16:13', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2002, '公众号用户', 4, 20, 'C', '0', '0', 'wxmp:wxuser:index', 'peoples', '2020-03-04 10:13:30', NULL, '', 'wxuser', 'wxmp/wxuser/index');
INSERT INTO `sys_menu` VALUES (2003, '用户消息', 4, 30, 'C', '0', '0', 'wxmp:wxmsg:index', 'clipboard', '2020-03-04 10:15:47', NULL, '', 'wxmsg', 'wxmp/wxmsg/index');
INSERT INTO `sys_menu` VALUES (2004, '素材管理', 4, 40, 'C', '0', '0', 'wxmp:wxmaterial:index', 'example', '2020-03-04 10:17:21', '2020-03-05 21:31:33', '', 'wxmaterial', 'wxmp/wxmaterial/index');
INSERT INTO `sys_menu` VALUES (2005, '自定义菜单', 4, 50, 'C', '0', '0', 'wxmp:wxmenu:get', 'cascader', '2020-03-04 10:18:02', '2020-03-04 10:29:20', '', 'wxmenu', 'wxmp/wxmenu/detail');
INSERT INTO `sys_menu` VALUES (2006, '消息自动回复', 4, 60, 'C', '0', '0', 'wxmp:wxautoreply:index', 'dashboard', '2020-03-04 10:18:53', NULL, '', 'wxautoreply', 'wxmp/wxautoreply/index');
INSERT INTO `sys_menu` VALUES (2007, '数据统计', 4, 70, 'C', '0', '0', NULL, 'druid', '2020-03-04 10:19:53', NULL, '', 'wxsummary', 'wxmp/wxsummary/index');
INSERT INTO `sys_menu` VALUES (2008, '用户标签删除', 2000, 0, 'F', '0', '0', 'wxmp:wxusertags:del', '#', '2020-03-04 17:08:10', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2009, '用户标签新增', 2000, 0, 'F', '0', '0', 'wxmp:wxusertags:add', '#', '2020-03-04 17:08:42', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2010, '公众号用户新增', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:add', '#', '2020-03-04 17:15:01', '2020-03-04 17:16:59', '', '', NULL);
INSERT INTO `sys_menu` VALUES (2011, '公众号用户修改', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:edit', '#', '2020-03-04 17:16:17', '2020-03-04 17:17:09', '', '', NULL);
INSERT INTO `sys_menu` VALUES (2012, '公众号用户打标签', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:tagging', '#', '2020-03-04 17:16:41', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2013, '公众号用户备注修改', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:edit:remark', '#', '2020-03-04 17:17:43', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2014, '公众号用户同步', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:synchro', '#', '2020-03-04 17:18:09', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2015, '公众号用户删除', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:del', '#', '2020-03-04 17:18:31', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2016, '公众号用户详情', 2002, 0, 'F', '0', '0', 'wxmp:wxuser:get', '#', '2020-03-04 17:18:55', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2017, '用户消息新增', 2003, 0, 'F', '0', '0', 'wxmp:wxmsg:add', '#', '2020-03-04 17:19:24', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2018, '用户消息修改', 2003, 0, 'F', '0', '0', 'wxmp:wxmsg:edit', '#', '2020-03-04 17:19:45', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2019, '用户消息删除', 2003, 0, 'F', '0', '0', 'wxmp:wxmsg:del', '#', '2020-03-04 17:20:03', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2020, '用户消息详情', 2003, 0, 'F', '0', '0', 'wxmp:wxmsg:get', '#', '2020-03-04 17:20:21', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2021, '素材新增', 2004, 0, 'F', '0', '0', 'wxmp:wxmaterial:add', '#', '2020-03-04 17:20:43', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2022, '素材修改', 2004, 0, 'F', '0', '0', 'wxmp:wxmaterial:edit', '#', '2020-03-04 17:21:03', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2023, '素材删除', 2004, 0, 'F', '0', '0', 'wxmp:wxmaterial:del', '#', '2020-03-04 17:21:24', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2024, '素材详情', 2004, 0, 'F', '0', '0', 'wxmp:wxmaterial:get', '#', '2020-03-04 17:21:43', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2025, '自定义菜单发布', 2005, 0, 'F', '0', '0', 'wxmp:wxmenu:add', '#', '2020-03-04 17:22:12', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2026, '消息自动回复新增', 2006, 0, 'F', '0', '0', 'wxmp:wxautoreply:add', '#', '2020-03-04 17:22:43', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2027, '消息自动回复修改', 2006, 0, 'F', '0', '0', 'wxmp:wxautoreply:edit', '#', '2020-03-04 17:23:05', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2028, '消息自动回复删除', 2006, 0, 'F', '0', '0', 'wxmp:wxautoreply:del', '#', '2020-03-04 17:23:36', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2029, '消息自动回复详情', 2006, 0, 'F', '0', '0', 'wxmp:wxautoreply:get', '#', '2020-03-04 17:23:59', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2033, '商城管理', 0, 5, 'M', '0', '0', NULL, 'shopping', '2021-01-21 17:44:55', NULL, '', 'mall', NULL);
INSERT INTO `sys_menu` VALUES (2034, '商品分类', 2033, 10, 'C', '0', '0', 'mall:goodscategory:index', 'build', '2021-01-21 17:47:43', '2021-01-21 17:48:30', '', 'goodscategory', 'mall/goodscategory/index');
INSERT INTO `sys_menu` VALUES (2035, '商品类目查询', 2034, 0, 'F', '0', '0', 'mall:goodscategory:get', '#', '2021-01-21 17:48:23', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2036, '新增商品类目', 2034, 0, 'F', '0', '0', 'mall:goodscategory:add', '#', '2021-01-21 17:48:51', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2037, '修改商品类目', 2034, 0, 'F', '0', '0', 'mall:goodscategory:edit', '#', '2021-01-21 17:49:11', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2038, '删除商品类目', 2034, 0, 'F', '0', '0', 'mall:goodscategory:del', '#', '2021-01-21 17:49:31', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2039, '商品管理', 2033, 10, 'C', '0', '0', 'mall:goodsspu:index', 'shopping', '2021-01-25 22:10:44', '2021-01-25 22:12:13', '', 'goodsspu', 'mall/goodsspu/index');
INSERT INTO `sys_menu` VALUES (2040, '商品查询', 2039, 0, 'F', '0', '0', 'mall:goodsspu:get', '#', '2021-01-25 22:13:08', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2041, '新增商品', 2039, 0, 'F', '0', '0', 'mall:goodsspu:add', '#', '2021-01-25 22:14:55', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2042, '修改商品', 2039, 0, 'F', '0', '0', 'mall:goodsspu:edit', '#', '2021-01-25 22:15:14', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2043, '删除商品', 2039, 0, 'F', '0', '0', 'mall:goodsspu:del', '#', '2021-01-25 22:15:35', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2044, '订单管理', 2033, 10, 'C', '0', '0', 'mall:orderinfo:index', 'list', '2021-01-27 00:07:14', '2021-01-27 00:07:45', '', 'orderinfo', 'mall/orderinfo/index');
INSERT INTO `sys_menu` VALUES (2045, '订单查询', 2044, 0, 'F', '0', '0', 'mall:orderinfo:get', '#', '2021-01-27 00:08:28', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2046, '商城订单修改', 2044, 0, 'F', '0', '0', 'mall:orderinfo:edit', '#', '2021-01-28 22:38:58', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2047, '商城订单新增', 2044, 0, 'F', '0', '0', 'mall:orderinfo:add', '#', '2021-01-28 22:39:21', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2048, '商城订单删除', 2044, 0, 'F', '0', '0', 'mall:orderinfo:del', '#', '2021-01-28 22:39:41', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2049, '小程序管理', 0, 3, 'M', '0', '0', NULL, 'phone', '2021-01-28 23:45:03', NULL, '', 'wxma', NULL);
INSERT INTO `sys_menu` VALUES (2050, '小程序用户', 2049, 10, 'C', '0', '0', 'wxmp:wxuser:index', 'peoples', '2021-01-28 23:54:34', NULL, '', 'wxuser-ma', 'wxma/wxuser/index');
INSERT INTO `sys_menu` VALUES (2051, '小程序用户查询', 2050, 0, 'F', '0', '0', 'wxmp:wxuser:get', '#', '2021-01-28 23:57:07', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2052, '草稿箱', 4, 44, 'C', '0', '0', '	 wxmp:wxdraft:index', 'guide', '2022-03-29 14:48:47', '2022-03-29 14:51:31', '', 'wxdraft', 'wxmp/wxdraft/index');
INSERT INTO `sys_menu` VALUES (2053, '新增草稿箱', 2052, 0, 'F', '1', '0', 'wxmp:wxdraft:add', '#', '2022-03-29 14:50:13', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2054, '修改草稿箱', 2052, 0, 'F', '0', '0', 'wxmp:wxdraft:edit', '#', '2022-03-29 14:50:28', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2055, '删除草稿箱', 2052, 0, 'F', '0', '0', 'wxmp:wxdraft:del', '#', '2022-03-29 14:50:41', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2057, '发布草稿', 2052, 0, 'F', '0', '0', 'wxmp:wxdraft:publish', '#', '2022-03-29 14:51:14', NULL, '', '', NULL);
INSERT INTO `sys_menu` VALUES (2058, '已发布', 4, 46, 'C', '0', '0', 'wxmp:wxfreepublish:index', 'clipboard', '2022-03-29 14:52:44', NULL, '', 'wxfreepublish', 'wxmp/wxfreepublish/index');
INSERT INTO `sys_menu` VALUES (2059, '删除已发布', 2058, 0, 'F', '0', '0', 'wxmp:wxfreepublish:del', '#', '2022-03-29 14:52:57', NULL, '', '', NULL);

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
INSERT INTO `sys_user` VALUES (6, 'admin', 'W', '2207153529@qq.com', '15116464005', '0', 'string', '$2a$10$OiLZmjXmxSnU.zSqzTqfD.r21LPL/FONKQWdetgQcHae.TNitDtkq', '1', '0', '', NULL, '2024-03-02 14:59:01', '2024-03-02 14:59:01');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (3, 6, 2);

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
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新者',
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
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别（1：男，2：女，0：未知）',
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

SET FOREIGN_KEY_CHECKS = 1;
