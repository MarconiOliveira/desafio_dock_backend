create table tb_terminal (
	id integer not null,
	serial varchar(20) not null,
	model varchar(20) not null,
	sam integer,
	ptid integer,
	plat integer,
	version varchar(20) not null,
	mxr integer,
	mxf  integer,
	primary key (id)
);