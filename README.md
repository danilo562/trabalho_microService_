# trabalho_microService_
trabalho para microService MBA

-Banco de dados utilizado Mysql com a database de nome projeto

-Todas as Aplicaçoes foram criadas com SPRING-BOOT

-O Eureka que esta com o nome do projeto naming-service roda na porta 8761

-O API Gateway roda na porta 8765

-O Zipkin roda na porta 9411

-O RabbitMq roda na porta 15672  usuario e senha guest

-A conta corrente roda da porta 8100 e vai ate 8199

-A investimento roda da porta 8200 e vai até 8299

-A Pag-Fatura roda da porta 8300 até 8399

-A mov-Conta roda da porta 8400 até 8399



Documentaçao das apis


http://localhost:8100/swagger-ui.html

http://localhost:8200/swagger-ui.html

http://localhost:8300/swagger-ui.html

http://localhost:8400/swagger-ui.html



quando clonar o projeto construir os JAR.

comando: mvn clean package 

ou dar um build no maven colocando o comando spring-boot:build-image


depois para rodar tudo ir na rais de onde esta o projeto e executar o comando docker-compose up
