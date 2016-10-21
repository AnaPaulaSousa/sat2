## Template Arquitetural para desenvolvimento de aplicações Web baseado em plataforma Java.

## Principais Tecnologias
Java8, JPA, CQRS, Restful, BDD, TDD, DDD, HTML5, WebSocket, Jersey2, Servlet3, JBossEAP, Lombok, Javaslang, Bootstrap, Bower, JQuery, Angular

## Projeto Web Core
Projeto com classes Utilitárias para Aplicações Web baseadas em JAX-RS 2.0, Java 8, Servlet 3.1
http://git.intra.goias.gov.br/goias/web-core
```xml
    <dependency>
      <groupId>gov.goias</groupId>
      <artifactId>web-core</artifactId>
    </dependency>
```

## Projeto Web Template
Projeto template para recursos web e identidade visual
http://git.intra.goias.gov.br/goias/web-template.git
```json
{
  "name": "angular-seed",
  "description": "A starter project for AngularJS",
  "version": "0.0.0",
  "homepage": "https://github.com/angular/angular-seed",
  "license": "MIT",
  "private": true,
  "dependencies": {
    ...,
    "web-template": "http://git.intra.goias.gov.br/goias/web-template.git"
  }
}
```

## Programação funcional
Programação funcional é utilizado para criar uma DSL fluente para o domínio, facilitando transformações entre camadas sem utilizar estruturas de controles (if, else, for), atribuição de variávies e variáveis mutáveis.
```java
    @GET
    @Path("/{id}")
    public Aluno obter(@PathParam("id") final Long id) {
        return Aluno.from(service.obterPorId(id).orElseThrow(() -> new NaoEncontradoException()));
    }
```

### Monads
Estruturas de composição computável onde podemos agregar contexto e interpretação a objetos. 

```java
    service.obterPorId(id).orElseThrow(() -> new NaoEncontradoException())
```

#### java.util.Optional
Evitando NullPointerExceptions

```java
    service.obterPorId(id).orElseThrow(() -> new NaoEncontradoException())
    
    final String username = userExtractor.extractUser(request)
                .orElse(MsgApp.CAS_LOGIN);

```

#### javaslang.control.Try: Tratamento de exceção de forma fluente
```java

    Try.of(() -> request.getSession(false))
            .onSuccess(s -> {if(s!=null) s.invalidate(); })
            .onFailure(e -> LOGGER.debug(e)).get();

    final String nome = Try.of(() -> obterNome())
                           .onFailure(e -> "ERRO")
                           .get();

```

## Javaslang
Framework completamente integrada a Java que estende o conceito de programação funcional em Java com DSLs específicas.
Ver http://www.javaslang.io
```java
    
    return Try.of(() -> repository.save(aluno))
            .onFailure(e -> new InfraException(e))
            .get();
                
                
```

## Lombok
Framework para injeção de código repetitivo e com semântica similar entre diferentes componentes. Reduz a quantidade de codigo em classes substituindo código Java por uma série de anotações, garantindo assim a integridade com o padrão Java Bean. 
Os principais métodos substituidos são, equals, hashCode, getters e setters com uma annotation somente, a @Data. 
Ver https://projectlombok.org/

```java
@Data(staticConstructor="of")
public class Company {
    private Person founder;
    private final String name;
    private List<Person> employees;
}

final Company c = Company.of("John Doe")

```

## Controllers CQRS (Separação de Responsabilidade entre Comandos e Consultas)

Possibilita que para diferentes operações a aplicação se comporte de forma diferente, como utilizar um banco Relacional para inclusões, alterações e remoções e um repositório de dados NoSQL para consultas.
 Controladores Restful Jersey são divididos em comandos e consultas acrescentando o prefixo Cmd e Queries. Assim, para cada mapeamento com @Path e consultas só é permitida métodos HTTP GET.
 Já para cada @Path com Comandos, somente metodos POST e GET são permitidos.
 Isso acontece porque a maioria dos browsers ainda não suportam métodos HTTP PUT e DELETE, por isso usamos @POST para métodos HTTP Post e Put e @GET para consultas, listagem e remoções.

## Persistência

Para JPA foi usado Spring Data JPA para facilitar a quantidade de codigo repetitivo para consultas e comandos. Todos os métodos de CRUD e paginação são herdados da interface CrudRepository e PagingAndSortingRepository 

```java
public interface AlunoRepository extends  PagingAndSortingRepository<Aluno, Long> , QueryByExampleExecutor<Aluno> {
    Optional<Aluno> findById(Long id);
    Page<Aluno> queryFirst10ByNome(final String nome, final Pageable pageable);
}
```

## Rich model
Através de especificações podemos construir uma DSL onde partes de um mesmo domínio são consultadas por uma API fluente e reutilizável 
```java

@Entity
@Table(name = "TB_ALUNO")
@Data
public class Aluno {
    @Id
    @Column(name = "ALUN_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ALUN_NOME")
    private String nome;

    @Column(name = "ALUN_EMAIL")
    private String email;

    @Column(name = "ALUN_NASCIMENTO")
    private Date nascimento;

    public static Specification<Aluno> nomeIniciando(final String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), nome.toUpperCase()+"%");
    }

    public static Specification<Aluno> comEmail(final String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Aluno> nascidoEm(final Date nascimento) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.<Date>get("nascimento"), nascimento);
    }

}

final Specification<Aluno> spec = where(nomeIniciando("John")).and(comEmail("email@email.com"));
final List<Aluno> alunos = repository.findAll(spec);
```


## Angular

Na a interface de usuário, para um front-end mais amigável, rico, contemporizado e simplificando, foi usado o AngularJS, um framework escrito em Java Script, que estende atributos HTML através de diretivas e faz binds de dados para o HTML com expressões.

```html
<html ng-app="myApp">
<head>
    <meta charset="utf-8">
</head>
<body>
<div ng-controller="MyAppCtrl as myAppCtrl">
    <nav id="sidebar" class="sidebar nav-collapse collapse">
        <ul id='versao-menu' class='dropdown-menu account' role='menu' style="min-width: 180px;">
            <li role='presentation' class='account-picture'>
                <i class='fa fa-calendar'></i>
                <span class='conteudoDropDownInterno'>{{ toolbar.alm.buildTime | date : 'dd/MM/yyyy HH:mm:ss' }}</span>
            </li>
            <li class="dropdown">
                <a ng-href="{{toolbar.gitRepositoryState.remoteBuildUrl}}" title="build" codigo="account"
                   class="dropdown-toggle" ng-click="goToRemoteBuildUrl()"
                   data-toggle="dropdown">
                    <i class='fa fa-check-square-o'></i>
                <span class='conteudoDropDownInterno'
                      title="{{toolbar.gitRepositoryState.remoteBuildUrl}}">Commit: {{toolbar.gitRepositoryState.commitIdAbbrev}}</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<script src="../angular/angular.js"></script>
<script src="../js/app.js"></script>
</body>
</html>
```


```javascript
angular.module('myApp')
    .controller('MyAppCtrl', ['$scope', '$http', 'Notifica', 'API_BASE_URL', '$window', function ($scope, $http, Notifica, API_BASE_URL, $window) {
        $scope.goToRemoteTagUrl = function () {
            $window.open($scope.toolbar.gitRepositoryState.remoteTagUrl);
        }
        $scope.goToRemoteBuildUrl = function () {
            $window.open($scope.toolbar.gitRepositoryState.remoteBuildUrl);
        }
        $http({
            method: "GET",
            url: API_BASE_URL + '/toolbar'
        }).success(function (toolbar) {
            $scope.toolbar = toolbar;
        }).error(function (data) {
            $scope.retorno = data;
            Notifica.exibaErro(null, $scope.retorno.mensagens);
        });
    },
    ]);
```

## I18n (Internacionalização)

Com base no padrão JVM Locale e a classe UniversalMessageResolver, mensagens são agregadas em um único arquivo .properties podendo ser usadas em todos as classes


## WebSocket

A framework atmosphere foi escolhida para utilizar WebSocket uma vez que tem um bom suporte para Servlet, Jersey e outras tecnologias.

```java
@Path("/")
@AtmosphereService(
        dispatch = true,
        interceptors = {AtmosphereResourceLifecycleInterceptor.class, TrackMessageSizeInterceptor.class},
        path = "/broadcast",
        servlet = "org.glassfish.jersey.servlet.ServletContainer")
public class WebsocketController {

    /**
    * Atende a primeira requisição do cliente estabelecendo com ele um socket
    */
    @GET
    public Response configureAtmosphereResource() {
         final AtmosphereResource r = (AtmosphereResource) request.getAttribute(ApplicationConfig.ATMOSPHERE_RESOURCE);
         r.getBroadcaster().broadcast("Hello World",r);
    }

    /**
    * Recebe mensagens do cliente quando o mesmo realiza um push após estabelecer um socket
    * Javascript: 
    *     var subSocket = atmosphere.subscribe(request);
    *     subSocket.push("João");
    */
   @POST
    public void receive(String message) {
         final AtmosphereResource r = (AtmosphereResource) request.getAttribute(ApplicationConfig.ATMOSPHERE_RESOURCE);
         r.getBroadcaster().broadcast("Hello "+message,r);
    }

}
```

## Bower

Bower é o gerenciador de pacotes mais usado para tecnologias web. Ele é usado para adicionar recursos de apresentação como CSS, HTML, e JS. Os plugins usados como DateTimePicker, atmosphere.js todos tiveram resolução de conflitos de pacote feito pelo bower.

## Teste

## BDD e TDD

Usando Cubumber e JUnit, fomos capazes de definir o comportamento da aplicação e em requisitos para que depois pudessemos aplicar TDD no sentido de construir os testes para validar o comportamento da aplicação.

```
# language:pt

Funcionalidade: Manter Aluno

  Cenário: Inserir um novo Aluno
    Dado que tenho um novo aluno a ser inserido
    Quando preencho o formulário e envio ao sistema
    Então o sistema responde o status http 303
    E o cabeçalho de resposta Location com a URL válida do novo registro
```

Posteriormente com a a API do cucumber com suporte a Java 8 podemos construir os testes da seguinte maneira

```java
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "classpath:features/integration/manter_aluno.feature", //feature:x:n -> x,n = numero das linhas
        format = {"pretty",
                "json:target/target_json/cucumber.json",
                "junit:target/target_junit/cucumber.xml",
                "html:target/cucumber"})
public class ITAluno implements Pt {

    public ITAluno() {

        //Cenário: Inserir um novo Aluno
        Dado("que tenho um novo aluno a ser inserido", () -> {aluno = createAluno();});

        Quando("preencho o formulário e envio ao sistema", () -> {

            final Form form = formWithAluno(aluno);
            response = getAlunoResource().request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        });

        Entao("o sistema responde o status http 303", () -> {
            assertTrue(response.getStatus() == Response.Status.SEE_OTHER.getStatusCode());
        });
   }
}
```

## Teste durante construções com o Maven

Usamos o plugin do maven surefire para executar testes unitários, testes funcionais e de integração durante uma construção Maven.
Durante a construção os plugins iniciam o banco em memória h2, aplica DDL, iniciar o  servidor de aplicação, inicia o navegador e automaticamente testa os recursos para gerar resultados do teste.
Tudo é feito usando portas aleatórias para tornar mais fácil a execução em ambiente Jenkins / Hudson CI.
Para executar testes de integração usar comando maven:

```
   mvn verify
```

## Suporte Jersey Bean Validation Estendido

Além das validações padrão do Java Bean Validation validações adicionais podem ser usadas na requisição do cliente com uma anotação para a chave e implementação adicionado a classe Validations. No exemplo abaixo @MyValidation é uma validação criada para o projeto.
```java
@Controller
@Path("/aluno")
public class AlunoCmds {
    private static final Logger logger = Logger.getLogger(AlunoCmds.class);

    @FormValidation
    @POST
    @ErrorTemplate(name = "aluno/aluno_form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response salvar(@FormParam("id") final Integer id,
                           @MyValidation @NotNull @NotEmpty @FormParam("nome") final String nome,
                           @NotNull @Past @FormParam("nascimento") final DateParam nascimento,
                           @NotNull @FormParam("sexo") final String sexo,
                           @NotNull @FormParam("situacao") final boolean situacao,
                           @FormParam("action") final String action) throws InfraException, ValidationException, WebApplicationException {

    }

}
```

## Mapeamento de Exceções para usuário

Usando Jersey é realmente fácil mapear como as respostas as requisições devem ser criadas uma vez exceções ocorrem.
Um exemplo seria mapear um pagina especifica para quando um recurso não é encontrado

```java
@Component
@Provider
public class NaoEncontradoExceptionMapper implements ExceptionMapper<NaoEncontradoException> {
	private static final Logger log = Logger.getLogger(NaoEncontradoExceptionMapper.class);

	@Override
	public Response toResponse(final NaoEncontradoException exception) {
		log.error(exception);
		return Response.status(getErrorStatus(exception)).entity(new Viewable(WebApp.ERROR_PAGE_URI, getErrorModel(exception))).build();
	}

	protected String getErrorModel(final NaoEncontradoException exception) {
		return exception.getMessage();
	}
	protected Response.Status getErrorStatus(final NaoEncontradoException exception) {
		return Response.Status.NOT_FOUND;
	}

}
```
## Autenticação
A plataforma de autenticação utilizada para Single Sign-On é o Aepero Cas 

A documentação pode ser vista em: [Goias Cas](http://git.intra.goias.gov.br/portal/goias-cas)

## Autorização
A autorização é integrada ao Portal sendo essa possibilitada pelos componentes portal-security e cas-security. 

Documentação: [Goias Cas](http://git.intra.goias.gov.br/portal/goias-cas#autoriza%C3%A7%C3%A3o)

Documentação: [portal-security](http://git.intra.goias.gov.br/portal/portal-seguranca)

### Autorização utilizando javax.annotation.security.RolesAllowed

Documentação: [Securing JAX-RS resources with standard javax.annotation.security annotations](https://jersey.java.net/documentation/latest/security.html#d0e12386)

Portal-security [Autorizaçao de usuários: Cas](http://git.intra.goias.gov.br/portal/portal-seguranca#autoriza%C3%A7ao-de-usu%C3%A1rios-cas)

## Proximos passos

   * Exemplos de Testes unitário
   * Exemplos de Testes de integração
   * Exemplos de Testes Funcionais automatizados
   * Projeto de Api Web   
   * Cache de paginas e entidades
   * Lorem ipsum
   
   
