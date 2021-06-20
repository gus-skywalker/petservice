function CRUDFetch() {

    this.listar = async function () {
        let url = '/api/pet/listar';
        let resposta = await fetch(url);
        let jsonData = await resposta.json();
        return jsonData;
    }

    this.buscarPorId = async function (id) {
        let url = "/api/pet/"
        let response = await fetch(url + "/" + id, {
            method: "GET",
            headers: {
                "Content-Typ": "application/json"
            }
        });

        let todos = await response.json();
        return mensagem;
    };

    this.cadastrar = async function (form) {
        let url = "/api/pet/cadastrar";

        let myHeader = {
            method: 'POST',
            body: new FormData(form)
        }
        try {
            let r = await fetch(url, myHeader);
            let response = await r.text();
            console.log(response);
            return response;
        } catch (e) { }
    };

    this.editar = async function (artigo, id) {

        let url = "/api/pet/atualizar";

        const response = await fetch(url, {
            method: "PUT",
            body: JSON.stringify(todo),
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (!response.ok) {
            throw new Error("There was an error processing the request");
        }
    };

    this.apagar = async function (id) {

        let url = 'api/pet/deletar';

        return fetch(url + '/' + id, {
            method: "DELETE"
        }).then(response => {
            if (!response.ok) {
                throw new Error("There was an error processing the request");
            }
        });
    };



}