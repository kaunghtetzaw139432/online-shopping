package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Subcat;

@Service

public interface SubcatService {

    Subcat add(Subcat subcat);

    List<Subcat> all();

    Subcat patch(int id, Subcat subcat);

    Subcat get(int id);

    void delete(int id);

    Subcat changeName(int id, String name);

    Subcat changeImage(int id, String image);
}
