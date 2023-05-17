package bear.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String username;
    LocalDate commentPostDate;
    String commentText;

    public Comment(){}

    public Comment(Integer id, String username, LocalDate commentPostDate, String commentText) {
        this.id = id;
        this.username = username;
        this.commentPostDate = commentPostDate;
        this.commentText = commentText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCommentPostDate() {
        return commentPostDate;
    }

    public void setCommentPostDate(LocalDate commentPostDate) {
        this.commentPostDate = commentPostDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
