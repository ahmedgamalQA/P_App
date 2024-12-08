package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.AddRecordPage;
import org.testng.asserts.SoftAssert;
import java.util.List;

import static StepDefinitions.Hooks.driver;
public class Web_Tests {

    String ActualPageTitle;
    String ActualFormHeaderName;
    AddRecordPage addRecordPage = new AddRecordPage(driver);
    SoftAssert soft = new SoftAssert();

    @Given("User Navigate to URL")
    public void userNavigateToURL() {
    }

    @When("check the value of page title and header of form")
    public void checkTheValueOfPageTitleAndHeaderOfForm() {
        ActualPageTitle = addRecordPage.GetTextOfPageTitle();
        ActualFormHeaderName = addRecordPage.GetTextOfFormHeader();
    }

    @Then("Verify the page title is {string}")
    public void verifyThePageTitleIs(String ExpectedPageTitle) {
        soft.assertTrue(ActualPageTitle.equals(ExpectedPageTitle));
        System.out.println("The Actual Result of PageTitle is: " + ActualPageTitle);
        soft.assertAll();
    }

    @And("Verify the header of form is {string}")
    public void verifyTheHeaderOfFormIs(String ExpectedFormHeaderName) {
        soft.assertTrue(ActualFormHeaderName.equals(ExpectedFormHeaderName));
        System.out.println("The Actual Result of FormHeaderName is: " + ActualFormHeaderName);
        soft.assertAll();
    }

    @When("User input all Required fields: {string}, {string}, {string}, {string}, {string}")
    public void userInputAllRequiredFields(String CarNumber, String FuelLiters, String FuelCost, String FuelType, String CustomerCompanyID) {
        addRecordPage.InsertAllInputs(CarNumber, FuelLiters, FuelCost, FuelType, CustomerCompanyID);
    }

    @And("Click on Add button")
    public void clickOnAddButton() {
        addRecordPage.ClickOnSubmitButton();
    }

    @Then("Assert that {string} is correct and display in the table")
    public void assertThatIsCorrectAndDisplayInTheTable(String CustomerCompanyID) {

        List<String> BodyTableValues = addRecordPage.GetAllValuesOfBodyTable();
        soft.assertTrue(BodyTableValues.contains(CustomerCompanyID));
        soft.assertAll();

        System.out.println("BodyTableValues: " + BodyTableValues);
    }



    @When("User inputs all required fields: {string}, {string}, {string}, {string}, {string}")
    public void userInputsAllRequiredFields(String CarNumber, String FuelLiters, String FuelCost, String FuelType, String CustomerCompanyID) {
        addRecordPage.InsertAllInputs(CarNumber, FuelLiters, FuelCost, FuelType, CustomerCompanyID);
    }

    @And("User clicks on the Delete button of the first row to remove the recently added record")
    public void userClicksOnTheDeleteButtonOfTheFirstRowToRemoveTheRecentlyAddedRecord() {
        addRecordPage.ClickOnDeleteButton();    }

    @Then("Validate that {string} is not displayed in the table")
    public void validateThatIsNotDisplayedInTheTable(String CustomerCompanyID) {
        List<String> BodyTableValues = addRecordPage.GetAllValuesOfBodyTable();
        soft.assertFalse(BodyTableValues.contains(CustomerCompanyID),
                "The CustomerCompanyID " + CustomerCompanyID + " should not be displayed in the table");
        soft.assertAll();
        System.out.println("BodyTableValues: " + BodyTableValues);
    }
}
