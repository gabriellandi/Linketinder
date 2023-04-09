CREATE DATABASE linketinder

CREATE TABLE "pais" (
  "id" serial PRIMARY KEY,
  "nome_pais" varchar(50) NOT NULL
);

CREATE TABLE "candidatos" (
  "id" serial PRIMARY KEY,
  "nome" varchar(50) NOT NULL,
  "sobrenome" varchar(50) NOT NULL,
  "dt_nascimento" date NOT NULL,
  "email" varchar(100) NOT NULL,
  "cpf" varchar(11) NOT NULL,
  "id_pais" int NOT NULL,
  "cep" varchar(8) NOT NULL,
  "formacao" varchar(50) NOT NULL,
  "desc_candidato" varchar(1000) NOT NULL,
  "senha" varchar(50) NOT NULL
);

CREATE TABLE "competencias" (
  "id" serial PRIMARY KEY,
  "nome_competencia" varchar(50) NOT NULL
);

CREATE TABLE "user_competencias" (
  "id" serial PRIMARY KEY,
  "id_candidato" int,
  "id_vaga" int,
  "id_competencia" int NOT NULL
);

CREATE TABLE "empresas" (
  "id" serial PRIMARY KEY,
  "nome_empresa" varchar(100) NOT NULL.
  "cnpj" varchar(14) NOT NULL,
  "email_coorp" varchar(100) NOT NULL,
  "desc_empresa" varchar(1000) NOT NULL,
  "id_pais" int NOT NULL,
  "cep" varchar(8) NOT NULL,
  "senha" varchar(50) NOT NULL
);

CREATE TABLE "vagas" (
  "id" serial PRIMARY KEY,
  "id_empresa" int NOT NULL,
  "nome" varchar(100) NOT NULL,
  "desc_vaga" varchar(1000) NOT NULL,
  "data_criacao" date NOT NULL,
  "estado" varchar(50) NOT NULL,
  "cidade" varchar(100) NOT NULL
);

CREATE TABLE "curtida_empresa_candidato" (
  "id" serial PRIMARY KEY,
  "id_candidato" int,
  "id_vaga" int NOT NULL,
  "match" boolean,
  "curtida_origem" varchar(50) NOT NULL
);

ALTER TABLE "candidatos" ADD FOREIGN KEY ("id_pais") REFERENCES "pais" ("id");

ALTER TABLE "user_competencias" ADD FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id");

ALTER TABLE "user_competencias" ADD FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id");

ALTER TABLE "user_competencias" ADD FOREIGN KEY ("id_competencia") REFERENCES "competencias" ("id");

ALTER TABLE "empresas" ADD FOREIGN KEY ("id_pais") REFERENCES "pais" ("id");

ALTER TABLE "vagas" ADD FOREIGN KEY ("id_empresa") REFERENCES "empresas" ("id");

ALTER TABLE "curtida_empresa_candidato" ADD FOREIGN KEY ("id_candidato") REFERENCES "candidatos" ("id");

ALTER TABLE "curtida_empresa_candidato" ADD FOREIGN KEY ("id_vaga") REFERENCES "vagas" ("id");

-- Inserindo competencias

INSERT INTO competencias (nome_competencia) VALUES
('Java'),
('Python'),
('JavaScript'),
('C#'),
('PHP'),
('Ruby'),
('Swift'),
('Objective-C'),
('Kotlin'),
('C++'),
('R'),
('Go'),
('TypeScript'),
('Scala'),
('Perl'),
('Lua'),
('Matlab'),
('Visual Basic'),
('Assembly');

-- Inserindo países;

INSERT INTO pais (nome_pais) VALUES ('China'), ('Índia'), ('Estados Unidos'), ('Indonésia'), ('Brasil');

-- Inserindo candidatos;

INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES ('Lucas', 'Silva', '1990-04-12', 'lucas.silva@gmail.com', '12345678901', 1, '12345678', 'Engenharia', 'Experiência em desenvolvimento de software', '123456');
INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES ('Juliana', 'Souza', '1995-08-22', 'juliana.souza@hotmail.com', '23456789012', 2, '23456789', 'Administração', 'Experiência em gerenciamento de projetos', '234567');
INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES ('Pedro', 'Oliveira', '1992-03-30', 'pedro.oliveira@yahoo.com.br', '34567890123', 3, '34567890', 'Marketing', 'Experiência em marketing digital', '345678');
INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES ('Fernanda', 'Santos', '1998-01-15', 'fernanda.santos@gmail.com', '45678901234', 4, '45678901', 'Ciência da Computação', 'Experiência em programação web', '456789');
INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES ('Gustavo', 'Rocha', '1991-06-20', 'gustavo.rocha@gmail.com', '56789012345', 5, '56789012', 'Economia', 'Experiência em análise financeira', '567890');

-- Inserindo competencias nos candidatos


INSERT INTO user_competencias (id_user, id_competencia)  VALUES 
    (1, 1), (1,3), (2,4),
    (1,2), (2,5), (3,7),
    (3,1), (4,8), (5,6),
    (2,9), (5,3), (1,6),
    (4,2), (5,8), (3,10);


-- INSERINDO EMPRESAS

INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('12345678901234', 'contato@techgroup.com', 'Empresa de tecnologia focada em soluções web', 1, '12345678', 'senha123', 'Tech Group');
INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('23456789012345', 'contato@innovatek.com', 'Empresa de tecnologia especializada em inteligência artificial', 2, '23456789', 'senha234', 'Innovatek');
INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('34567890123456', 'contato@datasphere.com', 'Empresa de tecnologia focada em big data', 3, '34567890', 'senha345', 'DataSphere');
INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('45678901234567', 'contato@techwave.com', 'Empresa de tecnologia focada em desenvolvimento de software', 4, '45678901', 'senha456', 'Techwave');
INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('56789012345678', 'contato@codesphere.com', 'Empresa de tecnologia especializada em blockchain', 5, '56789012', 'senha567', 'CodeSphere');
INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES ('67890123456789', 'contato@netgenius.com', 'Empresa de tecnologia focada em redes de computadores', 1, '67890123', 'senha678', 'NetGenius');

-- INSERINDO VAGAS

INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (1, 'Desenvolvedor Full Stack', 'Procuramos um desenvolvedor Full Stack para trabalhar em projetos web.', '2022-03-11', 'SP', 'São Paulo');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (2, 'Analista de Dados', 'Procuramos um analista de dados para trabalhar com análise de dados em projetos de Machine Learning.', '2022-03-11', 'RJ', 'Rio de Janeiro');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (3, 'Engenheiro de Software', 'Procuramos um engenheiro de software para trabalhar em projetos de desenvolvimento de software.', '2022-03-11', 'SP', 'Campinas');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (4, 'Desenvolvedor Mobile', 'Procuramos um desenvolvedor Mobile para trabalhar em projetos de aplicativos Android e iOS.', '2022-03-11', 'MG', 'Belo Horizonte');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (5, 'Analista de Segurança da Informação', 'Procuramos um analista de segurança da informação para trabalhar com auditoria e testes de segurança.', '2022-03-11', 'RS', 'Porto Alegre');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (1, 'Desenvolvedor Front-end', 'Procuramos um desenvolvedor Front-end para trabalhar em projetos web.', '2022-03-11', 'SP', 'São Paulo');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (2, 'Analista de Machine Learning', 'Procuramos um analista de Machine Learning para trabalhar em projetos de análise de dados.', '2022-03-11', 'RJ', 'Rio de Janeiro');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (3, 'Arquiteto de Software', 'Procuramos um arquiteto de software para trabalhar em projetos de grande porte.', '2022-03-11', 'SP', 'Campinas');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (4, 'Desenvolvedor Back-end', 'Procuramos um desenvolvedor Back-end para trabalhar em projetos web.', '2022-03-11', 'MG', 'Belo Horizonte');
INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (5, 'Analista de Segurança Cibernética', 'Procuramos um analista de segurança cibernética para trabalhar com auditoria e testes de segurança.', '2022-03-11', 'RS', 'Porto Alegre');

-- INSERINDO REGISTROS DAS COMPETENCIAS DAS VAGAS

INSERT INTO user_competencias (id_vaga, id_competencia)  VALUES 
    (1, 1), (1,3), (1,5),
    (2,1), (3,5), (3,8),
    (4,7), (4,13), (5,1),
    (5,4), (6,3), (6,13),
    (7,2), (8,1), (9,4);

-- INSERINDO CURTIDAS DOS CANDIDATOS

INSERT INTO curtida_empresa_candidato (id_candidato, curtida_origem, id_vaga) VALUES
    (1, 'CANDIDATO', 1),
    (2, 'CANDIDATO', 4),
    (3, 'CANDIDATO', 2),
    (4, 'CANDIDATO', 3),
    (5, 'CANDIDATO', 5);

-- INSERINDO CURTIDA DAS EMPRESAS (AS EMPRESAS CURTIRAM APARTIR DAS VAGAS)

INSERT INTO curtida_empresa_candidato (id_vaga, curtida_origem, id_candidato) VALUES
    (1, 'EMPRESA', 1),
    (2, 'EMPRESA', 4),
    (3, 'EMPRESA', 4),
    (4, 'EMPRESA', 3),
    (5, 'EMPRESA', 5);

-- REALIZANDO O MATCH

-- FILTRA AS CURTIDAS EM COMUM

select candidato.id_vaga,
	candidato.id_candidato,
	candidato.curtida_origem,
	empresa.id_vaga,
	empresa.id_candidato,
	empresa.curtida_origem,
	candidato.match AS match_empresa,
	empresa.match AS match_empresa
from 
	curtida_empresa_candidato EMPRESA,
	curtida_empresa_candidato CANDIDATO
where
	empresa.curtida_origem = 'EMPRESA'
	AND candidato.curtida_origem = 'CANDIDATO'
	AND empresa.id_vaga = candidato.id_vaga
	AND empresa.id_candidato = candidato.id_candidato
	
-- ATUALIZA O CAMPO MATCH NAS CURTIDADES DE ORIGEM EMPRESA

UPDATE curtida_empresa_candidato AS empresa
SET match = TRUE
FROM curtida_empresa_candidato AS candidato
WHERE empresa.id_vaga = candidato.id_vaga
AND empresa.id_candidato = candidato.id_candidato
AND empresa.curtida_origem = 'EMPRESA'
AND candidato.curtida_origem = 'CANDIDATO';

-- ATUALIZA O CAMPO MATCH NAS CURTIDADES DE ORIGEM CANDIDATO

UPDATE curtida_empresa_candidato AS candidato
SET match = TRUE
FROM curtida_empresa_candidato AS empresa
WHERE empresa.id_vaga = candidato.id_vaga
AND empresa.id_candidato = candidato.id_candidato
AND empresa.curtida_origem = 'EMPRESA'
AND candidato.curtida_origem = 'CANDIDATO';

-- Possibilita o cascade na tabela vagas

ALTER TABLE vagas
DROP CONSTRAINT vagas_id_empresa_fkey,
ADD CONSTRAINT vagas_id_empresa_fkey
    FOREIGN KEY (id_empresa)
    REFERENCES empresas (ID)
    ON DELETE CASCADE;

ALTER TABLE user_competencias
DROP CONSTRAINT user_competencias_id_candidato_fkey,
ADD CONSTRAINT user_competencias_id_candidato_fkey
    FOREIGN KEY (id_candidato)
    REFERENCES candidatos (ID)
    ON DELETE CASCADE;

ALTER TABLE user_competencias
DROP CONSTRAINT user_competencias_id_competencia_fkey,
ADD CONSTRAINT user_competencias_id_competencia_fkey
    FOREIGN KEY (id_competencia)
    REFERENCES competencias (ID)
    ON DELETE CASCADE;

ALTER TABLE user_competencias
DROP CONSTRAINT user_competencias_id_vaga_fkey,
ADD CONSTRAINT user_competencias_id_vaga_fkey
    FOREIGN KEY (id_vaga)
    REFERENCES vagas (ID)
    ON DELETE CASCADE;

ALTER TABLE candidatos
DROP CONSTRAINT candidatos_id_pais_fkey,
  ADD CONSTRAINT candidatos_id_pais_fkey
    FOREIGN KEY (id_pais) REFERENCES pais (id) ON DELETE CASCADE;