package mobilePlatforms;

import enums.MobilePlatform;

public class IosPlatform extends AbstractPlatform {

    private String deviceName = propertiesReader.getDeviceName();
    private String platformVersion = propertiesReader.getPlatformVersion();
    private boolean noReset = propertiesReader.getNoResetValue();
    private String automationName = propertiesReader.getAutomationName();
    private String udid = propertiesReader.getUdid();
    private String bundleId = propertiesReader.getBundleId();

    public IosPlatform(String appName) {
        super(MobilePlatform.IOS, appName);
    }

    @Override
    public void initCapabilities() {
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", mobilePlatform.toString());
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("bundleId", bundleId);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("noReset", noReset);
    }

    @Override
    protected void addInstalledAppCapabilities() {

    }

    @Override
    public void takePicture() {

    }

    @Override
    public void clickEnter() {

    }

    @Override
    public void disableCoachMarks() {

    }


}
