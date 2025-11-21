-- ====================================
-- CREATE TABLE EMPRESA
-- ====================================
CREATE TABLE TB_EMPRESA (
    id IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(11)
);

-- ====================================
-- CREATE TABLE USUARIO
-- ====================================
CREATE TABLE TB_USUARIO (
    id IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(11),
    cpf VARCHAR(11) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- ====================================
-- CREATE TABLE CURRICULO
-- ====================================
CREATE TABLE TB_CURRICULO (
    id IDENTITY PRIMARY KEY,
    formacao VARCHAR(50),
    experiencia INT,
    nivel_ingles VARCHAR(50),
    estado VARCHAR(50),
    usuario_id BIGINT UNIQUE,
    CONSTRAINT fk_curriculo_usuario FOREIGN KEY (usuario_id) REFERENCES TB_USUARIO(id)
);

-- tabela skills do curriculo
CREATE TABLE curriculo_skills (
    curriculo_id BIGINT NOT NULL,
    skill VARCHAR(255),
    CONSTRAINT fk_curriculo_skill FOREIGN KEY (curriculo_id) REFERENCES TB_CURRICULO(id)
);

-- ====================================
-- CREATE TABLE VAGA
-- ====================================
CREATE TABLE TB_VAGA (
    id IDENTITY PRIMARY KEY,
    cargo VARCHAR(255),
    salario DOUBLE,
    data_fim DATE,
    experiencia INT,
    nivel_ingles VARCHAR(50),
    modelo_trabalho VARCHAR(50),
    formacao VARCHAR(50),
    estado VARCHAR(50),
    match INT,
    empresa_id BIGINT,
    descricao VARCHAR(MAX),
    CONSTRAINT fk_vaga_empresa FOREIGN KEY (empresa_id) REFERENCES TB_EMPRESA(id)
);


-- tabela skills da vaga
CREATE TABLE vaga_skills (
    vaga_id BIGINT NOT NULL,
    skill VARCHAR(255),
    CONSTRAINT fk_vaga_skill FOREIGN KEY (vaga_id) REFERENCES TB_VAGA(id)
);

-- ====================================
-- CREATE TABLE CANDIDATURA
-- ====================================
CREATE TABLE TB_CANDIDATURA (
    id IDENTITY PRIMARY KEY,
    vaga_id BIGINT NOT NULL,
    curriculo_id BIGINT NOT NULL,
    data_candidatura DATE,
    CONSTRAINT fk_cand_vaga FOREIGN KEY (vaga_id) REFERENCES TB_VAGA(id),
    CONSTRAINT fk_cand_curriculo FOREIGN KEY (curriculo_id) REFERENCES TB_CURRICULO(id)
);
