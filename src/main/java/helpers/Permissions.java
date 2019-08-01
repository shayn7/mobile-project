package helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class Permissions implements Helper{

    private AbstractPlatform platform;

    public Permissions(AbstractPlatform platform){
        this.platform = platform;
        PageFactory.initElements(new AppiumFieldDecorator(platform.getDriver()),this);
    }


    public final void grantPermissions() {
        boolean isDisplayed;
        do {
            isDisplayed = platform.isElementDisplayed(allowButton);
            if(isDisplayed){
                platform.clickOnElement(allowButton);
                Log.info("Granted permissions");
            }
        } while (isDisplayed);
    }


    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private MobileElement allowButton;
}
