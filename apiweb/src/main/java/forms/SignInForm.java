package forms;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SignInForm extends Form {
    private final ITextBox loginTextBox = getElementFactory().getTextBox(getLocator(), getName());
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "Password Input");

    public SignInForm() {
        super(By.xpath("//div[contains(@class, 'EnterLogin__input')]//input[@name='login']"), "Login Input");
    }

    public void enterLogin(String login) {
        loginTextBox.type(login);
        loginTextBox.sendKeys(Keys.ENTER);
    }

    public void enterPassword(String password) {
        passwordTextBox.type(password);
        passwordTextBox.sendKeys(Keys.ENTER);
    }
}
