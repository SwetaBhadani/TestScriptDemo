package stepDefinition;

import baseUtil.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StepDefinition extends BaseUtil{

    WebElement lowestPriceAddToCartWebElement;

    String lowestPriceProductName;
    double lowestPrice;

    @Given("I open the website")
    public void i_open_the_website() {
        BaseUtil.initialize();
    }

    @Given("I add {int} different products to my wishlist")
    public void i_add_four_different_products_to_my_wishlist(Integer noOfProductsToWishlist) throws InterruptedException {
        BaseUtil.getHomePage().doWishList(noOfProductsToWishlist);
    }

    @When("I view my wishlist table")
    public void i_view_my_wishlist_table() throws InterruptedException {

        BaseUtil.getHomePage().goToWishList();
    }

    @Then("I find total four selected items in my wishlist")
    public void i_find_total_four_selected_items_in_my_wishlist() {

        int actualNoOfItemsInWishList = BaseUtil.getWishListPage().getWishListTableSize();
        Assert.assertEquals(actualNoOfItemsInWishList, 4);
    }

    @When("I search for lowest price product")
    public void i_search_for_lowest_price_product() {

        double minimumPrice = Double.MAX_VALUE;

        for(int i=1;i<=BaseUtil.getWishListPage().getWishListTableSize();i++){
            String priceXPath = "//*[@class=\"shop_table cart wishlist_table wishlist_view traditional responsive   \"]//tbody/tr["+i+"]/td[4]";
            WebElement priceElement=BaseUtil.getDriver().findElement(By.xpath(priceXPath));

            String addToCartXPath = "//*[@class=\"shop_table cart wishlist_table wishlist_view traditional responsive   \"]//tbody/tr["+i+"]/td[6]";
            WebElement addToCartElement = BaseUtil.getDriver().findElement(By.xpath(addToCartXPath));

            String productNameXPath = "//*[@class=\"shop_table cart wishlist_table wishlist_view traditional responsive   \"]//tbody/tr["+i+"]/td[3]";
            WebElement productNameElement = BaseUtil.getDriver().findElement(By.xpath(productNameXPath));

            String unitpriceRange=priceElement.getText();

            double unitPrice = Double.parseDouble(unitpriceRange.substring(unitpriceRange.lastIndexOf('£')+1));

            if(unitPrice < minimumPrice) {
                minimumPrice = unitPrice;
                lowestPriceAddToCartWebElement = addToCartElement;
                lowestPriceProductName = productNameElement.getText();
                lowestPrice = minimumPrice;
            }
        }
    }

    @When("I am able to add lowest price item to my cart")
    public void i_am_able_to_add_lowest_price_item_to_my_cart() throws InterruptedException {
        lowestPriceAddToCartWebElement.click();
        Thread.sleep(1000);
    }

    @Then("I am able to verify the item in my cart")
    public void i_am_able_to_verify_the_item_in_my_cart() throws InterruptedException {
        BaseUtil.getWishListPage().goToCart();
        Thread.sleep(1000);
        WebElement productNameElement=BaseUtil.getDriver().findElement(By.xpath("//*[@class=\"shop_table shop_table_responsive cart woocommerce-cart-form__contents\"]//tbody/tr[1]/td[3]"));
        String productName = productNameElement.getText();

        WebElement productPriceElement=BaseUtil.getDriver().findElement(By.xpath("//*[@class=\"shop_table shop_table_responsive cart woocommerce-cart-form__contents\"]//tbody/tr[1]/td[4]"));
        String productPrice = productPriceElement.getText();
        int lastPoundIndex=productPrice.lastIndexOf('£');
        String substring=productPrice.substring(lastPoundIndex+1);
        double price=Double.parseDouble(substring);

        Assert.assertEquals(productName, lowestPriceProductName);
        Assert.assertEquals(price, lowestPrice, 0.00);

        BaseUtil.getDriver().close();
    }
}
