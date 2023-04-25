package linketinder.Controller

import groovy.json.JsonSlurper
import linketinder.DAO.Access
import linketinder.DAO.EmpresaAccess
import linketinder.Model.CandidatoModel
import linketinder.Model.EmpresaModel

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EmpresaController", urlPatterns = ["/empresas"])
class EmpresaController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        def json = request.reader.text

        def map = new JsonSlurper().parseText(json)

        EmpresaModel novaEmpresa = new EmpresaModel(map.cnpj, map.pais, map.nome, map.email, map.descricao, map.cep, map.senha)
        salvarEmpresa(novaEmpresa)

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setHeader("Location", "/empresas")
    }

    static boolean salvarEmpresa(EmpresaModel empresa){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.inserir(empresa)
    }

    static List listaEmpresas(){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.listar()
    }

    static boolean atualizaEmpresa(EmpresaModel newEmpresa, String filtraDadoParaAtualizar){
        Access bankAccess = new EmpresaAccess()
        return bankAccess.atualizar(newEmpresa, filtraDadoParaAtualizar)
    }

    static boolean deletaEmpresa(String fitraDadoASerDeletado){
        Access bankAcess = new EmpresaAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }
}
