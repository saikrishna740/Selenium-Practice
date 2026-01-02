
package endToendECommerceAp.testRunners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "endToendECommerceAp.stepDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        }
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}

