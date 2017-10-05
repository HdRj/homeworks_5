package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCategoryPage extends Page {
    public AddCategoryPage(WebDriver driver) {
        super(driver);
    }

    private By categoryName=By.id("name_1");
    private By saveNewCategory = By.id("category_form_submit_btn");
    private static String nameOfCategory = "Тестова категорія";

    public void fillCategoryNameInput(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryName));

        driver.findElement(categoryName).sendKeys(nameOfCategory);

    }

    public void clickSaveButton(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions. elementToBeClickable(saveNewCategory));

        driver.findElement(saveNewCategory).click();
    }

    static String getNameOfCategory() {
        return nameOfCategory;
    }
}
