   -- V6__insert_mock_treinos.sql

   -- Inserindo um Treino de Exemplo para a Ana Paula (Segunda-feira)
   INSERT INTO treino (id_treino, nome_treino, descricao_treino, dia_da_semana, id_instrutor, id_cliente) VALUES
   (UNHEX(REPLACE('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6e', '-', '')),
    'Treino de Força A - Segunda',
    'Treino focado em força e hipertrofia para membros superiores e pernas.',
    'SEGUNDA',
    UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')),
    UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', ''))
   );

   -- Inserindo ExerciciosTreino para o Treino de Força A
   INSERT INTO exercicio_treino (id_exercicio_treino, id_exercicio, id_treino, quantidade_series, quantidade_repeticoes, carga_sugerida_kg, observacoes_adicionais, ordem_no_treino) VALUES
   (UNHEX(REPLACE('b1c2d3e4-f5a6-b7c8-d9e0-f1a2b3c4d5e6', '-', '')),
    UNHEX(REPLACE('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', '-', '')),
    UNHEX(REPLACE('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6e', '-', '')),
    4, 8, 50.00, 'Foco na técnica e controle do movimento.', 1),

   (UNHEX(REPLACE('c2d3e4f5-a6b7-c8d9-e0f1-a2b3c4d5e6f7', '-', '')),
    UNHEX(REPLACE('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', '-', '')),
    UNHEX(REPLACE('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6e', '-', '')),
    3, 10, 40.00, 'Contrair bem as escápulas no final do movimento.', 2),

   (UNHEX(REPLACE('d3e4f5a6-b7c8-d9e0-f1a2-b3c4d5e6f7a8', '-', '')),
    UNHEX(REPLACE('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', '-', '')),
    UNHEX(REPLACE('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6e', '-', '')),
    3, 12, 0.00, 'Usar peso corporal ou halteres leves para iniciar.', 3);

   -- Inserindo um segundo Treino de Exemplo para a Ana Paula (Quarta-feira)
   INSERT INTO treino (id_treino, nome_treino, descricao_treino, dia_da_semana, id_instrutor, id_cliente) VALUES
   (UNHEX(REPLACE('e1f2a3b4-c5d6-e7f8-a9b0-c1d2e3f4a5b6', '-', '')),
    'Treino de Pernas - Quarta',
    'Treino intenso para membros inferiores.',
    'QUARTA',
    UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')),
    UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', ''))
   );

   -- Inserindo ExerciciosTreino para o Treino de Pernas
   INSERT INTO exercicio_treino (id_exercicio_treino, id_exercicio, id_treino, quantidade_series, quantidade_repeticoes, carga_sugerida_kg, observacoes_adicionais, ordem_no_treino) VALUES
   (UNHEX(REPLACE('f1a2b3c4-d5e6-f7a8-b9c0-d1e2f3a4b5c7', '-', '')),
    UNHEX(REPLACE('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a', '-', '')),
    UNHEX(REPLACE('e1f2a3b4-c5d6-e7f8-a9b0-c1d2e3f4a5b6', '-', '')),
    4, 15, 30.00, 'Manter a contração no topo.', 1),

   (UNHEX(REPLACE('a2b3c4d5-e6f7-a8b9-c0d1-e2f3a4b5c6d8', '-', '')),
    UNHEX(REPLACE('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '-', '')),
    UNHEX(REPLACE('e1f2a3b4-c5d6-e7f8-a9b0-c1d2e3f4a5b6', '-', '')),
    3, 60, 0.00, 'Segurar por 60 segundos.', 2);
