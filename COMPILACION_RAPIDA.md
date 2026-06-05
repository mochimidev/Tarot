# ⚡ Guía Rápida de Compilación - Tarot App

## 🚨 REQUISITO CRÍTICO: Java 17 (NO Java 23)

```bash
# Verificar versión de Java actual
java -version

# ❌ SI VES: java version "23.0.1"
# ✅ DEBES: Instalar JDK 17
```

---

## 📥 Instalación de JDK 17

### Windows

#### Opción 1: Eclipse Temurin (RECOMENDADO)

1. Ir a: https://adoptium.net/
2. Descargar: **Temurin 17 LTS** para Windows x64
3. Instalar en: `C:\Program Files\Java\jdk-17`
4. Configurar variable de entorno:
   ```powershell
   $env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
   ```

#### Opción 2: Oracle JDK 17

1. Descargar de: https://www.oracle.com/java/technologies/downloads/#java17
2. Instalar en ubicación por defecto
3. Configurar:
   ```powershell
   $env:JAVA_HOME = "C:\Program Files\Java\jdk-17.0.x"
   ```

### macOS

```bash
# Instalar con Homebrew
brew install temurin@17

# Verificar instalación
/usr/libexec/java_home -v17
```

### Linux (Ubuntu/Debian)

```bash
# Instalar
sudo apt-get update
sudo apt-get install temurin-17-jdk

# Verificar
update-alternatives --list java
```

---

## ✅ Verificar Instalación

```bash
# Verificar Java
java -version
# Debe mostrar: java version "17.x.x"

# Verificar compilador
javac -version
# Debe mostrar: javac 17.x.x

# Verificar JAVA_HOME
echo $JAVA_HOME
# Debe mostrar ruta a JDK 17
```

---

## 🔧 Configurar Proyecto

### 1. Abrir terminal en el directorio del proyecto

```bash
cd C:\Users\Softm\Desktop\Tarot
# o
cd ~/Desktop/Tarot  # macOS/Linux
```

### 2. Establecer JAVA_HOME (si no está configurado globalmente)

**Windows (PowerShell):**

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
```

**macOS/Linux:**

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v17)
```

### 3. Verificar Gradle

```bash
./gradlew --version
# Debe mostrar Gradle 7.4 con JDK 17
```

---

## 🏗️ Compilar la Aplicación

### Paso 1: Limpiar build anterior

```bash
./gradlew clean
```

### Paso 2: Compilar proyecto

```bash
./gradlew build
```

### Paso 3: Ver resultado

```
BUILD SUCCESSFUL in XXs
```

---

## 📦 Generar APK

### Debug APK (para probar)

```bash
./gradlew assembleDebug

# Ubicación: app/build/outputs/apk/debug/app-debug.apk
```

### Release APK (producción)

```bash
./gradlew assembleRelease

# Ubicación: app/build/outputs/apk/release/app-release.apk
# (Requiere keystore configurado)
```

---

## 🧪 Ejecutar Tests

```bash
# Tests unitarios
./gradlew test

# Tests en dispositivo/emulador
./gradlew connectedAndroidTest
```

---

## ⚠️ Problemas Comunes

### Error: "Unsupported class file major version 67"

```
Causa: Java 23 configurado
Solución:
1. Instalar JDK 17
2. Configurar JAVA_HOME
3. Limpiar caché: ./gradlew clean
```

### Error: "SDK location not found"

```
Causa: Android SDK no configurado
Solución: Crear local.properties en raíz del proyecto:
sdk.dir=C:\Users\Softm\AppData\Local\Android\Sdk
```

### Error: "google-services.json is missing"

```
Causa: Configuración de Firebase faltante
Solución:
1. Ir a Firebase Console
2. Descargar google-services.json
3. Colocar en: app/google-services.json
```

### Error: "Unsupported class-file format"

```
Causa: Caché corrupto
Solución:
./gradlew clean
rm -r .gradle
./gradlew build
```

---

## 🚀 Instalar en Dispositivo

### Requisito: Dispositivo Android o Emulador conectado

```bash
# Instalar APK de debug
./gradlew installDebug

# Luego ejecutar:
adb shell am start -n com.example.evaluacion4charlottegabriel/.MainActivity
```

---

## 📊 Verificar Instalación Correcta

Después de compilar exitosamente:

```
✅ BUILD SUCCESSFUL
✅ APK creado en app/build/outputs/apk/debug/
✅ Tamaño: ~5-10 MB
✅ Firebase configurado
✅ 3 Activities compiladas
```

---

## 🔍 Verificar Todo Está Bien

```bash
# Listar APKs generados
ls -la app/build/outputs/apk/

# Verificar firma
jarsigner -verify app/build/outputs/apk/debug/app-debug.apk

# Ver información
aapt dump badging app/build/outputs/apk/debug/app-debug.apk
```

---

## 💡 Tips Útiles

### 1. Cachear Gradle (más rápido)

```bash
# Primera compilación (más lenta)
./gradlew build

# Compilaciones siguientes (más rápidas)
./gradlew build --build-cache
```

### 2. Compilación paralela

```bash
./gradlew build --parallel
```

### 3. Ver todas las tasks disponibles

```bash
./gradlew tasks
```

### 4. Debugging con Gradle

```bash
./gradlew build --info   # Información detallada
./gradlew build --debug  # Debug completo
```

---

## 📞 Soporte

Si persisten los errores:

1. Verificar `java -version` → Debe ser 17.x
2. Verificar `./gradlew --version` → Debe ser Gradle 7.4
3. Limpiar caché: `rm -r ~/.gradle`
4. Reiniciar terminal/IDE

---

**Última actualización:** Junio 2026  
**Version:** 1.0

🔮 ¡Listo para compilar! ✨
