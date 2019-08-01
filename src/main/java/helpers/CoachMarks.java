package helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class CoachMarks implements Helper{

    private AbstractPlatform platform;

    public CoachMarks(AbstractPlatform platform) {
        this.platform = platform;
        PageFactory.initElements(new AppiumFieldDecorator(platform.getDriver()),this);
    }

    public void handleCoachMarks(int explorePageSeconds, int launchPageSeconds) {
        boolean isExplorePageInvisible = platform.isElementInvisible(By.id(APP_PACKAGE +":id/letsTravelTv"),explorePageSeconds);
        boolean isLaunchPage = platform.isElementDisplayed(loginSignUpButton,launchPageSeconds);
        if (isExplorePageInvisible && !isLaunchPage) {
            Log.info("Coach marks appear");
            platform.disableCoachMarks();
            platform.setShouldHandleCoachMarks(false);
        }
    }

    @iOSXCUITFindBy(accessibility = "Let's Travel")
    @AndroidFindBy(id = APP_PACKAGE +":id/letsTravelTv")
    private MobileElement letsTravelText;
    @AndroidFindBy(id = APP_PACKAGE +":id/tutorial_signup_tv")
    private MobileElement loginSignUpButton;
}
