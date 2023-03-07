package regex

import java.util.regex.Pattern

class RegexUsuarios {
    def regexVerificador = Pattern.compile('^[1-5]$') // Aceita apenas numeros entre 1 e 5
    def regexNome = Pattern.compile('^[a-zA-Z ]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    def regexEmail = Pattern.compile('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$') // Aceita apenas emails, deve possuir carateres alfanumericos, um arroba, apenas letras, um ponto e apenas letras, exatamente nessa ordem
    def regexRegistro = Pattern.compile('^\\d{11}$') // Aceita apenas numeros e deve conter 11 numeros
    def regexPais = Pattern.compile('^[a-zA-Z]+$') // Aceita apenas letras minusculas e maiusculas
    def regexEstado = Pattern.compile('^[a-zA-Z]{2}$') // Aceita a abreviação de um estado, ou seja, 2 letras
    def regexCep = Pattern.compile('^\\d{8}$') // Aceita apenas numeros e deve possuir o tamanho de 8 caracteres
    def regexIdade = Pattern.compile('[1-8]\\d|9[0-9]') // Aceita apenas numeros maiores que 18 e menores que 99
    def regexDescricao = Pattern.compile('^[a-zA-ZÀ-ÿ\\s.,]{4,}$') // Aceita apenas letras e espaços e a palavra deve possuir pelo menos 4 caracteres
    def regexCompetencia = Pattern.compile('^[a-zA-Z]+$') // Aceita apenas letras minusculas e maiusculas
    def competencias = []
}
