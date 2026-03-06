public class JedliksToyCar {

    private int distanceTraveled=0;
    private int battery=100;
    
    public static JedliksToyCar buy() {
        JedliksToyCar c = new JedliksToyCar();
        return c;
    }

    public String distanceDisplay() {
        return "Driven "+distanceTraveled+" meters";
    }

    public String batteryDisplay() {
        
        if(battery==0){
            return "Battery empty";
        }else{
            return "Battery at "+battery+"%";
        }
        
        
    }

    public void drive() {

        if(battery>0){
            distanceTraveled+=20;
            battery-=1;
        }
        
    }
}
