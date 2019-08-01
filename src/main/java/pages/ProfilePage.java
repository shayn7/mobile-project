package pages;

import enums.SwipeDirection;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.testng.Assert;
import utils.Log;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ProfilePage extends AbstractPage {

    private String userName;
    private String modifiedUserName;
    private boolean isPopUpMessageDisplayed = false;
    private String userTag;
    private String modifiedUserTag;

    public ProfilePage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }    

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(settingsButton, 3);
    }

    public void clickOnSettingsIcon() {
        Log.info("Inside clickOnSettingsIcon()");
        platform.clickOnElement(settingsButton);

    }

    public void clickOnStartChatButton() {
        Log.info("Inside clickOnStartChatButton()");
        platform.clickOnElement(startChatButton);
    }

    public void addTrekToOurFavorites(String type) {
        Log.info("Inside addTrekToOurFavorites()");
        ExplorePage explorePage = new ExplorePage(platform);
        explorePage.addTileToFavorites(type);
        }

    public void EditProfileButton() {
        Log.info("Inside EditProfileButton()");
        platform.clickOnElement(startEditModeProfile);
    }

    public void modifyValue(String value) {
        Log.info("Inside modifyValue()");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        switch (value){
            case "UserName":
                setUserName(userNameEditable.getText());
                platform.setText(userNameEditable,String.valueOf(localDateTime),false);
                platform.clickOnElement(saveUserProfile);
                setModifiedUserName(userNameEditable.getText());
                break;
            case "UserTag":
                setUserTag(userTagEditable.getText());
                platform.clickOnElement(userTagEditable);
                platform.setText(dialogMyProfile,String.valueOf(localTime),false);
                platform.clickOnElement(userTagEditOKButton);
                platform.clickOnElement(saveUserProfile);
                setModifiedUserTag(userTagEditable.getText());
                break;
        }
    }

    public void clickOnSettingsButton() {
        Log.info("Inside clickOnSettingsButton()");
        platform.clickOnElement(settingsButton);
    }

    public void verifyValueHasChanged(String value) {
        Log.info("Inside verifyValueHasChanged()");
        switch (value){
            case "UserName":
                Assert.assertNotEquals(getModifiedUserName(),getUserNameField(),"User name is not " + getModifiedUserName() + " as expected");
                Log.info("User name is changed from "+ getUserNameField()+ " to " + getModifiedUserName() + " as expected");
                break;
            case "UserTag":
                Assert.assertNotEquals(getModifiedUserTag(),getUserTagField(),"User name is not " + getModifiedUserTag() + " as expected");
                Log.info("User name is changed from "+ getUserTagField()+ " to " + getModifiedUserTag() + " as expected");
                break;
        }
    }

    public String getModifiedUserTag() {
        return modifiedUserTag;
    }

    public String getUserNameField() {
        return userName;
    }
    public String getUserTagField() {
        return userTag;
    }

    public String getModifiedUserName() {
        return modifiedUserName;
    }

    public void verifyUnitOnProfile(String unit) {
        Log.info("Inside verifyUnitOnProfile()");
        platform.swipe(SwipeDirection.UP);
        platform.waitForTextToBePresentInElement(unitOnProfile,unit,30);
        Optional<String> expectedUnit = Optional.of(unit);
        Optional<String> actualUnit = Optional.ofNullable(unitOnProfile.getText());
        Assert.assertEquals(expectedUnit,actualUnit,"The unit did not change");
    }

    public void verifyText(String text) {
        Log.info("Inside verifyText()");
        boolean isTextAsExpected = false;
        for(MobileElement element : textViews){
            if(element.getText().equals(text)){
                Log.info("Text is " + text + " as expected");
                isTextAsExpected = true;
            }
        }
        if(!isTextAsExpected)
            Log.error("Text is not " + text + " as expected");
    }

    private void setModifiedUserName(String modifiedUserName) {
        this.modifiedUserName = modifiedUserName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setUserTag(String userTag) {
        this.userTag = userTag;
    }
    private void setModifiedUserTag(String modifiedUserTag) {
        this.modifiedUserTag = modifiedUserTag;
    }


    
    @AndroidFindBy(id = APP_PACKAGE +":id/startChat")
    private MobileElement startChatButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settingsButton")
    private MobileElement settingsButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/startEditMode")
    private MobileElement startEditModeProfile;
    @AndroidFindBy(id = APP_PACKAGE +":id/userName")
    private MobileElement userNameEditable;
    @AndroidFindBy(id = APP_PACKAGE +":id/on_done")
    private MobileElement saveUserProfile;
    @AndroidFindBy(id = APP_PACKAGE +":id/aboutUser")
    private MobileElement userTagEditable;
    @AndroidFindBy(id = APP_PACKAGE +":id/dialog_myprofile_edit_aboutme_et")
    private MobileElement dialogMyProfile;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement userTagEditOKButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/user_profile_km_unit")
    private MobileElement unitOnProfile;
    @AndroidFindBy(className = "android.widget.TextView")
    private List<MobileElement> textViews;
    @AndroidFindBy(id = APP_PACKAGE +":id/shareButton")
    private MobileElement shareProfileButton;
    @AndroidFindBy(id = "android:id/button_once")
    private MobileElement justOnce;
    @AndroidFindBy(id = "android:id/button_always")
    private MobileElement buttonAlways;
    @AndroidFindBy(id = "android:id/sem_title_default")
    private MobileElement completeActionUsing;




}

