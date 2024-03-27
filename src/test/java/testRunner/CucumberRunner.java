package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", glue = {"support", "stepDefinitions"},
        plugin = {"pretty",
                "summary",
                "html:src/test/resources/Reports/cucumber-reporting/CucumberHTMLReports",
                "json:src/test/resources/Reports/cucumber-reporting/Cucumber.json",
                "junit:src/test/resources/Reports/cucumber-reporting/Cucumber.xml",
                "rerun:target/rerun.txt", //Creates a text file with failed scenarios
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        monochrome = true,
        tags = "@addToCart"
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}