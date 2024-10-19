# PokeAPI App
___
## Índice
- [Introducción](#introducción)
  - [Postman](#postman) 
- [Manual técnico para desarrolladores](#manual-técnico-para-desarrolladores)
  - [Requisitos previos](#requisitos-previos) 
  - [Estructura](#estructura) 
  - [Metodología](#metodología) 
  - [Configuración de Maven](#configuración-de-maven) 
  - [Configuración de JavaFx](#configuración-de-javafx)
  - [Ejecución del proyecto](#ejecución-del-proyecto)
  - [Manejo de la caché y último estado](#manejo-de-la-caché-y-último-estado)
  - [Manejo de errores](#manejo-de-errores)
  - [Exportación de datos](#exportación-de-datos)
- [Manual de usuario](#manual-de-usuario)
- [Reparto de tareas](#reparto-de-tareas)
- [Extras](#extras)
- [Mejoras](#mejoras)
- [Conclusiones](#conclusiones)
- [Autores](#autores)

## Introducción
[Volver al índice](#índice)

Esta es una aplicación que permite realizar peticiones a la [API de Pokémon](https://pokeapi.co/) [(repositorio de GitHub)](https://github.com/PokeAPI/pokeapi). 

Tras una búsqueda por nombre, el programa mostrará los siguientes campos: número de identificación del pokémon, sprite (imagen) frontal por defecto, sus diferentes nombres en varios idiomas así como las áreas del juego en las que se encuentra el pokémon en estado salvaje.

También es posible registrarse, de modo que tras el registro (o inicio de sesión posterior) el programa permite recuperar la última búsqueda del usuario y también exportar a diferentes formatos la información recopilada.

___
### Postman
Se ha utilizado la aplicación [Postman](https://www.postman.com/) para testear diferentes peticiones a la [PokeAPI](https://pokeapi.co/). En concreto, se realizan 3 peticiones GET diferentes con las que componentos la información de nuestra aplicación.

A continuación se detalla cada petición con su respuesta adjuntando las capturas de Postman:

- #### *Pokémon*:
  La petición principal a partir de la que se guardan tanto el id como los nombres en diferentes idiomas es realizada a: https://pokeapi.co/api/v2/pokemon-species/{name} . Elegimos esta para este fin pues la respuesta  era considerablemente más corta que otras más completas.

  ![Peticion1](media/images/get1.JPG)

- #### *Áreas*:
  La información relativa a las áreas es extraída de la dirección: https://pokeapi.co/api/v2/pokemon/{id}/encounters . Esta petición se lleva a cabo a través del id del pokémon.

  ![Petición2](media/images/get2.JPG)
- #### *Imagen*
  Finalmente, la información sobre el sprite es obtenida a partir de la ruta: https://pokeapi.co/api/v2/pokemon/{name} . De nuevo aquí es necesario hacer una búsqueda por nombre, como en el primer caso.  Nos vamos a quedar exclusivamente con el sprite frontal por defecto, así nos aseguramos que todos los pokémon que existen tienen una referencia a su imagen. 
  ![Petición3](media/images/get3.JPG)


## Manual técnico para desarrolladores
[Volver al índice](#índice)

### Requisitos previos

- **Java SE 17 o superior**: El proyecto está desarrollado usando Java 17, por lo que necesitarás tener una versión igual o superior instalada. ([descargar](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- **JavaFX 23.0.1**: El proyecto usa JavaFX para la interfaz gráfica, por lo que deberás incluir el SDK de JavaFX. ([descargar](https://gluonhq.com/products/javafx/))
- **Maven**: La gestión de dependencias se hace con Maven, por lo que deberás tener Maven instalado.([descargar](https://maven.apache.org/download.cgi))
- **IDE recomendado**: Se recomienda el uso de IntelliJ IDEA para un desarrollo más sencillo, pero se puede usar cualquier otro IDE compatible con Java. ([descargar](https://www.jetbrains.com/idea/download/?section=windows))



### Estructura
El proyecto está planteado siguiendo el patrón [Modelo-Vista-Controlador.](https://es.wikipedia.org/wiki/Modelo%E2%80%93vista%E2%80%93controlador)

![MVC](media/images/mvc.jpg)

#### Modelo
___
El modelo contiene los datos del programa y define cómo estos deben ser manipulados, es decir, contiene la lógica que se necesita para gestionar el estado y las reglas del negocio.
Interactúa respondiendo a las solicitudes del controlador para acceder o actualizar los datos.  Notifica indirectamente a la vista cuando los datos han cambiado para que se actualice la presentación.

En nuestra aplicación cuenta con los siguientes paquetes:

- **<u>edu.badpals.pokeapi.model</u>**: Contiene las clases de modelos de datos como `PokemonData`, `Area`, `PokemonImage`(entre otras). Se utilizan estas clases para realizar el mapeado a los .json de respuesta de la API.


- **<u>edu.badpals.pokeapi.service</u>**: Gestiona las operaciones como peticiones API `APIPetitions`, manejo de caché `CacheManager`, exportación de datos `DocumentExporter` y guardado del último estado `StateManager`.


- **<u>edu.badpals.pokeapi.auth</u>**: la clase `LogInManager` se encarga tanto de verificar la autencidad de un usuario (comprobando sus credenciales en un fichero .properties) como de registrar nuevos usuarios. Las credenciales de los usuarios se guardan cifradas en el archivo. 


- **<u>/cache</u>**: en el directorio caché se guardan las respuestas a las peticiones que se han realizado a la API en sesiones previas, que son cargadas en el programa antes de hacer nuevas consultas, para mejorar el rendimiento. Los datos están almacenados en formato .json y las imágenes en .png .


- **<u>.appData</u>**: en este directorio se almacena en formato .bin la última búsqueda que realiza un usuario tras hacer logIn y salir de la aplicación. Cuando el usuario regresa puede restablecer esa búsqueda.

#### Controlador
___
El controlador recibe las entradas del usuario desde la vista y las traduce en acciones que el modelo debe ejecutar. Se encarga de interpretar las acciones del usuario, manejar los eventos, y de actualizar tanto el modelo como la vista.

- **<u>edu.badpals.pokeapi.controller</u>**: Coordina la interacción entre los diferentes componentes, y controla la lógica de la aplicación. Se gestiona todo desde la clase `AppController`.

#### Vista
___
Se encarga de la visualización de los datos del modelo de manera que el usuario los entienda. No contiene lógica de negocio, solo muestra lo que el modelo le proporciona.. La vista recibe entradas del usuario (clics, teclas, etc.) y las envía al controlador.

- `edu.badpals.pokeapi.Application.java`: Contiene la clase principal del programa, relacionada con la generación de la interfaz gráfica de usuario (con JavaFX). En ella se crean las escenas cuando se ejecuta el programa.


- **<u>resources</u>**: en el directorio resources se almacenan los recursos necesarios para construir la interfaz de usuario, desde los archivos .fxml en los que se diseñan las vistas, hasta la hoja de estilos css e imágenes. 

### Metodología
**Uso de Git**

Este proyecto sigue una metodología de desarrollo incremental basado en ramas, lo que facilita la gestión de versiones y la colaboración entre desarrolladores. Las ramas principales del proyecto son `main` y `develop`, mientras que el desarrollo se llevó a cabo en paralelo en las ramas `controller`, `service` y `model`.

El flujo de trabajo del desarrollo es el siguiente:

1. **Añadir Nuevas Funcionalidades**: Cuando se desea implementar una nueva funcionalidad, se trabaja en la rama propia al paquete que pertenece. Cada desarrollador trabaja en una única rama, permitiendo que el trabajo avance de manera independiente.

2. **Testeo**: Una vez que se ha completado la funcionalidad, se realizan pruebas para asegurar que todo funciona correctamente y cumple con los requisitos establecidos.

3. **Merge a Develop**: Después de las pruebas exitosas, se realiza un "merge" de la rama de funcionalidad a `develop`. Este paso es crucial para comprobar la integración de la nueva funcionalidad con el resto del código del proyecto.

4. **Merge a Main**: Finalmente, cuando la versión en `develop` ha sido probada y se confirma que es estable, se realiza un "merge" a la rama `main`. Esto marca el lanzamiento de una nueva versión del proyecto.

Este enfoque permite una colaboración fluida entre los dos desarrolladores, asegurando que el código sea de alta calidad y esté bien integrado antes de ser lanzado.

### Configuración de Maven

El archivo `pom.xml` incluye las siguientes dependencias importantes:

```xml
<dependencies>
    <!-- Dependencias para JavaFX -->
    <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-fxml</artifactId>
      <version>17.0.6</version>
    </dependency>

    <!-- Dependencias para JSON (Jackson) -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.18.0-rc1</version>
    </dependency>

  <!-- También se incluyen las siguientes dependencias para realizar testing, 
  pero no se han añadido test durante el desarrollo -->
  
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
</dependencies>
```
### Configuración de JavaFX

Para ejecutar el proyecto con JavaFX, tras [descargar el SDK]((https://gluonhq.com/products/javafx/)) se necesitan añadir los siguientes módulos en la configuración de tu IDE:

```
--module-path="ruta\directorio\javaFx\lib" --add-modules="javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing,javafx.media"
```
Para ejecutar el programa directamente en el IDE, es necesario añadirlos en *File>Project Structure>Global Libraries>New Global Library>Java* y a continuación cargar los .jar del directorio lib donde se haya guardado javaFx. 

También es posible preparar la ejecución del .jar del programa desde la ventana de *Run /Debug Configurations* añadiendo en *VM options* las líneas del cuadro anterior.

### Ejecución del proyecto

#### Desde el IDE (IntelliJ IDEA):

1. Importar el proyecto como un proyecto Maven.
2. Asegurarse de que las dependencias estén instaladas en el `pom.xml`.
3. Configurar los módulos de JavaFX como se describió anteriormente.
4. Ejecutar la clase `Main` para iniciar la aplicación.

#### Desde la terminal:
Una vez que el JAR esté generado, se ejecuta el siguiente comando **desde el directorio donde se encuentre el .jar generado**:
``` bash
java --module-path="/ruta/al/javafx/lib" --add-modules="javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing,javafx.media" -jar nombre-del-archivo.jar
```

### Manejo de la caché y último estado

El proyecto almacena los datos de los Pokémon consultados y sus imágenes localmente en la carpeta de caché (`/cache`).

- Los datos JSON se guardan usando la clase `CacheManager`, que guarda las respuestas de la API localmente para evitar llamadas repetidas.
- Las imágenes de los Pokémon se descargan y guardan en formato `.png` en el mismo directorio.

Se puede limpiar la caché con el método `deleteCache()` de la clase `CacheManager`.

El último estado del usuario loggeado se almacena en formato `.bin` en el directorio oculto `.appData`. Cuando se inicia sesión se muestra este estado y se almacena cuando el usuario loggeado sale del programa. 

### Manejo de errores

En la aplicación, los errores se gestionan de dos maneras:
- Los errores que ocurren en las clases del **modelo** (relacionadas con la lógica interna) se manejan de forma interna y se registran en un archivo de log.

- Por otro lado, los errores que se producen en la **interfaz de usuario** se presentan directamente en pantalla, para que el usuario pueda comprender qué ha fallado.

Para registrar los errores del modelo, existe una clase llamada `ErrorLogger`, ubicada en el paquete `service`. Esta clase es responsable de guardar los errores en un archivo llamado `error.log`, que se encuentra en la carpeta oculta `.appData`.


### Exportación de datos

El proyecto permite exportar la información de los Pokémon a diferentes formatos, como `.json`, `.xml`, `.txt`, y `.bin`.

- La exportación se maneja dentro de la clase `DocumentExporter`, y se puede seleccionar el formato que se prefiera tras hacer una consulta (aunque esto sólo se mostrará en la vista cuando el usuario se autentifique).

## Manual de usuario
[Volver al índice](#índice)



## Reparto de tareas
[Volver al índice](#índice)


## Extras
[Volver al índice](#índice)


## Mejoras
[Volver al índice](#índice)



## Conclusiones
[Volver al índice](#índice)


## Autores
[Volver al índice](#índice)