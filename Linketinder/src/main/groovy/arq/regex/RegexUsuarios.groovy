package classes.regex

import static java.util.regex.Pattern.compile

class RegexUsuarios {
    def regexVerificador = compile('^[1-9]|1[0-1]$') // Aceita apenas numeros entre 1 e 5
    def regexNome = compile('^[a-zA-Z. ]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    def regexEmail = compile('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$') // Aceita apenas emails, deve possuir carateres alfanumericos, um arroba, apenas letras, um ponto e apenas letras, exatamente nessa ordem
    def regexCpf = compile('^\\d{11}$') // Aceita apenas numeros e deve conter 11 numeros
    def regexCnpj = compile('^\\d{14}$') // Aceita apenas numeros e deve conter 11 numeros
    def regexEstado = compile('^[a-zA-Z]{2}$') // Aceita a abreviação de um estado, ou seja, 2 letras
    def regexCep = compile('^\\d{8}$') // Aceita apenas numeros e deve possuir o tamanho de 8 caracteres
    def regexDescricao = compile('^[a-zA-ZÀ-ÿ\\s.,]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    def regexDtNascimento = compile(/\b(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[012])\/(19|20)\d{2}\b/) // Aceita datas apenas no formato dd/mm/yyyy
    def regexFormacao = compile('^[a-zA-Z. ]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    def regexSenha = compile('^[1-9a-z. ]{4,}$') // Aceita uma letra com numeros e letras minusculas, com um tamanho maior que 4 caracteres
}