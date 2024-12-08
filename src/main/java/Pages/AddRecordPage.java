package Pages;

import org.openqa.selenium.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddRecordPage extends BasePage {
    public AddRecordPage(WebDriver driver) {
        super(driver);
    }

    private final By PageTitleLocator = By.tagName("h1");
    private final By FormHeaderLocator = By.xpath("//h3[1]");
    private final By carNumberLocator = By.cssSelector("input[name='carNumber']");
    private final By fuelInLitersLocator = By.cssSelector("input[name='fuelInLiters']");
    private final By fuelCostLocator = By.cssSelector("input[name='fuelCost']");
    private final By fuelTypeLocator = By.cssSelector("input[name='fuelType']");
    private final By companyIdLocator = By.cssSelector("input[name='companyId']");
    private final By dateAndTimeLocator = By.cssSelector("input[name='dateAndTime']");
    private final By SubmitLocator = By.cssSelector("button[type='submit']");
    private final By firstRowLocator = By.xpath("//tbody/tr[1]");
    private final By BodyTable =By.xpath("//table[@class='table table-striped']/tbody");
    private final By DeleteButtonLocator = By.cssSelector("button[class='btn btn-danger']");


    public String GetTextOfPageTitle() {
        return getText(PageTitleLocator);
    }

    public String GetTextOfFormHeader() {
        return getText(FormHeaderLocator);
    }

    public void InsertAllInputs(String CarNumber, String FuelLiters, String FuelCost, String FuelType, String CustomerCompanyID) {
        sendKeys(CarNumber, carNumberLocator);
        sendKeys(FuelLiters, fuelInLitersLocator);
        sendKeys(FuelCost, fuelCostLocator);
        sendKeys(FuelType, fuelTypeLocator);
        sendKeys(CustomerCompanyID, companyIdLocator);


        WebElement dateTimeInput = driver.findElement(dateAndTimeLocator);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String currentDateTime = sdf.format(new Date());

        js.executeScript("arguments[0].setAttribute('value', '" + currentDateTime + "');", dateTimeInput);
    }

    public void ClickOnSubmitButton() {
        Click(SubmitLocator);
    }

    public List<String> GetAllValuesOfBodyTable() {

        WebElement firstRow = driver.findElement(BodyTable);
        List<WebElement> cells = firstRow.findElements(By.tagName("td"));
        List<String> CellValues = new ArrayList<>();
        for (WebElement cell : cells) {
            CellValues.add(cell.getText());  // Add the text of each cell to the list
        }
        return CellValues;
    }

    public void ClickOnDeleteButton(){
        Click(DeleteButtonLocator);
    }

}
