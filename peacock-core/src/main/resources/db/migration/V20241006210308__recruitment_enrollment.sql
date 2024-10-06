create table recruitment_enrollment
(
    recruitment_id  bigint                   not null,
    account_id      bigint                   not null,
    position_id     bigint                   not null,
    contact_method  varchar(50)              not null,
    contact_content varchar(500)             not null,
    resume_link     varchar(500)             not null,
    created_at      timestamp with time zone not null,
    primary key (recruitment_id, account_id)
);
