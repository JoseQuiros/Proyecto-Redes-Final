use algebraRelacional

create table PROVEDORES(
p# int not null,pnombre varchar(30)
,categoria varchar(30), ciudad varchar(30), primary key (p#) )

create table ARTICULOS(
t# int not null,tnombre varchar(30)
,ciudad varchar(30), primary key (t#))


create table COMPONENTES(
c# int not null,cnombre varchar(30)
,color varchar(30), peso int,ciudad varchar(30), primary key (c#) )

create table ENVIOS(
p# int not null,c# int not null,t# int not null,
cantidad int,primary key (p#),foreign key (c#) references COMPONENTES(c#)
, foreign key (t#) references ARTICULOS(t#))

