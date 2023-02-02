create table state (id bigint not null identity(1,1) primary key, name varchar(255) not null );

insert into state (name) select distinct name_state from city;

alter table city add state_id bigint;
Go

update city set state_id = (select id from state where state.name = city.name_state)

alter table city alter column state_id bigint NOT NULL;