-- V1__create_database.sql

-- Tabela funcionario (usando BINARY(16) para UUIDs, sem AUTO_INCREMENT)
CREATE TABLE funcionario (
    id BINARY(16) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    escala_trabalho ENUM('INTEGRAL','TURNO_MANHA','TURNO_MANHA_E_NOITE','TURNO_MANHA_E_TARDE','TURNO_NOITE','TURNO_TARDE','TURNO_TARDE_E_NOITE'),
    nome VARCHAR(255) NOT NULL,
    salario DECIMAL(10,2),
    telefone VARCHAR(255),
    PRIMARY KEY (id)
);

-- Tabela administrador (FK para funcionario)
CREATE TABLE administrador (
    funcionario_id BINARY(16) NOT NULL,
    PRIMARY KEY (funcionario_id),
    CONSTRAINT FK_ADMIN_FUNCIONARIO FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
);

-- Tabela instrutor (FK para funcionario)
CREATE TABLE instrutor (
    funcionario_id BINARY(16) NOT NULL,
    especialidade VARCHAR(255),
    PRIMARY KEY (funcionario_id),
    CONSTRAINT FK_INSTRUTOR_FUNCIONARIO FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
);

-- Tabela recepcionista (FK para funcionario)
CREATE TABLE recepcionista (
    funcionario_id BINARY(16) NOT NULL,
    PRIMARY KEY (funcionario_id),
    CONSTRAINT FK_RECEPCIONISTA_FUNCIONARIO FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
);

-- Tabela cliente (usando BIGINT com AUTO_INCREMENT)
CREATE TABLE cliente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela avaliacao_fisica
CREATE TABLE avaliacao_fisica (
    id BIGINT NOT NULL AUTO_INCREMENT,
    altura DECIMAL(3,2),
    imc DECIMAL(5,2),
    peso DECIMAL(5,2),
    cliente_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_AVALIACAO_CLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

-- Tabela exercicio
CREATE TABLE exercicio (
    id BIGINT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(500),
    nome VARCHAR(255) NOT NULL,
    repeticoes INT NOT NULL,
    series INT NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela treino
CREATE TABLE treino (
    id BIGINT NOT NULL AUTO_INCREMENT,
    descricao MEDIUMTEXT,
    instrutor_id BINARY(16), -- FK para instrutor (que é FK de funcionario)
    PRIMARY KEY (id),
    CONSTRAINT FK_TREINO_INSTRUTOR FOREIGN KEY (instrutor_id) REFERENCES instrutor (funcionario_id)
);

-- Tabela exercicio_treino (tabela de junção para N:M entre exercicio e treino)
CREATE TABLE exercicio_treino (
    id BIGINT NOT NULL AUTO_INCREMENT,
    exercicio_id BIGINT NOT NULL,
    treino_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_EXERCICIO_TREINO_EXERCICIO FOREIGN KEY (exercicio_id) REFERENCES exercicio (id),
    CONSTRAINT FK_EXERCICIO_TREINO_TREINO FOREIGN KEY (treino_id) REFERENCES treino (id)
);

-- Tabela faturas_cliente
CREATE TABLE faturas_cliente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_pagamento DATE NOT NULL,
    metodo_pagamento ENUM('BOLETO','CARTAO_CREDITO','CARTAO_DEBITO','DINHEIRO','PIX'),
    status_pagamento ENUM('ATRASADO','CANCELADO','ESTORNADO','PAGO','PARCIAL','PENDENTE'),
    valor DECIMAL(10,2) NOT NULL,
    cliente_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_FATURAS_CLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

-- Tabela folha_pagamento
CREATE TABLE folha_pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_pagamento DATE,
    mes_referente ENUM('JANEIRO','FEVEREIRO','MARÇO','ABRIL','MAIO','JUNHO','JULHO','AGOSTO','SETEMBRO','OUTUBRO','NOVEMBRO','DEZEMBRO'),
    salario_pago_mes DECIMAL(10,2),
    funcionario_id BINARY(16),
    PRIMARY KEY (id),
    CONSTRAINT FK_FOLHA_FUNCIONARIO FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
);

-- Tabela frequencia
CREATE TABLE frequencia (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATE,
    presenca BOOLEAN NOT NULL, -- BIT(1) em MySQL pode ser interpretado como BOOLEAN em JPA
    cliente_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_FREQUENCIA_CLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

-- Tabela plano
CREATE TABLE plano (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_vencimento DATE NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    cliente_id BIGINT UNIQUE, -- UNIQUE KEY para cliente_id se um cliente só pode ter um plano
    PRIMARY KEY (id),
    CONSTRAINT FK_PLANO_CLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

-- Tabela treino_cliente (tabela de junção para N:M entre cliente e treino)
CREATE TABLE treino_cliente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    dias_da_semana VARCHAR(255),
    cliente_id BIGINT,
    treino_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_TREINO_CLIENTE_CLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    CONSTRAINT FK_TREINO_CLIENTE_TREINO FOREIGN KEY (treino_id) REFERENCES treino (id)
);

-- Tabela user (para autenticação, pode ser usada para login de funcionários ou outros usuários)
CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) UNIQUE, -- Login deve ser único
    password VARCHAR(255),
    role VARCHAR(20), -- Ou use ENUM('ADMIN', 'USER') se mais genérico
    PRIMARY KEY (id)
);