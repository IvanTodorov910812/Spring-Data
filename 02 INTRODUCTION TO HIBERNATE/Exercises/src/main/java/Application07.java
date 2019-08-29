import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Application07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
List<Address> addresses=em.createQuery("FROM Address ORDER BY size(employees) DESC,town.id ASC",Address.class).
        setMaxResults(10).getResultList();
        em.getTransaction().commit();
        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees\n",address.getText(),address.getTown(),address.getEmployees().size());
        }
            em.close();
            emf.close();

    }
}
