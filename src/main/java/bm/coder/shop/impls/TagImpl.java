package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.TagNotFoundException;
import bm.coder.shop.models.Tag;
import bm.coder.shop.repos.TagRepo;
import bm.coder.shop.services.TagService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagImpl implements TagService {
    @Autowired
    private final TagRepo tagRepo;

    @Override
    public Tag add(Tag tag) {
        return tagRepo.save(tag);
    }

    @Override
    public List<Tag> all() {
        return tagRepo.findAll();
    }

    @Override
    public Tag get(int id) {
        return tagRepo.findById(id).orElseThrow(()->new TagNotFoundException("No tags with that id"));
    }

    @Override
    public void update(int id, Tag tag) {
        Tag dbtag = get(id);
        if (dbtag != null) {
            dbtag.setName(tag.getName());
            dbtag.setImage(tag.getImage());
            tagRepo.save(dbtag);

        }
    }

    @Override
    public void drop(int id) {
        Tag dbtag = get(id);
        if (dbtag != null) {
            tagRepo.deleteById(id);

        }
    }

    @Override
    public void changeName(int id, String name) {
       Tag tag=get(id);
       if (tag!=null) {
        tag.setName(name);
        tagRepo.save(tag);
       }
    }

}
