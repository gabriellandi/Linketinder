package linketinder.location

import linketinder.jdbc.bancos.competencias.CompetenciasJDBC
import linketinder.regex.Regex

class Pais {
    String nome;

    static Pais criarPais(Scanner leitor){
        Pais novoPais = new Pais()

        println "Digite um nome para o pais"

        novoPais.setNome(leitor.nextLine())
        while(!Regex.validaNome(novoPais.getNome())){
            println "Esta competencia já existe no banco de dados"
            novoPais.setNome(leitor.nextLine())
        }

        return novoPais
    }
}
