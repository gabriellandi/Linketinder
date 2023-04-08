package linketinder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import linketinder.usuarios.Empresa;

import static org.junit.Assert.assertEquals;

public class TestPessoaJuridica {
    private static Empresa testRegexPessoaJuridica;

    @BeforeAll
    public static void setUp(){;
        testRegexPessoaJuridica = new Empresa(78541230001123l, 1, "Golpes SA", "gospes.sa@batutinhas.com", "Todo dia sai um bobo e um esperto de casa, e normalmente quando eles se encontram dá negocio.",
                12345678, "74000000");
    }

    @Test
    public void testNaoAceitaPaisForaDoPadrao(){
        int resultadoEsperado = 1;

        assertEquals(testRegexPessoaJuridica.getPais(), 1);
    }

    @Test
    public void testNaoAceitaCnpjForaDoPadrao(){
        Long resultadoEsperado = 78541230001123l;

        assertEquals(testRegexPessoaJuridica.getCnpj(), resultadoEsperado);
    }
}