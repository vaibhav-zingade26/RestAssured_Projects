Feature: Validating GoogleMaps API

  Scenario Outline:Verify the place is being successfully created using AddPostApi
    Given Add place payload "<name>" "<language>" "<address>"
    When user calls "AddPostAPI" with "POST" http request
    Then the API got status code "200"
    And "Status" in response body is "OK"
    And "Scope" in response body is "APP"
    And Verify place_id is created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address        |
      | Vaibhav | English  | shashtri nagar |
      | dada    |Hinglish  |Dhanori         |
