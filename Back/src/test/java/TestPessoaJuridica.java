import org.junit.BeforeClass;
import org.junit.Test;
import usuarios.PessoaJuridica;

import static org.junit.Assert.assertEquals;

public class TestPessoaJuridica {
    private static PessoaJuridica testRegexPessoaJuridica;

    @BeforeClass
    public static void setUp(){
        String[] array = {"Java", "Groovy"};
        testRegexPessoaJuridica = new PessoaJuridica("78541230001", "Brasil", "Golpes SA", "gospes.sa@batutinhas.com", "GO",
                "Todo dia sai um bobo e um esperto de casa, e normalmente quando eles se encontram dá negocio.", "74000000", array);
    }

    @Test
    public void testNaoAceitaPaisForaDoPadrao(){
        String resultadoEsperado = "Brasil";

        assertEquals(testRegexPessoaJuridica.getPais(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaCnpjForaDoPadrao(){
        String resultadoEsperado = "78541230001";

        assertEquals(testRegexPessoaJuridica.getCnpj(), resultadoEsperado);
    }
}
