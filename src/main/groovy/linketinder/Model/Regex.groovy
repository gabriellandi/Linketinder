package linketinder.Model

import static java.util.regex.Pattern.compile

class Regex {
    static def regexVerificador = compile('^[1-8]$') // Aceita apenas numeros entre 1 e 7
    static def regexNome = compile('^[a-zA-Z. ]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    static def regexEmail = compile('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$') // Aceita apenas emails, deve possuir carateres alfanumericos, um arroba, apenas letras, um ponto e apenas letras, exatamente nessa ordem
    static def regexCpf = compile('^\\d{11}$') // Aceita apenas numeros e deve conter 11 numeros
    static def regexCnpj = compile('^\\d{14}$') // Aceita apenas numeros e deve conter 11 numeros
    static def regexEstado = compile('^[a-zA-Z]{2}$') // Aceita a abreviação de um estado, ou seja, 2 letras
    static def regexCep = compile('^\\d{8}$') // Aceita apenas numeros e deve possuir o tamanho de 8 caracteres
    static def regexDescricao = compile('^[a-zA-ZÀ-ÿ\\s.,]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    static def regexDtNascimento = compile(/\b(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[012])\/(19|20)\d{2}\b/) // Aceita datas apenas no formato dd/mm/yyyy
    static def regexFormacao = compile('^[a-zA-Z. ]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    static def regexSenha = compile('^[1-9a-z. ]{4,}$') // Aceita uma letra com numeros e letras minusculas, com um tamanho maior que 4 caracteres

    static boolean validaCep(int cep){
        return regexCep.matcher(cep.toString()).matches()
    }

    static boolean validaVerificador(String verificador) {
        return regexVerificador.matcher(verificador).matches()
    }

    static boolean validaNome(String nome) {
        return regexNome.matcher(nome).matches()
    }

    static boolean validaEmail(String email) {
        return regexEmail.matcher(email).matches()
    }

    static boolean validaCpf(Long cpf) {
        return regexCpf.matcher(cpf.toString()).matches()
    }

    static boolean validaCnpj(Long cnpj) {
        return regexCnpj.matcher(cnpj.toString()).matches()
    }

    static boolean validaEstado(String estado) {
        return regexEstado.matcher(estado).matches()
    }

    static boolean validaDescricao(String descricao) {
        return regexDescricao.matcher(descricao).matches()
    }

    static boolean validaDtNascimento(String dataNascimento) {
        return regexDtNascimento.matcher(dataNascimento).matches()
    }

    static boolean validaFormacao(String formacao) {
        return regexFormacao.matcher(formacao).matches()
    }

    static boolean validaSenha(String senha) {
        return regexSenha.matcher(senha).matches()
    }


}