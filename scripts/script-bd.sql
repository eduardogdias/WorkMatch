-- ====================================
-- CREATE TABLE EMPRESA
-- ====================================
CREATE TABLE TB_EMPRESA (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(11)
);

-- ====================================
-- CREATE TABLE USUARIO
-- ====================================
CREATE TABLE TB_USUARIO (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
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
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
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
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    cargo VARCHAR(255),
    salario FLOAT,
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
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    vaga_id BIGINT NOT NULL,
    curriculo_id BIGINT NOT NULL,
    data_candidatura DATE,
    CONSTRAINT fk_cand_vaga FOREIGN KEY (vaga_id) REFERENCES TB_VAGA(id),
    CONSTRAINT fk_cand_curriculo FOREIGN KEY (curriculo_id) REFERENCES TB_CURRICULO(id)
);


-- =======================================================================================================================


-- ===============================
-- INSERT EMPRESAS
-- ===============================
INSERT INTO TB_EMPRESA (nome, cnpj, email, telefone)
VALUES
('Tech Solutions LTDA', '12345678000199', 'contato@techsolutions.com', '11987654321'),
('Global Data Corp', '98765432000155', 'rh@globaldata.com', '11912345678'),
('InovaSoft Tecnologia', '45678912000122', 'jobs@inovasoft.com', '11999887766');

-- ===============================
-- INSERT VAGAS
-- ===============================
INSERT INTO TB_VAGA
(cargo, salario, data_fim, experiencia, nivel_ingles, modelo_trabalho, formacao, estado, match, empresa_id, descricao)
VALUES
(
 'Desenvolvedor Backend Java',
 8500.00,
 '2025-12-31',
 2,
 'INTERMEDIARIO',
 'HIBRIDO',
 'GRADUACAO',
 'SP',
 80,
 1,
 'O desenvolvedor backend Java será responsável por criar e manter APIs de alta performance, colaborar com o time de produto para implementar novas funcionalidades e garantir a integração eficiente com diversos serviços internos. O dia a dia envolve participação em dailies, revisão de código e desenvolvimento contínuo de melhorias na arquitetura da plataforma.'
),
(
 'Analista de Dados',
 7000.00,
 '2025-10-15',
 1,
 'AVANCADO',
 'REMOTO',
 'FUNDAMENTAL',
 'RJ',
 75,
 2,
 'O analista de dados irá coletar, tratar e analisar dados para apoiar decisões estratégicas, construindo dashboards e relatórios automatizados. No cotidiano, trabalhará com equipes de marketing e produto, interpretando informações e propondo insights que gerem impacto direto nos resultados da empresa.'
),
(
 'Dev Frontend React',
 7800.00,
 '2025-11-20',
 2,
 'INTERMEDIARIO',
 'PRESENCIAL',
 'GRADUACAO',
 'SP',
 70,
 1,
 'O desenvolvedor frontend React será responsável pela construção de interfaces modernas e responsivas, garantindo boa experiência ao usuário e integração eficiente com APIs. No dia a dia participará de reuniões de alinhamento, desenvolverá componentes reutilizáveis e colaborará com o design para evoluir continuamente o produto.'
),
(
 'Suporte Técnico',
 3500.00,
 '2025-09-30',
 1,
 'BASICO',
 'PRESENCIAL',
 'MEDIO',
 'MG',
 35,
 3,
 'O profissional de suporte técnico prestará atendimento direto aos usuários, solucionando dúvidas, incidentes e registrando ocorrências no sistema. Sua rotina envolve abertura de chamados, testes básicos de hardware e software e acompanhamento de problemas até a resolução completa.'
),
(
 'Cientista de Dados',
 12000.00,
 '2025-12-15',
 3,
 'AVANCADO',
 'REMOTO',
 'POS',
 'SP',
 15,
 2,
 'O cientista de dados será responsável por desenvolver modelos preditivos, explorar grandes volumes de dados e construir soluções inteligentes que contribuam para decisões estratégicas. O dia a dia inclui análise estatística, experimentação, criação de pipelines e trabalho conjunto com equipes de engenharia e negócios.'
);

-- ===============================
-- INSERT SKILLS DAS VAGAS
-- ===============================

INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(1, 'Java'), (1, 'Spring Boot'), (1, 'REST API'), (1, 'SQL');

INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(2, 'Python'), (2, 'Pandas'), (2, 'ETL'), (2, 'Power BI');

INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(3, 'React'), (3, 'JavaScript'), (3, 'HTML'), (3, 'CSS');

INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(4, 'Windows'), (4, 'Redes'), (4, 'Help Desk');

INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(5, 'Machine Learning'), (5, 'Python'), (5, 'TensorFlow'), (5, 'SQL');
