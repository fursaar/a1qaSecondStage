package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ThirdCardPage extends Form {
    public ThirdCardPage() {
        super(By.xpath("//div[@class='personal-details']"), "Third card page");
    }
}
