package bm.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bm.coder.shop.models.Childcat;

@Repository
public interface ChildcatRepo extends JpaRepository<Childcat,Integer> {

}
