-- V1__create_database.sql

-- Desabilitar verificações de chaves estrangeiras temporariamente para permitir DROP TABLE
SET FOREIGN_KEY_CHECKS = 0;

-- Dropar tabelas na ordem inversa de dependência para evitar erros de chave estrangeira
DROP TABLE IF EXISTS avaliacao_fisica;
DROP TABLE IF EXISTS faturas_cliente;
DROP TABLE IF EXISTS folha_pagamento;
DROP TABLE IF EXISTS frequencia;
DROP TABLE IF EXISTS plano;
DROP TABLE IF EXISTS exercicio_treino; -- Nova tabela de junção
DROP TABLE IF EXISTS treino;
DROP TABLE IF EXISTS exercicio;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS instrutor;
DROP TABLE IF EXISTS recepcionista;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS `user`; -- 'user' é uma palavra reservada, usar aspas

-- Habilitar verificações de chaves estrangeiras
SET FOREIGN_KEY_CHECKS = 1;

-- Tabela funcionario (usando BINARY(16) para UUIDs)
CREATE TABLE funcionario (
    id_funcionario BINARY(16) NOT NULL,
    cpf_funcionario VARCHAR(255) NOT NULL UNIQUE,
    email_funcionario VARCHAR(255) NOT NULL UNIQUE,
    escala_trabalho ENUM('INTEGRAL','TURNO_MANHA','TURNO_MANHA_E_NOITE','TURNO_MANHA_E_TARDE','TURNO_NOITE','TURNO_TARDE','TURNO_TARDE_E_NOITE'),
    nome_funcionario VARCHAR(255) NOT NULL,
    salario_funcionario DECIMAL(10,2),
    telefone_funcionario VARCHAR(255),
    PRIMARY KEY (id_funcionario)
);

-- Tabela administrador (FK para funcionario)
CREATE TABLE administrador (
    id_funcionario BINARY(16) NOT NULL,
    PRIMARY KEY (id_funcionario),
    CONSTRAINT FK_ADMIN_FUNCIONARIO FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario)
);

-- Tabela instrutor (FK para funcionario)
CREATE TABLE instrutor (
    id_funcionario BINARY(16) NOT NULL,
    especialidade_instrutor VARCHAR(255),
    PRIMARY KEY (id_funcionario),
    CONSTRAINT FK_INSTRUTOR_FUNCIONARIO FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario)
);

-- Tabela recepcionista (FK para funcionario)
CREATE TABLE recepcionista (
    id_funcionario BINARY(16) NOT NULL,
    PRIMARY KEY (id_funcionario),
    CONSTRAINT FK_RECEPCIONISTA_FUNCIONARIO FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario)
);

-- Tabela cliente (usando BINARY(16) para UUIDs)
CREATE TABLE cliente (
    id_cliente BINARY(16) NOT NULL,
    cpf_cliente VARCHAR(255) NOT NULL UNIQUE,
    email_cliente VARCHAR(255) UNIQUE,
    nome_cliente VARCHAR(255) NOT NULL,
    telefone_cliente VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_cliente)
);

-- Tabela avaliacao_fisica (FK para cliente - ID agora é BINARY(16))
CREATE TABLE avaliacao_fisica (
    id BIGINT NOT NULL AUTO_INCREMENT, -- Mantido como BIGINT se não for UUID na entidade
    altura DECIMAL(3,2),
    imc DECIMAL(5,2),
    peso DECIMAL(5,2),
    id_cliente BINARY(16), -- Alterado para BINARY(16)
    PRIMARY KEY (id),
    CONSTRAINT FK_AVALIACAO_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- Tabela exercicio (com novos campos e enums)
CREATE TABLE exercicio (
    id_exercicio BINARY(16) NOT NULL,
    nome_exercicio VARCHAR(255) NOT NULL UNIQUE,
    descricao_exercicio VARCHAR(500),
    tipo_equipamento ENUM('HALTE_LIVRE','BARRA_LIVRE','MAQUINA','CABO','PESO_CORPORAL','ELASTICO','OUTRO'),
    grupo_muscular_principal ENUM('PEITO','COSTAS','OMBRO','BICEPS','TRICEPS','PERNAS','GLUTEOS','PANTURRILHA','ABDOMEN','ANTEBRACO','CARDIO','CORPO_INTEIRO','OUTRO'),
    url_video_demonstracao VARCHAR(255),
    PRIMARY KEY (id_exercicio)
);

-- Tabela treino (com id_cliente, nome_treino e unique constraint)
CREATE TABLE treino (
    id_treino BINARY(16) NOT NULL,
    nome_treino VARCHAR(255) NOT NULL,
    descricao_treino MEDIUMTEXT,
    dia_da_semana ENUM('SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO','DOMINGO') NOT NULL,
    id_instrutor BINARY(16),
    id_cliente BINARY(16) NOT NULL, -- FK para cliente
    PRIMARY KEY (id_treino),
    CONSTRAINT FK_TREINO_INSTRUTOR FOREIGN KEY (id_instrutor) REFERENCES instrutor (id_funcionario),
    CONSTRAINT FK_TREINO_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
    CONSTRAINT UQ_CLIENTE_DIA_SEMANA UNIQUE (id_cliente, dia_da_semana) -- Garante um treino por cliente por dia
);

-- Tabela exercicio_treino (tabela de junção com atributos adicionais)
CREATE TABLE exercicio_treino (
    id_exercicio_treino BINARY(16) NOT NULL,
    id_exercicio BINARY(16) NOT NULL,
    id_treino BINARY(16) NOT NULL,
    quantidade_series INT NOT NULL,
    quantidade_repeticoes INT NOT NULL,
    carga_sugerida_kg DECIMAL(5,2),
    observacoes_adicionais VARCHAR(255),
    ordem_no_treino INT,
    PRIMARY KEY (id_exercicio_treino),
    CONSTRAINT FK_EX_TREINO_EXERCICIO FOREIGN KEY (id_exercicio) REFERENCES exercicio (id_exercicio),
    CONSTRAINT FK_EX_TREINO_TREINO FOREIGN KEY (id_treino) REFERENCES treino (id_treino)
);

-- Tabela faturas_cliente (FK para cliente - ID agora é BINARY(16))
CREATE TABLE faturas_cliente (
    id BIGINT NOT NULL AUTO_INCREMENT, -- Mantido como BIGINT se não for UUID na entidade
    data_pagamento DATE NOT NULL,
    metodo_pagamento ENUM('BOLETO','CARTAO_CREDITO','CARTAO_DEBITO','DINHEIRO','PIX'),
    status_pagamento ENUM('ATRASADO','CANCELADO','ESTORNADO','PAGO','PARCIAL','PENDENTE'),
    valor DECIMAL(10,2) NOT NULL,
    id_cliente BINARY(16), -- Alterado para BINARY(16)
    PRIMARY KEY (id),
    CONSTRAINT FK_FATURAS_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- Tabela folha_pagamento (FK para funcionario - ID é BINARY(16))
CREATE TABLE folha_pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT, -- Mantido como BIGINT se não for UUID na entidade
    data_pagamento DATE,
    mes_referente ENUM('JANEIRO','FEVEREIRO','MARÇO','ABRIL','MAIO','JUNHO','JULHO','AGOSTO','SETEMBRO','OUTUBRO','NOVEMBRO','DEZEMBRO'),
    salario_pago_mes DECIMAL(10,2),
    id_funcionario BINARY(16), -- Alterado para BINARY(16)
    PRIMARY KEY (id),
    CONSTRAINT FK_FOLHA_FUNCIONARIO FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario)
);

-- Tabela frequencia (FK para cliente - ID agora é BINARY(16))
CREATE TABLE frequencia (
    id BIGINT NOT NULL AUTO_INCREMENT, -- Mantido como BIGINT se não for UUID na entidade
    data DATE,
    presenca BOOLEAN NOT NULL,
    id_cliente BINARY(16), -- Alterado para BINARY(16)
    PRIMARY KEY (id),
    CONSTRAINT FK_FREQUENCIA_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- Tabela plano (FK para cliente - ID agora é BINARY(16))
CREATE TABLE plano (
    id BIGINT NOT NULL AUTO_INCREMENT, -- Mantido como BIGINT se não for UUID na entidade
    data_vencimento DATE NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    id_cliente BINARY(16) UNIQUE, -- Alterado para BINARY(16)
    PRIMARY KEY (id),
    CONSTRAINT FK_PLANO_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- Tabela user (para autenticação)
CREATE TABLE `user` ( -- 'user' é uma palavra reservada, usar aspas
    id BINARY(16) NOT NULL,
    login VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(20),
    PRIMARY KEY (id)
);
