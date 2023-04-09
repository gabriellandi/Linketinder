package linketinder.jdbc

interface Connect {
    List listar()

    void deletar(Scanner leitor)

    void atualizar(Scanner leitor)
}