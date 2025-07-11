-- Insere um Funcionário (ID formatado para BINARY(16))
INSERT INTO funcionario (id, cpf, nome, email, telefone, escala_trabalho, salario) VALUES
(0xa0eebc999c0b4ef8bb6d6bb9bd380a11, -- UUID em formato hexadecimal
 '34210875805',                         -- CPF válido
 'Maria Silva',
 'maria.silva@academia.com',
 '11987654321',
 'TURNO_MANHA',
 3500.00
);

-- Insere o Instrutor referenciando o mesmo ID hexadecimal
INSERT INTO instrutor (funcionario_id, especialidade) VALUES
(0xa0eebc999c0b4ef8bb6d6bb9bd380a11, -- O MESMO ID hexadecimal
 'Musculação'
);

-- Exemplo de um segundo Instrutor (ID formatado para BINARY(16))
INSERT INTO funcionario (id, cpf, nome, email, telefone, escala_trabalho, salario) VALUES
(0xb1f0c1d20e1f4b2a8c3d7e4f5a6b7c8d, -- UUID em formato hexadecimal
 '85749632164',                         -- CPF válido
 'João Pereira',
 'joao.pereira@academia.com',
 '21998765432',
 'INTEGRAL',
 4000.00
);

INSERT INTO instrutor (funcionario_id, especialidade) VALUES
(0xb1f0c1d20e1f4b2a8c3d7e4f5a6b7c8d, -- O MESMO ID hexadecimal
 'Pilates'
);

-- Exemplo de um Recepcionista (ID formatado para BINARY(16))
INSERT INTO funcionario (id, cpf, nome, email, telefone, escala_trabalho, salario) VALUES
(0xd3e4f5a6b7c8d9e0f1a2b3c4d5e6f7a8, -- UUID em formato hexadecimal
 '12345678910',                         -- CPF válido
 'Fernanda Lima',
 'fernanda.lima@academia.com',
 '81912345678',
 'TURNO_MANHA_E_TARDE',
 2200.00
);

INSERT INTO recepcionista (funcionario_id) VALUES
(0xd3e4f5a6b7c8d9e0f1a2b3c4d5e6f7a8 -- O MESMO ID hexadecimal
);

-- Exemplo de um Administrador (ID formatado para BINARY(16))
INSERT INTO funcionario (id, cpf, nome, email, telefone, escala_trabalho, salario) VALUES
(0xe4f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9, -- UUID em formato hexadecimal
 '10987654321',                         -- CPF válido
 'Ricardo Borges',
 'ricardo.borges@academia.com',
 '71987654321',
 'INTEGRAL',
 6500.00
);

-- 2. Insere a referência na tabela 'administrador' para especializar o funcionário
INSERT INTO administrador (funcionario_id) VALUES
(0xe4f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9 -- O MESMO ID hexadecimal
);