package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final IButton signInButton = getElementFactory().getButton(By.xpath("//div[@id='index_login']//button[contains(@class, 'signIn')]"), "Sign In");

    public MainPage() {
        super(By.id("index_login"), "Main page");
    }

    public void clickToSignIn() {
        signInButton.click();
    }

}
