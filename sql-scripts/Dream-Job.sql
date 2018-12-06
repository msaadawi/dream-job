CREATE DATABASE IF NOT EXISTS `dj`;
USE `dj`;

DROP TABLE IF EXISTS `company`;
create table `company`(
`cid` int(11) not null AUTO_INCREMENT,
`name` varchar(255) default null,
`year_founded` varchar(255) default null,
`company_type` varchar(255) default null,
`company_size` varchar(255) default null,
`headquarters` varchar(255) default null,
`website` varchar(255) default null,
`company_logo` mediumblob default null,
constraint pk_company primary key (`cid`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL unique,
  `phone_numb` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `cv` mediumblob default null,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `jobid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `workplace` varchar(255) default null,
  `exp_level` varchar(255) DEFAULT NULL,
  `post_date` date DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `job_desc` mediumtext default null,
  `numb_of_applicants` int(11) DEFAULT null,
  `uploader_id` int(11) default null,
  PRIMARY KEY (`jobid`),
  constraint fk_company foreign key(`company`) references company(`cid`), 
  constraint fk_uploader_id foreign key(`uploader_id`) references user(`uid`)
) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`(
  `uid` int(11) not null,
  `jobid` int(11) not null,
  constraint fk_uid_apps foreign key (`uid`) references user(`uid`),
  constraint fk_jid_apps foreign key (`jobid`) references job(`jobid`),
  CONSTRAINT pk_apps PRIMARY KEY(`uid`,`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `saved_job`;
CREATE TABLE `saved_job`(
  `uid` int(11) not null,
  `jobid` int(11) not null,
  constraint fk_uid_saved_jobs foreign key (`uid`) references user(`uid`),
  constraint fk_jid_saved_jobs foreign key (`jobid`) references job(`jobid`),
  CONSTRAINT pk_apps PRIMARY KEY(`uid`,`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists`user_company`;
create table `user_company`(
	`uid` int(11) not null,
    `cid` int(11) not null,
    constraint pk_user_company primary key(`uid`,`cid`),
	constraint fk_uid_user_company foreign key (`uid`) references user(`uid`),
	constraint fk_cid_user_company foreign key (`cid`) references company(`cid`) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

