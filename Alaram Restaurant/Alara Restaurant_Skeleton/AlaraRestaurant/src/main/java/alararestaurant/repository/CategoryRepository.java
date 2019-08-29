package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String name);
    @Query("Select c from alararestaurant.domain.entities.Category c \n" +
            "join c.items i " +
            "Group by c order by size(c.items) desc,sum(i.price) desc")
    List<Category> findByCountItem();


}
