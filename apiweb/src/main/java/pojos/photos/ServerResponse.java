package pojos.photos;

import lombok.Data;

@Data
public class ServerResponse {
    private String upload_url;
    int album_id;
    private int user_id;
}
