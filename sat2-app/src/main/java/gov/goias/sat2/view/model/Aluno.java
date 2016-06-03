package gov.goias.sat2.view.model;

import gov.goias.sat2.Convertible;
import gov.goias.sat2.entities.Sistema;
import gov.goias.sat2.util.DozerUtil;
import lombok.Data;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import static org.dozer.loader.api.TypeMappingOptions.mapId;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

@Data
public class Aluno implements Convertible {
    private Long id;
    private String nome;
    private String email;

    public static BeanMappingBuilder getMappingBuilder(){
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(gov.goias.sat2.entities.Aluno.class, Aluno.class,
                        TypeMappingOptions.oneWay(),
                        mapId("A"),
                        mapNull(true)
                );
            }
        };
    }

    public static Aluno from(final gov.goias.sat2.entities.Aluno a){
        return DozerUtil.getMapper().map(a,Aluno.class);
    }

    public gov.goias.sat2.entities.Aluno toEntity(){
        return DozerUtil.getMapper().map(this,gov.goias.sat2.entities.Aluno.class);
    }

    public Aluno(){
    }

    public Aluno(final Long id, final String nome, final String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}