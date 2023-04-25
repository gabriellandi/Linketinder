package linketinder.Controller

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import linketinder.DAO.Access
import linketinder.DAO.VagasAccess
import linketinder.Model.CandidatoModel
import linketinder.Model.VagaModel

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

@WebServlet(name = "VagaController", urlPatterns = ["/vagas"])
class VagaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        def json = request.reader.text

        def map = new JsonSlurper().parseText(json)

        VagaModel novaVaga = new VagaModel(map.nome, map.empresa, map.descricao, map.estado, map.cidade)
        salvaVaga(novaVaga)

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setHeader("Location", "/vagas")
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List lista = listaVagas()
        def json = new JsonBuilder(lista).toPrettyString()
        resp.setContentType('application/json')
        resp.writer.write(json)
    }

    static boolean salvaVaga(VagaModel novaVaga){
        Access bankAccess = new VagasAccess()
        return bankAccess.inserir(novaVaga)
    }

    static List listaVagas(){
        Access bankAccess = new VagasAccess()
        return bankAccess.listar()
    }

    static boolean atualizaVaga(VagaModel vagaAtualizada, String filtraDadoParaAtualizar){
        Access bankAccess = new VagasAccess()
        return bankAccess.atualizar(vagaAtualizada, filtraDadoParaAtualizar)
    }

    static boolean deletaVaga(String fitraDadoASerDeletado){
        Access bankAcess = new VagasAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }
}
