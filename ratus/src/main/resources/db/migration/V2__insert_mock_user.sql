-- Adicionar o primeiro usuário que será administrador
INSERT INTO `user` (`id`, `login`, `password`, `role`) VALUES(
    0xe4f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9,
    'MASTER',
    '$2a$10$ZDH/rZJWz73RSOQ6FzwfrOTz7lPkzfBTKej1.EB5w2qqtocwSOzFa',
    'ADMIN'
);

-- Adicionar o usuário JOAO_INSTRUTOR (INSTRUTOR)
INSERT INTO `user` (`id`, `login`, `password`, `role`) VALUES(
    UNHEX(REPLACE('c5d6e7f8-a9b0-c1d2-e3f4-a5b6c7d8e9f0', '-', '')), -- Novo UUID gerado para este usuário
    'instrutor_joao',
    '$2a$10$eWUyf5k/ppDoRsQNUfMMxON85J.oFyzM8wkLMFkQ3RPIbWs/otEHK',
    'INSTRUCTOR'
)