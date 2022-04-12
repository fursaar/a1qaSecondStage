package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.function.BooleanSupplier;

public class ProfilePage extends Form {
    private ILabel postLabel;
    private ILabel postAuthorLabel;
    private ILabel commentLabel;
    private IButton showNextCommentButton;
    private ILink commentAuthorLink;
    private ILink postPhotoLink;
    private IButton postLikeButton;

    public ProfilePage() {
        super(By.xpath("//div[@id='page_photos_module']"), "Photos");
    }

    public void clickToLikeButtonUnderPost(int postId) {
        setPostLikeButtonByPostId(postId);
        postLikeButton.click();
    }

    public boolean isPostDeleted(int postId) {
        setPostByPostId(postId);
        return postLabel.state().waitForNotDisplayed();
    }

    public String getNameOfPostAuthor(int postId) {
        setPostAuthorByPostId(postId);
        return postAuthorLabel.getText();
    }

    public String getPostPhotoLink(int postId) {
        setPostPhotoByPostId(postId);
        return postPhotoLink.getAttribute("href");
    }

    public String getPostText(int postId) {
        setPostByPostId(postId);
        return postLabel.getText();
    }

    public String getCommentText(int commentId) {
        setCommentByCommentId(commentId);
        return commentLabel.getText();
    }

    public void clickToShowNextCommentInPost(int postId) {
        setShowNextCommentButtonByPostId(postId);
        showNextCommentButton.click();
    }

    public String getAuthorNameInComment(int commentId) {
        setCommentAuthorByCommentId(commentId);
        return commentAuthorLink.getText();
    }

    public boolean isPostLiked(int postId) {
        postLikeButton = getElementFactory().getButton(By.xpath(String.format("//div[contains(@id, '%d')]//div[@class='like_btns']//div[contains(@class, '--active')]", postId)), String.format("Post like button under post with id=%d", postId));
        return postLikeButton.state().waitForDisplayed();
    }

    private void setPostByPostId(int postId) {
        postLabel = getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%d')]//div[contains(@class, 'wall_post_text')]", postId)), String.format("Comment with id - %d", postId));
    }

    private void setPostAuthorByPostId(int postId) {
        postAuthorLabel = getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%d')]//h5[@class='post_author']//a[@class='author']", postId)), String.format("Author name"));
    }

    private void setPostLikeButtonByPostId(int postId) {
        postLikeButton = getElementFactory().getButton(By.xpath(String.format("//div[contains(@id, '%d')]//div[@class='like_btns']//div[contains(@class, '--post')]", postId)), String.format("Like under comment with id=%d", postId));
    }

    private void setShowNextCommentButtonByPostId(int postId) {
        this.showNextCommentButton = getElementFactory().getButton(By.xpath(String.format("//a[contains(@href, '%d') and contains(@class, 'next')]", postId)), String.format("Show next comment in post under id=%d", postId));
    }

    private void setCommentAuthorByCommentId(int commentId) {
        commentAuthorLink = getElementFactory().getLink(By.xpath(String.format("//div[@class='reply_text']//div[contains(@id, '%d')]//div[contains(@class, 'text')]/ancestor::div[contains(@class, 'reply_content')]//a[@class='author']", commentId)), String.format("Author under comment id=%d", commentId));
    }

    private void setCommentByCommentId(int commentId) {
        commentLabel = getElementFactory().getLabel(By.xpath(String.format("//div[@class='reply_text']//div[contains(@id, '%d')]//div[contains(@class, 'text')]", commentId)), String.format("Comment under id=%d", commentId));
    }

    private void setPostPhotoByPostId(int postId) {
        postPhotoLink = getElementFactory().getLink(By.xpath(String.format("//div[contains(@id, '%d')]//a[contains(@href, 'photo')]", postId)), String.format("Photo under comment with id=%d", postId));
    }
}
