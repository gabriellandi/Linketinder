package linketinder.jdbc.bancos.pais

import linketinder.jdbc.Connect
import linketinder.jdbc.Utils
import linketinder.location.Pais

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class PaisJDBC implements Connect{
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

    void inserir(Pais pais){
        String INSERIR = "INSERT INTO pais (nome) VALUES (?)"
        try{
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setString(1, pais.nome)
            salvar.executeUpdate()
            System.out.println("Pais salvo com sucesso!")
        }catch(Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando pais")
            System.exit(-42)
        }
    }

    @Override
    void deletar(Scanner leitor) {
        String DELETAR_PAIS = "DELETE FROM pais CASCADE WHERE nome_pais = ?"

        println "Informe o nome do pais: "
        String nome = leitor.nextLine()

        try {
            PreparedStatement delCandidato = conn.prepareStatement(DELETAR_PAIS)
            delCandidato.setString(1,nome)
            delCandidato.executeUpdate()
            delCandidato.close()
            conectionBD.desconectar(conn)
            println "O pais foi deletado"
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando o pais")
            System.exit(-42)
        }
    }

    @Override
    void atualizar(Scanner leitor) {
        println "Informe o nome do pais: "
        String nome = leitor.nextLine()

        String BUSCAR_POR_ID = "SELECT * FROM pais WHERE nome_pais=?"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement paises = conn.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            paises.setString(1, nome)
            ResultSet res = paises.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if (qtd > 0) {
                Pais novoPais = Pais.criarPais(leitor)

                String ATUALIZAR = "UPDATE pais SET nome_pais=? WHERE nome_pais=?"
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

                upd.setString(1, novoPais.nome)
                upd.setString(2, nome)
                upd.executeUpdate()
                upd.close()
                conectionBD.desconectar(conn)
                System.out.println("Competencia atualizada com sucesso!")
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro atualizando a competencia")
            System.exit(-42)
        }
    }
}
