
create table users (id bigint generated by default as identity, name varchar(255), email varchar(255) not null, role varchar(255));
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

