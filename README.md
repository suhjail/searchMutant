# MS SEARCH MUTANT

## Introducción

MS_search_mutant es un microservicio que se encarga de buscar por medio de una secuencia de ADN si un humano es mutante.
Para ello se recibe como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.
Si se encuentra ​más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical; el ADN es mutante. 

Por otra parte el MS permite buscar la estadistica de las verificaciones mostrando al usuario la cantidad de mutantes, humanos y el ratio entre ambos.

## Motivación

El MS para la búsqueda de mutantes surge por petición de Magneto el cual quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

## Validaciones a tomar en cuenta

- Para ejecutar el servicio de búsqueda de mutantes llamar a POST "ip:port/mutant/"
- Para la llamada se deberá enviar un json con el siguiente formato:
	{ "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] } 
- Se guardara en la base de datos solo los ADN que cumplan con un request válido:
	( Arreglo distinto de null,
	 Matriz desde 2x4 o 4x2 en adelante,
	 Strings del arreglo con caracteres (A,T,C,G))
- Solo se guardara un regristro por ADN verificado
- En caso de verificar un mutante se devolverá un HTTP 200-OK
- En caso de verificar un humano se devolverá HTTP 403-Forbidden
- En caso de request invalido se devolverá HTTP 400-BadRequest

- Para ejecutar el servicio de estádistica llamar a GET "ip:port/stats"
- El servicio devolverá un json con el siguiente formato:
	{"count_mutant_dna":40, "count_human_dna":100: "ratio":0.4}   

## Tests

Se realizaron pruebas de los distintos casos posibles (existe mutante, adn es humano, adn con longitud no valida,
secuencia de adn con caracter no valido, adn mutante oblicuo, adn existe en bd y es humano o mutante, consulta de estatisticas, adn no puede ser null)
Se ejecuto con eclipse un Coverage AS que dio como resultado un 94,5%

## Instalaciónn
Prerequisitos
- JDK 1.8
- Maven

Build:
```
mvn clean install
```

## Funcional:

#### Configuracion:

Configurar en el archivo `liquibase.properties` las propiedades a continuacion listadas para indicar el acceso a la base de datos a utilizar. Actualmente se esta trabajando con la BD H2.
```
datasource:
    url: {url}
    username: {username}
    password: {password}
 ```
  
#### Inicio de la aplicaciónn

Ejecutar:
```
mvn spring-boot:run
```
