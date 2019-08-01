Feature: Testing facebook sign in works
  @SmokeTest @facebook
  Scenario: facebook sign in
    Given we open the app
    And email = "0587212672" and password = "Wishtest33"
    When we get to the "facebook" sign in or sign up page
    And we sign in to facebook
    Then we should get to "ExplorePage"
