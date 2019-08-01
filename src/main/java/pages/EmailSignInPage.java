package pages;

import helpers.CoachMarks;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.By;
import utils.Log;

public class EmailSignInPage extends AbstractPage{

    public EmailSignInPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(signUpTab,3);
    }

    public void signIn(String email, String password){
        Log.info("Inside signIn()");
        platform.clickOnElement(signInTab);
        platform.setText(emailField,email);
        platform.setText(passwordField,password);
        platform.clickOnElement(signInButton);
        handleCoachMarks();
    }

    private void handleCoachMarks() {
        CoachMarks coachMarks = new CoachMarks(platform);
        coachMarks.handleCoachMarks(5,1);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/btnSignUp")
    private MobileElement signUpTab;
    @AndroidFindBy(id = APP_PACKAGE +":id/tvSignInGray")
    private MobileElement signInTab;
    @AndroidFindBy(id = APP_PACKAGE +":id/login_email_et")
    private MobileElement emailField;
    @AndroidFindBy(id = APP_PACKAGE +":id/login_password_et")
    private MobileElement passwordField;
    @AndroidFindBy(id = APP_PACKAGE +":id/btnSignIn")
    private MobileElement signInButton;
}
