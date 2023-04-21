package bear.blog.services;

import bear.blog.models.Image;
import bear.blog.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public Image getImageByName(String name){
        return imageRepository.findByName(name);
    }

    public Image getImageById(Integer id){
        return imageRepository.getReferenceById(id);
    }

    public List<String> getAllImageNames(){
        List<Image> listOfImages = imageRepository.findAll();
        List<String> listOfImageNames = new ArrayList<>();
        for(Image image: listOfImages){
            listOfImageNames.add(image.getName());
        }
        return listOfImageNames;
    }

    public Image saveImage(String name, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(name);
        image.setData(file.getBytes());

        return imageRepository.save(image);
    }


}
