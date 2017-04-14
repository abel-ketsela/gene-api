Config Info
-------------------------------------------------------------------------------------

Database -> MySQL

schema -> gene_api_db
username -> admin
passowrd -> admin#123


spring.datasource.url=jdbc:mysql://localhost:3306/gene_api_db?useSSL=false
spring.datasource.username=admin
spring.datasource.password=admin#123
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto = update


sample api uri
-------------------------------------------------------------------------------------
to load data -> http://localhost:8080/loaddata

to fetch genes -> http://localhost:8080/api/genes
to fetch genes with pagination -> http://localhost:8080/api/genes?start=10&limit=20

to fetch gene -> http://localhost:8080/api/genes/673

to fetch variants-> http://localhost:8080/api/genes/673/variants
to fetch variants with pagination -> http://localhost:8080/api/genes/673/variants?start=10&limit=20

to fetch variant-> http://localhost:8080/api/genes/1956/variants/12250
