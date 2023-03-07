import regex.RegexUsuarios
import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper
import listas.ArrayEmpresas
import listas.ArrayFuncionarios
import usuarios.PessoaFisica
import usuarios.PessoaJuridica

static void main(String[] args) {



    //Array onde será armazenado todos os candidatos
    def listaCandidatos = new ArrayFuncionarios()

    //Leitura do XML
    def candidatosXml = new File('arquivos/candidatos.xml')
    def candidatos = new XmlSlurper().parse(candidatosXml)

    //Adicionando os candidatos ao array
    candidatos.candidato.each { candidato ->
        def trabalhador = new PessoaFisica(nome: candidato.@nome, email: candidato.@email, cpf: candidato.@cpf, idade: candidato.@idade, estado: candidato.@estado, cep: candidato.@cep, descricao: candidato.@descricao, competencias: candidato.@competencias)
        listaCandidatos.addFuncionario(trabalhador)
    }

    //Array onde será armazenado todas as empresas
    def listaEmpresas = new ArrayEmpresas()

    //Leitura do XML
    def empresasXml = new File('arquivos/empresas.xml')
    def empresas = new XmlSlurper().parse(empresasXml)

    //Adicionando as empresas ao array
    empresas.empresa.each { empresa ->
        def company = new PessoaJuridica(nome: empresa.@nome, email: empresa.@email, cnpj: empresa.@cnpj, pais: empresa.@pais, estado: empresa.@estado, cep: empresa.@cep, descricao: empresa.@descricao, competencias: empresa.@competencias)
        listaEmpresas.addEmpresa(company)
    }

    try(Scanner leitor = new Scanner(System.in)){

        RegexUsuarios regex = new RegexUsuarios();

        def nome
        def email
        def registro
        def pais
        def estado
        def cep
        def idade
        def descricao
        def competencia
        def competencias = []
        def addCompetencia = true
        def verificador = ""

        while(true){
            println()
            println "Seja bem vindo ao Linketinder\n" +
                    "Digite o numero correspondente à ação desejada\n" +
                    "1. Listar empregadores\n" +
                    "2. Listar candidatos\n" +
                    "3. Adicionar uma nova empresa\n" +
                    "4. Adicionar um novo candidato\n" +
                    "5. Encerrar Linketinder"
            print("Digite um número de 1 a 5: ")
            verificador = leitor.nextLine()

            if(regex.regexVerificador.matcher(verificador).matches()){
                switch (verificador){
                    case "1":
                        listaEmpresas.printList()
                        break
                    case "2":
                        listaCandidatos.printList()
                        break
                    case "3":
                        println "Digite o nome da sua empresa"
                        nome = leitor.nextLine()
                        while(!regex.regexNome.matcher(nome).matches()){
                            nome = leitor.nextLine()
                            println "Digite um nome com pelo menos 4 caracteres"
                        }

                        println "Digite o email coorporativo"
                        email =  leitor.nextLine()
                        while(!regex.regexEmail.matcher(email).matches()){
                            email = leitor.nextLine()
                            println "Digite um email válido"
                        }

                        println "Digite o numero do seu cnpj"
                        registro =  leitor.nextLine()
                        while(!regex.regexRegistro.matcher(registro).matches()){
                            registro = leitor.nextLine()
                            println "Digite um numero de 11 caracteres"
                        }

                        println "Digite o país onde é localizada a sede da empresa"
                        pais =  leitor.nextLine()
                        while(!regex.regexPais.matcher(pais).matches()){
                            pais = leitor.nextLine()
                            println "Digite um nome usando apenas letras"
                        }

                        println "Digite o estado onde ela fica localizada"
                        estado =  leitor.nextLine()
                        while(!regex.regexEstado.matcher(estado).matches()){
                            estado = leitor.nextLine()
                            println "Digite uma sigla com 2 caracteres"
                        }

                        println "Digite o cep do endereço da empresa"
                        cep =  leitor.nextLine()
                        while(!regex.regexCep.matcher(cep).matches()){
                            cep = leitor.nextLine()
                            println "Digite um numero com 8 caracteres"
                        }

                        println "Digite uma descrição da sua empresa"
                        descricao =  leitor.nextLine()
                        while(!regex.regexDescricao.matcher(descricao).matches()){
                            descricao = leitor.nextLine()
                            println "Digite uma descrição usando apenas letras"
                        }

                        competencias = [];
                        while(addCompetencia){
                            println 'Deseja adicionar uma tecnologia que sua empresa utiliza?'
                            verificador = leitor.nextLine();
                            switch (verificador){
                                case "Y":
                                    println "Digite uma tecnologia"
                                    competencia =  leitor.nextLine()
                                    while(!regex.regexCompetencia.matcher(competencia).matches()){
                                        competencia = leitor.nextLine()
                                        println "Digite uma tecnologia usando apenas letras"
                                    }
                                    competencias << competencia
                                    break
                                case "N":
                                    addCompetencia = false;
                                    break
                                default:
                                    println "Digite Y para sim N para não"
                            }
                        }

                        def newCompany = new PessoaJuridica(
                        nome: nome,
                        email:  email,
                        cnpj:  registro,
                        pais:  pais,
                        estado:  estado,
                        cep:  cep,
                        descricao:  descricao,
                        competencias: competencias)

                        listaEmpresas.addEmpresa(newCompany)

                        def fileCompany = new File("arquivos/empresas.xml")
                        def writerCompany = new StringWriter()
                        def builderCompany = new MarkupBuilder(writerCompany)

                        builderCompany.mkp.xmlDeclaration(version: "1.0", encoding: "UTF-8")

                        builderCompany.empresas {
                            for (company in listaEmpresas.lista) {
                                empresa(nome: company.nome, email: company.email, cnpj: company.cnpj, pais: company.pais, estado: company.estado, cep: company.cep, descricao: company.descricao, competencias: company.competencias)
                            }
                        }

                        fileCompany.write(writerCompany.toString())
                        break
                    case "4":
                        println "Digite o seu nome"
                        nome = leitor.nextLine()
                        while(!regex.regexNome.matcher(nome).matches()){
                            nome = leitor.nextLine()
                            println "Digite um nome com pelo menos 4 caracteres"
                        }

                        println "Digite seu email"
                        email =  leitor.nextLine()
                        while(!regex.regexEmail.matcher(email).matches()){
                            email = leitor.nextLine()
                            println "Digite um email válido"
                        }

                        println "Digite o numero do seu cpf"
                        registro =  leitor.nextLine()
                        while(!regex.regexRegistro.matcher(registro).matches()){
                            registro = leitor.nextLine()
                            println "Digite um numero de 11 caracteres"
                        }

                        println "Digite a sua idade"
                        idade =  leitor.nextLine()
                        while(!regex.regexIdade.matcher(idade).matches()){
                            idade = leitor.nextLine()
                            println "Digite um nome usando apenas letras"
                        }

                        println "Digite o estado onde você mora"
                        estado =  leitor.nextLine()
                        while(!regex.regexEstado.matcher(estado).matches()){
                            estado = leitor.nextLine()
                            println "Digite uma sigla com 2 caracteres"
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

                        competencias = [];
                        while(addCompetencia){
                            println 'Deseja adicionar uma tecnologia que você domina? (Y para sim, N para salvar e sair)'
                            verificador = leitor.nextLine();
                                switch (verificador){
                                    case "Y":
                                        println "Digite uma tecnologia"
                                        competencia =  leitor.nextLine()
                                        while(!regex.regexCompetencia.matcher(competencia).matches()){
                                            competencia = leitor.nextLine()
                                            println "Digite uma tecnologia usando apenas letras"
                                        }
                                        competencias << competencia
                                        break
                                    case "N":
                                        if(competencias != []){
                                            addCompetencia = false;
                                        } else {
                                            println 'Você precisa adicionar pelo menos uma tecnologia'
                                        }
                                        break
                                    default:
                                        println "Digite Y para sim N para não"
                                }
                        }

                            def newCandidate = new PessoaFisica(
                                nome: nome,
                                email:  email,
                                cpf:  registro,
                                idade:  idade,
                                estado:  estado,
                                cep:  cep,
                                descricao:  descricao,
                                competencias: competencias)

                        listaCandidatos.addFuncionario(newCandidate)
                        listaCandidatos.printList()

                        def fileCandidate = new File('arquivos/candidatos.xml')
                        def writerCandidate = new StringWriter()
                        def builderCandidate = new MarkupBuilder(writerCandidate)

                        builderCandidate.mkp.xmlDeclaration(version: "1.0", encoding: "UTF-8")

                        builderCandidate.candidatos {
                            for (pessoa in listaCandidatos.lista) {
                                candidato(nome: pessoa.nome, email: pessoa.email, cpf: pessoa.cpf, idade: pessoa.idade, estado: pessoa.estado, cep: pessoa.cep, descricao: pessoa.descricao, competencias: pessoa.competencias)
                            }
                        }

                        fileCandidate.write(writerCandidate.toString())
                        break
                    case "5":
                        println "ok 5"
                        break
            }
            }else{
                println "Digite uma opção válida"
                verificador = leitor.nextLine()
            }
        }
    }
}