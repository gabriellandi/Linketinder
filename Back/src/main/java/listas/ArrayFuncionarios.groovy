package listas

import usuarios.PessoaFisica

@groovy.transform.ToString
class ArrayFuncionarios  {
    def lista = PessoaFisica[]

    ArrayFuncionarios(){
        this.lista = []
    }

    void addFuncionario(PessoaFisica pessoa){
        this.lista << pessoa
    }

    void printList() {
        lista.each {
            println("\n")
            println("Nome: ${it.nome}")
            println("Idade: ${it.idade}")
            println("Email: ${it.email}")
            println("CPF: ${it.cpf}")
            println("CEP: ${it.cep}")
            println("Estado: ${it.estado}")
            println("Descrição: ${it.descricao}")
            println("Tecnologias dominadas: ${it.competencias}")
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        ArrayFuncionarios that = (ArrayFuncionarios) o

        if (lista != that.lista) return false

        return true
    }

    int hashCode() {
        return (lista != null ? lista.hashCode() : 0)
    }
}
