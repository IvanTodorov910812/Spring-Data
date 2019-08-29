import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Application04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Employee> employee= em.createQuery("FROM Employee WHERE salary>50000", Employee.class)
                .getResultList();
        for (Employee employee1 : employee) {
            System.out.println(employee1.getFirstName());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
