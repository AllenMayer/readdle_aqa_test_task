package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends AbstractPage {

    private boolean popupIsDisplayed;

    private final By buyButton = By.cssSelector("button.buy-button");
    private final By popup = By.cssSelector(".modal__holder");
    private final By closePopupButton = By.cssSelector(".modal__close");
    private final By productTitleInCart = By.cssSelector(".cart-product__title");
    private final By productTitle = By.cssSelector("h1.product__title");
    private final By toastNotification = By.cssSelector(".notification__wrapper--success");

    public ProductPage verifyProductPageHasCorrectItem(String productName) {
        Assert.assertTrue(getElement(productTitle).getText().contains(productName), "Product page has incorrect item.");
        return this;
    }

    public ProductPage addToCart() {
        getElement(buyButton).click();
        return this;
    }

    public ProductPage verifyProductAddedToCart(String productName) {
        if (isDisplayed(popup)) {
            this.popupIsDisplayed = true;
            Assert.assertTrue(getElement(productTitleInCart).getText().contains(productName), "Product in the cart popup is incorrect.");
        } else {
            Assert.assertTrue(isDisplayed(toastNotification), "Toast notification is not displayed.");
            Assert.assertTrue(hasClass(getElement(buyButton), "buy-button_state_in-cart"), "Buy button has not changed.");
        }
        return this;
    }

    public HomePage closePopupIfDisplayed() {
        if(popupIsDisplayed) getElement(closePopupButton).click();
        return new HomePage();
    }

    private boolean hasClass(WebElement element, String myClass) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(myClass)) {
                return true;
            }
        }
        return false;
    }
}
