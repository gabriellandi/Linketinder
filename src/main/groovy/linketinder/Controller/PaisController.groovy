package linketinder.Controller

import linketinder.DAO.Access
import linketinder.DAO.PaisAccess
import linketinder.Model.PaisModel
import linketinder.Model.Regex

class PaisController {
    static boolean salvarPais(String nome){
        Access bankAccess = new PaisAccess()
        PaisModel novoPais = new PaisModel(nome)
        return bankAccess.inserir(novoPais)
    }

    static List listaPaises(){
        Access bankAccess = new PaisAccess()
        return bankAccess.listar()
    }

    static boolean atualizaPaises(PaisModel pais, String filtraDadoParaAtualizar){
        Access bankAccess = new PaisAccess()
        return bankAccess.atualizar(pais, filtraDadoParaAtualizar)
    }

    static boolean deletaPais(String fitraDadoParaDeletar){
        Access bankAccess = new PaisAccess()
        return bankAccess.deletar(fitraDadoParaDeletar)
    }
}
