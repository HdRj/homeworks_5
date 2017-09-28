package tests;

import pages.DashboardPage;
import pages.LoginPage;
import utils.Browsers;

public class HomeWork2Task1 extends BaseTest {

    public HomeWork2Task1(Browsers browser){
        super(browser);
    }

    @Override
    public void run() {
        LoginPage loginPage=new LoginPage(driver);

        loginPage.open();
        loginPage.maximize();
        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickLoginButton();

        DashboardPage dashboardPage =new DashboardPage(driver);

        dashboardPage.clickLogoutImage();
        dashboardPage.clicklogoutButton();

    }

}
