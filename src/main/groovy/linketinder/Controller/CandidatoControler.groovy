package linketinder.Controller

import linketinder.DAO.Access
import linketinder.DAO.CandidatoAccess
import linketinder.Model.CandidatoModel

class CandidatoControler {
    static boolean salvaUsuario(CandidatoModel candidato){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.inserir(candidato)
    }

    static List listaUsuarios(){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.listar()
    }

    static boolean atualizaCandidato(CandidatoModel newCandidade, String filtraDadoParaAtualizar){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.atualizar(newCandidade, filtraDadoParaAtualizar)
    }

    static boolean deletaCandidato(String fitraDadoASerDeletado){
        Access bankAcess = new CandidatoAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }

}
