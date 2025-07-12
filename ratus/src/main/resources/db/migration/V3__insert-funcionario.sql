-- V3__insert_funcionario.sql

-- Insere um Funcionário (ID formatado para BINARY(16))
INSERT INTO funcionario (id_funcionario, cpf_funcionario, nome_funcionario, email_funcionario, telefone_funcionario, escala_trabalho, salario_funcionario) VALUES
(UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')), -- UUID em formato BINARY(16)
 '34210875805',                                                   -- CPF válido
 'Maria Silva',
 'maria.silva@academia.com',
 '11987654321',
 'TURNO_MANHA',
 3500.00
);

-- Insere o Instrutor referenciando o mesmo ID hexadecimal
INSERT INTO instrutor (id_funcionario, especialidade_instrutor) VALUES
(UNHEX(REPLACE('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '-', '')), -- O MESMO ID em formato BINARY(16)
 'Musculação'
);

-- Exemplo de um segundo Instrutor (ID formatado para BINARY(16))
INSERT INTO funcionario (id_funcionario, cpf_funcionario, nome_funcionario, email_funcionario, telefone_funcionario, escala_trabalho, salario_funcionario) VALUES
(UNHEX(REPLACE('b1f0c1d2-0e1f-4b2a-8c3d-7e4f5a6b7c8d', '-', '')), -- UUID em formato BINARY(16)
 '85749632164',                                                   -- CPF válido
 'João Pereira',
 'joao.pereira@academia.com',
 '21998765432',
 'INTEGRAL',
 4000.00
);

INSERT INTO instrutor (id_funcionario, especialidade_instrutor) VALUES
(UNHEX(REPLACE('b1f0c1d2-0e1f-4b2a-8c3d-7e4f5a6b7c8d', '-', '')), -- O MESMO ID em formato BINARY(16)
 'Pilates'
);

-- Exemplo de um Recepcionista (ID formatado para BINARY(16))
INSERT INTO funcionario (id_funcionario, cpf_funcionario, nome_funcionario, email_funcionario, telefone_funcionario, escala_trabalho, salario_funcionario) VALUES
(UNHEX(REPLACE('d3e4f5a6-b7c8-d9e0-f1a2-b3c4d5e6f7a8', '-', '')), -- UUID em formato BINARY(16)
 '12345678910',                                                   -- CPF válido
 'Fernanda Lima',
 'fernanda.lima@academia.com',
 '81912345678',
 'TURNO_MANHA_E_TARDE',
 2200.00
);

INSERT INTO recepcionista (id_funcionario) VALUES
(UNHEX(REPLACE('d3e4f5a6-b7c8-d9e0-f1a2-b3c4d5e6f7a8', '-', '')) -- O MESMO ID em formato BINARY(16)
);

-- Exemplo de um Administrador (ID formatado para BINARY(16))
INSERT INTO funcionario (id_funcionario, cpf_funcionario, nome_funcionario, email_funcionario, telefone_funcionario, escala_trabalho, salario_funcionario) VALUES
(UNHEX(REPLACE('e4f5a6b7-c8d9-e0f1-a2b3-c4d5e6f7a8b9', '-', '')), -- UUID em formato BINARY(16)
 '10987654321',                                                   -- CPF válido
 'Ricardo Borges',
 'ricardo.borges@academia.com',
 '71987654321',
 'INTEGRAL',
 6500.00
);

-- Insere a referência na tabela 'administrador' para especializar o funcionário
INSERT INTO administrador (id_funcionario) VALUES
(UNHEX(REPLACE('e4f5a6b7-c8d9-e0f1-a2b3-c4d5e6f7a8b9', '-', '')) -- O MESMO ID em formato BINARY(16)
);
