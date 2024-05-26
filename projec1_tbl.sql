create database projectdb;

use projectdb;

create table movie(
	rnum varchar(5) not null primary key,
	movieNm varchar(40) not null,
	movieCd varchar(20) not null);

    
select*from movie;


create table mDetail(
		movieNm varchar(40) not null primary key,
		openDt varchar (10) not null,
		genreNm varchar(10) not null,
		showTm varchar(5) not null,
		directors varchar(30),
		actors varchar(500));
        
        
select *from mDetail;
