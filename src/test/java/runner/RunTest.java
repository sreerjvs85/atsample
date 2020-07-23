package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"json:target/report.json"},
        glue = {"stepDefinitions"},
        tags = "@New"
)
public class RunTest extends AbstractTestNGCucumberTests {

}
