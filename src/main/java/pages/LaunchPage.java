package pages;


import enums.LoginOptions;
import helpers.CoachMarks;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;

public class LaunchPage extends AbstractPage {
    
    public LaunchPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    public void clickOnSkipButton(){
        Log.info("Inside clickOnSkipButton()");
        platform.clickOnElement(skipButton);
        handleCoachMarks();
    }

    public void loginUsing(String option){
        Log.info("Inside loginUsing()");
        LoginOptions loginOption = LoginOptions.valueOf(option.toUpperCase());
        switch (loginOption){
            case EMAIL:
                platform.clickOnElement(loginSignUpButton);
                break;
            case FACEBOOK:
                platform.clickOnElement(facebookSignInButton);
                break;
            case GOOGLE:
                platform.clickOnElement(googleSignInButton);
                break;
        }
    }

    private void handleCoachMarks() {
        CoachMarks coachMarks = new CoachMarks(platform);
        coachMarks.handleCoachMarks(8,0);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(loginSignUpButton,2);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/tutorial_signup_tv")
    private MobileElement loginSignUpButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/tutorial_facebook_iv")
    private MobileElement facebookSignInButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/tutorial_google_iv")
    private MobileElement googleSignInButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/tutorial_skip_tv")
    private MobileElement skipButton;
}
