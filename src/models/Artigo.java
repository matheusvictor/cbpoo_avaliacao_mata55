package models;

import models.participante.especialista.Autor;
import models.participante.especialista.Revisor;

import java.util.List;
import java.util.HashSet;
import java.time.LocalDate;

public class Artigo {

    private static int contador = 1;

    protected int identificador;
    protected String titulo;
    protected String resumo;
    protected HashSet<Autor> autores;
    protected HashSet<Revisor> revisores;
    protected List<String> palavrasChave;
    protected int quantidadeDePaginas;
    protected LocalDate dataSubmissao;


    public Artigo(String titulo, String resumo, List<String> palavrasChave,
                  int quantidadeDePaginas, HashSet<Autor> autores) {

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

    public HashSet<Autor> getAutores() {
        return autores;
    }

    public HashSet<Revisor> getRevisores() {
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
