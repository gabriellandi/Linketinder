import linketinder.DAO.Access
import linketinder.DAO.CandidatoAccess
import linketinder.DAO.CompetenciaAccess
import linketinder.DAO.EmpresaAccess
import linketinder.DAO.PaisAccess
import linketinder.DAO.VagasAccess
import linketinder.Model.CompetenciasModel
import linketinder.DAO.bancos.PaisDAO
import linketinder.Model.Regex
import linketinder.DAO.bancos.CandidatoDAO
import linketinder.DAO.bancos.CompetenciasDAO
import linketinder.DAO.bancos.VagasDAO
import linketinder.View.CandidatoView
import linketinder.View.CompetenciasView
import linketinder.View.EmpresaView
import linketinder.View.PaisView
import linketinder.View.VagasView

class App {

    private static Access bankAcess;

    static void main(String[] args) {
        CandidatoDAO bancoCandidato = new CandidatoDAO()
        CompetenciasDAO bancoCompetencias = new CompetenciasDAO()
        VagasDAO bancoVagas = new VagasDAO()
        PaisDAO bancoPaises = new PaisDAO()


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
                                        "5. Listar paises\n" +
                                        "6. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        EmpresaView.apresentarEmpresa()
                                        break
                                    case "2":
                                        CandidatoView.apresentarCandidatos()
                                        break
                                    case "3":
                                        CompetenciasView.apresentarCompetencias()
                                        break
                                    case "4":
                                        VagasView.apresentarVagas()
                                        break
                                    case "5":
                                        PaisView.apresentarPaises()
                                        break
                                    case "6":
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
                                        "5. Cadastrar pais\n" +
                                        "6. Cadastrar competencia nos candidatos\n" +
                                        "7. Cadastrar competencia nas vagas\n" +
                                        "8. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        EmpresaView.cadastraEmpresa(leitor)
                                        break
                                    case "2":
                                        CandidatoView.cadastrarCandidato(leitor)
                                        break
                                    case "3":
                                        CompetenciasView.cadastraCompetencia(leitor)
                                        break
                                    case "4":
                                        VagasView.cadastraVaga(leitor)
                                        break
                                    case "5":
                                        PaisView.cadastrarPais(leitor)
                                        break
                                    case "6":
                                        Map idsCandidato = CompetenciasModel.cadastraCompetenciaCandidato(leitor, bancoCandidato)
                                        bancoCompetencias.inserirCompetenciasCandidatos(idsCandidato.idCandidato, idsCandidato.idCompetencia)
                                        break
                                    case "7":
                                        Map idsVagas = CompetenciasModel.cadastraCompetenciaVaga(leitor, bancoVagas)
                                        bancoCompetencias.inserirCompetenciasVagas(idsVagas.idVaga, idsVagas.idCompetencia)
                                        break
                                    case "8":
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
                                        "5. Deletar pais\n" +
                                        "6. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        EmpresaView.deletaEmpresa(leitor)
                                        break
                                    case "2":
                                        CandidatoView.deletaCandidato(leitor)
                                        break
                                    case "3":
                                        CompetenciasView.deletaCompetencia(leitor)
                                        break
                                    case "4":
                                        VagasView.deletaVaga(leitor)
                                        break
                                    case "5":
                                        PaisView.deletaPais(leitor)
                                        break
                                    case "6":
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
                                        "5. Editar pais\n" +
                                        "6. Retornar ao menu"
                                print("Digite um número de 1 a 5: ")
                                verificador = leitor.nextLine()
                                switch (verificador){
                                    case "1":
                                        EmpresaView.atualizaEmpresa(leitor)
                                        break
                                    case "2":
                                        CandidatoView.atualizaCandidato(leitor)
                                        break
                                    case "3":
                                        CompetenciasView.atualizaCompetencia(leitor)
                                        break
                                    case "4":
                                        VagasView.editaVaga(leitor)
                                        break
                                    case "5":
                                        PaisView.editarPaises(leitor)
                                        break
                                    case "6":
                                        dados = false
                                        break
                                }
                            }
                            break
                        case "5":
                            app = false
                            break
                    }
                }else{
                    println "Digite uma opção válida"
                }
            }
        }
    }

    private static void setBank(String type) {
        switch (type){
            case "candidato":
                bankAcess = new CandidatoAccess()
                break
            case "competencia":
                bankAcess = new CompetenciaAccess()
                break
            case "empresa":
                bankAcess = new EmpresaAccess()
                break
            case "pais":
                bankAcess = new PaisAccess()
                break
            case "vagas":
                bankAcess = new VagasAccess()
                break
        }
    }
}