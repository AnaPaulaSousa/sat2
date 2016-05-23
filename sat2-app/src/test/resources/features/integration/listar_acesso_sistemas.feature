# language:pt

Funcionalidade: Listar Sistemas

  #Fluxo padrão
  Cenário: Usuario acessa lista de sistemas que possui acesso
    Dado que o usuário ${portal.cas.user.test} está autenticado
    E que foi previamente concedido ao usuário ${portal.cas.user.test} acesso aos sistemas
      | id | sigla | descricao   | conexao                           | mensagem   | nomeAplicacao |
      | 1  |  ABC  | Sistema ABC | http://localhost:8080             | Bem vindo  | SABC          |
      | 2  |  DEF  | Sistema DEF | http://def.intra.goias.gov.br     | Bem vindos | SDEF          |
      | 3  |  GHI  | Sistema GHI | http://ghi.intra.goias.gov.br/ghi | Welcome    | SGHI          |
    Quando a apresentação solicita a lista de sistemas para o usuário ${portal.cas.user.test}
    Então o Portal responde para apresentação a representação App contendo os sistemas
      | ABC |
      | DEF |
      | GHI |

  #Fluxo Alternativo
  Cenário: Usuario não possui acesso a sistemas
    Dado que o usuário ${portal.cas.user.test} foi previamente autenticado
    Quando a lista de sistemas para o usuário ${portal.cas.user.test} é solicitada
    Então o Portal retorna uma lista vazia e status HTTP 200

  #Fluxo de exceção
  Cenário: Usuario sem autenticação acessa lista de sistemas
    Dado que um usuário não autenticado acessa a funcionalidade
    Quando a apresentação solicita a lista de sistemas do usuário não autenticado
    Então o Portal responde o status HTTP 403 negando o acesso do usuário
