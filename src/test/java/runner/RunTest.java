package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber-report/report.json"},
        glue = {"stepDefinitions"}
)
public class RunTest extends AbstractTestNGCucumberTests {

}