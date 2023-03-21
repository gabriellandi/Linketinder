package classes.usuarios

import groovy.transform.ToString
import classes.regex.RegexUsuarios

@ToString
class Usuario {
    String nome
    String email
    String estado
    String descricao
    String senha
    int cep
    int pais

    RegexUsuarios usuariosRegex = new RegexUsuarios()

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        if(nome.matches(usuariosRegex.regexNome)){
            this.nome = nome
        } else {
            throw new IllegalArgumentException("Nome Inválido")
        }
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        if(email.matches(usuariosRegex.regexEmail)){
            this.email = email
        } else {
            throw new IllegalArgumentException("Email Inválido")
        }
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        if(descricao.matches(usuariosRegex.regexDescricao)){
            this.descricao = descricao
        } else {
            throw new IllegalArgumentException("Descrição inválida")
        }
    }

    int getCep() {
        return cep
    }

    void setCep(int cep) {
        String teste = Integer.toString(cep)
        if(teste.matches(usuariosRegex.regexCep)){
            this.cep = cep
        } else {
            throw new IllegalArgumentException("Cep inválido")
        }
    }

}
