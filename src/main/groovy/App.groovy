import linketinder.DAO.Access
import linketinder.DAO.CandidatoAccess
import linketinder.DAO.CompetenciaAccess
import linketinder.DAO.EmpresaAccess
import linketinder.DAO.PaisAccess
import linketinder.DAO.VagasAccess
import linketinder.competencias.Competencias
import linketinder.DAO.bancos.pais.PaisDAO
import linketinder.location.Pais
import linketinder.regex.Regex
import linketinder.usuarios.Candidato
import linketinder.usuarios.Empresa
import linketinder.vagas.Vagas
import linketinder.DAO.bancos.candidato.CandidatoDAO
import linketinder.DAO.bancos.competencias.CompetenciasDAO
import linketinder.DAO.bancos.empresa.EmpresaDAO
import linketinder.DAO.bancos.vagas.VagasDAO

class App {

    private static Access bankAcess;

    static void main(String[] args) {
        CandidatoDAO bancoCandidato = new CandidatoDAO()
        EmpresaDAO bancoEmpresa = new EmpresaDAO()
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
                                        setBank("empresa")
                                        List listaEmpresas = bankAcess.listar()
                                        Empresa.apresentarEmpresa(listaEmpresas)
                                        break
                                    case "2":
                                        setBank("candidato")
                                        List listaCandidatos = bankAcess.listar()
                                        Candidato.apresentarCandidatos(listaCandidatos)
                                        break
                                    case "3":
                                        setBank("competencia")
                                        List listaCompetencias = bankAcess.listar()
                                        Competencias.apresentarCompetencias(listaCompetencias)
                                        break
                                    case "4":
                                        setBank("vagas")
                                        List listaVagas = bankAcess.listar()
                                        Vagas.apresentarVagas(listaVagas)
                                        break
                                    case "5":
                                        setBank("pais")
                                        List listaPaises = bankAcess.listar()
                                        Pais.apresentaPais(listaPaises)
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
                                        setBank("empresa")
                                        Empresa novaEmpresa = Empresa.cadastrarEmpresa(leitor, bancoPaises)
                                        bankAcess.inserir(novaEmpresa)
                                        break
                                    case "2":
                                        setBank("candidato")
                                        Candidato novoCandidato = Candidato.criarCandidato(leitor, bancoPaises)
                                        bankAcess.insert(novoCandidato)
                                        break
                                    case "3":
                                        setBank("competencia")
                                        Competencias novaCompetencia = Competencias.cadastrarCompetencia(leitor)
                                        bankAcess.inserir(novaCompetencia)
                                        break
                                    case "4":
                                        setBank("vagas")
                                        Vagas novaVaga = Vagas.inserirVaga(leitor)
                                        bankAcess.inserir(novaVaga)
                                        break
                                    case "5":
                                        setBank("pais")
                                        Pais novoPais = Pais.criarPais(leitor)
                                        bankAcess.inserir(novoPais)
                                        break
                                    case "6":
                                        Map idsCandidato = Competencias.cadastraCompetenciaCandidato(leitor, bancoCandidato)
                                        bancoCompetencias.inserirCompetenciasCandidatos(idsCandidato.idCandidato, idsCandidato.idCompetencia)
                                        break
                                    case "7":
                                        Map idsVagas = Competencias.cadastraCompetenciaVaga(leitor, bancoVagas)
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
                                        setBank("empresa")
                                        bankAcess.deletar(leitor)
                                        break
                                    case "2":
                                        setBank("candidato")
                                        bankAcess.deletar(leitor)
                                        break
                                    case "3":
                                        setBank("competencias")
                                        bankAcess.deletar(leitor)
                                        break
                                    case "4":
                                        setBank("vagas")
                                        bankAcess.deletar(leitor)
                                        break
                                    case "5":
                                        setBank("pais")
                                        bankAcess.deletar(leitor)
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
                                        setBank("empresa")
                                        bankAcess.atualizar(leitor)
                                        break
                                    case "2":
                                        setBank("candidato")
                                        bankAcess.atualizar(leitor)
                                        break
                                    case "3":
                                        setBank("competencias")
                                        bankAcess.atualizar(leitor)
                                        break
                                    case "4":
                                        setBank("vagas")
                                        bankAcess.atualizar(leitor)
                                        break
                                    case "5":
                                        setBank("pais")
                                        bankAcess.atualizar(leitor)
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