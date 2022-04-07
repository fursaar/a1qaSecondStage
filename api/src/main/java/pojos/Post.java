package pojos;


import lombok.Data;

@Data
public class Post {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return String.format("PostPojo{ \nuserId: %d, \nid: %d, \ntitle: '%s', \nbody '%s'\n}", userId, id, title, body);
    }
}
