#Sistema de Ouvidoria - UNIFACISA

Desenvolvimento de uma aplicaçãoa CRUD com uma implementação de um sistema de ouvidoria, utilizando categorias distintas (Reclamações, Elogios e Sugestões), adicionando, alterando ou deletando novas informações através de um Banco de Dados MySQL em uma conexão local.

#Start
Para também usar essa aplicação, close o repositório com:

```bash
$ git clone https://github.com/pedrohpdo/ouvidoriaJavaUnifacisa
```

Dentro do MySQL Workbench crie uma nova conexão com o user _root_ e o password _root_, depois, crie as tabelas com os seguintes comandos:

```sql
CREATE DATABASE ouvidoriap2facisa;

USE ouvidoriap2facisa;

CREATE TABLE feedbacks (
    
    id int not null auto_increment primary key,
    type varchar(20) not null,
    author varchar(45) not null,
    description varchar(250) not null
    	
);
```
Depois é só rodar o projeto através do próprio eclipse
```
## Tecnologias Usadas

- Java
- MySQL