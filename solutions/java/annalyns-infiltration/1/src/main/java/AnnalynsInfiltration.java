class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {  
        return ((knightIsAwake)? false : true);
    }

    public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {

        if(knightIsAwake || archerIsAwake ||prisonerIsAwake){
            return true;
        }
        return false;

    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
       if(!archerIsAwake && prisonerIsAwake){
           return true;
       }
        return false;

    }

    public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake, boolean petDogIsPresent) {
        if(!archerIsAwake && petDogIsPresent){
            return true;
        }else if(prisonerIsAwake && !knightIsAwake && !archerIsAwake && !petDogIsPresent){
            return true;
        }else{
            return false;
        }
    }
}
