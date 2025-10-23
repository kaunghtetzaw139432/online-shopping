package bm.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bm.coder.shop.models.Subcat;

@Repository
public interface SubcatRepo extends JpaRepository<Subcat,Integer> {

}
