# hapvida-petservice
Repositorio de uso exclusivo para processo seletivo da HapVida

Seleção Desenvolvedor HapVida
Autor: Gustavo Xavier Damasceno
@gus-skywalker (GitHub)

FRONT-END

./web

(Falta serializar Json e implementar funções de cadastro)

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

Endpoint / Método Java

TUTOR
/api/tutor
/listar (listar)
/{id} (getTutorById)
/cadastrar (cadastrar)
/apagar/{id} (deleteTutorById)

VETERINARIO
/api/vet
/listar (listar)
/{id} (getVetById)
/cadastrar (cadastrar)
/delete/{id} (deleteVetById)

ANIMAL/PET
/api/pet
/api/listar (listar)
/{id} (getPetById)
/cadastrar (cadastrar)
/atualizar/{id} (updatePet)
/apagar/{id} (deletePetById)

CONSULTA
/api/consulta
/listar (listar)
/{id} (getConsultaById)
/cadastrar (cadastrar)
/atualizar/{id} (updateConsultaById)
/apagar/{id} (deleteConsultaById)


EXEMPLOS JSON

POST
localhost:8080/api/tutor/cadastrar
{
    "nome": "maria",
    "telefone": "32332",
    "email": "blabla"
}

localhost:8080/api/vet/cadastrar
{
    "nome": "dr.dolittle",
    "telefone": "32332",
    "email": "blabla"
}

http://localhost:8080/api/consulta/cadastrar
{
"status": "REALIZADO",
"dataconsulta": "2021-12-12",
"animal_id": 1,
"veterinario_id": 1
}

http://localhost:8080/api/pet/cadastrar
{
"nome": "kekel",
"especie": "MAMIFERO",
"raca": "pastor alemao",
"datanascimento": "1983-12-12",
"tutor_id": 1
}

DELETE
localhost:8080/api/tutor/delete/1
{
    "nome": "chico",
    "telefone": "32332",
    "email": "blabla"
}

GET
http://localhost:8080/api/tutor/listar
[
  {
    "id": 1,
    "nome": "Mariana",
    "telefone": "+55 32323232",
    "email": "teste@teste.br",
    "animais": []
  },
  {
    "id": 2,
    "nome": "Marcos Richard",
    "telefone": "+ 1 232323",
    "email": "marcos@teste.br",
    "animais": []
  },
  {
    "id": 3,
    "nome": "Jamiroquai",
    "telefone": "+5 3457683",
    "email": "doutor@teste.br",
    "animais": []
  }
]
