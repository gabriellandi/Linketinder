package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import classes.usuarios.PessoaFisica;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestUsuarios {
    private static PessoaFisica testRegexUsuarios;

    @BeforeAll
    public static void setUp(){
        Date dtNascimento = new Date();
        testRegexUsuarios = new PessoaFisica("Landi", 12345678912l, "Gabriel", "gabriel.landi@batutinhas.com", "Um bom estudante",
                12345678, dtNascimento, 1, "Formação da pessoa", "senha123");
    }

    @Test
    public void testNaoAceitaNomeForaDoPadrao(){
        String resultadoEsperado = "Gabriel";

        assertEquals(testRegexUsuarios.getNome(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaEmailForaDoPadrao(){
        String resultadoEsperado = "gabriel.landi@batutinhas.com";

        assertEquals(testRegexUsuarios.getEmail(), resultadoEsperado);
    }


    @Test
    public void testNaoAceitaDescricaoForaDoPadrao(){
        String resultadoEsperado = "Um bom estudante";

        assertEquals(testRegexUsuarios.getDescricao(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaCepForaDoPadrao(){
        int resultadoEsperado = 12345678;

        assertEquals(testRegexUsuarios.getCep(), resultadoEsperado);
    }
}
