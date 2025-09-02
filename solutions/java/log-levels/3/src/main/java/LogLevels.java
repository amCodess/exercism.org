public class LogLevels {
    
    public static String message(String logLine) {
        // Usar substring como recomienda el feedback
        int startIndex = logLine.indexOf("]: ") + 3;
        return logLine.substring(startIndex).trim();
    }
    
    public static String logLevel(String logLine) {
        // Extraer el nivel entre [ y ] usando substring
        int start = logLine.indexOf("[") + 1;
        int end = logLine.indexOf("]");
        return logLine.substring(start, end).toLowerCase();
    }
    
    public static String reformat(String logLine) {
        // Reutilizar métodos existentes (recomendación del feedback)
        String mensaje = message(logLine);
        String nivel = logLevel(logLine);
        return mensaje + " (" + nivel + ")";
    }
}
