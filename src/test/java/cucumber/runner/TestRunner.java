package cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.java8.En;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import mobilePlatforms.AbstractPlatform;
import mobilePlatforms.MobilePlatformFactory;
import org.testng.annotations.*;

//i can add tags={"@OpenApp,@SignIn"} for example
@CucumberOptions(features = "classpath:features", glue = "cucumber.steps", tags = {"@facebook"})
public class TestRunner implements En {

    protected static AbstractPlatform platform;
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    @Parameters({"appName", "mobilePlatform"})
    public void setEnvironment(String appName, String mobilePlatform) {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        platform = MobilePlatformFactory.getMobilePlatform(mobilePlatform, appName);
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
        platform.tearDown();
    }
}


