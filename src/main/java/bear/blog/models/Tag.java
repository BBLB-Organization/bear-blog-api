package bear.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String tagNames;

    public Tag(){}

    public Tag(Integer id, String tagNames) {
        this.id = id;
        this.tagNames = tagNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagName(String tagNames) {
        this.tagNames = tagNames;
    }
}
