package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;


public class AboutPage extends AbstractPage {

    public AboutPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }


    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(aboutPage, 3);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='About']")
    private MobileElement aboutPage;
}
