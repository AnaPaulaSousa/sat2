package gov.goias.sat2.view.model;

import gov.goias.conf.adapters.DateAdapter;
import gov.goias.sat2.Convertible;
import gov.goias.sat2.util.DozerUtil;
import lombok.Data;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.ParseException;
import java.util.Date;

import static org.dozer.loader.api.TypeMappingOptions.mapId;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

@Data
public class Aluno implements Convertible {
    private Long id;
    private String nome;
    private String email;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date nascimento;

    private String sexo;
    private Boolean situacao;

    public static BeanMappingBuilder getMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {

//                String dateFormat = "MMM dd yyyy HH:mm:ss 'GMT'Z (zzz)";
//ex            Thu Jun 23 2016 00:00:00 GMT-0300 (BRT)
                //"EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzz)";


                mapping(gov.goias.sat2.entities.Aluno.class, Aluno.class,
                        TypeMappingOptions.oneWay(),
                        mapId("A"),
                        mapNull(true)
//                        ,
//                        TypeMappingOptions.dateFormat(dateFormat)
                );
//                        .fields("nascimento", "nascimento");

//                mapping(Aluno.class, gov.goias.sat2.entities.Aluno.class,
//                        TypeMappingOptions.oneWay(),
//                        mapId("A"),
//                        mapNull(true),
//                        TypeMappingOptions.dateFormat(dateFormat)
//                ).fields("nascimento", "nascimento",
//                        FieldsMappingOptions.hintA(String.class),
//                        FieldsMappingOptions.hintB(Date.class)
//                );
            }
        };
    }

    public static Aluno from(final gov.goias.sat2.entities.Aluno a) {
        return DozerUtil.getMapper().map(a, Aluno.class);
    }

    public gov.goias.sat2.entities.Aluno toEntity() {
        return DozerUtil.getMapper().map(this, gov.goias.sat2.entities.Aluno.class);
    }

    public Aluno() {
    }

    public Aluno(final Long id, final String nome, final String email, final Date nascimento, final String sexo, final Boolean situacao) throws ParseException {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.situacao = situacao;
        this.nascimento = nascimento;

//        if (nascimento != null){
//            String sData = nascimento.substring(4);
//            String format = "MMM dd yyyy HH:mm:ss 'GMT'Z (zzz)";
//            DateFormat dateFormat = new SimpleDateFormat(format);
//            this.nascimento = dateFormat.parse(sData);
//        }

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

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }


}