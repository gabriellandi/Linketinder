//Adiciona Empresa

import Empresa from "./empresa";

let nomeEmpresa = document.getElementById('nome') as HTMLInputElement;
let emailEmpresa = document.getElementById('email') as HTMLInputElement;
let cnpjEmpresa = document.getElementById('cnpj') as HTMLInputElement;
let competenciasEmpresa  = document.getElementById('competencias') as HTMLInputElement;
let cepEmpresa  = document.getElementById('cep') as HTMLInputElement;
let estadoEmpresa  = document.getElementById('estado') as HTMLInputElement;
let descricaoEmpresa  = document.getElementById('descricao') as HTMLInputElement;

let botaoSalvaEmpresa = document.getElementById('adiciona-Empresa') as HTMLElement;


//Evento
botaoSalvaEmpresa.addEventListener("click", (e) => {

    let newEmpresa = new Empresa();

    const regexNome = /^[a-zA-Z \.]+$/;
    if (regexNome.test(nomeEmpresa.value.trim())) {
        newEmpresa.nome = nomeEmpresa.value;
    } else {
        alert('O campo nome só pode receber letras e pontos.');
        return;
    }

    if (emailEmpresa.checkValidity()) {
        newEmpresa.email = emailEmpresa.value;
    } else {
        alert('O campo email deve conter um email válido.');
        return;
    }

    const cnpj = parseInt(cnpjEmpresa.value, 10);
    if (cnpj.toString().length === 14) {
        newEmpresa.cnpjEmpresa = cnpj;
    } else {
        alert('O campo cnpj deve conter 14 números.');
        return;
    }

    const cep = parseInt(cepEmpresa.value, 10);
    if (cep.toString().length === 8) {
        newEmpresa.cep = cep;
    } else {
        alert('O campo cep deve conter 8 números.');
        return;
    }

    if (estadoEmpresa.value.trim() !== "" && descricaoEmpresa.value.trim() !== "") {
        newEmpresa.estado = estadoEmpresa.value;
        newEmpresa.descricao = descricaoEmpresa.value;
    } else {
        alert('Por favor, preencha todos os campos antes de enviar o formulário!');
        return;
    }

    newEmpresa.competencias = competenciasEmpresa.value
        .split(",")
        .map(word => word.trim().toUpperCase())
        .filter(word => word !== "");

    newEmpresa.vagas = [];

    saveEmpresa(newEmpresa);

    alert("Empresa cadastrada com sucesso.")
});

//Função

function saveEmpresa(empresa: Empresa) {
    let empresas: Empresa[] = [];
  
    const empresasString = localStorage.getItem("empresas");
    if (empresasString) {
      try {
        empresas = JSON.parse(empresasString);
      } catch (error) {
        console.error(`Não foi possível analisar o JSON armazenado em 'empresas': ${error}`);
      }
    }
  
    empresas.push(empresa);
  
    localStorage.setItem("empresas", JSON.stringify(empresas));
}