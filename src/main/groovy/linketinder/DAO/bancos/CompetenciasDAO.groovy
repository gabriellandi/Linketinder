package linketinder.DAO.bancos

import linketinder.Model.CompetenciasModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciasDAO implements IConnect {
    Utils conectionBD = new Utils()

    @Override
    boolean inserir(Object objeto){
        CompetenciasModel c = (CompetenciasModel) objeto
        String INSERIR = "INSERT INTO competencias (nome_competencia) VALUES (?)"
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setString(1, c.nome)

            int rowsUpdated = salvar.executeUpdate()

            salvar.close()
            conectionBD.desconectar(conn)

            return rowsUpdated > 0
        }catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    List listar() {
        String BUSCAR_TODOS = "SELECT * FROM competencias"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement competencias = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = competencias.executeQuery()

            res.last()
            res.beforeFirst()

            List listCompetencias = []

            while(res.next()){
                def comp = [:]
                comp.id = res.getInt(1)
                comp.nome = res.getString(2)
                listCompetencias << comp
            }

            return listCompetencias
            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todos as competencias")
            System.exit(-42)
        }
    }

    @Override
    boolean atualizar(Object objeto, String filtraDadoParaAtualizar){
        CompetenciasModel competenciaAtualizada = (CompetenciasModel) objeto
        try {
            Connection conn = conectionBD.conectar()
            String ATUALIZAR = "UPDATE competencias SET UPPER(nome_competencia)=? WHERE (nome_competencia)=?"
            PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

            upd.setString(1, competenciaAtualizada.nome)
            upd.setString(2, filtraDadoParaAtualizar)
            int rowsUpdated = upd.executeUpdate()

            upd.close()
            conectionBD.desconectar(conn)

            return rowsUpdated > 0
        } catch (Exception e) {
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean deletar(String fitraDadoParaDeletar) {
        String DELETAR_COMP = "DELETE FROM competencias CASCADE WHERE id = ?"
        int id = Integer.parseInt(fitraDadoParaDeletar)
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement delCompetencia = conn.prepareStatement(DELETAR_COMP)
            delCompetencia.setInt(1,id)
            int rowsUpdated = delCompetencia.executeUpdate()

            delCompetencia.close()
            conectionBD.desconectar(conn)

            return rowsUpdated > 0
        } catch (Exception e){
            e.printStackTrace()
            System.exit(-42)

        }
    }

    List verificaCompetencias(){
        String BUSCAR_TODOS = "SELECT * FROM competencias"

        List listaCompetencias = []

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement competencias = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = competencias.executeQuery()

            res.last()
            res.beforeFirst()

            while(res.next()){
                listaCompetencias.add(res.getString(2).toUpperCase())
            }

            return listaCompetencias
            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todos os competencias")
            System.exit(-42)
        }
    }

    void inserirCompetenciasCandidatos(int idCandidato, int idCompetencia){
        String INSERIR = "INSERT INTO user_competencias (id_candidato, id_competencia) VALUES (?,?)"
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)

            salvar.setInt(1, idCandidato)
            salvar.setInt(2, idCompetencia)
            salvar.executeUpdate()
            System.out.println("Competencia salva no usuario com sucesso!")
        }catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando competencia no usuario")
            System.exit(-42)
        }
    }

    void inserirCompetenciasVagas(int idVaga, int idCompetencia){
        String INSERIR = "INSERT INTO user_competencias (id_vaga, id_competencia) VALUES (?,?)"
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)

            salvar.setInt(1, idVaga)
            salvar.setInt(2, idCompetencia)
            salvar.executeUpdate()
            System.out.println("Competencia salva na vaga com sucesso!")
        }catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando competencia na vaga")
            System.exit(-42)
        }
    }
}
