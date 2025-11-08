# 🏥 Clínica Plus — API REST (Spring Boot + PostgreSQL)

A **Clínica Plus API** é um sistema backend desenvolvido em **Java 21 (Spring Boot 3)** com **PostgreSQL**, criado para gerenciar **pacientes**, **médicos** e **consultas médicas**.  
O projeto segue boas práticas de **REST**, **soft delete**, **validação de dados**, e já vem com **Swagger/OpenAPI** para documentação automática.

---

## 🚀 Tecnologias Utilizadas
- **Java 21+**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Spring Validation (Jakarta)**
- **SpringDoc OpenAPI (Swagger UI)**
- **Dotenv (java-dotenv)**
- **Insomnia** (para testes)

---

## ⚙️ Estrutura do Projeto
```
src/main/java/com/clinica/clinica_plus/
│
├── config/                 # Configurações e integração .env
├── controller/             # Controllers REST
├── model/                  # Entidades JPA (Paciente, Médico, Consulta)
├── repository/             # Interfaces JPA
├── service/                # Regras de negócio
└── ClinicaPlusApplication  # Classe principal
```

---

## 🧩 Configuração do `.env`

Crie um arquivo chamado `.env` na **raiz do projeto** com suas credenciais do banco:

```bash
DB_URL=jdbc:postgresql://localhost:5432/clinica_plus
DB_USERNAME=clinica_user
DB_PASSWORD=123456
```

---

## 🗄️ application.properties

O projeto carrega as variáveis do `.env` automaticamente:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false
server.port=8080
```

---

## ▶️ Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/alvarodultra/clinica-plus.git
   ```
2. Acesse o diretório:
   ```bash
   cd clinica-plus
   ```
3. Configure o `.env` conforme o exemplo acima.
4. Execute:
   ```bash
   ./mvnw spring-boot:run
   ```
5. A API estará disponível em:
   ```
   http://localhost:8080
   ```

---

## 📘 Endpoints Principais

### 🧍 Pacientes
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/pacientes` | Cadastra novo paciente |
| `GET`  | `/pacientes` | Lista todos os pacientes ativos |
| `PUT`  | `/pacientes/{id}` | Atualiza dados do paciente |
| `DELETE` | `/pacientes/{id}` | Inativa o paciente (soft delete) |

### 👩‍⚕️ Médicos
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/medicos` | Cadastra novo médico |
| `GET`  | `/medicos` | Lista todos os médicos |

### 📅 Consultas
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/consultas/agendar` | Agenda nova consulta |
| `GET`  | `/consultas` | Lista todas as consultas |
| `GET`  | `/consultas/{id}` | Busca uma consulta específica |
| `PUT`  | `/consultas/{id}/cancelar` | Cancela uma consulta |
| `PUT`  | `/consultas/{id}/concluir` | Marca consulta como concluída |

---

## 🧠 Regras Importantes
- Pacientes e médicos possuem campo `ativo` → exclusão lógica.
- CPF e e-mail de paciente são **únicos**.
- CRM de médico é **único**.
- Data da consulta deve ser **futura**.
- As entidades são relacionadas:
  - `Consulta` → N:1 com `Paciente`
  - `Consulta` → N:1 com `Médico`

---

## 🧪 Testes no Insomnia
A coleção de testes está pronta para importação:  
📁 `ClinicaPlus_Insomnia.json`

Ela inclui requisições para:
- Criar/atualizar/excluir pacientes
- Cadastrar médicos
- Agendar e listar consultas

---

## 📚 Swagger UI
Documentação automática disponível em:
```
http://localhost:8080/swagger-ui.html
```

---

## 🔒 Boas Práticas Adotadas
✅ Soft delete (`ativo = false`)  
✅ Validações com `@NotBlank`, `@Email`, `@Pattern`, `@Future`  
✅ Enum para especialidades médicas  
✅ Padrão RESTful completo  
✅ Banco relacional com chaves estrangeiras  
✅ Tratamento de erros customizado (`ExceptionHandler`)

---

## 👨‍💻 Autor
**Álvaro Filipe Silva Dultra**  
📍 Salvador - BA  
💼 Advogado & Desenvolvedor Java  
📧 [alvarodultra.dev@gmail.com](mailto:alvarodultra.dev@gmail.com)

---

## 🪶 Licença
Este projeto é distribuído sob a licença MIT.  
Você pode utilizá-lo livremente para fins acadêmicos e profissionais.
