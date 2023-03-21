package gigjob.firebase.storage;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String FOLDER = "image/";
    @Value("${firebase.bucket-name}")
    private String bucketName;
    @Value("${firebase.image-url}")
    private String imageUrl;

    public String saveFile(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileId = UUID.randomUUID().toString();
        String fileName = fileId + "." + filenameExtension;
        String filePath = FOLDER + fileName;
        bucket.create(filePath, file.getBytes(), file.getContentType());
        return fileName;
    }

    // Need access permissions
    public String getImageUrl(String fileName) {
        // '/' is different from '%2F'
        // '/' is for file path
        // '%2F' is for GET method when calling image url
        return String.format(imageUrl, "image%2F" + fileName);
    }

    public boolean deleteImage(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket(bucketName);
        return bucket.get(FOLDER + fileName).delete();
    }

    public String getFilename(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("%2F") + 3, imageUrl.indexOf("?alt"));
    }
}
