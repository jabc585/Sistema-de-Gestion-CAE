#!/bin/bash

# Script para ejecutar el Dashboard de Ahorro Energético
# con Docker 29.2.0 usando buildx plugin

set -e

echo "==============================================="
echo "Dashboard de Ahorro Energético - SGC"
echo "==============================================="

# Docker plugin paths
BUILDX="/usr/libexec/docker/cli-plugins/docker-buildx"
DOCKER_CMD="docker"

# Verificar que Docker está instalado
if ! command -v docker &> /dev/null; then
    echo "❌ Error: Docker no está instalado."
    exit 1
fi

# Verificar que buildx plugin existe
if [ ! -f "$BUILDX" ]; then
    echo "❌ Error: Plugin buildx no encontrado en $BUILDX"
    exit 1
fi

echo "✅ Docker version: $(docker version --format '{{.Client.Version}}')"
echo "✅ Buildx plugin encontrado"
echo ""

# Permitir conexiones X11 desde Docker
echo "Configurando permisos X11..."
xhost +local:docker > /dev/null 2>&1 || true

# Construir imagen Docker usando buildx
echo ""
echo "Construyendo imagen con buildx..."
echo "Esto puede tardar varios minutos la primera vez..."
echo ""

$BUILDX build \
    --network=host \
    --add-host=repo.maven.apache.org:104.18.18.12 \
    --add-host=repo1.maven.org:151.101.8.209 \
    -t sgc-dashboard:latest .

if [ $? -ne 0 ]; then
    echo ""
    echo "❌ Error al construir la imagen."
    exit 1
fi

# Ejecutar aplicación
echo ""
echo "✅ Imagen construida exitosamente"
echo ""
echo "Ejecutando aplicación..."
echo "Espera a que aparezca la ventana del dashboard..."
echo ""

$DOCKER_CMD run --rm \
    --name sgc-dashboard \
    -e DISPLAY=${DISPLAY:-:0} \
    -v /tmp/.X11-unix:/tmp/.X11-unix:rw \
    -v "$(pwd)/data:/app/data:ro" \
    --network host \
    sgc-dashboard:latest

# Limpiar permisos X11 al salir
echo ""
echo "Cerrando aplicación..."
xhost -local:docker > /dev/null 2>&1 || true

echo "✅ Aplicación cerrada correctamente"
