package pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends AbstractPage {
    private final By cartProduct = By.cssSelector("a.cart-product__title");

    public void verifyProductSuccessfullyAddedToCart(String productName) {
        Assert.assertNotEquals(getElements(cartProduct).size(), 0, "Cart is empty.");
        Assert.assertTrue(getElement(cartProduct).getText().contains(productName), "Cart has incorrect item.");
    }
}
