package mobilePlatforms;


public class MobilePlatformFactory {

    public static synchronized AbstractPlatform getMobilePlatform(String platform, String appName) {
        if (platform.equalsIgnoreCase("android"))
            return new AndroidPlatform(appName);
        else if (platform.equalsIgnoreCase("ios"))
            return new IosPlatform(appName);
        else throw new RuntimeException(platform + " platform is not supported!");
    }
}