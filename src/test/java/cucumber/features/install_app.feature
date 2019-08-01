Feature: Install application
@InstallApp
  Scenario: Install the application
    Given we install the app
    Then we should get to "WelcomePage"