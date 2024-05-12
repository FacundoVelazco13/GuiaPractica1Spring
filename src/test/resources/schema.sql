-- auto-generated definition
create table alumno
(
    id     integer not null
        primary key,
    legajo varchar(255),
    nombre varchar(255)
);
create table docente
(
    id      integer not null
        primary key,
    salario double precision,
    nombre  varchar(255)
);
create table curso
(
    creditos            integer,
    cupo                integer,
    docente_asignado_id integer
        constraint fkhdmyboh8w2av4kv5iqk484848
            references docente,
    id                  integer not null
        primary key,
    nombre              varchar(255)
);
create table curso_alumno (curso_id integer not null, alumno_id integer not null);

create sequence alumno_seq start with 1 increment by 50;
create sequence curso_seq start with 1 increment by 50;
create sequence docente_seq start with 1 increment by 50;

alter table if exists curso_alumno add constraint FKkfvl4s9krhlfujxcynhd1qegn foreign key (alumno_id) references alumno;
alter table if exists curso_alumno add constraint FK5vv7tujgwxjxwjlmr7m59lny6 foreign key (curso_id) references curso;

