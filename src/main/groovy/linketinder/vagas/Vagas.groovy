package linketinder.vagas

import linketinder.jdbc.bancos.empresa.EmpresaJDBC
import linketinder.regex.Regex
import linketinder.usuarios.Empresa

class Vagas {
    String nomeVaga;
    int idEmpresa;
    String descVaga;
    String estado;
    String cidade;
    Date dataCriacao;

    Vagas(String nomeVaga, int idEmpresa, String descVaga, String estado, String cidade, Date dataCriacao) {
        this.nomeVaga = nomeVaga
        this.idEmpresa = idEmpresa
        this.descVaga = descVaga
        this.estado = estado
        this.cidade = cidade
        this.dataCriacao = dataCriacao
    }

    Vagas(){
        this.nomeVaga = nomeVaga
        this.idEmpresa = idEmpresa
        this.descVaga = descVaga
        this.estado = estado
        this.cidade = cidade
        this.dataCriacao = dataCriacao
    }

    static Vagas inserirVaga(Scanner leitor){
        Vagas novaVaga = new Vagas()
        EmpresaJDBC bancoEmpresa = new EmpresaJDBC()

        println "Digite um nome para vaga"
        novaVaga.setNomeVaga(leitor.nextLine())
        while(!Regex.validaNome(novaVaga.getNomeVaga())){
            println "Digite um nome com pelo menos 4 caracteres"
            novaVaga.setNomeVaga(leitor.nextLine())
        }

        println "Digite o numero correspondente ao ID da empresa que está criando a vaga"
        List listaEmpresas = bancoEmpresa.listar()
        Empresa.apresentarEmpresa(listaEmpresas)
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

        novaVaga.setDataCriacao(new Date())

        return novaVaga
    }

    static void apresentarVagas(List vagas){
        int count = 1
        vagas.forEach {vaga->
            println("${count} - ${vaga.nome}")
            println("Descricao: ${vaga.descricao}")
            println("Estado: ${vaga.estado}")
            println("Cidade: ${vaga.cidade}")
            count++
        }
    }
}