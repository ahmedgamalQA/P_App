@SmokeTests @WebTests
Feature: Test Scenarios for PetroApp website

  Background:
    Given User Navigate to URL

  @TC1
  Scenario: TC1 -- Verify page title and form header is correct
    When check the value of page title and header of form
    Then Verify the page title is "Car Fuel Consumption Tracker"
    And Verify the header of form is "Add Record"


  @TC2
  Scenario Outline: TC2 -- check Add Functionality for Car Fuel Information
    When User input all Required fields: "<CarNumber>", "<FuelLiters>", "<FuelCost>", "<Fuel Type>", "<CustomerCompanyID>"
    And Click on Add button
    Then Assert that "<CustomerCompanyID>" is correct and display in the table
    Examples:
      | CarNumber |  FuelLiters      |  FuelCost  |  Fuel Type  |  CustomerCompanyID  |
      |  5678XYZ  |  30.5 liters     | 75.25 USD  |  Hybrid     |  C98765ABC          |




  @TC3
  Scenario Outline: TC3 -- Check the delete functionality for recently added data
    When User input all Required fields: "<CarNumber>", "<FuelLiters>", "<FuelCost>", "<FuelType>", "<CustomerCompanyID>"
    And Click on Add button
    And User clicks on the Delete button of the first row to remove the recently added record
    Then Validate that "<CustomerCompanyID>" is not displayed in the table

    Examples:
      | CarNumber | FuelLiters      | FuelCost  | FuelType | CustomerCompanyID |
      | 5678XYZ   | 30.5 liters     | 75.25 USD | Hybrid   | C98765ABC         |
