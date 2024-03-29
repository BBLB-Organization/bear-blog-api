package bear.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String blogText;
    String blogTitle;
    Integer imageId;
    Integer tagListId;
    String commentId;
    String userName;
    LocalDate createdOn;

    public Blog(){}

    public Blog(Integer id, String blogText, String blogTitle, Integer imageId, Integer tagListId, String commentId, String userName, LocalDate createdOn){
        this.id = id;
        this.blogText = blogText;
        this.blogTitle = blogTitle;
        this.imageId = imageId;
        this.tagListId = tagListId;
        this.commentId = commentId;
        this.userName = userName;
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getTagListId() {
        return tagListId;
    }

    public void setTagListId(Integer tagListId) {
        this.tagListId = tagListId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}

