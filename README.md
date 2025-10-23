# IMC REST Spring Boot

Este projeto é uma API REST desenvolvida com Spring Boot para gerenciamento de usuários e cálculo de IMC (Índice de Massa Corporal). O objetivo é fornecer endpoints para CRUD de usuários e cálculo/classificação do IMC.

## Estrutura do Projeto

```
src/main/java/com/imc_RSB/imc_rest_springboot/
│
├── Controller
│   └── ApiController.java
├── DTO
│   └── ImcRequest.java
├── Models
│   └── Users.java
├── Repo
│   └── UsersRepo.java
├── Service
│   └── ImcService.java
└── ImcRestSpringbootApplication.java
```
### 1. GET /  
Retorna mensagem simples para teste    
**Exemplo de Resposta:**  
```
 Hello World
```
### 2. GET /users  
Lista todos os usuários cadastrados.  
**Exemplo de Resposta:**

```
[
	{
		"id": 1,
		"name": "Bob",
		"age": 28,
		"weight": 72.5,
		"height": 1.8
	},
	{
		"id": 2,
		"name": "John",
		"age": 28,
		"weight": 72.5,
		"height": 1.8
	}
]
```

### 3. POST /save  
Salva um novo usuário.  
Payload de Requisição:
```
{
  "name": "John",
  "age": 28,
  "weight": 72.5,
  "height": 1.80
}
```

### 4. PUT /update/{id}  
Atualiza os dados de um usuário existente.  
Exemplo de URL:
```
PUT /update/1
```
Payload de Requisição:
```
{
  "name": "John Doe",
  "age": 29,
  "weight": 73.0,
  "height": 1.80
}
```

### 5. DELETE /delete/{id}  
Remove um usuário pelo ID.  
Exemplo de URL:
```
DELETE /delete/1
```
Exemplo de Resposta:
```
User ID Deleted: 1
```

### 6. POST /calculateImc  
Calcula o IMC com base no peso e altura informados.  
Payload de Requisição:
```
{
  "weight": 70,
  "height": 1.75
}
```
Exemplo de Resposta:
```
{
  "IMC": "22.86",
  "classification": "Normal weight"
}
```
## Sobre o cálculo do IMC

A fórmula utilizada é:  
**IMC = peso / (altura * altura)**

Classificação:
- IMC < 18.5: Underweight
- 18.5 <= IMC < 24.9: Normal weight
- 25.0 <= IMC < 29.9: Overweight
- 30.0 <= IMC < 39.9: Obesity
- IMC >= 40: Severe obesity
---

A API estará disponível em [http://localhost:8080](http://localhost:8080).
