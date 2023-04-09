package linketinder.usuarios

import groovy.transform.ToString
import linketinder.jdbc.bancos.pais.PaisJDBC
import linketinder.regex.Regex

@ToString
class Empresa extends Usuario {
    Long cnpj

    Empresa(Long cnpj, int pais, String nome, String email, String descricao, int cep, String senha) {
        this.cnpj = cnpj
        this.pais = pais
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.senha = senha
    }
    Empresa(){
        this.cnpj = cnpj
        this.pais = pais
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.senha = senha
    }

    static Empresa cadastrarEmpresa(Scanner leitor, PaisJDBC bancoPaises){
        Empresa empresa = new Empresa()
        println "Digite o nome da sua empresa"
        empresa.setNome(leitor.nextLine())
        while(!Regex.validaNome(empresa.getNome())){
            println "Digite um nome com pelo menos 4 caracteres"
            empresa.setNome(leitor.nextLine())
        }

        println "Digite o email coorporativo"
        empresa.setEmail(leitor.nextLine())
        while(!Regex.validaEmail(empresa.getEmail())){
            println "Digite um email válido"
            empresa.setEmail(leitor.nextLine())
        }

        println "Digite o numero do seu cnpj"
        empresa.setCnpj(Long.parseLong(leitor.nextLine()))
        while(!Regex.validaCnpj(empresa.getCnpj())){
            println "Digite um numero de 14 caracteres"
            empresa.setCnpj(Long.parseLong(leitor.nextLine()))
        }

        List paises = bancoPaises.listar()
        apresentarPaises(paises)
        println "Digite o numero correspondente ao país onde sua empresa está localizada."
        int num = leitor.nextInt()
        leitor.nextLine()
        while(num <= 0 || num > paises.size()){
            println "Digite o numero válido correspondente ao país onde sua empresa está localizada."
            num = leitor.nextInt()
            leitor.nextLine()
        }
        empresa.setPais(paises[num - 1].id)

        println "Digite o cep do endereço da sua empresa"
        empresa.setCep(Integer.parseInt(leitor.nextLine()))
        while(!Regex.validaCep(empresa.getCep())){
            println "Digite um numero com 8 caracteres"
            empresa.setCep(Integer.parseInt(leitor.nextLine()))
        }

        println "Digite uma descrição da sua empresa"
        empresa.setDescricao(leitor.nextLine())
        while(!Regex.validaDescricao(empresa.getDescricao())){
            println "Digite uma descrição da sua empresa"
            empresa.setDescricao(leitor.nextLine())
        }

        println "Digite sua senha."
        empresa.setSenha(leitor.nextLine())
        while(!Regex.validaSenha(empresa.getSenha())){
            println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
            empresa.setSenha(leitor.nextLine())
        }

        return empresa
    }

    static void apresentarEmpresa(List listaEmpresa){
        int count = 1
        listaEmpresa.forEach { empresa ->
            println("${count} - ${empresa.nome}")
            println("cnpj: ${empresa.cnpj}")
            println("Cep: ${empresa.cep}")
            count++
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Empresa that = (Empresa) o

        if (cnpj != that.cnpj) return false

        return true
    }

    int hashCode() {
        return cnpj.hashCode()
    }
}
