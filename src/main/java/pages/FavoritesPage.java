package pages;

import enums.SwipeDirection;
import enums.TileType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class FavoritesPage extends AbstractPage {

    public void setNameOfRemovedTile(String nameOfRemovedTile) {
        this.nameOfRemovedTile = nameOfRemovedTile;
    }

    private String nameOfRemovedTile;
    private By nameLocator = By.id(APP_PACKAGE +":id/tvName");

    public FavoritesPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }


    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(favoritesPageTitle);
    }

    public void removeTileFromFavorites(String tile) {
        Log.info("Inside removeTileFromFavorites()");
        TileType tileType = TileType.valueOf(tile.toUpperCase());
        switch (tileType){
            case TREK:
                handleRemoveTileFromFavorites(treks,nameLocator);
                break;
        }
    }

    private void handleRemoveTileFromFavorites(List<MobileElement> elements, By locator) {
        while(elements.isEmpty())
            platform.swipe(SwipeDirection.DOWN);
        elements.stream()
                .findAny()
                .ifPresent(e -> {
                    platform.swipeUntilNestedElementIsDisplayed(e, locator);
                    List<WebElement> listOfNestedElements = platform.getNestedElements(e, locator);
                    setNameOfRemovedTile(listOfNestedElements.get(0).getText());
                    Log.info("Removed trek name is: " + nameOfRemovedTile);
                    platform.clickOnElement(e.findElementByAccessibilityId("rvItem_iv_minus"));
                });
    }


    public void verifyIfTileNotDisplayedInFavorites(String tile) {
        Log.info("Inside verifyIfTileNotDisplayedInFavorites()");
        TileType tileType = TileType.valueOf(tile.toUpperCase());
        platform.longSwipe(SwipeDirection.UP);
        switch (tileType){
            case TREK:
                platform.verifyThatConditionIsFalse(isTileFoundInFavorites(treks,nameLocator,nameOfRemovedTile), "Trek not displayed in favorites", "Trek displayed in favorites - not as expected");
                break;
        }
    }

    public void verifyIfTileDisplayedInFavorites(String tile) {
        Log.info("Inside verifyIfTileDisplayedInFavorites()");
        TileType tileType = TileType.valueOf(tile.toUpperCase());
        String tileName = ExplorePage.getTileName();
        switch (tileType){
            case TREK:
                Log.info("Expected trek name is: " + tileName);
                platform.verifyThatConditionIsTrue(isTileFoundInFavorites(treks,nameLocator,tileName), "Trek displayed in favorites", "Trek not displayed in favorites as expected");
                break;
            case SITE:
                break;
        }
    }

    private boolean isTileFoundInFavorites(List<MobileElement> elements, By locator, String expectedName) {
        Log.info("Inside isTileFoundInFavorites()");
        AtomicBoolean isTileFound = new AtomicBoolean(false);
        long startTime = System.currentTimeMillis();
        while (!isTileFound.get() && System.currentTimeMillis()-startTime <= 15*1000) {
            platform.swipe(SwipeDirection.DOWN);
            if (!elements.isEmpty()){
                for (MobileElement element : elements) {
                    platform.swipeUntilNestedElementIsDisplayed(element, locator);
                    List<WebElement> listOfNestedElements = platform.getNestedElements(element, locator);
                    listOfNestedElements.stream()
                            .findFirst()
                            .ifPresent(e -> {
                                Log.info("Actual name found is: " + e.getText());
                                if (e.getText().equals(expectedName))
                                    isTileFound.set(true);
                            });
                }
            }
        }
        return isTileFound.get();
    }

    @AndroidFindBy(accessibility = "rvItem_ContainerTrek")
    private List<MobileElement> treks;
    @AndroidFindBy(accessibility = "rvItem_ContainerSite")
    private List<MobileElement> sites;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Favorites']")
    private MobileElement favoritesPageTitle;

}
