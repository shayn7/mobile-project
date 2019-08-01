package pages;

import helpers.CoachMarks;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;

import java.time.LocalDateTime;

public class EmailSignUpPage extends AbstractPage {

    public EmailSignUpPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(signUpButton);
    }

    public void signUp(String email, String password, String passwordVerification) {
        Log.info("Inside signUp()");
        email = email.contains("@") ? generateEmailAtRunTime(email) : email;
        setCredentials(email, password, passwordVerification);
        platform.clickOnElement(signUpButton);
        handleCoachMarks();
    }

    private void setCredentials(String email, String password, String passwordVerification) {
        platform.setText(emailField,email);
        platform.setText(passwordField,password);
        platform.setText(confirmPasswordField,passwordVerification);
    }

    private String generateEmailAtRunTime(String email) {
        Log.info("Generating new mail...");
        String localDateTime = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
        String currentDateAndTime = localDateTime.replace(":",".");
        return email.replace("@", "+" + currentDateAndTime + "@");
    }

    private void handleCoachMarks() {
        CoachMarks coachMarks = new CoachMarks(platform);
        coachMarks.handleCoachMarks(5,1);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/btnSignUp")
    private MobileElement signUpButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/login_email_et")
    private MobileElement emailField;
    @AndroidFindBy(id = APP_PACKAGE +":id/login_password_et")
    private MobileElement passwordField;
    @AndroidFindBy(id = APP_PACKAGE +":id/login_confirm_password_et")
    private MobileElement confirmPasswordField;


}
