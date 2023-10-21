package br.com.serratec.ecommerce.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

// Classe utilitária para operações com propriedades de objetos
public class Utils {

    // Método para copiar propriedades não nulas de um objeto de origem para um objeto de destino
    public static void copyNonNullProperties(Object source, Object target) {
        // Utiliza o método da classe BeanUtils para copiar propriedades, passando as propriedades nulas como argumento
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Método para obter os nomes das propriedades nulas de um objeto
    public static String[] getNullPropertyNames(Object source) {
        // Cria um BeanWrapper para o objeto de origem
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Obtém todas as propriedades do objeto de origem
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Conjunto para armazenar os nomes das propriedades nulas
        Set<String> emptyNames = new HashSet<>();

        // Itera sobre as propriedades
        for (PropertyDescriptor pd : pds) {
            // Obtém o valor da propriedade
            Object srcValue = src.getPropertyValue(pd.getName());
            // Se o valor for nulo, adiciona o nome da propriedade ao conjunto de nomes nulos
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        // Converte o conjunto para um array de strings
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
