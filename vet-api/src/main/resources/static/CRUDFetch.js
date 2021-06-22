function CRUDFetch(url) {

    this.listar = async function () {
        
        let resposta = await fetch(url + '/listar');
        let jsonData = await resposta.json();
        return jsonData;
    }

    this.buscarPorId = async function (id) {
        let response = await fetch(url + "/" + id, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        let todos = await response.json();
        return mensagem;
    };

    this.cadastrar = async function (bean) {
        
        let myHeader = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(bean)
        }
        try {
            let r = await fetch(url + '/cadastrar', myHeader);
            let response = r.text();
            return response;
        } catch (e) { console.log(e); }
    };

    this.atualizar = async function (bean, id) {

        let response = await fetch(url + '/atualizar/' + id, {
            method: "PUT",
            body: JSON.stringify(bean),
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (!response.ok) {
            throw new Error("Ocorreu um erro ao efetuar a requisição");
        }
    };

    this.apagar = async function (id) {

        return fetch(url + '/apagar/' + id, {
            method: "DELETE"
        }).then(response => {
            if (!response.ok) {
                throw new Error("Ocorreu um erro ao efetuar a requisição");
            }
        });
    };
}