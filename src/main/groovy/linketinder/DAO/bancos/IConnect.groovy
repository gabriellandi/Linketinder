package linketinder.DAO.bancos

interface IConnect {
    void inserir(Object objeto)

    List listar()

    void deletar(Scanner leitor)

    void atualizar(Scanner leitor)
}