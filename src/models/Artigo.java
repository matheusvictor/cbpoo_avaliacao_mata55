package models;

import models.especialista.AutorDeArtigo;
import models.especialista.RevisorDeArtigo;

import java.util.List;
import java.util.HashSet;
import java.time.LocalDate;

public class Artigo {

    private static int contador = 1;

    protected int identificador;
    protected String titulo;
    protected String resumo;
    protected HashSet<AutorDeArtigo> autores;
    protected HashSet<RevisorDeArtigo> revisores;
    protected List<String> palavrasChave;
    protected int quantidadeDePaginas;
    protected LocalDate dataSubmissao;


    public Artigo(String titulo, String resumo, List<String> palavrasChave,
                  int quantidadeDePaginas, HashSet<AutorDeArtigo> autores) {

        this.identificador = contador;
        this.titulo = titulo;
        this.resumo = resumo;
        this.autores = autores;
        this.revisores = new HashSet<>();
        this.palavrasChave = palavrasChave;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.dataSubmissao = LocalDate.now();

        contador++;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public HashSet<AutorDeArtigo> getAutores() {
        return autores;
    }

    public HashSet<RevisorDeArtigo> getRevisores() {
        return revisores;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public LocalDate getDataSubmissao() {
        return dataSubmissao;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "identificador=" + identificador +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", autores=" + autores +
                ", revisores=" + revisores +
                ", palavrasChave=" + palavrasChave +
                ", quantidadeDePaginas=" + quantidadeDePaginas +
                ", dataSubmissao=" + dataSubmissao +
                '}';
    }

}
