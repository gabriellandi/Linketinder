import org.junit.BeforeClass;
import org.junit.Test;
import usuarios.PessoaFisica;

import static org.junit.Assert.assertEquals;

public class TestPessoaFisica {

    private static PessoaFisica testRegexPessoaFisica;

    @BeforeClass
    public static void setUp(){
        String[] array = {"Java", "Groovy"};
        testRegexPessoaFisica = new PessoaFisica("78541236589", "26", "Gabriel Landi", "gabriel.landi@batutinhas.com", "GO",
                "Um bom trabalhador", "74000000", array);
    }

    @Test
    public void testNaoAceitaCpfErrado(){
        String resultadoEsperado = "78541236589";

        assertEquals(testRegexPessoaFisica.getCpf(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaIdadeErrada(){
        String resultadoEsperado = "26";

        assertEquals(testRegexPessoaFisica.getIdade(), resultadoEsperado);
    }

}
