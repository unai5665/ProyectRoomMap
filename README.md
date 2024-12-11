# Proyecto de Mapa con Room y Marcadores

Este proyecto es una aplicación de Android que utiliza una base de datos local (Room) para almacenar y gestionar marcadores en un mapa interactivo. Los usuarios pueden visualizar diferentes tipos de ubicaciones (como restaurantes, cafeterías, parques y museos) en el mapa, cada uno representado por un marcador.

## Características Principales
- **Base de Datos Local con Room**: Almacena información sobre los marcadores, incluyendo título, coordenadas (latitud y longitud) y tipo.
- **Mapas Interactivos**: Muestra los marcadores en un mapa interactivo utilizando Google Maps.
- **Gestión de Tipos de Marcadores**: Los marcadores se clasifican por tipos, como restaurantes, cafés, parques y museos.
- **Inicialización Automática**: Inserta automáticamente marcadores y tipos iniciales si la base de datos está vacía.

## Requisitos
- **Android Studio** (versión mínima recomendada: Arctic Fox o superior).
- **API de Google Maps**: Debes configurar tu clave de API de Google Maps en el archivo `google_maps_api.xml`.
- **Dispositivo con Android 6.0 (API 23)** o superior.

## Configuración del Proyecto

### Clave de Google Maps
1. Obtén una clave de API de Google Maps desde la [Consola de Google Cloud](https://console.cloud.google.com/).
2. Añade la clave en el archivo `google_maps_api.xml`:
   ```xml
   <string name="google_maps_key" translatable="false">TU_CLAVE_DE_API</string>
   ```

### Dependencias
Asegúrate de que las siguientes dependencias estén en tu archivo `build.gradle`:
```gradle
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0"
implementation "androidx.room:room-runtime:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"
implementation "com.google.android.gms:play-services-maps:18.0.2"
```




## Capturas de Pantalla

### Mapa con Marcadores
![Mapa con marcadores](assets/map_screenshot.png)

## Créditos
Proyecto desarrollado por [Unai Perez Toscano].
