package linketinder.DAO.bancos

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Utils {
    static Connection conectar() {
        Properties props = new Properties();
        props.setProperty("user", "landi");
        props.setProperty("password", "acelera");
        props.setProperty("ssl", "false");
        String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver")
            return DriverManager.getConnection(URL_SERVIDOR, props);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ClassNotFoundException) {
                System.err.println("Verifique o drive de conexao")
            } else {
                System.err.println("Verifique se o servidor está ativo.")
            }
            System.exit(-42);
            return null
        }
    }

    static Connection conectar(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        // ...
        try {
            return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            // ...
        }
    }

    static void desconectar(Connection conn) {
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
