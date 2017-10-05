package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DashboardPage extends Page{

    private By logoutImage = By.id("employee_infos");
    private By logoutButton = By.id("header_logout");
    private By menu = By.cssSelector("a.title.has_submenu");
    private By menu_stat=By.partialLinkText("Статистика");
    private By categories=By.id("subtab-AdminCategories");
    private By catalog=By.partialLinkText("Каталог");


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

    private void delay(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
