package forms;

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
    private final ILink nextPageLink = getElementFactory().getLink(By.className("button--secondary"), "Next page");
    private final IButton hideHelpFormButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'send-to-bottom')]"), "Close help form");
    private final IButton expandHelpFormButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'close')]"), "Expand help form");
    private final IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//button[contains(text(), 'no')]"), "Accept cookies");
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'timer')]"), "Timer");

    public FirstCardPage() {
        super(By.className("login-form"), "First card page");
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

    public void chooseDomain(Domain domain) {
        chooseDomainButton = getElementFactory().getButton(By.xpath(String.format("//div[@class ='dropdown__list']//div[text() = '%s']", domain.getDomainOption())), "Domain chooser");
        chooseDomainButton.click();
    }

    public void uncheckTerms() {
        termsCheckbox.click();
    }

    public void clickToNext() {
        nextPageLink.click();
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

    public enum Domain {
        ORG (".org"),
        CO_UK (".co.uk"),
        NET (".net"),
        GOV (".gov"),
        DE (".de"),
        FR (".fr"),
        NL (".nl"),
        COM (".com"),
        BE (".be"),
        JPG (".jpg");

        private final String domain;

        Domain(String domain) {
            this.domain = domain;
        }

        public String getDomainOption() {
            return this.domain;
        }
    }
}
