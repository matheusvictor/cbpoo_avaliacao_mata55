package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class ConversorData {

    public static LocalDate converterDataParaLocalDate(String dataEmString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataEmString, formatter);
    }

}
