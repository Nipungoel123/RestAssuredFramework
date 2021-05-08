Feature : Validating Place API's

  Scenario: Verifying successfully addition of Place using Add Place API
    Given Add Place Payload
    When user calls "AddPlace" using post http request
    Then API call is "Success" with status code "200"
    And "Status" in response body is "Ok"

