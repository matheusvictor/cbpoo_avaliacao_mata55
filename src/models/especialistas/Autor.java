package models.especialistas;

import models.Artigo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor extends Especialista {

    private ArrayList<Artigo> artigosSubmetidos;

    public Autor(String cpf, String nome, String senha, LocalDate dataNascimento, String titulacaoAcademica,
                 String instituicaoDeVinculo, String especialidade, ArrayList<Artigo> artigosSubmetidos) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo, especialidade);
        this.artigosSubmetidos = artigosSubmetidos;
    }

    public Artigo submeterArtigo(String titulo, String resumo, List<String> palavrasChave, int quantidadeDePaginas) {
        Artigo artigo = new Artigo(titulo, resumo, palavrasChave, quantidadeDePaginas);
        this.artigosSubmetidos.add(artigo);
        return artigo;
    }

}
