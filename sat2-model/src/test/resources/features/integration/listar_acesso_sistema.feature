# language:pt

Funcionalidade: Listar Sistemas

  #Fluxo padrão
  Cenário: Usuário acessa funcionalidade
    Dado que o usuário ${portal.cas.user.test} está previamente autenticado
    Então o usuário ${portal.cas.user.test} é identificado pelo Portal

  @drop
  Cenário: Portal lista acessos do usuário
    Dado que foi previamente concedido ao usuário ${portal.cas.user.test} os acessos dos sistemas
      | sistema_1 | perfil_a |
      | sistema_2 | perfil_b |
      | sistema_3 | perfil_c |
    Quando usuário ${portal.cas.user.test} acessa o Portal de forma autenticada
    Então o Portal obtém uma lista contendo todos sistemas que o usuário ${portal.cas.user.test} possui acesso
      | sistema_1 |
      | sistema_2 |
      | sistema_3 |
    E filtra os sistemas listados de acordo com ambiente especificado pela RN18

  #Fluxos de exceção
  Cenário: Usuário não identificado acessa funcionalidade
    Dado que o usuário xyz está indevidamente autenticado
    Então o Portal não identifica o usuário xyz
