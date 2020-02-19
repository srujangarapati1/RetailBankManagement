
create table Login_E(UserId varchar2(15),
password varchar2(20),
role varchar2(20) check(role in('cashier','execute')),
loginTime timestamp
);
create table Customer_E(custId number(9) primary key,
CustomerName varchar2(20) not null,
ssnId number(9) not null unique,
Age number(3) not null,
AddressLine1 varchar2(20) not null,
AddressLine2 varchar2(20) not null,
City varchar2(20) not null,
State varchar2(20) not null
);
alter table customer_e add constraint uq ssnId unique;
select max(custId) from Customer_E;
select * from login_e;
delete from LOGIN_E

 drop table Login_E

 create sequence acc_E
 start with 1
 increment by 1;

 create sequence cust_E
 start with 100
 increment by 1;
 
 create table Account_E(CustId number(9) not null,
 AccountId number(9) not null,
 AccountType varchar2(20) check(AccountType in('Savings','Current')),
 Amount number(20) not null,
 AccountStatus varchar2(20) DEFAULT 'Active',
 constraint fk_E foreign key(CustId) references Customer_E(custId)
 )
 drop table account_e;
select * from account_e
 select password from Login_E where UserId='123456789'
 insert into Login_E values(1234567890,'maruthi12345','execute',SYSDATE);
  insert into Login_E values(1234567899,'veera12345','cashier',SYSDATE);
 update Login_E set loginTime=SYSDATE where UserId='1234567890';
 
 
 insert into Customer_E values(1465251,'maruthi',1245,25,'banagahjhsgakj',
 'jsdfuiaeu','banagalore','kereala');
 
 insert into Account_E(CustId,AccountId,AccountType,Amount) values(105,
 12,'Savings',2000);
 select * from Account_E;
 select role from Login_E where UserId=1234567890
 
 create table AccountStatement(
  SourceAccountId number(9),
  TargetAccountId number(9),
 Amount number(20,2) not null,
 TransactionDate timestamp
 );
 select customerId from Customer_e
 insert into AccountStatement values(2,6,100,sysdate);
 drop table AccountStatement;
 select * from AccountStatement where SourceAccountId=2 OR TargetAccountId=4 order by TransactionDate
 select SOURCEACCOUNTID,TARGETACCOUNTID,AMOUNT,TO_CHAR(TRANSACTIONDATE,'DD-MM-YYYY') from AccountStatement where SourceAccountId=2 OR TargetAccountId=2 order by TransactionDate