create table account
(
    id            bigserial primary key,
    email         varchar(500) not null,
    auth_provider varchar(50)  not null
);

create unique index uni_account_1 on account (email);
