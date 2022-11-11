package models.organizadores;

import java.time.LocalDate;

public class RootAdmin extends GeneralChair {
    public RootAdmin(String cpf, String nome, String senha, LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }
}
