package prpoductshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prpoductshop.domain.dtos.UserDtoQuery04;
import prpoductshop.domain.entities.Product;
import prpoductshop.domain.entities.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product>  findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal bigger,BigDecimal less);
//    @Query("SELECT p.name,p.price FROM prpoductshop.domain.entities.User as u INNER JOIN u.soldProducts as p " +
//            "where p.buyer is not null Group by p.buyer\n" +
//            "ORDER BY count(p.buyer) desc ,u.lastName")
/*@Query("SELECT p FROM prpoductshop.domain.entities.Product as p INNER JOIN  p.buyer as u\n" +
        "where p.buyer is not null Group by p.buyer\n" +
        "ORDER BY count(p.buyer) desc ,u.lastName")*/
    List<Product> findAllBySellerId(Long id);
}
