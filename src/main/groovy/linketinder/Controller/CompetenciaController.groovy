package linketinder.Controller

import linketinder.DAO.Access
import linketinder.DAO.CompetenciaAccess
import linketinder.DAO.VagasAccess
import linketinder.DAO.bancos.CompetenciasDAO
import linketinder.Model.Competencias
import linketinder.Model.VagaModel

class CompetenciaController {
    static boolean salvaCompetencia(Competencias novaCompetencia){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.inserir(novaCompetencia)
    }

    static List listaCompetencias(){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.listar()
    }

    static boolean atualizaCompetencia(Competencias competenciaAtualizada, String filtraDadoParaAtualizar){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.atualizar(competenciaAtualizada, filtraDadoParaAtualizar)
    }

    static boolean deletaCompetencia(String fitraDadoASerDeletado){
        Access bankAcess = new CompetenciaAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }

    static boolean insereCompetenciaCandidato(int idCandidato, int idCompetencia){

    }
}
