-- V5__insert_mock_clientes.sql

-- Inserindo clientes mockados
INSERT INTO cliente (id_cliente, cpf_cliente, nome_cliente, email_cliente, telefone_cliente) VALUES
(UNHEX(REPLACE('8a9b0c1d-2e3f-4a5b-6c7d-8e9f0a1b2c3d', '-', '')),
 '12345678901',
 'Ana Paula Souza',
 'ana.souza@email.com',
 '11991234567'),

(UNHEX(REPLACE('9b0c1d2e-3f4a-5b6c-7d8e-9f0a1b2c3d4e', '-', '')),
 '09876543210',
 'Bruno Costa',
 'bruno.costa@email.com',
 '21987654321'),

(UNHEX(REPLACE('a0b1c2d3-e4f5-6a7b-8c9d-0e1f2a3b4c5d', '-', '')),
 '11223344556',
 'Carla Oliveira',
 'carla.oliveira@email.com',
 '31976543210');
