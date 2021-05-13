# README.txt
#
# Copyright (C) 2012-2021 Rafael Corchuelo.
#
# In keeping with the traditional purpose of furthering education and research, it is
# the policy of the copyright owner to permit non-commercial use and redistribution of
# this software. It has been tested carefully, but it is not guaranteed for any particular
# purposes.  The copyright owner does not offer any warranties or representations, nor do
# they accept any liabilities with respect to them.


Este es el proyecto Acme-Planner, correspondiente a los alumnos del grupo 30 de DP2 de 
Ingeniería Informática del Software de la Universidad de Sevilla. Para este proyecto había 
que trabajar con un framework especial proporcionado por los profesores de la asignatura.

GitHub Repository: https://github.com/felconmar/Acme-Planner.git
GitHub Release: https://github.com/felconmar/Acme-Planner/releases/tag/1.0.1


INTERPRETACIÓN DEL MÓDULO DE SPAM (Importante leerlo)
-Consideraremos que una cadena contiene spam cuando el porcentaje de palabras de spam con respecto al total de ese texto (número de palabras spam / total de palabras) supere
el umbral establecido. Por ejemplo, el texto "viagra mesa azul" tiene un porcentaje de spam del 33.33%. Si el umbral fuera de 10%, lo detectaría como spam.
- Hemos implementado el spam de tal forma que pueda detectar palabras con espacios incluidos, por ejemplo, la palabra "million dollar" cuenta como una única palabra de spam
cuando se encuentra escrita en ese orden, sin embargo, por ejemplo "million lights dollar", no se considera spam. Las palabras compuestas registradas como spam cuentan como una única palabra, por ejemplo "million dollar mesa", tendría un porcentaje de spam del 50%, porque "million dollar" se considera una única palabra, puesto que solo provoca spam cuando va en ese orden.
- Para que funcione el módulo de spam, es necesario hacer un populate initial, para cargar el módulo. De otra forma al acceder al módulo de spam, pues dará error, porque no lo podrá encontrar en la base de datos. Para cargar las palabras de spam, hacer un populate-sample.

CAMBIO DE NIVEL A HACIA EL B
- A mediados del Sprint decidimos presentarnos al nivel B en lugar del A. EL presupuesto y el workplan han sido adaptados a este cambio, sin embargo, en gitGub hemos decidido dejar sin eliminar las ramas correspondientes a las funcionalidades del A, para que se pueda realizar un mejor seguimiento de todo lo que ha ocurrido en el proyecto. (La rama master no incorpora las funcionalidades A).

Url del proyecto en CleverCloud: http://app-812a79d4-b2d0-42d1-a1a9-069917a7af35.cleverapps.io/Acme-Planner 
PARA ACCEDER DE FORMA CORRECTA A LA APLICACIÓN DESPLEGADA EN CLEVERCLOUD HAY QUE PONER AL FINAL DE LA URL "/Acme-Planner/"


Usuarios para navegar por la página:

- Rol de Manager: usuario -> manager1
                  contraseña -> manager1
           
- Rol de Administrador: usuario -> administrator
                        contraseña -> administrator
