package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FirstCardPage extends Form {

    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder, 'Password')]"), "Password");
    private final ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder, 'email')]"), "Email");
    private final ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder, 'Domain')]"), "Domain");
    private final ICheckBox termsCheckbox = getElementFactory().getCheckBox(By.xpath("//label[contains(@for, 'terms')]"), "Accept terms");
    private final IComboBox domainComboBox = getElementFactory().getComboBox(By.xpath("//div[contains(@class, 'opener')]"), "Domain dropdown opener");
    private  IButton chooseDomainButton;
    private final ILink nextLink = getElementFactory().getLink(By.xpath("//a[@class ='button--secondary']"), "Next");
    private final IButton hideHelpFormButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'send-to-bottom')]"), "Close help form");
    private final IButton expandHelpFormButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'close')]"), "Expand help form");
    private final IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//button[contains(text(), 'no')]"), "Accept cookies");
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'timer')]"), "Timer");

    public FirstCardPage() {
        super(By.xpath("//div[@class='login-form']"), "First card page");
    }

    public void enterPassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void enterEmail(String email) {
        emailTextBox.clearAndType(email);
    }

    public void enterDomain(String domain) {
        domainTextBox.clearAndType(domain);
    }

    public void openDomainList() {
        domainComboBox.click();
    }

    public void setChooseDomainButton(String domain) {
        switch (domain) {
            case  (".org"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.org']"), "Domain chooser");
            case (".co.uk"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.co.uk']"), "Domain chooser");
            case (".net"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.net']"), "Domain chooser");
            case (".gov"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.gov']"), "Domain chooser");
            case (".de"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.de']"), "Domain chooser");
            case (".fr"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.fr']"), "Domain chooser");
            case (".nl"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.nl']"), "Domain chooser");
            case (".com"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.com']"), "Domain chooser");
            case (".be"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.be']"), "Domain chooser");
            case (".jpg"):
                chooseDomainButton = getElementFactory().getButton(By.xpath("//div[@class ='dropdown__list']//div[text() = '.jpg']"), "Domain chooser");
            default:
                AqualityServices.getLogger().error("Error in setChooseDomainButton method ( No such domain )");
        }
    }

    public void chooseDomain(String domainName) {
        setChooseDomainButton(domainName);
        chooseDomainButton.click();
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
        acceptCookiesButton.click();
    }

    public boolean isCookiesFormClosed() {
        return acceptCookiesButton.state().waitForNotDisplayed();
    }

    public String getTextFromTimer() {
        return timerLabel.getText();
    }
}
