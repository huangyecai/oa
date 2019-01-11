DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(64) NOT NULL COMMENT '主键id（uuid）',
  `user_id` varchar(64) DEFAULT NULL COMMENT '名称',
  `role_id` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;