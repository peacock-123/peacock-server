create table position
(
    id         bigserial primary key,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    name       varchar(50)              not null
);

create unique index uni_position_1 on position (name);
