import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;

public class CartTest extends BaseTest {
    @Parameters({"productName"})
    @Test(description = "Verify user can add item to cart.")
    public void verifyUserCanAddProductToCart(String productName) {
        new HomePage().proceedToHomePage()
                .searchProduct(productName)
                .verifySuggestedItemContainsSearchQuery()
                .selectFromDropdown()
                .verifyProductPageHasCorrectItem(productName)
                .addToCart()
                .verifyProductAddedToCart(productName)
                .closePopupIfDisplayed()
                .proceedToHomePage()
                .goToCart()
                .verifyProductSuccessfullyAddedToCart(productName);
    }
}
