package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ProductsPage extends Page {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private By newProducts=By.id("page-header-desc-configuration-add");


    public void clickNewProduct(){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(newProducts));
            driver.findElement(newProducts).click();
        } catch(Exception e){
            Reporter.log(e.toString());
            driver.findElement(newProducts).sendKeys(Keys.chord(Keys.CONTROL,"p"));
        }

    }
}
