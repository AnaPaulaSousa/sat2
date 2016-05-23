# language:pt


Funcionalidade: Cadastro de Aluno
  Usuário deve ser capaz de realizar o cadastro de um aluno corretamente.

  Cenario: Cadastrar Aluno
    Dado que eu tenha permissao de acesso
    Quando informo o nome, data de nascimento, sexo, situação
    E aciono o botão para cadastrar um novo aluno
    Entao o sistema deve apresentar o novo aluno na lista de alunos

