package pages;

import enums.SwipeDirection;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.testng.Assert;
import utils.Log;
import java.lang.management.PlatformLoggingMXBean;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SettingsPage extends AbstractPage {

    private static Optional<String> unitType;

    public SettingsPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(settingsHeader);
    }

    public void wePickUnit(String unit) {
        Log.info("Inside wePickUnit()");
        platform.clickOnElement(unitsButton);
        switch (unit){
            case "Mi.":
                platform.clickOnElement(unitMi);
                unitType = Optional.ofNullable(unitMi.getText());
                break;
            case "Km.":
                platform.clickOnElement(unitKm);
                unitType = Optional.ofNullable(unitKm.getText());
                break;
        }
    }

    public void clickOnSignOutButton() {
        Log.info("Inside clickOnSignOutButton()");
        platform.longSwipe(SwipeDirection.DOWN);
        platform.clickOnElement(signOutButton, 20);
    }

    public void clickOnAboutButton() {
        Log.info("Inside clickOnAboutButton()");
        platform.longSwipe(SwipeDirection.DOWN);
        platform.clickOnElement(aboutButton);
    }

    public void clickOnReturnArrow() {
        Log.info("Inside clickOnReturnArrow()");
        platform.clickOnElement(settingsReturnArrow);
    }

    public void clickOnLanguageSpinner() {
        Log.info("Inside clickOnLanguageSpinner()");
        platform.clickOnElement(languageSpinner);
    }

    public void clickOnKeepScreenOn() {
        Log.info("Inside clickOnKeepScreenOn()");
        platform.clickOnElement(keepScreenOnWhileRecordingButton);
    }

    public void clickOnSyncWifi() {
        Log.info("Inside clickOnSyncWifi()");
        platform.clickOnElement(syncOnlyWhenWifiEnabledButton);
    }

    public void clickOnShowOtherUsersOnMap() {
        Log.info("Inside clickOnShowOtherUsersOnMap()");
        platform.clickOnElement(showOtherUsersOnMapButton);
    }

    public void clickOnOthersCanSeeMeOnTheMap() {
        Log.info("Inside clickOnOthersCanSeeMeOnTheMap()");
        platform.clickOnElement(othersCanSeeMeOnTheMapButton);
    }

    public void clickOnAllowUsersToFollowMe() {
        Log.info("Inside clickOnAllowUsersToFollowMe()");
        platform.clickOnElement(allowUsersToFollowMeButton);
    }

    public void clickOnAllowToTakePicturesWithExternalCamera() {
        Log.info("Inside clickOnAllowToTakePicturesWithExternalCamera()");
        platform.longSwipe(SwipeDirection.DOWN);
        platform.clickOnElement(allowToTakePicturesWithExternalCameraButton);
    }


    public void clickOnEnableChat() {
        Log.info("Inside clickOnEnableChat()");
        platform.clickOnElement(enableChatWithOtherUsersButton);
    }


    public void clickOnAllowNewMessages() {
        Log.info("Inside clickOnAllowNewMessages()");
        platform.clickOnElement(notificationsForNewMessagesButton);
    }


    public void verifyTheUnitChanged(String unit) {
        Log.info("Inside verifyTheUnitChanged()");
        String unitSpinnerName = unitSpinner.getText();
        Assert.assertEquals(unitSpinnerName,unit, "The unit did not change");
    }

    public void clickOnArrowTrekPrivacy() {
        Log.info("Inside clickOnArrowTrekPrivacy()");
        platform.clickOnElement(spinnerTrekPrivacy);
    }

    public void clickOnArrowMediaPrivacy() {
        Log.info("Inside clickOnArrowMediaPrivacy()");
        platform.clickOnElement(spinnerMediaPrivacy);
    }

    public void selectLanguage(String desiredLanguage) {
        Log.info("Inside selectLanguage()");
        AtomicBoolean wasClicked = new AtomicBoolean(false);
        do {
            List<MobileElement> filteredLanguageList = languageList.stream()
                    .filter(e -> e.getText().equals(desiredLanguage))
                    .collect(Collectors.toList());
            if (!filteredLanguageList.isEmpty()) {
                filteredLanguageList.stream()
                        .findFirst()
                        .ifPresent(MobileElement::click);
                wasClicked.set(true);
            } else
                platform.swipeInElement(languages, SwipeDirection.DOWN);
        } while (!wasClicked.get());
    }


    public void isElementChecked(String value) {
        Log.info("Inside isElementChecked()");
        boolean isChecked;
        switch (value){
            case "Keep screen on while recording":
                isChecked = platform.isElementChecked(keepScreenOnWhileRecordingButton);
                verifyIfChecked(!isChecked);
                break;
            case "Sync only when wifi enabled":
                isChecked = platform.isElementChecked(syncOnlyWhenWifiEnabledButton);
                verifyIfChecked(!isChecked);
                break;
            case "Show other users on map":
                isChecked = platform.isElementChecked(showOtherUsersOnMapButton);
                verifyIfChecked(!isChecked);
                break;
            case "Others can see me on the map":
                isChecked = platform.isElementChecked(othersCanSeeMeOnTheMapButton);
                verifyIfChecked(!isChecked);
                break;
            case "Allow users to follow me":
                isChecked = platform.isElementChecked(allowUsersToFollowMeButton);
                verifyIfChecked(!isChecked);
                break;
            case "Allow to take pictures with external camera":
                isChecked = platform.isElementChecked(allowToTakePicturesWithExternalCameraButton);
                verifyIfChecked(!isChecked);
                break;
            case "Enable  chat with other users":
                isChecked = platform.isElementChecked(enableChatWithOtherUsersButton);
                verifyIfChecked(!isChecked);
                break;
            case "New messages":
                isChecked = platform.isElementChecked(notificationsForNewMessagesButton);
                verifyIfChecked(!isChecked);
                break;

        }
    }

    private void verifyIfChecked(boolean isChecked) {
        platform.verifyThatConditionIsTrue(isChecked,"Element is checked","Element is not checked");
    }


    public void chooseWhoCanSeeTrek(String whoCanSeeTrek) {
        Log.info("chooseWhoCanSeeTrek");
        switch (whoCanSeeTrek) {
            case "Everyone":
                platform.clickOnElement(trekPrivacyEveryone);
                break;
            case "Only me":
                platform.clickOnElement(trekPrivacyOnlyMe);
                break;
            case "Only followers":
                platform.clickOnElement(trekPrivacyOnlyFollowers);
                break;
        }
    }

    public void confirmWhoCanSeeTrek(String whoCanSeeTrek) {
        Log.info("confirmWhoCanSeeTrek");
        String isAllowedToSeeTrek = spinnerTrekPrivacy.getText();
        Assert.assertEquals(isAllowedToSeeTrek, whoCanSeeTrek, "the trek privacy is not correct");
    }

    public void chooseWhoCanSeeMedia(String whoCanSeeMedia) {
        Log.info("chooseWhoCanSeeMedia");
        switch (whoCanSeeMedia) {
            case "Everyone":
                platform.clickOnElement(mediaPrivacyEveryone);
                break;
            case "Only me":
                platform.clickOnElement(mediaPrivacyOnlyMe);
                break;
            case "Only followers":
                platform.clickOnElement(mediaPrivacyOnlyFollowers);
                break;
        }
    }

    public void confirmWhoCanSeeMedia(String whoCanSeeMedia) {
        Log.info("confirmWhoCanSeeMedia");
        String isAllowedToSeeMedia = spinnerMediaPrivacy.getText();
        Assert.assertEquals(isAllowedToSeeMedia, whoCanSeeMedia, "the media privacy is not correct");
    }


    public void enableDisableChatBtn() {
        Log.info("Inside chatSettingsBtn()");
        platform.clickOnElement(EnableDisableChatBtn);
    }

    public void impossibleToChangeMediaPrivacy() {
        Log.info("impossibleToChangeMediaPrivacy");
        boolean isEnabled = platform.isElementEnabled(spinnerMediaPrivacy);
        platform.verifyThatConditionIsFalse(isEnabled,"element is not enabled","element is enabled");
    }

    @AndroidFindBy(id = APP_PACKAGE + ":id/toolbar_settings_tv")
    private MobileElement settingsTitle;
    @AndroidFindBy(accessibility = "Navigate up")
    private MobileElement settingsReturnArrow;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_general_units_sp")
    private MobileElement unitsButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/listitem_units_spinner_tv")
    private MobileElement unitSpinner;
    @AndroidFindBy(id = APP_PACKAGE + ":id/userLanguageSpinner")
    private MobileElement languageSpinner;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_general_screen_on_sw")
    private MobileElement keepScreenOnWhileRecordingButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_coach_mark_sw")
    private MobileElement enableAppGuideButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_sync_sw")
    private MobileElement syncOnlyWhenWifiEnabledButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_privacy_users_on_map_sw")
    private MobileElement showOtherUsersOnMapButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_privacy_share_location_sw")
    private MobileElement othersCanSeeMeOnTheMapButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_privacy_following_sw")
    private MobileElement allowUsersToFollowMeButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_privacy_external_camera_sw")
    private MobileElement allowToTakePicturesWithExternalCameraButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_privacy_allow_chat_sw")
    private MobileElement enableChatWithOtherUsersButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_notifications_messages_sw")
    private MobileElement notificationsForNewMessagesButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/aboutTextView")
    private MobileElement aboutButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/contactUsTextView")
    private MobileElement contactUsButton;
    @AndroidFindBy(id = APP_PACKAGE + ":id/settings_sign_out_bt")
    private MobileElement signOutButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mi.']")
    private MobileElement unitMi;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Km.']")
    private MobileElement unitKm;
    @AndroidFindBy(id = APP_PACKAGE + ":id/spPrivacy")
    private MobileElement spinnerTrekPrivacy;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Everyone']")
    private MobileElement trekPrivacyEveryone;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Only me']")
    private MobileElement trekPrivacyOnlyMe;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Only followers']")
    private MobileElement trekPrivacyOnlyFollowers;
    @AndroidFindBy(id = APP_PACKAGE + ":id/spMediaPrivacy")
    private MobileElement spinnerMediaPrivacy;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Everyone']")
    private MobileElement mediaPrivacyEveryone;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Only me']")
    private MobileElement mediaPrivacyOnlyMe;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Only followers']")
    private MobileElement mediaPrivacyOnlyFollowers;
    @AndroidFindBy(id = APP_PACKAGE +":id/toolbar_settings_tv")
    private MobileElement settingsHeader;
    @AndroidFindBy(id = APP_PACKAGE +":id/settings_privacy_allow_chat_sw")
    private MobileElement EnableDisableChatBtn;
    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    private List<MobileElement> languageList;
    @AndroidFindBy(xpath = "//android.widget.ListView")
    private MobileElement languages;

}
