-- Configurar ENGINE, CHARSET e COLLATE globalmente
SET default_storage_engine=InnoDB;
SET NAMES utf8mb4;
SET character_set_database=utf8mb4;
SET collation_database=utf8mb4_0900_ai_ci;

-- Agrupar todos os DROP TABLE IF EXISTS
DROP TABLE IF EXISTS `administrador`;
DROP TABLE IF EXISTS `avaliacao_fisica`;
DROP TABLE IF EXISTS `cliente`;
DROP TABLE IF EXISTS `exercicio`;
DROP TABLE IF EXISTS `exercicio_treino`;
DROP TABLE IF EXISTS `faturas_cliente`;
DROP TABLE IF EXISTS `folha_pagamento`;
DROP TABLE IF EXISTS `frequencia`;
DROP TABLE IF EXISTS `funcionario`;
DROP TABLE IF EXISTS `instrutor`;
DROP TABLE IF EXISTS `plano`;
DROP TABLE IF EXISTS `recepcionista`;
DROP TABLE IF EXISTS `treino`;
DROP TABLE IF EXISTS `treino_cliente`;

-- Criar tabelas sem dependências primeiro
-- Tabela: cliente
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cpf` (`cpf`),
  UNIQUE KEY `UK_email` (`email`)
);

-- Tabela: funcionario
CREATE TABLE `funcionario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `escala_trabalho` enum('INTEGRAL','TURNO_MANHA','TURNO_MANHA_E_NOITE','TURNO_MANHA_E_TARDE','TURNO_NOITE','TURNO_TARDE','TURNO_TARDE_E_NOITE') DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cpf` (`cpf`),
  UNIQUE KEY `UK_email` (`email`)
);

-- Tabela: exercicio
CREATE TABLE `exercicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(500) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `repeticoes` int NOT NULL,
  `series` int NOT NULL,
  PRIMARY KEY (`id`)
);

-- Criar tabelas com dependências
-- Tabela: administrador
CREATE TABLE `administrador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `funcionario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_funcionario_id` (`funcionario_id`)
);

-- Tabela: avaliacao_fisica
CREATE TABLE `avaliacao_fisica` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `altura` decimal(3,2) DEFAULT NULL,
  `imc` decimal(5,2) DEFAULT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: faturas_cliente
CREATE TABLE `faturas_cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_pagamento` date NOT NULL,
  `metodo_pagamento` enum('BOLETO','CARTAO_CREDITO','CARTAO_DEBITO','DINHEIRO','PIX') DEFAULT NULL,
  `status_pagamento` enum('ATRASADO','CANCELADO','ESTORNADO','PAGO','PARCIAL','PENDENTE') DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: folha_pagamento
CREATE TABLE `folha_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_pagamento` date DEFAULT NULL,
  `mes_referente` enum('ABRIL','AGOSTO','DEZEMBRO','FEVEREIRO','JANEIRO','JULHO','JUNHO','MAIO','MARÇO','NOVEMBRO','OUTUBRO','SETEMBRO') DEFAULT NULL,
  `salario_pago_mes` decimal(10,2) DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: frequencia
CREATE TABLE `frequencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `presenca` bit(1) NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: instrutor
CREATE TABLE `instrutor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `especialidade` varchar(255) DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_funcionario_id` (`funcionario_id`)
);

-- Tabela: plano
CREATE TABLE `plano` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_vencimento` date NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cliente_id` (`cliente_id`)
);

-- Tabela: recepcionista
CREATE TABLE `recepcionista` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `funcionario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_funcionario_id` (`funcionario_id`)
);

-- Tabela: treino
CREATE TABLE `treino` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` mediumtext,
  `instrutor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: exercicio_treino
CREATE TABLE `exercicio_treino` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exercicio_id` bigint NOT NULL,
  `treino_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

-- Tabela: treino_cliente
CREATE TABLE `treino_cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dias_da_semana` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `treino_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Adicionar chaves estrangeiras
ALTER TABLE `administrador`
  ADD CONSTRAINT `fk_administrador_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);

ALTER TABLE `avaliacao_fisica`
  ADD CONSTRAINT `fk_avaliacao_fisica_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

ALTER TABLE `faturas_cliente`
  ADD CONSTRAINT `fk_faturas_cliente_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

ALTER TABLE `folha_pagamento`
  ADD CONSTRAINT `fk_folha_pagamento_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);

ALTER TABLE `frequencia`
  ADD CONSTRAINT `fk_frequencia_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

ALTER TABLE `instrutor`
  ADD CONSTRAINT `fk_instrutor_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);

ALTER TABLE `plano`
  ADD CONSTRAINT `fk_plano_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

ALTER TABLE `recepcionista`
  ADD CONSTRAINT `fk_recepcionista_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);

ALTER TABLE `treino`
  ADD CONSTRAINT `fk_treino_instrutor` FOREIGN KEY (`instrutor_id`) REFERENCES `instrutor` (`id`);

ALTER TABLE `exercicio_treino`
  ADD CONSTRAINT `fk_exercicio_treino_exercicio` FOREIGN KEY (`exercicio_id`) REFERENCES `exercicio` (`id`),
  ADD CONSTRAINT `fk_exercicio_treino_treino` FOREIGN KEY (`treino_id`) REFERENCES `treino` (`id`);

ALTER TABLE `treino_cliente`
  ADD CONSTRAINT `fk_treino_cliente_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_treino_cliente_treino` FOREIGN KEY (`treino_id`) REFERENCES `treino` (`id`);