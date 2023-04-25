package linketinder.View

import linketinder.Controller.CandidatoControler
import linketinder.Controller.CompetenciaController
import linketinder.Controller.VagaController
import linketinder.Model.CompetenciasModel
import linketinder.Model.Regex
import linketinder.Model.VagaModel

class CompetenciasView {
    static CompetenciasModel criaCompetencia(Scanner leitor){
        CompetenciasModel novaCompetencia = new CompetenciasModel()

        println "Digite um nome para a competencia"

        novaCompetencia.setNome(leitor.nextLine())
        while(!Regex.validaNome(novaCompetencia.getNome())){
            println "Digite uma competencia com pelo menos 4 caracteres"
            novaCompetencia.setNome(leitor.nextLine())
        }

        return novaCompetencia
    }

    static void cadastraCompetencia(Scanner leitor){
        CompetenciasModel novaCompetencia = criaCompetencia(leitor)

        if(CompetenciaController.salvaCompetencia(novaCompetencia)){
            println "Competencia salva com sucesso"
        }else{
            println "A competencia nao foi salva, contate nosso suporte"
        }
    }

    static void atualizaCompetencia(Scanner leitor){
        println "Digite o nome da competencia a ser atualizada"

        String nome = leitor.nextLine()
        while(!Regex.validaNome(nome)){
            println "Digite uma competencia com pelo menos 4 caracteres"
            nome = leitor.nextLine()
        }

        CompetenciasModel competenciaAtualizada = criaCompetencia(leitor)

        if(CompetenciaController.atualizaCompetencia(competenciaAtualizada, nome.toUpperCase())){
            println "Competencia atualizada com sucesso"
        }else{
            println "A competencia nao foi atualizada, reveja o nome digitado"
        }
    }

    static void deletaCompetencia(Scanner leitor){
        println "Digite o id da competencia a ser deletada"
        String id = leitor.nextLine()

        if(CompetenciaController.deletaCompetencia(id)){
            println "Competencia deletada com sucesso"
        }else{
            println "A competencia nao foi deletada, reveja o id digitado"
        }
    }

    static void cadastraCompetenciaCandidato(Scanner leitor){
        Map ids = [:]

        println "Digite o numero correspondente ao ID do candidato que você deseja inserir a competencia"
        List listCandidatos = CandidatoControler.listaUsuarios()
        CandidatoView.apresentarCandidatos()
        int idCandidato = leitor.nextInt()
        leitor.nextLine()
        while(idCandidato <= 0 || idCandidato > listCandidatos.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idCandidato = leitor.nextInt()
            leitor.nextLine()
        }
        idCandidato = listCandidatos[idCandidato - 1].id

        println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
        List listCompetencias = CompetenciaController.listaCompetencias()
        apresentarCompetencias()
        int idCompetencia = leitor.nextInt()
        leitor.nextLine()
        while(idCompetencia <= 0 || idCompetencia > listCompetencias.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idCompetencia = leitor.nextInt()
            leitor.nextLine()
        }
        idCompetencia = listCompetencias[idCompetencia - 1].id

        ids.idCandidato = idCandidato
        ids.idCompetencia = idCompetencia


    }

    static void cadastraCompetenciaVaga(Scanner leitor){
        Map ids = [:]

        println "Digite o numero correspondente ao ID da vaga que você deseja inserir a competencia"
        List listVagas = VagaController.listaVagas()
        VagaModel.apresentarVagas(listVagas)
        int idVaga = leitor.nextInt()
        leitor.nextLine()
        while(idVaga <= 0 || idVaga > listVagas.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idVaga = leitor.nextInt()
            leitor.nextLine()
        }
        idVaga = listVagas[idVaga - 1].id

        println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
        List listCompetencias = CompetenciaController.listaCompetencias()
        apresentarCompetencias()
        int idCompetencia = leitor.nextInt()
        leitor.nextLine()
        while(idCompetencia <= 0 || idCompetencia > listCompetencias.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idCompetencia = leitor.nextInt()
            leitor.nextLine()
        }
        idCompetencia = listCompetencias[idCompetencia - 1].id

        ids.idVaga = idVaga
        ids.idCompetencia = idCompetencia


    }

    static void apresentarCompetencias(){
        List competencias = CompetenciaController.listaCompetencias()
        int count = 1
        competencias.forEach { competencia ->
            println("${count} - ${competencia.nome}")
            count++
        }
    }
}
