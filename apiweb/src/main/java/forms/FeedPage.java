package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {
    private final IButton profile = getElementFactory().getButton(By.xpath("//*[@id='l_pr']"), "Profile");

    public FeedPage() {
        super(By.id("l_pr"), "Profile");
    }

    public void clickToProfile() {
        profile.click();
    }
}
