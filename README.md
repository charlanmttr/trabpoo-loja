# API Loja

Projeto desenvolvido por Charlan Matter e Felipe Vargas para a disciplina de Programação Orientada a Objetos Avançada. A API tem o objetivo de simular uma loja, podendo gerenciar Funcionarios, Clientes e Produtos. Rotas disponibilizadas:
  - **/vendedores:** GET, POST e UPDATE
  - **/vendedores/{id}:** GET, DELETE
  - **/clientes:** GET, POST e UPDATE
  - **/clientes/{id}:** GET, DELETE
  - **/produtos:** GET, POST e UPDATE
  - **/produtos/{id}:** GET, DELETE

# Como utilizar

É necessario ter instalado: 
  - MySQL para criar e utilizar o banco de dados.
  - Java para executar a API.
  - [Apache TomCat](https://tomcat.apache.org/download-90.cgi) para o servidor .
  - Instalar algum cliente REST. Nós utilizamos o [Insomnia](https://insomnia.rest/download/) para executar as requisições.

Para utilizar:
  - Importe/clone o arquivo disponibilizado no GitHub na sua IDE.
  - Crie uma base de dados MySQL com os comandos. Nós utilizamos o software MySQL Workbench para gerenciar a base de dados:
  ```
  create database loja;
  use  loja;
  ```
  - Configure o arquivo ```persistence.xml``` que se encontra na pasta ```src/main/resources/META-INF/``` e configure a sua base de dados no projeto, alterando as propriedades ```javax.persistence.jdbc.url```, ```javax.persistence.jdbc.user``` e ```javax.persistence.jdbc.password```:
  ```
  <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost/```nome_da_base_de_dados```?allowPublicKeyRetrieval=true&amp;useSSL=false&amp;serverTimezone=UTC" />
  <property name="javax.persistence.jdbc.user" value="user_bd" />
  <property name="javax.persistence.jdbc.password" value="password_bd" />
  ```
  - Crie um servidor Apache Tomcat e disponibilize-o para o projeto Loja.
  - Inicie o servidor.
  - Teste as requisições no Insomnia. Essas requisições podem ser importadas no Insomnia a partir do arquivo ```API-Loja.json```.
  
# Dúvidas
  
Se necessário, podem entrar em contato com nós:
  
  - charlan.matter@outlook.com
  - felipe.vargas1822@gmail.com
  
Ou pelo WhatsApp.
 
