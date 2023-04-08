package linketinder.usuarios


import groovy.transform.ToString

@ToString
class Usuario {
    String nome
    String email
    String estado
    String descricao
    String senha
    int cep
    int pais

    static void apresentarPaises(List list){
        int count = 1;
        list.forEach { pais ->
            println("${count} - ${pais.nome}")
            count++
        }
    }
}
