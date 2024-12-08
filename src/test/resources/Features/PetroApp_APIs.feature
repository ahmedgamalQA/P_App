@ApiTests
Feature: verify the APIs functionality of PetroApp

  Background:
    Given the API is available

  # Scenario for Authentication tests
  Scenario Outline: Authenticate & Fetch cars using session ID
    When I send a POST request to "?endpoint=authenticate" with parameters username is "<Username>" and password is "<Password>"
    Then the response should have a 200 status code
    And Verify that a valid Session Id is returned and user_id is <user_id> and status is "success"

    # Scenario for Fetch cars using session ID tests
    When I send a GET request to "?endpoint=fetch_cars" with header Session-Id
    Then the response should have a 200 status code
    And Verify that a List of Cars are returned and user_id is <user_id> and status is "success"
    And Assert the IDs of cars is "<Cars_IDs>"

    Examples:
      | Username   | Password     | user_id | Cars_IDs  |
      | user1      | password123  |    1    | 101,102   |
      | user2      | password456  |    2    | 201,202   |
      | admin      | adminpass    |    0    | 101,102,201,202 |


  Scenario Outline: check the invalid Scenarios for Authentication tests
    When I send a POST request to "?endpoint=authenticate" with parameters username is "<Username>" and password is "<Password>"
    Then the response should have a 200 status code
    And Verify the response have status is "error" and the expected message is "<message>"

    Examples:
      | Username    | Password     |             message                |
      | invalidUser | invalidPass  |    Invalid username or password    |
      |             | invalidPass  |    Username and password required  |
      | invalidUse  |              |    Username and password required  |


  Scenario Outline: check the invalid Scenarios for Fetch cars using session ID
    When I send a GET request to "?endpoint=fetch_cars" with header "<Session-Id>"
    Then the response should have a 200 status code
    And Verify the response have status is "error" and the expected message is "<message>"

    Examples:
      | Session-Id        |             message                |
      | InvalidSessionID  |    Invalid session ID              |
      |                   |    Session-ID header required      |