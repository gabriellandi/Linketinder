import listas.ArrayEmpresas;
import listas.ArrayFuncionarios;
import org.junit.Test;
import usuarios.PessoaFisica;
import usuarios.PessoaJuridica;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TestArrays {
    @Test
    public void adicionaFuncionariosArray() {
        ArrayFuncionarios testArray = new ArrayFuncionarios();
        String[] array = {"Java", "Groovy"};
        PessoaFisica funcionarioFake;
        funcionarioFake = new PessoaFisica("78541236589", "26", "Gabriel Landi", "gabriel.landi@batutinhas.com", "GO",
                "Um bom trabalhador", "74000000", array);
        testArray.addFuncionario(funcionarioFake);

        ArrayFuncionarios resultadoEsperado = new ArrayFuncionarios();
        resultadoEsperado.addFuncionario(new PessoaFisica("78541236589", "26", "Gabriel Landi", "gabriel.landi@batutinhas.com", "GO",
                "Um bom trabalhador", "74000000", array));

        assertTrue(resultadoEsperado.equals(testArray));
    }

    @Test
    public void adicionaEmpresaArray() {
        ArrayEmpresas testArray = new ArrayEmpresas();
        String[] array = {"Java", "Groovy"};
        PessoaJuridica empresaFake;
        empresaFake = new PessoaJuridica("78541230001", "Brasil", "Golpes SA", "gospes.sa@batutinhas.com", "GO",
                "Todo dia sai um bobo e um esperto de casa, e normalmente quando eles se encontram dá negocio.", "74000000", array);
        testArray.addEmpresa(empresaFake);

        ArrayEmpresas resultadoEsperado = new ArrayEmpresas();
        resultadoEsperado.addEmpresa(new PessoaJuridica("78541230001", "Brasil", "Golpes SA", "gospes.sa@batutinhas.com", "GO",
                "Todo dia sai um bobo e um esperto de casa, e normalmente quando eles se encontram dá negocio.", "74000000", array));

        System.out.println(empresaFake + " " + resultadoEsperado);
        assertTrue(resultadoEsperado.equals(testArray));
    }


}
