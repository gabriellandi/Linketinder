package linketinder.jdbc.bancos.empresa

import linketinder.jdbc.Connect
import linketinder.usuarios.Empresa
import linketinder.jdbc.Utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaJDBC implements Connect{
    Utils conectionBD = new Utils()

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

    @Override
    void deletar(Scanner leitorEmpresa) {
        String DELETAR_EMPRESA = "DELETE FROM empresas CASCADE WHERE cnpj = ?"

        println "Informe o cnpj da empresa: "
        String cnpj = leitorEmpresa.nextLine()

        Connection conn = conectionBD.conectar()
        try {
            PreparedStatement delEmpresa = conn.prepareStatement(DELETAR_EMPRESA)
            delEmpresa.setString(1, cnpj)
            delEmpresa.executeUpdate()
            delEmpresa.close()
            conectionBD.desconectar(conn)
            println "A empresa foi deletada"
        } catch (Exception e){
            e.printStackTrace()
            System.err.println("Erro deletando a empresa")
            System.exit(-42)
        }
    }

    @Override
    void atualizar(Scanner leitorEmpresa) {
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
                Empresa novaEmpresa = Empresa.cadastrarEmpresa(leitorEmpresa)

                String ATUALIZAR = "UPDATE empresas SET nome_empresa=?, cnpj=?, email_coorp=?, desc_empresa=?, id_pais=?, cep=?, senha=? WHERE id=?"
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

                upd.setString(1, novaEmpresa.getNome())
                upd.setLong(2, novaEmpresa.getCnpj())
                upd.setString(3, novaEmpresa.getEmail())
                upd.setString(4, novaEmpresa.getDescricao())
                upd.setInt(5, novaEmpresa.getPais())
                upd.setInt(6, novaEmpresa.getCep())
                upd.setString(7, novaEmpresa.getSenha())
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

    @Override
    List listar() {
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

            List listEmpresa = []

            while(res.next()){
                def empresa = [:]
                empresa.id = res.getInt(1)
                empresa.nome = res.getString(2)
                empresa.cnpj = res.getLong(3)
                empresa.email = res.getString(4)
                empresa.descricao = res.getString(5)
                empresa.pais = res.getInt(6)
                empresa.cep = res.getInt(7)
                empresa.senha = res.getString(8)
                listEmpresa << empresa
            }

            return listEmpresa

            conectionBD.desconectar(conn)
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro buscando todas as empresas")
            System.exit(-42)
        }
    }

}
