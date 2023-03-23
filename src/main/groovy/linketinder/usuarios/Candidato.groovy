package linketinder.usuarios

import groovy.transform.ToString

@ToString
class Candidato extends Usuario{
    Long cpf
    String sobrenome
    Date dtNascimento
    String formacao

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

    //CPF
    Long getCpf() {
        return cpf
    }

    void setCpf(int cpf) {
        String verify = Integer.toString(cpf)
        String cpfRegex = '^\\d{11}\$'
        if (verify.matches(cpfRegex)) {
            this.cpf = cpf
        } else {
            throw new IllegalArgumentException("CPF inválido.")
        }
    }


    void setIdade(String idade) {
        String idadeRegex = '[1-8]\\d|9[0-9]'
        if (idade.matches(idadeRegex)){
            this.idade = idade
        } else {
            throw new IllegalArgumentException("Idade inválida")
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
