# BD2
Sistema de notas
## Requerimientos
- Tener Docker instalado
- Tener Docker abierto
## Ejecución (con docker)
1) Clonar el repositorio a una carpeta
2) Abrir una terminal, ir a la carpeta del proyecto clonado y usar el siguiente comando (este comando crea las imagenes y levanta los containers del servidor y la base de datos)
- Windows: `./run.bat`
- Mac: `./run.sh`
3) Ejemplos para comunicarse con la API:
- Postman: hacer requests con direccion "http://localhost:8080"
- Swagger: entrando a "http://localhost:8080/swagger" en cualquier browser de internet
