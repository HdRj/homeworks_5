package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.Random;

public class AddProductPage extends Page {
    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    private By productName=By.id("form_step1_name_1");
    private By productCount=By.id("form_step1_qty_0_shortcut");
    private By productPrice=By.id("form_step1_price_shortcut");
    //private By swich=By.id("form_step1_active");
    private By closeMassege=By.xpath("//div[@class='growl-close']");
    private By buttonSave=By.xpath("//button[@class='btn btn-primary js-btn-save']");
    //private By swich2=By.xpath("//h2[@class='for-switch offline-title']");


    public String fillProdName(){
        String name=randomString();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(productName));
        driver.findElement(productName).sendKeys(name);

        return name;
    }

    public int fillProdCount(){
        Integer count=(int)(Math.random()*99+1);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(productCount));
        WebElement inputCount=driver.findElement(productCount);
        inputCount.clear();
        inputCount.sendKeys(count.toString());

        return count;
    }

    public double fillProgPrice(){
        Double price=Math.rint(Math.random()*(10000-10)+10)/100;
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(productPrice));
        WebElement inputPrice = driver.findElement(productPrice);
        inputPrice.clear();
        inputPrice.sendKeys(price.toString());

        return price;
    }

    public void onSwitch(){
        WebDriverWait wait = new WebDriverWait(driver, 10);


        //System.out.println(driver.findElement(swich).getLocation());
        /*
        WebElement element= driver.findElement(swich2);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].style.display = 'display: inline-block';", element);

        element= driver.findElement(swich);
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        */

        WebElement element=driver.findElement(productName);
        element.sendKeys(Keys.chord(Keys.CONTROL,"o"));


        try {
            //wait.until(ExpectedConditions.elementToBeClickable(closeMassege));
            wait.until(ExpectedConditions.visibilityOfElementLocated(closeMassege));

            driver.findElement(closeMassege).click();
            System.out.println("Настройки изменены");
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void clickSave(){
        driver.findElement(buttonSave).submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(closeMassege));

            driver.findElement(closeMassege).click();
            System.out.println("Настройки изменены");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private String randomString(){
        String symbols = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder randString = new StringBuilder();
        int count = (int)(Math.random()*15+3);
        for(int i=0;i<count;i++) {
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
        }
        return String.valueOf(randString);
    }


}
