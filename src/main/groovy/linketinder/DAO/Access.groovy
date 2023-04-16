package linketinder.DAO

import linketinder.DAO.bancos.IConnect

abstract class Access {
    IConnect bank = createAccess();

    void inserir(Scanner leitor){
        bank.insert(leitor);
    }

    List listar(){
        bank.listar()
    }

    void deletar(Scanner leitor){
        bank.deletar(leitor)
    }

    void atualizar(Scanner leitor){
        bank.atualizar(leitor)
    }

    protected abstract IConnect createAccess();
}
