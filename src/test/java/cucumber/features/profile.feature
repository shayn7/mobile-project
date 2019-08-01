Feature: Testing Profile page options

  @Profile1
  Scenario: Modify Profile UserName
    Given we open the app
    And we click on profile
    When we click on Edit profile
    #Then the UserName is modified to the modified name at runtime
    And we modify "UserName" to include current date and time
    Then "UserName" value is modified with current date and time
    And we should get a "Your profile has been updated" popup

  @Profile
  Scenario: Modify Profile UserTag
    Given we open the app
    And we click on profile
    When we click on Edit profile
  #Then the UserTag is modified to the modified tag at runtime
    And we modify "UserTag" to include current date and time
    Then "UserTag" value is modified with current date and time
    And we should get a "Your profile has been updated" popup

#  @Sanity @Profile
#  Scenario: Modify Profile UserName and UserTag
 #   Given we open the app
  #  And email = "song25@hotmail.com" and password = "123456"
  #  And we are on the Profile Page
  #  When we click on Edit profile
 #   And we click on Profile UserName
 #   And we modify UserName to "generate_at_runtime"
 #   Then the UserName is modified to the modified name
 #   When we click on Profile UserTag
 #   And we modify UserTag to "generate_at_runtime"
 #   Then the UserTag is modified to the modified name

 # @Profile
 # Scenario: Share Profile with WhatsApp
  #  Given we open the app
 #   And we click on profile
 #   When we click on Share Profile
 #  And we Choose method of Sharing "WhatsApp"
#    And we Choose Persistence of Sharing "Always"
 #  Then we Share the User Profile link

#  @Profile
 # Scenario Outline: Modify Profile picture
#    Given we open the app
 #   And email = "song25@hotmail.com" and password = "123456"
 #   And we are on the Profile Page
 #   And we click on Edit profile
 #   And we click on Profile picture
  # Then we can modify Profile picture with <Method>

  #Example:
#      |Method |
#      |take_photo |
#      |choose_photo_from_gallery |