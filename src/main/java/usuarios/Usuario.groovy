package usuarios

import regex.RegexUsuarios
import scala.util.matching.Regex

@groovy.transform.ToString
class Usuario {
    String nome, email, estado, descricao, cep
    def registroCurtida = int[]
    def competencias = []

    RegexUsuarios usuariosRegex = new RegexUsuarios();

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        if(nome.matches(usuariosRegex.regexNome)){
            this.nome = nome
        } else {
            throw new IllegalArgumentException("Nome Inválido");
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

    String getEstado() {
        return estado
    }

    void setEstado(String estado) {
        if(estado.matches(usuariosRegex.regexEstado)){
            this.estado = estado
        } else {
            throw new IllegalArgumentException("Estado inválido")
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

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        if(cep.matches(usuariosRegex.regexCep)){
            this.cep = cep
        } else {
            throw new IllegalArgumentException("Cep inválido")
        }
    }

    def getRegistroCurtida() {
        return registroCurtida
    }

    void setRegistroCurtida(registroCurtida) {
        this.registroCurtida = registroCurtida
    }

    def getCompetencias() {
        return competencias
    }

    void setCompetencias(competencias) {
        this.competencias = competencias
    }
}
