Feature: Testing google sign in works

    @googleSignIn
    Scenario: google repeat sign in
        Given we open the app
        And email = "catfun1234@gmail.com" and password = "12345678cf"
        When we get to the "google" sign in or sign up page
        And we sign in to google
        Then we should get to "ExplorePage"
