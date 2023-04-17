package linketinder.DAO

import linketinder.DAO.bancos.IConnect

abstract class Access {
    IConnect bank = createAccess();

    boolean inserir(Object objeto){
        bank.inserir(objeto);
    }

    List listar(){
        bank.listar()
    }

    boolean deletar(String dadoASerDeletado){
        bank.deletar(dadoASerDeletado)
    }

    boolean atualizar(Object objeto, String filtraDadoParaAtualizar){
        bank.atualizar(objeto, filtraDadoParaAtualizar)
    }

    protected abstract IConnect createAccess();
}
