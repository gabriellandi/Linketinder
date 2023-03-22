package linketinder.jdbc.bancos.empresa

import linketinder.regex.RegexUsuarios
import linketinder.usuarios.Empresa
import linketinder.jdbc.Utils
import linketinder.jdbc.bancos.pais.PaisJDBC

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaJDBC {
    Utils conectionBD = new Utils()
    RegexUsuarios regex = new RegexUsuarios();
    PaisJDBC bancoPaises = new PaisJDBC();

    void inserir(Empresa newEmpresa) {
        String INSERIR = "INSERT INTO empresas (cnpj, email_coorp, desc_empresa, id_pais, cep, senha, nome_empresa) VALUES (?, ?, ?, ?, ?, ?, ?)"
        try{
            Connection conn = conectionBD.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setLong(1, newEmpresa.cnpj)
            salvar.setString(2, newEmpresa.email)
            salvar.setString(3, newEmpresa.descricao)
            salvar.setInt(4, newEmpresa.pais)
            salvar.setInt(5, newEmpresa.cep)
            salvar.setString(6, newEmpresa.senha)
            salvar.setString(7, newEmpresa.nome)
            salvar.executeUpdate()
            System.out.println("Empresa salva com sucesso!")
        }catch(Exception e){
            e.printStackTrace()
            System.err.println("Erro salvando empresa")
            System.exit(-42)
        }
        println "ok"
    }

    List consultaIdVaga(int id) {
        String ID_VAGA = "SELECT id FROM vagas WHERE id_empresa = ?"
        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement vagas = conn.prepareStatement(ID_VAGA)
            vagas.setInt(1, id)
            ResultSet res = vagas.executeQuery()

            List idVagas = []
            while (res.next()) { // avança o cursor para a primeira linha dos resultados
                idVagas.add(res.getInt(1))
            }

            conectionBD.desconectar(conn)

            return idVagas
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as empresas")
            System.exit(-42)
            return -1
        }
    }

    void deletarEmpresa(Scanner leitorEmpresa) {
        String DELETAR_EMPRESA = "DELETE FROM empresas WHERE id = ?"
        String DELETAR_VAGA = "DELETE FROM vagas WHERE id_empresa = ?"
        String DELETAR_COMP_VAGA = "DELETE FROM user_competencias WHERE id_vaga = ?"
        String DELETAR_CURTIDA_VAGA = "DELETE FROM curtida_empresa_candidato WHERE id_vaga = ?"
        String BUSCAR_POR_ID = "SELECT * FROM empresas WHERE id=?"

        println "Informe o código da empresa: "
        int id = Integer.parseInt(leitorEmpresa.nextLine())

        List idsParaExcluir = consultaIdVaga(id);

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement empresas = conn.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            empresas.setInt(1, id)
            ResultSet res = empresas.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if(qtd>0) {
                idsParaExcluir.each{ idVaga ->
                    //Deleta na tabela user_competencias
                    PreparedStatement delCompVaga = conn.prepareStatement(DELETAR_COMP_VAGA)
                    delCompVaga.setInt(1,idVaga)
                    delCompVaga.executeUpdate()
                    delCompVaga.close()

                    //Deleta curtida
                    PreparedStatement delCurtida = conn.prepareStatement(DELETAR_CURTIDA_VAGA)
                    delCurtida.setInt(1,idVaga)
                    delCurtida.executeUpdate()
                    delCurtida.close()

                    //Deleta na tabela vaga
                    PreparedStatement delVaga = conn.prepareStatement(DELETAR_VAGA)
                    delVaga.setInt(1,idVaga)
                    delVaga.executeUpdate()
                    delVaga.close()
                }

                //Deleta na tabela empresa
                PreparedStatement delEmpresa = conn.prepareStatement(DELETAR_EMPRESA)
                delEmpresa.setInt(1, id)
                delEmpresa.executeUpdate()
                delEmpresa.close()
                conectionBD.desconectar(conn)
                println "A empresa foi deletada"
            }
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando a empresa")
            System.exit(-42)
        }
    }

    void atualizarEmpresa(Scanner leitorEmpresa) {
        println "Informe o código da empresa: "
        int id = Integer.parseInt(leitorEmpresa.nextLine())

        String BUSCAR_POR_ID = "SELECT * FROM empresas WHERE id=?"

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
                println "Digite o nome da sua empresa"
                String nome = leitorEmpresa.nextLine()
                while(!regex.regexNome.matcher(nome).matches()){
                    println "Digite um nome com pelo menos 4 caracteres"
                    nome = leitorEmpresa.nextLine()
                }

                println "Digite o email coorporativo"
                String email =  leitorEmpresa.nextLine()
                while(!regex.regexEmail.matcher(email).matches()){
                    println "Digite um email válido"
                    email = leitorEmpresa.nextLine()
                }

                println "Digite o numero do seu cnpj"
                String registro =  leitorEmpresa.nextLine()
                while(!regex.regexCnpj.matcher(registro).matches()){
                    println "Digite um numero de 14 caracteres"
                    registro = leitorEmpresa.nextLine()
                }

                println "Digite o numero correspondente ao país onde você mora."
                bancoPaises.listarPaises()
                String pais = leitorEmpresa.nextLine()
                while(!regex.regexVerificador.matcher(pais).matches()){
                    println "Digite o numero válido correspondente ao país onde você mora."
                    pais = leitorEmpresa.nextLine()
                }

                println "Digite o cep do endereço da empresa"
                String cep =  leitorEmpresa.nextLine()
                while(!regex.regexCep.matcher(cep).matches()){
                    println "Digite um numero com 8 caracteres"
                    cep = leitorEmpresa.nextLine()
                }

                println "Digite uma descrição da sua empresa"
                String descricao =  leitorEmpresa.nextLine()
                while(!regex.regexDescricao.matcher(descricao).matches()){
                    println "Digite uma descrição usando apenas letras"
                    descricao = leitorEmpresa.nextLine()
                }

                println "Digite sua senha."
                String senha = leitorEmpresa.nextLine()
                while(!regex.regexSenha.matcher(senha).matches()){
                    println "Digite uma senha com mais de 4 caracteres, com letras minusculas e numeros."
                    senha = leitorEmpresa.nextLine()
                }

                String ATUALIZAR = "UPDATE empresas SET nome_empresa=?, cnpj=?, email_coorp=?, desc_empresa=?, id_pais=?, cep=?, senha=? WHERE id=?"
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

                upd.setString(1, nome)
                upd.setLong(2, Long.parseLong(registro))
                upd.setString(3, email)
                upd.setString(4, descricao)
                upd.setInt(5, Integer.parseInt(pais))
                upd.setInt(6, Integer.parseInt(cep))
                upd.setString(7, senha)
                upd.setInt(8 , id)
                upd.executeUpdate()
                upd.close()
                conectionBD.desconectar(conn)
                System.out.println("Empresa atualizada com sucesso!")
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro atualizando a empresa")
            System.exit(-42)
        }
    }

    void listar() {
        String BUSCAR_TODOS = "SELECT * FROM empresas"

        try {
            Connection conn = conectionBD.conectar()
            PreparedStatement empresas = conn.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet res = empresas.executeQuery()

            res.last()
            res.beforeFirst()

            while(res.next()){
                println ("ID: " + res.getInt(1))
                println ("Nome Fantasia: " + res.getString(8))
                println ("Email: " + res.getString(3))
                println "--------------------"
            }

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as empresas")
            System.exit(-42)
        }
    }

    int contar() {
        String CONTAR_EMPRESAS = "SELECT COUNT(id) FROM empresas"
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
            System.err.println("Erro buscando todas as empresas")
            System.exit(-42)
            return -1
        }
    }
}
