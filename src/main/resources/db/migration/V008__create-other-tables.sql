    create table group_permission (
       group_id bigint not null,
        permission_id bigint not null
    );

    create table product (
       id bigint identity not null,
        description varchar(255),
        is_active bit not null,
        name varchar(255) not null,
        price numeric(19,2) not null,
        restaurant_id bigint not null,
        primary key (id)
    );

    create table restaurant (
       id bigint identity not null,
        active bit not null,
        address_complement varchar(255),
        address_neighborhood varchar(255),
        address_number varchar(255),
        address_street varchar(255),
        address_zip_code varchar(255),
        adress_city_id bigint,
        create_date_time time not null,
        freight_rate numeric(19,2) not null,
        is_open bit not null,
        name varchar(255) not null,
        update_date_time time not null,
        cuisine_id bigint not null,
        primary key (id)
    );

    create table restaurant_payment_form (
       restaurant_id bigint not null,
        payment_form_id bigint not null
    );

    create table user_group (
       user_id bigint not null,
        group_id bigint not null
    );

    create table user_table (
       id bigint identity not null,
        create_date_time datetime2 not null,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    );

    alter table group_permission 
       add constraint fk_permission_id 
       foreign key (permission_id) 
       references permission;

    alter table group_permission 
       add constraint fk_group_id_permission
       foreign key (group_id) 
       references group_table;

    alter table product 
       add constraint fk_restaurant_id_product 
       foreign key (restaurant_id) 
       references restaurant;

    alter table restaurant 
       add constraint fk_city_id 
       foreign key (adress_city_id) 
       references city;

    alter table restaurant 
       add constraint fk_cuisine_id 
       foreign key (cuisine_id) 
       references cuisine;

    alter table restaurant_payment_form 
       add constraint fk_payment_id 
       foreign key (payment_form_id) 
       references payment_form;

    alter table restaurant_payment_form 
       add constraint fk_restaurant_id_payment 
       foreign key (restaurant_id) 
       references restaurant;

    alter table user_group 
       add constraint fk_group_id 
       foreign key (group_id) 
       references group_table;

    alter table user_group 
       add constraint fk_user_id 
       foreign key (user_id) 
       references user_table;
