package services;

import models.participante.Participante;

import java.util.HashSet;
import java.time.LocalDate;

public class GerarListaParticipantesService {

    private HashSet<Participante> participantes;
    private static GerarListaParticipantesService gerador;

    public static GerarListaParticipantesService getInstance() {
        if (gerador == null) {
            gerador = new GerarListaParticipantesService();
        }
        return gerador;
    }

    private GerarListaParticipantesService() {
        gerarParticipantes();
    }

    private void gerarParticipantes() {

        this.participantes = new HashSet<>();

        Participante p1 = new Participante("123.456.789-00", "Mario Sergio Cortella", "1234", LocalDate.of(1954, 3, 5), "Filósofo", "Pontifícia Universidade Católica de São Paulo");
        Participante p2 = new Participante("123.456.789-00", "Leandro Karnal", "1234", LocalDate.of(1963, 2, 1), "Filósofo", "Universidade de São Paulo");

        this.participantes.add(p1);
        this.participantes.add(p2);
    }

}
