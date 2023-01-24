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
	select * from Sections;
	create table Sections (
	id integer PRIMARY KEY IDENTITY(1,1),
	hits int,
	source VARCHAR (100),
	section_name VARCHAR (100),
	subsection_name VARCHAR (100),
	 pub_date VARCHAR (100),
	document_type VARCHAR (100),
	lead_paragraph  VARCHAR (100),
	status VARCHAR (100)
	);
	Alter table Sections ALTER COLUMN  lead_paragraph VARCHAR (5000);

		create table Articls (
	id integer PRIMARY KEY IDENTITY(1,1),
	hits int,
	source VARCHAR (100),
	section_name VARCHAR (100),
	subsection_name VARCHAR (100),
	 pub_date VARCHAR (100),
	document_type VARCHAR (100),
	lead_paragraph  VARCHAR (100),
	status VARCHAR (100)
	);