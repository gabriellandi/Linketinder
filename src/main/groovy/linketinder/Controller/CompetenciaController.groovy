package linketinder.Controller

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import linketinder.DAO.Access
import linketinder.DAO.CompetenciaAccess
import linketinder.Model.CompetenciasModel

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

@WebServlet(name = "CompetenciaController", urlPatterns = ["/competencias"])
class CompetenciaController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        def json = request.reader.text

        def map = new JsonSlurper().parseText(json)

        CompetenciasModel novaCompetencia = new CompetenciasModel(map.nome)
        salvaCompetencia(novaCompetencia)

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setHeader("Location", "/competencias")
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List lista = CompetenciaController.listaCompetencias()
        def json = new JsonBuilder(lista).toPrettyString()
        resp.setContentType('application/json')
        resp.writer.write(json)
    }

    static boolean salvaCompetencia(CompetenciasModel novaCompetencia){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.inserir(novaCompetencia)
    }

    static List listaCompetencias(){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.listar()
    }

    static boolean atualizaCompetencia(CompetenciasModel competenciaAtualizada, String filtraDadoParaAtualizar){
        Access bankAccess = new CompetenciaAccess()
        return bankAccess.atualizar(competenciaAtualizada, filtraDadoParaAtualizar)
    }

    static boolean deletaCompetencia(String fitraDadoASerDeletado){
        Access bankAcess = new CompetenciaAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }

    static boolean insereCompetenciaCandidato(int idCandidato, int idCompetencia){

    }
}
