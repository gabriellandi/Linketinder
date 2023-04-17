package linketinder.DAO.bancos


import linketinder.Model.PaisModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class PaisDAO implements IConnect{
    Utils conectionBD = new Utils()
    Connection conn = conectionBD.conectar();

    @Override
    List listar() {
        String BUSCAR_TODOS = "SELECT * FROM pais";

        try {
            PreparedStatement candidatos = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = candidatos.executeQuery();

            res.last();
            int qtd = res.getRow()
            res.beforeFirst();

            List listPais = []

            while(res.next()){
                def pais = [:]
                pais.id = res.getInt(1)
                pais.nome = res.getString(2)
                listPais << pais
            }

            return listPais

            conectionBD.desconectar(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro buscando todos os paises")
            System.exit(-42);
        }
    }

    @Override
    boolean inserir(Object objeto){
        PaisModel pais = objeto as PaisModel
        String INSERIR = "INSERT INTO pais (nome_pais) VALUES (?)"
        try{
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setString(1, pais.nome)
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
        String DELETAR_PAIS = "DELETE FROM pais CASCADE WHERE nome_pais = ?"

        try {
            PreparedStatement delPais = conn.prepareStatement(DELETAR_PAIS)
            delPais.setString(1,fitraDadoParaDeletar)
            int rowsDeleted = delPais.executeUpdate();

            delPais.close()
            conectionBD.desconectar(conn)

            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean atualizar(Object objeto, String filtraDadoParaAtualizar) {
        String filtro = filtraDadoParaAtualizar
        PaisModel pais = (PaisModel) objeto
        try {
            String ATUALIZAR = "UPDATE pais SET nome_pais=? WHERE nome_pais=?"
            PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

            upd.setString(1, pais.nome)
            upd.setString(2, filtro)
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
}
