# Microservicio ImportFile

MSCIMPORTFILE es un microservicio diseñado para facilitar la importación de archivos CSV en aplicaciones. La principal funcionalidad del servicio es permitir la importación de archivos CSV, asociarlos con una configuración específica de columnas y realizar operaciones basadas en la configuración de esas columnas.

## Funcionalidades Principales

- **Importación de Archivos:** Importa archivos CSV y realiza operaciones según la configuración de columnas.
- **Configuración de Columnas:** Asocia archivos con configuraciones específicas de columnas para una importación precisa.
- **Manejo de Errores:** Proporciona información detallada sobre errores durante la importación.

## Tecnologías Utilizadas

- JAVA - Spring Boot
- SQL SERVER

## Estructura del Proyecto

- **`src/main/java/com/diceprojects/importcsv`**: Código fuente del microservicio.
    - `config`: Configuraciones de la aplicación.
    - `controllers`: Controladores que manejan las solicitudes HTTP (EndPoints del servicio).
    - `exceptions`: Clases relacionadas con el manejo de excepciones.
    - `persistences`: Modelos y entidades relacionadas con la persistencia.
    - `services`: Lógica de negocio y servicios.
    -  `clients`: La conexión con los otros microservicios del cual depende la API.
- **`src/test`**: Pruebas unitarias y de integración.

## Configuración

Asegúrate de configurar adecuadamente el archivo `application.properties` para la base de datos y otras configuraciones necesarias.

## Uso

1. **Compilación y Ejecución:** Utiliza Maven o tu herramienta de construcción preferida para compilar y ejecutar el proyecto.
   ```bash
   mvn clean install
   java -jar target/msvcimportcsv.jar

## Endpoints

-[HOST]:[PORT]/doc/swagger-ui/index.html

## Documentación de las Clases

La documentación Javadoc para cada clase se encuentra en el código fuente. Asegúrate de revisar la documentación de las clases para obtener más detalles sobre su funcionalidad y uso.

## Contribuir

¡Contribuciones son bienvenidas! Si encuentras errores o mejoras, abre un problema o envía una solicitud de extracción.

## Licencia

Este proyecto está bajo la Licencia[DICEPROJECTS.COM]. Consulta el archivo LICENSE.md para más detalles.

## Dependencia

Este proyecto depende del microservicio Columns (https://github.com/MartinDiCe/msvccolumns/).
