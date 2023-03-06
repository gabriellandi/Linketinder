import Empresa from "./empresa";
import Vagas from "./vagas";

const empresas: Empresa[] = JSON.parse(localStorage.getItem('empresas') || "[]");

const cnpjInput: HTMLInputElement = document.getElementById('cnpj') as HTMLInputElement;
const nomeInput: HTMLInputElement = document.getElementById('nome') as HTMLInputElement;
const competenciasInput: HTMLInputElement = document.getElementById('competencias') as HTMLInputElement;
const descricaoInput: HTMLInputElement = document.getElementById('descricao') as HTMLInputElement;
const salarioInput: HTMLInputElement = document.getElementById('salario') as HTMLInputElement;
const salvarVagaButton: HTMLButtonElement = document.getElementById('adiciona-Vaga') as HTMLButtonElement;

salvarVagaButton.addEventListener('click', (e) => {
    e.preventDefault()
    console.log("ok")

  const cnpj: number = parseInt(cnpjInput.value);
  const nome: string = nomeInput.value;
  const competencias: string[] = competenciasInput.value.split(',');
  const descricao: string = descricaoInput.value;
  const salario: string = salarioInput.value;

  const empresa: Empresa | undefined = empresas.find((e) => e.cnpjEmpresa === cnpj);

  if (empresa && empresa.vagas) {
    const novaVaga: Vagas = new Vagas();
    novaVaga.nome = nome;
    novaVaga.competencias = competencias;
    novaVaga.salario = salario;
    novaVaga.descricao = descricao;

    empresa.vagas.push(novaVaga);

    localStorage.setItem('empresas', JSON.stringify(empresas));
    alert('Vaga salva com sucesso!');
  } else {
    alert('Empresa não encontrada!');
  }
});