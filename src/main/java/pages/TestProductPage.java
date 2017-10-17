package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestProductPage extends Page {
    public TestProductPage(WebDriver driver) {
        super(driver);
    }

    public TestProductPage(WebDriver driver, String nameProduct, int countProduct, double priceProduct){
        super(driver);
        this.nameProduct=nameProduct.toUpperCase();
        Integer cP=countProduct;
        this.countProductRU=cP.toString()+" Товары";
        this.countProductUA= cP.toString()+" од.";
        Double pP=priceProduct;
        this.priceProduct=pP.toString().replace('.',',')+" ₴";
    }

    private String nameProduct;
    private String countProductRU;
    private String countProductUA;
    private String priceProduct;

    private By nameTest=By.xpath("//h1[@class='h1']");
    private By countTest=By.xpath("//div[@class='product-quantities']/span");
    private By priceTest=By.xpath("//div[@class='current-price']/span");
    private By basketButton=By.xpath("//button[@class='btn btn-primary add-to-cart']");
    private By toOrder=By.xpath("//a[@class='btn btn-primary']");
    private By detail=By.xpath("//li[@class='nav-item']/a[@class='nav-link']");

    public boolean checkName() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(nameTest));

        if (!nameProduct.equals(driver.findElement(nameTest).getText())) {

            return false;
        }
        return true;
    }

    public boolean checkCount() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(countTest));
        if (countProductUA.equals(driver.findElement(countTest).getText())) {
            return true;
        }
        if (countProductRU.equals(driver.findElement(countTest).getText())) {
            return true;
        }
        return false;
    }

    public boolean checkPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(priceTest));
        if(!priceProduct.equals(driver.findElement(priceTest).getText())){
            return false;
        }
        return true;
    }

    public void clickBasket(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(basketButton));
        WebElement element=driver.findElement(basketButton);
        try{
            element.click();
        }
        catch (Exception e){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
            element.click();
        }

    }

    public void clickToOrder(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(toOrder));
        driver.findElement(toOrder).click();

    }

    public int getCount(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(detail));

            element=driver.findElement(detail);

            try {
                element.click();

            }catch (Exception e){
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("arguments[0].scrollIntoView()", element);
                element.click();
            }
        }catch (Exception e){}
        wait.until(ExpectedConditions.elementToBeClickable(countTest));
        element=driver.findElement(countTest);
        String temp=element.getText();
        String[] brokenSentence = temp.split(" ");

        int count=Integer.parseInt(brokenSentence[0]);
        return count;
    }

    public double getPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(priceTest));
        WebElement element=driver.findElement(priceTest);

        String temp=element.getText();
        int index=temp.indexOf(' ');
        if(index==-1) {
            index=temp.indexOf('\u0020');
        }
        temp = temp.substring(0, index);
        temp=temp.replace(',','.');
        Double price = Double.parseDouble(temp);
        return price;
    }

}
