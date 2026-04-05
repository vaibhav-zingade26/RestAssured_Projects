package CucumberFramework;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/CucumberFramework",
        glue = "src/test/java/CucumberFramework/StepDefinations",
        tags=""    )

public class TestRunner extends AbstractTestNGCucumberTests {


}
