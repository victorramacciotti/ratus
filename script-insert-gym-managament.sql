-- Script para inserir dados de exemplo (mock) no banco de dados gym_management

-- Inserir dados na tabela cliente
INSERT INTO `cliente` (`cpf`, `email`, `nome`, `telefone`) VALUES
('12345678901', 'joao.silva@example.com', 'João Silva', '11987654321'),
('23456789012', 'maria.santos@example.com', 'Maria Santos', '11976543210'),
('34567890123', 'pedro.almeida@example.com', 'Pedro Almeida', '11965432109');

-- Inserir dados na tabela funcionario
INSERT INTO `funcionario` (`cpf`, `email`, `escala_trabalho`, `nome`, `salario`, `telefone`) VALUES
('45678901234', 'ana.costa@example.com', 'INTEGRAL', 'Ana Costa', 3500.00, '11954321098'),
('56789012345', 'carlos.oliveira@example.com', 'TURNO_MANHA', 'Carlos Oliveira', 2500.00, '11943210987'),
('67890123456', 'lucia.fernandes@example.com', 'TURNO_NOITE', 'Lúcia Fernandes', 3000.00, '11932109876');

-- Inserir dados na tabela exercicio
INSERT INTO `exercicio` (`descricao`, `nome`, `repeticoes`, `series`) VALUES
('Agachamento com barra livre', 'Agachamento Livre', 12, 3),
('Supino reto com halteres', 'Supino Halteres', 10, 4),
('Flexão de braço no solo', 'Flexão de Braço', 15, 3),
('Elevação lateral com halteres', 'Elevação Lateral', 12, 3);

-- Inserir dados na tabela instrutor
INSERT INTO `instrutor` (`especialidade`, `funcionario_id`) VALUES
('Musculação', 1),
('Treinamento Funcional', 2);

-- Inserir dados na tabela treino
INSERT INTO `treino` (`descricao`, `instrutor_id`) VALUES
('Treino de força para membros inferiores', 1),
('Treino de hipertrofia para membros superiores', 2),
('Treino funcional de alta intensidade', 1);

-- Inserir dados na tabela administrador
INSERT INTO `administrador` (`funcionario_id`) VALUES
(3);

-- Inserir dados na tabela recepcionista
INSERT INTO `recepcionista` (`funcionario_id`) VALUES
(3);

-- Inserir dados na tabela plano
INSERT INTO `plano` (`data_vencimento`, `tipo`, `valor`, `cliente_id`) VALUES
('2025-08-01', 'Mensal', 99.90, 1),
('2025-08-01', 'Trimestral', 249.90, 2),
('2025-08-01', 'Anual', 899.90, 3);

-- Inserir dados na tabela avaliacao_fisica
INSERT INTO `avaliacao_fisica` (`altura`, `imc`, `peso`, `cliente_id`) VALUES
(1.75, 22.86, 70.00, 1),
(1.65, 24.24, 66.00, 2),
(1.80, 23.61, 76.50, 3);

-- Inserir dados na tabela faturas_cliente
INSERT INTO `faturas_cliente` (`data_pagamento`, `metodo_pagamento`, `status_pagamento`, `valor`, `cliente_id`) VALUES
('2025-07-01', 'PIX', 'PAGO', 99.90, 1),
('2025-07-01', 'CARTAO_CREDITO', 'PENDENTE', 249.90, 2),
('2025-07-01', 'BOLETO', 'PAGO', 899.90, 3);

-- Inserir dados na tabela folha_pagamento
INSERT INTO `folha_pagamento` (`data_pagamento`, `mes_referente`, `salario_pago_mes`, `funcionario_id`) VALUES
('2025-07-05', 'JULHO', 3500.00, 1),
('2025-07-05', 'JULHO', 2500.00, 2),
('2025-07-05', 'JULHO', 3000.00, 3);

-- Inserir dados na tabela frequencia
INSERT INTO `frequencia` (`data`, `presenca`, `cliente_id`) VALUES
('2025-07-01', 1, 1),
('2025-07-01', 0, 2),
('2025-07-02', 1, 3);

-- Inserir dados na tabela exercicio_treino
INSERT INTO `exercicio_treino` (`exercicio_id`, `treino_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 2);

-- Inserir dados na tabela treino_cliente
INSERT INTO `treino_cliente` (`dias_da_semana`, `cliente_id`, `treino_id`) VALUES
('SEG,QUA,SEX', 1, 1),
('TER,QUI', 2, 2),
('SEG,TER,QUA', 3, 3);