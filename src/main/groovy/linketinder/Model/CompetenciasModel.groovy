package linketinder.Model

import linketinder.DAO.bancos.CandidatoDAO
import linketinder.DAO.bancos.CompetenciasDAO
import linketinder.DAO.bancos.VagasDAO

class CompetenciasModel {
    String nome

    CompetenciasModel(String nome){
        this.nome = nome
    }

    CompetenciasModel(){
        this.nome = nome
    }
}
