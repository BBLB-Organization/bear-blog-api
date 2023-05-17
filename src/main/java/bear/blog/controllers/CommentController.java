package bear.blog.controllers;

import bear.blog.models.Comment;
import bear.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<List<Comment>> getAllCommentsPerBlogId(@PathVariable Integer blogId){
        List<Comment> listOfCommentsPerBlogId = this.commentService.getAllCommentsPerBlogId(blogId);
        ResponseEntity response;
        response = new ResponseEntity(listOfCommentsPerBlogId, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment savedComment = this.commentService.saveComment(comment);
        ResponseEntity response;
        response = new ResponseEntity(savedComment, HttpStatus.OK);
        return response;
    }

}
