create table skill
(
    id         bigserial primary key,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    name       varchar(50)              not null
);

create unique index uni_skill_1 on skill (name);
