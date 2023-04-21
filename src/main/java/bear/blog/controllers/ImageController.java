package bear.blog.controllers;

import bear.blog.models.Image;
import bear.blog.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("image")
@CrossOrigin("*")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam String name) throws IOException {
        Image image = imageService.getImageByName(name);
        return new ResponseEntity<>(image.getData(),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllImageNames(){
        List allImageNames = imageService.getAllImageNames();
        return new ResponseEntity<>(allImageNames,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("fileName") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //String pathName = "uploads/"+fileName;
        Image image = imageService.saveImage(fileName,file);
        return new ResponseEntity(image, HttpStatus.OK);
    }
}
