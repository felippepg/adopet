CREATE TABLE perfis (
    id bigint not null auto_increment,
    descricao varchar(100) not null,
    primary key(id)
);

CREATE TABLE usuarios (
    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(100),
    perfis_id bigint not null,
    primary key(id),
    constraint fk_usuario_perfis_id foreign key(perfis_id) references perfis(id)
);

CREATE TABLE tutores (
    id bigint not null auto_increment,
    nome varchar(100),
    cpf varchar(20),
    perfis_id bigint not null,
    primary key(id),
    constraint fk_tutor_perfis_id foreign key(perfis_id) references perfis(id)
);
