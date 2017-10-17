package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

public class AllProductPage extends Page {

    public AllProductPage(WebDriver driver) {

        super(driver);

    }

    public AllProductPage(WebDriver driver, String nameProduct){
        super(driver);
        testProduct=By.linkText(firstUpperCase(nameProduct));
        testProductFF=By.linkText(nameProduct);
    }

    private By testProduct;
    private By testProductFF;
    private By nextPage=By.xpath("//a[@class='next js-search-link']");
    private By product=By.xpath("//h1[@class='h3 product-title']/a");


    public boolean clickTestProduct (){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(testProduct));
            element=driver.findElement(testProduct);
            try {
                element.click();
            } catch(Exception e){
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("arguments[0].scrollIntoView()", element);
                element.click();
            }

        } catch (Exception ee){
            try {
                wait.until(ExpectedConditions.elementToBeClickable(testProductFF));
                driver.findElement(testProductFF).click();
            } catch (Exception e) {
                try {
                    element=driver.findElement(nextPage);
                    try {
                        element.click();
                    } catch(Exception eee){
                        JavascriptExecutor jse = (JavascriptExecutor)driver;
                        jse.executeScript("arguments[0].scrollIntoView()", element);
                        element.click();
                    }
                    clickTestProduct();
                } catch (Exception e1) {
                    System.out.println(e + " " + e1);
                    Reporter.log("Доданий продукт не вдалося знайти:(");
                    return false;

                }
            }
        }
        return true;
    }

    public String randomProd(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(product));
        List <WebElement> list= driver.findElements(product);
        Reporter.log("Total products on the page: "+list.size());
        int numberProd=(int) Math.round((Math.random()*(list.size()-1)));
        Reporter.log("Selected product number: "+numberProd);
        WebElement element=list.get(numberProd);
        String name=element.getText();
        try {
            element.click();
        }catch (Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }
        return name;
    }



    private String firstUpperCase(String word){
        if(word == null || word.isEmpty())
            return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }





}
