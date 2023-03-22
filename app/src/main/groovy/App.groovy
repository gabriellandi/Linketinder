
import linketinder.competencias.Competencias
import linketinder.regex.RegexUsuarios
import linketinder.usuarios.Candidato
import linketinder.usuarios.Empresa
import linketinder.vagas.Vagas
import linketinder.jdbc.bancos.candidato.CandidatoJDBC
import linketinder.jdbc.bancos.competencias.CompetenciasJDBC
import linketinder.jdbc.bancos.empresa.EmpresaJDBC
import linketinder.jdbc.bancos.pais.PaisJDBC
import linketinder.jdbc.bancos.vagas.VagasJDBC

import java.text.SimpleDateFormat

class App {
    static void main(String[] args) {
        //Bancos
        CandidatoJDBC bancoCandidato = new CandidatoJDBC()
        PaisJDBC bancoPaises = new PaisJDBC()
        EmpresaJDBC bancoEmpresa = new EmpresaJDBC()
        CompetenciasJDBC bancoCompetencias = new CompetenciasJDBC()
        VagasJDBC bancoVagas = new VagasJDBC()


        try(Scanner leitor = new Scanner(System.in)){

            RegexUsuarios regex = new RegexUsuarios()

            //Usuarios
            def nome
            def sobrenome
            def email
            def registro
            def pais
            def estado
            def cep
            def idade
            def descricao
            def competencia
            def dtNascimento
            def formacao
            def senha

            //Vagas
            def contador
            def id
            def cidade

            def app = true
            String verificador = ""

            while(app){
                println()
                println "Seja bem vindo ao Linketinder\n" +
                        "Digite o numero correspondente à ação desejada\n" +
                        "1. Listar empregadores\n" +
                        "2. Listar candidatos\n" +
                        "3. Adicionar uma nova empresa\n" +
                        "4. Adicionar um novo candidato\n" +
                        "5. Cadastrar vagas \n" +
                        "6. Cadastrar competencias \n" +
                        "7. Inserir competencias nos candidatos \n" +
                        "8. Inserir competencias nas vagas \n" +
                        "9. Deletar dados \n" +
                        "10. Editar dados \n" +
                        "11. Encerrar Linketinder"
                print("Digite um número de 1 a 11: ")
                verificador = leitor.nextLine()

                if(regex.regexVerificador.matcher(verificador).matches()){
                    switch (verificador){
                        case "1":
                            bancoEmpresa.listar()
                            break
                        case "2":
                            bancoCandidato.listar()
                            break
                        case "3":
                            println "Digite o nome da sua empresa"
                            nome = leitor.nextLine()
                            while(!regex.regexNome.matcher(nome).matches()){
                                println "Digite um nome com pelo menos 4 caracteres"
                                nome = leitor.nextLine()
                            }

                            println "Digite o email coorporativo"
                            email =  leitor.nextLine()
                            while(!regex.regexEmail.matcher(email).matches()){
                                println "Digite um email válido"
                                email = leitor.nextLine()
                            }

                            println "Digite o numero do seu cnpj"
                            registro =  leitor.nextLine()
                            while(!regex.regexCnpj.matcher(registro).matches()){
                                println "Digite um numero de 14 caracteres"
                                registro = leitor.nextLine()
                            }

                            println "Digite o numero correspondente ao país onde você mora."
                            bancoPaises.listarPaises()
                            pais = leitor.nextLine()
                            while(!regex.regexVerificador.matcher(pais).matches()){
                                println "Digite o numero válido correspondente ao país onde você mora."
                                pais = leitor.nextLine()
                            }

                            println "Digite o cep do endereço da empresa"
                            cep =  leitor.nextLine()
                            while(!regex.regexCep.matcher(cep).matches()){
                                println "Digite um numero com 8 caracteres"
                                cep = leitor.nextLine()
                            }

                            println "Digite uma descrição da sua empresa"
                            descricao =  leitor.nextLine()
                            while(!regex.regexDescricao.matcher(descricao).matches()){
                                println "Digite uma descrição usando apenas letras"
                                descricao = leitor.nextLine()
                            }

                            println "Digite sua senha."
                            senha = leitor.nextLine()
                            while(!regex.regexSenha.matcher(senha).matches()){
                                println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
                                senha = leitor.nextLine()
                            }

                            def newCompany = new Empresa(Long.parseLong(registro), Integer.parseInt(pais), nome, email, descricao, Integer.parseInt(cep), senha)

                            bancoEmpresa.inserir(newCompany)

                            break
                        case "4":
                            println "Digite o seu nome"
                            nome = leitor.nextLine()
                            while(!regex.regexNome.matcher(nome).matches()){
                                nome = leitor.nextLine()
                                println "Digite um nome com pelo menos 4 caracteres"
                            }

                            println "Digite o seu sobrenome"
                            sobrenome = leitor.nextLine()
                            while(!regex.regexNome.matcher(sobrenome).matches()){
                                sobrenome = leitor.nextLine()
                                println "Digite um sobrenome com pelo menos 4 caracteres"
                            }

                            println "Digite seu email"
                            email =  leitor.nextLine()
                            while(!regex.regexEmail.matcher(email).matches()){
                                email = leitor.nextLine()
                                println "Digite um email válido"
                            }

                            println "Digite o numero do seu cpf"
                            registro =  leitor.nextLine()
                            while(!regex.regexCpf.matcher(registro).matches()){
                                registro = leitor.nextLine()
                                println "Digite um numero de 11 caracteres"
                            }

                            println "Digite o cep do seu endereço"
                            cep =  leitor.nextLine()
                            while(!regex.regexCep.matcher(cep).matches()){
                                cep = leitor.nextLine()
                                println "Digite um numero com 8 caracteres"
                            }

                            println "Digite uma descrição das suas habilidades"
                            descricao =  leitor.nextLine()
                            while(!regex.regexDescricao.matcher(descricao).matches()){
                                descricao = leitor.nextLine()
                                println "Digite uma descrição usando apenas letras"
                            }

                            println "Digite sua data de nascimento no formato dd/mm/yyyy."
                            dtNascimento = leitor.nextLine()
                            while(!regex.regexDtNascimento.matcher(dtNascimento).matches()){
                                println "Digite sua data de nascimento no formato dd/mm/yyyy."
                                dtNascimento = leitor.nextLine()
                            }
                            def formatoData = new SimpleDateFormat("dd/MM/yyyy")
                            def data = formatoData.parse(dtNascimento) as Date

                            println "Digite o numero correspondente ao país onde você mora."
                            bancoPaises.listarPaises()
                            pais = leitor.nextLine()
                            while(!regex.regexVerificador.matcher(pais).matches()){
                                println "Digite o numero válido correspondente ao país onde você mora."
                                pais = leitor.nextLine()
                            }

                            println "Digite qual curso superior você concluiu ou está concluindo"
                            formacao = leitor.nextLine()
                            while(!regex.regexFormacao.matcher(formacao).matches()){
                                println "Digite qual curso superior você concluiu ou está concluindo"
                                formacao = leitor.nextLine()
                            }

                            println "Digite sua senha."
                            senha = leitor.nextLine()
                            while(!regex.regexSenha.matcher(senha).matches()){
                                println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
                                senha = leitor.nextLine()
                            }


                            def newCandidate = new Candidato(sobrenome, Long.parseLong(registro), nome, email, descricao, Integer.parseInt(cep), data, Integer.parseInt(pais), formacao, senha)
                            bancoCandidato.inserir(newCandidate)
                            break
                        case "5":
                            //Cadastrar vagas
                            println "Digite um nome para vaga"
                            nome = leitor.nextLine()
                            while(!regex.regexNome.matcher(nome).matches()){
                                println "Digite um nome com pelo menos 4 caracteres"
                                nome = leitor.nextLine()
                            }

                            println "Digite o numero correspondente ao ID da empresa que está criando a vaga"
                            bancoEmpresa.listar()
                            contador = bancoEmpresa.contar()
                            id = leitor.nextLine()
                            while(Integer.parseInt(id) > contador){
                                println "Digite o numero válido correspondente a empresa onde você mora."
                                id = leitor.nextLine()
                            }

                            println "Digite uma descrição da vaga"
                            descricao =  leitor.nextLine()
                            while(!regex.regexDescricao.matcher(descricao).matches()){
                                println "Digite uma descrição usando apenas letras"
                                descricao = leitor.nextLine()
                            }

                            println "Digite o estado onde ela fica localizada"
                            estado =  leitor.nextLine()
                            while(!regex.regexEstado.matcher(estado).matches()){
                                println "Digite uma sigla com 2 caracteres"
                                estado = leitor.nextLine()
                            }

                            println "Digite a cidade onde ela fica localizada"
                            cidade =  leitor.nextLine()
                            while(!regex.regexNome.matcher(cidade).matches()){
                                println "Digite um nome com pelo menos 4 caracteres"
                                cidade = leitor.nextLine()
                            }

                            Vagas newVaga = new Vagas(nome, Integer.parseInt(id), descricao, estado, cidade, new Date())

                            bancoVagas.inserir(newVaga)

                            bancoVagas.listar()
                            break
                        case "6":
                            //Cadastrar competencia
                            println "Digite um nome para a competencia"
                            nome = leitor.nextLine()
                            while(bancoCompetencias.verificaCompetencias().contains(nome.toUpperCase())){
                                println "Esta competencia já existe no banco de dados"
                                nome = leitor.nextLine()
                            }

                            //Cadastra a competencia no banco
                            Competencias newComp = new Competencias(nome)
                            bancoCompetencias.inserir(newComp)

                            bancoCompetencias.listarCompetencias()

                            break
                        case "7":
                            // Inserir competencias nos candidatos
                            println "Digite o numero correspondente ao ID do candidato que você deseja inserir a competencia"
                            bancoCandidato.listar()
                            contador = bancoCandidato.contar()
                            String idCandidato = leitor.nextLine()
                            while(Integer.parseInt(idCandidato) > contador){
                                println "Digite o numero válido correspondente ao ID do candidato que você deseja inserir a competencia"
                                idCandidato = leitor.nextLine()
                            }

                            println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
                            bancoCompetencias.listarCompetencias()
                            contador = bancoCompetencias.contar()
                            id = leitor.nextLine()
                            while(Integer.parseInt(id) > contador){
                                println "Digite o numero correspondente a competencia que você deseja inserir."
                                id = leitor.nextLine()
                            }

                            bancoCompetencias.inserirCompetenciasCandidatos(Integer.parseInt(idCandidato), Integer.parseInt(id))

                            break
                        case "8":
                            // Inserir competencias nas vagas
                            println "Digite o numero correspondente ao ID da vaga que você deseja inserir a competencia"
                            bancoVagas.listar()
                            contador = bancoVagas.contar()
                            String idVaga = leitor.nextLine()
                            while(Integer.parseInt(idVaga) > contador){
                                println "Digite o numero válido correspondente ao ID da vaga que você deseja inserir a competencia"
                                idVaga = leitor.nextLine()
                            }

                            println "Digite o numero correspondente ao ID da competencia que você deseja inserir"
                            bancoCompetencias.listarCompetencias()
                            contador = bancoCompetencias.contar()
                            id = leitor.nextLine()
                            while(Integer.parseInt(id) > contador){
                                println "Digite o numero correspondente a competencia que você deseja inserir."
                                id = leitor.nextLine()
                            }

                            bancoCompetencias.inserirCompetenciasVagas(Integer.parseInt(idVaga), Integer.parseInt(id))

                            break
                        case "9":
                            //Deletar Dados
                            boolean dados = true
                            while(dados) {
                                println()
                                println "Digite o numero correspondente à ação desejada\n" +
                                        "1. Deletar empregadores\n" +
                                        "2. Deletar candidatos\n" +
                                        "3. Deletar competencias\n" +
                                        "4. Deletar vagas\n" +
                                        "5. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        bancoEmpresa.deletarEmpresa(leitor)
                                        break
                                    case "2":
                                        bancoCandidato.deletarCandidato(leitor)
                                        break
                                    case "3":
                                        bancoCompetencias.deletarCompetencia(leitor)
                                        break
                                    case "4":
                                        bancoVagas.deletarVaga(leitor)
                                        break
                                    case "5":
                                        dados = false
                                        break
                                }
                            }
                            break
                        case "10":
                            boolean dados = true
                            while(dados) {
                                println()
                                println "Digite o numero correspondente à ação desejada\n" +
                                        "1. Editar empregadores\n" +
                                        "2. Editar candidatos\n" +
                                        "3. Editar competencias\n" +
                                        "4. Editar vagas\n" +
                                        "5. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        bancoEmpresa.atualizarEmpresa(leitor)
                                        break
                                    case "2":
                                        bancoCandidato.atualizarCandidato(leitor)
                                        break
                                    case "3":
                                        bancoCompetencias.atualizarCompetencia(leitor)
                                        break
                                    case "4":
                                        bancoVagas.atualizarVaga(leitor)
                                        break
                                    case "5":
                                        dados = false
                                        break
                                }
                            }
                            break
                        case "11":
                            app = false
                            break
                    }
                }else{
                    println "Digite uma opção válida"
                }
            }
        }
    }
}