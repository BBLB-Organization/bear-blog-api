package services;

import models.Blog;
import org.springframework.stereotype.Service;
import repositories.BlogRepository;

import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs(){
        return this.blogRepository.findAll();
    }

    public Blog addBlog(Blog blog){
        return this.blogRepository.save(blog);
    }

}
