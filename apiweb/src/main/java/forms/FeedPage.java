package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {
    private final IButton profileButton = getElementFactory().getButton(getLocator(), "Profile");

    public FeedPage() {
        super(By.id("l_pr"), "Feed page");
    }

    public void clickToProfile() {
        profileButton.click();
    }
}
