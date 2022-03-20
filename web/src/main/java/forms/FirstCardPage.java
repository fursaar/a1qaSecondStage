package forms;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FirstCardPage extends Form {

    private final ITextBox passwordField = getElementFactory().getTextBox(By.xpath("//div[@id='app']//input[contains(@placeholder, 'Password')]"), "Password");
    private final ITextBox emailField = getElementFactory().getTextBox(By.xpath("//div[@id='app']//input[contains(@placeholder, 'email')]"), "Email");
    private final ITextBox domainField = getElementFactory().getTextBox(By.xpath("//div[@id='app']//input[contains(@placeholder, 'Domain')]"), "Domain");
    private final ICheckBox termsCheckbox = getElementFactory().getCheckBox(By.xpath("//div[@id='app']//label[contains(@for, 'terms')]"), "Accept terms");
    private final IComboBox domainDropdownOpener = getElementFactory().getComboBox(By.xpath("//div[@id='app']//div[contains(@class, 'opener')]"), "Domain dropdown opener");
    private final IButton orgDomain =  getElementFactory().getButton(By.xpath("//div[@id='app']//div[@class ='dropdown__list']//div[text() = '.org']"), "Domain chooser");
    private final ILink nextLink = getElementFactory().getLink(By.xpath("//div[@id='app']//a[@class ='button--secondary']"), "Next");
    private final IButton hideHelpFormButton = getElementFactory().getButton(By.xpath("//div[@id='app']//button[contains(@class, 'send-to-bottom')]"), "Close help form");
    private final IButton expandHelpFormButton = getElementFactory().getButton(By.xpath("//div[@id='app']//button[contains(@class, 'close')]"), "Expand help form");
    private final IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//div[@class='cookies']//button[contains(text(), 'no')]"), "Accept cookies");
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[@id='app']//div[contains(@class, 'timer')]"), "Timer");

    public FirstCardPage() {
        super(By.xpath("//div[@id='app']//div[@class='login-form']"), "Login form");
    }

    public void enterPassword(String password) {
        passwordField.click();
        passwordField.clearAndType(password);
    }

    public void enterEmail(String email) {
        emailField.click();
        emailField.clearAndType(email);
    }

    public void enterDomain(String domain) {
        domainField.click();
        domainField.clearAndType(domain);
    }

    public void openDomainList() {
        domainDropdownOpener.click();
    }

    public void chooseOrgDomain() {
        orgDomain.click();
    }

    public void uncheckTerms() {
        termsCheckbox.click();
    }

    public void clickToNext() {
        nextLink.click();
    }

    public void hideHelpForm() {
        hideHelpFormButton.click();
    }

    public boolean waitForHelpFormClosing() {
        return expandHelpFormButton.state().waitForNotDisplayed();
    }

    public void acceptCookies() {
        acceptCookiesButton.state().waitForDisplayed();
        acceptCookiesButton.click();
    }

    public boolean isCookiesFormClosed() {
        return acceptCookiesButton.state().waitForNotDisplayed();
    }

    public String getTextFromTimer() {
        return timerLabel.getText();
    }
}
