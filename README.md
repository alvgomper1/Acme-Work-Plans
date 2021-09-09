# Copyright (C) 2012-2021 Rafael Corchuelo.
#
In keeping with the traditional purpose of furthering education and research, it is
the policy of the copyright owner to permit non-commercial use and redistribution of
this software. It has been tested carefully, but it is not guaranteed for any particular
purposes.  The copyright owner does not offer any warranties or representations, nor do
they accept any liabilities with respect to them.


Este es el proyecto Acme-Work-Plans D3, correspondiente a los alumnos del grupo 30 de DP2 de 
Ingeniería Informática del Software de la Universidad de Sevilla. Para este proyecto había 
que trabajar con un framework especial proporcionado por los profesores de la asignatura.

GitHub Repository: https://github.com/alvgomper1/Acme-Work-Plans

Github Release: https://github.com/alvgomper1/Acme-Work-Plans/tree/v1.2



- EL PUERTO  ES EL 8090 por diversos conflictos

-NOS PRESENTAMOS AL NIVEL B

--ANTES DE EJECUTAR LOS TESTS ES NECESARIO: -Configurar el launcher de Acme-Work-Plans (Test) en tu Eclipse para que ejecute todos los tests en el directorio src/test/java: - Click derecho en la carpeta del proyecto en Eclipse ---> Propiedades ---> Run/Debug settings --> Seleccionas Acme-Work-Plans(Test) y le das a editar --> ---> Dentro de "Run all tests in the selected projects package or source folder..." ponemos el directorio "src/test/java"

-UNA VEZ TERMINEN LOS TESTS DE EJECUTARSE,PARA QUE APAREZCA BIEN EL COVERAGE DE LOS TESTS ES NECESARIO REALIZAR LO SIGUIENTE:

-Iniciar sesión como adminsitrador (user=administrator pass=administrator)

-Ir al menú del administrador y pulsar "Shut Down"

-Tras realizar esto, el coverage debe aparecer correctamente en Eclipse. Superamos el 60% de cobertura en todas las features

- Hemos partido del proyecto desarrollado durante el curso, cambiando algunos detalles de los nuevos requisitos, así como el nombre a "Acme-Work-Plans" (junto con los launchers de la base de datos). El compañero "Manuel García Marchena" aprobó la asignatura en la convocatoria de Junio, pero lo seguimos mencionando en los diferentes reportes del entregable, puesto que fue, junto con nosotros, autor del proyecto.

- IMPORTANTE LEER NUESTRA INTERPRETACIÓN DEL MÓDULO DE SPAM (Apartado 2 del reporte "Features Model").
- El atributo "workload" tiene el significado "horas.minutos". Es decir, 1.5 representa "1 hora y 50 minutos".
- Para satisfacer el requisito de "executionPeriod", hemos creado 2 fechas, una de inicio y otra de fin para las Tasks. No obstante, hemos creado además un nuevo atributo "executionPeriod" que indica la duración en horas entre la fecha de inicio y el fin de la tarea (En este caso, 1.5= 1 hora y 30 minutos) Se usará para saber si un workload entra dentro de la duración de una task. 

Url del proyecto en CleverCloud: http://app_8a279057-5f09-4846-9e5d-f0687595e43d.cleverapps.io/Acme-Work-Plans


USUARIOS PARA NAVEGAR POR LA PÁGINA

- Rol de Manager: usuario -> manager1
                  contraseña -> manager1
           
- Rol de Administrador: usuario -> administrator
                        contraseña -> administrator
