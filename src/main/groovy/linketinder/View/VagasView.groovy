package linketinder.View

import linketinder.Controller.EmpresaController
import linketinder.Controller.VagaController
import linketinder.Model.Regex
import linketinder.Model.VagaModel

class VagasView {
    static VagaModel criarVaga(Scanner leitor){
        VagaModel novaVaga = new VagaModel()

        println "Digite um nome para vaga"
        novaVaga.setNomeVaga(leitor.nextLine())
        while(!Regex.validaNome(novaVaga.getNomeVaga())){
            println "Digite um nome com pelo menos 4 caracteres"
            novaVaga.setNomeVaga(leitor.nextLine())
        }

        println "Digite o numero correspondente ao ID da empresa que está criando a vaga"
        List listaEmpresas = EmpresaController.listaEmpresas()
        EmpresaView.apresentarEmpresa()
        int num = leitor.nextInt()
        leitor.nextLine()
        while(num <= 0 || num > listaEmpresas.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            num = leitor.nextInt()
            leitor.nextLine()
        }
        novaVaga.setIdEmpresa(listaEmpresas[num - 1].id)

        println "Digite uma descrição da vaga"
        novaVaga.setDescVaga(leitor.nextLine())
        while(!Regex.validaDescricao(novaVaga.getDescVaga())){
            println "Digite uma descrição usando apenas letras"
            novaVaga.setDescVaga(leitor.nextLine())
        }

        println "Digite o estado onde ela fica localizada"
        novaVaga.setEstado(leitor.nextLine())
        while(!Regex.validaEstado(novaVaga.getEstado())){
            println "Digite uma sigla com 2 caracteres"
            novaVaga.setEstado(leitor.nextLine())
        }

        println "Digite a cidade onde ela fica localizada"
        novaVaga.setCidade(leitor.nextLine())
        while(!Regex.validaNome(novaVaga.getCidade())){
            println "Digite um nome com pelo menos 4 caracteres"
            novaVaga.setCidade(leitor.nextLine())
        }

        return novaVaga
    }

    static void cadastraVaga(Scanner leitor){
        VagaModel novaVaga = criarVaga(leitor)
        if(VagaController.salvaVaga(novaVaga)){
            println "Vaga salva com sucesso"
        }else{
            println "A vaga nao foi salva contate nosso suporte"
        }
    }

    static void apresentarVagas(){
        List vagas = VagaController.listaVagas()
        int count = 1
        vagas.forEach {vaga->
            println("${count} - ${vaga.nome}")
            println("Descricao: ${vaga.descricao}")
            println("Estado: ${vaga.estado}")
            println("Cidade: ${vaga.cidade}")
            count++
        }
    }

    static void deletaVaga(Scanner leitor){
        println "Informe o id da vaga que deseja deletar: "
        int id = Integer.parseInt(leitor.nextLine())

        if(VagaController.deletaVaga(id.toString())){
            println "Vaga deletada com sucesso"
        }else{
            println "Verifique se o id digitado existe"
        }
    }

    static void editaVaga(Scanner leitor){
        println "Informe o id da vaga, que deseja atualizar: "
        int id = Integer.parseInt(leitor.nextLine())

        VagaModel vagaAtualizada = criarVaga(leitor)

        if(VagaController.atualizaVaga(vagaAtualizada, id.toString())){
            println "Vaga atualizada com sucesso"
        }else{
            println "Verifique o id da vaga digitada"
        }
    }
}
