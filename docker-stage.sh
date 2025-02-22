#!/bin/bash

#Variables
APP_NAME="emqx-to-rabbit"
DOCKER_TAG="bitiot21/$APP_NAME"
PROFILE="stage"

echo "Iniciando el proceso de Docker..."
echo "Docker Tag: $DOCKER_TAG"
echo "Perfil: $PROFILE"

# 1. Renombrando application.yml para usar el perfil $PROFILE
echo "Paso 1. Renombrando propiedades para compilar..."
cp ./src/main/resources/application.yml ./src/main/resources/application-tmp.yml
cp ./src/main/resources/application-$PROFILE.yml ./src/main/resources/application.yml
sleep 5

# 2. Construyendo el JAR con Maven
echo "Paso 2. Compilando el proyecto con Maven..."
mvn clean package -DskipTests || { echo "Error en la compilación con Maven"; exit 1; }
sleep 5

# 3. Restaurar application.yml original
echo "Paso 3. Restaurando archivo de propiedades original..."
mv ./src/main/resources/application-tmp.yml ./src/main/resources/application.yml
sleep 5

# 4. Eliminar archivo temporal
echo "Paso 4. Eliminando archivo temporal..."
rm -f ./src/main/resources/application-tmp.yml
sleep 5

# 5. Renombrar el JAR generado para que coincida con el Dockerfile
#echo "Paso 5. Renombrando el JAR para la imagen Docker..."
#mv target/volga3-login-0.0.1-SNAPSHOT.jar target/volga3-login.jar || { echo "Error al renombrar el JAR"; exit 1; }
#sleep 5

# 6. Construir la imagen Docker
echo "Paso 6. Construyendo la imagen Docker..."
docker build -t $DOCKER_TAG . || { echo "Error al construir la imagen Docker"; exit 1; }
sleep 5

# 7. Etiquetar la imagen Docker para el entorno $PROFILE
echo "Paso 7. Cambiando el TAG de la imagen a $PROFILE..."
docker tag $DOCKER_TAG $DOCKER_TAG:$PROFILE || { echo "Error al etiquetar la imagen"; exit 1; }
sleep 5

# 8. Subir la imagen a Docker Hub
echo " Paso 8. Haciendo PUSH de la imagen Docker a Docker Hub..."
docker push $DOCKER_TAG:$PROFILE || { echo "Error al subir la imagen a Docker Hub"; exit 1; }
sleep 120

# Finalización
echo "El proceso de compilación y despliegue de $APP_NAME ha finalizado exitosamente."
sleep 10
read -p "Presiona cualquier tecla para continuar..."

