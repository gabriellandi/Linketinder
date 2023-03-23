package linketinder.jdbc.bancos.competencias

import linketinder.competencias.Competencias
import linketinder.jdbc.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciasJDBC {
    Utils conectionBD = new Utils()

    void inserir(Competencias c){
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

    void listarCompetencias() {
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

            while(res.next()){
                println ("ID: " + res.getInt(1))
                println ("Nome: " + res.getString(2))
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todos as competencias")
            System.exit(-42)
        }
    }

    int contar() {
        String CONTAR_EMPRESAS = "SELECT COUNT(id) FROM competencias"
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement empresas = conn.prepareStatement(CONTAR_EMPRESAS)
            ResultSet res = empresas.executeQuery()

            int numeroEmpresas = 0
            if (res.next()) { // avança o cursor para a primeira linha dos resultados
                numeroEmpresas = res.getInt(1)
            }

            conectionBD.desconectar(conn)

            return numeroEmpresas
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as competencias")
            System.exit(-42)
            return -1
        }
    }

    void atualizarCompetecia(Scanner leitorCompetencia){
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

    void deletarCompetencia(Scanner leitorCompetencia) {
        String DELETAR_COMP = "DELETE FROM competencias WHERE id = ?"
        String DELETAR_COMP_REF = "DELETE FROM user_competencias WHERE id_competencia = ?"
        String BUSCAR_POR_ID = "SELECT * FROM competencias WHERE id=?"

        println "Informe o id da competencia: "
        int id = Integer.parseInt(leitorCompetencia.nextLine())

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

            if(qtd>0) {
                //Deleta na tabela user_competencias
                PreparedStatement delCompVaga = conn.prepareStatement(DELETAR_COMP_REF)
                delCompVaga.setInt(1,id)
                delCompVaga.executeUpdate()
                delCompVaga.close()

                //Deleta na tabela vaga
                PreparedStatement delCompetencia = conn.prepareStatement(DELETAR_COMP)
                delCompetencia.setInt(1,id)
                delCompetencia.executeUpdate()
                delCompetencia.close()
                conectionBD.desconectar(conn)
                println "A competencia foi deletado"
            }
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
