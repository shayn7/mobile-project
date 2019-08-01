package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.testng.Assert;
import utils.Log;
import java.util.List;

public class ChatPage extends AbstractPage{

    private String userNameInFirstChatBox;

    public ChatPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(chatsTab);
    }

    public String getUserName() {
        Log.info("Inside getUserName()");
        return chatName.stream().findFirst().get().getText();
    }

    public void clickOnChatsTab() {
        Log.info("Inside clickOnChatsTab()");
        platform.clickOnElement(chatsTab);
    }
    public void clickOnFirstChatBox() {
        Log.info("Inside clickOnFirstChatBox()");
        platform.clickOnElement(firstChatBox);
    }

    public void getUserNameOfFirstChatBox() {
        userNameInFirstChatBox = getUserName();
    }

    public void verifyTheScrollWorked() {
        Log.info("Inside verifyTheScrollWorked()");
        Assert.assertNotEquals(getUserName(), userNameInFirstChatBox, "Wasn't able to scroll");
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHATS']")
    private MobileElement chatsTab;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc='position = 0']")
    private MobileElement firstChatBox;
    @AndroidFindBy(id = APP_PACKAGE +":id/smReceivers")
    private List<MobileElement> chatName;

}