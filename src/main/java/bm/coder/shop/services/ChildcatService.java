package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Childcat;

@Service
public interface ChildcatService {
    Childcat add(Childcat childcat);

    List<Childcat> all();

    void delete(int id);

    Childcat get(int id);

    void changeName(int id,String name);

    void patch(int id,Childcat childcat);

    void changeImage(int id,String image);
}
