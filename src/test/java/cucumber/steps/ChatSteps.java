package cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import enums.SwipeDirection;
import pages.ChatPage;
import pages.ChatsConversationPage;
import pages.ExplorePage;
import pages.SettingsPage;

public class ChatSteps extends TestRunner implements En {
    @When("we click on the chat icon")
    public void weClickOnTheChatIcon() {
        platform.iShouldBeOnPage("ExplorePage");
        platform.getPage(ExplorePage.class).clickOnTheChatIcon();
    }

    @And("we click on the chat tab")
    public void weClickOnTheChatTab() {
        platform.iShouldBeOnPage("ChatPage");
        platform.getPage(ChatPage.class).clickOnChatsTab();
    }

    @When("we click on the first chat box")
    public void clickOnFirstChatBox() {
        platform.getPage(ChatPage.class).clickOnFirstChatBox();

    }

    @And("we type {string}")
    public void weType(String message) {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).weType(message);
    }

    @And("we click send")
    public void weClickSend() {
        platform.getPage(ChatsConversationPage.class).weClickSendBtn();

    }

    @Then("we see that the message was sent")
    public void weSeeThatTheMessageWasSent() {
        platform.getPage(ChatsConversationPage.class).verifyThatMessageWasSent();
    }

    @And("we click back")
    public void weClickBack() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).weClickBack();
    }

    @When("we click on add attachment")
    public void weClickOnAddAttachment() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).weClickOnAddAttachment();

    }

    @And("we send the attachment")
    public void weSendTheAttachment() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).weSendTheAttachment();
    }

    @When("we record audio message")
    public void recordAudioMessage() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).recordAudioMessage();
    }

    @When("we change chat setting")
    public void weChangeChatSetting() {
        platform.iShouldBeOnPage("SettingsPage");
        platform.getPage(SettingsPage.class).enableDisableChatBtn();

    }

    @And("we take a picture")
    public void takePicture() {
        platform.takePicture();
    }

    @And("we click on the camera icon")
    public void weClickOnTheCameraIcon() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).clickOnCameraIcon();
    }

    @And("we confirm the picture")
    public void weConfirmThePicture() {
        platform.clickEnter();
    }

    @Then("we see that the image was sent")
    public void weSeeThatTheImageWasSent() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).verifyThatImageWasSent();
    }

    @And("we click on conversation menu")
    public void weClickOnConversationMenu() {
        platform.iShouldBeOnPage("ChatsConversationPage");
        platform.getPage(ChatsConversationPage.class).clickOnConversationMenu();
    }

    @And("we delete the conversation")
    public void weDeleteTheConversation() {
        platform.getPage(ChatsConversationPage.class).deleteConversation();
    }

    @Then("we see that the audio was sent")
    public void weSeeThatTheAudioWasSent() {
        platform.getPage(ChatsConversationPage.class).verifyAudioMessageWasSent();
    }

    @And("we scroll down")
    public void weScrollDown() {
        platform.swipe(SwipeDirection.DOWN);
    }

    @Then("we should see a different user in the first chat box")
    public void weShouldSeeADifferentUserInTheFirstChatBox() {
        platform.getPage(ChatPage.class).verifyTheScrollWorked();
    }

    @And("we get the user name of the first chat")
    public void weGetTheUserNameOfTheFirstChat() {
        platform.iShouldBeOnPage("ChatPage");
        platform.getPage(ChatPage.class).getUserNameOfFirstChatBox();
    }
}
