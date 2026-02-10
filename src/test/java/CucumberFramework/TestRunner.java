package CucumberFramework;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/CucumberFramework",
        glue = "StepDefination",
        tags=""    )

public class TestRunner extends AbstractTestNGCucumberTests {


}
