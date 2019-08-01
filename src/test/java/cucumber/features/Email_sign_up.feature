Feature: Testing that email sign up works

  @EmailSignUp @EmailSignUpSuccess
  Scenario: email sign up
    Given we open the app
    And email = "qa2020@wishtrip.com" and password = "123456"
    And confirm password = "123456"
    When we get to the "email" sign in or sign up page
    And we sign up
    Then we should get to "ExplorePage"

  @EmailSignUp @EmailSignUpPWNotIdentical
  Scenario: email sign up with wrong password in the confirm password line
    Given we open the app
    And email = "song25@hotmail.com" and password = "123456"
    And confirm password = "654321"
    When we get to the "email" sign in or sign up page
    And we sign up
    Then we should get a "Passwords not identical" message

  @EmailSignUp @EmailSignUpWrongEmailFormat
  Scenario: email sign up with wrong email format
    Given we open the app
    And email = "song25" and password = "123456"
    And confirm password = "123456"
    When we get to the "email" sign in or sign up page
    And we sign up
    Then we should get a "This isn't an email" message

  @EmailSignUp @EmailSignUpWrongPWFormat
  Scenario: email sign up with wrong password
    Given we open the app
    And email = "song25@hotmail.com" and password = "654"
    And confirm password = "654"
    When we get to the "email" sign in or sign up page
    And we sign up
    Then we should get a "Yo, this ain't strong enough" message