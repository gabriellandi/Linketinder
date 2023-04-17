package linketinder.DAO.bancos


import linketinder.Model.EmpresaModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaDAO implements IConnect{
    Utils conectionBD = new Utils()

    @Override
    boolean inserir(Object objeto) {
        EmpresaModel newEmpresa = (EmpresaModel) objeto
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
            return true
        }catch(Exception e){
            e.printStackTrace()
            System.exit(-42)
            return false
        }
    }

    @Override
    boolean deletar(String fitraDadoParaDeletar) {
        String DELETAR_EMPRESA = "DELETE FROM empresas CASCADE WHERE cnpj = ?"
        Connection conn = conectionBD.conectar()
        try {
            PreparedStatement delEmpresa = conn.prepareStatement(DELETAR_EMPRESA)
            delEmpresa.setString(1, fitraDadoParaDeletar)
            int rowsDeleted = delEmpresa.executeUpdate();

            delEmpresa.close()
            conectionBD.desconectar(conn)

            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace()
            System.exit(-42)
        }
    }

    @Override
    boolean atualizar(Object objeto, String filtraDadoParaAtualizar) {
        EmpresaModel novaEmpresa = (EmpresaModel) objeto

        try {
            Connection conn = conectionBD.conectar()
            String ATUALIZAR = "UPDATE empresas SET nome_empresa=?, cnpj=?, email_coorp=?, desc_empresa=?, id_pais=?, cep=?, senha=? WHERE cnpj=?"
            PreparedStatement upd = conn.prepareStatement(ATUALIZAR)

            upd.setString(1, novaEmpresa.getNome())
            upd.setLong(2, novaEmpresa.getCnpj())
            upd.setString(3, novaEmpresa.getEmail())
            upd.setString(4, novaEmpresa.getDescricao())
            upd.setInt(5, novaEmpresa.getPais())
            upd.setInt(6, novaEmpresa.getCep())
            upd.setString(7, novaEmpresa.getSenha())
            upd.setString(8 , filtraDadoParaAtualizar)
            int rowsUpdated = upd.executeUpdate()

            upd.close()
            conectionBD.desconectar(conn)

            return rowsUpdated > 0
        } catch (Exception e) {
            e.printStackTrace()
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
