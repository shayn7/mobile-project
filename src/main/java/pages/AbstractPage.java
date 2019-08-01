package pages;


import helpers.Helper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;
import utils.Log;


public abstract class AbstractPage implements Helper {

    protected AbstractPlatform platform;

    public AbstractPage(AbstractPlatform abstractPlatform) {
        platform = abstractPlatform;
        PageFactory.initElements(new AppiumFieldDecorator(abstractPlatform.getDriver()),this);
    }

    public abstract boolean verifyPage();

    public final void clickOnRecordButton() {
        Log.info("Inside clickOnRecordButton()");
        platform.clickOnElement(recordButton);
    }

    public final void clickOnProfileButton() {
        Log.info("Inside clickOnProfileButton()");
        platform.clickOnElement(profileButton);
    }

    public final void clickOnFavoritesButton() {
        Log.info("Inside clickOnFavoritesButton()");
        platform.clickOnElement(favoritesButton);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/record_button_main_btn")
    private MobileElement recordButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/mainBottombarProfileIv")
    private MobileElement profileButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/mainBottombarWishlistIv")
    private MobileElement favoritesButton;


}
