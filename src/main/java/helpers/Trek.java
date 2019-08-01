package helpers;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;
import utils.Log;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Trek implements Helper{

    private AbstractPlatform platform;
    private String csvFile;
    private List<Double> longitude;
    private List<Double> latitude;
    private List<Double> altitude;
    private boolean isFileRead;


    public Trek(AbstractPlatform abstractPlatform) {
        platform = abstractPlatform;
        csvFile = platform.getPropertiesReader().getCoordinatesCsvFile();
        PageFactory.initElements(new AppiumFieldDecorator(abstractPlatform.getDriver()),this);
    }

    public void startTrekking(){
        getCoordinatesFromCsvFile();
        if(isFileRead){
            Log.info("Setting coordinates...");
            for(int i = 0 ; i < longitude.size() ; i++){
                platform.setLocation(longitude.get(i),latitude.get(i),altitude.get(i));
                Log.info("longitude: "+longitude.get(i)+" latitude: "+latitude.get(i)+" altitude: "+altitude.get(i));
            }
        }else{
            Log.warn("Quiting! coordinates are empty");
        }
    }


    private void getCoordinatesFromCsvFile() {
        Log.info("Getting coordinates from: " + csvFile);
        try {
            List<String> result = Files.readAllLines(Paths.get(csvFile));
            longitude = getCollect(result, 0);
            latitude = getCollect(result, 1);
            altitude = getCollect(result, 2);
            isFileRead = true;
        } catch (IOException ioe) {
            Log.error("Couldn't read the file");
        }
    }

    private List<Double> getCollect(List<String> result, int columnNumber) {
        return result.stream().skip(1)
                .map(line -> line.split(",")[columnNumber]) // split each line and map to specific column
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }
}

