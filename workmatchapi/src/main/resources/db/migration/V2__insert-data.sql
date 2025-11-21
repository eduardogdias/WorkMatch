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
(cargo, salario, data_fim, experiencia, nivel_ingles, modelo_trabalho, formacao, estado, match, empresa_id)
VALUES
('Desenvolvedor Backend Java', 8500.00, DATE '2025-12-31', 2, 'INTERMEDIARIO', 'HIBRIDO', 'GRADUACAO', 'SP', 80, 1),
('Analista de Dados', 7000.00, DATE '2025-10-15', 1, 'AVANCADO', 'REMOTO', 'FUNDAMENTAL', 'RJ', 75, 2),
('Dev Frontend React', 7800.00, DATE '2025-11-20', 2, 'INTERMEDIARIO', 'PRESENCIAL', 'GRADUACAO', 'SP', 70, 1),
('Suporte Técnico', 3500.00, DATE '2025-09-30', 1, 'BASICO', 'PRESENCIAL', 'MEDIO', 'MG', 35, 3),
('Cientista de Dados', 12000.00, DATE '2025-12-15', 3, 'AVANCADO', 'REMOTO', 'POS', 'SP', 15, 2);

-- ===============================
-- INSERT SKILLS DAS VAGAS
-- ===============================

-- vaga 1: Backend Java
INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(1, 'Java'), (1, 'Spring Boot'), (1, 'REST API'), (1, 'SQL');

-- vaga 2: Analista de Dados
INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(2, 'Python'), (2, 'Pandas'), (2, 'ETL'), (2, 'Power BI');

-- vaga 3: Frontend React
INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(3, 'React'), (3, 'JavaScript'), (3, 'HTML'), (3, 'CSS');

-- vaga 4: Suporte
INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(4, 'Windows'), (4, 'Redes'), (4, 'Help Desk');

-- vaga 5: Cientista de Dados
INSERT INTO vaga_skills (vaga_id, skill)
VALUES
(5, 'Machine Learning'), (5, 'Python'), (5, 'TensorFlow'), (5, 'SQL');
