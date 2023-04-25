package linketinder.DAO.bancos


import linketinder.Model.CandidatoModel
import linketinder.View.CandidatoView

import javax.servlet.http.HttpServlet
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoDAO extends HttpServlet implements IConnect{

    Utils conectionBD = new Utils()
    Connection conn = conectionBD.conectar()

    @Override
    boolean inserir(Object objeto) {
        CandidatoModel newCandidate = (CandidatoModel) objeto
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
            int rowsInserted = salvar.executeUpdate();

            salvar.close()
            conectionBD.desconectar(conn)

            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean deletar(String fitraDadoParaDeletar) {
        String DELETAR_CANDIDATO = "DELETE FROM candidatos CASCADE WHERE cpf = ?"

        try {
            PreparedStatement delCandidato = conn.prepareStatement(DELETAR_CANDIDATO)
            delCandidato.setString(1, fitraDadoParaDeletar)
            int rowsDeleted = delCandidato.executeUpdate();

            delCandidato.close()
            conectionBD.desconectar(conn)

            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean atualizar(Object objeto, String filtraDadoParaAtualizar){
        CandidatoModel newCandidate = (CandidatoModel) objeto
        try {
            String ATUALIZAR = "UPDATE candidatos SET nome=?, sobrenome=?, dt_nascimento=?, email=?, cpf=?, id_pais=?, cep=?, formacao=?, desc_candidato=?, senha=? WHERE cpf=?"

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
            upd.setString(11, filtraDadoParaAtualizar)
            int rowsUpdated = upd.executeUpdate()

            upd.close()
            conectionBD.desconectar(conn)

            return rowsUpdated > 0
        } catch (Exception e) {
            e.printStackTrace()
            System.exit(-42)
            return false
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

