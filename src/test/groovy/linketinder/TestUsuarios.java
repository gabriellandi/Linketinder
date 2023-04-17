package linketinder;

import linketinder.Model.Regex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import linketinder.Model.CandidatoModel;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestUsuarios {
    private static CandidatoModel testRegexUsuarios;

    @BeforeAll
    public static void setUp(){
        Date dtNascimento = new Date();
        testRegexUsuarios = new CandidatoModel("Landi", 12345678912l, "Gabriel", "gabriel.landi@batutinhas.com", "Um bom estudante",
                12345678, dtNascimento, 1, "Formação da pessoa", "senha123");
    }

    @Test
    public void testNaoAceitaNomeForaDoPadrao(){
        assert(Regex.validaNome(testRegexUsuarios.getNome()));
    }

    @Test
    public void testNaoAceitaEmailForaDoPadrao(){
        assert(Regex.validaEmail(testRegexUsuarios.getEmail()));
    }


    @Test
    public void testNaoAceitaDescricaoForaDoPadrao(){
        assert(Regex.validaDescricao(testRegexUsuarios.getDescricao()));
    }

    @Test
    public void testNaoAceitaCepForaDoPadrao(){
        assert(Regex.validaCep(testRegexUsuarios.getCep()));
    }
}
