## Métodos/funciones de las referencias

- If-then-else en Java: permite ejecutar ramas alternativas según condiciones; se puede encadenar con else if para múltiples rangos, tal como se hace con los tramos de velocidad.[^4][^1]
- Operadores relacionales e igualdad: se usan para comparar rangos de velocidad con >, <, >=, <=, == y !=, regresando booleanos apropiados para las condiciones del if.[^5][^3]
- Conversiones numéricas: de int a double es conversión implícita (ampliación), de double a int requiere **cast** explícito y trunca los decimales, ideal para “ítems por minuto” enteros.[^2]


## Qué devuelve cada método

- productionRatePerHour(int): un double con la producción por hora ajustada por la tasa de éxito, aprovechando que Java puede promover int a double en expresiones mixtas automáticamente.[^5][^2]
- workingItemsPerMinute(int): un int con los autos válidos por minuto, calculado como producciónPorHora/60 y luego truncado con cast a int, preferiblemente haciendo la división en coma flotante antes del cast.[^3][^2]


## Problemas encontrados en tu solución (menores)

- Uso de 1 en vez de 1.0: no rompe nada porque la expresión se promueve a double cuando intervienen otros double, pero expresar literales como 1.0/60.0 hace el tipo explícito y evita divisiones enteras accidentales si la fórmula cambia.[^3][^5]
- Lógica duplicada de tasas en varios returns: puede centralizarse en una variable successRate para mejorar legibilidad y reducir errores de mantenimiento en condiciones encadenadas.[^1][^4]
- Validación de rango repetida en ambos métodos: puede conservarse, pero al reutilizar productionRatePerHour en workingItemsPerMinute la validación de rango se centraliza implícitamente.[^4][^1]


## Cómo evitar estos fallos en el futuro

- Aplicar el **cast** al resultado completo con paréntesis: (int) (expresión), asegurando que la truncación ocurra al final y no se vea afectada por la precedencia de operadores.[^2][^3]
- Reutilizar métodos para no duplicar reglas: calcular primero la producción por hora y derivar “por minuto” con división entre 60.0; esto mantiene una sola “fuente de verdad” para la tasa de éxito.[^1][^4]
- Especificar literales en double cuando corresponda (1.0, 60.0) para forzar aritmética de coma flotante y evitar divisiones enteras no deseadas.[^5][^3]


## Recomendación específica con código

- Refactorizar a una sola tasa de éxito y reutilizar productionRatePerHour en workingItemsPerMinute, usando 60.0 y cast final.

```java
public class CarsAssemble {
    public double productionRatePerHour(int speed) {
        if (speed < 0 || speed > 10) {
            return 0.0;
        }
        double successRate =
            (speed >= 1 && speed <= 4) ? 1.0 :
            (speed <= 8)               ? 0.90 :
            (speed == 9)               ? 0.80 :
            (speed == 10)              ? 0.77 : 0.0;

        return speed * 221 * successRate; // double por promoción implícita
    }

    public int workingItemsPerMinute(int speed) {
        // Trunca al final: divide entre 60.0 (double) y luego castea
        return (int) (productionRatePerHour(speed) / 60.0);
    }
}
```


### Resumen: Números, If-Else y Casting en “Cars, Assemble!”

- Instrucciones if-then-else con else if permiten cubrir rangos de velocidad 1–4, 5–8, 9 y 10 de forma clara y mantenible.[^4][^1]
- Comparaciones con >, >=, <= y == guían las condiciones; recordar la precedencia y agrupar con paréntesis si se complejiza la expresión.[^3][^5]
- Conversión implícita int→double y cast explícito double→int permiten calcular en double y truncar al final para el entero por minuto, que es lo que requiere el ejercicio.[^2]
<span style="display:none">[^10][^11][^12][^13][^14][^15][^16][^17][^18][^19][^20][^6][^7][^8][^9]</span>

<div style="text-align: center">⁂</div>

[^1]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/if.html

[^2]: https://www.w3schools.com/java/java_type_casting.asp

[^3]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html

[^4]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html

[^5]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op2.html

[^6]: https://www.w3schools.com/java/java_conditions.asp

[^7]: https://www.tutorialspoint.com/java/if_else_statement_in_java.htm

[^8]: https://www.oracletutorial.com/plsql-tutorial/plsql-if/

[^9]: https://docs.oracle.com/en/middleware/fusion-middleware/coherence/14.1.2/java-reference/com/tangosol/coherence/dslquery/operator/ComparisonOperator.html

[^10]: https://w3schools.netlify.app/learnjava/java_type_casting

[^11]: https://www.javainhand.com/2025/04/if-statement-in-oracle-plsql.html

[^12]: https://docs.oracle.com/en/database/other-databases/nosql-database/21.2/sqlreferencefornosql/value-comparison-operators.html

[^13]: https://w3schoolsua.github.io/java/java_type_casting_en.html

[^14]: https://www.geeksforgeeks.org/java/decision-making-javaif-else-switch-break-continue-jump/

[^15]: https://www.w3schools.com/java/java_conditions_elseif.asp

[^16]: https://docs.oracle.com/cd/E19316-01/819-3669/bnbub/index.html

[^17]: https://www.w3schools.com/java/exercise.asp?x=xrcise_type_casting1

[^18]: https://www.reddit.com/r/SQL/comments/1akznqx/oracle_plsql_tutorial_15_ifthenelsif_statement_in/

[^19]: https://www.youtube.com/watch?v=kFUqkaicfDw

[^20]: https://stackoverflow.com/questions/73655374/equivalent-function-in-oracle-sql-for-the-java-operator

