package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdRepository extends CrudRepository<Ad, Long> {

    List<Ad> findByTitle(String Title);
    List<Ad> findByTitleLike(String Title);
}