Arquitetura para aplicações Web baseada na plataforma Java. Utiliza padrão MVC com controladores Restful, domínio com JPA e Spring Transaction e view baseada em Bootstrap/HTML5 com gerenciamento de pacotes para apresentação com Bower.

## Principais Tecnologias

Java8, JPA, CQRS, Restful, BDD, TDD, DDD, HTML5, WebSocket, CSRF, Selenium, Jersey2, Servlet3, JBossEAP, Bootstrap, Bower, JQuery, I18n, SB de Admin 2

## Controllers CQRS (Separação de Responsabilidade entre Comandos e Consultas)

Possibilita que para diferentes operações a aplicação se comporte de forma diferente, como utilizar um banco Relacional para inclusões, alterações e remoções e um repositório de dados NoSQL para consultas.
 Controladores Restful Jersey são divididos em comandos e consultas acrescentando o prefixo Cmd e Queries. Assim, para cada mapeamento com @Path e consultas só é permitida métodos HTTP GET.
 Já para cada @Path com Comandos, somente metodos POST e GET são permitidos.
 Isso acontece porque a maioria dos browsers ainda não suportam métodos HTTP PUT e DELETE, por isso usamos @POST para métodos HTTP Post e Put e @GET para consultas, listagem e remoções.

## Persistência

Baseado em goias-dominio utilizando JPA e Spring Transaction

## Thymeleaf

Thymeleaf foi a framework de escolha para apresentação uma vez que é bem documentada, usa marcação HTML em vez de scriptlets e tem uma grande integração com Spring.
Durante a construção da arquitetura as frameworks Jersey2 e Thymeleaf não possuíam suporte por padrão logo alguma integração foi feita utilizando a classe ThymeleafViewProcessor

## SB Admin 2

[SB Admin 2] (http://startbootstrap.com/template-overviews/sb-admin-2/): Um template HTML de administração baseado em Bootstrap com plugins jQuery.

## I18n (Internacionalização)

Com base no padrão JVM Locale e a classe UniversalMessageResolver, mensagens são agregadas em um único arquivo .properties podendo ser usada em todos os templates thymeleaf.

## Security: CSRF

Em vez de usar Spring Security que é uma suíte completa para segurança, decidimos adotar algo mais leve como OWASP Security Suite. A "Open Web Application Security Project" é uma comunidade online dedicada a promover segurança para aplicações web. Da OWASP somente a proteção a CSRF foi adicionado, porém futuras versões deste template irá implementar outras proteções de segurança.
Entretanto, a maioria da OWASP Top 10 falhas críticas de segurança de aplicações web são cobertos usando Spring, Jersey, OWASP CSRFGuard 3 e Java EE.

## Integração do Thymeleaf com OWASP CSRFGuard 3

Exemplo:
```html
    <a href = "#" csrf: token_for = "href-dados" th: attr = "href-data = $ {it.id + '/ delete'}"-th-texto de dados = "# {delete.button.label } "class =" btn btn-esboço "-toggle dados =" btn-modal perigo "-alvo de dados =" # confirm-delete "> excluir </a>
```

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

##+ BDD e TDD

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

##+ Teste durante construções com o Maven

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

## O que não está no template?

   * Autenticação e Autorização
   * Exemplos de Testes unitário
   * Cache de paginas e entidades&lt;/verbatim&gt;
