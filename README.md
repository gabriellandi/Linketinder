Seja Bem Vindo!!!

Está aplicação será desenvolvida ao longo do programa Acelera ZG!!!

No momento ela está na versão 1.0.0. E nesta versão ela foi desenvolvida usando Groovy somente.

E se trata de uma aplicação de terminal,cujo objetivo é coa funcionalidade de Match do Tinder, com o campo individual de competências visto no Linkedin e na relação estabelecida entre empresa recrutadora e candidato.

Neste momento, existem 2 versões da aplicação:

BackEnd: Está rodando apenas no terminal, que foi desenvolvida com Groovy. A aplicação possui uma persistencia simples em XML, onde é possível adicionar novos candidados e novas empresas, as entradas de terminal estão todas verificadas com Regex. Os testes da aplicação foram desenvolvidos em JUnit 4.

Resumindo a lógica do Back:

    - Tenho uma classe Usuarios, que foi criada para ser herdada pelo Candidato e pela Empresa, pois as mesmas possuem caracteristicas de usuário.
    
    - Os objetos gerados pelo user do aplicação são adicionados em um Array de classe que foi desenvolvido para receber objetos do tipo correspondente, por exempo existe uma classe ArrayEmpresas para receber objetos do tipo Empresa. Essas classes foram criadas para ser mais simples e claro, adicionar uma empresa e até mesmo apresentá-la no terminal.
    
    - Por fim a cada empresa ou candidato cadastrado eles são salvos em um arquivo XML.

FrontEnd: É uma aplicação desenvolvida majoritariamente em TypeScript, onde os arquivos TS foram compilados usando o WebPack, na pasta de Front, é possível rodar a aplicaçao a partir da pasta "dist", e se necessário analisar a logica utilizada no TypeScript os arquivos estão na pasta src.

Lógica da aplicação Front:

    - Assim como no back, foi utilizada uma classe usuarios para ser herdada pelo candidato e pela empresa, uma classe foi definida para as vagas.
    
    - A partir dos objetos acima, criamos duas paginas para cadastro de empresas e candidatos, estás paginas vão salvar esses usuários ao LocalStorage para serem usados nas telas de perfil.
    
    - A tela de cadastro é apresentada, porém só é possivel salvar uma vaga se existir alguma empresa cadastrada, como não possuimos uma pagina de login ainda, uma vaga só é adicionada se o cnpj da empresa digitado na tela de cadastro, for corresponde à alguma empresa cadastrada no LocalStorage.
    
    - Para os candidatos por enquanto é apresentado apenas as vagas disponíveis, sem o cnpj da empresa, para preservar o anonimato.
    
    - Para a empresa é apresentado os candidatos, o foco da apresentação é a descrição que o candidato fez, as competencias dele o cep e o Estado. Como diferencial foi desenvolvido um gráfico usando o canvas, onde mostra a quantidade de usuários X competencias.
    
Banco de dados:

 - Abaixo apresento o Diagrama Entidade Relacionamento

![alt text](BancoDeDados/Linketinder_DER.png)

	A representação gráfica acima foi desenvolvida usando o BRModelo. Para desenvolver o banco de dados, utilizei como base o site [dbdiagram](https://dbdiagram.io), nele desenvolvi o esqueleto do meu banco de dados, exportei o arquivo SQL pelo site realizei algumas edições no arquivo antes de rodá-lo no banco.
	Dentro da pasta banco de dados se encontra o codigo sql, que gerou o banco e alguns dados de exemplo que foram inseridos.
