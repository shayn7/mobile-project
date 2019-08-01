package pages;

import helpers.Permissions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;

public class WelcomePage extends AbstractPage {
    
    public WelcomePage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    public void acceptTermsOfService() {
        if (!acceptTermsOfServiceButton.isSelected())
            platform.clickOnElement(acceptTermsOfServiceButton);
        platform.clickOnElement(continueButton);
        Permissions permissions = new Permissions(platform);
        permissions.grantPermissions();
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(welcomePageTitle,1);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/activity_launcher_title_tv")
    private MobileElement welcomePageTitle;
    @AndroidFindBy(id = APP_PACKAGE +":id/activity_launcher_terms_cb")
    private MobileElement acceptTermsOfServiceButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/activity_launcher_continue_fab")
    private MobileElement continueButton;
}
