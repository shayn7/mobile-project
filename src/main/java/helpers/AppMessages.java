package helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class AppMessages implements Helper{

    private AbstractPlatform platform;

    public AppMessages(AbstractPlatform platform) {
        this.platform = platform;
        PageFactory.initElements(new AppiumFieldDecorator(platform.getDriver()),this);
    }

    public void handlePendingMediaPopUp() {
        Log.info("Inside handlePendingMediaPopUp()");
        boolean isDisplayed = platform.isElementDisplayed(activateButton,3);
        if (isDisplayed)
            platform.clickOnElement(activateButton);
        else
            Log.info("Activate button element is not displayed");
    }

    public void handleAppRatingPopUp(){
        boolean isDisplayed = platform.isElementDisplayed(rankAppMessage,1);
        if (isDisplayed){
            platform.clickOnElement(rankAppCancelButton);
            platform.setShouldHandleAppRatingPopUp(false);
        }
    }

    public final void verifyErrorMessage(String expectedText){
        Log.info("Inside verifyErrorMessage()");
        platform.verifyTextMessage(errorMessage, expectedText);
    }


    @AndroidFindBy(id = APP_PACKAGE +":id/textinput_error")
    private MobileElement errorMessage;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Activate']")
    private MobileElement activateButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/messageTv")
    private MobileElement rankAppMessage;
    @AndroidFindBy(id = APP_PACKAGE +":id/negativeTv")
    private MobileElement rankAppCancelButton;
}
