package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;


public class RecordPage extends AbstractPage {

    public RecordPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(centerMapButton, 3);
    }

    public void startRecording() {
        Log.info("Inside startRecording()");
        boolean isDisplayed = platform.isElementDisplayed(startRecordingButton);
        if(isDisplayed)
            platform.clickOnElement(startRecordingButton);
        else{
            platform.setDeviceLocation();
            platform.clickOnElement(startRecordingButton);
        }
    }

    public void stopRecording() {
        Log.info("Inside stopRecording()");
        platform.clickOnElement(stopRecordingButton,7);
    }

    public void clickOnAddMediaButton() {
        Log.info("Inside clickOnAddMediaButton()");
        boolean isDisplayed = platform.isElementDisplayed(addMediaButton);
        if (isDisplayed)
            platform.clickOnElement(addMediaButton);
        else {
            startRecording();
            platform.clickOnElement(addMediaButton);
        }
    }

    public void clickOnAddPictureButton() {
        Log.info("Inside clickOnAddPictureButton()");
        platform.clickOnElement(addPictureButton);
    }

    public void confirmAddingPicture() {
        Log.info("Inside confirmAddingPicture()");
        platform.clickOnElement(addPictureOkButton,7);
    }

    public void finishTrek() {
        Log.info("Inside finishTrek()");
        platform.clickOnElement(finishTrekButton);
    }

    @AndroidFindBy(id = APP_PACKAGE +":id/centerMap")
    private MobileElement centerMapButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/record_button_main_btn")
    private MobileElement startRecordingButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/record_button_main_foreground")
    private MobileElement stopRecordingButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/fabAddAudioMenu")
    private MobileElement addMediaButton;
    @AndroidFindBy(id = APP_PACKAGE +":id/fabAddQuickSpot")
    private MobileElement addPictureButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'OK')]")
    private MobileElement addPictureOkButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Finish']")
    private MobileElement finishTrekButton;


}
