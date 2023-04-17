package linketinder.DAO.bancos


import linketinder.Model.VagaModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagasDAO implements IConnect{
    Utils conectionBD = new Utils()

    @Override
    boolean inserir(Object objeto){
        VagaModel v = (VagaModel) objeto
        String INSERIR = "INSERT INTO vagas (id_empresa, nome, desc_vaga, data_criacao, estado, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        def dataSql = new java.sql.Date(v.dataCriacao.time)
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)

            salvar.setInt(1, v.idEmpresa)
            salvar.setString(2, v.nomeVaga)
            salvar.setString(3, v.descVaga)
            salvar.setDate(4, dataSql)
            salvar.setString(5, v.estado)
            salvar.setString(6, v.cidade)
            salvar.executeUpdate()
            salvar.close()
            conectionBD.desconectar(conn)
            return true
        }catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
            return false
        }
    }

    @Override
    boolean deletar(String fitraDadoParaDeletar) {
        String DELETAR_VAGA = "DELETE FROM vagas CASCADE WHERE id = ?"
        int id = Integer.parseInt(fitraDadoParaDeletar)
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement delVaga = conn.prepareStatement(DELETAR_VAGA)
            delVaga.setInt(1,id)
            int rowsDeleted = delVaga.executeUpdate();

            delVaga.close()
            conectionBD.desconectar(conn)

            return rowsDeleted > 0
        } catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean atualizar(Object objeto, String filtraDadoParaAtualizar){
        VagaModel vagaAtualizada = (VagaModel) objeto
        int id = Integer.parseInt(filtraDadoParaAtualizar)
        def dataSql = new java.sql.Date(vagaAtualizada.dataCriacao.time)
        try {
            Connection conn = conectionBD.conectar()
            String ATUALIZAR = "UPDATE vagas SET nome=?, id_empresa=?, desc_vaga=?, data_criacao=?, estado=?, cidade=? WHERE id=?"
            PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

            upd.setString(1, vagaAtualizada.getNomeVaga())
            upd.setInt(2, vagaAtualizada.getIdEmpresa())
            upd.setString(3, vagaAtualizada.getDescVaga())
            upd.setDate(4, dataSql)
            upd.setString(5, vagaAtualizada.getEstado())
            upd.setString(6, vagaAtualizada.getCidade())
            upd.setInt(7, id)
            int rowsUpdated = upd.executeUpdate()

            upd.close()
            conectionBD.desconectar(conn)

            if (rowsUpdated > 0) {
                return true
            } else {
                return false
            }
            } catch (Exception e) {
                e.printStackTrace()
                System.exit(-42)
            }
    }

    @Override
    List listar() {
        String BUSCAR_TODOS = "SELECT * FROM vagas"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement vagas = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet res = vagas.executeQuery()

            res.last()
            res.beforeFirst()

            List listVagas = []

            while(res.next()){
                def vaga = [:]
                vaga.id = res.getInt(1)
                vaga.idEmpresa = res.getInt(2)
                vaga.nome = res.getString(3)
                vaga.descricao = res.getString(4)
                vaga.dataCriacao = res.getDate(5)
                vaga.estado = res.getString(6)
                vaga.cidade = res.getString(7)
                listVagas << vaga
            }

            return listVagas
            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as vagas")
            System.exit(-42)
        }
    }
}
