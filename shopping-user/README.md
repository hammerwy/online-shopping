# user service module
用户服务模块
## 建表sql
```sql
create database if not exists shopping;
-- user table
create table t_user
(
    user_id       varchar(40)                           not null comment '用户id'
        primary key,
    username      varchar(32) default ''                not null comment '用户名称',
    password      varchar(64)                           null comment '用户密码',
    phone         varchar(16)                           null comment '用户手机',
    mail          varchar(32)                           null comment '用户邮箱',
    licence_pic   varchar(512)                          null comment '用户营业执照照片',
    register_time timestamp   default CURRENT_TIMESTAMP null comment '用户注册的时间',
    user_type     tinyint                               null comment '用户类型：1，个人用户；2，企业用户；3，管理员',
    user_state    tinyint     default 1                 null comment '用户状态：1，启用；2，禁用',
    role_id       int(10)                               null comment '对应的角色id',
    constraint idx_phone
        unique (phone)
)
    comment '用户信息表' charset = utf8;
```
```sql
-- role table
create table t_role
(
    role_id   int(10) auto_increment comment '角色id'
        primary key,
    role_name varchar(32) null comment '角色名称',
    role_desc varchar(64) null comment '角色描述'
)
    comment '用户角色表' charset = utf8;
```
```sql
-- role permission table
create table t_role_permission
(
    id            int(10) auto_increment comment '自增id'
        primary key,
    role_id       int(10) null comment '角色id',
    permission_id int(10) null comment '权限id'
)
    comment '角色权限关联表' charset = utf8;

create index idx_role_id
    on t_role_permission (role_id);
```
```sql
-- permission table
create table t_permission
(
    permission_id   int(10) auto_increment comment '权限id'
        primary key,
    permission_name varchar(32) null comment '权限名称',
    permission_desc varchar(64) null comment '权限描述'
)
    comment '权限表' charset = utf8;
    
```
```sql
-- menu table 
create table t_menu
(
    id        int(10) auto_increment comment '菜单自增id'
        primary key,
    menu_name varchar(16)  null comment '菜单名称',
    url       varchar(128) null comment '菜单对应页面的url',
    parent_id int(10)      null comment '父类菜单id'
)
    comment '菜单表' charset = utf8;

create index idx_user_id
    on t_menu (menu_name);
```
```sql
create table t_location
(
    id        varchar(32)  not null comment '地址id'
        primary key,
    user_id   int(10)      null comment '用户id',
    location  varchar(128) null comment '地点',
    post_code varchar(10)  null comment '邮编',
    phone     varchar(11)  null comment '手机号',
    name      varchar(32)  null comment '名称'
)
    comment '用户地址表' charset = utf8;

create index idx_user_id
    on t_location (user_id);

```