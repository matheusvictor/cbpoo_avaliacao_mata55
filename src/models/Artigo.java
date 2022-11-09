package models;

import models.especialistas.Autor;
import models.especialistas.Revisor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Artigo implements Comparable<Artigo> {

    private static int contador = 1;

    protected int identificador;
    protected String titulo;
    protected String resumo;
    protected ArrayList<String> avaliacoesRevisores;
    protected HashSet<Autor> autores;
    protected HashSet<Revisor> revisores;
    protected List<String> palavrasChave;
    protected int quantidadeDePaginas;
    protected LocalDate dataSubmissao;
    protected boolean aprovado;
    protected boolean aguardandoAvaliacao;

    public Artigo(String titulo, String resumo, List<String> palavrasChave,
                  int quantidadeDePaginas) {

        this.identificador = contador;

        this.titulo = titulo;
        this.resumo = resumo;
        this.autores = new HashSet<>();
        this.revisores = new HashSet<>();
        this.palavrasChave = palavrasChave;
        this.avaliacoesRevisores = new ArrayList<>();
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.aprovado = false;
        this.aguardandoAvaliacao = true;
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

    public void vincularAutor(Autor autor) {
        this.autores.add(autor);
    }

    public HashSet<Autor> getAutores() {
        return autores;
    }

    public void vincularRevisor(Revisor revisor) {
        this.revisores.add(revisor);
    }

    public HashSet<Revisor> getRevisores() {
        return revisores;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void adicionarAvaliacaoDoRevisor(String avaliacao) {
        this.avaliacoesRevisores = avaliacoesRevisores;
    }

    public ArrayList<String> getAvaliacoesRevisores() {
        return avaliacoesRevisores;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setAguardandoAvaliacao(boolean aguardandoAvaliacao) {
        this.aguardandoAvaliacao = aguardandoAvaliacao;
    }

    public boolean isAguardandoAvaliacao() {
        return aguardandoAvaliacao;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public boolean isAprovado() {
        return aprovado;
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

    @Override
    public int compareTo(Artigo o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }

}