create table data(
    login char,
    password char);
alter table data
modify column login char(32);
alter table data
modify column password char(32);
insert into data (login, password) values ('admin','admin');
select * from data;
