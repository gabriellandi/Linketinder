package linketinder.Controller

import linketinder.DAO.Access
import linketinder.DAO.EmpresaAccess
import linketinder.Model.EmpresaModel

class EmpresaController {
    static boolean salvarEmpresa(EmpresaModel empresa){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.inserir(empresa)
    }

    static List listaEmpresas(){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.listar()
    }

    static boolean atualizaEmpresa(EmpresaModel newEmpresa, String filtraDadoParaAtualizar){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.atualizar(newEmpresa, filtraDadoParaAtualizar)
    }

    static boolean deletaEmpresa(String fitraDadoASerDeletado){
        Access bankAcess = new EmpresaAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }
}
