package cucumber.steps;

import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import pages.AbstractPage;
import pages.ExplorePage;
import pages.ProfilePage;

public class ProfileSteps extends TestRunner implements En {

    public ProfileSteps() {
        And("^we click on start chat$", () -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).clickOnStartChatButton();
        });
        And("we are on the Profile Page", () -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).clickOnStartChatButton();
        });
        And("^we click settings$", () -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).clickOnSettingsButton();
        });

        And("^we click on Edit profile$", () -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).EditProfileButton();
        });

        And("^we click on profile$", () -> {
            platform.iShouldBeOnPage("ExplorePage");
            platform.getPage(AbstractPage.class).clickOnProfileButton();
        });

        And("^we modify \"([^\"]*)\" to include current date and time$", (String value) -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).modifyValue(value);
        });
        Then("^\"([^\"]*)\" value is modified with current date and time$", (String value) -> {
            platform.getPage(ProfilePage.class).verifyValueHasChanged(value);
        });
     //   When("^we click on Share Profile$", () -> {
      //      platform.iShouldBeOnPage("ProfilePage");
      //      platform.getPage(ProfilePage.class).clickOnShareProfileButton();
      //  });
      //  And("^we Choose method of Sharing \"([^\"]*)\"$", (shareChoice) -> {
            // I should be on page:?
       //     platform.iShouldBeOnPage("ContentShare");
       //     platform.clickOnElement(shareChoice);
      //  });
      // Then("^we Share the User Profile link$", () -> {
      //     platform.iShouldBeOnPage("ProfilePage");
      //    platform.getPage(ProfilePage.class).clickOnWhatsAppButton();
       // });
    }
}

