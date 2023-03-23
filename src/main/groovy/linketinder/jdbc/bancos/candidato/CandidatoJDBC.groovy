package linketinder.jdbc.bancos.candidato

import linketinder.regex.RegexUsuarios
import linketinder.usuarios.Candidato
import linketinder.jdbc.Utils
import linketinder.jdbc.bancos.pais.PaisJDBC

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.text.SimpleDateFormat

class CandidatoJDBC{

    Utils conectionBD = new Utils()
    RegexUsuarios regex = new RegexUsuarios()
    PaisJDBC bancoPaises = new PaisJDBC()

    void inserir(Candidato newCandidate) {
        String INSERIR = "INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        def dataSql = new java.sql.Date(newCandidate.dtNascimento.time)
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setString(1, newCandidate.nome)
            salvar.setString(2, newCandidate.sobrenome)
            salvar.setDate(3, dataSql)
            salvar.setString(4, newCandidate.email)
            salvar.setLong(5, newCandidate.cpf)
            salvar.setInt(6, newCandidate.pais)
            salvar.setInt(7, newCandidate.cep)
            salvar.setString(8, newCandidate.formacao)
            salvar.setString(9, newCandidate.descricao)
            salvar.setString(10, newCandidate.senha)
            salvar.executeUpdate()
            System.out.println("Candidato salvo com sucesso!")
        }catch(Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando candidato")
            System.exit(-42)
        }
        println "ok"
    }

    void deletarCandidato(Scanner leitor) {
        String DELETAR_CANDIDATO = "DELETE FROM candidatos WHERE id = ?"
        String DELETAR_COMP_VAGA = "DELETE FROM user_competencias WHERE id_candidato = ?"
        String DELETAR_CURTIDA_VAGA = "DELETE FROM curtida_empresa_candidato WHERE id_candidato = ?"
        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"

        println "Informe o código do candidato: "
        int id = Integer.parseInt(leitor.nextLine())

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement vagas = conn.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vagas.setInt(1, id)
            ResultSet res = vagas.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if(qtd>0) {
                //Deleta na tabela user_competencias
                PreparedStatement delCompVaga = conn.prepareStatement(DELETAR_COMP_VAGA)
                delCompVaga.setInt(1,id)
                delCompVaga.executeUpdate()
                delCompVaga.close()

                //Deleta curtida
                PreparedStatement delCurtida = conn.prepareStatement(DELETAR_CURTIDA_VAGA)
                delCurtida.setInt(1,id)
                delCurtida.executeUpdate()
                delCurtida.close()

                //Deleta na tabela vaga
                PreparedStatement delCandidato = conn.prepareStatement(DELETAR_CANDIDATO)
                delCandidato.setInt(1,id)
                delCandidato.executeUpdate()
                delCandidato.close()
                conectionBD.desconectar(conn)
                println "O produto foi deletado"
            }
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando a vaga")
            System.exit(-42)
        }
    }

    void atualizarCandidato(Scanner leitor){
        println "Informe o código do candidato: "
        int id = Integer.parseInt(leitor.nextLine())

        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement vagas = conn.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vagas.setInt(1, id)
            ResultSet res = vagas.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if (qtd > 0) {
                println "Digite o seu nome"
                String nome = leitor.nextLine()
                while(!regex.regexNome.matcher(nome).matches()){
                    nome = leitor.nextLine()
                    println "Digite um nome com pelo menos 4 caracteres"
                }

                println "Digite o seu sobrenome"
                String sobrenome = leitor.nextLine()
                while(!regex.regexNome.matcher(sobrenome).matches()){
                    sobrenome = leitor.nextLine()
                    println "Digite um sobrenome com pelo menos 4 caracteres"
                }

                println "Digite seu email"
                String email =  leitor.nextLine()
                while(!regex.regexEmail.matcher(email).matches()){
                    email = leitor.nextLine()
                    println "Digite um email válido"
                }

                println "Digite o numero do seu cpf"
                String registro =  leitor.nextLine()
                while(!regex.regexCpf.matcher(registro).matches()){
                    registro = leitor.nextLine()
                    println "Digite um numero de 11 caracteres"
                }

                println "Digite o cep do seu endereço"
                String cep =  leitor.nextLine()
                while(!regex.regexCep.matcher(cep).matches()){
                    cep = leitor.nextLine()
                    println "Digite um numero com 8 caracteres"
                }

                println "Digite uma descrição das suas habilidades"
                String descricao =  leitor.nextLine()
                while(!regex.regexDescricao.matcher(descricao).matches()){
                    descricao = leitor.nextLine()
                    println "Digite uma descrição usando apenas letras"
                }

                println "Digite sua data de nascimento no formato dd/mm/yyyy."
                String dtNascimento = leitor.nextLine()
                while(!regex.regexDtNascimento.matcher(dtNascimento).matches()){
                    println "Digite sua data de nascimento no formato dd/mm/yyyy."
                    dtNascimento = leitor.nextLine()
                }
                def formatoData = new SimpleDateFormat("dd/MM/yyyy")
                def data = formatoData.parse(dtNascimento) as Date

                println "Digite o numero correspondente ao país onde você mora."
                bancoPaises.listarPaises()
                String pais = leitor.nextLine()
                while(!regex.regexVerificador.matcher(pais).matches()){
                    println "Digite o numero válido correspondente ao país onde você mora."
                    pais = leitor.nextLine()
                }

                println "Digite qual curso superior você concluiu ou está concluindo"
                String formacao = leitor.nextLine()
                while(!regex.regexFormacao.matcher(formacao).matches()){
                    println "Digite qual curso superior você concluiu ou está concluindo"
                    formacao = leitor.nextLine()
                }

                println "Digite sua senha."
                String senha = leitor.nextLine()
                while(!regex.regexSenha.matcher(senha).matches()){
                    println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
                    senha = leitor.nextLine()
                }

                def dataSql = new java.sql.Date(data.getTime())

                String ATUALIZAR = "UPDATE candidatos SET nome=?, sobrenome=?, dt_nascimento=?, email=?, cpf=?, id_pais=?, cep=?, formacao=?, desc_candidato=?, senha=? WHERE id=?"

                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)
                upd.setString(1, nome)
                upd.setString(2, sobrenome)
                upd.setDate(3, dataSql)
                upd.setString(4, email)
                upd.setLong(5, Long.parseLong(registro))
                upd.setInt(6, Integer.parseInt(pais))
                upd.setInt(7, Integer.parseInt(cep))
                upd.setString(8, formacao)
                upd.setString(9, descricao)
                upd.setString(10, senha)
                upd.setInt(11, id)
                upd.executeUpdate()
                upd.close()
                conectionBD.desconectar(conn)
                System.out.println("Candidato atualizada com sucesso!")
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro atualizando o candidato")
            System.exit(-42)
        }
    }

    void listar() {
        String BUSCAR_TODOS = "SELECT * FROM candidatos"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement candidatos = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet res = candidatos.executeQuery()

            res.last()
            res.beforeFirst()

            while(res.next()){
                println ("ID: " + res.getInt(1))
                println ("Nome: " + res.getString(2))
                println ("Data de Nascimento: " + res.getDate(4))
                println ("Email: " + res.getString(5))
                println "--------------------"
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todos os candidatos")
            System.exit(-42)
        }
    }

    int contar() {
        String CONTAR_EMPRESAS = "SELECT COUNT(id) FROM candidatos"
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement candidatos = conn.prepareStatement(CONTAR_EMPRESAS)
            ResultSet res = candidatos.executeQuery()

            int numeroEmpresas = 0
            if (res.next()) { // avança o cursor para a primeira linha dos resultados
                numeroEmpresas = res.getInt(1)
            }

            conectionBD.desconectar(conn)

            return numeroEmpresas
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as candidatos")
            System.exit(-42)
            return -1
        }
    }

}

