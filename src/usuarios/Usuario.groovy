package usuarios
@groovy.transform.ToString
class Usuario {
    String nome, email, estado, descricao, cep
    def registroCurtida = int[]
    def competencias = []
}
