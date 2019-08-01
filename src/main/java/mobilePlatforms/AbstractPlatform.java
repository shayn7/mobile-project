package mobilePlatforms;

import driver.DriverFactory;
import enums.MobilePlatform;
import enums.SwipeDirection;
import helpers.AppMessages;
import helpers.CoachMarks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;
import utils.Log;
import utils.PropertiesReader;
import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;




public abstract class AbstractPlatform extends DriverFactory {

    protected MobilePlatform mobilePlatform;
    protected DesiredCapabilities capabilities;
    protected PropertiesReader propertiesReader;
    private int secondsToWait = 3;
    private String applicationName;
    private File applicationPath;
    private AbstractPage actualPage;
    private boolean shouldHandleWelcomePage = true;
    private boolean shouldHandleCoachMarks = true;
    private boolean shouldHandleAppRatingPopUp = true;

    public AbstractPlatform(MobilePlatform mobilePlatform, String appName) {
        this.mobilePlatform = mobilePlatform;
        applicationName = appName;
        capabilities = new DesiredCapabilities();
        propertiesReader = new PropertiesReader();
    }

    public void openApp() {
        initCapabilities();
        addInstalledAppCapabilities();
        initDriver("http://127.0.0.1:4723/wd/hub", capabilities, mobilePlatform);
        Log.info("Application was open");
        if (this instanceof AndroidPlatform) {
            if (shouldHandleWelcomePage)
                handleWelcomePage();
        }
        if (shouldHandleCoachMarks)
            handleCoachMarks();
        if (shouldHandleAppRatingPopUp)
            handleAppRanking();
    }

    public void iShouldBeOnPage(String pageName) {
        actualPage = getCurrentPageAsObject(pageName);
        boolean isExpectedPage = actualPage.verifyPage();
        Log.info(getMessageBasedOnCondition(() -> isExpectedPage, pageName));
        Assert.assertTrue(isExpectedPage,"actual page is not " + pageName);
    }

    public <T extends AbstractPage> T getPage(Class<T> PageClass) {
        return (T) actualPage;
    }

    public void setShouldHandleCoachMarks(boolean shouldHandleCoachMarks) {
        this.shouldHandleCoachMarks = shouldHandleCoachMarks;
    }

    public void setShouldHandleAppRatingPopUp(boolean shouldHandleAppRatingPopUp) {
        this.shouldHandleAppRatingPopUp = shouldHandleAppRatingPopUp;
    }

    public void swipeUntilElementDisplayed(MobileElement element, SwipeDirection direction) {
        Log.info("Swiping until element is displayed");
        boolean isDisplayed;
        do {
            isDisplayed = isElementDisplayed(element);
            if (!isDisplayed)
                swipe(direction);
        } while (!isDisplayed);
        Log.info("Element was found");
    }

    public void swipeUntilNestedElementIsDisplayed(MobileElement parent, By childLocator) {
        Log.info("Swiping until nested element is displayed");
        long startTime = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(getDriver(),2);
        boolean isDisplayed;
        do {
            isDisplayed = isNestedElementDisplayed(parent, childLocator, wait);
            if(!isDisplayed)
                swipe(SwipeDirection.DOWN);
        } while (!isDisplayed && System.currentTimeMillis()-startTime <= 30*1000);
        if (isDisplayed)
            Log.info("Nested element was found");
        else
            Log.info("Quiting - couldn't find nested element");
    }

    private boolean isNestedElementDisplayed(MobileElement parent, By childLocator, WebDriverWait wait) {
        boolean isDisplayed = false;
        try {
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, childLocator));
            isDisplayed = true;
        } catch (TimeoutException e) {
            Log.info("Nested element is not displayed");
        }
        return isDisplayed;
    }

    public List<WebElement> getNestedElements(MobileElement parent, By childLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(),3);
            return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, childLocator));
        } catch (TimeoutException e) {
            return Collections.emptyList();
        }
    }


    public void longSwipe(SwipeDirection swipeDirection) {
        Log.info("Swiping " + swipeDirection + "...");
        Dimension size = getDriver().manage().window().getSize();
        int startX,startY,endY;
        switch (swipeDirection){
            case DOWN:
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * 0.8);
                endY = 0;
                performSwipe(startX, startY, endY);
                break;
            case UP:
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * 0.2);
                endY = (int) (size.getHeight() * 0.8);
                performSwipe(startX, startY, endY);
                break;
        }
    }

    public void swipe(SwipeDirection swipeDirection) {
        Log.info("Swiping " + swipeDirection + "...");
        Dimension screenSize = getDriver().manage().window().getSize();
        switch (swipeDirection){
            case DOWN:
                int startX = screenSize.getWidth() / 2;
                int startY = startX;
                performSwipe(startX, startY, 0);
                break;
        }
    }

    public void swipeInElement(MobileElement element, SwipeDirection swipeDirection) {
        Log.info("Swiping " + swipeDirection + " in element...");
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        switch (swipeDirection){
            case DOWN:
                new TouchAction(getDriver())
                        .press(point(x, y))
                        .waitAction(waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(x, 0)).release().perform();
                break;
        }
    }

    public void verifyThatConditionIsTrue(boolean condition, String message, String errorMessage){
        Log.info("Verifying that " + message);
        Assert.assertTrue(condition, errorMessage);
    }

    public void verifyThatConditionIsFalse(boolean condition, String message, String errorMessage){
        Log.info("Verifying that " + message);
        Assert.assertFalse(condition, errorMessage);
    }

    public void verifyTextMessage(MobileElement element, String expectedText) {
        Log.info("Verifying text message");
        String actualText = element.getText().toLowerCase();
        Log.info(getMessageBasedOnCondition(() -> actualText.equals(expectedText.toLowerCase()), actualText));
        Assert.assertEquals(expectedText.toLowerCase(), actualText);
    }

    private String getMessageBasedOnCondition(BooleanSupplier condition, String text){
        String value = text.contains("Page") ? "page" : "message";
        if (condition.getAsBoolean())
            return "Actual " + value + " is " + text + " as expected";
        else
            return "FAIL!!! - Actual " + value + " is not " + text + " as expected";
    }

    public void verifyLastMessageInList(List<MobileElement> list, String expectedText){
        String actualText = list.get(list.size()-1).getText().toLowerCase();
        Log.info(actualText.equals(expectedText.toLowerCase()) ? "Actual last message is " + actualText + " as expected" : "FAIL!!! - actual last message is not " + actualText + " as expected");
        Assert.assertEquals(expectedText.toLowerCase(), actualText);
    }

    public void clickOnElement(MobileElement element) {
        clickOnElement(element, secondsToWait);
    }

    public void clickOnElement(MobileElement element, int waitInSeconds) {
        Log.info("Trying to click on element " + element);
        boolean isClickable = isElementClickable(element, waitInSeconds);
        if (isClickable) {
            element.click();
            Log.info("Clicked on element " + element);
        } else
            Log.error("Wasn't able to click on element " + element);
    }

    public void setText(MobileElement element, String text) {
        setText(element, text, true);
    }

    public void setText(MobileElement element, String text, boolean hideKeyBoard) {
        boolean isDisplayed = isElementDisplayed(element);
        if (isDisplayed) {
            element.sendKeys(text);
            if(hideKeyBoard)
                getDriver().hideKeyboard();
            Log.info("Set text: " + text + " on element " + element);
        } else
            Log.error("Couldn't set text on element " + element);
    }

    public void waitForElementToBeDisplayed(MobileElement element, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element), waitInSeconds);
        } catch (Exception e) {
            Log.error("Element " + element + " is not displayed");
        }
    }

    public void waitForElementToBeDisplayed(MobileElement element) {
        waitForElementToBeDisplayed(element, secondsToWait);
    }

    public boolean isElementChecked(MobileElement element) {
        return Boolean.valueOf(element.getAttribute("Checked"));
    }

    public boolean isElementEnabled(MobileElement element){
        return element.isEnabled();
    }
    public boolean isElementDisplayed(MobileElement element, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element), waitInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(MobileElement element) {
        return isElementDisplayed(element, secondsToWait);
    }

    public boolean isElementClickable(MobileElement element, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(element), waitInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementClickable(MobileElement element) {
        return isElementClickable(element, secondsToWait);
    }


    public void verifyToastMessage(String toast) {
        Log.info("Verifying toast message...");
        long startTime = System.currentTimeMillis();
        boolean isPageContainsToast;
        do{
            String pageXmlHierarchy = getDriver().getPageSource().toLowerCase();
            isPageContainsToast = pageXmlHierarchy.contains(toast.toLowerCase());
        }while(!isPageContainsToast && System.currentTimeMillis()-startTime <= 5*1000);
        verifyThatConditionIsTrue(isPageContainsToast,"toast " + toast + " appears", "toast " + toast + " didn't appear");
    }

    public void installApp() {
        initCapabilities();
        setApplicationPath(mobilePlatform);
        capabilities.setCapability("app", applicationPath.getAbsolutePath());
        initDriver(propertiesReader.getUrl(), capabilities, mobilePlatform);
    }

    public void tearDown() {
        Log.info("Closing session...");
        Optional<AppiumDriver> driver = Optional.ofNullable(getDriver());
        if(driver.isPresent())
            getDriver().quit();
    }

    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }

    public void longPressOnElement(MobileElement element, int seconds){
        new TouchAction(getDriver()).longPress(LongPressOptions.longPressOptions()
                .withElement (ElementOption.element (element)))
                .waitAction(waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void waitForTextToBePresentInElement(MobileElement unitOnProfile, String unit, int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(),seconds);
        wait.until(ExpectedConditions.textToBePresentInElement(unitOnProfile,unit));
    }

    public void setDeviceLocation() {
        double[] coordinates = getDeviceCoordinates();
        setLocation(coordinates[0], coordinates[1], coordinates[2]);
    }

    public void sleep(long timeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isElementInvisible(By locator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(),timeInSeconds);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void setLocation(double longitude, double latitude, double altitude) {
        getDriver().setLocation(new Location(longitude, latitude, altitude));
    }

    protected void tapByScreenCoordinates(int x, int y){
        Log.info("Tapping by coordinates");
        new TouchAction(getDriver())
                .tap(PointOption.point(x, y))
                .perform();
    }

    private void waitFor(ExpectedCondition<WebElement> webElementExpectedCondition, int waitInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), waitInSeconds);
        wait.until(webElementExpectedCondition);
    }

    private AbstractPage getCurrentPageAsObject(String pageName) {
        Page page = Page.valueOf(pageName);
        return page.getPage(this);
    }

    private double[] getDeviceCoordinates() {
        Location location = getDriver().location();
        double[] coordinates = new double[3];
        try {
            coordinates[0] = location.getLongitude();
            coordinates[1] = location.getLatitude();
            coordinates[2] = location.getAltitude();
        } catch (WebDriverException e) {
            Log.warn("Cannot retrieve the current geo coordinates from the device");
        }
        return coordinates;
    }

    private void performSwipe(int startX, int startY, int endY) {
        new TouchAction(getDriver())
                .press(point(startX, startY))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(startX, endY)).release().perform();
    }
    
    private void setApplicationPath(MobilePlatform mobilePlatform) {
        File workingDirectory = new File(System.getProperty("user.dir"));
        String path = workingDirectory + "/applications/";
        if (mobilePlatform.equals(MobilePlatform.ANDROID))
            applicationPath = new File(path + "/" + this.applicationName + ".apk");
    }

    private void handleWelcomePage() {
        WelcomePage welcomePage = new WelcomePage(this);
        if (welcomePage.verifyPage()){
            Log.info("handling welcome page");
            welcomePage.acceptTermsOfService();
            shouldHandleWelcomePage = false;
        }
    }

    private void handleCoachMarks() {
        CoachMarks coachMarks = new CoachMarks(this);
        coachMarks.handleCoachMarks(3,0);
    }

    private void handleAppRanking() {
        AppMessages messages = new AppMessages(this);
        messages.handleAppRatingPopUp();
    }

    public abstract void initCapabilities();
    protected abstract void addInstalledAppCapabilities();
    public abstract void takePicture();
    public abstract void clickEnter();
    public abstract void disableCoachMarks();

}
