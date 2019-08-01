package mobilePlatforms;

import enums.MobilePlatform;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Dimension;
import utils.Log;

public class AndroidPlatform extends AbstractPlatform {

    private String packageName = propertiesReader.getPackageName();
    private String activity = propertiesReader.getActivityName();
    private String deviceName = propertiesReader.getDeviceName();
    private String platformVersion = propertiesReader.getPlatformVersion();
    private boolean noReset = propertiesReader.getNoResetValue();
    private String automationName = propertiesReader.getAutomationName();

    public AndroidPlatform(String appName) {
        super(MobilePlatform.ANDROID, appName);
    }


    @Override
    public void initCapabilities() {
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", mobilePlatform.toString());
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("noReset", noReset);
        capabilities.setCapability("newCommandTimeout", 300);

    }

    protected void addInstalledAppCapabilities() {
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", activity);
    }

    @Override
    public void takePicture() {
        Log.info("Taking picture...");
        sleep(5);
        getAndroidDriver().pressKey(new KeyEvent(AndroidKey.CAMERA));
    }

    @Override
    public void clickEnter() {
        Log.info("Clicking enter...");
        sleep(2);
        getAndroidDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Override
    public void disableCoachMarks() {
        Dimension screenSize = getDriver().manage().window().getSize();
        int x = screenSize.getWidth() / 2;
        int y = (int)(screenSize.getHeight()*0.6);
        tapByScreenCoordinates(x,y);
    }


}
