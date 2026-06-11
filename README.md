# Tarot Kawaii Dreams

Tarot Kawaii Dreams es una app Android nativa de tarot ilustrado con estetica kawaii, acuarela pastel y una interfaz tipo album magico. La experiencia esta centrada en lecturas suaves, cartas coleccionables, familias de arcanos y una mascota guia.

La app esta construida en Java con vistas personalizadas. Usa los assets reales del proyecto desde `assets/tarot_cards` y mantiene una navegacion inferior consistente entre las pantallas principales.

## Capturas

| Inicio | Coleccion |
| --- | --- |
| ![Inicio](docs/screenshots/home.png) | ![Coleccion](docs/screenshots/coleccion.png) |

| Gotitas | Carta del Dia |
| --- | --- |
| ![Gotitas](docs/screenshots/coleccion-gotitas.png) | ![Carta del Dia](docs/screenshots/carta-dia.png) |

| Si o No | Mascota |
| --- | --- |
| ![Si o No](docs/screenshots/si-o-no.png) | ![Mascota](docs/screenshots/mascota.png) |

| Ajustes |
| --- |
| ![Ajustes](docs/screenshots/ajustes.png) |

## Flujo Principal

- **Inicio:** saludo, unicornio hero, acceso a Carta del Dia y grid 2x2 para lecturas, coleccion y mascota.
- **Coleccion:** indice de familias con Gotitas, Chispas, Estrellas, Brotes y Unicornios.
- **Familias:** cada familia abre un album con cartas desbloqueadas y cartas dormidas.
- **Carta del Dia:** carta cerrada con revelacion al tocar y guardado en el album.
- **Si o No:** lectura rapida con respuesta y explicacion.
- **Tarot de Parejas:** dos cartas y una interpretacion conjunta.
- **Mascota:** panel de estado y preferencias de compania.
- **Ajustes:** preferencias visuales y de experiencia.

## Sistema Visual

El lenguaje visual busca una sensacion premium, suave y coleccionable:

- fondo acuarela pastel compartido en toda la app
- contenedores crema o blanco rosado
- bordes dorados finos
- sombras ligeras y difusas
- esquinas redondeadas
- botones con gradiente pastel
- texto morado elegante
- iconos kawaii acuarela
- bottom nav tipo pildora con item activo

## Pantallas Incluidas

- `MainActivity` para Inicio
- `CollectionActivity` para indice de coleccion y albums por familia
- `CartaDelDia` para lectura diaria
- `SiYNo` para consulta rapida
- `tarotPareja` para lectura de parejas
- `MascotaActivity` para la guia kawaii
- `SettingsActivity` para ajustes
- `CardDetailActivity` para detalle de cartas

## Estructura

```text
app/
  src/main/java/com/example/evaluacion4charlottegabriel/
    MainActivity.java
    CartaDelDia.java
    SiYNo.java
    tarotPareja.java
    CollectionActivity.java
    CardDetailActivity.java
    MascotaActivity.java
    SettingsActivity.java
    TarotNavigator.java
    Dao/
    ui/
      DreamBackground.java
      DreamBottomNav.java
      DreamButton.java
      DreamPanelDrawable.java
      TarotScaffold.java
      CollectionCard.java
      TarotCardWidget.java
assets/
  tarot_cards/
docs/
  screenshots/
```

## Compilacion

Desde la raiz del proyecto:

```powershell
.\gradlew.bat assembleDebug
```

APK generada:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Notas

- Las ilustraciones se toman desde los assets existentes.
- La pantalla Coleccion siempre inicia en el indice de familias.
- El grid de cartas aparece solo despues de tocar una familia.
- Los botones, toggles, cards y bottom nav comparten el mismo estilo pastel/dorado.

## Autores

Charlotte Rodriguez y Gabriel Barrientos.
