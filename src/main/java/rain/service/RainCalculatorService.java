package rain.service;

public class RainCalculatorService {

    public long calculate(int landscape[]) {

        if (landscape == null || landscape.length < 3) {
            return 0;
        }

        long current;
        long pitLeft = 0;
        boolean inPit = false;
        long waterAmount = 0;
        long currentPitAmount = 0;
        int pitLeftColumnNumber = 0;

        for (int i = 1; i < landscape.length; i++) {
            current = landscape[i];
            if (inPit) {
                if (pitEnded(current, pitLeft)) {
                    inPit = false;
                    // this allows us not to add water from last slope to the total
                    waterAmount += currentPitAmount;
                } else {
                    currentPitAmount += pitLeft - current;
                }
            } else {
                pitLeftColumnNumber = i - 1;
                if (pitStarted(current, landscape[pitLeftColumnNumber])) {
                    inPit = true;
                    pitLeft = landscape[pitLeftColumnNumber];
                    currentPitAmount = pitLeft - current;
                }
            }
        }

        //if we reach the end of array and still in a pit then we have long last hill
        // and we have to run the same algorithm backward to [pitLeftColumnNumber] to find small pits at this hill
        if (inPit) {
            inPit = false;
            for (int i = landscape.length - 2; i >= pitLeftColumnNumber; i--) {
                current = landscape[i];
                if (inPit) {
                    if (pitEnded(current, pitLeft)) {
                        inPit = false;
                    } else {
                        waterAmount += pitLeft - current;
                    }
                } else {
                    if (pitStarted(current, landscape[i + 1])) {
                        inPit = true;
                        pitLeft = landscape[i + 1];
                        waterAmount += pitLeft - current;
                    }
                }
            }
        }

        return waterAmount;
    }

    private boolean pitEnded(final long current, final long pitLeft) {
        return current >= pitLeft;
    }

    private boolean pitStarted(final long current, final long previous) {
        return current < previous;
    }

}
