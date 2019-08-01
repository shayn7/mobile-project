package cucumber.steps;

import cucumber.api.Scenario;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.runner.TestRunner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import utils.Log;

import java.io.File;

public class Hooks extends TestRunner {

        private File workingDirectory = new File(System.getProperty("user.dir"));
        private String path = workingDirectory + "/screenshots/";

    public Hooks() {
        Before((Scenario scenario) -> {
            Log.start(scenario.getName());
        });

        After((Scenario scenario)-> {
            if(scenario.isFailed()){
                String screenshot = path + scenario.getName()+".png";
                File source = platform.getDriver().getScreenshotAs(OutputType.FILE);
                File destination = new File(screenshot);
                FileUtils.copyFile(source, destination);
                Log.fatal("Screenshot was taken and saved under " + screenshot);
            }
            else{
                Log.end(scenario.getName());
            }
        });
    }
}

