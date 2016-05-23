# language:pt

Funcionalidade: Gerar Historico Funcional Com Jasig Cas

  Cenário: Usuário autenticado pelo CAS
    Dado que o usuário ${portal.cas.user.test} foi autenticado pelo CAS
    Quando o usuário ${portal.cas.user.test} altera o domínio da aplicação
    Então o historico de banco é gerado para usuario ${portal.cas.user.test}
