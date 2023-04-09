package linketinder.competencias

import linketinder.jdbc.bancos.candidato.CandidatoJDBC
import linketinder.jdbc.bancos.competencias.CompetenciasJDBC
import linketinder.jdbc.bancos.vagas.VagasJDBC
import linketinder.usuarios.Candidato
import linketinder.vagas.Vagas

class Competencias {
    String nome;

    Competencias(){
        this.nome = nome
    }

    static Competencias cadastrarCompetencia(Scanner leitor){
        Competencias novaCompetencia = new Competencias()

        println "Digite um nome para a competencia"

        novaCompetencia.setNome(leitor.nextLine())
        while(!validaCompetencia(novaCompetencia.getNome())){
            println "Esta competencia já existe no banco de dados"
            novaCompetencia.setNome(leitor.nextLine())
        }

        return novaCompetencia
    }

    boolean validaCompetencia(String competencia){
        CompetenciasJDBC bancoCompetencias = new CompetenciasJDBC()
        List listaCompetencias = bancoCompetencias.verificaCompetencias()
        return listaCompetencias.contains(competencia.toUpperCase())
    }

    static Map cadastraCompetenciaCandidato(Scanner leitor, CandidatoJDBC bancoCandidato){
        CompetenciasJDBC bancoCompetencias = new CompetenciasJDBC()
        Map ids = [:]

        println "Digite o numero correspondente ao ID do candidato que você deseja inserir a competencia"
        List listCandidatos = bancoCandidato.listar()
        Candidato.apresentarCandidatos(listCandidatos)
        int idCandidato = leitor.nextInt()
        leitor.nextLine()
        while(idCandidato <= 0 || idCandidato > listCandidatos.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idCandidato = leitor.nextInt()
            leitor.nextLine()
        }
        idCandidato = listCandidatos[idCandidato - 1].id

        println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
        List listCompetencias = bancoCompetencias.listar()
        apresentarCompetencias(listCompetencias)
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

        return ids
    }

    static Map cadastraCompetenciaVaga(Scanner leitor, VagasJDBC bancoVagas){
        CompetenciasJDBC bancoCompetencias = new CompetenciasJDBC()
        Map ids = [:]

        println "Digite o numero correspondente ao ID da vaga que você deseja inserir a competencia"
        List listVagas = bancoVagas.listar()
        Vagas.apresentarVagas(listVagas)
        int idVaga = leitor.nextInt()
        leitor.nextLine()
        while(idVaga <= 0 || idVaga > listVagas.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            idVaga = leitor.nextInt()
            leitor.nextLine()
        }
        idVaga = listVagas[idVaga - 1].id

        println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
        List listCompetencias = bancoCompetencias.listar()
        apresentarCompetencias(listCompetencias)
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

        return ids
    }

    static void apresentarCompetencias(List competencias){
        int count = 1
        competencias.forEach { competencia ->
            println("${count} - ${competencia.nome}")
            count++
        }
    }
}
