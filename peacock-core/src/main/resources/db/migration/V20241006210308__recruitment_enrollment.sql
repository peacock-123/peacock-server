create table recruitment_enrollment
(
    recruitment_id bigint                   not null,
    account_id     bigint                   not null,
    created_at     timestamp with time zone not null,
    primary key (recruitment_id, account_id)
);
