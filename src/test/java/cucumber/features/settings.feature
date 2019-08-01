Feature: Settings
  @Sanity
  @test
  Scenario Outline: Change the units on the settings page
    Given we open the app
    And we click on the profile icon of the navigation bar
    When we click on the Settings icon in the Profile page
    And we pick "<item>" unit
    And the status of Units changes to "<NewUnit>"
    And we click on the return arrow of the settings page
    Then the units in my profile are "<units on profile>"

    Examples:
      |item |NewUnit| units on profile|
      | Mi. |Mi.    | mi              |
      | Km. |Km.    | km              |

  @SmokeTest
  Scenario: Get to the Settings page when registered
    Given we open the app
    And we click on the profile icon of the navigation bar
    When we click on the Settings icon in the Profile page
    Then we should get to "SettingsPage"

  @lang
  Scenario: Change language in Settings
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on Language spinner
    And we select "Français"
    And we click on the return arrow of the settings page
    Then the text in the hours section should be "Heures"

  @cb
  Scenario: Click on the yellow check box in settings
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on the yellow button for Keep screen on while recording
    And we click on the yellow button for Sync only when wifi enabled
    And we click on the yellow button for Show other users on map
    Then the "Keep screen on while recording" button changes color
    Then the "Sync only when wifi enabled" button changes color
    Then the "Show other users on map" button changes color
    And we click on the yellow button for Others can see me on the map
    And we click on the yellow button for Allow users to follow me
    And we click on the yellow button for Allow to take pictures with external camera
    And we click on the yellow button for Enable chat with other users
    And we click on the yellow button for New messages
    Then the "Others can see me on the map" button changes color
    Then the "Allow users to follow me" button changes color
    Then the "Allow to take pictures with external camera" button changes color
    Then the "Enable  chat with other users" button changes color
    Then the "New messages" button changes color

  @Sanity
  Scenario Outline: In the Settings page change who is allowed to see the trek
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    When we click on the yellow arrow of the Trek Privacy
    And we pick "<item>" privacy settings
    Then the status of Trek Privacy changes to "<item2>"
    And the status of Media Privacy changes to "<item3>"

    Examples:
      |  item          |  item2        |  item3         |
      | Everyone       |  Everyone     |  Everyone      |
      | Only followers | Only followers| Only followers |
      | Only me        |  Only me      |   Only me      |

  @Sanity
  Scenario Outline: In the Settings page change the settings media privacy and trek privacy stays the same
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we set media privacy to "Everyone"
    And we set trek privacy to "Everyone"
    When we click on the yellow arrow of the Media Privacy
    And we pick "<item2>" privacy settings
    And the status of Media Privacy changes to "<item2>"
    Then the status of Trek Privacy changes to "<item>"

    Examples:
      |  item2          |  item         |
      | Everyone        |  Everyone     |
      | Only me         |  Everyone     |
      | Only followers  |  Everyone     |


  Scenario: In the Settings setting trek to followers can only lead to changing media to only me
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we set media privacy to "Everyone"
    And we set trek privacy to "Everyone"
    When we click on the yellow arrow of the Media Privacy
    And we set trek privacy to "Only followers"
    Then media privacy can only be changed to "Only Me"


  Scenario: In the Settings setting trek to only me can only lead to changing media to only me
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we set media privacy to "Everyone"
    And we set trek privacy to "Everyone"
    When we click on the yellow arrow of the Media Privacy
    And we set trek privacy to "Only Me"
    Then we are preventing from changing the media privacy

  @about
  Scenario: Click on About
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on the About button
    Then we should get to "AboutPage"

  @logout
  Scenario: Sign out from Settings
    Given we open the app
    And we click on the profile icon of the navigation bar
    And we click on the Settings icon in the Profile page
    And we should get to "SettingsPage"
    When we click on the sign out button
    Then we should get to "LaunchPage"
