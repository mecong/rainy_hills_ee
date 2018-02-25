package rain.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rain.service.RainCalculatorService;
import rain.service.StringListParserService;

@Path ("rain")
@Slf4j
@AllArgsConstructor (onConstructor = @__(@Inject))
@NoArgsConstructor
public class RainServlet {
    private  StringListParserService parserService;
    private  RainCalculatorService rainCalculatorService;

    @GET
    public String calculate(@QueryParam ("q") final String list) {
        final int[] parse;
        try {
            parse = parserService.parse(list);
            return String.valueOf(rainCalculatorService.calculate(parse));
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return "NumberFormatException";
        }
    }
}
