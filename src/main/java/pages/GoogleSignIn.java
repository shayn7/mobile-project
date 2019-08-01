package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;

import java.util.List;

public class GoogleSignIn extends AbstractPage {

    public GoogleSignIn(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    public void signIn(String email, String password) {
        Log.info("Inside signIn()");
        if (platform.isElementDisplayed(googleSignInTitlePage)) {
            boolean wasClicked = false;
            for (MobileElement account : accountName){
                String text = account.getText();
                if (text.equals(email)){
                    platform.clickOnElement(account);
                    wasClicked = true;
                    break;
                }
            }
            if(!wasClicked){
                platform.clickOnElement(useAnotherAccountButton);
                performSignIn(email, password);
            }
        } else {
            performSignIn(email, password);
        }
    }

    private void performSignIn(String email, String password) {
        platform.setText(googleEmailField, email);
        platform.clickOnElement(googleEmailNextButton);
        platform.setText(googlePasswordField, password);
        platform.clickOnElement(googlePasswordNextButton);
        platform.clickOnElement(iAgreeButton);
    }

    @Override
    public boolean verifyPage() {
         if (platform.isElementDisplayed(googleSignInTitlePage) || platform.isElementDisplayed(googleSignInFirstTitlePage)){
             return true;
         }
         else{
             return false;
         }
    }

    @AndroidFindBy(id = "com.google.android.gms:id/main_title")
    private MobileElement googleSignInTitlePage;

    @AndroidFindBy(id = "com.google.android.gms:id/account_name")
    private List<MobileElement> accountName;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign in']")
    private MobileElement googleSignInFirstTitlePage;

    @AndroidFindBy(id = "identifierId")
    private MobileElement googleEmailField;

    @AndroidFindBy(id = "identifierNext")
    private MobileElement googleEmailNextButton;

    @AndroidFindBy(id = "passwordNext")
    private MobileElement googlePasswordNextButton;

    @AndroidFindBy(id = "password")
    private MobileElement googlePasswordField;

    @AndroidFindBy(id = "com.google.android.gms:id/add_account_chip_title")
    private MobileElement useAnotherAccountButton;

    @AndroidFindBy(id = "signinconsentNext")
    private MobileElement iAgreeButton;



}
