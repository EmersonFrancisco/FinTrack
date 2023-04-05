# FinTrack - Controle Financeiro

## Missão
  API para futura comunicaçãio com APP, que ira ser responsavel para gerenciamento, armazenamento, e entrega de dados de conta pessoal para controle proprio, permitindo
o usuario de ter conhecimento de todas suas contas com apenas um clique.

## Funcionalidade em Desenvolvimento ⌛ ![](https://geps.dev/progress/46)

### Usuario
- [x] Criação Tabela Banco
- [x] Criação e mapeamento de classe
- [x] Endpoint para Criação
- [x] Endpoint para Edição
- [x] Endpoint para Deleção
- [ ] Endpoint para Autenticação
- [ ] Sistema de Validação e persisntencia da autenticação

### Carteira
- [x] Criação Tabela Banco
- [x] Criação e mapeamento de classe
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção

### Despesa
- [x] Criação Tabela Banco
- [x] Criação e mapeamento de classe
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção
  - **Parcelas**
  - [x] Criação Tabela Banco
  - [x] Criação e mapeamento de classe
  - [ ] Endpoint para Criação 
  - [ ] Endpoint para Edição 
  - [ ] Endpoint para Deleção

### Cartões de Credito
- [x] Criação Tabela Banco
- [x] Criação e mapeamento de classe
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção
- [x] Relacionamento de Despesas com Cartão de Credito

### Deposito
- [x] Criação Tabela Banco
- [ ] Endpoint para Efetuar Deposito
   - vincular deposito para creditar em carteira


### Pagamento
- [x] Criação Tabela Banco
- [ ] Endpoint para Criação
   - vincular pagamento com despesa, e vincular para debitar em carteira

## Diagrama de Classe - UML
![Diagrama de Classe](https://user-images.githubusercontent.com/98819630/229304511-51368701-f086-4745-8d31-fd5a8c793662.png)

## Fluxograma

![Fluxograma](https://user-images.githubusercontent.com/98819630/228962060-5b3dd648-1f98-420d-81b3-a07e97efcc3c.png)

## Tecnologias Utilizadas

- JAVA 17
- Spring Framework 3.0.5
   - Maven Plugin
   - Data JPA
   - Starter Validation
   - Starter Web
   - Starter test
- MySql JDBC Connector
- Liquibase
- Apache Tomcat
   
## Autores

 [<img src="https://avatars.githubusercontent.com/u/98819630?v=4" width=115><br><sub>Emerson Airton Francisco</sub>](https://github.com/EmersonFrancisco) 
