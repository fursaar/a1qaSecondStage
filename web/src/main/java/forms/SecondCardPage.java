package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.EmulateKeysUtils;

public class SecondCardPage extends Form {
    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(getLocator(), "Unselect All");
    private ICheckBox randomCheckbox;
    private ILink uploadLink = getElementFactory().getLink(By.xpath("//a[contains(@class, 'upload')]"), "Upload link");
    private IButton nextButton = getElementFactory().getButton(By.xpath("//button[contains(text(), 'Next')]"), "Next button");

    public SecondCardPage() {
        super(By.xpath("//label[contains(@for, 'unselectall')]"), "Second card page");
    }

    public void selectCheckboxByIndex(int checkBoxIndex) {
        randomCheckbox = getElementFactory().getCheckBox(By.xpath(String.format("//div[@class = 'avatar-and-interests__interests-list__item'][%d]//label", checkBoxIndex)), "Random checkbox");
        randomCheckbox.click();
    }

    public void unselectAllCheckboxes() {
        unselectAllCheckbox.click();
    }

    public void clickUploadButton() {
        uploadLink.click();
    }

    public void clickToNext() {
        nextButton.click();
    }

    public enum Checkboxes {
        PONIES (1),
        POLO (2),
        DOUGH (3),
        SNAILS (4),
        BALLS (5),
        POSTITS (6),
        FAUCETS (7),
        ENVELOPPES (8),
        CABLES (9),
        QUESTIONS (10),
        SQUARES (11),
        PURPLE (12),
        COTTON (13),
        DRYWALL (14),
        CLOSETS (15),
        TIRES (16),
        WINDOWS (17),
        SELECT_ALL (18),
        MULLETS (19),
        CINNAMON (20),
        USELECT_ALL (21);

        private final int indexOfCheckbox;

        Checkboxes(int indexOfCheckbox) {
            this.indexOfCheckbox = indexOfCheckbox;
        }

        public int getIndexOfCheckbox() {
            return this.indexOfCheckbox;
        }
    }
}
