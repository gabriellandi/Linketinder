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
            println("\n")
            println("Nome: ${it.nome}")
            println("Email: ${it.email}")
            println("CNPJ: ${it.cnpj}")
            println("País: ${it.pais}")
            println("Estado: ${it.estado}")
            println("Cep: ${it.cep}")
            println("Descrição da empresa: ${it.descricao}")
            println("Tecnologias usadas na empresa: ${it.competencias}")
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        ArrayEmpresas that = (ArrayEmpresas) o

        if (lista != that.lista) return false

        return true
    }

    int hashCode() {
        return (lista != null ? lista.hashCode() : 0)
    }
}
