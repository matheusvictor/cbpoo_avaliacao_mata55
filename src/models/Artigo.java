package models;

import exceptions.NumeroMaximoAutoresException;
import exceptions.NumeroMaximoPalavrasChaveException;
import models.especialistas.Autor;
import models.especialistas.Revisor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Artigo implements Comparable<Artigo> {

    private static int contador = 1;
    public final static int MAX_AUTORES = 5;
    public final static int QTD_PALAVRAS_CHAVE = 3;

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

    public Artigo() {
        this.identificador = contador;

        this.autores = new HashSet<>();
        this.revisores = new HashSet<>();
        this.palavrasChave = new ArrayList<>();
        this.avaliacoesRevisores = new ArrayList<>();
        this.aprovado = false;
        this.aguardandoAvaliacao = true;
        this.dataSubmissao = LocalDate.now();
    }

    public Artigo(String titulo, String resumo, int quantidadeDePaginas) {

        this();
        this.identificador = contador;

        this.titulo = titulo;
        this.resumo = resumo;
        this.quantidadeDePaginas = quantidadeDePaginas;
        contador++;
    }

    public boolean addAutor(Autor autor) throws NumeroMaximoAutoresException {
        if (this.autores.size() >= MAX_AUTORES) {
            throw new NumeroMaximoAutoresException();
        }
        return this.autores.add(autor);
    }

    public boolean addPalavraChave(String palavraChave) throws NumeroMaximoPalavrasChaveException {
        if (this.autores.size() >= QTD_PALAVRAS_CHAVE) {
            throw new NumeroMaximoPalavrasChaveException();
        }
        return this.palavrasChave.add(palavraChave);
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

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void setAutores(HashSet<Autor> autores) {
        this.autores = autores;
    }

    public void setQuantidadeDePaginas(int quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
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

    public String obterDetalhes() {
        return "ID: " + identificador + "\n" +
                "Título: " + titulo + "\n" +
                "Resumo: " + resumo + "\n" +
                "Qtd. de páginas: " + quantidadeDePaginas + "\n" +
                "Palavras-chave: " + palavrasChave +
                "Autores: " + autores +
                "Revisores: " + revisores +
                "Data de submissão: " + dataSubmissao;
    }

    @Override
    public String toString() {
        return "[" + identificador + "]" + " - " + titulo + " - " + dataSubmissao;
    }

    @Override
    public int compareTo(Artigo o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }

}