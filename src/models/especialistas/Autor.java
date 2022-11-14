package models.especialistas;

import models.Artigo;
import models.Participante;

import java.time.LocalDate;
import java.util.ArrayList;

public class Autor extends Especialista {

    private ArrayList<Artigo> artigosSubmetidos = new ArrayList<>();

    public Autor(String cpf, String nome, LocalDate dataNascimento,
                 String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }

    public static Autor converterParticipante(Participante participante) {
        Autor autor = new Autor(
                participante.getCpf(),
                participante.getNome(),
                participante.getDataNascimento(),
                participante.getTitulacaoAcademica(),
                participante.getInstituicaoDeVinculo()
        );

        autor.setSenha(participante.getSenha());
        autor.setCertificado(participante.isCertificado());
        autor.setInscricaoValida(participante.isInscricaoValida());
        autor.setValidacaoPendente(participante.isValidacaoPendente());

        return autor;
    }

    public boolean addArtigoSubmetido(Artigo artigo) {
        return this.artigosSubmetidos.add(artigo);
    }

}
