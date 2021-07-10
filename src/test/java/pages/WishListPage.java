package pages;

import baseUtil.BaseUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishListPage {

    @FindBy(xpath="//*[@class=\"shop_table cart wishlist_table wishlist_view traditional responsive   \"]//tbody/tr")
    public List<WebElement> wishlistItemList;

    @FindBy(xpath = "//*[@class=\"cart-contents\"]")
    public WebElement cartIcon;

    public WishListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getWishListTableSize() {
        return wishlistItemList.size();
    }

    public void goToCart(){
        cartIcon.click();
        BaseUtil.createCartPage();
    }
}
