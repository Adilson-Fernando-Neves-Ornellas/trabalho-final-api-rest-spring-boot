# E-Commerce API - Documentação

Bem-vindo à documentação da API do nosso sistema de E-Commerce. Esta API foi desenvolvida para gerenciar produtos, categorias, pedidos e contas de usuário em nosso sistema de compras online.

## Sobre o Projeto

O seu grupo foi convidado a desenvolver uma API para um novo sistema de E-Commerce. Neste sistema, os usuários poderão executar várias ações, dependendo de seu tipo (cliente ou admin). Abaixo estão listadas as principais funcionalidades oferecidas pela API:

### Funcionalidades do Cliente:

- Consultar uma lista de produtos.
- Consultar uma lista de produtos vinculada a uma categoria.
- Consultar um produto pelo seu id.
- Consultar uma lista de categorias.
- Consultar uma categoria pelo id.
- Cadastrar uma conta (Toda conta deve conter: e-mail, senha, telefone, data de cadastro e perfil).
- Criar um pedido (Todo pedido deve ter: Número, Cliente, Data do pedido, valor total, desconto total, acréscimo total e observação).
- Adicionar itens ao pedido (Cada item poderá ter: quantidade, valor unitário, desconto, acréscimo e valor total).

### Funcionalidades do Admin:

- Cadastrar e atualizar categorias.
- Cadastrar, atualizar e inativar produtos.
- Visualizar tabela de log das alterações e inclusões feitas em produtos e categorias (id, tipo, data, valor original, valor atual, id do usuário).

### Requisitos Extras:

- Implementação da documentação no Swagger: [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).
- Adicionar foto ao produto.

## Endpoints Disponíveis

### Clientes (Autenticado = false):

- **GET /produtos**: Retorna uma lista de todos os produtos disponíveis.
- **GET /produtos/{id}**: Retorna detalhes de um produto específico pelo seu id.
- **GET /categorias**: Retorna uma lista de todas as categorias disponíveis.
- **GET /categorias/{id}**: Retorna detalhes de uma categoria específica pelo seu id.

### Pedidos (Autenticado = true):

- **POST /usuarios**: Cadastra uma nova conta de usuário (requer autenticação).
- **POST /pedidos**: Cria um novo pedido (requer autenticação). O corpo da requisição deve conter detalhes do pedido e dos itens.
- **POST /pedidos/{id}/itens**: Adiciona um item ao pedido existente (requer autenticação). O corpo da requisição deve conter detalhes do item.

### Admin (Autenticado = true, tipo = Admin):

- **POST /categorias**: Cadastra uma nova categoria (requer autenticação).
- **PUT /categorias/{id}**: Atualiza uma categoria existente pelo seu id (requer autenticação).
- **POST /produtos**: Cadastra um novo produto (requer autenticação).
- **PUT /produtos/{id}**: Atualiza um produto existente pelo seu id (requer autenticação).
- **DELETE /produtos/{id}**: Inativa um produto existente pelo seu id (requer autenticação).
- **DELETE /categorias/{id}**: Inativa uma categoria existente pelo seu id (requer autenticação).
- **GET /logs**: Retorna a tabela de log das alterações e inclusões feitas em produtos e categorias.

## Tratamento de Erros

A API foi desenvolvida com tratamento de erro para garantir a integridade dos dados e a segurança do sistema. Caso ocorram erros, serão retornados os status code apropriados para indicar o tipo de erro.

## Documentação Swagger

A documentação detalhada dos endpoints está disponível no Swagger. Para visualizar a documentação e testar os endpoints, acesse [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

## Extras

- **Adicionar Foto ao Produto**: A API permite a adição de fotos aos produtos em base64 para uma melhor experiência visual do usuário.

---

Obrigado por utilizar nossa API de E-Commerce! Se você tiver alguma dúvida ou precisar de suporte, não hesite em entrar em contato conosco.

*Equipe de Desenvolvimento

- ** ADILSON FERNANDO NEVES ORNELLAS
- ** BÁRBARA DÊSLANDES DA SILVA DE OLIVEIRA
- ** BERNARDO CAMARA SIQUEIRA DE MORAES
- ** BRUNA ZIMBRÃO SILVA
- ** DOUGLAS DO AMARAL ROCHA MARIANO
- ** GABRIEL DA COSTA GARCIA
- ** LEONARDO LUCAS DA SILVA MENDES

- Curso de Full Stack do Serratec*
