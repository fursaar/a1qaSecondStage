package forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProfilePage extends Form {
    private ILabel postTextLabel;
    private ILabel postAuthorLabel;

    public ProfilePage() {
        super(By.xpath("//div[@id='page_photos_module']"), "Photos");
    }

    private void setPostText(int postId) {
        postTextLabel = getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%d')]//div[contains(@class, 'wall_post_text')]", postId)), String.format("Comment with id - %d", postId));
    }

    private void setPostAuthor(int postId) {
        postAuthorLabel = getElementFactory().getLabel(By.xpath(String.format("//a[@class='author' and contains(@data-post-id, '%d')]", postId)), String.format("Author name"));
    }

    public String getNameOfPostAuthorByPostId(int postId) {
        setPostAuthor(postId);
        return postAuthorLabel.getText();
    }

    public String getPostTextByPostId(int postId) {
        setPostText(postId);
        return postTextLabel.getText();
    }

}
