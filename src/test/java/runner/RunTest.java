package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"format","json:target/report.json"},
        glue = {"stepDefinitions"},
        features = {"src/test/resources/features"})

public class RunTest {

}
