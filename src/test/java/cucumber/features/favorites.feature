Feature: Testing favorites options

  @FavoritesIconChangeStatusTrek
  Scenario: Add a trek to WishList from Explore page
    Given we open the app
    And we are on "ExplorePage"
    When we add a "TREK" to our Favorites
    Then the Favorites icon of the "TREK" should change status

  @FavoritesIconChangeStatusSite
  Scenario: Add a site to WishList from Explore page
    Given we open the app
    And we are on "ExplorePage"
    When we add a "SITE" to our Favorites
    Then the Favorites icon of the "SITE" should change status

  @FavoritesProfilePage
  Scenario: Add a trek to WishList from Profile page
    Given we open the app
    And we are on "ExplorePage"
    And we click on profile icon in the navigation bar
    When we add a "TREK" to our Favorites from profile page
    Then the Favorites icon of the "TREK" should change status

  @FavoritesTrekWhenNotRegistered
  Scenario: Add trek to Favorites when not registered
    Given we open the app
    And we click on skip button
    When we add a "TREK" to our Favorites
    Then we get the pop up window "Hi, for this feature, please login."

  @FavoritesSiteWhenNotRegistered
  Scenario: Add site to Favorites when not registered
    Given we open the app
    And we click on skip button
    When we add a "SITE" to our Favorites
    Then we get the pop up window "Hi, for this feature, please login."

  @FavoritesRemoveTrek
  Scenario: Remove a trek or site from Favorites from Favorites page
    Given we open the app
    And we are on "ExplorePage"
    And we click on the Favorites icon in the navigation bar
    And we are on "FavoritesPage"
    When we remove a "TREK" from our Favorites
    Then this "TREK" should not be in my list of Favorites

  @FavoritesGetToFavoritesPage
  Scenario: Getting to the Favorites Page
    Given we open the app
    And we are on "ExplorePage"
    When we click on the Favorites icon in the navigation bar
    Then we should get to "FavoritesPage"

  @FavoritesTrekDisplayedFromExplore
  Scenario: Does a specific Favorited item from Explorer appear in my WishList?
    Given we open the app
    And we are on "ExplorePage"
    And we add a "TREK" to our Favorites
    And we click on the Favorites icon in the navigation bar
    Then this "TREK" should be in my list of Favorites

  @FavoritesTrekDisplayedFromProfile
  Scenario: Does a specific Favorited item from Profile appear in my WishList?
    Given we open the app
    And we are on "ExplorePage"
    And we click on profile icon in the navigation bar
    When we add a "TREK" to our Favorites from profile page
    And we click on the Favorites icon in the navigation bar
    Then this "TREK" should be in my list of Favorites