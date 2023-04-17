package linketinder.Model

import groovy.transform.ToString

@ToString
class EmpresaModel {
    String nome
    String email
    String descricao
    String senha
    int cep
    int pais
    Long cnpj

    EmpresaModel(Long cnpj, int pais, String nome, String email, String descricao, int cep, String senha) {
        this.cnpj = cnpj
        this.pais = pais
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.senha = senha
    }

    EmpresaModel(){
        this.cnpj = cnpj
        this.pais = pais
        this.nome = nome
        this.email = email
        this.descricao = descricao
        this.cep = cep
        this.senha = senha
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        EmpresaModel that = (EmpresaModel) o

        if (cnpj != that.cnpj) return false

        return true
    }

    int hashCode() {
        return cnpj.hashCode()
    }
}
