## Miembros

### Santiago Arboleda Velasco - A00369824
### Cristian Camilo Cardona - A00369414
### Camilo Andres Bueno - A00219928



# Integrales Distribuidas

## Descripcion


El sistema debe:

1.  Permitir a los usuarios ingresar una función para integrar junto con los rangos de integración a través de un "cliente". Esto puede lograrse mediante un intérprete para leer la función o usando una librería externa.
2.  Implementar una estrategia de aproximación de integrales que combine varios patrones de diseño, utilizando estructuras de almacenamiento y procesamiento distribuidos. La estrategia debe balancear rendimiento y precisión.
3.  Ejecutar y analizar experimentos para determinar el valor de la distribución a partir de una cierta cantidad de datos, variables que afectan los resultados y la capacidad de aproximación del sistema.

## Objetivo

El objetivo de este proyecto es diseñar e implementar un sistema de software distribuido que permita a los usuarios aproximar integrales definidas utilizando diferentes métodos numéricos. Los estudiantes deben seleccionar la estrategia de aproximación más adecuada basada en resultados de pruebas y experimentos.

Nuestra base de datos permitirá a Fénix Gym:

### Estructura
### /dep

-   Diagrama de deployment de la solución en PowerPoint, incluyendo:
    -   Estructura de Flynn: Almacenamiento (UMA, NUMA) y Procesamiento (SIMD, MISD, MIMD).
    -   Estilos de arquitectura.
    -   Patrones de diseño.
    -   Mapeo de componentes de la arquitectura a los tipos de componentes de cada patrón/estilo.
    -   Explicación de la estrategia para resolver el problema de forma distribuida.

### ./src

-   Código fuente de los proyectos en la estructura definida por convención.

### ./doc

-   Informe en Word que incluya:
    -   Excel con la tabla de valores de configuración-parámetros vs. tiempos vs. precisión y la gráfica XY comparativa de las ejecuciones.
    -   Análisis comparativo de los resultados de las ejecuciones.

## Instrucciones de Compilación y Ejecución

### Requisitos Previos

-  Java
- Gradle
- Ice

### Compilación

1.  Clonar el repositorio del proyecto.
2.  Navegar al directorio `./src`.
3.  Ejecutar en este orden
	* Broker.java
	* Server.java
	* Cliente.java
4. De otra forma:
	* Buscar el los archivos
		* broker.jar
		* server.jar
		* client.jar
	* Ejecutarlos editandoles el manifiesto y config.server indicando





