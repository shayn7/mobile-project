Feature: Sanity tests

  @sanity
  Scenario: Add trek to Favorites when not registered
    Given we open the app
    And we click on skip button
    When we add a "TREK" to our Favorites
    Then we get the pop up window "Hi, for this feature, please login."

  @sanity
  Scenario: Add site to Favorites when not registered
    Given we open the app
    And we click on skip button
    When we add a "SITE" to our Favorites
    Then we get the pop up window "Hi, for this feature, please login."

  @sanity
  Scenario: Email sign up
    Given we open the app
    And email = "qa2020@wishtrip.com" and password = "123456"
    And confirm password = "123456"
    When we get to the "email" sign in or sign up page
    And we sign up
    Then we should get to "ExplorePage"

  @sanity
  Scenario: Getting to the Favorites Page
    Given we open the app
    And we are on "ExplorePage"
    When we click on the Favorites icon in the navigation bar
    Then we should get to "FavoritesPage"

  @sanity
  Scenario: Add a trek to WishList from Explore page
    Given we open the app
    And we are on "ExplorePage"
    When we add a "TREK" to our Favorites
    Then the Favorites icon of the "TREK" should change status

  @sanity
  Scenario: Add a site to WishList from Explore page
    Given we open the app
    And we are on "ExplorePage"
    When we add a "SITE" to our Favorites
    Then the Favorites icon of the "SITE" should change status

  @sanity
  Scenario: Remove a trek or site from Favorites from Favorites page
    Given we open the app
    And we are on "ExplorePage"
    And we click on the Favorites icon in the navigation bar
    And we are on "FavoritesPage"
    When we remove a "TREK" from our Favorites
    Then this "TREK" should not be in my list of Favorites

  @sanity
  Scenario: Modify Profile UserName
    Given we open the app
    And we click on profile
    When we click on Edit profile
    And we modify "UserName" to include current date and time
    Then "UserName" value is modified with current date and time
    And we should get a "Your profile has been updated" popup

  @sanity
  Scenario: Modify Profile UserTag
    Given we open the app
    And we click on profile
    When we click on Edit profile
    And we modify "UserTag" to include current date and time
    Then "UserTag" value is modified with current date and time
    And we should get a "Your profile has been updated" popup

  @sanity
  Scenario: Save a Trek
    Given we open the app
    And we are on "ExplorePage"
    When we click on record
    And we click on Activate
    And we click on start
    And we click on add media
    And we click on add pic
    And we click allow
    And we click on take a pic
    And we click on the check button
    And we click on stop
    And we click on finish
    And we click on save
    Then we should get to "SavedTrekPage"

  @sanity
  Scenario: Click on About
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on the About button
    Then we should get to "AboutPage"

  @sanity
  Scenario: Sign out from Settings
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on the sign out button
    Then we should get to "LaunchPage"
