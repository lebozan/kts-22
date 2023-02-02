INSERT into users(user_type, first_name, last_name, email, password, phone_number, address, blocked, active, profile_picture)
values ('passenger', 'Bojan', 'Cakic', 'bc@mail.com', '$2a$12$d3lz2zRcxNO/hYQfC2Ls3epOaODx8Vjfkk9pr6.vO/UT0i6GdToc.',
        '064131313', 'adresa 1', false, true, '');

INSERT into users(user_type, first_name, last_name, email, password, phone_number, address, blocked, active, profile_picture)
values ('admin', 'Zoran', 'Stankovic', 'zs@mail.com', '$2a$12$d3lz2zRcxNO/hYQfC2Ls3epOaODx8Vjfkk9pr6.vO/UT0i6GdToc.',
        '066121212', 'adresa 2', false, true, '');

INSERT into users(user_type, first_name, last_name, email, password, phone_number, address, blocked, active, profile_picture)
values ('driver', 'Nikola', 'Nikolic', 'nn@mail.com', '$2a$12$d3lz2zRcxNO/hYQfC2Ls3epOaODx8Vjfkk9pr6.vO/UT0i6GdToc.',
        '065111213', 'adresa 3', false, true, '');


insert into vehicle_type(type, price_per_km) values ('STANDARD', 100);
insert into vehicle_type(type, price_per_km) values ('LUX', 200);
insert into vehicle_type(type, price_per_km) values ('VAN', 300);


INSERT into role(id, name) values (1, 'ROLE_ADMIN');
INSERT into role(id, name) values (2, 'ROLE_PASSENGER');
INSERT into role(id, name) values (3, 'ROLE_DRIVER');


INSERT into authority(id, name) values (1, 'ADMIN_PERMISSIONS');
INSERT into authority(id, name) values (2, 'PASSENGER_PERMISSIONS');
INSERT into authority(id, name) values (3, 'DRIVER_PERMISSIONS');

insert into role_authority (role_id, authority_id) values (1, 1);
insert into role_authority (role_id, authority_id) values (2, 2);
insert into role_authority (role_id, authority_id) values (3, 3);

insert into document(name, image, driver_id) values ('vozacka dozvola', '', 3);

insert into users_documents(driver_id, documents_id) values (3, 1);

insert into vehicle (model, number_of_seats, plate_number, driver_id, vehicle_type_id, baby_flag, pets_flag)
values ('VW Golf 2', 4, 'NS 123-AB', 3, 1, true, true);

insert into driver_vehicle (vehicle_id, driver_id) values (1, 3);