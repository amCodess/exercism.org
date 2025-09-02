# Resumen: Métodos de String, Problemas Encontrados y Recomendaciones

## **Métodos de String Explicados en las Referencias**

### **Inmutabilidad de String**

- Los objetos `String` en Java **nunca cambian** después de crearse
- Métodos como `replace()`, `trim()`, `toLowerCase()` devuelven una **nueva cadena**
- **Siempre hay que asignar** el resultado: `logLine = logLine.trim();`


### **Métodos Clave para tu Ejercicio**

**contains() y indexOf()**

- `contains("texto")`: devuelve `true` si encuentra el texto
- `indexOf("texto")`: devuelve la posición (-1 si no existe)
- Útiles para detectar niveles de log: `if(logLine.contains("[ERROR]"))`

**split()**

- Divide un String usando un separador
- `String[] partes = logLine.split(": ", 2);` separa nivel y mensaje
- Más limpio que múltiples `replace()`

**trim()**

- Elimina espacios, tabs (`\t`) y saltos de línea (`\r\n`) solo al **inicio y final**
- No elimina espacios en medio de la cadena
- **Crítico aplicarlo antes de concatenar**

**toLowerCase() y toUpperCase()**

- Cambian a minúsculas/mayúsculas
- `"ERROR".toLowerCase()` → `"error"`

**substring(int start, int end)**

- Extrae una porción de la cadena
- **El feedback recomienda usarlo** en lugar de múltiples `replace()`

**Concatenación**

- Con `+`: `"Mensaje" + " (error)"`
- Con `.concat()`: `"Mensaje".concat(" (error)")`


## **Problemas Principales Encontrados en tu Código**

### **1. Inmutabilidad no Respetada (Problema Crítico)**

```java
// ❌ INCORRECTO - No guarda el resultado
logLine.replace(level, "");
logLine.trim();

// ✅ CORRECTO - Asigna el resultado
logLine = logLine.replace(level, "");
logLine = logLine.trim();
```


### **2. Separador ":" No Eliminado**

- Tu código eliminaba `[ERROR]` pero dejaba `": Stack overflow"`
- **Solución**: eliminar `level + ": "` en lugar de solo `level`


### **3. trim() Aplicado Demasiado Tarde**

```java
// ❌ PROBLEMÁTICO
logLine = mensajeSucio + " (error)";  // Los \t\r\n quedan en medio
logLine = logLine.trim();             // trim() no los puede quitar

// ✅ CORRECTO
String mensajeLimpio = mensajeSucio.trim();  // Limpia primero
logLine = mensajeLimpio + " (error)";        // Luego concatena
```


### **4. No Reutilización de Métodos**

- El feedback recomienda que `reformat()` use `message()` y `logLevel()`
- Tu código repite la lógica en lugar de reutilizar


## **Recomendaciones del Feedback**

1. **Usar `substring()`** en lugar de múltiples `replace()`
2. **Reutilizar métodos**: `reformat()` debería llamar a `message()` y `logLevel()`
3. **Evitar duplicación** de lógica entre métodos

## **Recomendaciones Específicas para tu Código**

### **Versión Mejorada Usando substring()**

```java
public static String message(String logLine) {
    // Encontrar donde termina el nivel (después del ]: )
    int startIndex = logLine.indexOf("]: ") + 3;
    return logLine.substring(startIndex).trim();
}

public static String logLevel(String logLine) {
    // Extraer entre [ y ]
    int start = logLine.indexOf("[") + 1;
    int end = logLine.indexOf("]");
    return logLine.substring(start, end).toLowerCase();
}

public static String reformat(String logLine) {
    // Reutilizar métodos existentes (como recomienda el feedback)
    String mensaje = message(logLine);
    String nivel = logLevel(logLine);
    return mensaje + " (" + nivel + ")";
}
```


### **Alternativa con split() (Más Limpia)**

```java
public static String message(String logLine) {
    String[] partes = logLine.split("]: ", 2);
    return partes[1].trim();
}

public static String logLevel(String logLine) {
    String nivelConCorchetes = logLine.split("]: ")[0];
    return nivelConCorchetes.substring(1).toLowerCase(); // Quita el [
}
```


### **Mejores Prácticas Aplicadas**

1. **Orden correcto**: `trim()` antes de concatenar
2. **Reutilización**: `reformat()` usa `message()` y `logLevel()`
3. **Método más eficiente**: `substring()` o `split()` en lugar de múltiples `replace()`
4. **Código más legible**: menos repetición, métodos más cortos

### **Validaciones Adicionales (Opcional)**

```java
public static String message(String logLine) {
    if (logLine == null || !logLine.contains("]: ")) {
        return ""; // o lanzar excepción
    }
    int startIndex = logLine.indexOf("]: ") + 3;
    return logLine.substring(startIndex).trim();
}
```

**Conclusión**: Tu enfoque inicial era correcto conceptualmente, pero los detalles de inmutabilidad de String y el timing de `trim()` causaron los fallos en los tests. La versión mejorada sigue las recomendaciones del feedback y es más robusta.

