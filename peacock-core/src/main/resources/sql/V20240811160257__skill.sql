create table skill
(
    id   bigserial primary key,
    name varchar(50) not null
);

create unique index uni_skill_1 on skill (name);
