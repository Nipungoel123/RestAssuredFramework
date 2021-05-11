Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verifying successfully addition of Place using Add Place API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" using "POST" http request
    Then API call is "Success" with status code "200"
    And "status" in response body is "OK"
    And verify place_id created that maps "<name>" using "getPlaceApi"

    Examples:
      |name|language|address|
      | AAhouse   |   English     |  Abil House     |

    @DeletePlace
    Scenario: Verify if delete place API is working
      Given DeletePlace payload
      When user calls "deletePlaceApi" using "POST" http request
      Then API call is "Success" with status code "200"
      And "status" in response body is "OK"



