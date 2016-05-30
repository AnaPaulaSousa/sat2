# language:pt

Funcionalidade: Manter Aluno

  #FB - Fluxo básico
  Cenário: Listar alunos
    Dado que existem alunos previamente cadastrados
    Quando a lista de alunos é acessada pelo ator
    Então o sistema retorna a Lista de Alunos

  #FA01
  Cenário: Novo Aluno
    Quando o ator preenche as informações de aluno
    E o ator executa o FA03
    Então o fluxo FA01 é finalizado

  #FA03
  Cenário: Salvar Aluno
    Quando o sistema valida os campos preenchidos
    Então o sistema salva o aluno

  #EX01
  Cenário: Nome não informado
    Dado que o nome do aluno não foi informado
    Quando o sistema tenta salvar o aluno
    Então o sistema levanta uma exceção de validação


#  @drop
#  Cenário: Portal lista acessos do usuário
#    Dado que foi previamente concedido ao usuário ${portal.cas.user.test} os acessos dos sistemas
#      | sistema_1 | perfil_a |
#      | sistema_2 | perfil_b |
#      | sistema_3 | perfil_c |
#    Quando usuário ${portal.cas.user.test} acessa o Portal de forma autenticada
#    Então o Portal obtém uma lista contendo todos sistemas que o usuário ${portal.cas.user.test} possui acesso
#      | sistema_1 |
#      | sistema_2 |
#      | sistema_3 |
#    E filtra os sistemas listados de acordo com ambiente especificado pela RN18
#
#  #Fluxos de exceção
#  Cenário: Usuário não identificado acessa funcionalidade
#    Dado que o usuário xyz está indevidamente autenticado
#    Então o Portal não identifica o usuário xyz
