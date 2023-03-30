# FinTrack - Controle Financeiro

## Missão
  API para futura comunicaçãio com APP, que ira ser responsavel para gerenciamento, armazenamento, e entrega de dados de conta pessoal para controle proprio, permitindo
o usuario de ter conhecimento de todas suas contas com apenas um clique.

## Funcionalidade em Desenvolvimento ⌛

### Usuario
- [ ] Criação Tabela Banco
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção
- [ ] Endpoint para Autenticação
- [ ] Sistema de Validação e persisntencia da autenticação

### Carteira
- [ ] Criação Tabela Banco
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção

### Despesa
- [ ] Criação Tabela Banco
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção
  - **Parcelas**
  - [ ] Criação Tabela Banco relacionamento despesa
  - [ ] Endpoint para Criação ao criar despesa
  - [ ] Endpoint para Edição ao editar despesa
  - [ ] Endpoint para Deleção ao editar despesa

### Cartões de Credito
- [ ] Criação Tabela Banco
- [ ] Endpoint para Criação
- [ ] Endpoint para Edição
- [ ] Endpoint para Deleção
- [ ] Relacionamento de Despesas com Cartão de Credito

### Deposito
- [ ] Criação Tabela Banco
- [ ] Endpoint para Efetuar Deposito
   - vincular deposito para creditar em carteira


### Pagamento
- [ ] Criação Tabela Banco
- [ ] Endpoint para Criação
   - vincular pagamento com despesa, e vincular para debitar em carteira

## Diagrama de Classe - UML

![Diagrama de Classe](https://user-images.githubusercontent.com/98819630/228962327-e7e6d546-314c-4388-a9a0-0a420a876457.png)

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
- MySql JDBC Connector 8.0.32
   - FlyWay
