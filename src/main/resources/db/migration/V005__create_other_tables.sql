create table city (
	id bigint not null identity(1, 1) primary key,
	name_city varchar(80) not null,
	name_state varchar(80) not null,
);

create TABLE group_table(
	id bigint not null identity(1, 1) primary key,
	name varchar(255) not null,
);

create TABLE payment_form(
	id bigint not null identity(1, 1) primary key,
	description varchar(255) not null,
);

create TABLE permission(
	id bigint not null identity(1,1) primary key, 
	name varchar(255) not null,
	description varchar(255),
	);