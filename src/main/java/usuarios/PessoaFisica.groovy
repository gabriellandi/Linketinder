package usuarios

import java.util.regex.Pattern


@groovy.transform.ToString
class PessoaFisica extends Usuario{
    String cpf, idade

    PessoaFisica(String cpf, String idade, String nome, String email, String estado, String descricao, String cep, String[] competencias) {
        this.cpf = cpf
        this.idade = idade
        this.nome = nome
        this.email = email
        this.estado = estado
        this.descricao = descricao
        this.cep = cep
        this.competencias = competencias
    }

    PessoaFisica(LinkedHashMap<String, Object> map) {
        this.nome = map.nome
        this.email = map.email
        this.cpf = map.cpf
        this.idade = map.idade
        this.estado = map.estado
        this.cep = map.cep
        this.descricao = map.descricao
        this.competencias = map.competencias
    }

    //CPF
    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        String cpfRegex = '^\\d{11}\$';
        if (cpf.matches(cpfRegex)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    //Idade
    String getIdade() {
        return idade
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

        PessoaFisica that = (PessoaFisica) o

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
