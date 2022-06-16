package pageObjects;

import consts.Constants;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends AbstractPage {

    private String productName;

    private final By searchField = By.cssSelector("input.search-form__input");
    private final By suggestedGoods = By.cssSelector("a.suggest-goods");

    public HomePage proceedToHomePage() {
        proceedToPage(Constants.BusinessConfigs.BASE_URL);
        return this;
    }

    public HomePage searchProduct(String productName) {
        this.productName = productName;
        getElement(searchField).sendKeys(productName);
        return this;
    }

    public HomePage verifySuggestedItemContainsSearchQuery() {
        Assert.assertTrue(getElement(suggestedGoods).getText().contains(this.productName), "It");
        return this;
    }

    public ProductPage selectFromDropdown() {
        getElement(suggestedGoods).click();
        return new ProductPage();
    }

    public CartPage goToCart() {
        proceedToPage(Constants.BusinessConfigs.CART_PAGE_URL);
        return new CartPage();
    }
}
