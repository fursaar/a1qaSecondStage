package pojos.photos;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PhotoResponse {
    int id;
    int album_id;
    int owner_id;
    int user_id;
    private String text;
    private int date;
    private ArrayList<Object> sizes;
}
