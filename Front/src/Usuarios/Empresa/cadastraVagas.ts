import Empresa from "./empresa";
import Vagas from "./vagas";

const empresas: Empresa[] = JSON.parse(localStorage.getItem('empresas') || "[]");

const cnpjInput: HTMLInputElement = document.getElementById('cnpj') as HTMLInputElement;
const nomeInput: HTMLInputElement = document.getElementById('nome') as HTMLInputElement;
const descricaoInput: HTMLInputElement = document.getElementById('descricao') as HTMLInputElement;
const salarioInput: HTMLInputElement = document.getElementById('salario') as HTMLInputElement;
const salvarVagaButton: HTMLButtonElement = document.getElementById('adiciona-Vaga') as HTMLButtonElement;

let linguagensSelecionadas: string[] = [];

function armazenarLinguagens() {
  let checkboxes = document.getElementsByName('linguagem');
  for (let i = 0; i < checkboxes.length; i++) {
    // verificar se o elemento é um input do tipo "checkbox"
    if ((checkboxes[i] as HTMLInputElement).type === 'checkbox' && (checkboxes[i] as HTMLInputElement).checked) {
      linguagensSelecionadas.push((checkboxes[i] as HTMLInputElement).value);
    }
  }
  console.log(linguagensSelecionadas);
  return false;
}

salvarVagaButton.addEventListener('click', (e) => {
  armazenarLinguagens();
    e.preventDefault()
    console.log("ok")

  const cnpj: number = parseInt(cnpjInput.value);
  const nome: string = nomeInput.value;
  const descricao: string = descricaoInput.value;
  const competencias: string[] = linguagensSelecionadas;
  const salario: string = salarioInput.value;

  const empresa: Empresa | undefined = empresas.find((e) => e.cnpjEmpresa === cnpj);

  if (empresa && empresa.vagas) {
    const novaVaga: Vagas = new Vagas();

    novaVaga.competencias = competencias;


  if (nome.trim() !== "" && descricao.trim() !== "" && salario.trim() !== "") {
    novaVaga.nome = nome;
    novaVaga.salario = salario;
    novaVaga.descricao = descricao;
  } else {
      alert('Por favor, preencha todos os campos antes de enviar o formulário!');
      return;
  }
    

    empresa.vagas.push(novaVaga);

    localStorage.setItem('empresas', JSON.stringify(empresas));
    alert('Vaga salva com sucesso!');
  } else {
    alert('Empresa não encontrada!');
  }
});