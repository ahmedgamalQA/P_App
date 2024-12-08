package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Wait<WebDriver> fluentWait;
    protected final JavascriptExecutor js;
    protected final Actions actions;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(15))
                .ignoring(NoSuchElementException.class) // Ignore NoSuchElementException
                .ignoring(StaleElementReferenceException.class) // Ignore StaleElementReferenceException
                .ignoring(TimeoutException.class);
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    protected BasePage ScrollToElement(By by) {
        WebElement element = driver.findElement(by);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
        return this;
    }

    protected BasePage waitForElementToDisappear(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        return this;
    }

    protected BasePage WaitForElement(By by) {
        ScrollToElement(by);
        fluentWait.until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(by),
                ExpectedConditions.presenceOfElementLocated(by),
                ExpectedConditions.visibilityOfElementLocated(by)
        ));
        return this;
    }

    protected WebElement findElement(By by) {
        int attempts = 0;
        WebElement element = null;

        while (attempts < 3) {
            try {
                element = fluentWait.until(driver -> {
                    WebElement foundElement = driver.findElement(by);
                    return foundElement.isDisplayed() ? foundElement : null;
                });

                if (element != null) {
                    return element;
                }
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale element reference. Retrying...");
            } catch (TimeoutException e) {
                System.err.println("Timeout waiting for element with locator: " + by.toString());
                e.printStackTrace();  // Print the stack trace for debugging
                throw e; // Re-throw the exception if needed
            }
            attempts++;
        }

        // Handle the case where the element couldn't be found within the specified attempts
        System.err.println("Element not found within the specified attempts for locator: " + by.toString());
        return null; // or throw an exception, return a default value, or handle it according to your needs
    }

    protected BasePage Click(By by) {
        WaitForElement(by);
        WebElement element = findElement(by);
        element.click();
        return this;
    }

    protected BasePage sendKeys(String string, By by) {
        WaitForElement(by);
        WebElement element = findElement(by);
        element.sendKeys(string);
        return this;
    }

    protected String getText(By by) {
        WaitForElement(by);
        return driver.findElement(by).getText();
    }
}
