package listas

import usuarios.PessoaFisica
import usuarios.PessoaJuridica

class ArrayEmpresas {
    def lista = PessoaFisica[]

    ArrayEmpresas(){
        this.lista = []
    }

    void addEmpresa(PessoaJuridica empresa){
        this.lista << empresa
    }

    void printList() {
        lista.each {
            println("Nome: ${it.nome}")
            println("Email: ${it.email}")
            println("CNPJ: ${it.cnpj}")
            println("\n")
        }
    }
}
