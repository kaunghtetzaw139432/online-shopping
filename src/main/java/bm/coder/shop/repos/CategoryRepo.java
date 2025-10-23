package bm.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bm.coder.shop.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
