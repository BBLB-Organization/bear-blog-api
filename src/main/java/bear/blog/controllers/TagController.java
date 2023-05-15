package bear.blog.controllers;

import bear.blog.models.Tag;
import bear.blog.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag-list")
@CrossOrigin("*")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagListById(@PathVariable Integer id){
        Tag tagList = this.tagService.getTagListById(id);
        ResponseEntity response;
        response = new ResponseEntity(tagList, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity saveTagList(@RequestBody Tag tagList){
        System.out.println(tagList.getTagNames());
        Tag finalTagList = this.tagService.saveTagList(tagList);
        ResponseEntity response;
        response = new ResponseEntity(finalTagList, HttpStatus.OK);
        return response;
    }


}
