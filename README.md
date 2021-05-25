# Copyright (C) 2012-2021 Rafael Corchuelo.
#
In keeping with the traditional purpose of furthering education and research, it is
the policy of the copyright owner to permit non-commercial use and redistribution of
this software. It has been tested carefully, but it is not guaranteed for any particular
purposes.  The copyright owner does not offer any warranties or representations, nor do
they accept any liabilities with respect to them.


Este es el proyecto Acme-Planner D3, correspondiente a los alumnos del grupo 30 de DP2 de 
Ingeniería Informática del Software de la Universidad de Sevilla. Para este proyecto había 
que trabajar con un framework especial proporcionado por los profesores de la asignatura.

GitHub Repository: https://github.com/alvgomper1/Acme-Planner.git

- EL PUERTO  ES EL 8090 por diversos conflictos

-NOS PRESENTAMOS AL NIVEL B

-ANTES DE EJECUTAR LOS TESTS ES NECESARIO:
	-Configurar el launcher de Acme-Planner (Test) en tu Eclipse para que ejecute todos los tests en el directorio src/test/java:
		- Click derecho en la carpeta del proyecto en Eclipse ---> Propiedades ---> Run/Debug settings --> Seleccionas Acme-Planner(Test) y le das a editar -->
		---> Dentro de "Run all tests in the selected projects package or source folder..." ponemos el directorio "src/test/java"

- UNA VEZ TERMINEN LOS TESTS DE EJECUTARSE,PARA QUE APAREZCA BIEN EL COVERAGE DE LOS TESTS ES NECESARIO REALIZAR LO SIGUIENTE:
	- Iniciar sesión como adminsitrador (user=administrator pass=administrator)
	- Ir al menú del administrador y pulsar "Shut Down"
	- Tras realizar esto, el coverage debe aparecer correctamente en Eclipse. Superamos el 60% de cobertura en todas las features

INTERPRETACIÓN DEL MÓDULO DE SPAM (Importante leerlo)
-Consideraremos que una cadena contiene spam cuando el porcentaje de palabras de spam con respecto al total de ese texto (número de palabras spam / total de palabras) supere
el umbral establecido. Por ejemplo, el texto "viagra mesa azul" tiene un porcentaje de spam del 33.33%. Si el umbral fuera de 10%, lo detectaría como spam.
- Hemos implementado el spam de tal forma que pueda detectar palabras con espacios incluidos, por ejemplo, la palabra "million dollar" cuenta como una única palabra de spam
cuando se encuentra escrita en ese orden, sin embargo, por ejemplo "million lights dollar", no se considera spam. Las palabras compuestas registradas como spam cuentan como una única palabra, por ejemplo "million dollar mesa", tendría un porcentaje de spam del 50%, porque "million dollar" se considera una única palabra, puesto que solo provoca spam cuando va en ese orden.
- Para que funcione el módulo de spam, es necesario hacer un populate initial, para cargar el módulo. De otra forma al acceder al módulo de spam, pues dará error, porque no lo podrá encontrar en la base de datos. Para cargar las palabras de spam, hacer un populate-sample.


Url del proyecto en CleverCloud: https://app-97baeb30-90ef-49e6-8e97-1a2ceb8d961f.cleverapps.io/Acme-Planner
PARA ACCEDER DE FORMA CORRECTA A LA APLICACIÓN DESPLEGADA EN CLEVERCLOUD HAY QUE PONER AL FINAL DE LA URL "/Acme-Planner/"


USUARIOS PARA NAVEGAR POR LA PÁGINA

- Rol de Manager: usuario -> manager1
                  contraseña -> manager1
           
- Rol de Administrador: usuario -> administrator
                        contraseña -> administrator
