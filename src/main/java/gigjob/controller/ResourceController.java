package gigjob.controller;

import gigjob.firebase.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResourceController {
    private final FileStorageService fileStorageService;

    @PostMapping("/v1/resource/upload")
    public ResponseEntity<Object> upload(@RequestParam(name = "file") MultipartFile file) {
        try {
            String fileName = fileStorageService.saveFile(file);
            String imageUrl = fileStorageService.getImageUrl(fileName);
            System.out.println("ResourceController.create" + imageUrl);
            log.info("Upload " + fileName + " image url: " + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/resource/delete")
    public ResponseEntity<Object> delete(String fileName) {
        try {
            if (fileStorageService.deleteImage(fileName)) {
                System.out.println("delete file successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
