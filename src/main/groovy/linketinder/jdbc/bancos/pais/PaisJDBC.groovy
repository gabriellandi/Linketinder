package linketinder.jdbc.bancos.pais

import linketinder.jdbc.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class PaisJDBC {
    Utils conectionBD = new Utils()

    List consultaPaises() {
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
}
