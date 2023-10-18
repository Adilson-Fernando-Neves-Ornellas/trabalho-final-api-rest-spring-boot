package br.com.serratec.ecommerce.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    public static String converterDataParaDataHora(Date data) {
        return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(data);
    }
}
