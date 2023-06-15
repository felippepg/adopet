create table perfis (
    id bigint not null auto_increment,
    descricao varchar(50) not null,

    primary key(id)
);

CREATE TABLE usuario (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(255) not null,
    perfil_id bigint not null,
    primary key(id),
    constraint fk_usuario_perfil_id foreign key(perfil_id) references perfis(id)
);

CREATE TABLE abrigo (
    id bigint not null auto_increment,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    usuario_id bigint not null,

    primary key(id),
    constraint fk_abrigo_usuario_id foreign key(usuario_id) references usuario(id)


);