package models;

import models.especialista.AutorDeArtigo;
import models.especialista.RevisorDeArtigo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.HashSet;

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
                  int quantidadeDePaginas, Date dataSubmissao, HashSet<AutorDeArtigo> autores) {

        this.identificador = contador;
        this.titulo = titulo;
        this.resumo = resumo;
        this.autores = autores;
        this.palavrasChave = palavrasChave;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.dataSubmissao = LocalDate.now();

        contador++;
    }
}
