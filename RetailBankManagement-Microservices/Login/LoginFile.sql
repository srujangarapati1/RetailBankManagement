create table Login_Table(
loginId varchar(25) primary key,
password varchar(10) not null,
role varchar(10) not null
);
select * from LOGIN
drop table login
insert into LOGIN_TABLE values('srujan@gmail.com','srujan123', 'Accountant');
insert into LOGIN_TABLE values('ramu','ramu123', 'cashier');
select * from CUSTOMER
update ACCOUNT set balance = 0 where balance = 20;
insert into ACCOUNT a (a.balance) values (0) where a.balance=20;