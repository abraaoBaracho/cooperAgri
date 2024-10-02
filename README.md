# **CooperAgri Logistica**
Projeto da disciplina **Laboratório de Empreendimentos** do curso **Análise e Desenvolvimento de Sistemas**.

## Principais Tecnologias
 - **Java 17**: Utilizaremos a versão LTS mais recente do Java para aproveitar as inovações que essa linguagem robusta e amplamente utilizada oferece;
 - **Spring Boot 3**: Trabalharemos com a versão mais recente do Spring Boot, que maximiza a produtividade do desenvolvedor por meio de sua poderosa premissa de autoconfiguração;
 - **Spring Data JPA**: Exploraremos como essa ferramenta pode simplificar a camada de acesso a dados, facilitando a integração com bancos de dados SQL.
   
   ## Diagrama de Classes (Domínio da API)
   erDiagram
 user {
     INT id PK "AUTO_INCREMENT"
     VARCHAR nome "50"
     VARCHAR cpf "11"
     VARCHAR senha "100"
     VARCHAR email "30"
 }
 endereco {
     INT id PK "AUTO_INCREMENT"
     VARCHAR rua "100"
     VARCHAR cep "10"
     VARCHAR numero "10"
     VARCHAR bairro "50"
     VARCHAR cidade "50"
 }
 dados_bancarios {
     INT id PK "AUTO_INCREMENT"
     VARCHAR banco "100"
     VARCHAR agencia "20"
     VARCHAR conta "20"
     VARCHAR tipo_conta "20"
     VARCHAR pix "100"
 }
 produto {
     INT id PK "AUTO_INCREMENT"
     VARCHAR nome "50"
     VARCHAR ncm "20"
     VARCHAR cod "20"
     DECIMAL preco "10,2"
     INT quantidade
     VARCHAR unidade_de_medida "20"
 }
 fornecedor {
     INT id PK "AUTO_INCREMENT"
     VARCHAR nome "100"
     VARCHAR cpf "14"
     VARCHAR rg "20"
     VARCHAR caf_dap "50"
     VARCHAR apelido "50"
     INT endereco_ID FK
     VARCHAR fone "20"
     INT dados_bancarios_ID FK
     VARCHAR arquivos "50"
 }
 servico {
     INT id PK "AUTO_INCREMENT"
     VARCHAR tipo "100"
     DECIMAL valor_servico "10,2"
     INT quantidade_de_horas
     DECIMAL valor_total "10,2"
     INT trabalhador_ID FK
     DATE dia_servico
 }
 trabalhador {
     INT id PK "AUTO_INCREMENT"
     VARCHAR nome "100"
     VARCHAR cpf "14"
     VARCHAR rg "20"
     INT endereco_ID FK
     VARCHAR fone "20"
     INT dados_bancarios_ID FK
 }
 venda {
     INT id PK "AUTO_INCREMENT"
     DATE dia_venda
     DECIMAL valor "10,2"
     INT fornecedor_ID FK
 }
 venda_produto {
     INT venda_ID
     INT produto_ID 
     INT quantidade
 }
 fornecedor ||--o| endereco : "has"
 fornecedor ||--o| dados_bancarios : "has"
 trabalhador ||--o| endereco : "has"
 trabalhador ||--o| dados_bancarios : "has"
 venda ||--o| fornecedor : "from"
 venda_produto ||--o| venda : "related to"
 venda_produto ||--o| produto : "includes"
 trabalhador ||--o| servico : "assigned to"

