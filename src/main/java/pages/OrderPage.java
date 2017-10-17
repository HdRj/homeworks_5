package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class OrderPage extends Page {
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameConsumer= By.xpath("//input[@name='firstname']");
    private By lastNameConsumer=By.xpath("//input[@name='lastname']");
    private By emailConsumer=By.xpath("//input[@name='email']");
    private By buttonContinue=By.xpath("//button[@name='continue']");
    private By buttonContinue2=By.xpath("//button[@name='confirm-addresses']");
    private By addressConsumer=By.xpath("//input[@name='address1']");
    private By postCodeConsumer=By.xpath("//input[@name='postcode']");
    private By cityConsumer=By.xpath("//input[@name='city']");
    private By buttonContinue3=By.xpath("//button[@name='confirmDeliveryOption']");
    private By radioButton1=By.xpath("//input[@id='payment-option-1']");
    private By checkBox=By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']");
    private By button4=By.xpath("//button[@class='btn btn-primary center-block']");

    public void fillFirstName(String firstName){
        driver.findElement(firstNameConsumer).sendKeys(firstName);
        Reporter.log("Client first name is: "+firstName);
    }

    public void  fiilLastName(String lastName){
        driver.findElement(lastNameConsumer).sendKeys(lastName);
        Reporter.log("Client last name is: "+lastName);

    }

    public void fillRandomEmail(){
        String randomEmail="acc"+Math.round(Math.random()*10000)+"@example.com";
        driver.findElement(emailConsumer).sendKeys(randomEmail);
        Reporter.log("Client e-mail: "+randomEmail);

    }

    public void fillAddress(String address){

        driver.findElement(addressConsumer).sendKeys(address);
        Reporter.log("Client address is: "+address);

    }

    public void fillPostCode(String postCode){

        driver.findElement(postCodeConsumer).sendKeys(postCode);
        Reporter.log("Client post code is: "+postCode);

    }

    public void fillCity(String city){

        driver.findElement(cityConsumer).sendKeys(city);
        Reporter.log("City: "+city);

    }


    public void clickButton(){
        WebElement element=driver.findElement(buttonContinue);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }

    public void clickButton2(){
        WebElement element=driver.findElement(buttonContinue2);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }

    public void clickButton3(){
        WebElement element=driver.findElement(buttonContinue3);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }

    public void clickRadio(){
        WebElement element=driver.findElement(radioButton1);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }

    }

    public void clickCheckBox(){
        WebElement element=driver.findElement(checkBox);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }

    public void clickButton4(){

        WebElement element=driver.findElement(button4);
        try {
            element.click();
        } catch(Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }


    }


}
