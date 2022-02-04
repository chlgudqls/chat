create database chatdb;
use chatdb;

create table chat
(
	cno int primary key auto_increment comment '채팅번호',
	cdate datetime default now() comment '작성일자',
	cname varchar(100) comment '작성자명',
	cnote text comment '채팅내용'
);

insert into chat (cname,cnote)
value ('홍길동','안녕하세요?1'),
('홍길동','안녕하세요?2'),
('dd213홍길동','안녕하세요?3');