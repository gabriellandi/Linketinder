package linketinder.Controller

import groovy.json.JsonSlurper
import linketinder.DAO.Access
import linketinder.DAO.CandidatoAccess
import linketinder.Model.CandidatoModel

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

@WebServlet(name = "CandidatoController", urlPatterns = ["/candidatos"])
class CandidatoControler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        def json = request.reader.text

        def map = new JsonSlurper().parseText(json)

        CandidatoModel novoCandidato = new CandidatoModel(map.sobrenome, map.cpf, map.nome, map.email, map.descricao, map.cep, new SimpleDateFormat("yyyy-MM-dd").parse(map.dtNascimento), map.pais, map.formacao, map.senha)
        salvaUsuario(novoCandidato)

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setHeader("Location", "/candidatos")
    }

    static boolean salvaUsuario(CandidatoModel candidato){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.inserir(candidato)
    }

    static List listaUsuarios(){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.listar()
    }

    static boolean atualizaCandidato(CandidatoModel newCandidade, String filtraDadoParaAtualizar){
        Access bankAccess = new CandidatoAccess()
        return bankAccess.atualizar(newCandidade, filtraDadoParaAtualizar)
    }

    static boolean deletaCandidato(String fitraDadoASerDeletado){
        Access bankAcess = new CandidatoAccess()
        return bankAcess.deletar(fitraDadoASerDeletado)
    }

}
