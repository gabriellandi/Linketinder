package linketinder;

import linketinder.Model.Regex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import linketinder.Model.CandidatoModel;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestPessoaFisica {

    private static CandidatoModel testRegexPessoaFisica;

    @BeforeAll
    public static void setUp(){
        Date dtNascimento = new Date();
        testRegexPessoaFisica = new CandidatoModel("Landi", 78541236589l, "Gabriel", "gabriel.landi@batutinhas.com", "Um bom estudante",
                12345678, dtNascimento, 1, "Formação da pessoa", "senha123");
    }

    @Test
    public void testNaoAceitaCpfErrado(){
        assert(Regex.validaCpf(testRegexPessoaFisica.getCpf()));
    }


}
