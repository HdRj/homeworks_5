package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class FinalOrderPage extends Page {
    public FinalOrderPage(WebDriver driver) {
        super(driver);
    }

    public By message=By.xpath("//h3[@class='h1 card-title']");
    public By name=By.xpath("//div[@class='col-sm-4 col-xs-9 details']/span");
    public By price=By.xpath("//div[@class='row']/div[@class='col-xs-5 text-sm-right text-xs-left']");
    public By count=By.xpath("//div[@class='row']/div[@class='col-xs-2']");

    private String nameOrder;
    private double priceOrder;
    private int countOrder;
    private String messageOrder;

    public void info(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(name));
        }catch (Exception e){}

        nameOrder=driver.findElement(name).getText().toLowerCase();
        int index=nameOrder.indexOf(" - size");
        if (index != -1) {
            nameOrder=nameOrder.substring(0,index);
        }

        messageOrder=driver.findElement(message).getText().toLowerCase();
        messageOrder=messageOrder.substring(1);

        String temp=driver.findElement(price).getText();
        index=temp.indexOf(' ');
        temp=temp.substring(0,index);
        temp=temp.replace(',','.');
        priceOrder=Double.parseDouble(temp);

        temp=driver.findElement(count).getText();
        //index=temp.indexOf(' ');
        //temp=temp.substring(0,index);
        countOrder=Integer.parseInt(temp);
    }

    public boolean checkMessage(){
        if(messageOrder.contentEquals("ваше замовлення підтверджено") ||
                messageOrder.contentEquals("ваш заказ подтверждён")  ){
            Reporter.log("The purchase notice is correct");
            return true;
        } else{
            Reporter.log("The purchase notice is incorrect");
            Reporter.log("Should be: ваше замовлення підтверджено");
            Reporter.log("OR");
            Reporter.log("Should be: ваш заказ подтвержден");
            Reporter.log("Real: "+messageOrder);
            System.out.println(messageOrder);
            return false;
        }

    }

    public boolean checkName(String name){
        if(this.nameOrder.equals(name.toLowerCase())){
            Reporter.log("Name of product is correct");
            return true;
        } else {
            Reporter.log("Name of product is incorrect");
            Reporter.log("Should be: "+name.toLowerCase());
            Reporter.log("Real: "+nameOrder);
            System.out.println(nameOrder);
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


}
