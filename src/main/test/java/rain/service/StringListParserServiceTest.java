package rain.service;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StringListParserServiceTest {
    private StringListParserService service;

    @DataProvider (name = "common")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {"1,2,3", new int[]{1, 2, 3}},
                {"1,2,3,               4", new int[]{1, 2, 3, 4}},
                {"1", new int[]{1}},
                {"", null},
                {null, null},
        };
    }

    @BeforeClass
    public void setUp() throws Exception {
        service = new StringListParserService();
    }

    @Test (expectedExceptions = NumberFormatException.class)
    public void shouldThrowAnExceptionIfNotANumber() throws Exception {
        service.parse("abc");
    }

    @Test (dataProvider = "common")
    public void testParse(String input, int[] output) throws Exception {
        assertEquals(service.parse(input), output);
    }


}