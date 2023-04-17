package linketinder.DAO.bancos

interface IConnect {
    boolean inserir(Object objeto)

    List listar()

    boolean deletar(String fitraDadoParaDeletar)

    boolean atualizar(Object objeto, String filtraDadoParaAtualizar)
}