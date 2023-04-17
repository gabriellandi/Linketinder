package linketinder.Model

import groovy.transform.ToString

import java.text.SimpleDateFormat

@ToString
class CandidatoModel {
    String nome
    String email
    String estado
    String descricao
    String senha
    int cep
    int pais
    Long cpf
    String sobrenome
    Date dtNascimento
    String formacao

    CandidatoModel(){
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

    CandidatoModel(String sobrenome, Long cpf, String nome, String email, String descricao, int cep, Date dtNascimento, int pais, String formacao, String senha) {
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

    void setDtNascimento(String data) {
        def formatoData = new SimpleDateFormat("dd/MM/yyyy")
        def dtNascimento = formatoData.parse(data) as Date
        this.dtNascimento = dtNascimento
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        CandidatoModel that = (CandidatoModel) o

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
