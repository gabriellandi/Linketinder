package linketinder.usuarios

import groovy.transform.ToString

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