package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Tag;

@Service
public interface TagService {
    Tag add(Tag tag);

    List<Tag> all();

    Tag get(int id);

    void update(int id, Tag tag);

    void drop(int id);

    void changeName(int id, String name);

}
