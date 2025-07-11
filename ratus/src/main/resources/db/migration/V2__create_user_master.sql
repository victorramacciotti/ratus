-- Adicionar o primeiro usuário que será administrador
INSERT INTO `user` (`login`, `password`, `role`) VALUES(
    'MASTER',
    '$2a$10$ZDH/rZJWz73RSOQ6FzwfrOTz7lPkzfBTKej1.EB5w2qqtocwSOzFa',
    'ADMIN'
);