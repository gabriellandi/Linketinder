package listas

import usuarios.PessoaFisica

@groovy.transform.ToString
class ArrayFuncionarios {
    def lista = PessoaFisica[]

    ArrayFuncionarios(){
        this.lista = []
    }

    void addFuncionario(PessoaFisica pessoa){
        this.lista << pessoa
    }

    void printList() {
        lista.each {
            println("Nome: ${it.nome}")
            println("Email: ${it.email}")
            println("CPF: ${it.cpf}")
            println("\n")
        }
    }
}
