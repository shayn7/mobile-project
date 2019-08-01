package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;

public class SavedTrekPage extends AbstractPage {

    public SavedTrekPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(finishedTrekText,3);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/finishTextView")
    private MobileElement finishedTrekText;
}
