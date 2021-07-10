package baseUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.WishListPage;

import java.util.concurrent.TimeUnit;

public class BaseUtil {

    private static WebDriver driver;
    private static HomePage homePage;
    private static WishListPage wishListPage;
    private static CartPage cartPage;

    public static void initialize() {
        // ../../../../drivers/chromedriver
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver=new ChromeDriver();
        driver.get("https://testscriptdemo.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static HomePage getHomePage() {
        return homePage;
    }

    public static WishListPage getWishListPage() {
        return wishListPage;
    }

    public static void createWishListPage() {
        wishListPage = new WishListPage(driver);
    }

    public static CartPage getCartPage() {
        return cartPage;
    }

    public static void createCartPage() {
        cartPage = new CartPage(driver);
    }

}
