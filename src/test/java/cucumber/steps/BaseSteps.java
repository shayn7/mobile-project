package cucumber.steps;
import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import helpers.AppMessages;
import pages.*;
import pages.EmailSignInPage;
import pages.EmailSignUpPage;
import pages.FacebookSignInPage;
import pages.LaunchPage;


public class BaseSteps extends TestRunner implements En {

    private String email;
    private String password;
    private String passwordVerification;

    public BaseSteps() {

        Given("we open the app", () -> {
            platform.openApp();
        });

        Then("we should get to {string}", (String pageName) -> {
            platform.iShouldBeOnPage(pageName);
        });

        And("we are on {string}", (String pageName) -> {
            platform.iShouldBeOnPage(pageName);
        });

        And("email = {string} and password = {string}", (String email, String password) -> {
            this.email = email;
            this.password = password;
        });

        When("we get to the {string} sign in or sign up page", (String loginOption) -> {
            platform.iShouldBeOnPage("LaunchPage");
            platform.getPage(LaunchPage.class).loginUsing(loginOption);
        });

        When("we sign in", () -> {
            platform.iShouldBeOnPage("EmailSignInPage");
            platform.getPage(EmailSignInPage.class).signIn(email,password);
        });

        And("^we sign in to facebook$", () -> {
            platform.iShouldBeOnPage("FacebookSignInPage");
            platform.getPage(FacebookSignInPage.class).signIn(email,password);
        });

        Given("^we install the app$", () -> {
            platform.installApp();
        });

        And("^confirm password = \"([^\"]*)\"$", (String password) -> {
            this.passwordVerification = password;
        });

        And("^we sign up$", () -> {
            platform.iShouldBeOnPage("EmailSignUpPage");
            platform.getPage(EmailSignUpPage.class).signUp(email,password,passwordVerification);
        });

        Then("^we should get a \"([^\"]*)\" message$", (String errorMessage) -> {
            AppMessages messages = new AppMessages(platform);
            messages.verifyErrorMessage(errorMessage);
        });

        And("^we sign in to google$", () -> {
            platform.iShouldBeOnPage("GoogleSignIn");
            platform.getPage(GoogleSignIn.class).signIn(email,password);
        });

        And("^we click on the profile icon of the navigation bar$", () -> {
            platform.iShouldBeOnPage("ExplorePage");
            platform.getPage(AbstractPage.class).clickOnProfileButton();
        });

        Then("we should get a \"([^\"]*)\" popup", (String toast) -> {
            platform.verifyToastMessage(toast);
        });

        And("we click on profile icon in the navigation bar", () -> {
            platform.getPage(AbstractPage.class).clickOnProfileButton();
        });

        When("we click on the Favorites icon in the navigation bar", () -> {
            platform.getPage(AbstractPage.class).clickOnFavoritesButton();
        });

        And("we click on skip button", () -> {
            platform.iShouldBeOnPage("LaunchPage");
            platform.getPage(LaunchPage.class).clickOnSkipButton();
        });
        Then("we get the pop up window {string}", (String popUpMessage) -> {
            platform.getPage(ExplorePage.class).verifyIfPopupMessageDisplayed(popUpMessage);
        });

    }

}
