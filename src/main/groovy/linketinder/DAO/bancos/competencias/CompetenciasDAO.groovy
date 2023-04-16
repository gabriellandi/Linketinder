package linketinder.DAO.bancos.competencias

import linketinder.competencias.Competencias
import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciasDAO implements IConnect {
    Utils conectionBD = new Utils()

    @Override
    void inserir(Object objeto){
        Competencias c = (Competencias) objeto
        String INSERIR = "INSERT INTO (nome) competencias VALUES (?)"
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)

            salvar.setString(1, c.nome)
            salvar.executeUpdate()
            System.out.println("Competencia salva com sucesso!")
        }catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando competencia")
            System.exit(-42)
        }
    }

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

    void atualizar(Scanner leitorCompetencia){
        println "Informe o código da competencia: "
        int id = Integer.parseInt(leitorCompetencia.nextLine())

        String BUSCAR_POR_ID = "SELECT * FROM competencias WHERE id=?"

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
                println "Digite um nome para a competencia"
                String nome = leitorCompetencia.nextLine()
                while(verificaCompetencias().contains(nome.toUpperCase())){
                    println "Esta competencia já existe no banco de dados"
                    nome = leitorCompetencia.nextLine()
                }

                String ATUALIZAR = "UPDATE competencias SET nome_competencia=? WHERE id=?"
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

                upd.setString(1, nome)
                upd.setInt(2, id)
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

    void deletar(Scanner leitorCompetencia) {
        String DELETAR_COMP = "DELETE FROM competencias CASCADE WHERE id = ?"

        println "Informe o id da competencia: "
        int id = Integer.parseInt(leitorCompetencia.nextLine())

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement delCompetencia = conn.prepareStatement(DELETAR_COMP)
            delCompetencia.setInt(1,id)
            delCompetencia.executeUpdate()
            delCompetencia.close()
            conectionBD.desconectar(conn)
            println "A competencia foi deletado"
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando a competencia")
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
