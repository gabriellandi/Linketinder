package classes.vagas

import java.time.LocalDate

class Vagas {
    String nomeVaga;
    int idEmpresa;
    String descVaga;
    String estado;
    String cidade;
    Date dataCriacao;

    Vagas(String nomeVaga, int idEmpresa, String descVaga, String estado, String cidade, Date dataCriacao) {
        this.nomeVaga = nomeVaga
        this.idEmpresa = idEmpresa
        this.descVaga = descVaga
        this.estado = estado
        this.cidade = cidade
        this.dataCriacao = dataCriacao
    }
}
