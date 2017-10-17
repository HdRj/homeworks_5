package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    private By toOrder=By.xpath("//a[@class='btn btn-primary']");
    private By name=By.xpath("//div[@class='product-line-info']/a[@class='label']");
    private By price=By.xpath("//div[@class='product-line-info']/span[@class='value']");
    private By count=By.xpath("//div[@id='cart-subtotal-products']/span[@class='label js-subtotal']");


    private String nameOrder;
    private double priceOrder;
    private int countOrder;

    public void info(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(name));
        }catch (Exception e){}

        nameOrder=driver.findElement(name).getText().toLowerCase();

        String temp=driver.findElement(price).getText();
        int index=temp.indexOf(' ');
        temp=temp.substring(0,index);
        temp=temp.replace(',','.');
        priceOrder=Double.parseDouble(temp);

        temp=driver.findElement(count).getText();
        index=temp.indexOf(' ');
        temp=temp.substring(0,index);
        countOrder=Integer.parseInt(temp);
    }

    public boolean checkName(String name){
        if(this.nameOrder.equals(name.toLowerCase())){
            Reporter.log("Name of product is correct");
            return true;
        } else {
            Reporter.log("Name of product is incorrect");
            Reporter.log("Should be: "+name);
            Reporter.log("Real: "+nameOrder);
            return false;
        }
    }

    public boolean checkCount(){
        if(this.countOrder==1){
            Reporter.log("Count of product is correct");
            return true;
        } else {
            Reporter.log("Count of product is incorrect");
            Reporter.log("Should be: 1");
            Reporter.log("Real: "+countOrder);
            return false;
        }
    }

    public boolean checkPrice(double price){
        if(Math.abs(this.priceOrder-price)<0.001){
            Reporter.log("Price of product is correct");
            return true;
        } else {
            Reporter.log("Price of product is incorrect");
            Reporter.log("Should be: "+price);
            Reporter.log("Real: "+priceOrder);
            return false;
        }
    }

    public void clickButtonOrder(){

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(toOrder));
        WebElement element=driver.findElement(toOrder);
        try {
            element.click();
        }catch (Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
    }
}
