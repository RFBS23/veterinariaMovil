DROP DATABASE IF EXISTS veterinaria;
create database veterinaria;
use veterinaria;

create table clientes(
	idcliente int primary key auto_increment,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    dni char(8) not null,
    claveacceso varchar(100) not null
);
insert into clientes (nombres, apellidos, dni, claveacceso) values
					('teresa', 'pereza', '45653245', '12345'),
					('julia', 'perez', '98653245', '12345');
select * from clientes;

delimiter $$
create procedure spu_clientes_login(in _dni char(8))
begin
	select nombres, apellidos, dni
    from clientes
    where dni = _dni;
end $$
call spu_clientes_login('45653245');

delimiter $$
create procedure spu_clientes_registrar(
	in _nombres varchar(50),
    in _apellidos varchar(50),
    in _dni char(8),
    in _claveacceso varchar(100)
) begin
insert into clientes (nombres, apellidos, dni, claveacceso) values
	(_nombres, _apellidos, _dni, _claveacceso);
end $$
call spu_clientes_registrar('mario', 'bros', '85213469', '12345');

delimiter $$
create procedure spu_clientes_listar()
begin
	select idcliente, concat(nombres, ' ', apellidos), dni
    from clientes
    order by idcliente asc;
end $$
call spu_clientes_listar();


create table animales(
	idanimal int primary key auto_increment,
    nombreanimal varchar(100) not null
);
select * from animales;

create table razas(
	idraza int primary key auto_increment,
    idanimal int not null,
    nombreraza varchar(50),
    constraint fk_idanimal_razas foreign key (idanimal) references animales (idanimal)
);
select * from razas;

create table mascotas(
	idmascota int primary key auto_increment,
    idcliente int not null,
    idraza int not null,
    nombre varchar(50) not null,
    fotografia varchar(200) null,
    color varchar(100) not null,
    genero varchar(12) not null,
    constraint fk_idcliente_mascotas foreign key (idcliente) references clientes (idcliente),
    constraint fk_idraza_mascotas foreign key (idraza) references razas (idraza)
);
select * from mascotas;