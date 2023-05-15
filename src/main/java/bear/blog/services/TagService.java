package bear.blog.services;

import bear.blog.models.Tag;
import bear.blog.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Tag getTagListById(Integer tagId){
        Tag tags = this.tagRepository.getReferenceById(tagId);
        return tags;
    }

    public Tag saveTagList(Tag tags){
        return this.tagRepository.save(tags);
    }

}
