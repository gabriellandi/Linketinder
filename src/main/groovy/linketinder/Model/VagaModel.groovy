package linketinder.Model

import linketinder.DAO.bancos.EmpresaDAO

class VagaModel {
    String nomeVaga;
    int idEmpresa;
    String descVaga;
    String estado;
    String cidade;
    Date dataCriacao;

    VagaModel(String nomeVaga, int idEmpresa, String descVaga, String estado, String cidade, Date dataCriacao) {
        this.nomeVaga = nomeVaga
        this.idEmpresa = idEmpresa
        this.descVaga = descVaga
        this.estado = estado
        this.cidade = cidade
        this.dataCriacao = dataCriacao
    }

    VagaModel(){
        this.nomeVaga = nomeVaga
        this.idEmpresa = idEmpresa
        this.descVaga = descVaga
        this.estado = estado
        this.cidade = cidade
        this.dataCriacao = dataCriacao
    }
}