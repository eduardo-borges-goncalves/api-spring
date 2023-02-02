alter table city add constraint fk_city_state foreign key (state_id) references state(id);
Go

alter table city drop column name_state;