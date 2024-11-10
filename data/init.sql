create database game_and_love;

use game_and_love;

create table galgame
(
    id                bigint auto_increment
        primary key,
    translated_name   varchar(2000) null comment '译名',
    info              varchar(5000) null comment '信息',
    score             double        null comment '得分',
    `rank`            bigint        null comment '排名',
    number_of_ratings varchar(500)  null comment '评分人数',
    subject_id        bigint        null comment '游戏编号',
    original_name     varchar(2000) null comment '原名',
    img_url           varchar(1000) null comment '图片地址'
);

create table galgame_tier_maker_rank
(
    id         bigint auto_increment comment '主键'
        primary key,
    user_uin   varchar(20) null comment 'qq号码',
    rank_name  varchar(50) null comment '排行名',
    rank_level bigint      null comment '排行等级'
);

create table galgame_tier_maker_subject
(
    id                 bigint auto_increment comment '主键'
        primary key,
    galgame_subject_id bigint        null comment '游戏编号',
    user_uin           varchar(20)   null comment 'qq号码',
    rank_level         bigint        null comment '排行等级',
    rank_order         bigint        null comment '排行序号',
    galgame_img_url    varchar(1000) null comment '图片地址'
);

create table galgame_twelve_voting
(
    id                      bigint auto_increment comment '主键'
        primary key,
    galgame_subject_id      bigint           null comment 'galgame编号',
    votes_cast_count        bigint default 0 not null comment '作品票数',
    user_uin                varchar(20)      null comment 'qq号',
    user_nick               varchar(100)     null comment 'qq名称',
    update_time             datetime         null comment '更新时间',
    galgame_translated_name varchar(2000)    null comment 'galgame译名'
) comment '十二神器投票';

create table member
(
    id         bigint auto_increment comment '主键'
        primary key,
    uin        varchar(20)  null comment 'qq号码',
    nick       varchar(100) null comment 'qq名称',
    card       varchar(100) null comment '群名称',
    gender     varchar(4)   null comment '性别',
    join_time  datetime     null comment '入群时间',
    generation bigint       null comment '时代',
    role       varchar(10)  null comment '身份'
);

create table user
(
    id         bigint auto_increment comment '主键'
        primary key,
    uin        varchar(20)  null comment 'qq号',
    nick       varchar(100) null comment 'qq名',
    card       varchar(100) null comment '群名称',
    gender     varchar(4)   null comment '性别',
    join_time  datetime     null comment '入群时间',
    generation bigint       null comment '时代',
    role       varchar(10)  null comment '身份'
);