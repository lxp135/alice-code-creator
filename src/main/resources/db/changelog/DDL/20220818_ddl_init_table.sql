-- liquibase formatted sql
-- changeset contact@liuxp.me:20220818_ddl_init_table

DROP TABLE IF EXISTS base_dictionary;
CREATE TABLE base_dictionary  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  group_code varchar(255) NOT NULL COMMENT '分组编码',
  group_name varchar(255) NOT NULL COMMENT '分组名称',
  dict_code varchar(255) NOT NULL COMMENT '字典编码',
  dict_name varchar(255) NOT NULL COMMENT '字典名称',
  is_enable tinyint(1) NULL DEFAULT NULL COMMENT '启停标志 0：正常、1：禁用',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  create_user varchar(255) NULL DEFAULT NULL COMMENT '创建用户',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  update_user varchar(255) NULL DEFAULT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '数据字典表';

DROP TABLE IF EXISTS base_menu;
CREATE TABLE base_menu  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  menu_key varchar(255) NOT NULL COMMENT '菜单英文名称',
  menu_name varchar(255) NULL DEFAULT NULL COMMENT '菜单中文名称',
  menu_description varchar(255) NULL DEFAULT NULL COMMENT '菜单描述',
  menu_url varchar(255) NULL DEFAULT NULL COMMENT '菜单地址',
  menu_icon varchar(255) NULL DEFAULT NULL COMMENT '菜单图标',
  menu_type tinyint(1) NOT NULL COMMENT '菜单类型',
  menu_level tinyint(1) NOT NULL COMMENT '菜单级别',
  menu_order int(4) NULL DEFAULT NULL COMMENT '菜单顺序',
  menu_parent_id bigint(21) NULL DEFAULT NULL COMMENT '父级主键',
  menu_parent_name varchar(255) NULL DEFAULT NULL COMMENT '父级菜单名称',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '菜单基本信息表';

DROP TABLE IF EXISTS base_role;
CREATE TABLE base_role  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  role_name varchar(255) NULL DEFAULT NULL COMMENT '角色名称',
  role_description varchar(255) NULL DEFAULT NULL COMMENT '角色描述',
  is_enable tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0:正常 1:禁用',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  create_user varchar(255) NULL DEFAULT NULL COMMENT '创建用户',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  update_user varchar(255) NULL DEFAULT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '角色基本信息表';

DROP TABLE IF EXISTS base_role_menu_rel;
CREATE TABLE base_role_menu_rel  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  role_id bigint(20) NOT NULL COMMENT '角色表主键',
  menu_id bigint(20) NOT NULL COMMENT '菜单表主键',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  create_user varchar(255) NULL DEFAULT NULL COMMENT '创建用户',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  update_user varchar(255) NULL DEFAULT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '角色与菜单关系表';

DROP TABLE IF EXISTS base_role_user_rel;
CREATE TABLE base_role_user_rel  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  role_id bigint(20) NOT NULL COMMENT '角色表主键',
  user_id bigint(20) NOT NULL COMMENT '用户表主键',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  create_user varchar(255) NULL DEFAULT NULL COMMENT '创建用户',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  update_user varchar(255) NULL DEFAULT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '角色与用户关系表';

DROP TABLE IF EXISTS base_user;
CREATE TABLE base_user  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_account varchar(255) NOT NULL COMMENT '用户账号',
  user_name varchar(255) NULL DEFAULT NULL COMMENT '用户名称',
  user_password varchar(255) NOT NULL COMMENT '用户密码',
  user_sex tinyint(1) NULL DEFAULT 0 COMMENT '性别 0:未填写 1:男 2:女',
  user_face varchar(255) NULL DEFAULT NULL COMMENT '用户头像',
  user_email varchar(255) NULL DEFAULT NULL COMMENT '电子邮件',
  user_wechat varchar(255) NULL DEFAULT NULL COMMENT '微信号',
  user_phone varchar(255) NULL DEFAULT NULL COMMENT '手机号',
  user_birthday date NULL DEFAULT NULL COMMENT '出生日期',
  user_signature varchar(255) NULL DEFAULT NULL COMMENT '签名',
  is_enable tinyint(1) NULL DEFAULT 0 COMMENT '用户状态 0:禁用 1:正常',
  create_time datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  create_user varchar(255) NULL DEFAULT NULL COMMENT '创建用户',
  update_time datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  update_user varchar(255) NULL DEFAULT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '用户基本信息表';

DROP TABLE IF EXISTS generator_config_datasource;
CREATE TABLE generator_config_datasource  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  datasource_name varchar(255) NOT NULL COMMENT '数据源名称',
  datasource_type varchar(255) NOT NULL COMMENT '数据源类型（MySQL,Oracle,SQLServer）',
  driver_class_name varchar(255) NOT NULL COMMENT 'JDBC驱动',
  url varchar(255) NOT NULL COMMENT '数据源地址',
  username varchar(50) NULL DEFAULT NULL COMMENT '用户名',
  password varchar(50) NULL DEFAULT NULL COMMENT '密码',
  owner_user_id bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  owner_user_name varchar(255) NOT NULL COMMENT '所有者用户名称',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '数据源配置';

DROP TABLE IF EXISTS generator_config_group;
CREATE TABLE generator_config_group  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  group_name varchar(50) NOT NULL COMMENT '分组名称',
  group_desc text NOT NULL COMMENT '分组内容描述',
  template_amount int(4) NULL DEFAULT NULL COMMENT '模板总数',
  default_sign varchar(255) NULL DEFAULT NULL COMMENT '默认签名',
  default_package varchar(255) NULL DEFAULT NULL COMMENT '默认包路径',
  default_table_prefix varchar(255) NULL DEFAULT NULL COMMENT '默认表前缀',
  default_field_unique varchar(255) NULL DEFAULT NULL COMMENT '唯一标识字段',
  default_field_ext varchar(255) NULL DEFAULT NULL COMMENT '扩展标识字段（可以存多个，按照逗号分割）',
  default_field_effective varchar(255) NULL DEFAULT NULL COMMENT '逻辑删除标识字段名称',
  owner_user_id bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  owner_user_name varchar(255) NOT NULL COMMENT '所有者用户名称',
  is_public tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否公开 0：私有 1：公开',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '模板分组';

DROP TABLE IF EXISTS generator_config_individual;
CREATE TABLE generator_config_individual  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  default_sign varchar(255) NULL DEFAULT NULL COMMENT '默认签名',
  default_package varchar(255) NULL DEFAULT NULL COMMENT '默认包路径',
  default_table_prefix varchar(255) NULL DEFAULT NULL COMMENT '默认表前缀',
  default_group_id bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '默认分组编号',
  default_group_name varchar(50) NULL DEFAULT NULL COMMENT '默认分组名称',
  default_datasource_id bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '默认数据源编号',
  default_datasource_name varchar(50) NULL DEFAULT NULL COMMENT '默认数据源名称',
  default_field_unique varchar(255) NULL DEFAULT NULL COMMENT '唯一标识字段',
  default_field_ext varchar(255) NULL DEFAULT NULL COMMENT '扩展标识字段（可以存多个，按照逗号分割）',
  default_field_effective varchar(255) NULL DEFAULT NULL COMMENT '逻辑删除标识字段名称',
  owner_user_id bigint(20) UNSIGNED NOT NULL COMMENT '所有者用户编号',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '用户个性化配置';

DROP TABLE IF EXISTS generator_config_mapping;
CREATE TABLE generator_config_mapping  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  datasource_type varchar(255) NOT NULL COMMENT '数据源类型（MySQL,Oracle,SQLServer）',
  db_type varchar(20) NOT NULL COMMENT '数据库类型',
  jdbc_type varchar(20) NOT NULL COMMENT 'jdbc类型',
  java_type varchar(20) NOT NULL COMMENT 'java类型',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '数据类型映射';

DROP TABLE IF EXISTS generator_config_template;
CREATE TABLE generator_config_template  (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  template_code varchar(50) NOT NULL COMMENT '模板英文名',
  template_name varchar(50) NOT NULL COMMENT '模板中文名',
  template_content text NOT NULL COMMENT '模板内容',
  file_path varchar(20) NOT NULL COMMENT '生成地址相对路径',
  file_name varchar(20) NOT NULL COMMENT '文件后缀与扩展名',
  group_id bigint(20) NULL DEFAULT NULL COMMENT '分组编号',
  group_name varchar(50) NULL DEFAULT NULL COMMENT '分组名称',
  create_time datetime(0) NOT NULL COMMENT '创建时间',
  create_user varchar(255) NOT NULL COMMENT '创建用户',
  update_time datetime(0) NOT NULL COMMENT '更新时间',
  update_user varchar(255) NOT NULL COMMENT '更新用户',
  is_delete tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 0：未删除 1：已删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '代码生成器模板';