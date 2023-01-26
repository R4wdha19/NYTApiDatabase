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
published_date varchar(100),
rank int,
updated varchar(100));
drop table authors;

	create table Sections (
	id integer PRIMARY KEY IDENTITY(1,1),
	hits int,
	source VARCHAR (5000),
	section_name VARCHAR (5000),
	subsection_name VARCHAR (5000),
	 pub_date VARCHAR (5000),
	document_type VARCHAR (5000),
	lead_paragraph  VARCHAR (5000),
	status VARCHAR (5000)
	);
	Alter table Sections ALTER COLUMN  lead_paragraph VARCHAR (5000);
	drop table Sections;
		create table Articles (
	id integer PRIMARY KEY IDENTITY(1,1),
	hits int,
	source VARCHAR (5000),
	section_name VARCHAR (5000),
	subsection_name VARCHAR (5000),
	 pub_date VARCHAR (5000),
	document_type VARCHAR (5000),
	lead_paragraph  VARCHAR (5000),
	status VARCHAR (5000),
	name  VARCHAR (5000),
	value VARCHAR (5000),
	rank int
	);

		select * from authors;
	select * from Sections;
	
	select * from Articles;
	delete from Articles where id is not null;
	drop table Articles;

	-- What are the top 5 sections with the most articles.
	select Distinct subsection_name, document_type,COUNT(*) AS 'NumberOFArticles'
	from Sections group by document_type,subsection_name;

--How many articles were written by each author?
Alter table articles add author_id int;
Alter table articles add Section_id int;




	 
	
	
	