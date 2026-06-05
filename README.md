# 🔮 Tarot - Aplicación Móvil Android

**Aplicación interactiva de Tarot para responder preguntas del día mediante la interpretación de las cartas del tarot**

<div align="center">
    <img src="https://i.pinimg.com/originals/04/78/e6/0478e6056d0a6f4e4ba0ac53b77904aa.gif" alt="Tarot GIF" width="300">
</div>

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

## 🎯 Funcionalidades por Módulo

### 1. 🌟 Carta del Día

La carta del día o los arcanos hoy es un instrumento de conocimiento personal. Permite relacionar las experiencias diarias con el mensaje de las cartas, aumentando el juicio personal y espiritual del usuario.

**Características:**

- Selección automática basada en la fecha actual
- Significado de la carta derecha e invertida
- Algoritmo: `(día + mes + año) % 78` para garantizar la misma carta por día

<div align="center">
    <img src="https://media.tenor.com/zX0YOqbWMwsAAAAC/kakegurui-yumeko-jabami.giff" alt="Carta del Día" width="280">
</div>

**Preguntas de ejemplo:**

- ¿Qué energía me acompañará hoy?
- ¿Cuál será mi lección del día?
- ¿Cómo será mi día?

---

### 2. ✅❌ Sí y No

Función interactiva para realizar preguntas que se pueden responder con sí, no o quizás. El algoritmo valida la carta contra listas predefinidas para determinar la respuesta más acertada.

**Características:**

- 45+ preguntas comunes pre-configuradas
- Selección pseudoaleatoria de cartas (0-77)
- Interpretación inteligente de resultados

<div align="center">
    <img src="https://i.pinimg.com/originals/22/4e/e3/224ee3aa67dad6545247827e915b4f7c.gif" alt="Sí y No" width="280">
</div>

**Preguntas de ejemplo:**

- ¿Mi pareja me es infiel?
- ¿Encontraré trabajo pronto?
- ¿Tendré hijos?
- ¿Me voy a casar?
- ¿Mi esposo/a me ama?
- ¿Piensa en mí?
- ¿Volveremos a estar juntos?
- ¿Encontraré el amor?
- ¿Voy a conseguir trabajo?

---

### 3. 💕 Tarot de las Parejas

El amor es el sentimiento más confuso pero hermoso que experimentamos. La compatibilidad es imprescindible para una relación exitosa. Este módulo analiza las energías de dos personas para ofrecer recomendaciones personalizadas.

**Características:**

- Lectura dual con dos cartas
- Interpretación amorosa de cada carta
- Análisis de compatibilidad energética
- Recomendaciones basadas en las energías interpretadas

<div align="center">
    <img src="https://i.pinimg.com/originals/39/7e/5d/397e5d11538de7e8833bd2118e5f8beb.gif" alt="Tarot de Parejas" width="280">
</div>

**Interpretación:**

- Energía de la primera persona
- Energía de la segunda persona
- Compatibilidad general
- Consejos para la relación

---

## 🛠️ Stack Tecnológico

| Componente                | Versión | Descripción                  |
| ------------------------- | ------- | ---------------------------- |
| **Java**                  | 11+     | Lenguaje de programación     |
| **Android SDK**           | 32+     | API mínima para compilación  |
| **Gradle**                | 7.4     | Sistema de construcción      |
| **Android Gradle Plugin** | 7.3.1   | Plugin para compilar Android |
| **Firebase**              | 31.1.1+ | Base de datos en tiempo real |
| **Material Design**       | 1.7.0+  | Componentes de UI            |
| **AndroidX**              | 1.5.1+  | Librería de soporte          |

---

## 📋 Requisitos Previos

### Sistema

- **JDK 11 o anterior** (requerido - NO compatible con Java 23)
- Android Studio 2022.1+
- Android SDK 32+
- Gradle 7.4

### Proyecto

```bash
# Verificar requisitos
./gradlew --version

# Debe mostrar:
# Gradle 7.4 con JDK 11-19 (NO funciona con Java 23)
```

### ⚠️ Problema Conocido: Java 23

**El proyecto no compila con Java 23** debido a incompatibilidad de bytecode entre:

- Gradle 7.4 soporta hasta Java 19
- Java 23 genera bytecode versión 67 (no soportado por Groovy/ASM)

**Solución:**

1. **Opción A (Recomendada):** Instalar JDK 17

   ```bash
   # Descargar desde https://adoptium.net/
   # Configurar JAVA_HOME=/path/to/jdk-17
   ```

2. **Opción B:** Actualizar Gradle y AGP
   ```bash
   # Cambiar a Gradle 8.11+
   # Cambiar a AGP 8.2+
   # Actualizar compileSdk a 34+
   ```

---

## 🚀 Instalación y Compilación

### 1. Clonar repositorio

```bash
git clone <URL_DEL_REPO>
cd Tarot
```

### 2. Configurar JDK (Importante!)

```powershell
# Windows - Establecer JDK 17
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# macOS/Linux
export JAVA_HOME=/usr/libexec/java_home -v17
```

### 3. Compilar la aplicación

```bash
# Limpiar caché anterior
./gradlew clean

# Compilar proyecto
./gradlew build

# Compilar e instalar en emulador
./gradlew installDebug

# Ejecutar tests
./gradlew test
```

### 4. Generar APK

```bash
# Debug APK
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk

# Release APK (requiere configuración de firma)
./gradlew assembleRelease
```

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

```
MainActivity
  ↓
[Generar número aleatorio 0-77]
  ↓
Firebase → Obtener carta aleatoria
  ↓
SiYNo → Validar en listas (SI/NO/TALVEZ)
  ↓
Mostrar respuesta con interpretación
```

### Tarot de Parejas

```
MainActivity
  ↓
[Generar dos números aleatorios]
  ↓
Firebase → Obtener dos cartas
  ↓
tarotPareja → Mostrar energías duales
  ↓
Interpretación amorosa de ambas cartas
```

---

## 🎨 Interfaz de Usuario

### Pantallas Principales

#### 1. Pantalla Principal (MainActivity)

- Tres botones principales para acceder a cada módulo
- Fondo temático con degradado oscuro
- Títulos y descripciones de cada sección

#### 2. Carta del Día (CartaDelDia)

- Imagen grande de la carta
- Título y número de la carta
- Descripción/interpretación
- Indicador de inversión (si aplica)
- Botón para volver

#### 3. Sí y No (SiYNo)

- Imagen de la carta seleccionada
- Título y descripción
- **Respuesta principal** (SÍ / NO / TALVEZ)
- Interpretación detallada
- Botón para hacer otra pregunta

#### 4. Tarot de Parejas (tarotPareja)

- Dos cartas lado a lado
- Interpretación para cada persona
- Energías amorosas
- Recomendaciones de compatibilidad

---

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
