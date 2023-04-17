package linketinder.View

import linketinder.Controller.CandidatoControler
import linketinder.Controller.PaisController
import linketinder.Model.CandidatoModel
import linketinder.Model.Regex

class CandidatoView {

    static CandidatoModel criarCandidato(Scanner leitor){
        CandidatoModel newCandidate = new CandidatoModel()

        println "Digite o seu nome"
        newCandidate.setNome(leitor.nextLine())
        while(!Regex.validaNome(newCandidate.getNome())){
            println "Digite um nome com pelo menos 4 caracteres"
            newCandidate.setNome(leitor.nextLine())
        }

        println "Digite o seu sobrenome"
        newCandidate.setSobrenome(leitor.nextLine())
        while(!Regex.validaNome(newCandidate.getSobrenome())){
            println "Digite um sobrenome com pelo menos 4 caracteres"
            newCandidate.setSobrenome(leitor.nextLine())
        }

        println "Digite seu email"
        newCandidate.setEmail(leitor.nextLine())
        while(!Regex.validaEmail(newCandidate.getEmail())){
            println "Digite um email valido"
            newCandidate.setEmail(leitor.nextLine())
        }

        println "Digite seu cpf"
        newCandidate.setCpf(Long.parseLong(leitor.nextLine()))
        while(!Regex.validaCpf(newCandidate.getCpf())){
            println "Digite um numero de 11 caracteres"
            newCandidate.setCpf(Long.parseLong(leitor.nextLine()))
        }

        println "Digite o cep do seu endereço"
        newCandidate.setCep(Integer.parseInt(leitor.nextLine()))
        while(!Regex.validaCep(newCandidate.getCep())){
            println "Digite um numero com 8 caracteres"
            newCandidate.setCep(Integer.parseInt(leitor.nextLine()))
        }

        println "Digite uma descrição das suas habilidades"
        newCandidate.setDescricao(leitor.nextLine())
        while(!Regex.validaDescricao(newCandidate.getDescricao())){
            println "Digite uma descrição usando apenas letras"
            newCandidate.setDescricao(leitor.nextLine())
        }

        println "Digite sua data de nascimento no formato dd/mm/yyyy."
        String dtNascimento = leitor.nextLine()
        while(!Regex.validaDtNascimento(dtNascimento)){
            println "Digite sua data de nascimento no formato dd/mm/yyyy."
            dtNascimento = leitor.nextLine()
        }
        newCandidate.setDtNascimento(dtNascimento)

        List paises = PaisController.listaPaises()
        PaisView.apresentarPaises()
        println "Digite o numero correspondente ao país onde você mora."
        int num = leitor.nextInt()
        leitor.nextLine()
        while(num <= 0 || num > paises.size()){
            println "Digite o numero válido correspondente ao país onde você mora."
            num = leitor.nextInt()
            leitor.nextLine()
        }
        newCandidate.setPais(paises[num - 1].id)

        println "Digite qual curso superior você concluiu ou está concluindo"
        newCandidate.setFormacao(leitor.nextLine())
        while(!Regex.validaFormacao(newCandidate.getFormacao())){
            println "Digite qual curso superior você concluiu ou está concluindo"
            newCandidate.setFormacao(leitor.nextLine())
        }

        println "Digite sua senha."
        newCandidate.setSenha(leitor.nextLine())
        while(!Regex.validaSenha(newCandidate.getSenha())){
            println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
            newCandidate.setSenha(leitor.nextLine())
        }

        return newCandidate
    }

    static void cadastrarCandidato(Scanner leitor){
        CandidatoModel newCandidate = criarCandidato(leitor)

        if(CandidatoControler.salvaUsuario(newCandidate)){
            println "Candidato salvo com sucesso"
        } else {
            println "O candidato nao foi salvo, contate nosso suporte"
        }
    }

    static void apresentarCandidatos(){
        List candidatos = CandidatoControler.listaUsuarios()

        int count = 1
        candidatos.forEach { candidato ->
            println("${count} - ${candidato.descricao}")
            println("Formação: ${candidato.formacao}")
            println("Cep: ${candidato.cep}")
            count++
        }
    }

    static void atualizaCandidato(Scanner leitor){
        println "Informe o cpf do candidato a ser atualizado: "
        Long cpf = Long.parseLong(leitor.nextLine())
        while(!Regex.validaCpf(cpf)){
            println "Digite um numero de 11 caracteres"
            cpf = Long.parseLong(leitor.nextLine())
        }

        CandidatoModel candidatoAtualizado = criarCandidato(leitor)

        if(CandidatoControler.atualizaCandidato(candidatoAtualizado, cpf.toString())){
            println "Candidato atualizado com sucesso"
        } else {
            println "O candidato nao foi atualizado, verifique se o cpf esta correto"
        }
    }

    static void deletaCandidato(Scanner leitor){
        println "Informe o cpf do candidato a ser deletado: "
        Long cpf = Long.parseLong(leitor.nextLine())
        while(!Regex.validaCpf(cpf)){
            println "Digite um numero de 11 caracteres"
            cpf = Long.parseLong(leitor.nextLine())
        }

        if(CandidatoControler.deletaCandidato(cpf.toString())){
            println "O candidato foi deletado"
        } else {
            println "Verifique o cpf digitado, o dado nao foi localizado no banco"
        }
    }

}
