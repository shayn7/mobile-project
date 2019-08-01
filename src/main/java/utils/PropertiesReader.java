package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private String deviceName;
    private String platformVersion;
    private String packageName;
    private String activityName;
    private boolean noResetValue;
    private String url;
    private String automationName;
    private String udid;
    private String bundleId;
    private String propertiesFile;
    private String coordinatesCsvFile;

    public PropertiesReader(){
        String path = setPropertiesFilePath();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path + propertiesFile));
        } catch (IOException e) {
            System.out.println("Device Configuration properties file cannot be found");
        }
        getProperties(properties);
    }

    private String setPropertiesFilePath() {
        File workingDirectory = new File(System.getProperty("user.dir"));
        String path = workingDirectory + "/src/main/resources/";
        this.propertiesFile = "env-configuration.properties";
        return path;
    }

    private void getProperties(Properties properties) {
        try {
            deviceName = properties.getProperty("deviceName");
            platformVersion = properties.getProperty("platformVersion");
            packageName = properties.getProperty("packageName");
            activityName = properties.getProperty("activityName");
            noResetValue = Boolean.valueOf(properties.getProperty("noResetValue"));
            url = properties.getProperty("url");
            automationName = properties.getProperty("automationName");
            udid = properties.getProperty("udid");
            bundleId = properties.getProperty("bundleId");
            coordinatesCsvFile = properties.getProperty("coordinatesCsvFile");
        } catch (Exception e) {
            System.out.println("Exception while trying to get properties from " + propertiesFile);
            e.printStackTrace();
        }
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public boolean getNoResetValue() {
        return noResetValue;
    }

    public String getUrl() {
        return url;
    }

    public String getAutomationName() {
        return automationName;
    }

    public String getUdid() {
        return udid;
    }

    public String getBundleId() {
        return bundleId;
    }

    public String getCoordinatesCsvFile() {
        return coordinatesCsvFile;
    }

}
