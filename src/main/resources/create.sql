CREATE DATABASE IF NOT EXISTS book;
USE book;

CREATE TABLE `book` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二手书id',
    `title` varchar(255) DEFAULT NULL COMMENT '书名',
    `price` int(11) DEFAULT NULL COMMENT '价格（以角为单位）',
    `img_src` varchar(255) DEFAULT NULL COMMENT '图片路径',
    `comment` text COMMENT '二手书简介',
    `status` int(11) DEFAULT NULL COMMENT '状态 0-上架 1-下架 2-已售出',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='二手书';

CREATE TABLE `order` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
     `uid` int(11) DEFAULT NULL COMMENT '订单的用户id',
     `create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
     `price` int(11) DEFAULT NULL COMMENT '订单总价',
     `phone` varchar(255) DEFAULT NULL COMMENT '订单联系人电话',
     `address` varchar(255) DEFAULT NULL COMMENT '订单地址',
     `status` smallint(6) DEFAULT NULL COMMENT '订单状态 0-未确定 1-已经确定 2-已完成',
     `accept_name` varchar(255) DEFAULT NULL COMMENT '接收人姓名',
     `bid` int(11) DEFAULT NULL COMMENT '书本id',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(255) DEFAULT NULL COMMENT '用户名',
    `is_admin` smallint(8) DEFAULT NULL COMMENT '是否是管理员',
    `password` varchar(255) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

INSERT INTO `book`.`user`(`name`, `is_admin`, `password`) VALUES ('admin', 1, '21232f297a57a5a743894a0e4a801fc3');
