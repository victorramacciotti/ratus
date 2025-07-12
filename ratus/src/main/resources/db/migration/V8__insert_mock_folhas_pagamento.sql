-- V8__insert_mock_folhas_pagamento.sql

-- Inserindo registros de folha de pagamento mockadas

-- Folha de Pagamento para Maria Silva (Instrutora) - Maio 2025
INSERT INTO folha_pagamento (id_folha_pagamento, id_funcionario, mes_referencia, ano_referencia, data_pagamento, salario_base, valor_total_liquido, observacoes) VALUES
(UNHEX(REPLACE('1b2c3d4e-5f6a-7b8c-9d0e-1f2a3b4c5d6e', '-', '')),
 UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')),
 'MAIO', 2025, '2025-05-31', 3500.00, 3200.50, 'Pagamento normal do mês de maio.');

-- Folha de Pagamento para Maria Silva (Instrutora) - Junho 2025
INSERT INTO folha_pagamento (id_folha_pagamento, id_funcionario, mes_referencia, ano_referencia, data_pagamento, salario_base, valor_total_liquido, observacoes) VALUES
(UNHEX(REPLACE('2c3d4e5f-6a7b-8c9d-0e1f-2a3b4c5d6e7f', '-', '')),
 UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')),
 'JUNHO', 2025, '2025-06-30', 3500.00, 3350.75, 'Pagamento com bônus de desempenho.');

-- Folha de Pagamento para Ricardo Borges (Administrador) - Junho 2025
INSERT INTO folha_pagamento (id_folha_pagamento, id_funcionario, mes_referencia, ano_referencia, data_pagamento, salario_base, valor_total_liquido, observacoes) VALUES
(UNHEX(REPLACE('3d4e5f6a-7b8c-9d0e-1f2a-3b4c5d6e7f8a', '-', '')),
 UNHEX(REPLACE('e4f5a6b7-c8d9-e0f1-a2b3-c4d5e6f7a8b9', '-', '')),
 'JUNHO', 2025, '2025-06-30', 5000.00, 4700.00, 'Pagamento normal.');

-- Folha de Pagamento para Maria Silva (Instrutora) - Julho 2025 (Futuro)
INSERT INTO folha_pagamento (id_folha_pagamento, id_funcionario, mes_referencia, ano_referencia, data_pagamento, salario_base, valor_total_liquido, observacoes) VALUES
(UNHEX(REPLACE('5f6a7b8c-9d0e-1f2a-3b4c-5d6e7f8a9b0c', '-', '')),
 UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')),
 'JULHO', 2025, '2025-07-31', 3500.00, 3200.50, 'Previsão de pagamento para julho.');
