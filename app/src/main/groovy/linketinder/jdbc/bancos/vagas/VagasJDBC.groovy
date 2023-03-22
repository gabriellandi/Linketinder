package linketinder.jdbc.bancos.vagas

import linketinder.regex.RegexUsuarios
import linketinder.vagas.Vagas
import linketinder.jdbc.Utils
import linketinder.jdbc.bancos.empresa.EmpresaJDBC

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagasJDBC {
    Utils conectionBD = new Utils()
    RegexUsuarios regex = new RegexUsuarios();
    EmpresaJDBC bancoEmpresa = new EmpresaJDBC();

    void inserir(Vagas v){
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
            System.out.println("Vaga salva com sucesso!")
        }catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando vaga")
            System.exit(-42)
        }
    }

    void deletarVaga(Scanner leitorVaga) {
        String DELETAR_VAGA = "DELETE FROM vagas WHERE id = ?"
        String DELETAR_COMP_VAGA = "DELETE FROM user_competencias WHERE id_vaga = ?"
        String DELETAR_CURTIDA_VAGA = "DELETE FROM curtida_empresa_candidato WHERE id_vaga = ?"
        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"

        println "Informe o código do produto: "
        int id = Integer.parseInt(leitorVaga.nextLine())

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
                PreparedStatement delCompVaga = conn.prepareStatement(DELETAR_COMP_VAGA)
                delCompVaga.setInt(1,id)
                delCompVaga.executeUpdate()
                delCompVaga.close()

                //Deleta curtida
                delCurtida.close()
                delCurtida.setInt(1,idVaga)
                delCurtida.executeUpdate()
                delCurtida.close()

                //Deleta na tabela vaga
                PreparedStatement delVaga = conn.prepareStatement(DELETAR_VAGA)
                delVaga.setInt(1,id)
                delVaga.executeUpdate()
                delVaga.close()
                conectionBD.desconectar(conn)
                println "A vaga foi deletada"
            }
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando a vaga")
            System.exit(-42)
        }
    }

    void atualizarVaga(Scanner leitorVaga){
            println "Informe o código da vaga: "
            int id = Integer.parseInt(leitorVaga.nextLine())

            String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"

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
                    println "Digite um nome para vaga"
                    String nome = leitorVaga.nextLine()
                    while (!regex.regexNome.matcher(nome).matches()) {
                        println "Digite um nome com pelo menos 4 caracteres"
                        nome = leitorVaga.nextLine()
                    }

                    println "Digite o numero correspondente ao ID da empresa que está criando a vaga"
                    bancoEmpresa.listar()
                    int contador = bancoEmpresa.contar()
                    String idEmpresa = leitorVaga.nextLine()
                    while (Integer.parseInt(idEmpresa) > contador) {
                        println "Digite o numero válido correspondente a empresa onde você mora."
                        id = leitorVaga.nextLine()
                    }

                    println "Digite uma descrição da vaga"
                    String descricao = leitorVaga.nextLine()
                    while (!regex.regexDescricao.matcher(descricao).matches()) {
                        println "Digite uma descrição usando apenas letras"
                        descricao = leitorVaga.nextLine()
                    }

                    println "Digite o estado onde ela fica localizada"
                    String estado = leitorVaga.nextLine()
                    while (!regex.regexEstado.matcher(estado).matches()) {
                        println "Digite uma sigla com 2 caracteres"
                        estado = leitorVaga.nextLine()
                    }

                    println "Digite a cidade onde ela fica localizada"
                    String cidade = leitorVaga.nextLine()
                    while (!regex.regexNome.matcher(cidade).matches()) {
                        println "Digite um nome com pelo menos 4 caracteres"
                        cidade = leitorVaga.nextLine()
                    }
                    def dataSql = new java.sql.Date(System.currentTimeMillis());

                    String ATUALIZAR = "UPDATE vagas SET nome=?, id_empresa=?, desc_vaga=?, data_criacao=?, estado=?, cidade=? WHERE id=?"
                    PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

                    upd.setString(1, nome)
                    upd.setInt(2, Integer.parseInt(idEmpresa))
                    upd.setString(3, descricao)
                    upd.setDate(4, dataSql)
                    upd.setString(5, estado)
                    upd.setString(6, cidade)
                    upd.setInt(7, id)
                    upd.executeUpdate()
                    upd.close()
                    conectionBD.desconectar(conn)
                    System.out.println("Vaga atualizada com sucesso!")
                }

                conectionBD.desconectar(conn)
            } catch (Exception e) {
                e.printStackTrace()
                System.err.println("Erro atualizando as vagas")
                System.exit(-42)
            }
    }

    void listar() {
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

            while(res.next()){
                println ("ID: " + res.getInt(1))
                println ("Nome da Vaga: " + res.getString(3))
                println ("Descricao da vaga: " + res.getString(5))
                println "--------------------"
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as vagas")
            System.exit(-42)
        }
    }

    int contar() {
        String CONTAR_EMPRESAS = "SELECT COUNT(id) FROM vagas"
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement vagas = conn.prepareStatement(CONTAR_EMPRESAS)
            ResultSet res = vagas.executeQuery()

            int numeroEmpresas = 0
            if (res.next()) { // avança o cursor para a primeira linha dos resultados
                numeroEmpresas = res.getInt(1)
            }

            conectionBD.desconectar(conn)

            return numeroEmpresas
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as vagas")
            System.exit(-42)
            return -1
        }
    }
}
