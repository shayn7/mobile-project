package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;

public class FacebookSignInPage extends AbstractPage {

    public FacebookSignInPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    public void signIn(String email, String password){
        Log.info("Inside signIn()");
        platform.setText(fbEmailField,email);
        platform.setText(fbPasswordField,password);
        platform.clickOnElement(fbLoginButton);
        platform.clickOnElement(continueButton);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(facebookPageTitle);
    }

    @AndroidFindBy(xpath = "//android.view.View[@text='facebook']")
    private MobileElement facebookPageTitle;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='m_login_email']")
    private MobileElement fbEmailField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='m_login_password']")
    private MobileElement fbPasswordField;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Log In']")
    private MobileElement fbLoginButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
    private MobileElement continueButton;

}
