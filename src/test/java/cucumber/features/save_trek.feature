Feature: Saving a trek
  @SaveTrek
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

  @startTrekking
  Scenario: Save a Trek
    Given we open the app
    And we are on "ExplorePage"
    When we click on record
    And we click on Activate
    And we click on start
    Then we should start trekking
















