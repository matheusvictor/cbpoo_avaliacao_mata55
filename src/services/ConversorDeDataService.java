package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ConversorDeDataService {

    public static LocalDate converterStringDataNascimentoParaLocalDate(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataNascimento, formatter);
    }

}
