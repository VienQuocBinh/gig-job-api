package gigjob.firebase.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${firebase.google-credentials}")
    private String googleCredential;
    @Value("${firebase.bucket-name}")
    private String bucketName;
    @Value("${firebase.image-url}")
    private String imageUrl;

    private StorageOptions storageOptions;

    @EventListener
    public void initialize(ApplicationReadyEvent event) {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(googleCredential).getInputStream());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .setStorageBucket(bucketName)
                    .build();
            this.storageOptions = StorageOptions.newBuilder()
                    .setCredentials(googleCredentials).build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String bucketName = "image/";
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileId = UUID.randomUUID().toString();
        String fileName = fileId + "." + filenameExtension;
        String filePath = bucketName + fileName;
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
}
