package rain.api;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;
import rain.service.RainCalculatorService;
import rain.service.StringListParserService;

import static org.testng.Assert.assertEquals;

public class RainServletTest extends Arquillian {

    @Inject
    RainServlet servlet;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RainServlet.class)
                .addClass(StringListParserService.class)
                .addClass(RainCalculatorService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testCalculate() throws Exception {
        assertEquals(servlet.calculate("100,1,43"), "42");
    }
}