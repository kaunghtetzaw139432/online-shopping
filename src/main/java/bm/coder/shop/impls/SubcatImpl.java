package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.SubcatNotFoundException;
import bm.coder.shop.models.Subcat;
import bm.coder.shop.repos.SubcatRepo;
import bm.coder.shop.services.SubcatService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubcatImpl implements SubcatService {
    @Autowired
    private final SubcatRepo subcatRepo;

    @Override
    public Subcat add(Subcat subcat) {
        return subcatRepo.save(subcat);
    }

    @Override
    public List<Subcat> all() {
        return subcatRepo.findAll();
    }

    @Override
    public Subcat patch(int id, Subcat subcat) {
        Subcat cat = get(id);
        if (cat != null) {
            cat.setName(subcat.getName());
            cat.setImage(subcat.getImage());
            return subcatRepo.save(cat);
        }
        return null;
    }

    @Override
    public Subcat get(int id) {
        return subcatRepo.findById(id).orElseThrow(() -> new SubcatNotFoundException("No subcats with that id"));
    }

    @Override
    public void delete(int id) {
        Subcat cat = get(id);
        if (cat != null) {
            subcatRepo.deleteById(id);
        }
    }

    @Override
    public Subcat changeName(int id, String name) {
        Subcat cat = get(id);
        if (cat != null) {
            cat.setName(name);
            subcatRepo.save(cat);
        }
        return null;
    }

    @Override
    public Subcat changeImage(int id, String image) {
        Subcat cat = get(id);
        if (cat != null) {
            cat.setImage(image);
            subcatRepo.save(cat);
        }
        return null;
    }

}
