package jdbc.bancos.pais

import jdbc.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class PaisJDBC {
    Utils conectionBD = new Utils()

    void listarPaises() {
        String BUSCAR_TODOS = "SELECT * FROM pais";

        try {
            Connection conn = conectionBD.conectar();
            PreparedStatement candidatos = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = candidatos.executeQuery();

            res.last();
            int qtd = res.getRow()
            res.beforeFirst();

            while(res.next()){
                println (res.getInt(1) + " - " + res.getString(2));
            }

            conectionBD.desconectar(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro buscando todos os candidatos")
            System.exit(-42);
        }
    }
}
