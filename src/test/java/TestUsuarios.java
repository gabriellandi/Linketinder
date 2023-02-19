import org.junit.BeforeClass;
import org.junit.Test;
import usuarios.PessoaFisica;

import static org.junit.Assert.assertEquals;

public class TestUsuarios {
    private static PessoaFisica testRegexUsuarios;

    @BeforeClass
    public static void setUp(){
        String[] array = {"Java", "Groovy"};
        testRegexUsuarios = new PessoaFisica("78541236589", "26", "Gabriel Landi", "gabriel.landi@batutinhas.com", "GO",
                "Um bom trabalhador", "74000000", array);
    }

    @Test
    public void testNaoAceitaNomeForaDoPadrao(){
        String resultadoEsperado = "Gabriel Landi";

        assertEquals(testRegexUsuarios.getNome(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaEmailForaDoPadrao(){
        String resultadoEsperado = "gabriel.landi@batutinhas.com";

        assertEquals(testRegexUsuarios.getEmail(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaEstadoForaDoPadrao(){
        String resultadoEsperado = "GO";

        assertEquals(testRegexUsuarios.getEstado(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaDescricaoForaDoPadrao(){
        String resultadoEsperado = "Um bom trabalhador";

        assertEquals(testRegexUsuarios.getDescricao(), resultadoEsperado);
    }

    @Test
    public void testNaoAceitaCepForaDoPadrao(){
        String resultadoEsperado = "74000000";

        assertEquals(testRegexUsuarios.getCep(), resultadoEsperado);
    }
}
