public class LogLevels {
    public static String [] logLevels = {"INFO", "WARNING", "ERROR"};
    public static String message(String logLine) {

        logLine= logLine.replace(":","");
        logLine= logLine.replace("[","");
        logLine= logLine.replace("]","");

        for(String level: logLevels){
            if(logLine.contains(level)){
                logLine= logLine.replace(level,"");
            }
        }
        
        logLine= logLine.trim();
        return logLine;
    }

    public static String logLevel(String logLine) {
        logLine= logLine.trim();
        for(String level: logLevels){
            if(logLine.contains(level)){
                return level.toLowerCase();
            }
        }

        return logLine;
    }

    public static String reformat(String logLine) {

        logLine= logLine.replace(":","");
        logLine= logLine.replace("[","");
        logLine= logLine.replace("]","");
        logLine= logLine.trim();
        
        for(String level: logLevels){
            if(logLine.contains(level)){
                logLine= logLine.replace(level,"");
                logLine+=" ("+level.toLowerCase()+")";
            }
        }

        logLine= logLine.trim();

        return logLine;
    }
}
