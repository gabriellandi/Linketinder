package linketinder.View

import linketinder.Controller.PaisController
import linketinder.Model.PaisModel
import linketinder.Model.Regex

class PaisView {
    static void cadastrarPais(Scanner leitor){
        println "Digite um nome para o pais"
        String nome
        nome = leitor.nextLine()
        while(!Regex.validaNome(nome)){
            println "Digite um nome com pelo menos 4 caracteres"
            nome = leitor.nextLine()
        }

        boolean verificaPersistencia = PaisController.salvarPais(nome)

        if(verificaPersistencia){
            println "Pais salvo com sucesso"
        } else {
            println "O pais não foi salvo, contate nosso suporte"
        }
    }

    static void apresentarPaises(){
        List listaPaises = PaisController.listaPaises()
        int count = 1
        listaPaises.forEach { pais ->
            println("${count} - ${pais.nome}")
            count++
        }
    }

    static void editarPaises(Scanner leitor){
        println "Informe o nome do pais que deseja atualizar: "
        String nome = leitor.nextLine()

        PaisModel pais = new PaisModel()
        println "Digite o novo nome do pais"
        pais.setNome(leitor.nextLine())
        while(!Regex.validaNome(pais.getNome())){
            println "Digite um nome com pelo menos 4 caracteres"
            pais.setNome(leitor.nextLine())
        }

        if(PaisController.atualizaPaises(pais, nome)){
            println "Pais atualizado com sucesso"
        } else {
            println "O dado nao foi atualizado, verifique o nome do pais a ser atualizado"
        }
    }

    static void deletaPais(Scanner leitor){
        println "Digite o novo nome para o pais"
        String nome
        nome = leitor.nextLine()
        while(!Regex.validaNome(nome)){
            println "Digite um nome com pelo menos 4 caracteres"
            nome = leitor.nextLine()
        }

        if(PaisController.deletaPais(nome)){
            println "Pais deletado com sucesso"
        }else{
            println "Verifique se o pais que deseja excluir existe, pois ele nao foi localizado no banco"
        }
    }
}
