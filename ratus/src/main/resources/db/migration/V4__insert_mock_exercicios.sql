-- V4__insert_mock_exercicios.sql

-- Inserindo exercícios mockados
INSERT INTO exercicio (id_exercicio, nome_exercicio, descricao_exercicio, tipo_equipamento, grupo_muscular_principal, url_video_demonstracao) VALUES
(UNHEX(REPLACE('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', '-', '')),
 'Supino Reto com Barra',
 'Exercício fundamental para o peito, utilizando barra e banco reto.',
 'BARRA_LIVRE',
 'PEITO',
 'https://www.youtube.com/watch?v=supino-reto'),

(UNHEX(REPLACE('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', '-', '')),
 'Remada Curvada com Barra',
 'Exercício para costas, focando na espessura e força.',
 'BARRA_LIVRE',
 'COSTAS',
 'https://www.youtube.com/watch?v=remada-curvada'),

(UNHEX(REPLACE('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', '-', '')),
 'Agachamento Búlgaro',
 'Exercício unilateral para pernas e glúteos, utilizando o peso corporal ou halteres.',
 'PESO_CORPORAL',
 'PERNAS',
 'https://www.youtube.com/watch?v=agachamento-bulgaro'),

(UNHEX(REPLACE('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a', '-', '')),
 'Cadeira Extensora',
 'Exercício isolado para quadríceps, utilizando máquina.',
 'MAQUINA',
 'PERNAS',
 'https://www.youtube.com/watch?v=cadeira-extensora'),

(UNHEX(REPLACE('5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', '-', '')),
 'Rosca Direta com Halteres',
 'Exercício isolado para bíceps, utilizando halteres.',
 'HALTE_LIVRE',
 'BICEPS',
 'https://www.youtube.com/watch?v=rosca-direta-halteres'),

(UNHEX(REPLACE('6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', '-', '')),
 'Elevação Lateral com Halteres',
 'Exercício isolado para ombros, focando no deltoide lateral.',
 'HALTE_LIVRE',
 'OMBRO',
 'https://www.youtube.com/watch?v=elevacao-lateral'),

(UNHEX(REPLACE('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '-', '')),
 'Prancha Abdominal',
 'Exercício isométrico para fortalecimento do core.',
 'PESO_CORPORAL',
 'ABDOMEN',
 'https://www.youtube.com/watch?v=prancha-abdominal');
