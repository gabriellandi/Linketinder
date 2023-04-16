package linketinder.usuarios

import groovy.transform.ToString
import linketinder.DAO.bancos.pais.PaisDAO
import linketinder.regex.Regex

import java.text.SimpleDateFormat

@ToString
class Candidato extends Usuario{
    Long cpf
    String sobrenome
    Date dtNascimento
    String formacao

    Candidato(){
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.dtNascimento = dtNascimento
        this.pais = pais
        this.formacao = formacao
        this.senha = senha
    }

    Candidato(String sobrenome, Long cpf, String nome, String email, String descricao, int cep, Date dtNascimento, int pais, String formacao, String senha) {
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.dtNascimento = dtNascimento
        this.pais = pais
        this.formacao = formacao
        this.senha = senha
    }

    static Candidato criarCandidato(Scanner leitor, PaisDAO bancoPaises){
        Candidato newCandidate = new Candidato()

        println "Digite o seu nome"
        newCandidate.setNome(leitor.nextLine())
        while(!Regex.validaNome(newCandidate.getNome())){
            println "Digite um nome com pelo menos 4 caracteres"
            newCandidate.setNome(leitor.nextLine())
        }

        println "Digite o seu sobrenome"
        newCandidate.setSobrenome(leitor.nextLine())
        while(!Regex.validaNome(newCandidate.getSobrenome())){
            println "Digite um sobrenome com pelo menos 4 caracteres"
            newCandidate.setSobrenome(leitor.nextLine())
        }

        println "Digite seu email"
        newCandidate.setEmail(leitor.nextLine())
        while(!Regex.validaEmail(newCandidate.getEmail())){
            println "Digite um email valido"
            newCandidate.setEmail(leitor.nextLine())
        }

        println "Digite seu cpf"
        newCandidate.setCpf(Long.parseLong(leitor.nextLine()))
        while(!Regex.validaCpf(newCandidate.getCpf())){
            println "Digite um numero de 11 caracteres"
            newCandidate.setCpf(Long.parseLong(leitor.nextLine()))
        }

        println "Digite o cep do seu endereço"
        newCandidate.setCep(Integer.parseInt(leitor.nextLine()))
        while(!Regex.validaCep(newCandidate.getCep())){
            println "Digite um numero com 8 caracteres"
            newCandidate.setCep(Integer.parseInt(leitor.nextLine()))
        }

        println "Digite uma descrição das suas habilidades"
        newCandidate.setDescricao(leitor.nextLine())
        while(!Regex.validaDescricao(newCandidate.getDescricao())){
            println "Digite uma descrição usando apenas letras"
            newCandidate.setDescricao(leitor.nextLine())
        }

        println "Digite sua data de nascimento no formato dd/mm/yyyy."
        String dtNascimento = leitor.nextLine()
        while(!Regex.validaDtNascimento(dtNascimento)){
            println "Digite sua data de nascimento no formato dd/mm/yyyy."
            dtNascimento = leitor.nextLine()
        }
        newCandidate.setDtNascimento(dtNascimento)

        List paises = bancoPaises.listar()
        apresentarPaises(paises)
        println "Digite o numero correspondente ao país onde você mora."
        int num = leitor.nextInt()
        leitor.nextLine()
        while(num <= 0 || num > paises.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            num = leitor.nextInt()
            leitor.nextLine()
        }
        newCandidate.setPais(paises[num - 1].id)

        println "Digite qual curso superior você concluiu ou está concluindo"
        newCandidate.setFormacao(leitor.nextLine())
        while(!Regex.validaFormacao(newCandidate.getFormacao())){
            println "Digite qual curso superior você concluiu ou está concluindo"
            newCandidate.setFormacao(leitor.nextLine())
        }

        println "Digite sua senha."
        newCandidate.setSenha(leitor.nextLine())
        while(!Regex.validaSenha(newCandidate.getSenha())){
            println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
            newCandidate.setSenha(leitor.nextLine())
        }

        return newCandidate
    }

    void setDtNascimento(String data) {
        def formatoData = new SimpleDateFormat("dd/MM/yyyy")
        def dtNascimento = formatoData.parse(data) as Date
        this.dtNascimento = dtNascimento
    }

    static void apresentarCandidatos(List candidatos){
        int count = 1
        candidatos.forEach { candidato ->
            println("${count} - ${candidato.descricao}")
            println("Formação: ${candidato.formacao}")
            println("Cep: ${candidato.cep}")
            count++
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Candidato that = (Candidato) o

        if (cpf != that.cpf) return false
        if (idade != that.idade) return false

        return true
    }

    int hashCode() {
        int result
        result = cpf.hashCode()
        result = 31 * result + idade.hashCode()
        return result
    }
}
