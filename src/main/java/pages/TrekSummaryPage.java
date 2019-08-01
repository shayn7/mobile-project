package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;

public class TrekSummaryPage extends AbstractPage {

    public TrekSummaryPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(saveTrekButton);
    }

    public void saveTrek() {
        Log.info("Inside saveTrek()");
        platform.clickOnElement(saveTrekButton);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/save_trek")
    private MobileElement saveTrekButton;

}
