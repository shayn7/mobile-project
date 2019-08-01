package driver;

import enums.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private AppiumDriver driver;

    public final void initDriver(String url, DesiredCapabilities desiredCapabilities, MobilePlatform mobilePlatform)  {
        driver = getDriver(url, desiredCapabilities, mobilePlatform);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    protected AndroidDriver getAndroidDriver() {
        return (AndroidDriver) driver;
    }

    protected IOSDriver getIosDriver() {
        return (IOSDriver) driver;
    }

    private AppiumDriver getDriver(String hubUrl, DesiredCapabilities desiredCapabilities, MobilePlatform mobilePlatform){
        switch(mobilePlatform) {
            case ANDROID:
                return new AndroidDriver(getUrl(hubUrl), desiredCapabilities);
            case IOS:
                return new IOSDriver(getUrl(hubUrl), desiredCapabilities);
            default:
                throw new RuntimeException("Cannot get the driver");
        }
    }

    private static URL getUrl(String hubUrl){
        URL url = null;
        try {
            url = new URL(hubUrl);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException caught: " + e);
        }
        return url;
    }
}
