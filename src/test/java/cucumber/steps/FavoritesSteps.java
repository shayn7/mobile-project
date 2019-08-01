package cucumber.steps;

import cucumber.api.java8.En;
import cucumber.runner.TestRunner;
import org.testng.Assert;
import pages.AbstractPage;
import pages.ExplorePage;
import pages.FavoritesPage;
import pages.ProfilePage;


public class FavoritesSteps extends TestRunner implements En {

    public FavoritesSteps() {

        When("we add a {string} to our Favorites", (String tile) -> {
            platform.iShouldBeOnPage("ExplorePage");
            platform.getPage(ExplorePage.class).addTileToFavorites(tile);
        });
        Then("the Favorites icon of the {string} should change status", (String tile) -> {

        });
        When("we add a {string} to our Favorites from profile page", (String tile) -> {
            platform.iShouldBeOnPage("ProfilePage");
            platform.getPage(ProfilePage.class).addTrekToOurFavorites(tile);
        });
        When("^we remove a \"([^\"]*)\" from our Favorites$", (String tile) -> {
            platform.getPage(FavoritesPage.class).removeTileFromFavorites(tile);
        });
        Then("^this \"([^\"]*)\" should be in my list of Favorites$", (String tile) -> {
            platform.iShouldBeOnPage("FavoritesPage");
            platform.getPage(FavoritesPage.class).verifyIfTileDisplayedInFavorites(tile);
        });
        Then("^this \"([^\"]*)\" should not be in my list of Favorites$", (String tile) -> {
            platform.getPage(FavoritesPage.class).verifyIfTileNotDisplayedInFavorites(tile);
        });

    }
}