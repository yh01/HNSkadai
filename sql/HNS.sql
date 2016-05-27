drop database if exists HNS;
create database HNS character set utf8;
use HNS;
create table trn_user(
	id int primary key not null auto_increment,
	name varchar(255) unique,
	pass varchar(255)
);

create table trn_address(
	id int not null unique,
	name varchar(255),
	phone_number varchar(255),
	zip varchar(255),
	address varchar(255)
);

create table address(
	zip varchar(255),
	ken_or_capital varchar(255),
	city varchar(255),
	ward varchar(255),
	town varchar(255)
);

insert into address values
("1638001","東京都","","新宿区","西新宿"),
("2318588","神奈川県","横浜市","中区","日本大通");

create table code_test(
	name varchar(255),
	pass varchar(255)
);

insert into code_test values
("あいう","あいう"),
("test","test");

create table hex_test(
	name text,
	pass text
);