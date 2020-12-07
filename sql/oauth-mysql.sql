DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` varchar(32) NOT NULL COMMENT ' 主键ID （会员信息表的ID相同）',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '加密后的密码',
  `phone` varchar(18) NOT NULL COMMENT '手机号用于登录',
  `is_unexpired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否过期 0：过期 1：未过期',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
  `is_unlocked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否锁定 0:  锁定 1:未锁定',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `oauth_role`;
CREATE TABLE `oauth_role` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `value` varchar(128) NOT NULL COMMENT '角色值',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 0：未启用 1：启用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


DROP TABLE IF EXISTS `oauth_authority`;
CREATE TABLE `oauth_authority` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `value` varchar(128) NOT NULL COMMENT '权限值',
  `pid` varchar(32) NOT NULL COMMENT '父ID',
  `is_menu` tinyint(1) NOT NULL COMMENT '是否菜单 1:  是  0 :否',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

DROP TABLE IF EXISTS `oauth_user_role_mapping`;
CREATE TABLE `oauth_user_role_mapping` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

DROP TABLE IF EXISTS `oauth_role_authority_mapping`;
CREATE TABLE `oauth_role_authority_mapping` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `authority_id` varchar(32) NOT NULL COMMENT '权限ID',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(1000) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(512) DEFAULT NULL COMMENT '客户端密匙',
  `scope` varchar(512) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(512) DEFAULT NULL COMMENT '客户端支持的grant_type',
  `authorities` varchar(1000) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效时间值',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '更新令牌有效时间值',
  `additional_information` varchar(512) DEFAULT NULL,
  `autoapprove` varchar(512) DEFAULT NULL COMMENT '用户是否自动Approval操作',
  `web_server_redirect_uri` varchar(512) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端列表';