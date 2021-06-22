let CRUDApp = new function() {

     this.lista_pacientes = [
        {
            nome_animal: "Romeo",
            especie: "Mamifero",
            raca: "Felino SRD",
            nasc_animal: "03/04/2019",
            nome_tutor: "Gustavo",
            telefone_tutor: "21 972857756",
            email_tutor: "gustavo.xdam@gmail.com",
            veterinarios: "opcoes",
            status: "cadastrado"
        },
        {
            nome_animal: "Julieta",
            especie: "Mamifero",
            raca: "Felino SRD",
            nasc_animal: "12/12/2020",
            nome_tutor: "Gustavo",
            telefone_tutor: "21 972857756",
            email_tutor: "gustavo.xdam@gmail.com",
            veterinarios: "opcoes",
            status: "cadastrado"
        }
    ];

    this.el = document.getElementById('lista');
    
    this.contador = function (data) {
        let el = document.getElementById('counter'); // Variável Elemento de Escopo Local
        let name = 'Pacientes'; // Variável dentro da função a ser renomeada caso haja contatos

        if (data) {
            if (data > 1) {
                name = 'pacientes cadastrados';
            }
            el.innerHTML = data + ' ' + name;
        } else {
            el.innerHTML = 'Sem pacientes';
        }
    };

    this.criar = function() {
        let el1 = document.getElementById('add-nome_animal');
        let el2 = document.getElementById('add-especie');
        let el3 = document.getElementById('add-raca');
        let el4 = document.getElementById('add-nasc_animal');
        let el5 = document.getElementById('add-nome_tutor');
        let el6 = document.getElementById('add-telefone_tutor');
        let el7 = document.getElementById('add-email_tutor');
        let el8 = document.getElementById('add-veterinarios');
            let option8 = el8.options[el8.selectedIndex];
        let el9 = document.getElementById('status');
            let option9 = el9.options[el9.selectedIndex];
        var select = document.getElementById('la');
        console(option8);
        console(option9);

        // Pega as variáveis do campo de input do form
        // Melhor separado ou fazê-lo diretamente?
        let nome_animal = el1.value;
        let especie = el2.value;
        let raca = el3.value;
        let nasc_animal = el4.value;
        let nome_tutor = el5.value;
        let telefone_tutor = el6.value;
        let email_tutor = el7.value;
        let veterinarios = option8.value;
        let status = option9.value;

        if (nome_animal && nome_tutor && telefone_tutor) {

            this.lista_pacientes.push(    
                {    
                nome_animal: nome_animal,
                especie: especie,
                raca: raca,
                nasc_animal: nasc_animal,
                nome_tutor: nome_tutor,
                telefone_tutor: telefone_tutor,
                email_tutor: email_tutor,
                veterinarios: veterinarios,
                status: status
                }
            );

            // Mostra a nova lista
            //alert("Paciente cadastrado com sucesso");
            console.log('Inserçao com sucesso'); // Confirma no console
            //this.listaTudo();
        } else {
            console.log('Erro');
            //alert("Campos obrigatórios devem ser preenchidos");
        }
    };

    this.listaTudo = function () {
        let data = ''; //Variável de Escopo Local
        
        if (this.lista_pacientes.length > 0) { // Recupera Variável de Contatos Global e faz um loop nos Objetos da Matriz
            for (o = 0; o < this.lista_pacientes.length; o++) {
                data += '<tr class="paciente">';
                data += '<td class="paciente_data">' + this.lista_pacientes[o].data + '</td>';
                data += '<td class="paciente_status">' + this.lista_pacientes[o].status + '</td>';
                data += '<td class="paciente_nome_animal">' + this.lista_pacientes[o].nome_animal + '</td>';
                data += '<td class="paciente_nome_tutor">' + this.lista_pacientes[o].nome_tutor + '</td>';
                data += '<td class="paciente_telefone_tutor">' + this.lista_pacientes[o].telefone_tutor + '</td>';
                data += '<td class="paciente_email_tutor">' + this.lista_pacientes[o].email_tutor + '</td>';
                data += '<td class="paciente_veterinario">' + this.lista_pacientes[o].veterinarios + '</td>';

                data += '<td><button onclick="CRUDApp.atualiza(' + o + ')">Edit</button></td>'; // Insere botão Edição na própria lista de contatos
                data += '<td><button onclick="CRUDApp.delete(' + o + ')">Delete</button></td>'; //Insere botão Delete na própria lista de contatos
                data += '</tr>';
            }
        }
        this.contador(this.lista_pacientes); // Executa o contador
        return this.el.innerHTML = data; // Retorna o input dos dados do laço For() no Elemento Global 'el'
    };

    this.atualiza = function (item) {
        let el1 = document.getElementById('add-nome_animal');
        let el2 = document.getElementById('add-especie');
        let el3 = document.getElementById('add-raca');
        let el4 = document.getElementById('add-nasc_animal');
        let el5 = document.getElementById('add-nome_tutor');
        let el6 = document.getElementById('add-telefone_tutor');
        let el7 = document.getElementById('add-email_tutor');
        let el8 = document.getElementById('add-veterinarios');
        let el9 = document.getElementById('status');

        // Preenche os campos com os valores
        el1.value = this.lista_pacientes[item].nome_animal;
        el2.value = this.lista_pacientes[item].especie;
        el3.value = this.lista_pacientes[item].raca;
        el4.value = this.lista_pacientes[item].nasc_animal;
        el5.value = this.lista_pacientes[item].nome_tutor;
        el6.value = this.lista_pacientes[item].telefone_tutor;
        el7.value = this.lista_pacientes[item].email_tutor;
        el8.value = this.lista_pacientes[item].veterinarios;
        el9.value = this.lista_pacientes[item].status;

        console.log('Item selecionado:' + item);

        document.getElementByClassName('form-pacientes').onsubmit = function () {
            let atualiza_paciente = {
                nome_animal: el1.value,
                especie: el2.value,
                raca: el3.value,
                nasc_animal: el4.value,
                nome_tutor: el5.value,
                telefone_tutor: el6.value,
                email_tutor: el7.value,
                veterinarios: el8.options[el8.selectedIndex],
                status: el9.options[el9.selectedIndex]
            };

            console.log(atualiza_paciente); // Retirar depois

            // Validação dos itens obrigatórios
            let novo_nome = el1.value;
            let novo_tutor = el5.value;
            let novo_telefone = el6.value;

            if (novo_nome && novo_tutor && novo_telefone != null) {
                self.lista_pacientes[item] = atualiza_paciente;
                self.listaTudo();
                alert('Atualizaco com sucesso');
            } else {
                alert('Campos obrigatórios devem ser preenchidos');
            }
        }
    };

    this.delete = function (item) {
        // Deleta a linha escolhida
        this.lista_pacientes.splice(item, 1);
        // Mostra a nova lista
        this.listaTudo();
      };   
}

CRUDApp.listaTudo(); //Lista tudo no início