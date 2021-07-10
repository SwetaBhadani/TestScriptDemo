package pages;

import baseUtil.BaseUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class=\"yith-wcwl-add-button\"]")
    public List<WebElement> allWishlist;

    @FindBy(xpath = "//*[@class=\"site-header container-fluid\"]//*[@class=\"lar la-heart\"]")
    public WebElement wishListIcon;

    public void doWishList(int noOfProductsToWishlist) throws InterruptedException {
        List<WebElement> toBeWishlisted = new ArrayList<>();

        int countClickedWishlist = 0; // 2
        for(int i = 0; i < allWishlist.size() && countClickedWishlist < noOfProductsToWishlist; i++) {
            if(allWishlist.get(i).isDisplayed() == true) {
                countClickedWishlist++;
                toBeWishlisted.add(allWishlist.get(i));

            }
        }

        for(int i = 0; i < toBeWishlisted.size(); i++) {
            toBeWishlisted.get(i).click();
            Thread.sleep(1000); // put sleep between wishlist click
        }
    }

    public void goToWishList() throws InterruptedException {
        wishListIcon.click();
        Thread.sleep(1000);
        BaseUtil.createWishListPage();
    }
}
