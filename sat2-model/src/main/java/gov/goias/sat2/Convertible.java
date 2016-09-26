package gov.goias.sat2;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

/**
 * <b>Título:</b> Convertible
 * <br><b>Descrição:</b> Define API para conversões de objetos
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
public interface Convertible {

    final Logger LOGGER = Logger.getLogger(Convertible.class);

    /**
     * Constroi uma lista de valores das propriedades de um Pojo
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Lista de valores
     */
    default List<String> asListofValues(final String... fields) {
        return asListofValues(null, 0, null, fields);
    }

    /**
     * Constroi uma lista de valores das propriedades de um Pojo com um valor adicional
     * @param f função para geração do valor adicional
     * @param additionalValOrder ordem do valor adicional na lista
     * @param fieldForAdditionalVal atributo que terá seu valor usado como parâmetro da função de construção do valor adicional
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Lista de valores
     */
    default List<String> asListofValues(final Function<Object, String> f, final Integer additionalValOrder, final String fieldForAdditionalVal, final String... fields) {
        final List<String> values = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                if(additionalValOrder == i && f != null){
                    values.add(asString(f.apply(getMethodValue(fieldForAdditionalVal))));
                }
                values.add(asString(getMethodValue(fields[i])));
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
            } catch (InvocationTargetException e) {
                LOGGER.error(e);
            }
        }
        return values;
    }

    /**
     * Constroi um mapa com nome de atributos e seus respectivos valores
     * @param fields Atributos do pojo a que serão usados para construir um mapa lista de valores
     * @return Map com somente lista de campos
     */
    default Map<String, String> asMapofValues(final String... fields) {
        return asMapofValues(null, null, null, fields);
    }

    /**
     * Constroi um mapa com nome de atributos e seus respectivos valores com um valor adicional
     * @param f função para geração do valor adicional
     * @param additionalField nome do campo adicional
     * @param fieldForAdditionalVal atributo que terá seu valor usado como parâmetro da função de construção do valor adicional
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Map montado pelo processamento de função, campos adicionais e valor do campo adicional
     */
    default Map<String,String> asMapofValues(final Function<Object, String> f, final String additionalField, final String fieldForAdditionalVal, final String... fields) {
        final Map<String,String> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                if(f != null && !map.containsKey(additionalField)) {
                    map.put(additionalField,asString(f.apply(getMethodValue(fieldForAdditionalVal))));
                }
                map.put(fields[i], asString(getMethodValue(fields[i])));
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
            } catch (InvocationTargetException e) {
                LOGGER.error(e);
            }
        }
        return map;
    }

    /**
     * Obtem o valor Literal do campo
     * @param fieldValue campo a ser convertido a String
     * @return representação em String do Objeto
     */
    default String asString(final Object fieldValue) {
        if (Date.class.isInstance(fieldValue)) {
            return ((Date) fieldValue).toInstant().atZone( ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return fieldValue == null ? "" : String.valueOf(fieldValue);
        }
    }

    /**
     * Obtem o valor do método pelo nome
     * @param field campo que método get sera executado
     * @return valor do campo field
     * @throws InvocationTargetException para erros na execução do método
     * @throws IllegalAccessException caso não tenha acesso ao método (privado)
     */
    default Object getMethodValue(final String field) throws InvocationTargetException, IllegalAccessException {
        final String format = "get".concat(String.valueOf(field.charAt(0)).toUpperCase()).concat(field.substring(1));
        final Method method = Arrays.asList(this.getClass().getDeclaredMethods()).stream().filter(m -> m.getName().equals(format)).findFirst().orElse(null);
        return method.invoke(this,null);
    }

}