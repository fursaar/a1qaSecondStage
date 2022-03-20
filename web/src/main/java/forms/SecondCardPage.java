package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.EmulateKeysUtils;
import utils.RandomUtils;

public class SecondCardPage extends Form {

    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(By.xpath("//div[@id='app']//label[contains(@for, 'unselectall')]"), "Unselect All");
    private ICheckBox randomCheckbox;
    private ILink uploadLink = getElementFactory().getLink(By.xpath("//div[@id='app']//a[contains(@class, 'upload')]"), "Upload link");
    private IButton nextButton = getElementFactory().getButton(By.xpath("//div[@id='app']//button[contains(text(), 'Next')]"), "Next button");

    public SecondCardPage() {
        super(By.xpath("//div[@id='app']//label[contains(@for, 'unselectall')]"), "Unselect All");
    }

    public void selectRandomCheckbox() {
        randomCheckbox = getElementFactory().getCheckBox(By.xpath("//div[@id='app']//div[@class = 'avatar-and-interests__interests-list__item'][" + RandomUtils.generateRandomInterest() + "]//label"), "Random checkbox");
        randomCheckbox.click();
    }

    public void unselectAllCheckboxes() {
        unselectAllCheckbox.click();
    }

    public void uploadFile(String filePath) {
        uploadLink.click();
        EmulateKeysUtils.setClipboardData(filePath);
        EmulateKeysUtils.pasteClipboardData();
    }

    public void clickToNext() {
        nextButton.click();
    }

}
