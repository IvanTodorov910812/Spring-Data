package prpoductshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prpoductshop.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM prpoductshop.domain.entities.User as u INNER JOIN u.soldProducts as p " +
            "where p.buyer is not null ORDER BY u.lastName,u.firstName")
    List<User> findAllwithOneSellerAndBuyerQuery2();
    @Query("SELECT u FROM prpoductshop.domain.entities.User as u INNER JOIN u.soldProducts as p " +
            "where p.buyer is not null Group by u.id \n" +
            "ORDER BY count(p.seller),u.lastName")
    List<User> findAllWithOneSellerQuery04();
}
