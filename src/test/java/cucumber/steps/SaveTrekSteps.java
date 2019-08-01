package cucumber.steps;

import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import helpers.AppMessages;
import helpers.Permissions;
import helpers.Trek;
import pages.AbstractPage;
import pages.RecordPage;
import pages.TrekSummaryPage;

public class SaveTrekSteps extends TestRunner implements En {


    public SaveTrekSteps() {
        
        When("we click on record", () -> {
            platform.getPage(AbstractPage.class).clickOnRecordButton();
        });
        And("we click on Activate", () -> {
            AppMessages messages = new AppMessages(platform);
            messages.handlePendingMediaPopUp();
            platform.setDeviceLocation();
        });
        And("we click on start", () -> {
            platform.iShouldBeOnPage("RecordPage");
            platform.getPage(RecordPage.class).startRecording();
        });
        And("we click on add media", () -> {
            platform.getPage(RecordPage.class).clickOnAddMediaButton();
        });
        And("we click on add pic", () -> {
            platform.getPage(RecordPage.class).clickOnAddPictureButton();
        });
        And("we click allow", () -> {
            Permissions permissions = new Permissions(platform);
            permissions.grantPermissions();
        });
        And("we click on take a pic", () -> {
            platform.takePicture();
        });
        And("we click on the check button", () -> {
            platform.getPage(RecordPage.class).confirmAddingPicture();
        });
        And("we click on stop", () -> {
            platform.getPage(RecordPage.class).stopRecording();
        });
        And("we click on finish", () -> {
            platform.getPage(RecordPage.class).finishTrek();
            platform.iShouldBeOnPage("TrekSummaryPage");
        });
        And("we click on save", () -> {
            platform.getPage(TrekSummaryPage.class).saveTrek();
            platform.iShouldBeOnPage("SavedTrekPage");
        });
        Then("^we should start trekking$", () -> {
            Trek trek = new Trek(platform);
            trek.startTrekking();
        });
    }
}
