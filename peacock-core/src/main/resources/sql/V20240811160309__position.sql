create table position
(
    id   bigserial primary key,
    name varchar(50) not null
);

create unique index uni_position_1 on position (name);
