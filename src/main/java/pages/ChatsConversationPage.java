package pages;

import enums.SwipeDirection;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.testng.Assert;
import utils.Log;

import java.util.List;

public class ChatsConversationPage extends AbstractPage {
    private String message;

    public ChatsConversationPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(msgBox);
    }

    public void weType(String message) {
        Log.info("Inside weType()");
        this.message = message;
        platform.setText(msgBox, message);
    }

    public void weClickSendBtn() {
        Log.info("Inside weClickSendBtn()");
        platform.clickOnElement(sendMsgBtn);
    }

    public void verifyThatMessageWasSent() {
        Log.info("Inside verifyThatMessageWasSent()");
        platform.verifyLastMessageInList(messagesInChat, message);
    }

    public void weClickBack() {
        Log.info("Inside weClickBack()");
        platform.clickOnElement(backBtn);
    }

    public void weClickOnAddAttachment() {
        Log.info("Inside weClickOnAddAttachment()");
        platform.clickOnElement(addAttachment);

    }

    public void weSendTheAttachment() {
        Log.info("Inside weSendTheAttachment()");
        platform.clickOnElement(sendTheAttachment);

    }

    public void recordAudioMessage() {
        Log.info("Inside recordAudioMessage()");
        platform.longPressOnElement(audioButton, 4);
    }

    public void clickOnCameraIcon() {
        Log.info("Inside clickOnCameraIcon()");
        platform.clickOnElement(cameraIcon);

    }

    public void verifyThatImageWasSent() {
        Log.info("Inside verifyThatImageWasSent()");
        Assert.assertTrue(!mediaViewList.isEmpty());
    }

    public void clickOnConversationMenu() {
        Log.info("Inside clickOnConversationMenu()");
        platform.clickOnElement(conversationMenu);
    }

    public void deleteConversation() {
        Log.info("Inside deleteConversation()");
        platform.clickOnElement(deleteButton);
        platform.clickOnElement(deleteButtonConfirmation);
    }

    public void verifyAudioMessageWasSent() {
        Log.info("Inside verifyAudioMessageWasSent()");
        boolean isDisplayed = platform.isElementDisplayed(audioMessage);
        Assert.assertTrue(isDisplayed, "Audio message is not displayed");
    }



    @AndroidFindBy(id = APP_PACKAGE + ":id/conversation_message")
    private MobileElement msgBox;
    @AndroidFindBy(id = APP_PACKAGE + ":id/conversation_send")
    private MobileElement sendMsgBtn;
    @AndroidFindBy(id = "com.recntrek.staging:id/message")
    private List<MobileElement> messagesInChat;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private MobileElement backBtn;
    @AndroidFindBy(id = APP_PACKAGE + ":id/attach_button")
    private MobileElement addAttachment;
    @AndroidFindBy(id = APP_PACKAGE + ":id/conversation_send")
    private MobileElement sendTheAttachment;
    @AndroidFindBy(id = APP_PACKAGE + ":id/record_button")
    private MobileElement audioButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Camera']")
    private MobileElement cameraIcon;
    @AndroidFindBy(id = APP_PACKAGE + ":id/preview")
    private List<MobileElement> mediaViewList;
    @AndroidFindBy(accessibility = "More options")
    private MobileElement conversationMenu;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    private MobileElement deleteButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
    private MobileElement deleteButtonConfirmation;
    @AndroidFindBy(id = APP_PACKAGE + ":id/applozic_audio_seekbar")
    private MobileElement audioMessage;


}

