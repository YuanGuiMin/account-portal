CREATE DATABASE IF NOT EXISTS `account_portal` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `account`(
    `id`          VARCHAR(20)  NOT NULL     COMMENT 'id',
    `username`    VARCHAR(20)  NOT NULL     COMMENT 'name for login',
    `password`    VARCHAR(255) NOT NULL     COMMENT 'password for login',
    `state`       TINYINT(1)   NOT NULL     COMMENT 'account state',
    `deleted`     TINYINT(1)   NOT NULL     COMMENT 'account delete state',
    `create_time` DATETIME     NOT NULL     COMMENT 'create time',
    `update_time` DATETIME     DEFAULT NULL COMMENT 'update time',
    `delete_time` DATETIME     DEFAULT NULL COMMENT 'delete time',
    PRIMARY KEY(`id`),
    INDEX(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user`(
    `id`             VARCHAR(20)  NOT NULL     COMMENT 'id',
    `account_id`     VARCHAR(20)  NOT NULL     COMMENT 'account id',
    `nickname`       VARCHAR(32)  NOT NULL     COMMENT 'user nickname',
    `head_img`       TEXT         DEFAULT NULL COMMENT 'user head image',
    `background_img` TEXT         DEFAULT NULL COMMENT 'user background image',
    `description`    VARCHAR(100) DEFAULT NULL COMMENT 'user description',
    `gender`         TINYINT(1)   DEFAULT NULL COMMENT 'user gender',
    `create_time`    DATETIME     NOT NULL     COMMENT 'create time',
    `update_time`    DATETIME     DEFAULT NULL COMMENT 'update time',
    PRIMARY KEY(`id`),
    INDEX(`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;