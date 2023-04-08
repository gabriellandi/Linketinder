
import linketinder.competencias.Competencias
import linketinder.regex.Regex
import linketinder.usuarios.Candidato
import linketinder.usuarios.Empresa
import linketinder.vagas.Vagas
import linketinder.jdbc.bancos.candidato.CandidatoJDBC
import linketinder.jdbc.bancos.competencias.CompetenciasJDBC
import linketinder.jdbc.bancos.empresa.EmpresaJDBC
import linketinder.jdbc.bancos.vagas.VagasJDBC

class App {
    static void main(String[] args) {
        CandidatoJDBC bancoCandidato = new CandidatoJDBC()
        EmpresaJDBC bancoEmpresa = new EmpresaJDBC()
        CompetenciasJDBC bancoCompetencias = new CompetenciasJDBC()
        VagasJDBC bancoVagas = new VagasJDBC()


        try(Scanner leitor = new Scanner(System.in)){
            def app = true
            String verificador = ""

            while(app){
                println()
                println "Seja bem vindo ao Linketinder\n" +
                        "Digite o numero correspondente à ação desejada\n" +
                        "1. Listar dados\n" +
                        "2. Cadastrar dados\n" +
                        "3. Deletar dados \n" +
                        "4. Editar dados \n" +
                        "5. Encerrar Linketinder"
                print("Digite um número de 1 a 5: ")
                verificador = leitor.nextLine()
                if(Regex.validaVerificador(verificador)){
                    switch (verificador){
                        case "1":
                            boolean dados = true
                            while(dados) {
                                println()
                                println "Digite o numero correspondente à ação desejada\n" +
                                        "1. Listar empregadores\n" +
                                        "2. Listar candidatos\n" +
                                        "3. Listar competencias\n" +
                                        "4. Listar vagas\n" +
                                        "5. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        List listaEmpresas = bancoEmpresa.listar()
                                        Empresa.apresentarEmpresa(listaEmpresas)
                                        break
                                    case "2":
                                        List listaCandidatos = bancoCandidato.listar()
                                        Candidato.apresentarCandidatos(listaCandidatos)
                                        break
                                    case "3":
                                        List listaCompetencias = bancoCompetencias.listarCompetencias()
                                        Competencias.apresentarCompetencias(listaCompetencias)
                                        break
                                    case "4":
                                        List listaVagas = bancoVagas.listar()
                                        Vagas.apresentarVagas(listaVagas)
                                        break
                                    case "5":
                                        dados = false
                                        break
                                }
                            }
                            break
                        case "2":
                            boolean dados = true
                            while(dados) {
                                println()
                                println "Digite o numero correspondente à ação desejada\n" +
                                        "1. Cadastrar empregadores\n" +
                                        "2. Cadastrar candidatos\n" +
                                        "3. Cadastrar competencias\n" +
                                        "4. Cadastrar vagas\n" +
                                        "5. Cadastrar competencia nos candidatos\n" +
                                        "6. Cadastrar competencia nas vagas\n" +
                                        "7. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        Empresa novaEmpresa = Empresa.cadastrarEmpresa(leitor)
                                        bancoEmpresa.inserir(novaEmpresa)
                                        break
                                    case "2":
                                        Candidato novoCandidato = Candidato.cadastrarCandidato(leitor)
                                        bancoCandidato.inserir(novoCandidato)
                                        break
                                    case "3":
                                        Competencias novaCompetencia = Competencias.cadastrarCompetencia(leitor)
                                        bancoCompetencias.inserir(novaCompetencia)
                                        break
                                    case "4":
                                        Vagas novaVaga = Vagas.inserirVaga(leitor)
                                        bancoVagas.inserir(novaVaga)
                                        break
                                    case "5":
                                        Competencias.cadastraCompetenciaCandidato(leitor)
                                        break
                                    case "6":
                                        Competencias.cadastraCompetenciaVaga(leitor)
                                        break
                                    case "7":
                                        dados = false
                                        break
                                }
                            }
                            break
                        case "3":
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
                        case "4":
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