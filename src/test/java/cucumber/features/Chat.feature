Feature: Testing Chat options

  @OpenChat
  Scenario: Open chat
    Given we open the app
    When we click on the chat icon
    And we click on the chat tab
    Then we should get to "ChatPage"

  @SendChat
  Scenario: Send chat
    Given we open the app
    And we click on the chat icon
    And we click on the chat tab
    When we click on the first chat box
    And we type "Testing"
    And we click send
    Then we see that the message was sent

  @SendChatFromSearch
  Scenario: Send chat from the search engine
    Given we open the app
    And we search for "song52"
    And we click search
    And we click on "song52"
    And we click on start chat
    And we type "Hello"
    And we click send
    Then we see that the message was sent

  @IntoAChatAndOut
  Scenario: Opening the chat conversation and going back out to the ChatsPage
    Given we open the app
    And we click on the chat icon
    And we click on the chat tab
    When we click on the first chat box
    And we click back
    Then we should get to "ChatPage"

  @AddAttachment
  Scenario: Add an attachment to a conversation
    Given we open the app
    And we click on the chat icon
    And we click on the chat tab
    And we click on the first chat box
    And we click on conversation menu
    And we delete the conversation
    When we click on add attachment
    And we click on the camera icon
    And we take a picture
    And we confirm the picture
    And we send the attachment
    Then we see that the image was sent

  @AddAudio
  Scenario: Add audio to a conversation
    Given we open the app
    And we click on the chat icon
    And we click on the chat tab
    And we click on the first chat box
    When we record audio message
    And we click send
    Then we see that the audio was sent

  @ScrollInChatsPage
  Scenario: Scrolling down in ChatsPage
    Given we open the app
    When we click on the chat icon
    And we click on the chat tab
    And we get the user name of the first chat
    And we scroll down
    Then we should see a different user in the first chat box




