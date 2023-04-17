package linketinder.View


import linketinder.Controller.EmpresaController
import linketinder.Controller.PaisController
import linketinder.Model.Regex
import linketinder.Model.EmpresaModel

class EmpresaView {
    static EmpresaModel criarEmpresa(Scanner leitor){
        EmpresaModel empresa = new EmpresaModel()
        println "Digite o nome da sua empresa"
        empresa.setNome(leitor.nextLine())
        while(!Regex.validaNome(empresa.getNome())){
            println "Digite um nome com pelo menos 4 caracteres"
            empresa.setNome(leitor.nextLine())
        }

        println "Digite o email coorporativo"
        empresa.setEmail(leitor.nextLine())
        while(!Regex.validaEmail(empresa.getEmail())){
            println "Digite um email válido"
            empresa.setEmail(leitor.nextLine())
        }

        println "Digite o numero do seu cnpj"
        empresa.setCnpj(Long.parseLong(leitor.nextLine()))
        while(!Regex.validaCnpj(empresa.getCnpj())){
            println "Digite um numero de 14 caracteres"
            empresa.setCnpj(Long.parseLong(leitor.nextLine()))
        }

        List paises = PaisController.listaPaises()
        PaisView.apresentarPaises()
        println "Digite o numero correspondente ao país onde sua empresa está localizada."
        int num = leitor.nextInt()
        leitor.nextLine()
        while(num <= 0 || num > paises.size()){
            println "Digite o numero válido correspondente ao país onde sua empresa está localizada."
            num = leitor.nextInt()
            leitor.nextLine()
        }
        empresa.setPais(paises[num - 1].id)

        println "Digite o cep do endereço da sua empresa"
        empresa.setCep(Integer.parseInt(leitor.nextLine()))
        while(!Regex.validaCep(empresa.getCep())){
            println "Digite um numero com 8 caracteres"
            empresa.setCep(Integer.parseInt(leitor.nextLine()))
        }

        println "Digite uma descrição da sua empresa"
        empresa.setDescricao(leitor.nextLine())
        while(!Regex.validaDescricao(empresa.getDescricao())){
            println "Digite uma descrição da sua empresa"
            empresa.setDescricao(leitor.nextLine())
        }

        println "Digite sua senha."
        empresa.setSenha(leitor.nextLine())
        while(!Regex.validaSenha(empresa.getSenha())){
            println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
            empresa.setSenha(leitor.nextLine())
        }

        return empresa
    }

    static void cadastraEmpresa(Scanner leitor){
        EmpresaModel novaEmpresa = criarEmpresa(leitor)
        if(EmpresaController.salvarEmpresa(novaEmpresa)){
            println "Empresa cadastrada com sucesso"
        }else{
            println "A empresa nao foi salva, contate nosso suporte"
        }
    }

    static void apresentarEmpresa(){
        List listaEmpresa = EmpresaController.listaEmpresas()
        int count = 1
        listaEmpresa.forEach { empresa ->
            println("${count} - ${empresa.nome}")
            println("cnpj: ${empresa.cnpj}")
            println("Cep: ${empresa.cep}")
            count++
        }
    }

    static void atualizaEmpresa(Scanner leitor){
        println "Informe o cnpj do candidato a ser atualizado: "
        Long cnpj = Long.parseLong(leitor.nextLine())
        while(!Regex.validaCnpj(cnpj)){
            println "Digite um numero de 14 caracteres"
            cnpj = Long.parseLong(leitor.nextLine())
        }

        EmpresaModel empresaAtualizado = criarEmpresa(leitor)

        if(EmpresaController.atualizaEmpresa(empresaAtualizado, cnpj.toString())){
            println "Empresa atualizada com sucesso"
        } else {
            println "A empresa nao foi atualizado, verifique se o cnpj esta correto"
        }
    }

    static void deletaEmpresa(Scanner leitor){
        println "Informe o cnpj da empresa a ser deletado: "
        Long cnpj = Long.parseLong(leitor.nextLine())
        while(!Regex.validaCnpj(cnpj)){
            println "Digite um numero de 14 caracteres"
            cnpj = Long.parseLong(leitor.nextLine())
        }

        if(EmpresaController.deletaEmpresa(cnpj.toString())){
            println "Empresa deletada com sucesso"
        } else {
            println "A empresa nao foi deletada, verifique se o cnpj esta correto"
        }
    }
}
