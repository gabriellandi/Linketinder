package linketinder.jdbc.bancos.candidato

import linketinder.jdbc.Connect
import linketinder.usuarios.Candidato
import linketinder.jdbc.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoJDBC implements Connect{

    Utils conectionBD = new Utils()
    Connection conn = conectionBD.conectar()

    void inserir(Candidato newCandidate) {
        String INSERIR = "INSERT INTO candidatos (nome, sobrenome, dt_nascimento, email, cpf, id_pais, cep, formacao, desc_candidato, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        def dataSql = new java.sql.Date(newCandidate.dtNascimento.time)
        try{
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
    }

    @Override
    void deletar(Scanner leitor) {
        String DELETAR_CANDIDATO = "DELETE FROM candidatos CASCADE WHERE cpf = ?"

        println "Informe o cpf do candidato: "
        String cpf = leitor.nextLine()

        try {
            PreparedStatement delCandidato = conn.prepareStatement(DELETAR_CANDIDATO)
            delCandidato.setString(1,cpf)
            delCandidato.executeUpdate()
            delCandidato.close()
            conectionBD.desconectar(conn)
            println "O candidato foi deletado"
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando o candidado")
            System.exit(-42)
        }
    }

    @Override
    void atualizar(Scanner leitor){
        println "Informe o código do candidato: "
        int id = Integer.parseInt(leitor.nextLine())

        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"

        try {
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

                Candidato newCandidate = Candidato.criarCandidato(leitor)

                String ATUALIZAR = "UPDATE candidatos SET nome=?, sobrenome=?, dt_nascimento=?, email=?, cpf=?, id_pais=?, cep=?, formacao=?, desc_candidato=?, senha=? WHERE id=?"

                def dataSql = new java.sql.Date(newCandidate.dtNascimento.time)

                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)
                upd.setString(1, newCandidate.nome)
                upd.setString(2, newCandidate.sobrenome)
                upd.setDate(3, dataSql)
                upd.setString(4, newCandidate.email)
                upd.setLong(5, newCandidate.cpf)
                upd.setInt(6, newCandidate.pais)
                upd.setInt(7, newCandidate.cep)
                upd.setString(8, newCandidate.formacao)
                upd.setString(9, newCandidate.descricao)
                upd.setString(10, newCandidate.senha)
                upd.setInt(11, id)
                upd.executeUpdate()
                upd.close()
                conectionBD.desconectar(conn)
                System.out.println("Candidato atualizado com sucesso!")
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro atualizando o candidato")
            System.exit(-42)
        }
    }

    @Override
    List listar() {
        String BUSCAR_TODOS = "SELECT * FROM candidatos"

        try {
            PreparedStatement candidatos = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet res = candidatos.executeQuery()

            res.last()
            res.beforeFirst()

            List listCandidate = [];
            while(res.next()){
                def candidato = [:]
                candidato.id = res.getInt(1)
                candidato.nome = res.getString(2)
                candidato.sobrenome = res.getString(3)
                candidato.dtNascimento = res.getDate(4)
                candidato.email = res.getString(5)
                candidato.cpf = res.getLong(6)
                candidato.pais = res.getInt(7)
                candidato.cep = res.getInt(8)
                candidato.formacao = res.getString(9)
                candidato.descricao = res.getString(10)
                candidato.senha = res.getString(11)
                listCandidate << candidato
            }

            return listCandidate
            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todos os candidatos")
            System.exit(-42)
        }
    }

}

