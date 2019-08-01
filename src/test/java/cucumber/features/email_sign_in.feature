Feature: Testing that email sign in works

  @SignIn
  Scenario: email sign in
    Given we open the app
    And email = "song25@hotmail.com" and password = "123456"
    When we get to the "email" sign in or sign up page
    And we sign in
    Then we should get to "ExplorePage"

  @EmailSignIn @EmailSignInWrongPWFormat
  Scenario: email sign in with wrong password
    Given we open the app
    And email = "song25@hotmail.com" and password = "654321"
    When we get to the "email" sign in or sign up page
    And we sign in
    Then we should get a "sign-in failed" popup

  @EmailSignIn @EmailSignInWrongEmailFormat
  Scenario: email sign in with wrong email format
    Given we open the app
    And email = "song25" and password = "123456"
    When we get to the "email" sign in or sign up page
    And we sign in
    Then we should get a "This isn't an email" message


