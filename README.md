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

## Endpoints

### 1. `GET /`
Retorna uma mensagem de teste.

**Resposta:**
```
Hello World
```

---

### 2. `GET /users`
Lista todos os usuários cadastrados.

**Resposta:**
```json
[
  {
    "id": 1,
    "name": "João",
    "age": 30,
    "weight": 80.0,
    "height": 1.75
  }
]
```

---

### 3. `POST /save`
Cadastra um novo usuário.

**Payload:**
```json
{
  "name": "Maria",
  "age": 25,
  "weight": 65.0,
  "height": 1.68
}
```
**Resposta:**
```
User Saved
```

---

### 4. `PUT /update/{id}`
Atualiza os dados de um usuário pelo ID.

**Payload:**
```json
{
  "name": "Maria Silva",
  "age": 26,
  "weight": 66.0,
  "height": 1.68
}
```
**Resposta:**
```
User Updated
```

---

### 5. `DELETE /delete/{id}`
Remove um usuário pelo ID.

**Resposta:**
```
User ID Deleted: {id}
```

---

### 6. `POST /calculateImc`
Calcula o IMC a partir do peso e altura informados.

**Payload:**
```json
{
  "weight": 70.0,
  "height": 1.70
}
```
**Resposta:**
```
IMC: 24.22 - Classificação: Peso normal
```

---

## Sobre o cálculo do IMC

A fórmula utilizada é:  
**IMC = peso / (altura * altura)**

Classificação:
- IMC < 18.5: Abaixo do peso
- 18.5 <= IMC < 24.9: Peso normal
- 25.0 <= IMC < 29.9: Sobrepeso
- 30.0 <= IMC < 39.9: Obesidade
- IMC >= 40: Obesidade grave
