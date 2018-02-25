package rain.service;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rain.service.RainCalculatorService;

import static org.testng.Assert.assertEquals;

public class RainCalculatorServiceTest {
    private static final int MAX = 32000;
    private RainCalculatorService calculator;

    @DataProvider (name = "common")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {new int[]{3, 2, 4, 1, 2}, 2},
                {new int[]{1, 2, 1, 5, 2, 3, 4, 3, 2, 5, 4, 1, 3, 1}, 14},
                {new int[]{4, 1, 1, 0, 2, 3}, 8},
                {new int[]{5, 2, 3, 4, 5, 4, 1, 3, 1}, 8},
                {new int[]{10, 0, 10, 0, 9}, 19},
                {new int[]{0, 0, 0, 0, 0}, 0},
                {new int[]{0, 1000, 0, 0, 0}, 0},
                {new int[]{2, 1, 0, 0, 1}, 2},
                {new int[]{1, 0, 6, 0, 0, 8, 8, 0, 0, 0, 6, 6, 0, 11, 0, 0, 4}, 57},
                {new int[]{6, 6, 4, 2, 2, 8, 1, 2, 3}, 13},
                {new int[]{6, 6, 4, 2, 2, 8, 1, 8}, 17},
                {new int[]{6, 6, 4, 2, 2, 8, 1, 0}, 10},
                {new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}, 0},
                {new int[]{5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5}, 25},
                {new int[]{4, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 4}, 32},
                {new int[]{5, 0, 4, 3, 2, 1, 0}, 4},
                {new int[]{}, 0},
                {new int[]{5}, 0},
                {new int[]{5, 5}, 0},
                {new int[]{5, 5, 5}, 0},
                {null, 0},
        };
    }


    @BeforeClass
    public void setUp() throws Exception {
        calculator = new RainCalculatorService();
    }

    @Test (dataProvider = "common")
    public void testCalculate(int[] landscape, int expectedWaterAmount) throws Exception {
        assertEquals(calculator.calculate(landscape), expectedWaterAmount);
    }

    @Test
    public void testEdgeCasesCalculation1() {
        int landscape[] = new int[MAX];

        landscape[0] = landscape[landscape.length - 1] = MAX;

        long waterAmount = calculator.calculate(landscape);

        assertEquals(waterAmount, 1023936000L);
    }


    @Test
    public void testEdgeCasesCalculation2() {
        int landscape[] = new int[MAX];

        landscape[0] = 2;
        landscape[2] = 10;

        long waterAmount = calculator.calculate(landscape);

        assertEquals(waterAmount, 2);
    }
}