package bear.blog.controllers;

import bear.blog.models.Blog;
import bear.blog.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
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

