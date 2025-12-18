# Bank

## 📌 Descrição do Projeto

O **Bank** é um sistema desenvolvido em **Java**, utilizando **Maven** como ferramenta de gerenciamento de dependências e build. O projeto simula funcionalidades básicas de um sistema bancário, com suporte a diferentes perfis de usuários.

---

## 🛠️ Tecnologias Utilizadas

* Java 21
* Maven

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

* Java JDK instalado
* Maven instalado
* Terminal (Linux, macOS ou Windows)

---

### ▶️ Passo a Passo

#### 1️⃣ Abrir o terminal na pasta raiz do projeto

Abra o terminal na pasta raiz do projeto **Bank**.

Para verificar se você está na pasta correta, execute:

* **Linux / macOS:**

```bash
ls
```

* **Windows:**

```bash
dir
```

O resultado deve conter, obrigatoriamente:

* A pasta `src`
* O arquivo `pom.xml`

Se esses arquivos estiverem visíveis, você está na pasta raiz correta.

---

#### 2️⃣ Compilar e empacotar o projeto

Execute um dos comandos abaixo:

```bash
mvn clean install
```

ou

```bash
mvn install
```

Após a execução, será criada a pasta **`target`**, que contém os arquivos gerados no processo de compilação e empacotamento.

Dentro dessa pasta, estará disponível o arquivo:

```text
Main-1.0-SNAPSHOT.jar
```

Esse arquivo `.jar` é o responsável por executar o projeto.

Na pasta raiz, execute o comando abaixo:

```bash
java -jar target/Main-1.0-SNAPSHOT.jar
```

---

## 👤 Usuários Pré-Cadastrados para Login

O sistema já possui usuários criados para fins de teste:

### 🔐 Administrador

* **Email:** [maria@admin.com](mailto:maria@admin.com)
* **Senha:** 12345678
* **Perfil:** admin

---

### 👥 Clientes

**Cliente 1**

* **Email:** [maria@cliente.com](mailto:maria@cliente.com)
* **Senha:** 12345678
* **Perfil:** cliente
* **ID da Conta:** `aad9b6c1-c7ff-4757-a5e1-99f15eb17afe`

**Cliente 2**

* **Email:** [joao@cliente.com](mailto:joao@cliente.com)
* **Senha:** 12345678
* **Perfil:** cliente
* **ID da Conta:** `ed2ad472-3d20-48c3-86da-3d977d83d281`
