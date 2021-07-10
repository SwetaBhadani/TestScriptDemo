package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath="//*[@class=\"shop_table cart wishlist_table wishlist_view traditional responsive   \"]//tbody/tr")
    public List<WebElement> wishlistItemList;



}

