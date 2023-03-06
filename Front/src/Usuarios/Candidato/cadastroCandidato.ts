//Adiciona Empresa

import Candidato from "./candidato";

let nomeCandidato = document.getElementById('nome') as HTMLInputElement;
let emailCandidato = document.getElementById('email') as HTMLInputElement;
let cpfCandidato = document.getElementById('cpf') as HTMLInputElement;
let competenciasCandidato  = document.getElementById('competencias') as HTMLInputElement;
let cepCandidato  = document.getElementById('cep') as HTMLInputElement;
let estadoCandidato  = document.getElementById('estado') as HTMLInputElement;
let descricaoCandidato  = document.getElementById('descricao') as HTMLInputElement;

let botaoSalvaCandidato = document.getElementById('adiciona-Candidato') as HTMLElement;


//Evento
botaoSalvaCandidato.addEventListener("click", (e) => {
    e.preventDefault();

    let newCandidato = new Candidato();

    const regexNome = /^[a-zA-Z \.]+$/;
    if (regexNome.test(nomeCandidato.value.trim())) {
        newCandidato.nome = nomeCandidato.value;
    } else {
        alert('O campo nome só pode receber letras e pontos.');
        return;
    }

    if (emailCandidato.checkValidity()) {
        newCandidato.email = emailCandidato.value;
    } else {
        alert('O campo email deve conter um email válido.');
        return;
    }

    const cpf = parseInt(cpfCandidato.value, 10);
    if (cpf.toString().length === 11) {
        newCandidato.cpfCandidato = cpf;
    } else {
        alert('O campo cpf deve conter 11 números.');
        return;
    }

    const cep = parseInt(cepCandidato.value, 10);
    if (cep.toString().length === 8) {
        newCandidato.cep = cep;
    } else {
        alert('O campo cep deve conter 8 números.');
        return;
    }

    if (estadoCandidato.value.trim() !== "" && descricaoCandidato.value.trim() !== "") {
        newCandidato.estado = estadoCandidato.value;
        newCandidato.descricao = descricaoCandidato.value;
    } else {
        alert('Por favor, preencha todos os campos antes de enviar o formulário!');
        return;
    }

    newCandidato.competencias = competenciasCandidato.value
        .split(",")
        .map(word => word.trim().toUpperCase())
        .filter(word => word !== "");

    console.log(newCandidato);

    saveCandidato(newCandidato);
});

//Função

function saveCandidato(empresa: Candidato) {
    let candidatos: Candidato[] = [];
  
    const candidatosString = localStorage.getItem("candidatos");
    if (candidatosString) {
      try {
        candidatos = JSON.parse(candidatosString);
      } catch (error) {
        console.error(`Não foi possível analisar o JSON armazenado em 'candidatos': ${error}`);
      }
    }
  
    candidatos.push(empresa);
  
    localStorage.setItem("candidatos", JSON.stringify(candidatos));
}