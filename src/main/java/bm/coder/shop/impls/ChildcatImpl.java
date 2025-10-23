package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.ChildcatNotFoundException;
import bm.coder.shop.models.Childcat;
import bm.coder.shop.repos.ChildcatRepo;
import bm.coder.shop.services.ChildcatService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChildcatImpl implements ChildcatService {
    @Autowired
    private final ChildcatRepo childcatRepo;

    @Override
    public Childcat add(Childcat childcat) {
        return childcatRepo.save(childcat);
    }

    @Override
    public List<Childcat> all() {
        return childcatRepo.findAll();
    }

    @Override
    public void delete(int id) {
        Childcat cat = get(id);
        if (cat != null) {
            childcatRepo.deleteById(id);
        }
    }

    @Override
    public Childcat get(int id) {
        return childcatRepo.findById(id).orElseThrow(() -> new ChildcatNotFoundException("no childcat with that id"));
    }

    @Override
    public void changeName(int id, String name) {
        Childcat cat = get(id);
        if (cat != null) {
            cat.setName(name);
            childcatRepo.save(cat);
        }
    }

    @Override
    public void changeImage(int id, String image) {
        Childcat cat = get(id);
        if (cat != null) {
            cat.setName(image);
            childcatRepo.save(cat);
        }
    }

    @Override
    public void patch(int id, Childcat childcat) {
      Childcat cat=get(id);
      if (cat!=null) {
        cat.setName(childcat.getName());
        cat.setImage(childcat.getImage());
        childcatRepo.save(cat);
      }
    }

}
