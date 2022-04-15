package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProfilePage extends Form {

    public ProfilePage() {
        super(By.xpath("//div[@id='page_photos_module']"), "Profile page");
    }

    public void clickToLikeButtonUnderPost(int postId) {
        getPostLikeButtonByPostId(postId).click();
    }

    public boolean isPostDeleted(int postId) {
        return getPostLabelByPostId(postId).state().waitForNotDisplayed();
    }

    public String getNameOfPostAuthor(int postId) {
        return getPostAuthorLabelByPostId(postId).getText();
    }

    public String getPostPhotoLink(int postId) {
        return getPostPhotoLinkByPostId(postId).getAttribute("href");
    }

    public String getPostText(int postId) {
        return getPostLabelByPostId(postId).getText();
    }

    public String getCommentText(int commentId) {
        return getCommentLabelByCommentId(commentId).getText();
    }

    public void clickToShowNextCommentInPost(int postId) {
        getShowNextCommentButtonByPostId(postId).click();
    }

    public String getAuthorNameInComment(int commentId) {
        return getCommentAuthorLinkByCommentId(commentId).getText();
    }

    public boolean isPostLiked(int postId) {
        return getLikedPostLikeButtonByPostId(postId).state().waitForDisplayed();
    }

    private ILabel getPostLabelByPostId(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%d')]//div[contains(@class, 'wall_post_text')]", postId)), String.format("Comment with id - %d", postId));
    }

    private ILabel getPostAuthorLabelByPostId(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%d')]//h5[@class='post_author']//a[@class='author']", postId)), String.format("Author name"));
    }

    private IButton getPostLikeButtonByPostId(int postId) {
        return getElementFactory().getButton(By.xpath(String.format("//div[contains(@id, '%d')]//div[@class='like_btns']//div[contains(@class, '--post')]", postId)), String.format("Like under comment with id=%d", postId));
    }

    private IButton getShowNextCommentButtonByPostId(int postId) {
        return getElementFactory().getButton(By.xpath(String.format("//a[contains(@href, '%d') and contains(@class, 'next')]", postId)), String.format("Show next comment in post under id=%d", postId));
    }

    private ILink getCommentAuthorLinkByCommentId(int commentId) {
        return getElementFactory().getLink(By.xpath(String.format("//div[@class='reply_text']//div[contains(@id, '%d')]//div[contains(@class, 'text')]/ancestor::div[contains(@class, 'reply_content')]//a[@class='author']", commentId)), String.format("Author under comment id=%d", commentId));
    }

    private ILabel getCommentLabelByCommentId(int commentId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@class='reply_text']//div[contains(@id, '%d')]//div[contains(@class, 'text')]", commentId)), String.format("Comment under id=%d", commentId));
    }

    private ILink getPostPhotoLinkByPostId(int postId) {
        return getElementFactory().getLink(By.xpath(String.format("//div[contains(@id, '%d')]//a[contains(@href, 'photo')]", postId)), String.format("Photo under comment with id=%d", postId));
    }

    private IButton getLikedPostLikeButtonByPostId(int postId) {
        return  getElementFactory().getButton(By.xpath(String.format("//div[contains(@id, '%d')]//div[@class='like_btns']//div[contains(@class, '--active')]", postId)), String.format("Post like button under post with id=%d", postId));
    }
}
