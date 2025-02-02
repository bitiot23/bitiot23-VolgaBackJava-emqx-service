#!/bin/bash

# 1. Renombrando application.yml para usar el perfil stage
echo "Renombrando propiedades para compilar..."
cp ./src/main/resources/application.yml ./src/main/resources/application-tmp.yml
cp ./src/main/resources/application-stage.yml ./src/main/resources/application.yml

# 2. Construyendo el JAR con Maven
echo "Compilando el proyecto con Maven..."
mvn clean package -DskipTests

# 3. Restaurar application.yml original
echo "Restaurando archivo de propiedades original..."
mv ./src/main/resources/application-tmp.yml ./src/main/resources/application.yml

# 4. Eliminar archivo temporal
echo "Eliminando archivo temporal..."
rm -f ./src/main/resources/application-tmp.yml

# 5. Construir la imagen Docker
echo "Construyendo la imagen Docker..."
docker build -t volga/emqx-to-rabbit .

# 6. Etiquetar la imagen Docker para el entorno stage
echo "Cambiando el TAG de la imagen a STAGE..."
docker tag volga/emqx-to-rabbit volga/emqx-to-rabbit:STAGE

# 7. Subir la imagen a Docker Hub
echo "Haciendo PUSH de la imagen Docker a Docker Hub..."
docker push volga/emqx-to-rabbit:STAGE

# Finalización
echo "El proceso de compilación y despliegue ha finalizado."
read -p "Presiona cualquier tecla para continuar..."