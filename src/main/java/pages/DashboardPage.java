package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

public class DashboardPage extends Page{

    private By logoutImage = By.id("employee_infos");
    private By logoutButton = By.id("header_logout");
    private By menu = By.cssSelector("a.title.has_submenu");
    private By menu_stat=By.partialLinkText("Статистика");
    private By categories=By.id("subtab-AdminCategories");
    private By products=By.id("subtab-AdminProducts");
    //private By catalog=By.partialLinkText("Каталог");
    private By catalog=By.xpath("//a/span[contains(text(),'Каталог')]");
    private By ff=By.xpath("//a[@class='btn btn-danger-outline fake-button col-md-4']");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public void clickLogoutImage(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions. elementToBeClickable(logoutImage));

        driver.findElement(logoutImage).click();
    }

    public void clickLogoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions. elementToBeClickable(logoutButton));

        driver.findElement(logoutButton).click();
    }

    public void clickMenus(){

        WebDriverWait wait = new WebDriverWait(driver, 5);



        wait.until(ExpectedConditions.elementToBeClickable(menu));
        delay();

        List <WebElement> menus = driver.findElements(menu);

        //System.out.println("Total: "+menus.size());

        String oldTitle;
        String newTitle;

        for (int i=0;i<menus.size();i++){
            menus.get(i).click();

            delay();
            oldTitle=driver.getTitle();
            System.out.println(oldTitle);

            this.refresh();

            delay();
            newTitle=driver.getTitle();
            System.out.println(oldTitle.equals(newTitle));

            driver.navigate().back();

            delay();
            menus=driver.findElements(menu);

        }

        wait.until(ExpectedConditions.elementToBeClickable(menu_stat));
        delay();
        driver.findElement(menu_stat).click();
        delay();
        oldTitle=driver.getTitle();
        System.out.println(oldTitle);
        this.refresh();
        delay();
        newTitle=driver.getTitle();
        System.out.println(oldTitle.equals(newTitle));
    }

    public void  clickCategories(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(catalog));

        WebElement webElement=driver.findElement(catalog);

        Actions action = new Actions(driver);
        action.moveToElement(webElement);
        action.perform();

        wait.until(ExpectedConditions.elementToBeClickable(categories));
        driver.findElement(categories).click();
    }

    public void clickProducts(){
        /*
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(catalog));

        WebElement element= driver.findElement(products);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        */


        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(catalog));

        WebElement webElement=driver.findElement(catalog);

        /*Actions action = new Actions(driver);
        action.moveToElement(webElement).build().perform();
        action.perform();*/
        try {
            mouseOver(webElement);
        } catch (Exception e){
            Reporter.log(e.toString());
        }


        try {
            wait.until(ExpectedConditions.elementToBeClickable(products));

            webElement = driver.findElement(products);
            webElement.click();
        } catch(Exception e){
            Reporter.log("Не вдалося клікнути на Товари"+e.toString());
            //webElement = driver.findElement(products);
            //String href=webElement.getAttribute("href");
            //String href="/admin147ajyvk0/index.php/product/catalog?_token=h_HWCnMNwbiM1_gjvjQedP7a0Y3NXucMosF3vFCGLVA";
            String href="/admin147ajyvk0/index.php/product/catalog";
            driver.get("http://prestashop-automation.qatestlab.com.ua"+href);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(ff));
                driver.findElement(ff).click();
            } catch (Exception e2){
                Reporter.log(e2.toString());
            }

        }

    }

    private void delay(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void mouseOver(WebElement element) {
        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );"
                + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor)driver).executeScript(code, element);
    }
}
