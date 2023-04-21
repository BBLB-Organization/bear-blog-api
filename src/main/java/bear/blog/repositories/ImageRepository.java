package bear.blog.repositories;

import bear.blog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByName(String name);
}
