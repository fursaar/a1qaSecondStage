package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.Button;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private final ILink startLink = getElementFactory().getLink(getLocator(), "Start");
    public MainPage() {
        super(By.xpath("//a[contains(@class, 'start')]"), "Start");
    }

    public void clickToStartLink() {
        startLink.click();
    }




}
