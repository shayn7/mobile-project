package cucumber.steps;

import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import pages.ProfilePage;
import pages.SettingsPage;

public class SettingsSteps extends TestRunner implements En {

    public SettingsSteps() {

        When("we click on the Settings icon in the Profile page", () -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).clickOnSettingsIcon();
        });


        When("we click on the sign out button", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnSignOutButton();
        });

        When("we click on the About button", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnAboutButton();
        });

        And("we click on the return arrow of the settings page", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnReturnArrow();
        });

        When("we click on Language spinner", () -> {

            platform.getPage(SettingsPage.class).clickOnLanguageSpinner();
        });

        When("we click on the yellow button for Keep screen on while recording", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnKeepScreenOn();
        });

        When("we click on the yellow button for Sync only when wifi enabled", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnSyncWifi();
        });

        When("we click on the yellow button for Show other users on map", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnShowOtherUsersOnMap();
        });

        And("^we pick \"([^\"]*)\" unit$", (String unit) -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).wePickUnit(unit);
        });
        And("we click on the yellow button for Others can see me on the map", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnOthersCanSeeMeOnTheMap();
        });
        And("we click on the yellow button for Allow users to follow me", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnAllowUsersToFollowMe();
        });
        And("we click on the yellow button for Allow to take pictures with external camera", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnAllowToTakePicturesWithExternalCamera();
        });
        And("we click on the yellow button for Enable chat with other users", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnEnableChat();
        });
        And("we click on the yellow button for New messages", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnAllowNewMessages();
        });
        And("^the status of Units changes to \"([^\"]*)\"$", (String unit) -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).verifyTheUnitChanged(unit);
        });
        Then("^the units in my profile are \"([^\"]*)\"$", (String unit) -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).verifyUnitOnProfile(unit);
        });
        When("^we click on the yellow arrow of the Trek Privacy$", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnArrowTrekPrivacy();
        });
        When("^we click on the yellow arrow of the Media Privacy$", () -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).clickOnArrowMediaPrivacy();
        });

        And("^we select \"([^\"]*)\"$", (String language) -> {
            platform.getPage(SettingsPage.class).selectLanguage(language);
        });

        Then("^the \"([^\"]*)\" button changes color$", (String value) -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).isElementChecked(value);
        });

        Then("^the text in the hours section should be \"([^\"]*)\"$", (String text) -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).verifyText(text);
        });

        And("^we pick \"([^\"]*)\" privacy settings$", (String whoCanSeeMedia) -> {
            platform.getPage(SettingsPage.class).chooseWhoCanSeeMedia(whoCanSeeMedia);
        });

        Then("^the status of Trek Privacy changes to \"([^\"]*)\"$", (String whoCanSeeTrek) -> {
            platform.getPage(SettingsPage.class).confirmWhoCanSeeTrek(whoCanSeeTrek);
        });

        And("^the status of Media Privacy changes to \"([^\"]*)\"$", (String whoCanSeeMedia) -> {
            platform.getPage(SettingsPage.class).confirmWhoCanSeeMedia(whoCanSeeMedia);
        });
        And("^we set media privacy to \"([^\"]*)\"$", (String whoCanSeeMedia) -> {
            platform.iShouldBeOnPage("SettingsPage");
            platform.getPage(SettingsPage.class).chooseWhoCanSeeMedia(whoCanSeeMedia);
        });
        And("^we set trek privacy to \"([^\"]*)\"$", (String whoCanSeeTrek) -> {
            platform.getPage(SettingsPage.class).chooseWhoCanSeeTrek(whoCanSeeTrek);
        });
        Then("^we are preventing from changing the media privacy$", () -> {
            platform.getPage(SettingsPage.class).impossibleToChangeMediaPrivacy();
        });


    }
}
