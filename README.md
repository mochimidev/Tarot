# Dream Sprouts Tarot

Dream Sprouts Tarot es una aplicacion Android nativa de tarot con una estetica premium, magica y kawaii. La experiencia actual esta inspirada en colecciones de cartas digitales, con una interfaz pastel, ilustrada y pensada para lectura emocional.

El proyecto esta hecho en Java con layouts y vistas custom, y toma la informacion de las cartas desde Firebase Realtime Database.

## Capturas actuales

Estas son las capturas reales del estado actual de la app:

| Inicio | Carta del dia |
| --- | --- |
| ![Inicio](docs/screenshots/home.png) | ![Carta del dia](docs/screenshots/carta-del-dia.png) |

| Si o No | Tarot de parejas |
| --- | --- |
| ![Si o No](docs/screenshots/si-o-no.png) | ![Tarot de parejas](docs/screenshots/tarot-parejas.png) |

## Que incluye esta version

- Home con hero animado, estrellas, nubes y accesos ilustrados.
- Carta del dia con revelacion visual y boton para guardar en coleccion.
- Consulta Si o No con carta grande y resultado claro.
- Tarot de parejas con dos cartas y conexion visual entre ambas.
- Coleccion con album de cartas, rarezas y progreso.
- Mi Mascota con un espiritu evolutivo que sube de nivel.
- Ajustes con controles simples para sonido y animaciones.

## Tecnologias

- Android nativo
- Java
- XML y vistas custom
- Gradle
- Firebase Realtime Database
- AndroidX AppCompat, ConstraintLayout y Material Components

## Estructura principal

```text
app/
  src/main/java/com/example/evaluacion4charlottegabriel/
    MainActivity.java
    CartaDelDia.java
    SiYNo.java
    tarotPareja.java
    CollectionActivity.java
    SpiritPetActivity.java
    SettingsActivity.java
    Dao/
      Carta.java
      DaoCarta.java
    ui/
      DreamBackground.java
      DreamButton.java
      TarotScaffold.java
      AnimatedRevealCard.java
      TarotCardWidget.java
      CollectionCard.java
      SpiritPetWidget.java
  src/main/res/
    layout/
    drawable/
    values/
docs/
  screenshots/
```

## Pantallas

### Inicio

La pantalla principal presenta las experiencias disponibles en formato de carta ilustrada, con acceso directo a cada modo.

### Carta del dia

Muestra una carta gigante, su revelacion y el significado correspondiente. La carta se puede guardar en la coleccion.

### Si o No

Interpreta una carta como respuesta afirmativa, negativa o intermedia segun listas internas de cartas favorables y desfavorables.

### Tarot de parejas

Muestra dos cartas grandes para analizar la energia entre dos personas.

### Coleccion

Permite ver el album completo, el progreso y el estado de cada carta.

### Mi Mascota

Presenta la mascota guia de la app y su nivel, ligado a la coleccion del usuario.

## Compilacion

Desde la raiz del proyecto:

```powershell
.\gradlew.bat assembleDebug
```

La APK debug se genera en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Nota tecnica

El proyecto fue actualizado a Gradle 8.7 y Android Gradle Plugin 8.2 para poder compilar con el entorno local actual.

## Autores

Charlotte Rodriguez y Gabriel Barrientos.
