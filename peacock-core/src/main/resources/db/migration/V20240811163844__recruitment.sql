create table recruitment
(
    id                 bigserial primary key,
    created_at         timestamp with time zone not null,
    updated_at         timestamp with time zone not null,
    type               varchar(50)              not null,
    title              varchar(255)             not null,
    content            text                     not null,
    purpose            varchar(50)              not null,
    method             varchar(50)              not null,
    contact            varchar(500)             not null,
    process_type       varchar(50)              not null,
    ended_at           timestamp with time zone,
    duration           varchar(255)             not null,
    interval_type      varchar(50)              not null,
    interval_frequency int,
    author_id          bigint                   not null
);

create table recruitment_position_group
(
    id             bigserial primary key,
    created_at     timestamp with time zone not null,
    updated_at     timestamp with time zone not null,
    recruitment_id bigint                   not null,
    count          int                      not null,
    sequence       int                      not null
);

create table recruitment_position
(
    created_at                    timestamp with time zone not null,
    updated_at                    timestamp with time zone not null,
    recruitment_position_group_id bigint                   not null,
    position_id                   bigint                   not null,
    sequence                      int                      not null
);

create table recruitment_skill
(
    created_at                    timestamp with time zone not null,
    updated_at                    timestamp with time zone not null,
    recruitment_position_group_id bigint                   not null,
    skill_id                      bigint                   not null,
    sequence                      int                      not null
);
