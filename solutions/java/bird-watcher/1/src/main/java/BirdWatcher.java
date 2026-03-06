class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        return new int[] {0, 2, 5, 3, 7, 8, 4};
    }

    public int getToday() {
        return birdsPerDay[birdsPerDay.length - 1];
    }

    public void incrementTodaysCount() {
        birdsPerDay[birdsPerDay.length - 1]++;
    }

    public boolean hasDayWithoutBirds() {
        for (int singleDayBirds : birdsPerDay) {
            if (singleDayBirds == 0) {
                return true;
            }
        }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int countBirds = 0;
        int limit = Math.min(numberOfDays, birdsPerDay.length);

        for (int i = 0; i < limit; i++) {
            countBirds += birdsPerDay[i];
        }

        return countBirds;
    }

    public int getBusyDays() {
        int countBusyDays = 0;

        for (int singleDayBirds : birdsPerDay) {
            if (singleDayBirds >= 5) {
                countBusyDays++;
            }
        }

        return countBusyDays;
    }
}
