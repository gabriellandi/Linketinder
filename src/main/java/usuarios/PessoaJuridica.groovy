package usuarios

import groovy.transform.ToString

@ToString
class PessoaJuridica extends Usuario {
    String pais
    String cnpj

    PessoaJuridica(LinkedHashMap<String, Object> map) {
        this.nome = map.nome
        this.email = map.email
        this.cnpj = map.cnpj
        this.pais = map.pais
        this.estado = map.estado
        this.cep = map.cep
        this.descricao = map.descricao
        this.competencias = map.competencias
    }

    PessoaJuridica(String cnpj, String pais, String nome, String email, String estado, String descricao, String cep, String[] competencias) {
        this.cnpj = cnpj
        this.pais = pais
        this.nome = nome
        this.email = email
        this.estado = estado
        this.descricao = descricao
        this.cep = cep
        this.competencias = competencias
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        PessoaJuridica that = (PessoaJuridica) o

        if (cnpj != that.cnpj) return false
        if (pais != that.pais) return false

        return true
    }

    int hashCode() {
        int result
        result = (pais != null ? pais.hashCode() : 0)
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0)
        return result
    }
}
