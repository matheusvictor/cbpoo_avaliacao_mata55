package services;

import exceptions.DataInvalidaException;
import exceptions.ParticipanteNaoEncontradoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class ConversorData {

    public static LocalDate converterDataParaLocalDate(String dataEmString) throws DataInvalidaException {
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataFormatada = LocalDate.parse(dataEmString, formatoData);

            return dataFormatada;

        } catch (Exception exception) {
            throw new DataInvalidaException();
        }
    }

}
