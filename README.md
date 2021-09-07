# trabalho_microService_
trabalho para microService MBA

-Banco de dados utilizado Mysql com a database de nome projeto

-Todas as Aplicaçoes foram criadas com SPRING-BOOT

-O Eureka que esta com o nome do projeto naming-service roda na porta 8761

-O API Gateway roda na porta 8765

-A conta corrente roda da porta 8100 e vai ate 8199

-A investimento roda da porta 8200 e vai até 8299

-A Pag-Fatura roda da porta 8300 até 8399

-A mov-Conta roda da porta 8400 até 8399


Ate o momento são esses links das chamadas das apis

Conta Corrente ===================
Ver Saldo conta corrente por doc
GET
http://localhost:8765/conta-corrente/pesq_doc/2

Ver saldo da conta corrente e investimento por doc
GET
http://localhost:8765/conta-corrente/saldo_cont_inves/3

Investir retirando saldo da conta corrente
Put
http://localhost:8765/conta-corrente/investir/3/34


Credito em  conta corrente
PUT
http://localhost:8765/conta-corrente/credito/2/989887


Debito em conta corrente
PUT
http://localhost:8765/conta-corrente/debito/3/3000



INVESTIMENTO====================
Saldo investimento por doc
GET
http://localhost:8765/investimento/pesq_inv_doc/2

Saldo investimento por ID
GET
http://localhost:8765/investimento/pesq_inv_id/2


Coloca saldo no investimento por Doc
PUT
http://localhost:8765/investimento/incluir_saldo/3/100

Retira saldo no investimento por Doc
PUT
http://localhost:8765/investimento/retira_saldo/3/100

MOVIMENTACAO CONTA=============

PARA CRIAR A MOV NA CONTA {id_cont}/{saldo}/{tipo}/{doc}/{acho}
POST
http://localhost:8765/mov-conta/criar_mov/3/22/conta-corrente/3/pagamento_de_fatura


Para ver extrato por doc
GET
http://localhost:8765/mov-conta/extrato_conta/2



PAGAMENTO DE FATURA=============

Para fazer o pagamento da fatura por doc
PUT

http://localhost:8765/pag-fatura/pag_fat/3


Eureka

http://localhost:8761
