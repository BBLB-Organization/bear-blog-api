package bear.blog.services;

import bear.blog.models.Blog;
import bear.blog.models.Comment;
import bear.blog.repositories.BlogRepository;
import bear.blog.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository){
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public Comment getCommentById(Integer id){
        return this.commentRepository.getReferenceById(id);
    }

    public List<Comment> getAllCommentsPerBlogId(Integer blogId){

        List<Comment> listOfCommentsPerBlogId = new ArrayList<>();

        Blog blog = this.blogRepository.getReferenceById(blogId);
        String listOfCommentIdsAsString = blog.getCommentId();
        String[] listOfCommentIdAsArray = listOfCommentIdsAsString.split(",");

        for(String stringCommentId: listOfCommentIdAsArray){
            Integer commentId = Integer.parseInt(stringCommentId);
            Comment comment = this.commentRepository.getReferenceById(commentId);
            listOfCommentsPerBlogId.add(comment);
        }
        return listOfCommentsPerBlogId;
    }

    public Comment saveComment(Comment comment){
        LocalDate dateAsOfNow = LocalDate.now();
        comment.setCommentPostDate(dateAsOfNow);
        return this.commentRepository.save(comment);
    }

}
