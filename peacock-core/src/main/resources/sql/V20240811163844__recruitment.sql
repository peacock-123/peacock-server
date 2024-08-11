create table recruitment
(
    id                 bigserial primary key,
    title              varchar(255) not null,
    content            text         not null,
    purpose            varchar(50)  not null,
    method             varchar(50)  not null,
    contact            varchar(500) not null,
    process_type       varchar(50)  not null,
    ended_at           timestamp with time zone,
    duration           varchar(255) not null,
    interval_type      varchar(50)  not null,
    interval_frequency int
);

create table recruitment_position_group
(
    recruitment_id bigint not null,
    count          int    not null,
    sequence       int    not null
)
