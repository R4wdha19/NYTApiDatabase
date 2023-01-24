use ApiDB;
DROP TABLE Books;

create table authors (
id integer PRIMARY KEY IDENTITY(1,1), 
publisher varchar(100),
name varchar(100), 
title varchar(100), 
author varchar(100),

	list_name varchar(100) ,
	published_date_description varchar(100),
	published_date varchar(100) ,
	updated varchar(100));

	select * from authors;

	create table Sections (
