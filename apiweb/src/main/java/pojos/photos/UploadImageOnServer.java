package pojos.photos;

import lombok.Data;

@Data
public class UploadImageOnServer {
    int server;
    String photo;
    String hash;
}
