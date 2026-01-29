# Dashboard de Análisis de Ahorro Energético ⚡

Aplicación Java Swing para análisis de datos de ahorro energético del sistema SGC.

## ✨ Características

- 15,899 registros desde CSV
- Dashboard con gráficos (JFreeChart)
- Machine Learning (Weka)
- 4 pestañas: Gráficos, Datos, Predicciones, Resumen
- **Dockerizado con Java 11**

## 🚀 Ejecución

### Requisitos
- Docker 20.10+ (tienes versión 29.2.0 ✅)
- Linux con X11
- **No requiere docker-compose**

### Comandos

```bash
cd /home/robot/Documentos/Java/P1/Sistema-de-Gestion-CAE
./run.sh
```

Usa comandos nativos de Docker:
- `docker build` para construir la imagen
- `docker run` para ejecutar el contenedor

## 🐳 Comandos Manuales

Si prefieres ejecutar manualmente:

```bash
# Construir imagen
docker build -t sgc-dashboard:latest .

# Ejecutar aplicación
xhost +local:docker
docker run --rm \
    --name sgc-dashboard \
    -e DISPLAY=$DISPLAY \
    -v /tmp/.X11-unix:/tmp/.X11-unix:rw \
    -v $(pwd)/data:/app/data:ro \
    --network host \
    sgc-dashboard:latest
```

## 📊 Uso de la Aplicación

4 pestañas disponibles:

1. **Gráficos** - Visualizaciones con JFreeChart
2. **Datos** - Tabla con 15,899 registros
3. **Predicciones** - ML con Weka
4. **Resumen** - Métricas clave

## 🐛 Solución de Problemas

### GUI no aparece
```bash
xhost +local:docker
./run.sh
```

### Permisos Docker
```bash
sudo usermod -aG docker $USER
# Reiniciar sesión
```

### Ver logs
```bash
docker logs sgc-dashboard
```

### Limpiar imágenes
```bash
docker rmi sgc-dashboard:latest
```

## 🔧 Tecnologías

- Java 11
- Maven 3.8.6
- Weka 3.8.6
- JFreeChart 1.5.3
- Docker (sin compose)

---

**Listo para ejecutar con `./run.sh` 🚀**
