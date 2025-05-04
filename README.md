## Funcionalidades

✅ Adicionar um pessoa (post/pessoas)

✅ Alterar um pessoa (put/pessoas/{id})

✅ Remover pessoa (delete/pessoas/{id})

✅ Adicionar um tarefa (post/tarefas)

✅ Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})

✅ Finalizar a tarefa (put/tarefas/finalizar/{id})

✅ Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)

✅ Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)

✅ Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)

✅ Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
      
# Testando a API com Postman

Este guia fornece instruções sobre como testar essa API utilizando o Postman. Siga os passos detalhados para cada funcionalidade da API.

## Conceitos Gerais

* **Método HTTP:** Selecione o método HTTP apropriado (GET, POST, PUT, DELETE) no Postman, dependendo da operação que você deseja realizar.
* **URL:** Insira a URL do endpoint da API que você quer testar. Por exemplo: `http://localhost:8080/pessoas`.
* **Body:** Para requisições que enviam dados (POST, PUT), você precisará incluir um corpo (body) na requisição, geralmente no formato JSON.
* **Path Variables:** Para endpoints que contêm variáveis de caminho como `{id}`, substitua `{id}` pelo valor específico do ID do recurso que você deseja acessar.

## Instruções Detalhadas por Endpoint
### Adicionar Departamento (POST /departamentos/)

* **Método:** `POST`
* **URL:** `http://localhost:8080/departamentos/`
* **Body (JSON):**

    ```json
    {
        "nome": "Nome do Novo Departamento"
    }
    ```
### Adicionar Pessoa (POST /pessoas)

* **Método:** `POST`
* **URL:** `http://localhost:8080/pessoas`
* **Body (JSON):**

    ```json
    {
        "nome": "Nome da Pessoa",
        "idDepartamento":  {
            "id":  1
        }
    }
    ```

    **Importante:** Certifique-se de que o `idDepartamento` fornecido exista no banco de dados.

### Atualizar Pessoa (PUT /pessoas/{id})

* **Método:** `PUT`
* **URL:** `http://localhost:8080/pessoas/1` (Substitua `1` pelo ID da pessoa que você deseja atualizar)
* **Body (JSON):**

    ```json
    {
        "id": 1, 
        "nome": "Novo Nome",
        "idDepartamento": {
            "id": 2
        }
    }
    ```

### Deletar Pessoa (DELETE /pessoas/{id})

* **Método:** `DELETE`
* **URL:** `http://localhost:8080/pessoas/1` (Substitua `1` pelo ID da pessoa a ser deletada)

### Listar Pessoas (GET /pessoas/)

* **Método:** `GET`
* **URL:** `http://localhost:8080/pessoas/`

### Buscar Pessoas por Nome (GET /pessoas/gastos?nome=...)

* **Método:** `GET`
* **URL:** `http://localhost:8080/pessoas/gastos?nome=NomePessoa` (Substitua `NomePessoa` pelo nome da pessoa que você quer buscar)

### Adicionar Tarefa (POST /tarefas/)

* **Método:** `POST`
* **URL:** `http://localhost:8080/tarefas/`
* **Body (JSON):**

    ```json
    {
       "titulo": "Nova Tarefa",
        "descricao": "Descrição da nova tarefa",
        "prazo": "2025-05-15",
        "duracao": 5,
        "finalizado": false,
        "idDepartamento": {
        "id": 1
        },
        "idPessoa": {
        "id": 1
        }
    }
    ```

### Alocar Pessoa na Tarefa (PUT /tarefas/alocar/{id})

* **Método:** `PUT`
* **URL:** `http://localhost:8080/tarefas/alocar/1` (Substitua `1` pelo ID da tarefa à qual você quer alocar uma pessoa)
* **Body (JSON):**

    ```json
    {
        "id": 1,
        "idDepartamento":{
            "id": 1
        }
    }
    ```

### Finalizar Tarefa (PUT /tarefas/finalizar/{id})

* **Método:** `PUT`
* **URL:** `http://localhost:8080/tarefas/finalizar/1` (Substitua `1` pelo ID da tarefa que você quer finalizar)
* **Body (JSON):**

    ```json
    {
        "finalizado": true
    }
    ```

### Listar Tarefas Pendentes (GET /tarefas/pendentes)

* **Método:** `GET`
* **URL:** `http://localhost:8080/tarefas/pendentes`

### Listar Departamentos com Pessoas e Tarefas (GET /departamentos/)

* **Método:** `GET`
* **URL:** `http://localhost:8080/departamentos/`
