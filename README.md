# Tarot

Aplicacion movil Android para realizar lecturas de tarot con tres modos principales: carta del dia, respuesta de si o no y lectura para parejas.

El proyecto esta desarrollado en Java, usa layouts XML con una estetica oscura/mistica y obtiene la informacion de las cartas desde Firebase Realtime Database.

## Capturas reales de la app

Las siguientes capturas muestran la interfaz visual actual usando los recursos incluidos en `app/src/main/res`.

| Inicio | Carta del dia |
| --- | --- |
| ![Pantalla de inicio](docs/screenshots/home.png) | ![Carta del dia](docs/screenshots/carta-del-dia.png) |

| Si o No | Tarot de parejas |
| --- | --- |
| ![Pantalla Si o No](docs/screenshots/si-o-no.png) | ![Tarot de parejas](docs/screenshots/tarot-parejas.png) |

## Funcionalidades

- Lectura de carta del dia.
- Consulta rapida de Si o No.
- Lectura de tarot para parejas con dos cartas.
- Interpretacion de cartas derechas e invertidas.
- Imagenes de cartas almacenadas como recursos Android.
- Integracion con Firebase Realtime Database para cargar informacion de las cartas.

## Tecnologias

- Android nativo
- Java
- XML layouts
- Gradle
- Firebase Realtime Database
- AndroidX AppCompat, CardView y ConstraintLayout

## Estructura principal

```text
app/
  src/main/java/com/example/evaluacion4charlottegabriel/
    MainActivity.java
    CartaDelDia.java
    SiYNo.java
    tarotPareja.java
    Dao/
      Carta.java
      DaoCarta.java
  src/main/res/
    layout/
    drawable/
    values/
docs/
  screenshots/
```

## Pantallas

### Inicio

La pantalla principal presenta las tres lecturas disponibles y sirve como punto de navegacion hacia cada experiencia.

### Carta del dia

Muestra una carta seleccionada para el dia, su imagen y la interpretacion correspondiente. La actividad recibe la carta mediante `Intent` y renderiza titulo, descripcion e imagen.

### Si o No

Interpreta una carta como respuesta afirmativa, negativa o intermedia segun listas internas de cartas favorables y desfavorables.

### Tarot de parejas

Muestra dos cartas: una para la persona consultante y otra para su pareja. Cada carta incluye imagen, titulo e interpretacion amorosa.

## Requisitos

- Android Studio
- SDK Android instalado
- JDK compatible con Gradle 7.4 / Android Gradle Plugin 7.4.2
- Archivo `app/google-services.json` configurado para Firebase

## Compilacion

Desde la raiz del proyecto:

```powershell
.\gradlew.bat assembleDebug
```

La APK debug se genera en:

```text
app/build/outputs/apk/debug/
```

## Notas de desarrollo

- El paquete principal es `com.example.evaluacion4charlottegabriel`.
- Las cartas se cargan desde recursos `drawable` con nombres como `carta0`, `carta1`, `carta2`, etc.
- Los colores principales estan definidos en `app/src/main/res/values/colors.xml`.
- Los fondos y botones usan drawables XML en `app/src/main/res/drawable`.

## Autores

Charlotte Rodriguez y Gabriel Barrientos.
