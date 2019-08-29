package prpoductshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prpoductshop.domain.dtos.CategoryDtoQuery03;
import prpoductshop.domain.entities.Category;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT COUNT(p.buyer),SUM(p.price) FROM prpoductshop.domain.entities.Category  as c INNER JOIN c.products as p where  p.buyer is not null group by c.id  ORDER BY c.products.size DESC")
     List<Object[]> findCategoryByuerIsNotNull();
    @Query("SELECT c.name, c.products.size, AVG(p.price),SUM(p.price) FROM prpoductshop.domain.entities.Category  as c INNER JOIN c.products as p group by c.id  ORDER BY c.products.size DESC")
    List<Object[]> findAllOrderByNumberProducts();
}
