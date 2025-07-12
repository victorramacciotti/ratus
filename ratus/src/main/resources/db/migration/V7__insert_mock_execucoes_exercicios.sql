-- V7__insert_mock_execucoes_exercicios.sql

-- Inserindo execuções de exercícios mockadas para Ana Paula Souza

-- Execução 1: Supino Reto - Semana 1
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('1a1b2c3d-4e5f-6a7b-8c9d-0e1f2a3b4c5d', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('b1c2d3e4-f5a6-b7c8-d9e0-f1a2b3c4d5e6', '-', '')),
 '2025-07-01 09:00:00',
 4, 8, 50.00, 'Bom controle, mas um pouco de dificuldade na última série.');

-- Execução 2: Remada Curvada - Semana 1
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('2b2c3d4e-5f6a-7b8c-9d0e-1f2a3b4c5d6e', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('c2d3e4f5-a6b7-c8d9-e0f1-a2b3c4d5e6f7', '-', '')),
 '2025-07-01 09:30:00',
 3, 10, 40.00, 'Forma sólida, sentir mais a contração nas costas.');

-- Execução 3: Agachamento Búlgaro - Semana 1
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('3c3d4e5f-6a7b-8c9d-0e1f-2a3b4c5d6e7f', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('d3e4f5a6-b7c8-d9e0-f1a2-b3c4d5e6f7a8', '-', '')),
 '2025-07-03 10:00:00',
 3, 12, 0.00, 'Ainda adaptando, bom equilíbrio.');

-- Execução 4: Supino Reto - Semana 2 (Progresso)
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('4d4e5f6a-7b8c-9d0e-1f2a-3b4c5d6e7f8a', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('b1c2d3e4-f5a6-b7c8-d9e0-f1a2b3c4d5e6', '-', '')),
 '2025-07-08 09:00:00',
 4, 8, 55.00, 'Conseguiu aumentar a carga, excelente!');

-- Execução 5: Remada Curvada - Semana 2 (Progresso)
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('5e5f6a7b-8c9d-0e1f-2a3b-4c5d6e7f8a9b', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('c2d3e4f5-a6b7-c8d9-e0f1-a2b3c4d5e6f7', '-', '')),
 '2025-07-08 09:30:00',
 3, 10, 42.50, 'Mais fácil que na semana anterior.');

-- Execução 6: Cadeira Extensora - Semana 1
INSERT INTO execucao_exercicio (id_execucao_exercicio, id_cliente, id_exercicio_treino, data_execucao, series_realizadas, repeticoes_realizadas, carga_realizada_kg, observacoes) VALUES
(UNHEX(REPLACE('6f6a7b8c-9d0e-1f2a-3b4c-5d6e7f8a9b0c', '-', '')),
 UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 UNHEX(REPLACE('f1a2b3c4-d5e6-f7a8-b9c0-d1e2f3a4b5c7', '-', '')),
 '2025-07-10 10:00:00',
 4, 15, 30.00, 'Sentiu bem o quadríceps.');
