package bear.blog.models;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    @Lob
    byte[] data;

    public Image(){}

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public Image(Integer id, String name, byte[] data){
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
