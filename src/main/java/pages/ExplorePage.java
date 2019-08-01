package pages;

import enums.SwipeDirection;
import enums.TileType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobilePlatforms.AbstractPlatform;
import utils.Log;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class ExplorePage extends AbstractPage{

    private static boolean isEmptyHeartWasClicked = false;
    private boolean isPopUpMessageDisplayed = false;
    private static String tileName;

    public ExplorePage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(letsTravelText,2);
    }

    public void addTileToFavorites(String type) {
        Log.info("Inside addTileToFavorites()");
        TileType tileType = TileType.valueOf(type.toUpperCase());
        switch (tileType){
            case TREK:
                handleAddingTileToFavorites(treks,By.id(APP_PACKAGE +":id/tvName"));
                break;
            case SITE:
                handleAddingTileToFavorites(sites, By.id(APP_PACKAGE + ":id/listitem_name_tv"));
                break;
        }
    }

    private void handleAddingTileToFavorites(List<MobileElement> elements, By locator) {
        do {
            platform.swipe(SwipeDirection.DOWN);
            if(!elements.isEmpty()){
                for(MobileElement element : elements){
                    platform.swipeUntilNestedElementIsDisplayed(element, locator);
                    if (clickOnEmptyHeartIcon(element, locator))
                        break;
                    if (isPopUpMessageDisplayed)
                        break;
                }
            }
        } while (!isEmptyHeartWasClicked && !isPopUpMessageDisplayed);
    }

    public static String getTileName() {
        return tileName;
    }

    public static boolean isEmptyHeartWasClicked() {
        return isEmptyHeartWasClicked;
    }

    public void verifyIfPopupMessageDisplayed(String popupMessage) {
        Log.info("Inside verifyIfPopupMessageDisplayed()");
        platform.verifyTextMessage(pleaseLoginPopUp,popupMessage);
    }
    private boolean clickOnEmptyHeartIcon(MobileElement tile, By locator) {
        Log.info("Inside clickOnEmptyHeartIcon()");
        boolean isEmptyHeartDisplayed = isEmptyHeartDisplayed(tile);
        if(isEmptyHeartDisplayed){
            platform.clickOnElement(tile.findElementByAccessibilityId("rvItem_iv_plus"));
            isPopUpMessageDisplayed = platform.isElementDisplayed(pleaseLoginPopUp,3);
            if (handleIfPopUpMessageNotDisplayed(tile,locator))
                return true;
        }
        return false;
    }

    private boolean handleIfPopUpMessageNotDisplayed(MobileElement tile, By locator) {
        Log.info("Inside handleIfPopUpMessageNotDisplayed()");
        if (!isPopUpMessageDisplayed){
            Log.info("Popup message was not displayed");
            Log.info("Empty heart was clicked");
            isEmptyHeartWasClicked = true;
            verifyThatFavoriteIconChangedStatus(tile);
            List<WebElement> listOfNestedElements = platform.getNestedElements(tile,locator);
            listOfNestedElements.stream().findFirst().ifPresent(e-> tileName = e.getText());
            Log.info("Added " + tileName +" to favorites");
            return true;
        }
        return false;
    }

    private void verifyThatFavoriteIconChangedStatus(MobileElement trek) {
        Log.info("Inside verifyThatFavoriteIconChangedStatus()");
        try{
            boolean isFavoritesIconChangedStatus = platform.isElementDisplayed(trek.findElementByAccessibilityId("rvItem_iv_minus"));
            if(isFavoritesIconChangedStatus)
                Log.info("Favorites icon changed status");
            else
                Log.warn("Favorites icon wasn't change status as expected");
        }catch (NoSuchElementException e){
            Log.warn("Could not find the element");
        }
    }


    private boolean isEmptyHeartDisplayed(MobileElement tile) {
        Log.info("Inside isEmptyHeartDisplayed()");
        boolean isEmptyHeartDisplayed = false;
        try {
            tile.findElementByAccessibilityId("rvItem_iv_plus");
            isEmptyHeartDisplayed = true;
            Log.info("Empty heart was displayed");
        }catch (NoSuchElementException e){
            Log.warn("Skipped - already in favorites");
        }
        return isEmptyHeartDisplayed;
    }

    public void weClickOnListOption(String option) {
        Log.info("Inside weClickOnListOption()");
        List<MobileElement> list = listOption.stream().filter(item->item.getText().equals(option)).collect(Collectors.toList());
        platform.clickOnElement(list.get(0));
    }

    public void clickOnTheChatIcon() {
        Log.info("Inside clickOnTheChatIcon()");
        platform.clickOnElement(chatIcon);
    }

    public void searchFor(String anything) {
        Log.info("Inside searchFor()");
        platform.clickOnElement(searchBtn);
        platform.setText(searchField,anything,false);
    }

    @iOSXCUITFindBy(accessibility = "Let's Travel")
    @AndroidFindBy(id = APP_PACKAGE +":id/letsTravelTv")
    private MobileElement letsTravelText;
    @AndroidFindBy(id = APP_PACKAGE +":id/mainBottombarChatIv")
    private MobileElement chatIcon;
    @AndroidFindBy(id = APP_PACKAGE +":id/collapseMe")
    private MobileElement searchBtn;
    @AndroidFindBy(id = APP_PACKAGE +":id/search_src_text")
    private MobileElement searchField;
    @AndroidFindBy(id = APP_PACKAGE +":id/tvProfileName")
    private List<MobileElement> listOption;
    @AndroidFindBy(id = APP_PACKAGE +":id/reg_bagging")
    private MobileElement pleaseLoginPopUp;
    @AndroidFindBy(id = APP_PACKAGE +":id/reg_bagging")
    private MobileElement loginPopUpWindow;
    @AndroidFindBy(accessibility = "rvItem_ContainerTrek")
    private List<MobileElement> treks;
    @AndroidFindBy(accessibility = "rvItem_ContainerSite")
    private List<MobileElement> sites;
}
