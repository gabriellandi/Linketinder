package linketinder.Controller

import linketinder.DAO.Access
import linketinder.DAO.VagasAccess
import linketinder.Model.VagaModel

class VagaController {
    static boolean salvaVaga(VagaModel novaVaga){
        Access bankAccess = new VagasAccess()
        return bankAccess.inserir(novaVaga)
    }

    static List listaVagas(){
        Access bankAccess = new VagasAccess()
        return bankAccess.listar()
    }

    static boolean atualizaVaga(VagaModel vagaAtualizada, String filtraDadoParaAtualizar){
        Access bankAccess = new VagasAccess()
        return bankAccess.atualizar(vagaAtualizada, filtraDadoParaAtualizar)
    }

    static boolean deletaVaga(String fitraDadoASerDeletado){
        Access bankAcess = new VagasAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }
}
