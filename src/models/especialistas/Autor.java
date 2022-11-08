package models.especialistas;

import models.Artigo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor extends Especialista {

    private ArrayList<Artigo> artigosPublicados;

    public Autor(String cpf, String nome, LocalDate dataNascimento,
                 String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo, especialidade);
        this.artigosPublicados = new ArrayList<>();
    }

    public Artigo submeterArtigo(String titulo, String resumo, List<String> palavrasChave, int quantidadeDePaginas) {
        Artigo artigo = new Artigo(titulo, resumo, palavrasChave, quantidadeDePaginas);
        this.artigosPublicados.add(artigo);
        return artigo;
    }

    public void vincularCoAutoresAoArtigo(Artigo artigo, String cpf, String nome, LocalDate dataNascimento,
                                          String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        Autor coAutor = new Autor(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo, especialidade);
        if (artigo.getAutores().size() < 5)
            artigo.vincularAutor(coAutor);
    }

}
