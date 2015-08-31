create database securitys;

CREATE table user(
   name   varchar(16) not null PRIMARY KEY,
   pwd    varchar(32) not null,
   roleid varchar(32) not null
)

create table role(
  id       varchar(32) not null PRIMARY KEY,
  roleName varchar(16) not null
)

create table function(
   id       varchar(32) not null PRIMARY KEY,
   roleid   varchar(32) not null,
   url      varchar(32) not null
)

insert into user values("admin","123456","1");
insert into user values("tinys","123456","0");

insert into role values("1","admin角色");
insert into role values("0","普调角色");

insert into function values ("1","1","admin.jsp");
insert into function values ("2","1","index.jsp");
insert into function values ("3","0","index.jsp");