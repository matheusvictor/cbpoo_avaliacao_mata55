package models.especialistas;

import models.Artigo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Autor extends Especialista {

    private ArrayList<Artigo> artigosSubmetidos;

    public Autor(String cpf, String nome, LocalDate dataNascimento, String titulacaoAcademica,
                 String instituicaoDeVinculo) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }

}
