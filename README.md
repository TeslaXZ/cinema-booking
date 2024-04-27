
# # GuayaCine-Backend
Este repositorio contiene el backend de la aplicación de administracion de cine. Está construido con Java 17 y Spring Boot, y utiliza H2 como base de datos de prueba. Proporciona funcionalidad de API REST para la gestión de reservas de butacas en el cine.




## Requisitos Previos
Antes de comenzar, asegurarse de tener los siguientes requisitos instalados:
Java 17
H2: Se utiliza H2 como base de datos de prueba. No es necesario instalarla por separado, ya que Spring Boot configurará una instancia en memoria para pruebas.

## Instalación

1. Clona el repositorio:

```bash
  git clone https://github.com/TeslaXZ/cinema-booking
```
2. Navega al directorio del proyecto:
```bash
    cd cinema-booking
```
3. Instala las dependencias:
```bash
    mvn clean install
```

## Uso

1. Inicia el servidor utilizando Maven:
   ```bash
    mvn spring-boot:run

2. Accede a la aplicación:El servidor debería estar ejecutándose en http://localhost:8080. Puedes explorar las API y la aplicación desde esta dirección.

3. Explora la API:Puedes acceder a la documentación de la API utilizando Swagger en http://localhost:8080/swagger-ui.html.
   

   # GuayaCine-FrontEnt
El frontEnd está construido con React y utiliza Vite para el entorno de desarrollo. Proporciona una interfaz de usuario para la gestión de reservas de butacas en el cine. El frontend interactúa con el backend a través de API REST.


## Requisitos Previos

Antes de comenzar, asegúrate de tener los siguientes requisitos instalados:

Node.js: Asegúrate de tener Node.js instalado en tu sistema.

npm o yarn: Para gestionar dependencias e iniciar la aplicación.

Vite: El frontend utiliza Vite para el entorno de desarrollo. Puedes instalarlo siguiendo las instrucciones en el sitio web de Vite(https://vitejs.dev/).

## Instalación

1. Configura la URL base del backend:

    Abre el archivo de configuración (ApiConfig.js).

    Ajusta la URL base para que coincida con la dirección del backend en caso de ser necesario.

2. Inicia la aplicación:
```bash
    npm run dev
```
3. Accede a la aplicación:La aplicación debería estar ejecutándose en http://localhost:5173 por defecto (o en otro puerto si se especifica).
