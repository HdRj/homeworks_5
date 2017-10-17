package tests;

import pages.AddCategoryPage;
import pages.CategoriesPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Browsers;

public class HomeWork3 extends BaseTest {

    public HomeWork3(Browsers browser) {
        super(browser);
    }

    @Override
    public void run() {
        String nameOfCategory="Тестова категорія";
        //1
        LoginPage loginPage=new LoginPage(driver);

        loginPage.open();
        loginPage.maximize();
        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickLoginButton();
        //2
        DashboardPage dashboardPage =new DashboardPage(driver);
        dashboardPage.clickCategories();
        //3
        CategoriesPage categoriesPage=new CategoriesPage(driver);
        categoriesPage.clickNewCategory();
        //4
        AddCategoryPage addCategoryPage=new AddCategoryPage(driver);
        addCategoryPage.fillCategoryNameInput(nameOfCategory);
        addCategoryPage.clickSaveButton();

        categoriesPage=new CategoriesPage(driver, nameOfCategory);
        if(categoriesPage.isAddedCategory()){
            System.out.println("New category created successfully!");
        } else {
            System.out.println("Category was not created:(");
        }
        //5
        categoriesPage.sendCategoryFilter();

        categoriesPage=new CategoriesPage(driver, nameOfCategory);
        if(categoriesPage.isTableCategory()){
            System.out.println("New category is in the table!");
        } else{
            System.out.println("New category do not in the table:(");
        }



    }
}
