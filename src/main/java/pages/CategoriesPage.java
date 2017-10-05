package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CategoriesPage extends Page {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    private By newCategory = By.id("page-header-desc-category-new_category");
    private By resultInfo=By.xpath("//div[@class='alert alert-success']");
    private By filterCategory=By.name("categoryFilter_name");
    private By tableNameCategory=By.xpath("//td[normalize-space(text())='"+
            AddCategoryPage.getNameOfCategory()+"']");
    //private By tableNameCategory=By.xpath("//td[normalize-space(text())='Тестова категорія']");

    public void clickNewCategory(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions. elementToBeClickable(newCategory));

        driver.findElement(newCategory).click();
    }

    public boolean isAddedCategory(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultInfo));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public void sendCategoryFilter(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterCategory));
        driver.findElement(filterCategory).sendKeys(AddCategoryPage.getNameOfCategory()+ Keys.ENTER);
    }

    public boolean isTableCategory(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterCategory));
        List<WebElement> list=driver.findElements(tableNameCategory);
        return !list.isEmpty();

    }


}
