Feature: Open application
  @SmokeTest @OpenApp
  Scenario: open the application
    Given we open the app
    Then we should get to "ExplorePage"