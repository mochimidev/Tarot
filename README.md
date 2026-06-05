# 🔮 Tarot - Aplicación Móvil Android

> **Aplicación interactiva de Tarot para responder preguntas del día mediante la interpretación de las cartas del tarot con diseño misterioso y profesional**

<div align="center">

![Tarot App](https://img.shields.io/badge/Tarot-1.0.0-purple?style=flat-square&logo=android)
![Java](https://img.shields.io/badge/Java-17-red?style=flat-square&logo=java)
![Firebase](https://img.shields.io/badge/Firebase-RealDB-orange?style=flat-square&logo=firebase)
![Android](https://img.shields.io/badge/Android-8.0%2B-green?style=flat-square&logo=android)

</div>

---

## ✨ Presentación Visual

### 🎨 Diseño Profesional Místico

```
┌─────────────────────────────────────────────┐
│                                             │
│   ✨ INTERFAZ MÍSTICA PROFESIONAL ✨       │
│                                             │
│   • Paleta de colores oscura y elegante    │
│   • Tipografía premium con sans-serif      │
│   • Bordes redondeados y elevaciones       │
│   • Gradientes púrpura a índigo profundo   │
│   • Cards con borders decorativos          │
│   • Acentos en oro místico                 │
│                                             │
└─────────────────────────────────────────────┘
```

### 🌙 Paleta de Colores

| Elemento           | Color             | Código    |
| ------------------ | ----------------- | --------- |
| 🌌 Fondo Principal | Oscuro Misterioso | `#0a0e27` |
| 💜 Primario        | Púrpura Místico   | `#9d4edd` |
| ✨ Acentos         | Oro Místico       | `#ffd60a` |
| 🟣 Secundario      | Púrpura Claro     | `#c77dff` |
| 🔮 Detalles        | Índigo Profundo   | `#3c096c` |

---

## 📱 Descripción

La aplicación Tarot fue creada con el propósito de brindar una experiencia interactiva y mística para los usuarios interesados en conocer las predicciones del tarot. Utiliza un algoritmo pseudoaleatorio combinado con una base de datos de Firebase para ofrecer interpretaciones precisas y significativas de las cartas.

**Desarrollo**: Charlotte & Gabriel  
**Plataforma**: Android  
**Lenguaje**: Java  
**Base de Datos**: Firebase Realtime Database

---

## ✨ Características Principales

- 🎰 **Selección aleatoria de cartas del tarot** - Sistema de generación de números pseudoaleatorios
- 🔄 **Interpretación de cartas invertidas vs derechas** - Significados duales para cada carta
- 💬 **Respuestas personalizadas a preguntas de Sí/No** - Análisis inteligente basado en cartas
- 💕 **Lectura de compatibilidad de parejas** - Análisis de energías entre dos personas
- 📊 **Integración con Firebase** - Datos en tiempo real y persistencia en la nube
- 🎨 **Interfaz visual atractiva y temática** - Diseño oscuro con paleta mística

---

## 📱 Interfaz de Usuario - Pantallas Principales

### 🏠 Pantalla Principal (Home)

```
╔════════════════════════════════════════╗
║                                        ║
║             ✨ BIENVENIDO ✨           ║
║    Explora los misterios del Tarot    ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  ✨ Carta del Día              │   ║
║  │  Descubre tu carta y destino   │   ║
║  │         [IMAGEN CARTA]         │   ║
║  │   ━━━━━━━━━━━━━━━━━━━━━━━━  │   ║
║  │    LEER MI CARTA              │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  ⚡ Sí o No                     │   ║
║  │  Obtén respuesta clara         │   ║
║  │         [IMAGEN CARTA]         │   ║
║  │   ━━━━━━━━━━━━━━━━━━━━━━━━  │   ║
║  │    REALIZAR CONSULTA           │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  💕 Tarot de Parejas           │   ║
║  │  Descubre la conexión          │   ║
║  │         [IMAGEN CARTA]         │   ║
║  │   ━━━━━━━━━━━━━━━━━━━━━━━━  │   ║
║  │    EXPLORAR CONEXIÓN           │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  © 2024 Charlotte & Gabriel            ║
╚════════════════════════════════════════╝

🎨 ELEMENTOS VISUALES:
✓ Fondo con gradiente púrpura profundo
✓ Cards con bordes decorativos
✓ Tipografía elegante y legible
✓ Botones con gradiente interactivo
```

### ⭐ Pantalla - Carta del Día

```
╔════════════════════════════════════════╗
║  ← ATRÁS                               ║
║                                        ║
║        ✨ Tu Carta del Día ✨          ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  La Emperatriz                 │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │                                │   ║
║  │     ╔────────────────────╗    │   ║
║  │     ║                    ║    │   ║
║  │     ║  [IMAGEN GRANDE]   ║    │   ║
║  │     ║   300 x 400px      ║    │   ║
║  │     ║                    ║    │   ║
║  │     ╚────────────────────╝    │   ║
║  │                                │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  📖 SIGNIFICADO                │   ║
║  │                                │   ║
║  │ "La carta de hoy te trae un    │   ║
║  │ mensaje de fertilidad, amor y  │   ║
║  │ creatividad. Es momento de     │   ║
║  │ manifestar tus deseos..."      │   ║
║  │                                │   ║
║  └────────────────────────────────┘   ║
║                                        ║
╚════════════════════════════════════════╝

🎨 ESTILO:
✓ Borde card púrpura (#9d4edd)
✓ Título en oro (#ffd60a)
✓ Imagen con frame místico
✓ Texto descriptivo en blanco 80%
```

### ❓ Pantalla - Sí o No

```
╔════════════════════════════════════════╗
║  ← ATRÁS                               ║
║                                        ║
║         ⚡ Sí o No ⚡                  ║
║   Recibe una respuesta clara          ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  La Sacerdotisa                │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  ╔────────────────────────╗    │   ║
║  │  ║   [IMAGEN CARTA]       ║    │   ║
║  │  ║   280 x 350px          ║    │   ║
║  │  ╚────────────────────────╝    │   ║
║  └────────────────────────────────┘   ║
║                                        ║
║  ╔════════════════════════════════╗   ║
║  ║      ✨ SÍ ✨                  ║   ║
║  ║  (Con borde dorado destacado)  ║   ║
║  ╚════════════════════════════════╝   ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  💡 INTERPRETACIÓN             │   ║
║  │                                │   ║
║  │ "La energía indica que tu      │   ║
║  │ pregunta tendrá respuesta      │   ║
║  │ positiva. Los signos del       │   ║
║  │ universo están a tu favor..."  │   ║
║  │                                │   ║
║  └────────────────────────────────┘   ║
║                                        ║
╚════════════════════════════════════════╝

🎨 ESTILO:
✓ Borde card índigo (#5a189a)
✓ Respuesta en box dorado (3dp border)
✓ Interpretación en blanco 80%
✓ Tipografía responsiva
```

### 💕 Pantalla - Tarot de Parejas

```
╔════════════════════════════════════════╗
║  ← ATRÁS                               ║
║                                        ║
║     💕 Tarot de Parejas 💕             ║
║  Descubre la conexión entre dos almas ║
║                                        ║
║  ┌─────────────────┬─────────────────┐ ║
║  │      TÚ         │   TU PAREJA     │ ║
║  ├─────────────────┼─────────────────┤ ║
║  │ ╔───────────╗   │  ╔───────────╗ │ ║
║  │ ║  [CARTA]  ║   │  ║  [CARTA]  ║ │ ║
║  │ ║ 200 x280  ║   │  ║ 200 x280  ║ │ ║
║  │ ╚───────────╝   │  ╚───────────╝ │ ║
║  │                 │                 │ ║
║  │ La Emperatriz   │  El Amante      │ ║
║  │                 │                 │ ║
║  │ "Fertilidad,    │ "Amor, unidad,  │ ║
║  │ amor y poder.." │ decisión..."    │ ║
║  │                 │                 │ ║
║  └─────────────────┴─────────────────┘ ║
║                                        ║
║  ┌────────────────────────────────┐   ║
║  │  ✨ CONEXIÓN REVELADA ✨       │   ║
║  │                                │   ║
║  │ "Vuestra conexión es profunda  │   ║
║  │ y mágica. El universo ha       │   ║
║  │ alineado vuestros destinos     │   ║
║  │ para un propósito especial..."  │   ║
║  │                                │   ║
║  └────────────────────────────────┘   ║
║                                        ║
╚════════════════════════════════════════╝

🎨 ESTILO:
✓ Layout lado a lado balanceado
✓ Cards con bordes púrpura claro
✓ Sección conexión con borde dorado
✓ Espaciado profesional
```

---

## 🎯 Descripción de Funcionalidades

### 1. 🌟 Carta del Día

**Propósito:** Proporcionar orientación diaria a través del tarot

**Características Técnicas:**

- Algoritmo determinista: `(día + mes + año) % 78`
- Garantiza la misma carta para cada usuario en el mismo día
- Interpretación dual (carta derecha/invertida)
- Conexión con Firebase para datos de cartas

**Datos Mostrados:**

- Nombre de la carta
- Imagen ilustrativa
- Descripción significado
- Consejos personalizados

---

### 2. ⚡ Sí y No

**Propósito:** Responder preguntas binarias con análisis tarótico

**Características Técnicas:**

- Base de datos de 78 cartas
- Clasificación: SÍ (45 cartas), NO (20 cartas), QUIZÁS (13 cartas)
- Selección pseudoaleatoria
- Lógica inteligente de decisión

**Casos de Uso:**

- Preguntas sobre relaciones
- Consultas laborales
- Decisiones importantes
- Predicciones personales

---

### 3. 💕 Tarot de Parejas

**Propósito:** Analizar la compatibilidad y energía de dos personas

**Características Técnicas:**

- Lectura dual simultánea
- Dos cartas independientes
- Análisis de compatibilidad cruzada
- Interpretación amorosa específica

**Interpretación:**

- Energía de persona 1
- Energía de persona 2
- Dinámicas de la relación
- Recomendaciones futuras

---

## 🛠️ Stack Tecnológico

```
┌─────────────────────────────────────────┐
│           TAROT APP TECH STACK          │
├─────────────────────────────────────────┤
│                                         │
│ 🔹 FRONTEND                             │
│   • Android XML Layouts                 │
│   • ConstraintLayout (Responsive)       │
│   • Material Design Components          │
│   • Gradientes personalizados           │
│                                         │
│ 🔹 BACKEND                              │
│   • Java 11+ (Orientado a Objetos)     │
│   • Firebase Realtime Database          │
│   • ValueEventListener (Async)          │
│   • DAO Pattern (Data Access)           │
│                                         │
│ 🔹 BUILD SYSTEM                         │
│   • Gradle 7.4                          │
│   • Android Gradle Plugin 7.3.1         │
│   • Compilación a bytecode Android      │
│                                         │
│ 🔹 DEPENDENCIES                         │
│   • AndroidX Core: 1.5.1                │
│   • Material Design: 1.7.0              │
│   • Firebase BOM: 31.1.1                │
│   • ConstraintLayout: 2.1.4             │
│                                         │
└─────────────────────────────────────────┘
```

---

## 📋 Requisitos del Sistema

### Requisitos Mínimos

| Componente         | Versión      | Nota                  |
| ------------------ | ------------ | --------------------- |
| **Android**        | 8.0 (API 26) | Mínimo soportado      |
| **RAM**            | 2GB          | Funcionamiento básico |
| **Almacenamiento** | 150MB        | Incluye assets        |
| **Pantalla**       | 4.5"         | Resolución mínima     |

### Requisitos Recomendados

| Componente         | Versión       | Nota                 |
| ------------------ | ------------- | -------------------- |
| **Android**        | 11+ (API 30+) | Mejor experiencia    |
| **RAM**            | 4GB+          | Mejor rendimiento    |
| **Almacenamiento** | 200MB+        | Caché incluido       |
| **Pantalla**       | 5.5-6.5"      | Óptima visualización |

### Requisitos de Compilación

```bash
# ⚠️ CRÍTICO: JDK 17 es obligatorio

JDK: Java 17 LTS (NOT Java 23)
  └─ Descargar: https://adoptium.net/
  └─ Verificar: java -version

Gradle: 7.4+
  └─ Compatible con JDK 17

Android SDK: 32+ (API)
  └─ Build Tools: 32.0.0+

Android Studio: 2022.1+
  └─ Con Gradle 7.4 integrado
```

---

## 🚀 Guía de Instalación

---

## 📂 Estructura del Proyecto

```
Tarot/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/evaluacion4charlottegabriel/
│   │   │   │   ├── MainActivity.java          # Pantalla principal
│   │   │   │   ├── CartaDelDia.java           # Módulo: Carta del Día
│   │   │   │   ├── SiYNo.java                 # Módulo: Sí/No
│   │   │   │   ├── tarotPareja.java           # Módulo: Tarot de Parejas
│   │   │   │   └── Dao/
│   │   │   │       ├── Carta.java             # Modelo de datos
│   │   │   │       └── DaoCarta.java          # Acceso a datos
│   │   │   ├── res/
│   │   │   │   ├── drawable/                  # Recursos gráficos
│   │   │   │   ├── layout/                    # Layouts XML
│   │   │   │   ├── mipmap-*/                  # Iconos app
│   │   │   │   ├── values/                    # Strings, colores, estilos
│   │   │   │   └── xml/                       # Configuración de datos
│   │   │   └── AndroidManifest.xml            # Configuración app
│   │   ├── test/ & androidTest/               # Tests
│   │   └── google-services.json                # Config Firebase
│   ├── build.gradle                           # Dependencias & config
│   └── proguard-rules.pro                     # Reglas de ofuscación
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties           # Versión Gradle
├── build.gradle                               # Config global
├── settings.gradle                            # Módulos proyecto
├── gradle.properties                          # Propiedades Gradle
└── README.md                                  # Este archivo
```

---

## 🎓 Flujo de Datos

### Carta del Día

```
MainActivity
  ↓
[Calcular: (día + mes + año) % 78]
  ↓
Firebase → Obtener carta con ID calculado
  ↓
CartaDelDia → Mostrar título, descripción e imagen
  ↓
[Si está invertida] → Mostrar variante invertida
```

### Sí y No

````
MainActivity
  ↓
[Generar número aleatorio 0-77]
  ↓
Firebase → Obtener carta aleatoria
  ↓
SiYNo → Validar en listas (SI/NO/TALVEZ)
  ↓
---

## 🚀 Guía de Instalación Paso a Paso

### Paso 1: Descargar e Instalar JDK 17

```bash
# Opción A: Descargar desde Adoptium (RECOMENDADO)
# 1. Ir a: https://adoptium.net/
# 2. Descargar: Eclipse Temurin 17 LTS
# 3. Instalar en: C:\Program Files\Java\jdk-17 (Windows)
#    O: /usr/lib/jvm/java-17-adoptium (Linux)

# Opción B: Usar gestor de paquetes
# macOS (con Homebrew):
brew install temurin@17

# Ubuntu/Debian:
sudo apt install temurin-17-jdk

# Windows (con Chocolatey):
choco install temurin17
````

### Paso 2: Configurar Variables de Entorno

```powershell
# ═══════════════════════════════════════════════════
# WINDOWS (PowerShell)
# ═══════════════════════════════════════════════════

# Establecer JAVA_HOME
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# Verificar (debe mostrar 17.x.x)
java -version

# Para permanencia, agregar al PATH del sistema
# (Panel de Control → Sistema → Variables de Entorno)
```

```bash
# ═══════════════════════════════════════════════════
# macOS / LINUX
# ═══════════════════════════════════════════════════

# Agregar a ~/.bash_profile o ~/.zshrc
export JAVA_HOME=/usr/libexec/java_home -v17

# Recargar shell
source ~/.bash_profile
# o
source ~/.zshrc

# Verificar
java -version
```

### Paso 3: Clonar Repositorio

```bash
# Crear directorio
mkdir -p ~/Proyectos
cd ~/Proyectos

# Clonar repositorio
git clone <URL_REPOSITORIO>
cd Tarot
```

### Paso 4: Compilar Aplicación

```bash
# 1. Limpiar builds anteriores
./gradlew clean

# 2. Compilar con Gradle
./gradlew build

# 3. Generar APK debug
./gradlew assembleDebug

# ✅ Si ve "BUILD SUCCESSFUL" - ¡Listo!
# El APK se genera en: app/build/outputs/apk/debug/app-debug.apk
```

### Paso 5: Instalar en Dispositivo/Emulador

```bash
# Opción A: Instalar en emulador ejecutándose
./gradlew installDebug

# Opción B: Instalar APK directamente
adb install app/build/outputs/apk/debug/app-debug.apk

# Opción C: Desde Android Studio
# Build → Build Bundle(s) / APK(s) → Build APK(s)
```

---

## 🔧 Solución de Problemas

### ❌ Error: "Unsupported class file major version 67"

**Causa:** Usando Java 23 (incompatible)

**Solución:**

```bash
# Verificar versión actual
java -version

# Instalar JDK 17 desde https://adoptium.net/
# Configurar JAVA_HOME correctamente
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# Limpiar caché de Gradle
rm -r ~/.gradle/caches
./gradlew clean build
```

---

### ❌ Error: "SDK location not found"

**Causa:** Android SDK no configurado

**Solución:**

```bash
# Crear archivo local.properties en raíz del proyecto
# Windows:
sdk.dir=C:\Users\{usuario}\AppData\Local\Android\Sdk

# macOS/Linux:
sdk.dir=/Users/{usuario}/Library/Android/sdk
```

---

### ❌ Error: "google-services.json is missing"

**Causa:** Archivo de configuración de Firebase faltante

**Solución:**

```
1. Ir a https://firebase.google.com/
2. Crear nuevo proyecto
3. Agregar aplicación Android
4. Descargar google-services.json
5. Colocar en: app/google-services.json
```

---

### ❌ Error: "Failed to notify dependency resolution listener"

**Causa:** Caché de Gradle corrupto

**Solución:**

```bash
# Opción 1: Limpiar caché
./gradlew clean --refresh-dependencies

# Opción 2: Borrar caché global (NUCLEAR)
rm -r ~/.gradle/caches
rm -r app/build
./gradlew clean build

# Opción 3: Usar --offline (si falla internet)
./gradlew build --offline
```

---

## 📊 Métricas del Proyecto

```
┌────────────────────────────────────────┐
│          TAROT APP METRICS             │
├────────────────────────────────────────┤
│                                        │
│  Archivos Java:           5            │
│  Lineas de código:        ~800         │
│  Métodos:                 45+          │
│  Clases:                  6            │
│                                        │
│  Layouts XML:             4            │
│  Drawables:               78 imágenes  │
│  Valores recursos:        20+          │
│                                        │
│  Tamaño APK (debug):      12-15 MB     │
│  Tamaño APK (release):    8-10 MB      │
│                                        │
│  APIs utilizadas:         Firebase     │
│  Tiempo compilación:      ~30-45s      │
│                                        │
└────────────────────────────────────────┘
```

---

## 📚 Documentación Adicional

- **[MEJORAS.md](MEJORAS.md)** - Detalle de mejoras implementadas
- **[COMPILACION_RAPIDA.md](COMPILACION_RAPIDA.md)** - Guía rápida de compilación
- **[README_DESIGN.md](README_DESIGN.md)** - Especificaciones de diseño detalladas

---

## 🎨 Personalización

### Cambiar Colores

```xml
<!-- app/src/main/res/values/colors.xml -->
<color name="mystic_dark">#0a0e27</color>      <!-- Fondo -->
<color name="mystic_purple">#9d4edd</color>    <!-- Primario -->
<color name="mystic_gold">#ffd60a</color>      <!-- Acentos -->
```

### Cambiar Tema

```xml
<!-- app/src/main/res/values/themes.xml -->
<style name="Theme.Evaluacion4charlottegabriel">
    <item name="colorPrimary">@color/mystic_purple</item>
    <item name="colorSecondary">@color/mystic_gold</item>
</style>
```

### Agregar Cartas

```
1. Agregar imagen a: app/src/main/res/drawable/
2. Nombrar como: carta0.png, carta1.png, etc.
3. Actualizar Firebase con nueva descripción
```

---

## 🚀 Build & Deploy

### APK Debug

```bash
./gradlew assembleDebug
# Ubicación: app/build/outputs/apk/debug/app-debug.apk
```

### APK Release

```bash
# Requiere configuración de keystore
./gradlew assembleRelease
# Ubicación: app/build/outputs/apk/release/app-release.apk
```

### Bundle (Play Store)

```bash
./gradlew bundle
# Ubicación: app/build/outputs/bundle/release/app-release.aab
```

---

## 📞 Soporte & Contacto

| Canal          | Contacto                |
| -------------- | ----------------------- |
| 📧 **Email**   | [contacto@tarotapp.com] |
| 🐙 **GitHub**  | [github.com/tarotapp]   |
| 💬 **Discord** | [Servidor Comunidad]    |
| 🔗 **Web**     | [tarotapp.com]          |

---

## 📄 Licencia & Créditos

**Proyecto:** Tarot App v1.0  
**Creadores:** Charlotte Rodriguez & Gabriel Barrientos  
**Año:** 2024  
**Licencia:** Todos los derechos reservados © 2024

### Agradecimientos

- 🙏 Material Design por Google
- 🙏 Firebase por Google Cloud
- 🙏 Android Community
- 🙏 Stack Overflow Community

---

## 🔮 Hoja de Ruta Futura

- [ ] **v1.1** - Caché local y sincronización offline
- [ ] **v1.2** - Animaciones y transiciones
- [ ] **v1.3** - Sistema de historial
- [ ] **v1.4** - Compartir en redes sociales
- [ ] **v1.5** - Soporte multiidioma
- [ ] **v2.0** - Tiradas de tarot avanzadas

---

<div align="center">

### ✨ Gracias por usar Tarot App ✨

_Una aplicación mística creada con ❤️ para ti_

**[↑ Volver arriba](#-tarot---aplicación-móvil-android)**

</div>

---

_Última actualización: Junio 2024_  
_Versión: 1.0.0 - Diseño Profesional Místico_

## 🔐 Configuración Firebase

### Estructura de Base de Datos

```json
{
  "0": {
    "titulo": "El Loco",
    "descripcion": "Nuevos comienzos, aventura...",
    "descripcionInvertida": "Indecisión, miedo...",
    "descripcionAmorosa": "Amor aventurero, libertad..."
  },
  "1": {
    "titulo": "El Mago",
    "descripcion": "Manifestación, poder...",
    "descripcionInvertida": "Confusión, engaño...",
    "descripcionAmorosa": "Conexión mágica, comunicación..."
  }
  // ... 78 cartas en total (0-77)
}
```

### Archivos de Configuración

- `app/google-services.json` - Credenciales de Firebase
- Debe configurarse en Firebase Console

---

## 🧪 Testing

### Ejecutar Tests Unitarios

```bash
./gradlew test
```

### Ejecutar Tests Instrumentados (Android)

```bash
./gradlew connectedAndroidTest
```

### Tests Disponibles

- `ExampleUnitTest.java` - Tests básicos
- `ExampleInstrumentedTest.java` - Tests en dispositivo

---

## 📊 Estadísticas

| Métrica                   | Valor            |
| ------------------------- | ---------------- |
| **Cartas disponibles**    | 78               |
| **Respuestas SÍ/NO**      | 45+ preguntas    |
| **Pantallas principales** | 4                |
| **Módulos funcionales**   | 3                |
| **Líneas de código Java** | ~500+            |
| **Mín. SDK**              | 26 (Android 8.0) |
| **Target SDK**            | 32 (Android 12)  |

---

## 🐛 Solución de Problemas

### Error: "Unsupported class file major version 67"

```
❌ Causado por: Java 23
✅ Solución: Cambiar a JDK 17
```

### Error: "SDK location not found"

```
❌ Causado por: Android SDK no configurado
✅ Solución: Crear local.properties con:
   sdk.dir=/path/to/Android/Sdk
```

### Error: "google-services.json is missing"

```
❌ Causado por: Firebase config no presente
✅ Solución: Descargar desde Firebase Console
   Ubicación: app/google-services.json
```

---

## 🚀 Mejoras Futuras

- [ ] Autenticación de usuarios
- [ ] Historial de tiradas
- [ ] Interpretaciones más detalladas
- [ ] Temas personalizables (claro/oscuro)
- [ ] Estadísticas de predicciones
- [ ] Notificaciones diarias
- [ ] Compartir resultados en redes sociales
- [ ] Versión para iOS
- [ ] App Web con Flutter

---

## 👥 Contribuidores

- **Charlotte** - UI/UX & Firebase
- **Gabriel** - Lógica & Algoritmos

---

## 📄 Licencia

Este proyecto es de uso educativo y recreativo.

---

## 📞 Soporte

Para reportar bugs o sugerir mejoras:

1. Crear un issue en el repositorio
2. Contactar a los desarrolladores
3. Consultar la documentación de Firebase

---

**Última actualización**: Junio 2026  
**Estado**: En desarrollo activo

🔮 _"Las cartas no mienten, solo revelan lo que el corazón ya sabe"_ ✨
