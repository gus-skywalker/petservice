# petservice-api + javascript (front-end)
@gus-skywalker (GitHub)

FRONT-END
Acessar: localhost:8080/

EXECUTAR APLICAÇÃO (BACK-END)

    ./vet-api

    mvn spring-boot:run

SQL H2-Database
    
    localhost:8080/h2-ui

LOCAL STORAGE (H2 DB)
    
    ./db/test2

SQL COMMANDS

INSERT INTO tutor (nome, telefone, email) VALUES ('JOAO','+85 32575454','teste@teste.br’);
INSERT INTO veterinario (nome, telefone, email) VALUES ('Dra.Mariana','+85 32575454','teste@teste.br’);
INSERT INTO animal (nome, especie, raca, datanascimento, tutor_id) VALUES ('JOHNY','AVE','ARARA','2015-02-01’,1);
INSERT INTO consulta (dataconsulta, status, animal_id, veterinario_id) VALUES ('2021-06-20','AGENDADO',1, 1)


ENDPOINTS

TUTOR /api/tutor
VETERINARIO /api/vet
ANIMAL/PET /api/pet
CONSULTA /api/consulta

/listar (listar)
/{id} (getTutorById)
/cadastrar (cadastrar)
/atualizar (atualizar)
/apagar/{id} (deleteTutorById)


EXEMPLOS JSON

GET {ID}
localhost:8080/api/tutor/1

POST
localhost:8080/api/tutor/cadastrar
{
    "nome": "chico",
    "telefone": "(85)3233-3232",
    "email": "chicoscience@musica.br"
}

ATUALIZAR CONSULTA
http://localhost:8080/api/consulta/atualizar/1

{
"status": "REALIZADO",
"dataconsulta": "2021-12-12",
"animal": { "id":2 },
"veterinario": { "id": 2 }
}

APAGAR {ID}
localhost:8080//api/tutor/apagar/3

GET ALL

localhost:8080/api/consulta/listar
[
{
    "id": 1,
    "dataconsulta": "2021-06-23",
    "status": "AGENDADO",
    "animal": {
      "id": 2,
      "nome": "kekel",
      "especie": "MAMIFERO",
      "raca": "gato siames femma",
      "datanascimento": null,
      "consulta": [],
      "tutor": {
        "id": 1,
        "nome": "Marquinhos",
        "telefone": "+21 97833222",
        "email": "marquinho_d2@teste.br"
      }
    },
    "veterinario": {
      "id": 1,
      "nome": "Dr. Dolittle",
      "telefone": "+21 32453423",
      "email": "drdolittle@clinica.pet"
    }
  },
  {
    "id": 2,
    "dataconsulta": "2021-06-23",
    "status": "REALIZADO",
    "animal": {
      "id": 3,
      "nome": "juca",
      "especie": "PEIXE",
      "raca": "goldenfish",
      "datanascimento": null,
      "consulta": [],
      "tutor": {
        "id": 1,
        "nome": "Marquinhos",
        "telefone": "+21 97833222",
        "email": "marquinho_d2@teste.br"
      }
    },
    "veterinario": {
      "id": 2,
      "nome": "Dr. Dolittle 2",
      "telefone": "(85) Paracuru",
      "email": "dolittle2@filmemassademais.com"
    }
  },
  {
    "id": 3,
    "dataconsulta": "2021-06-24",
    "status": "CANCELADO",
    "animal": {
      "id": 4,
      "nome": "juca",
      "especie": "PEIXE",
      "raca": "goldenfish",
      "datanascimento": null,
      "consulta": [],
      "tutor": {
        "id": 2,
        "nome": "Renatovski",
        "telefone": "+85 454954454",
        "email": "macaroots@hotmail.com"
      }
    },
    "veterinario": {
      "id": 2,
      "nome": "Dr. Dolittle 2",
      "telefone": "(85) Paracuru",
      "email": "dolittle2@filmemassademais.com"
    }
  }
]
