package gigjob.firebase.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
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
    private Storage storage;

    @EventListener
    public void initialize(ApplicationReadyEvent event) {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(googleCredential).getInputStream());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .setStorageBucket(bucketName)
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String bucketName = "image";
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = bucketName + "/" + UUID.randomUUID() + "." + filenameExtension;
        bucket.create(fileName, file.getBytes(), file.getContentType());

        return fileName;
    }

    public String getImageUrl(String name) {
        return String.format(imageUrl, name);
    }
}
