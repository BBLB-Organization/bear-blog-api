package bear.blog.services;

import bear.blog.models.Blog;
import bear.blog.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Blog getBlogById(Integer id){
        return this.blogRepository.getReferenceById(id);
    }

    public Blog addBlog(Blog blog){
        LocalDate dateAsOfNow = LocalDate.now();
        blog.setCreatedOn(dateAsOfNow);
        return this.blogRepository.save(blog);
    }

    public Blog updateBlog(Blog newBlog){
        Blog oldBlog = this.blogRepository.getReferenceById(newBlog.getId());
        oldBlog.setCommentId(newBlog.getCommentId());
        return this.blogRepository.save(oldBlog);
    }

}
