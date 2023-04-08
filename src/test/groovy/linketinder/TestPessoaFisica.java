package linketinder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import linketinder.usuarios.Candidato;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestPessoaFisica {

    private static Candidato testRegexPessoaFisica;

    @BeforeAll
    public static void setUp(){
        Date dtNascimento = new Date();
        testRegexPessoaFisica = new Candidato("Landi", 78541236589l, "Gabriel", "gabriel.landi@batutinhas.com", "Um bom estudante",
                12345678, dtNascimento, 1, "Formação da pessoa", "senha123");
    }

    @Test
    public void testNaoAceitaCpfErrado(){
        Long resultadoEsperado = 78541236589l;

        assertEquals(testRegexPessoaFisica.getCpf(), resultadoEsperado);
    }

//    @Test
//    public void testNaoAceitaIdadeErrada(){
//        String resultadoEsperado = "26";
//
//        assertEquals(testRegexPessoaFisica.getIdade(), resultadoEsperado);
//    }

}