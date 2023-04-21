package bear.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String blogText;
    String blogTitle;

    public Blog(){}

    public Blog(Integer id, String blogText, String blogTitle){
        this.id = id;
        this.blogText = blogText;
        this.blogTitle = blogTitle;
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
}

