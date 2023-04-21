package bear.blog.controllers;

import bear.blog.models.Blog;
import bear.blog.models.Image;
import bear.blog.repositories.ImageRepository;
import bear.blog.services.BlogService;
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
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {

    private BlogService blogService;
    private ImageRepository imageRepository;

    public BlogController(BlogService blogService, ImageRepository imageRepository){
        this.blogService = blogService;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(){
        List<Blog> allBlogs = this.blogService.getAllBlogs();
        ResponseEntity response;
        response = new ResponseEntity(allBlogs,HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity addBlog(@RequestBody Blog blog){
        Blog newBlog = this.blogService.addBlog(blog);
        ResponseEntity response;
        response = new ResponseEntity(newBlog, HttpStatus.OK);
        return response;
    }


}

